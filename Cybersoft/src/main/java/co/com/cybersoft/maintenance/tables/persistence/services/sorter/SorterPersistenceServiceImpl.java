package co.com.cybersoft.maintenance.tables.persistence.services.sorter;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;
import co.com.cybersoft.maintenance.tables.events.sorter.CreateSorterEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import co.com.cybersoft.maintenance.tables.persistence.repository.sorter.SorterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.sorter.SorterCustomRepo;
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
public class SorterPersistenceServiceImpl implements SorterPersistenceService{

	private final SorterRepository sorterRepository;
	
	private final SorterCustomRepo sorterCustomRepo;
	
	
	public SorterPersistenceServiceImpl(final SorterRepository sorterRepository, final SorterCustomRepo sorterCustomRepo) {
		this.sorterRepository=sorterRepository;
		this.sorterCustomRepo=sorterCustomRepo;
	}
	
	public SorterDetailsEvent createSorter(CreateSorterEvent event) throws Exception{
		Sorter sorter = new Sorter().fromSorterDetails(event.getSorterDetails());
		sorter = sorterRepository.save(sorter);
		sorter = sorterRepository.findOne(sorter.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",sorter.getId());
		return new SorterDetailsEvent(new SorterDetails().toSorterDetails(sorter));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"sorter", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public SorterPageEvent requestSorterPage(RequestSorterPageEvent event) throws Exception {
		Page<Sorter> sorters = sorterRepository.findAll(event.getPageable());
		return new SorterPageEvent(sorters);
	}

	public SorterDetailsEvent requestSorterDetails(RequestSorterDetailsEvent event) throws Exception {
		SorterDetails sorterDetails=null;
		if (event.getId()!=null){
			Sorter sorter = sorterRepository.findOne(event.getId());
			if (sorter!=null)
				sorterDetails = new SorterDetails().toSorterDetails(sorter);
		}
		return new SorterDetailsEvent(sorterDetails);
		
	}

	public SorterDetailsEvent modifySorter(SorterModificationEvent event) throws Exception {
		Sorter sorter = new Sorter().fromSorterDetails(event.getSorterDetails());
		sorter = sorterRepository.save(sorter);
		sorter = sorterRepository.findOne(sorter.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",sorter.getId());
		return new SorterDetailsEvent(new SorterDetails().toSorterDetails(sorter));
	}
	
		public SorterDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Sorter> all = sorterRepository.findAll();
			SorterDetails single = new SorterDetails();
			for (Sorter sorter : all) {
				single=new SorterDetails().toSorterDetails(sorter);
				break;
			}
			return new SorterDetailsEvent(single);
		}
	
	public SorterPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Sorter> all = sorterCustomRepo.findAllActiveByCompany(fields);
			List<SorterDetails> list = new ArrayList<SorterDetails>();
			for (Sorter sorter : all) {
				list.add(new SorterDetails().toSorterDetails(sorter, fields));
			}
			return new SorterPageEvent(list);
		}public SorterPageEvent requestAllByTypeSorter(EmbeddedField... fields) throws Exception {
			List<Sorter> all = sorterCustomRepo.findAllActiveByTypeSorter(fields);
			List<SorterDetails> list = new ArrayList<SorterDetails>();
			for (Sorter sorter : all) {
				list.add(new SorterDetails().toSorterDetails(sorter, fields));
			}
			return new SorterPageEvent(list);
		}public SorterPageEvent requestAllByNameSorter(EmbeddedField... fields) throws Exception {
			List<Sorter> all = sorterCustomRepo.findAllActiveByNameSorter(fields);
			List<SorterDetails> list = new ArrayList<SorterDetails>();
			for (Sorter sorter : all) {
				list.add(new SorterDetails().toSorterDetails(sorter, fields));
			}
			return new SorterPageEvent(list);
		}
	

	public SorterPageEvent requestSorterFilterPage(RequestSorterPageEvent event) throws Exception {
		Page<Sorter> page = sorterCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new SorterPageEvent(page, sorterCustomRepo.getTotalCount());
	}
	
	public SorterPageEvent requestSorterFilter(
			RequestSorterPageEvent event) throws Exception {
		List<Sorter> all = sorterCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		SorterPageEvent pageEvent = new SorterPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}