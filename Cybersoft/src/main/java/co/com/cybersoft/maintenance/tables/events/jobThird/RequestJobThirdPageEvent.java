package co.com.cybersoft.maintenance.tables.events.jobThird;

import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.jobThird.JobThirdFilterInfo;

/**
 * Event class for JobThird
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RequestJobThirdPageEvent {

	private Pageable pageable;
	private JobThirdFilterInfo filter;
	
	public RequestJobThirdPageEvent(Pageable pageable, JobThirdFilterInfo filter){
		this.pageable=pageable;
		this.filter=filter;
	}
	
	public RequestJobThirdPageEvent(Pageable pageable){
		this.pageable=pageable;
	}

	public Pageable getPageable() {
		return pageable;
	}
	
	public JobThirdFilterInfo getFilter() {
		return filter;
	}
}