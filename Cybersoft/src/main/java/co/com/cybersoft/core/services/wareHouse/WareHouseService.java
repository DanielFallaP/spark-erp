package co.com.cybersoft.core.services.wareHouse;

import co.com.cybersoft.events.wareHouse.CreateWareHouseEvent;
import co.com.cybersoft.events.wareHouse.WareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.WareHousePageEvent;
import co.com.cybersoft.events.wareHouse.WareHouseModificationEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHousePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface WareHouseService {
	WareHouseDetailsEvent createWareHouse(CreateWareHouseEvent event ) throws Exception;
	
	WareHousePageEvent requestWareHousePage(RequestWareHousePageEvent event) throws Exception;

	WareHouseDetailsEvent requestWareHouseDetails(RequestWareHouseDetailsEvent event ) throws Exception;

	WareHouseDetailsEvent modifyWareHouse(WareHouseModificationEvent event) throws Exception;
	
	WareHousePageEvent requestAll() throws Exception;
	
	WareHousePageEvent requestByCodePrefix(String code) throws Exception;
	
	WareHousePageEvent requestByContainingDescription(String description) throws Exception;
	
}