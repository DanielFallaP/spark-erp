package co.com.cybersoft.maintenance.tables.events.storeType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import java.util.List;

/**
 * Event class for StoreType
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class StoreTypePageEvent {
	private Page<StoreType> storeTypePage;
	
	private List<StoreType> allList;
	
	private Long totalCount;
	
	private List<StoreTypeDetails> storeTypeList;



	
	public StoreTypePageEvent(){
    }
	public StoreTypePageEvent(List<StoreTypeDetails>  storeTypeList){
			this.storeTypeList= storeTypeList;
	}



	
	public List<StoreTypeDetails> getStoreTypeList() {
	return storeTypeList;
	}



	
	public List<StoreType> getAllList() {
		return allList;
	}

	public void setAllList(List<StoreType> allList) {
		this.allList = allList;
	}
	
	public StoreTypePageEvent(Page<StoreType>  storeTypePage){
		this.storeTypePage= storeTypePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public StoreTypePageEvent(Page<StoreType>  storeTypePage, Long totalCount){
		this.storeTypePage= storeTypePage;
		this.totalCount=totalCount;
	}

	public Page<StoreType> getStoreTypePage() {
		return storeTypePage;
	}
	
	
}