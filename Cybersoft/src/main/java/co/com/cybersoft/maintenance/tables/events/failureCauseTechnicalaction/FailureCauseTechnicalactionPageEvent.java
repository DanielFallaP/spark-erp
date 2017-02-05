package co.com.cybersoft.maintenance.tables.events.failureCauseTechnicalaction;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import java.util.List;

/**
 * Event class for FailureCauseTechnicalaction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class FailureCauseTechnicalactionPageEvent {
	private Page<FailureCauseTechnicalaction> failureCauseTechnicalactionPage;
	
	private List<FailureCauseTechnicalaction> allList;
	
	private Long totalCount;
	
	private List<FailureCauseTechnicalactionDetails> failureCauseTechnicalactionList;



	
	public FailureCauseTechnicalactionPageEvent(){
    }
	public FailureCauseTechnicalactionPageEvent(List<FailureCauseTechnicalactionDetails>  failureCauseTechnicalactionList){
			this.failureCauseTechnicalactionList= failureCauseTechnicalactionList;
	}



	
	public List<FailureCauseTechnicalactionDetails> getFailureCauseTechnicalactionList() {
	return failureCauseTechnicalactionList;
	}



	
	public List<FailureCauseTechnicalaction> getAllList() {
		return allList;
	}

	public void setAllList(List<FailureCauseTechnicalaction> allList) {
		this.allList = allList;
	}
	
	public FailureCauseTechnicalactionPageEvent(Page<FailureCauseTechnicalaction>  failureCauseTechnicalactionPage){
		this.failureCauseTechnicalactionPage= failureCauseTechnicalactionPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public FailureCauseTechnicalactionPageEvent(Page<FailureCauseTechnicalaction>  failureCauseTechnicalactionPage, Long totalCount){
		this.failureCauseTechnicalactionPage= failureCauseTechnicalactionPage;
		this.totalCount=totalCount;
	}

	public Page<FailureCauseTechnicalaction> getFailureCauseTechnicalactionPage() {
		return failureCauseTechnicalactionPage;
	}
	
	
}