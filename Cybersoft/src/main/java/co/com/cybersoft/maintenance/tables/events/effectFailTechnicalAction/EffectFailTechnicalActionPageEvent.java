package co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFailTechnicalAction;
import java.util.List;

/**
 * Event class for EffectFailTechnicalAction
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class EffectFailTechnicalActionPageEvent {
	private Page<EffectFailTechnicalAction> effectFailTechnicalActionPage;
	
	private List<EffectFailTechnicalAction> allList;
	
	private Long totalCount;
	
	private List<EffectFailTechnicalActionDetails> effectFailTechnicalActionList;



	
	public EffectFailTechnicalActionPageEvent(){
    }
	public EffectFailTechnicalActionPageEvent(List<EffectFailTechnicalActionDetails>  effectFailTechnicalActionList){
			this.effectFailTechnicalActionList= effectFailTechnicalActionList;
	}



	
	public List<EffectFailTechnicalActionDetails> getEffectFailTechnicalActionList() {
	return effectFailTechnicalActionList;
	}



	
	public List<EffectFailTechnicalAction> getAllList() {
		return allList;
	}

	public void setAllList(List<EffectFailTechnicalAction> allList) {
		this.allList = allList;
	}
	
	public EffectFailTechnicalActionPageEvent(Page<EffectFailTechnicalAction>  effectFailTechnicalActionPage){
		this.effectFailTechnicalActionPage= effectFailTechnicalActionPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public EffectFailTechnicalActionPageEvent(Page<EffectFailTechnicalAction>  effectFailTechnicalActionPage, Long totalCount){
		this.effectFailTechnicalActionPage= effectFailTechnicalActionPage;
		this.totalCount=totalCount;
	}

	public Page<EffectFailTechnicalAction> getEffectFailTechnicalActionPage() {
		return effectFailTechnicalActionPage;
	}
	
	
}