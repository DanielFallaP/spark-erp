package co.com.cybersoft.man.services.requisition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.purchase.docs.web.domain.requisition.RequisitionBodyInfo;
import co.com.cybersoft.purchase.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.purchase.docs.web.domain.requisitionDistribution.RequisitionDistributionBodyInfo;
import co.com.cybersoft.purchase.docs.web.domain.requisitionDistribution.RequisitionDistributionInfo;
import co.com.cybersoft.man.services.item.ItemVerificationSender;
import co.com.cybersoft.purchase.tables.persistence.domain.BusinessRules;
import co.com.cybersoft.purchase.tables.persistence.domain.User;
import co.com.cybersoft.purchase.tables.persistence.repository.businessRules.BusinessRulesRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.user.UserRepository;

public class RequisitionManServiceImpl implements RequisitionManService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BusinessRulesRepository businessRulesRepo;
	
	@Override
	public void checkAndSendVerificationMessage(String requestingUser,
			RequisitionInfo requisition) throws Exception {
		List<BusinessRules> all = businessRulesRepo.findAll();
		List<String> items=new ArrayList<String>();
		
		//Add the items for verification (those that doesn't have any code)
		List<RequisitionBodyInfo> requisitionBodyList = requisition.getRequisitionBodyList();
		if (requisition.getCheckDocument()!=null && requisition.getCheckDocument())
			for (RequisitionBodyInfo requisitionBody : requisitionBodyList) {
				if (requisitionBody.getItem()== null || requisitionBody.getItem().equals("")){
					items.add(requisitionBody.getItemDescription());
				}
			}
		
		//Set and send the message, if it's the case
		if (!all.isEmpty()&&!items.isEmpty()){
			BusinessRules businessRules = all.get(0);
			String chiefAdminUser = businessRules.getChiefAdminUser();
			User user = userRepository.findByUser(chiefAdminUser);
			User reqUser = userRepository.findByUser(requestingUser);
			new Thread(new ItemVerificationSender(items,user,reqUser)).start();
		}
	}

	/**
	 * Checks whether the requisition is ready, i.e, whether it has every
	 * piece of information needed to continue the process
	 */
	@Override
	public RequisitionInfo checkRequisition(RequisitionInfo requisitionInfo)
			throws Exception {
		if (requisitionInfo.getReady()){
			List<RequisitionBodyInfo> requisitionBodyList = requisitionInfo.getRequisitionBodyList();
			for (RequisitionBodyInfo requisitionBody : requisitionBodyList) {
				if (requisitionBody.getItem()== null || requisitionBody.getItem().equals("")){
					requisitionInfo.setCheckDocument(true);
					requisitionInfo.setReady(false);
					break;
				}
			}
		}
		return requisitionInfo;
	}

	/**
	 * Checks whether the distribution for the given requisition is complete
	 */
	@Override
	public void checkDistributionCompletion(
			RequisitionDistributionInfo requisitionDistributionInfo)throws Exception {
		if (requisitionDistributionInfo.getReady()){
			List<RequisitionDistributionBodyInfo> bodyList = requisitionDistributionInfo.getRequisitionDistributionBodyList();

			Set<String> compoundKey = new HashSet<String>();
			//AFE-account key check
			for (RequisitionDistributionBodyInfo requisitionDistributionBodyInfo : bodyList) {
				compoundKey.add(requisitionDistributionBodyInfo.getAfe()+"@@@@@"+requisitionDistributionBodyInfo.getInventoryAccount());
			}
			
			if (compoundKey.size()!=bodyList.size()){
				throw new Exception("label.requisitionDistributionKeyViolation");
			}
			
			//Percentage check
			Double percentage=0D;			
			for (RequisitionDistributionBodyInfo requisitionDistributionBodyInfo : bodyList) {
				if (requisitionDistributionBodyInfo.getDistributionPercentage()==null){
					requisitionDistributionInfo.setReady(Boolean.FALSE);
					throw new Exception("label.requisitionDistributionInvalidPercentage");
				}
				percentage+=requisitionDistributionBodyInfo.getDistributionPercentage();
			}
			if (percentage!=100){
				requisitionDistributionInfo.setReady(Boolean.FALSE);
				throw new Exception("label.requisitionDistributionInvalidPercentage");
			}
		}
	}
}
