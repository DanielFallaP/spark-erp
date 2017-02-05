package co.com.cybersoft.maintenance.tables.events.technicalAction;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import java.util.List;

/**
 * Event class for TechnicalAction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class TechnicalActionPageEvent {
	private Page<TechnicalAction> technicalActionPage;
	
	private List<TechnicalAction> allList;
	
	private Long totalCount;
	
	private List<TechnicalActionDetails> technicalActionList;



	
	public TechnicalActionPageEvent(){
    }
	public TechnicalActionPageEvent(List<TechnicalActionDetails>  technicalActionList){
			this.technicalActionList= technicalActionList;
	}



	
	public List<TechnicalActionDetails> getTechnicalActionList() {
	return technicalActionList;
	}



	
	public List<TechnicalAction> getAllList() {
		return allList;
	}

	public void setAllList(List<TechnicalAction> allList) {
		this.allList = allList;
	}
	
	public TechnicalActionPageEvent(Page<TechnicalAction>  technicalActionPage){
		this.technicalActionPage= technicalActionPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public TechnicalActionPageEvent(Page<TechnicalAction>  technicalActionPage, Long totalCount){
		this.technicalActionPage= technicalActionPage;
		this.totalCount=totalCount;
	}

	public Page<TechnicalAction> getTechnicalActionPage() {
		return technicalActionPage;
	}
	
	
}