package co.com.cybersoft.purchase.tables.events.region;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import java.util.List;

/**
 * Event class for Region
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RegionPageEvent {
	private Page<Region> regionPage;
	
	private List<RegionDetails> regionList;



	
	public RegionPageEvent(List<RegionDetails>  regionList){
			this.regionList= regionList;
	}



	
	public List<RegionDetails> getRegionList() {
	return regionList;
	}



	
	public RegionPageEvent(Page<Region>  regionPage){
		this.regionPage= regionPage;
	}

	public Page<Region> getRegionPage() {
		return regionPage;
	}
	
}