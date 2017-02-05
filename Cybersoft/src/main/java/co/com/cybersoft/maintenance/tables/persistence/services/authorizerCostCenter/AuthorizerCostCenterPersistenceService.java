package co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter;

import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.CreateAuthorizerCostCenterEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.AuthorizerCostCenterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.authorizerCostCenter.RequestAuthorizerCostCenterPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface AuthorizerCostCenterPersistenceService {

	AuthorizerCostCenterDetailsEvent createAuthorizerCostCenter(CreateAuthorizerCostCenterEvent event) throws Exception;

	AuthorizerCostCenterPageEvent requestAuthorizerCostCenterPage(RequestAuthorizerCostCenterPageEvent event) throws Exception;

	AuthorizerCostCenterDetailsEvent requestAuthorizerCostCenterDetails(RequestAuthorizerCostCenterDetailsEvent event) throws Exception;
	
	AuthorizerCostCenterDetailsEvent modifyAuthorizerCostCenter(AuthorizerCostCenterModificationEvent event) throws Exception;
	AuthorizerCostCenterPageEvent requestAuthorizerCostCenterFilterPage(RequestAuthorizerCostCenterPageEvent event) throws Exception;
	AuthorizerCostCenterPageEvent requestAuthorizerCostCenterFilter(RequestAuthorizerCostCenterPageEvent event) throws Exception;
	
	AuthorizerCostCenterPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception;
	AuthorizerCostCenterPageEvent requestAllByResponsible(EmbeddedField... fields) throws Exception;

	
	
	AuthorizerCostCenterDetailsEvent getOnlyRecord() throws Exception;
	
}