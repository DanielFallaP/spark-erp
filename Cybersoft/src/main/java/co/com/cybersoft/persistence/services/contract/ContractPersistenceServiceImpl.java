package co.com.cybersoft.persistence.services.contract;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.events.contract.CreateContractEvent;
import co.com.cybersoft.events.contract.ContractDetailsEvent;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.events.contract.ContractModificationEvent;
import co.com.cybersoft.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.events.contract.RequestContractPageEvent;
import co.com.cybersoft.persistence.domain.Contract;
import co.com.cybersoft.persistence.repository.ContractRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractPersistenceServiceImpl implements ContractPersistenceService{

	private final ContractRepository contractRepository;
	
	public ContractPersistenceServiceImpl(final ContractRepository contractRepository) {
		this.contractRepository=contractRepository;
	}
	
	@Override
	public ContractDetailsEvent createContract(CreateContractEvent event) throws Exception{
		Contract contract = Contract.fromContractDetails(event.getContractDetails());
		contract = contractRepository.save(contract);
		return new ContractDetailsEvent(contract.toContractDetails());
	}

	@Override
	public ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception {
		Page<Contract> contracts = contractRepository.findAll(event.getPageable());
		return new ContractPageEvent(contracts);
	}

	@Override
	public ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event) throws Exception {
		Contract contract = contractRepository.findByCode(event.getId());
		ContractDetails contractDetails = contract.toContractDetails();
		return new ContractDetailsEvent(contractDetails);
	}

	@Override
	public ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception {
		Contract contract = Contract.fromContractDetails(event.getContractDetails());
		contract = contractRepository.save(contract);
		return new ContractDetailsEvent(contract.toContractDetails());
	}
	
	@Override
	public ContractPageEvent requestAll() throws Exception {
		List<Contract> all = contractRepository.findAll();
		List<ContractDetails> list = new ArrayList<ContractDetails>();
		for (Contract contract : all) {
			list.add(contract.toContractDetails());
		}
		return new ContractPageEvent(list);
	}

}