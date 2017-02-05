package co.com.cybersoft.maintenance.tables.persistence.services.contract;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;
import co.com.cybersoft.maintenance.tables.events.contract.CreateContractEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractPageEvent;
import co.com.cybersoft.maintenance.tables.events.contract.ContractModificationEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.maintenance.tables.persistence.repository.contract.ContractRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.contract.ContractCustomRepo;
//import co.com.cybersoft.man.services.security.SessionAction;
//import co.com.cybersoft.man.services.security.SessionLogger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;

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
	
	public ContractDetailsEvent createContract(CreateContractEvent event) throws Exception{
		Contract contract = new Contract().fromContractDetails(event.getContractDetails());
		contract = contractRepository.save(contract);
		contract = contractRepository.findOne(contract.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",contract.getId());
		return new ContractDetailsEvent(new ContractDetails().toContractDetails(contract));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"contract", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public ContractPageEvent requestContractPage(RequestContractPageEvent event) throws Exception {
		Page<Contract> contracts = contractRepository.findAll(event.getPageable());
		return new ContractPageEvent(contracts);
	}

	public ContractDetailsEvent requestContractDetails(RequestContractDetailsEvent event) throws Exception {
		ContractDetails contractDetails=null;
		if (event.getId()!=null){
			Contract contract = contractRepository.findOne(event.getId());
			if (contract!=null)
				contractDetails = new ContractDetails().toContractDetails(contract);
		}
		return new ContractDetailsEvent(contractDetails);
		
	}

	public ContractDetailsEvent modifyContract(ContractModificationEvent event) throws Exception {
		Contract contract = new Contract().fromContractDetails(event.getContractDetails());
		contract = contractRepository.save(contract);
		contract = contractRepository.findOne(contract.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",contract.getId());
		return new ContractDetailsEvent(new ContractDetails().toContractDetails(contract));
	}
	
		public ContractDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Contract> all = contractRepository.findAll();
			ContractDetails single = new ContractDetails();
			for (Contract contract : all) {
				single=new ContractDetails().toContractDetails(contract);
				break;
			}
			return new ContractDetailsEvent(single);
		}
	
	public ContractPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Contract> all = contractCustomRepo.findAllActiveByCompany(fields);
			List<ContractDetails> list = new ArrayList<ContractDetails>();
			for (Contract contract : all) {
				list.add(new ContractDetails().toContractDetails(contract, fields));
			}
			return new ContractPageEvent(list);
		}public ContractPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception {
			List<Contract> all = contractCustomRepo.findAllActiveByDescription(fields);
			List<ContractDetails> list = new ArrayList<ContractDetails>();
			for (Contract contract : all) {
				list.add(new ContractDetails().toContractDetails(contract, fields));
			}
			return new ContractPageEvent(list);
		}public ContractPageEvent requestAllByResponsible(EmbeddedField... fields) throws Exception {
			List<Contract> all = contractCustomRepo.findAllActiveByResponsible(fields);
			List<ContractDetails> list = new ArrayList<ContractDetails>();
			for (Contract contract : all) {
				list.add(new ContractDetails().toContractDetails(contract, fields));
			}
			return new ContractPageEvent(list);
		}public ContractPageEvent requestAllByCostCenter(EmbeddedField... fields) throws Exception {
			List<Contract> all = contractCustomRepo.findAllActiveByCostCenter(fields);
			List<ContractDetails> list = new ArrayList<ContractDetails>();
			for (Contract contract : all) {
				list.add(new ContractDetails().toContractDetails(contract, fields));
			}
			return new ContractPageEvent(list);
		}
	

	public ContractPageEvent requestContractFilterPage(RequestContractPageEvent event) throws Exception {
		Page<Contract> page = contractCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ContractPageEvent(page, contractCustomRepo.getTotalCount());
	}
	
	public ContractPageEvent requestContractFilter(
			RequestContractPageEvent event) throws Exception {
		List<Contract> all = contractCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ContractPageEvent pageEvent = new ContractPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}