package co.com.cybersoft.purchase.tables.events.continent;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import java.util.List;

/**
 * Event class for Continent
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ContinentPageEvent {
	private Page<Continent> continentPage;
	
	private List<Continent> allList;
	
	private Long totalCount;
	
	private List<ContinentDetails> continentList;



	
	public ContinentPageEvent(){
    }
	public ContinentPageEvent(List<ContinentDetails>  continentList){
			this.continentList= continentList;
	}



	
	public List<ContinentDetails> getContinentList() {
	return continentList;
	}



	
	public List<Continent> getAllList() {
		return allList;
	}

	public void setAllList(List<Continent> allList) {
		this.allList = allList;
	}
	
	public ContinentPageEvent(Page<Continent>  continentPage){
		this.continentPage= continentPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ContinentPageEvent(Page<Continent>  continentPage, Long totalCount){
		this.continentPage= continentPage;
		this.totalCount=totalCount;
	}

	public Page<Continent> getContinentPage() {
		return continentPage;
	}
	
	
}