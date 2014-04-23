package co.com.cybersoft.core.services.calculusType;

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
public interface CalculusTypeService {
	CalculusTypeDetailsEvent createCalculusType(CreateCalculusTypeEvent event ) throws Exception;
	
	CalculusTypePageEvent requestCalculusTypePage(RequestCalculusTypePageEvent event) throws Exception;

	CalculusTypeDetailsEvent requestCalculusTypeDetails(RequestCalculusTypeDetailsEvent event ) throws Exception;

	CalculusTypeDetailsEvent modifyCalculusType(CalculusTypeModificationEvent event) throws Exception;
	
	CalculusTypePageEvent requestAll() throws Exception;
	
}