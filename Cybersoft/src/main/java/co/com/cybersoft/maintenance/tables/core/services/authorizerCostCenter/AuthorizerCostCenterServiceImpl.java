package co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter;

import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.CreateAuthorizerCostCenterEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter.AuthorizerCostCenterPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AuthorizerCostCenterServiceImpl implements AuthorizerCostCenterService{

	private final AuthorizerCostCenterPersistenceService authorizerCostCenterPersistenceService;
	
	public AuthorizerCostCenterServiceImpl(final AuthorizerCostCenterPersistenceService authorizerCostCenterPersistenceService){
		this.authorizerCostCenterPersistenceService=authorizerCostCenterPersistenceService;
	}
	
	/**
	 */
	public AuthorizerCostCenterDetailsEvent createAuthorizerCostCenter(CreateAuthorizerCostCenterEvent event ) throws Exception{
		return authorizerCostCenterPersistenceService.createAuthorizerCostCenter(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public AuthorizerCostCenterPageEvent requestAuthorizerCostCenterPage(RequestAuthorizerCostCenterPageEvent event) throws Exception{
		return authorizerCostCenterPersistenceService.requestAuthorizerCostCenterPage(event);
	}

	public AuthorizerCostCenterDetailsEvent requestAuthorizerCostCenterDetails(RequestAuthorizerCostCenterDetailsEvent event ) throws Exception{
		return authorizerCostCenterPersistenceService.requestAuthorizerCostCenterDetails(event);
	}

	public AuthorizerCostCenterDetailsEvent modifyAuthorizerCostCenter(AuthorizerCostCenterModificationEvent event) throws Exception {
		return authorizerCostCenterPersistenceService.modifyAuthorizerCostCenter(event);
	}
	
	public AuthorizerCostCenterDetailsEvent requestOnlyRecord() throws Exception {
		return authorizerCostCenterPersistenceService.getOnlyRecord();
	}
	
	public AuthorizerCostCenterPageEvent requestAuthorizerCostCenterFilterPage(RequestAuthorizerCostCenterPageEvent event) throws Exception {
		return authorizerCostCenterPersistenceService.requestAuthorizerCostCenterFilterPage(event);
	}
	
	public AuthorizerCostCenterPageEvent requestAuthorizerCostCenterFilter(RequestAuthorizerCostCenterPageEvent event) throws Exception {
		return authorizerCostCenterPersistenceService.requestAuthorizerCostCenterFilter(event);
	}
	

	public AuthorizerCostCenterPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception {
		return authorizerCostCenterPersistenceService.requestAllByCostCenter(fields);
	}public AuthorizerCostCenterPageEvent requestAllByResponsible(EmbeddedField... fields) throws Exception {
		return authorizerCostCenterPersistenceService.requestAllByResponsible(fields);
	}
	
	
	
}