package co.com.cybersoft.events.wareHouse;

import java.util.List;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.WareHouseDetails;
import co.com.cybersoft.persistence.domain.WareHouse;

/**
 * Event class for WareHouse
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WareHousePageEvent {
	private Page<WareHouse> wareHousePage;
	
	private List<WareHouseDetails> wareHouseList;

	public WareHousePageEvent(List<WareHouseDetails>  wareHouseList){
			this.wareHouseList= wareHouseList;
	}

	public List<WareHouseDetails> getWareHouseList() {
	return wareHouseList;
	}
	
	public WareHousePageEvent(Page<WareHouse>  wareHousePage){
		this.wareHousePage= wareHousePage;
	}

	public Page<WareHouse> getWareHousePage() {
		return wareHousePage;
	}
	
}