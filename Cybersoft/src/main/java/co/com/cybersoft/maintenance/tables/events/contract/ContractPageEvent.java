package co.com.cybersoft.maintenance.tables.events.contract;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import java.util.List;

/**
 * Event class for Contract
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ContractPageEvent {
	private Page<Contract> contractPage;
	
	private List<Contract> allList;
	
	private Long totalCount;
	
	private List<ContractDetails> contractList;



	
	public ContractPageEvent(){
    }
	public ContractPageEvent(List<ContractDetails>  contractList){
			this.contractList= contractList;
	}



	
	public List<ContractDetails> getContractList() {
	return contractList;
	}



	
	public List<Contract> getAllList() {
		return allList;
	}

	public void setAllList(List<Contract> allList) {
		this.allList = allList;
	}
	
	public ContractPageEvent(Page<Contract>  contractPage){
		this.contractPage= contractPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ContractPageEvent(Page<Contract>  contractPage, Long totalCount){
		this.contractPage= contractPage;
		this.totalCount=totalCount;
	}

	public Page<Contract> getContractPage() {
		return contractPage;
	}
	
	
}