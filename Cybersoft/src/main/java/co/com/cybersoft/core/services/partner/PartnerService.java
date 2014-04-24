package co.com.cybersoft.core.services.partner;

import co.com.cybersoft.events.partner.CreatePartnerEvent;
import co.com.cybersoft.events.partner.PartnerDetailsEvent;
import co.com.cybersoft.events.partner.PartnerPageEvent;
import co.com.cybersoft.events.partner.PartnerModificationEvent;
import co.com.cybersoft.events.partner.RequestPartnerDetailsEvent;
import co.com.cybersoft.events.partner.RequestPartnerPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface PartnerService {
	PartnerDetailsEvent createPartner(CreatePartnerEvent event ) throws Exception;
	
	PartnerPageEvent requestPartnerPage(RequestPartnerPageEvent event) throws Exception;

	PartnerDetailsEvent requestPartnerDetails(RequestPartnerDetailsEvent event ) throws Exception;

	PartnerDetailsEvent modifyPartner(PartnerModificationEvent event) throws Exception;
	
	PartnerPageEvent requestAll() throws Exception;
	
	PartnerPageEvent requestByCodePrefix(String code) throws Exception;
	
	PartnerPageEvent requestByContainingDescription(String description) throws Exception;
	
}