package co.com.cybersoft.core.services.afe;

import co.com.cybersoft.events.afe.CreateAfeEvent;
import co.com.cybersoft.events.afe.AfeDetailsEvent;
import co.com.cybersoft.events.afe.AfePageEvent;
import co.com.cybersoft.events.afe.AfeModificationEvent;
import co.com.cybersoft.events.afe.RequestAfeDetailsEvent;
import co.com.cybersoft.events.afe.RequestAfePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AfeService {
	AfeDetailsEvent createAfe(CreateAfeEvent event ) throws Exception;
	
	AfePageEvent requestAfePage(RequestAfePageEvent event) throws Exception;

	AfeDetailsEvent requestAfeDetails(RequestAfeDetailsEvent event ) throws Exception;

	AfeDetailsEvent modifyAfe(AfeModificationEvent event) throws Exception;
}