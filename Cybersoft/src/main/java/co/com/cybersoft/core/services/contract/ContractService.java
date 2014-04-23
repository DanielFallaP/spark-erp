package co.com.cybersoft.core.services.contract;

import co.com.cybersoft.events.contract.CreateContractEvent;
import co.com.cybersoft.events.contract.ContractDetailsEvent;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.events.contract.ContractModificationEvent;
import co.com.cybersoft.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.events.contract.RequestContractPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ContractService {
	ContractDetailsEvent createContract(CreateContractEvent event ) throws Exception;
	
	ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception;

	ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event ) throws Exception;

	ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception;
	
	ContractPageEvent requestAll() throws Exception;
	
}