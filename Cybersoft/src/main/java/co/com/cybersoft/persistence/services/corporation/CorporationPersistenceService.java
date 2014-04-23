package co.com.cybersoft.persistence.services.corporation;

import co.com.cybersoft.events.corporation.CreateCorporationEvent;
import co.com.cybersoft.events.corporation.CorporationDetailsEvent;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.events.corporation.CorporationModificationEvent;
import co.com.cybersoft.events.corporation.RequestCorporationDetailsEvent;
import co.com.cybersoft.events.corporation.RequestCorporationPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CorporationPersistenceService {

	CorporationDetailsEvent createCorporation(CreateCorporationEvent event) throws Exception;

	CorporationPageEvent requestCorporationPage(RequestCorporationPageEvent event) throws Exception;

	CorporationDetailsEvent requestCorporationDetails(RequestCorporationDetailsEvent event) throws Exception;
	
	CorporationDetailsEvent modifyCorporation(CorporationModificationEvent event) throws Exception;
	
	CorporationPageEvent requestAll() throws Exception;
	
}