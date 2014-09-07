package co.com.cybersoft.man.services.purchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.docs.persistence.repository.purchaseOrder.PurchaseOrderRepository;
import co.com.cybersoft.docs.web.domain.purchaseOrder.PurchaseOrderInfo;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.tables.persistence.domain.ThirdParty;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.tables.persistence.repository.thirdParty.ThirdPartyRepository;
import co.com.cybersoft.tables.persistence.repository.user.UserRepository;

public class PurchaseOrderManServiceImpl implements PurchaseOrderManService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ThirdPartyRepository thirdPartyRepo;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	private ReportingService reportingService;
	
	@Override
	public void processPurchaseOrder(String requestingUser, PurchaseOrderInfo purchaseOrderInfo)throws Exception {
		User user = userRepo.findByUser(requestingUser);
		ThirdParty thirdParty = thirdPartyRepo.findByThirdParty(purchaseOrderInfo.getThirdParty());
		if (purchaseOrderInfo.getReady()){
			new Thread(new PurchaseOrderSender(user, purchaseOrderInfo.getNumericId(), purchaseOrderInfo, thirdParty, reportingService)).start();
		}
	}
}
