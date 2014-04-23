package co.com.cybersoft.events.contract;

import co.com.cybersoft.core.domain.ContractDetails;

/**
 * Event class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractDetailsEvent {
	
	private ContractDetails contractDetails;
	
	public ContractDetailsEvent(ContractDetails contractDetails){
		this.contractDetails=contractDetails;
	}

	public ContractDetails getContractDetails() {
		return contractDetails;
	}

}