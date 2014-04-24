package co.com.cybersoft.core.services.partner;

import co.com.cybersoft.events.partner.CreatePartnerEvent;
import co.com.cybersoft.events.partner.PartnerDetailsEvent;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.events.partner.PartnerModificationEvent;
import co.com.cybersoft.events.partner.RequestPartnerDetailsEvent;
import co.com.cybersoft.events.partner.RequestPartnerPageEvent;
import co.com.cybersoft.persistence.services.partner.PartnerPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PartnerServiceImpl implements PartnerService{

	private final PartnerPersistenceService partnerPersistenceService;
	
	public PartnerServiceImpl(final PartnerPersistenceService partnerPersistenceService){
		this.partnerPersistenceService=partnerPersistenceService;
	}
	
	/**
	 */
	@Override
	public PartnerDetailsEvent createPartner(CreatePartnerEvent event ) throws Exception{
		return partnerPersistenceService.createPartner(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public PartnerPageEvent requestPartnerPage(RequestPartnerPageEvent event) throws Exception{
		return partnerPersistenceService.requestPartnerPage(event);
	}

	@Override
	public PartnerDetailsEvent requestPartnerDetails(RequestPartnerDetailsEvent event ) throws Exception{
		return partnerPersistenceService.requestPartnerDetails(event);
	}

	@Override
	public PartnerDetailsEvent modifyPartner(PartnerModificationEvent event) throws Exception {
		return partnerPersistenceService.modifyPartner(event);
	}
	
	@Override
	public PartnerPageEvent requestAll() throws Exception {
		return partnerPersistenceService.requestAll();
	}
	
	@Override
	public PartnerPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return partnerPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public PartnerPageEvent requestByContainingDescription(String description) throws Exception {
		return partnerPersistenceService.requestByContainingDescription(description);
	}
	
}