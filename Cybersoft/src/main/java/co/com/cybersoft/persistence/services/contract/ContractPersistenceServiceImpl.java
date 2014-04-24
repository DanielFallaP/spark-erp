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
import co.com.cybersoft.persistence.repository.contract.ContractRepository;
import co.com.cybersoft.persistence.repository.contract.ContractCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractPersistenceServiceImpl implements ContractPersistenceService{

	private final ContractRepository contractRepository;
	
	private final ContractCustomRepo contractCustomRepo;
	
	public ContractPersistenceServiceImpl(final ContractRepository contractRepository, final ContractCustomRepo contractCustomRepo) {
		this.contractRepository=contractRepository;
		this.contractCustomRepo=contractCustomRepo;
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
		ContractDetails contractDetails=null;
		if (event.getId()!=null){
			Contract contract = contractRepository.findByCode(event.getId());
			if (contract!=null)
				contractDetails = contract.toContractDetails();
		}
		else{
					Contract contract = contractRepository.findByDescription(event.getDescription());
					if (contract!=null)
						contractDetails = contract.toContractDetails();
				}
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
	
	@Override
	public ContractPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<Contract> codes = contractCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<ContractDetails> list = new ArrayList<ContractDetails>();
		for (Contract contract : codes) {
			list.add(contract.toContractDetails());
		}
		return new ContractPageEvent(list);
	}

	@Override
	public ContractPageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<ContractDetails> list = new ArrayList<ContractDetails>();
		List<Contract> descriptions = contractCustomRepo.findByContainingDescription(description);
		for (Contract contract : descriptions) {
			list.add(contract.toContractDetails());
		}
		return new ContractPageEvent(list);
	}

}