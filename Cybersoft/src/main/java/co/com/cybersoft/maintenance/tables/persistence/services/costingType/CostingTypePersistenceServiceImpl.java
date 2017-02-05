package co.com.cybersoft.maintenance.tables.persistence.services.costingType;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;
import co.com.cybersoft.maintenance.tables.events.costingType.CreateCostingTypeEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import co.com.cybersoft.maintenance.tables.persistence.repository.costingType.CostingTypeRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.costingType.CostingTypeCustomRepo;
//import co.com.cybersoft.man.services.security.SessionAction;
//import co.com.cybersoft.man.services.security.SessionLogger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostingTypePersistenceServiceImpl implements CostingTypePersistenceService{

	private final CostingTypeRepository costingTypeRepository;
	
	private final CostingTypeCustomRepo costingTypeCustomRepo;
	
	
	public CostingTypePersistenceServiceImpl(final CostingTypeRepository costingTypeRepository, final CostingTypeCustomRepo costingTypeCustomRepo) {
		this.costingTypeRepository=costingTypeRepository;
		this.costingTypeCustomRepo=costingTypeCustomRepo;
	}
	
	public CostingTypeDetailsEvent createCostingType(CreateCostingTypeEvent event) throws Exception{
		CostingType costingType = new CostingType().fromCostingTypeDetails(event.getCostingTypeDetails());
		costingType = costingTypeRepository.save(costingType);
		costingType = costingTypeRepository.findOne(costingType.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",costingType.getId());
		return new CostingTypeDetailsEvent(new CostingTypeDetails().toCostingTypeDetails(costingType));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"costingType", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CostingTypePageEvent requestCostingTypePage(RequestCostingTypePageEvent event) throws Exception {
		Page<CostingType> costingTypes = costingTypeRepository.findAll(event.getPageable());
		return new CostingTypePageEvent(costingTypes);
	}

	public CostingTypeDetailsEvent requestCostingTypeDetails(RequestCostingTypeDetailsEvent event) throws Exception {
		CostingTypeDetails costingTypeDetails=null;
		if (event.getId()!=null){
			CostingType costingType = costingTypeRepository.findOne(event.getId());
			if (costingType!=null)
				costingTypeDetails = new CostingTypeDetails().toCostingTypeDetails(costingType);
		}
		return new CostingTypeDetailsEvent(costingTypeDetails);
		
	}

	public CostingTypeDetailsEvent modifyCostingType(CostingTypeModificationEvent event) throws Exception {
		CostingType costingType = new CostingType().fromCostingTypeDetails(event.getCostingTypeDetails());
		costingType = costingTypeRepository.save(costingType);
		costingType = costingTypeRepository.findOne(costingType.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",costingType.getId());
		return new CostingTypeDetailsEvent(new CostingTypeDetails().toCostingTypeDetails(costingType));
	}
	
		public CostingTypeDetailsEvent getOnlyRecord() throws Exception {
			Iterable<CostingType> all = costingTypeRepository.findAll();
			CostingTypeDetails single = new CostingTypeDetails();
			for (CostingType costingType : all) {
				single=new CostingTypeDetails().toCostingTypeDetails(costingType);
				break;
			}
			return new CostingTypeDetailsEvent(single);
		}
	
	public CostingTypePageEvent requestAllByCostingType(EmbeddedField... fields) throws Exception {
			List<CostingType> all = costingTypeCustomRepo.findAllActiveByCostingType(fields);
			List<CostingTypeDetails> list = new ArrayList<CostingTypeDetails>();
			for (CostingType costingType : all) {
				list.add(new CostingTypeDetails().toCostingTypeDetails(costingType, fields));
			}
			return new CostingTypePageEvent(list);
		}
	

	public CostingTypePageEvent requestCostingTypeFilterPage(RequestCostingTypePageEvent event) throws Exception {
		Page<CostingType> page = costingTypeCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CostingTypePageEvent(page, costingTypeCustomRepo.getTotalCount());
	}
	
	public CostingTypePageEvent requestCostingTypeFilter(
			RequestCostingTypePageEvent event) throws Exception {
		List<CostingType> all = costingTypeCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CostingTypePageEvent pageEvent = new CostingTypePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}