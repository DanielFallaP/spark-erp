package co.com.cybersoft.maintenance.tables.persistence.services.typeMaintenance;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.TypeMaintenanceDetails;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.CreateTypeMaintenanceEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.TypeMaintenanceModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenanceDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeMaintenance.RequestTypeMaintenancePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance.TypeMaintenanceRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance.TypeMaintenanceCustomRepo;
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
public class TypeMaintenancePersistenceServiceImpl implements TypeMaintenancePersistenceService{

	private final TypeMaintenanceRepository typeMaintenanceRepository;
	
	private final TypeMaintenanceCustomRepo typeMaintenanceCustomRepo;
	
	
	public TypeMaintenancePersistenceServiceImpl(final TypeMaintenanceRepository typeMaintenanceRepository, final TypeMaintenanceCustomRepo typeMaintenanceCustomRepo) {
		this.typeMaintenanceRepository=typeMaintenanceRepository;
		this.typeMaintenanceCustomRepo=typeMaintenanceCustomRepo;
	}
	
	public TypeMaintenanceDetailsEvent createTypeMaintenance(CreateTypeMaintenanceEvent event) throws Exception{
		TypeMaintenance typeMaintenance = new TypeMaintenance().fromTypeMaintenanceDetails(event.getTypeMaintenanceDetails());
		typeMaintenance = typeMaintenanceRepository.save(typeMaintenance);
		typeMaintenance = typeMaintenanceRepository.findOne(typeMaintenance.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",typeMaintenance.getId());
		return new TypeMaintenanceDetailsEvent(new TypeMaintenanceDetails().toTypeMaintenanceDetails(typeMaintenance));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"typeMaintenance", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public TypeMaintenancePageEvent requestTypeMaintenancePage(RequestTypeMaintenancePageEvent event) throws Exception {
		Page<TypeMaintenance> typeMaintenances = typeMaintenanceRepository.findAll(event.getPageable());
		return new TypeMaintenancePageEvent(typeMaintenances);
	}

	public TypeMaintenanceDetailsEvent requestTypeMaintenanceDetails(RequestTypeMaintenanceDetailsEvent event) throws Exception {
		TypeMaintenanceDetails typeMaintenanceDetails=null;
		if (event.getId()!=null){
			TypeMaintenance typeMaintenance = typeMaintenanceRepository.findOne(event.getId());
			if (typeMaintenance!=null)
				typeMaintenanceDetails = new TypeMaintenanceDetails().toTypeMaintenanceDetails(typeMaintenance);
		}
		return new TypeMaintenanceDetailsEvent(typeMaintenanceDetails);
		
	}

	public TypeMaintenanceDetailsEvent modifyTypeMaintenance(TypeMaintenanceModificationEvent event) throws Exception {
		TypeMaintenance typeMaintenance = new TypeMaintenance().fromTypeMaintenanceDetails(event.getTypeMaintenanceDetails());
		typeMaintenance = typeMaintenanceRepository.save(typeMaintenance);
		typeMaintenance = typeMaintenanceRepository.findOne(typeMaintenance.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",typeMaintenance.getId());
		return new TypeMaintenanceDetailsEvent(new TypeMaintenanceDetails().toTypeMaintenanceDetails(typeMaintenance));
	}
	
		public TypeMaintenanceDetailsEvent getOnlyRecord() throws Exception {
			Iterable<TypeMaintenance> all = typeMaintenanceRepository.findAll();
			TypeMaintenanceDetails single = new TypeMaintenanceDetails();
			for (TypeMaintenance typeMaintenance : all) {
				single=new TypeMaintenanceDetails().toTypeMaintenanceDetails(typeMaintenance);
				break;
			}
			return new TypeMaintenanceDetailsEvent(single);
		}
	
	public TypeMaintenancePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<TypeMaintenance> all = typeMaintenanceCustomRepo.findAllActiveByCompany(fields);
			List<TypeMaintenanceDetails> list = new ArrayList<TypeMaintenanceDetails>();
			for (TypeMaintenance typeMaintenance : all) {
				list.add(new TypeMaintenanceDetails().toTypeMaintenanceDetails(typeMaintenance, fields));
			}
			return new TypeMaintenancePageEvent(list);
		}public TypeMaintenancePageEvent requestAllByNameTypeMaintenance(EmbeddedField... fields) throws Exception {
			List<TypeMaintenance> all = typeMaintenanceCustomRepo.findAllActiveByNameTypeMaintenance(fields);
			List<TypeMaintenanceDetails> list = new ArrayList<TypeMaintenanceDetails>();
			for (TypeMaintenance typeMaintenance : all) {
				list.add(new TypeMaintenanceDetails().toTypeMaintenanceDetails(typeMaintenance, fields));
			}
			return new TypeMaintenancePageEvent(list);
		}
	

	public TypeMaintenancePageEvent requestTypeMaintenanceFilterPage(RequestTypeMaintenancePageEvent event) throws Exception {
		Page<TypeMaintenance> page = typeMaintenanceCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new TypeMaintenancePageEvent(page, typeMaintenanceCustomRepo.getTotalCount());
	}
	
	public TypeMaintenancePageEvent requestTypeMaintenanceFilter(
			RequestTypeMaintenancePageEvent event) throws Exception {
		List<TypeMaintenance> all = typeMaintenanceCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		TypeMaintenancePageEvent pageEvent = new TypeMaintenancePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}