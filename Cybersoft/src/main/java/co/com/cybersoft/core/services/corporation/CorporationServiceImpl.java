package co.com.cybersoft.core.services.corporation;

import java.util.Date;

import co.com.cybersoft.events.corporation.CreateCorporationEvent;
import co.com.cybersoft.events.corporation.CorporationDetailsEvent;
import co.com.cybersoft.events.corporation.CorporationPageEvent;
import co.com.cybersoft.events.corporation.CorporationModificationEvent;
import co.com.cybersoft.events.corporation.RequestCorporationDetailsEvent;
import co.com.cybersoft.events.corporation.RequestCorporationPageEvent;
import co.com.cybersoft.persistence.services.corporation.CorporationPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CorporationServiceImpl implements CorporationService{

	private final CorporationPersistenceService corporationPersistenceService;
	
	public CorporationServiceImpl(final CorporationPersistenceService corporationPersistenceService){
		this.corporationPersistenceService=corporationPersistenceService;
	}
	
	/**
	 */
	@Override
	public CorporationDetailsEvent createCorporation(CreateCorporationEvent event ) throws Exception{
		return corporationPersistenceService.createCorporation(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public CorporationPageEvent requestCorporationPage(RequestCorporationPageEvent event) throws Exception{
		return corporationPersistenceService.requestCorporationPage(event);
	}

	@Override
	public CorporationDetailsEvent requestCorporationDetails(RequestCorporationDetailsEvent event ) throws Exception{
		return corporationPersistenceService.requestCorporationDetails(event);
	}

	@Override
	public CorporationDetailsEvent modifyCorporation(CorporationModificationEvent event) throws Exception {
		return corporationPersistenceService.modifyCorporation(event);
	}
	
	@Override
	public CorporationPageEvent requestAll() throws Exception {
		return corporationPersistenceService.requestAll();
	}

}