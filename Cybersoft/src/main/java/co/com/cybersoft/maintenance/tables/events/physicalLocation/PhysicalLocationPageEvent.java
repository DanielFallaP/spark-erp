package co.com.cybersoft.maintenance.tables.events.physicalLocation;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import java.util.List;

/**
 * Event class for PhysicalLocation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class PhysicalLocationPageEvent {
	private Page<PhysicalLocation> physicalLocationPage;
	
	private List<PhysicalLocation> allList;
	
	private Long totalCount;
	
	private List<PhysicalLocationDetails> physicalLocationList;



	
	public PhysicalLocationPageEvent(){
    }
	public PhysicalLocationPageEvent(List<PhysicalLocationDetails>  physicalLocationList){
			this.physicalLocationList= physicalLocationList;
	}



	
	public List<PhysicalLocationDetails> getPhysicalLocationList() {
	return physicalLocationList;
	}



	
	public List<PhysicalLocation> getAllList() {
		return allList;
	}

	public void setAllList(List<PhysicalLocation> allList) {
		this.allList = allList;
	}
	
	public PhysicalLocationPageEvent(Page<PhysicalLocation>  physicalLocationPage){
		this.physicalLocationPage= physicalLocationPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public PhysicalLocationPageEvent(Page<PhysicalLocation>  physicalLocationPage, Long totalCount){
		this.physicalLocationPage= physicalLocationPage;
		this.totalCount=totalCount;
	}

	public Page<PhysicalLocation> getPhysicalLocationPage() {
		return physicalLocationPage;
	}
	
	
}