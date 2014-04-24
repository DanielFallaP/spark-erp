package co.com.cybersoft.persistence.services.afeType;

import co.com.cybersoft.events.afeType.CreateAfeTypeEvent;
import co.com.cybersoft.events.afeType.AfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.AfeTypePageEvent;
import co.com.cybersoft.events.afeType.AfeTypeModificationEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypeDetailsEvent;
import co.com.cybersoft.events.afeType.RequestAfeTypePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AfeTypePersistenceService {

	AfeTypeDetailsEvent createAfeType(CreateAfeTypeEvent event) throws Exception;

	AfeTypePageEvent requestAfeTypePage(RequestAfeTypePageEvent event) throws Exception;

	AfeTypeDetailsEvent requestAfeTypeDetails(RequestAfeTypeDetailsEvent event) throws Exception;
	
	AfeTypeDetailsEvent modifyAfeType(AfeTypeModificationEvent event) throws Exception;
	
	AfeTypePageEvent requestAll() throws Exception;
	
	AfeTypePageEvent requestByCodePrefix(String codePrefix) throws Exception;

	AfeTypePageEvent requestByContainingDescription(String description) throws Exception;
	
}