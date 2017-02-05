package co.com.cybersoft.maintenance.tables.persistence.services.contract;

import co.com.cybersoft.maintenance.tables.events.contract.CreateContractEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractPageEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractModificationEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ContractPersistenceService {

	ContractDetailsEvent createContract(CreateContractEvent event) throws Exception;

	ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception;

	ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event) throws Exception;
	
	ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception;
	ContractPageEvent requestContractFilterPage(RequestContractPageEvent event) throws Exception;
	ContractPageEvent requestContractFilter(RequestContractPageEvent event) throws Exception;
	
	ContractPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	ContractPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception;
	ContractPageEvent requestAllByResponsible(EmbeddedField... fields) throws Exception;
	ContractPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception;

	
	
	ContractDetailsEvent getOnlyRecord() throws Exception;
	
}