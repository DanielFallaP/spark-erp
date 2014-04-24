package co.com.cybersoft.persistence.services.active;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.ActiveDetails;
import co.com.cybersoft.events.active.CreateActiveEvent;
import co.com.cybersoft.events.active.ActiveDetailsEvent;
import co.com.cybersoft.events.active.ActivePageEvent;
import co.com.cybersoft.events.active.ActiveModificationEvent;
import co.com.cybersoft.events.active.RequestActiveDetailsEvent;
import co.com.cybersoft.events.active.RequestActivePageEvent;
import co.com.cybersoft.persistence.domain.Active;
import co.com.cybersoft.persistence.repository.active.ActiveRepository;
import co.com.cybersoft.persistence.repository.active.ActiveCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ActivePersistenceServiceImpl implements ActivePersistenceService{

	private final ActiveRepository activeRepository;
	
	private final ActiveCustomRepo activeCustomRepo;
	
	public ActivePersistenceServiceImpl(final ActiveRepository activeRepository, final ActiveCustomRepo activeCustomRepo) {
		this.activeRepository=activeRepository;
		this.activeCustomRepo=activeCustomRepo;
	}
	
	@Override
	public ActiveDetailsEvent createActive(CreateActiveEvent event) throws Exception{
		Active active = Active.fromActiveDetails(event.getActiveDetails());
		active = activeRepository.save(active);
		return new ActiveDetailsEvent(active.toActiveDetails());
	}

	@Override
	public ActivePageEvent requestActivePage(RequestActivePageEvent event) throws Exception {
		Page<Active> actives = activeRepository.findAll(event.getPageable());
		return new ActivePageEvent(actives);
	}

	@Override
	public ActiveDetailsEvent requestActiveDetails(RequestActiveDetailsEvent event) throws Exception {
		ActiveDetails activeDetails=null;
		if (event.getId()!=null){
			Active active = activeRepository.findByCode(event.getId());
			if (active!=null)
				activeDetails = active.toActiveDetails();
		}
		return new ActiveDetailsEvent(activeDetails);
		
	}

	@Override
	public ActiveDetailsEvent modifyActive(ActiveModificationEvent event) throws Exception {
		Active active = Active.fromActiveDetails(event.getActiveDetails());
		active = activeRepository.save(active);
		return new ActiveDetailsEvent(active.toActiveDetails());
	}
	
	@Override
	public ActivePageEvent requestAll() throws Exception {
		List<Active> all = activeRepository.findAll();
		List<ActiveDetails> list = new ArrayList<ActiveDetails>();
		for (Active active : all) {
			list.add(active.toActiveDetails());
		}
		return new ActivePageEvent(list);
	}
	
	@Override
	public ActivePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Active> codes = activeCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<ActiveDetails> list = new ArrayList<ActiveDetails>();
		for (Active active : codes) {
			list.add(active.toActiveDetails());
		}
		return new ActivePageEvent(list);
	}

	@Override
	public ActivePageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<ActiveDetails> list = new ArrayList<ActiveDetails>();
		List<Active> descriptions = activeCustomRepo.findByContainingDescription(description);
		for (Active active : descriptions) {
			list.add(active.toActiveDetails());
		}
		return new ActivePageEvent(list);
	}

}