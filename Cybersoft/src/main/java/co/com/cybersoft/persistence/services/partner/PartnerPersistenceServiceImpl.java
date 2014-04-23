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
import co.com.cybersoft.persistence.repository.PartnerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PartnerPersistenceServiceImpl implements PartnerPersistenceService{

	private final PartnerRepository partnerRepository;
	
	public PartnerPersistenceServiceImpl(final PartnerRepository partnerRepository) {
		this.partnerRepository=partnerRepository;
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
		Partner partner = partnerRepository.findByCode(event.getId());
		PartnerDetails partnerDetails = partner.toPartnerDetails();
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

}