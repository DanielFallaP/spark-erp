package co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.CreateAuthorizerCostCenterEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter.AuthorizerCostCenterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter.AuthorizerCostCenterCustomRepo;
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
public class AuthorizerCostCenterPersistenceServiceImpl implements AuthorizerCostCenterPersistenceService{

	private final AuthorizerCostCenterRepository authorizerCostCenterRepository;
	
	private final AuthorizerCostCenterCustomRepo authorizerCostCenterCustomRepo;
	
	
	public AuthorizerCostCenterPersistenceServiceImpl(final AuthorizerCostCenterRepository authorizerCostCenterRepository, final AuthorizerCostCenterCustomRepo authorizerCostCenterCustomRepo) {
		this.authorizerCostCenterRepository=authorizerCostCenterRepository;
		this.authorizerCostCenterCustomRepo=authorizerCostCenterCustomRepo;
	}
	
	public AuthorizerCostCenterDetailsEvent createAuthorizerCostCenter(CreateAuthorizerCostCenterEvent event) throws Exception{
		AuthorizerCostCenter authorizerCostCenter = new AuthorizerCostCenter().fromAuthorizerCostCenterDetails(event.getAuthorizerCostCenterDetails());
		authorizerCostCenter = authorizerCostCenterRepository.save(authorizerCostCenter);
		authorizerCostCenter = authorizerCostCenterRepository.findOne(authorizerCostCenter.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",authorizerCostCenter.getId());
		return new AuthorizerCostCenterDetailsEvent(new AuthorizerCostCenterDetails().toAuthorizerCostCenterDetails(authorizerCostCenter));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"authorizerCostCenter", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public AuthorizerCostCenterPageEvent requestAuthorizerCostCenterPage(RequestAuthorizerCostCenterPageEvent event) throws Exception {
		Page<AuthorizerCostCenter> authorizerCostCenters = authorizerCostCenterRepository.findAll(event.getPageable());
		return new AuthorizerCostCenterPageEvent(authorizerCostCenters);
	}

	public AuthorizerCostCenterDetailsEvent requestAuthorizerCostCenterDetails(RequestAuthorizerCostCenterDetailsEvent event) throws Exception {
		AuthorizerCostCenterDetails authorizerCostCenterDetails=null;
		if (event.getId()!=null){
			AuthorizerCostCenter authorizerCostCenter = authorizerCostCenterRepository.findOne(event.getId());
			if (authorizerCostCenter!=null)
				authorizerCostCenterDetails = new AuthorizerCostCenterDetails().toAuthorizerCostCenterDetails(authorizerCostCenter);
		}
		return new AuthorizerCostCenterDetailsEvent(authorizerCostCenterDetails);
		
	}

	public AuthorizerCostCenterDetailsEvent modifyAuthorizerCostCenter(AuthorizerCostCenterModificationEvent event) throws Exception {
		AuthorizerCostCenter authorizerCostCenter = new AuthorizerCostCenter().fromAuthorizerCostCenterDetails(event.getAuthorizerCostCenterDetails());
		authorizerCostCenter = authorizerCostCenterRepository.save(authorizerCostCenter);
		authorizerCostCenter = authorizerCostCenterRepository.findOne(authorizerCostCenter.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",authorizerCostCenter.getId());
		return new AuthorizerCostCenterDetailsEvent(new AuthorizerCostCenterDetails().toAuthorizerCostCenterDetails(authorizerCostCenter));
	}
	
		public AuthorizerCostCenterDetailsEvent getOnlyRecord() throws Exception {
			Iterable<AuthorizerCostCenter> all = authorizerCostCenterRepository.findAll();
			AuthorizerCostCenterDetails single = new AuthorizerCostCenterDetails();
			for (AuthorizerCostCenter authorizerCostCenter : all) {
				single=new AuthorizerCostCenterDetails().toAuthorizerCostCenterDetails(authorizerCostCenter);
				break;
			}
			return new AuthorizerCostCenterDetailsEvent(single);
		}
	
	public AuthorizerCostCenterPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception {
			List<AuthorizerCostCenter> all = authorizerCostCenterCustomRepo.findAllActiveByCostCenter(fields);
			List<AuthorizerCostCenterDetails> list = new ArrayList<AuthorizerCostCenterDetails>();
			for (AuthorizerCostCenter authorizerCostCenter : all) {
				list.add(new AuthorizerCostCenterDetails().toAuthorizerCostCenterDetails(authorizerCostCenter, fields));
			}
			return new AuthorizerCostCenterPageEvent(list);
		}public AuthorizerCostCenterPageEvent requestAllByResponsible(EmbeddedField... fields) throws Exception {
			List<AuthorizerCostCenter> all = authorizerCostCenterCustomRepo.findAllActiveByResponsible(fields);
			List<AuthorizerCostCenterDetails> list = new ArrayList<AuthorizerCostCenterDetails>();
			for (AuthorizerCostCenter authorizerCostCenter : all) {
				list.add(new AuthorizerCostCenterDetails().toAuthorizerCostCenterDetails(authorizerCostCenter, fields));
			}
			return new AuthorizerCostCenterPageEvent(list);
		}
	

	public AuthorizerCostCenterPageEvent requestAuthorizerCostCenterFilterPage(RequestAuthorizerCostCenterPageEvent event) throws Exception {
		Page<AuthorizerCostCenter> page = authorizerCostCenterCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new AuthorizerCostCenterPageEvent(page, authorizerCostCenterCustomRepo.getTotalCount());
	}
	
	public AuthorizerCostCenterPageEvent requestAuthorizerCostCenterFilter(
			RequestAuthorizerCostCenterPageEvent event) throws Exception {
		List<AuthorizerCostCenter> all = authorizerCostCenterCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		AuthorizerCostCenterPageEvent pageEvent = new AuthorizerCostCenterPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}