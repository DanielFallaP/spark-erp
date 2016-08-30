package co.com.cybersoft.purchase.tables.persistence.services.continent;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.events.continent.CreateContinentEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentModificationEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepo;
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
public class ContinentPersistenceServiceImpl implements ContinentPersistenceService{

	private final ContinentRepository continentRepository;
	
	private final ContinentCustomRepo continentCustomRepo;
	
	
	public ContinentPersistenceServiceImpl(final ContinentRepository continentRepository, final ContinentCustomRepo continentCustomRepo) {
		this.continentRepository=continentRepository;
		this.continentCustomRepo=continentCustomRepo;
	}
	
	public ContinentDetailsEvent createContinent(CreateContinentEvent event) throws Exception{
		Continent continent = new Continent().fromContinentDetails(event.getContinentDetails());
		continent = continentRepository.save(continent);
		continent = continentRepository.findOne(continent.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",continent.getId());
		return new ContinentDetailsEvent(new ContinentDetails().toContinentDetails(continent));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"continent", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ContinentPageEvent requestContinentPage(RequestContinentPageEvent event) throws Exception {
		Page<Continent> continents = continentRepository.findAll(event.getPageable());
		return new ContinentPageEvent(continents);
	}

	public ContinentDetailsEvent requestContinentDetails(RequestContinentDetailsEvent event) throws Exception {
		ContinentDetails continentDetails=null;
		if (event.getId()!=null){
			Continent continent = continentRepository.findOne(event.getId());
			if (continent!=null)
				continentDetails = new ContinentDetails().toContinentDetails(continent);
		}
		else{
							Continent continent = new ContinentPersistenceFactory(continentRepository).getContinentByField(event.getField(),event.getValue());
							if (continent!=null)
								continentDetails = new ContinentDetails().toContinentDetails(continent);
						}
		return new ContinentDetailsEvent(continentDetails);
		
	}

	public ContinentDetailsEvent modifyContinent(ContinentModificationEvent event) throws Exception {
		Continent continent = new Continent().fromContinentDetails(event.getContinentDetails());
		continent = continentRepository.save(continent);
		continent = continentRepository.findOne(continent.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",continent.getId());
		return new ContinentDetailsEvent(new ContinentDetails().toContinentDetails(continent));
	}
	
		public ContinentDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Continent> all = continentRepository.findAll();
			ContinentDetails single = new ContinentDetails();
			for (Continent continent : all) {
				single=new ContinentDetails().toContinentDetails(continent);
				break;
			}
			return new ContinentDetailsEvent(single);
		}
	
	public ContinentPageEvent requestAllByContinent(EmbeddedField... fields) throws Exception {
			List<Continent> all = continentCustomRepo.findAllActiveByContinent(fields);
			List<ContinentDetails> list = new ArrayList<ContinentDetails>();
			for (Continent continent : all) {
				list.add(new ContinentDetails().toContinentDetails(continent, fields));
			}
			return new ContinentPageEvent(list);
		}
	
	public ContinentPageEvent requestByContainingContinent(String continent) throws Exception {
			ArrayList<ContinentDetails> list = new ArrayList<ContinentDetails>();
			List<Continent> continentList = continentCustomRepo.findByContainingContinent(continent);
			for (Continent continentEntity : continentList) {
				list.add(new ContinentDetails().toContinentDetails(continentEntity));
			}
			return new ContinentPageEvent(list);
		}

	public ContinentPageEvent requestContinentFilterPage(RequestContinentPageEvent event) throws Exception {
		Page<Continent> page = continentCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ContinentPageEvent(page, continentCustomRepo.getTotalCount());
	}
	
	public ContinentPageEvent requestContinentFilter(
			RequestContinentPageEvent event) throws Exception {
		List<Continent> all = continentCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ContinentPageEvent pageEvent = new ContinentPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}