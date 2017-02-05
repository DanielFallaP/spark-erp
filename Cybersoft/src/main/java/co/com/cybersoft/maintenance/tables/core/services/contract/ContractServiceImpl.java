package co.com.cybersoft.maintenance.tables.core.services.contract;

import co.com.cybersoft.maintenance.tables.events.contract.CreateContractEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractPageEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractModificationEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.contract.ContractPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

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
	public ContractDetailsEvent createContract(CreateContractEvent event ) throws Exception{
		return contractPersistenceService.createContract(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception{
		return contractPersistenceService.requestContractPage(event);
	}

	public ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event ) throws Exception{
		return contractPersistenceService.requestContractDetails(event);
	}

	public ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception {
		return contractPersistenceService.modifyContract(event);
	}
	
	public ContractDetailsEvent requestOnlyRecord() throws Exception {
		return contractPersistenceService.getOnlyRecord();
	}
	
	public ContractPageEvent requestContractFilterPage(RequestContractPageEvent event) throws Exception {
		return contractPersistenceService.requestContractFilterPage(event);
	}
	
	public ContractPageEvent requestContractFilter(RequestContractPageEvent event) throws Exception {
		return contractPersistenceService.requestContractFilter(event);
	}
	

	public ContractPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return contractPersistenceService.requestAllByCompany(fields);
	}public ContractPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception {
		return contractPersistenceService.requestAllByDescription(fields);
	}public ContractPageEvent requestAllByResponsible(EmbeddedField... fields) throws Exception {
		return contractPersistenceService.requestAllByResponsible(fields);
	}public ContractPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception {
		return contractPersistenceService.requestAllByCostCenter(fields);
	}
	
	
	
}