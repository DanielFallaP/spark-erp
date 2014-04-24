package co.com.cybersoft.persistence.services.partner;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.events.partner.CreatePartnerEvent;
import co.com.cybersoft.events.partner.PartnerDetailsEvent;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.events.partner.PartnerModificationEvent;
import co.com.cybersoft.events.partner.RequestPartnerDetailsEvent;
import co.com.cybersoft.events.partner.RequestPartnerPageEvent;
import co.com.cybersoft.persistence.domain.Partner;
import co.com.cybersoft.persistence.repository.partner.PartnerRepository;
import co.com.cybersoft.persistence.repository.partner.PartnerCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PartnerPersistenceServiceImpl implements PartnerPersistenceService{

	private final PartnerRepository partnerRepository;
	
	private final PartnerCustomRepo partnerCustomRepo;
	
	public PartnerPersistenceServiceImpl(final PartnerRepository partnerRepository, final PartnerCustomRepo partnerCustomRepo) {
		this.partnerRepository=partnerRepository;
		this.partnerCustomRepo=partnerCustomRepo;
	}
	
	@Override
	public PartnerDetailsEvent createPartner(CreatePartnerEvent event) throws Exception{
		Partner partner = Partner.fromPartnerDetails(event.getPartnerDetails());
		partner = partnerRepository.save(partner);
		return new PartnerDetailsEvent(partner.toPartnerDetails());
	}

	@Override
	public PartnerPageEvent requestPartnerPage(RequestPartnerPageEvent event) throws Exception {
		Page<Partner> partners = partnerRepository.findAll(event.getPageable());
		return new PartnerPageEvent(partners);
	}

	@Override
	public PartnerDetailsEvent requestPartnerDetails(RequestPartnerDetailsEvent event) throws Exception {
		PartnerDetails partnerDetails=null;
		if (event.getId()!=null){
			Partner partner = partnerRepository.findByCode(event.getId());
			if (partner!=null)
				partnerDetails = partner.toPartnerDetails();
		}
		else{
					Partner partner = partnerRepository.findByDescription(event.getDescription());
					if (partner!=null)
						partnerDetails = partner.toPartnerDetails();
				}
		return new PartnerDetailsEvent(partnerDetails);
		
	}

	@Override
	public PartnerDetailsEvent modifyPartner(PartnerModificationEvent event) throws Exception {
		Partner partner = Partner.fromPartnerDetails(event.getPartnerDetails());
		partner = partnerRepository.save(partner);
		return new PartnerDetailsEvent(partner.toPartnerDetails());
	}
	
	@Override
	public PartnerPageEvent requestAll() throws Exception {
		List<Partner> all = partnerRepository.findAll();
		List<PartnerDetails> list = new ArrayList<PartnerDetails>();
		for (Partner partner : all) {
			list.add(partner.toPartnerDetails());
		}
		return new PartnerPageEvent(list);
	}
	
	@Override
	public PartnerPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Partner> codes = partnerCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<PartnerDetails> list = new ArrayList<PartnerDetails>();
		for (Partner partner : codes) {
			list.add(partner.toPartnerDetails());
		}
		return new PartnerPageEvent(list);
	}

	@Override
	public PartnerPageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<PartnerDetails> list = new ArrayList<PartnerDetails>();
		List<Partner> descriptions = partnerCustomRepo.findByContainingDescription(description);
		for (Partner partner : descriptions) {
			list.add(partner.toPartnerDetails());
		}
		return new PartnerPageEvent(list);
	}

}