package co.com.cybersoft.maintenance.tables.events.job;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import java.util.List;

/**
 * Event class for Job
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class JobPageEvent {
	private Page<Job> jobPage;
	
	private List<Job> allList;
	
	private Long totalCount;
	
	private List<JobDetails> jobList;



	
	public JobPageEvent(){
    }
	public JobPageEvent(List<JobDetails>  jobList){
			this.jobList= jobList;
	}



	
	public List<JobDetails> getJobList() {
	return jobList;
	}



	
	public List<Job> getAllList() {
		return allList;
	}

	public void setAllList(List<Job> allList) {
		this.allList = allList;
	}
	
	public JobPageEvent(Page<Job>  jobPage){
		this.jobPage= jobPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public JobPageEvent(Page<Job>  jobPage, Long totalCount){
		this.jobPage= jobPage;
		this.totalCount=totalCount;
	}

	public Page<Job> getJobPage() {
		return jobPage;
	}
	
	
}