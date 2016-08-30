package co.com.cybersoft.purchase.tables.events.region;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import java.util.List;

/**
 * Event class for Region
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class RegionPageEvent {
	private Page<Region> regionPage;
	
	private List<Region> allList;
	
	private Long totalCount;
	
	private List<RegionDetails> regionList;



	
	public RegionPageEvent(){
    }
	public RegionPageEvent(List<RegionDetails>  regionList){
			this.regionList= regionList;
	}



	
	public List<RegionDetails> getRegionList() {
	return regionList;
	}



	
	public List<Region> getAllList() {
		return allList;
	}

	public void setAllList(List<Region> allList) {
		this.allList = allList;
	}
	
	public RegionPageEvent(Page<Region>  regionPage){
		this.regionPage= regionPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public RegionPageEvent(Page<Region>  regionPage, Long totalCount){
		this.regionPage= regionPage;
		this.totalCount=totalCount;
	}

	public Page<Region> getRegionPage() {
		return regionPage;
	}
	
	
}