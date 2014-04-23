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
import co.com.cybersoft.persistence.repository.ActiveRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ActivePersistenceServiceImpl implements ActivePersistenceService{

	private final ActiveRepository activeRepository;
	
	public ActivePersistenceServiceImpl(final ActiveRepository activeRepository) {
		this.activeRepository=activeRepository;
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
		Active active = activeRepository.findByCode(event.getId());
		ActiveDetails activeDetails = active.toActiveDetails();
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

}