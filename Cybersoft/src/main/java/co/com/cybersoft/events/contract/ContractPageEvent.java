package co.com.cybersoft.events.contract;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Contract;
import co.com.cybersoft.core.domain.ContractDetails;
import co.com.cybersoft.persistence.domain.Contract;
import java.util.List;

/**
 * Event class for Contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContractPageEvent {
	private Page<Contract> contractPage;
	
	private List<ContractDetails> contractList;



	
	public ContractPageEvent(List<ContractDetails>  contractList){
			this.contractList= contractList;
	}



	
	public List<ContractDetails> getContractList() {
	return contractList;
	}



	
	public ContractPageEvent(Page<Contract>  contractPage){
		this.contractPage= contractPage;
	}

	public Page<Contract> getContractPage() {
		return contractPage;
	}
	
}