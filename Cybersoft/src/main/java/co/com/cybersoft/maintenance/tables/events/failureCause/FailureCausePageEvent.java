package co.com.cybersoft.maintenance.tables.events.failureCause;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import java.util.List;

/**
 * Event class for FailureCause
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class FailureCausePageEvent {
	private Page<FailureCause> failureCausePage;
	
	private List<FailureCause> allList;
	
	private Long totalCount;
	
	private List<FailureCauseDetails> failureCauseList;



	
	public FailureCausePageEvent(){
    }
	public FailureCausePageEvent(List<FailureCauseDetails>  failureCauseList){
			this.failureCauseList= failureCauseList;
	}



	
	public List<FailureCauseDetails> getFailureCauseList() {
	return failureCauseList;
	}



	
	public List<FailureCause> getAllList() {
		return allList;
	}

	public void setAllList(List<FailureCause> allList) {
		this.allList = allList;
	}
	
	public FailureCausePageEvent(Page<FailureCause>  failureCausePage){
		this.failureCausePage= failureCausePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public FailureCausePageEvent(Page<FailureCause>  failureCausePage, Long totalCount){
		this.failureCausePage= failureCausePage;
		this.totalCount=totalCount;
	}

	public Page<FailureCause> getFailureCausePage() {
		return failureCausePage;
	}
	
	
}