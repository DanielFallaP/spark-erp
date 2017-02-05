package co.com.cybersoft.maintenance.tables.events.job;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobFilterInfo;

/**
 * Event class for Job
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestJobPageEvent {

	private Pageable pageable;
	private JobFilterInfo filter;
	
	public RequestJobPageEvent(Pageable pageable, JobFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestJobPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public JobFilterInfo getFilter() {
		return filter;
	}
}