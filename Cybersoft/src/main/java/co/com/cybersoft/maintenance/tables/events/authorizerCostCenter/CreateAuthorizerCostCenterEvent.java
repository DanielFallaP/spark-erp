package co.com.cybersoft.maintenance.tables.events.authorizerCostCenter;

import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;

/**
 * Event class for AuthorizerCostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateAuthorizerCostCenterEvent {
		
	private AuthorizerCostCenterDetails authorizerCostCenterDetails;
	
	public CreateAuthorizerCostCenterEvent(AuthorizerCostCenterDetails authorizerCostCenterDetails){
		this.authorizerCostCenterDetails=authorizerCostCenterDetails;
	}

	public AuthorizerCostCenterDetails getAuthorizerCostCenterDetails() {
		return authorizerCostCenterDetails;
	}
	
	
}