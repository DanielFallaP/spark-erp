package co.com.cybersoft.maintenance.tables.events.causePending;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import java.util.List;

/**
 * Event class for CausePending
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CausePendingPageEvent {
	private Page<CausePending> causePendingPage;
	
	private List<CausePending> allList;
	
	private Long totalCount;
	
	private List<CausePendingDetails> causePendingList;



	
	public CausePendingPageEvent(){
    }
	public CausePendingPageEvent(List<CausePendingDetails>  causePendingList){
			this.causePendingList= causePendingList;
	}



	
	public List<CausePendingDetails> getCausePendingList() {
	return causePendingList;
	}



	
	public List<CausePending> getAllList() {
		return allList;
	}

	public void setAllList(List<CausePending> allList) {
		this.allList = allList;
	}
	
	public CausePendingPageEvent(Page<CausePending>  causePendingPage){
		this.causePendingPage= causePendingPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CausePendingPageEvent(Page<CausePending>  causePendingPage, Long totalCount){
		this.causePendingPage= causePendingPage;
		this.totalCount=totalCount;
	}

	public Page<CausePending> getCausePendingPage() {
		return causePendingPage;
	}
	
	
}