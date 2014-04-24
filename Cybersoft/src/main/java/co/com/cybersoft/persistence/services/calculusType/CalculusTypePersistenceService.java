package co.com.cybersoft.persistence.services.calculusType;

import co.com.cybersoft.events.calculusType.CreateCalculusTypeEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.CalculusTypePageEvent;
import co.com.cybersoft.events.calculusType.CalculusTypeModificationEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypeDetailsEvent;
import co.com.cybersoft.events.calculusType.RequestCalculusTypePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CalculusTypePersistenceService {

	CalculusTypeDetailsEvent createCalculusType(CreateCalculusTypeEvent event) throws Exception;

	CalculusTypePageEvent requestCalculusTypePage(RequestCalculusTypePageEvent event) throws Exception;

	CalculusTypeDetailsEvent requestCalculusTypeDetails(RequestCalculusTypeDetailsEvent event) throws Exception;
	
	CalculusTypeDetailsEvent modifyCalculusType(CalculusTypeModificationEvent event) throws Exception;
	
	CalculusTypePageEvent requestAll() throws Exception;
	
	CalculusTypePageEvent requestByCodePrefix(String codePrefix) throws Exception;

	CalculusTypePageEvent requestByContainingDescription(String description) throws Exception;
	
}