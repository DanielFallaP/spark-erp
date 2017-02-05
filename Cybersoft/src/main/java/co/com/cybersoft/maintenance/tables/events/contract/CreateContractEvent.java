package co.com.cybersoft.maintenance.tables.events.contract;

import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;

/**
 * Event class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateContractEvent {
		
	private ContractDetails contractDetails;
	
	public CreateContractEvent(ContractDetails contractDetails){
		this.contractDetails=contractDetails;
	}

	public ContractDetails getContractDetails() {
		return contractDetails;
	}
	
	
}