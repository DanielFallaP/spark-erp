package co.com.cybersoft.persistence.services.contract;

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
public interface ContractPersistenceService {

	ContractDetailsEvent createContract(CreateContractEvent event) throws Exception;

	ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception;

	ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event) throws Exception;
	
	ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception;
	
	ContractPageEvent requestAll() throws Exception;
	
	ContractPageEvent requestByCodePrefix(String codePrefix) throws Exception;

	ContractPageEvent requestByContainingDescription(String description) throws Exception;
	
}