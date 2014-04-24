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
import co.com.cybersoft.persistence.repository.corporation.CorporationRepository;
import co.com.cybersoft.persistence.repository.corporation.CorporationCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CorporationPersistenceServiceImpl implements CorporationPersistenceService{

	private final CorporationRepository corporationRepository;
	
	private final CorporationCustomRepo corporationCustomRepo;
	
	public CorporationPersistenceServiceImpl(final CorporationRepository corporationRepository, final CorporationCustomRepo corporationCustomRepo) {
		this.corporationRepository=corporationRepository;
		this.corporationCustomRepo=corporationCustomRepo;
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
		CorporationDetails corporationDetails=null;
		if (event.getId()!=null){
			Corporation corporation = corporationRepository.findByCode(event.getId());
			if (corporation!=null)
				corporationDetails = corporation.toCorporationDetails();
		}
		else{
					Corporation corporation = corporationRepository.findByDescription(event.getDescription());
					if (corporation!=null)
						corporationDetails = corporation.toCorporationDetails();
				}
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
	
	@Override
	public CorporationPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Corporation> codes = corporationCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<CorporationDetails> list = new ArrayList<CorporationDetails>();
		for (Corporation corporation : codes) {
			list.add(corporation.toCorporationDetails());
		}
		return new CorporationPageEvent(list);
	}

	@Override
	public CorporationPageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<CorporationDetails> list = new ArrayList<CorporationDetails>();
		List<Corporation> descriptions = corporationCustomRepo.findByContainingDescription(description);
		for (Corporation corporation : descriptions) {
			list.add(corporation.toCorporationDetails());
		}
		return new CorporationPageEvent(list);
	}

}