package co.com.cybersoft.persistence.services.corporation;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.CorporationDetails;
import co.com.cybersoft.events.corporation.CreateCorporationEvent;
import co.com.cybersoft.events.corporation.CorporationDetailsEvent;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.events.corporation.CorporationModificationEvent;
import co.com.cybersoft.events.corporation.RequestCorporationDetailsEvent;
import co.com.cybersoft.events.corporation.RequestCorporationPageEvent;
import co.com.cybersoft.persistence.domain.Corporation;
import co.com.cybersoft.persistence.repository.CorporationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CorporationPersistenceServiceImpl implements CorporationPersistenceService{

	private final CorporationRepository corporationRepository;
	
	public CorporationPersistenceServiceImpl(final CorporationRepository corporationRepository) {
		this.corporationRepository=corporationRepository;
	}
	
	@Override
	public CorporationDetailsEvent createCorporation(CreateCorporationEvent event) throws Exception{
		Corporation corporation = Corporation.fromCorporationDetails(event.getCorporationDetails());
		corporation = corporationRepository.save(corporation);
		return new CorporationDetailsEvent(corporation.toCorporationDetails());
	}

	@Override
	public CorporationPageEvent requestCorporationPage(RequestCorporationPageEvent event) throws Exception {
		Page<Corporation> corporations = corporationRepository.findAll(event.getPageable());
		return new CorporationPageEvent(corporations);
	}

	@Override
	public CorporationDetailsEvent requestCorporationDetails(RequestCorporationDetailsEvent event) throws Exception {
		Corporation corporation = corporationRepository.findByCode(event.getId());
		CorporationDetails corporationDetails = corporation.toCorporationDetails();
		return new CorporationDetailsEvent(corporationDetails);
	}

	@Override
	public CorporationDetailsEvent modifyCorporation(CorporationModificationEvent event) throws Exception {
		Corporation corporation = Corporation.fromCorporationDetails(event.getCorporationDetails());
		corporation = corporationRepository.save(corporation);
		return new CorporationDetailsEvent(corporation.toCorporationDetails());
	}
	
	@Override
	public CorporationPageEvent requestAll() throws Exception {
		List<Corporation> all = corporationRepository.findAll();
		List<CorporationDetails> list = new ArrayList<CorporationDetails>();
		for (Corporation corporation : all) {
			list.add(corporation.toCorporationDetails());
		}
		return new CorporationPageEvent(list);
	}

}