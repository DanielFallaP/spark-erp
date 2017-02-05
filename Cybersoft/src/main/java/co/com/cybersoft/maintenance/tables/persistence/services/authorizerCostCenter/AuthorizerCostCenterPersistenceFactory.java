package co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter;

import co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter.AuthorizerCostCenterRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AuthorizerCostCenterPersistenceFactory {

	AuthorizerCostCenterRepository authorizerCostCenterRepository;
	
	public AuthorizerCostCenterPersistenceFactory(AuthorizerCostCenterRepository authorizerCostCenterRepository){
		this.authorizerCostCenterRepository=authorizerCostCenterRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter getAuthorizerCostCenterByField(String field, String value){
		
		return null;
	}
}