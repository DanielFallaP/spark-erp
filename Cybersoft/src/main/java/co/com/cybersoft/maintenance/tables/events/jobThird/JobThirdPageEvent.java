package co.com.cybersoft.maintenance.tables.events.jobThird;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import java.util.List;

/**
 * Event class for JobThird
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class JobThirdPageEvent {
	private Page<JobThird> jobThirdPage;
	
	private List<JobThird> allList;
	
	private Long totalCount;
	
	private List<JobThirdDetails> jobThirdList;



	
	public JobThirdPageEvent(){
    }
	public JobThirdPageEvent(List<JobThirdDetails>  jobThirdList){
			this.jobThirdList= jobThirdList;
	}



	
	public List<JobThirdDetails> getJobThirdList() {
	return jobThirdList;
	}



	
	public List<JobThird> getAllList() {
		return allList;
	}

	public void setAllList(List<JobThird> allList) {
		this.allList = allList;
	}
	
	public JobThirdPageEvent(Page<JobThird>  jobThirdPage){
		this.jobThirdPage= jobThirdPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public JobThirdPageEvent(Page<JobThird>  jobThirdPage, Long totalCount){
		this.jobThirdPage= jobThirdPage;
		this.totalCount=totalCount;
	}

	public Page<JobThird> getJobThirdPage() {
		return jobThirdPage;
	}
	
	
}