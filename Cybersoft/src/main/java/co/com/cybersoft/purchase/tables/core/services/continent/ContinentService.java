package co.com.cybersoft.purchase.tables.core.services.continent;

import co.com.cybersoft.purchase.tables.events.continent.CreateContinentEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;
import co.com.cybersoft.purchase.tables.events.continent.ContinentModificationEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentDetailsEvent;
import co.com.cybersoft.purchase.tables.events.continent.RequestContinentPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public interface ContinentService {
	ContinentDetailsEvent requestOnlyRecord() throws Exception;

	ContinentDetailsEvent createContinent(CreateContinentEvent event ) throws Exception;
	
	ContinentPageEvent requestContinentPage(RequestContinentPageEvent event) throws Exception;

	ContinentDetailsEvent requestContinentDetails(RequestContinentDetailsEvent event ) throws Exception;

	ContinentDetailsEvent modifyContinent(ContinentModificationEvent event) throws Exception;
	
	ContinentPageEvent requestAllByContinent(EmbeddedField... fields) throws Exception;

	
	ContinentPageEvent requestByContainingContinent(String continent) throws Exception;
	
	ContinentPageEvent requestContinentFilterPage(RequestContinentPageEvent event) throws Exception;
}