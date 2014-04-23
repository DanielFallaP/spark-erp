package co.com.cybersoft.core.services.wareHouse;

import co.com.cybersoft.events.wareHouse.CreateWareHouseEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHousePageEvent;
import co.com.cybersoft.events.wareHouse.WareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.WareHouseModificationEvent;
import co.com.cybersoft.events.wareHouse.WareHousePageEvent;
import co.com.cybersoft.persistence.services.wareHouse.WareHousePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WareHouseServiceImpl implements WareHouseService{

	private final WareHousePersistenceService wareHousePersistenceService;
	
	public WareHouseServiceImpl(final WareHousePersistenceService wareHousePersistenceService){
		this.wareHousePersistenceService=wareHousePersistenceService;
	}
	
	/**
	 */
	@Override
	public WareHouseDetailsEvent createWareHouse(CreateWareHouseEvent event ) throws Exception{
		return wareHousePersistenceService.createWareHouse(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public WareHousePageEvent requestWareHousePage(RequestWareHousePageEvent event) throws Exception{
		return wareHousePersistenceService.requestWareHousePage(event);
	}

	@Override
	public WareHouseDetailsEvent requestWareHouseDetails(RequestWareHouseDetailsEvent event ) throws Exception{
		return wareHousePersistenceService.requestWareHouseDetails(event);
	}

	@Override
	public WareHouseDetailsEvent modifyWareHouse(WareHouseModificationEvent event) throws Exception {
		return wareHousePersistenceService.modifyWareHouse(event);
	}
	
	@Override
	public WareHousePageEvent requestAll() throws Exception {
		return wareHousePersistenceService.requestAll();
	}

	@Override
	public WareHousePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return wareHousePersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public WareHousePageEvent requestByContainingDescription(String description) throws Exception {
		return wareHousePersistenceService.requestByContainingDescription(description);
	}

}