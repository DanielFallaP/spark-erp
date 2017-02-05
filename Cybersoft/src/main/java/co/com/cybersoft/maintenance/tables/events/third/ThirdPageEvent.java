package co.com.cybersoft.maintenance.tables.events.third;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import co.com.cybersoft.maintenance.tables.core.domain.ThirdDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import java.util.List;

/**
 * Event class for Third
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ThirdPageEvent {
	private Page<Third> thirdPage;
	
	private List<Third> allList;
	
	private Long totalCount;
	
	private List<ThirdDetails> thirdList;



	
	public ThirdPageEvent(){
    }
	public ThirdPageEvent(List<ThirdDetails>  thirdList){
			this.thirdList= thirdList;
	}



	
	public List<ThirdDetails> getThirdList() {
	return thirdList;
	}



	
	public List<Third> getAllList() {
		return allList;
	}

	public void setAllList(List<Third> allList) {
		this.allList = allList;
	}
	
	public ThirdPageEvent(Page<Third>  thirdPage){
		this.thirdPage= thirdPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ThirdPageEvent(Page<Third>  thirdPage, Long totalCount){
		this.thirdPage= thirdPage;
		this.totalCount=totalCount;
	}

	public Page<Third> getThirdPage() {
		return thirdPage;
	}
	
	
}