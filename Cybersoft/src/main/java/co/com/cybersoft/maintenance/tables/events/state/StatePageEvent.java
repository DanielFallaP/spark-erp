package co.com.cybersoft.maintenance.tables.events.state;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import java.util.List;

/**
 * Event class for State
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class StatePageEvent {
	private Page<State> statePage;
	
	private List<State> allList;
	
	private Long totalCount;
	
	private List<StateDetails> stateList;



	
	public StatePageEvent(){
    }
	public StatePageEvent(List<StateDetails>  stateList){
			this.stateList= stateList;
	}



	
	public List<StateDetails> getStateList() {
	return stateList;
	}



	
	public List<State> getAllList() {
		return allList;
	}

	public void setAllList(List<State> allList) {
		this.allList = allList;
	}
	
	public StatePageEvent(Page<State>  statePage){
		this.statePage= statePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public StatePageEvent(Page<State>  statePage, Long totalCount){
		this.statePage= statePage;
		this.totalCount=totalCount;
	}

	public Page<State> getStatePage() {
		return statePage;
	}
	
	
}