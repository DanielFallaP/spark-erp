package co.com.cybersoft.maintenance.tables.events.causeClose;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import java.util.List;

/**
 * Event class for CauseClose
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CauseClosePageEvent {
	private Page<CauseClose> causeClosePage;
	
	private List<CauseClose> allList;
	
	private Long totalCount;
	
	private List<CauseCloseDetails> causeCloseList;



	
	public CauseClosePageEvent(){
    }
	public CauseClosePageEvent(List<CauseCloseDetails>  causeCloseList){
			this.causeCloseList= causeCloseList;
	}



	
	public List<CauseCloseDetails> getCauseCloseList() {
	return causeCloseList;
	}



	
	public List<CauseClose> getAllList() {
		return allList;
	}

	public void setAllList(List<CauseClose> allList) {
		this.allList = allList;
	}
	
	public CauseClosePageEvent(Page<CauseClose>  causeClosePage){
		this.causeClosePage= causeClosePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CauseClosePageEvent(Page<CauseClose>  causeClosePage, Long totalCount){
		this.causeClosePage= causeClosePage;
		this.totalCount=totalCount;
	}

	public Page<CauseClose> getCauseClosePage() {
		return causeClosePage;
	}
	
	
}