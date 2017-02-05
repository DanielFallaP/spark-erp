package co.com.cybersoft.maintenance.tables.persistence.services.contract;

import co.com.cybersoft.maintenance.tables.persistence.repository.contract.ContractRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractPersistenceFactory {

	ContractRepository contractRepository;
	
	public ContractPersistenceFactory(ContractRepository contractRepository){
		this.contractRepository=contractRepository;
	}
	
	public co.com.cybersoft.maintenance.tables.persistence.domain.Contract getContractByField(String field, String value){
		if (field.equals("description"))
					return contractRepository.findByDescription(value);		
		return null;
	}
}