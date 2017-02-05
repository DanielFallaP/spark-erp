package co.com.cybersoft.maintenance.tables.events.effectFail;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import java.util.List;

/**
 * Event class for EffectFail
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class EffectFailPageEvent {
	private Page<EffectFail> effectFailPage;
	
	private List<EffectFail> allList;
	
	private Long totalCount;
	
	private List<EffectFailDetails> effectFailList;



	
	public EffectFailPageEvent(){
    }
	public EffectFailPageEvent(List<EffectFailDetails>  effectFailList){
			this.effectFailList= effectFailList;
	}



	
	public List<EffectFailDetails> getEffectFailList() {
	return effectFailList;
	}



	
	public List<EffectFail> getAllList() {
		return allList;
	}

	public void setAllList(List<EffectFail> allList) {
		this.allList = allList;
	}
	
	public EffectFailPageEvent(Page<EffectFail>  effectFailPage){
		this.effectFailPage= effectFailPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public EffectFailPageEvent(Page<EffectFail>  effectFailPage, Long totalCount){
		this.effectFailPage= effectFailPage;
		this.totalCount=totalCount;
	}

	public Page<EffectFail> getEffectFailPage() {
		return effectFailPage;
	}
	
	
}