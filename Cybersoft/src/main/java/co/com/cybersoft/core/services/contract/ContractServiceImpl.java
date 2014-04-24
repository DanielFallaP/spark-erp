package co.com.cybersoft.core.services.contract;

import co.com.cybersoft.events.contract.CreateContractEvent;
import co.com.cybersoft.events.contract.ContractDetailsEvent;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.events.contract.ContractModificationEvent;
import co.com.cybersoft.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.events.contract.RequestContractPageEvent;
import co.com.cybersoft.persistence.services.contract.ContractPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractServiceImpl implements ContractService{

	private final ContractPersistenceService contractPersistenceService;
	
	public ContractServiceImpl(final ContractPersistenceService contractPersistenceService){
		this.contractPersistenceService=contractPersistenceService;
	}
	
	/**
	 */
	@Override
	public ContractDetailsEvent createContract(CreateContractEvent event ) throws Exception{
		return contractPersistenceService.createContract(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception{
		return contractPersistenceService.requestContractPage(event);
	}

	@Override
	public ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event ) throws Exception{
		return contractPersistenceService.requestContractDetails(event);
	}

	@Override
	public ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception {
		return contractPersistenceService.modifyContract(event);
	}
	
	@Override
	public ContractPageEvent requestAll() throws Exception {
		return contractPersistenceService.requestAll();
	}
	
	@Override
	public ContractPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return contractPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public ContractPageEvent requestByContainingDescription(String description) throws Exception {
		return contractPersistenceService.requestByContainingDescription(description);
	}
	
}