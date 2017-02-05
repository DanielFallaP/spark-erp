package co.com.cybersoft.maintenance.tables.events.authorizerCostCenter;

import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;

/**
 * Event class for AuthorizerCostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AuthorizerCostCenterDetailsEvent {
	
	private AuthorizerCostCenterDetails authorizerCostCenterDetails;
	
	public AuthorizerCostCenterDetailsEvent(AuthorizerCostCenterDetails authorizerCostCenterDetails){
		this.authorizerCostCenterDetails=authorizerCostCenterDetails;
	}

	public AuthorizerCostCenterDetails getAuthorizerCostCenterDetails() {
		return authorizerCostCenterDetails;
	}

}