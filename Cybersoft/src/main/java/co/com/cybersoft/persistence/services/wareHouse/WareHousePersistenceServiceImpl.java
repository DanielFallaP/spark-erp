package co.com.cybersoft.persistence.services.wareHouse;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.WareHouseDetails;
import co.com.cybersoft.events.wareHouse.CreateWareHouseEvent;
import co.com.cybersoft.events.wareHouse.WareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.WareHousePageEvent;
import co.com.cybersoft.events.wareHouse.WareHouseModificationEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHouseDetailsEvent;
import co.com.cybersoft.events.wareHouse.RequestWareHousePageEvent;
import co.com.cybersoft.persistence.domain.WareHouse;
import co.com.cybersoft.persistence.repository.wareHouse.WareHouseRepository;
import co.com.cybersoft.persistence.repository.wareHouse.WareHouseCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WareHousePersistenceServiceImpl implements WareHousePersistenceService{

	private final WareHouseRepository wareHouseRepository;
	
	private final WareHouseCustomRepo wareHouseCustomRepo;
	
	public WareHousePersistenceServiceImpl(final WareHouseRepository wareHouseRepository, final WareHouseCustomRepo wareHouseCustomRepo) {
		this.wareHouseRepository=wareHouseRepository;
		this.wareHouseCustomRepo=wareHouseCustomRepo;
	}
	
	@Override
	public WareHouseDetailsEvent createWareHouse(CreateWareHouseEvent event) throws Exception{
		WareHouse wareHouse = WareHouse.fromWareHouseDetails(event.getWareHouseDetails());
		wareHouse = wareHouseRepository.save(wareHouse);
		return new WareHouseDetailsEvent(wareHouse.toWareHouseDetails());
	}

	@Override
	public WareHousePageEvent requestWareHousePage(RequestWareHousePageEvent event) throws Exception {
		Page<WareHouse> wareHouses = wareHouseRepository.findAll(event.getPageable());
		return new WareHousePageEvent(wareHouses);
	}

	@Override
	public WareHouseDetailsEvent requestWareHouseDetails(RequestWareHouseDetailsEvent event) throws Exception {
		WareHouseDetails wareHouseDetails=null;
		if (event.getId()!=null){
			WareHouse wareHouse = wareHouseRepository.findByCode(event.getId());
			if (wareHouse!=null)
				wareHouseDetails = wareHouse.toWareHouseDetails();
		}
		else{
					WareHouse wareHouse = wareHouseRepository.findByDescription(event.getDescription());
					if (wareHouse!=null)
						wareHouseDetails = wareHouse.toWareHouseDetails();
				}
		return new WareHouseDetailsEvent(wareHouseDetails);
		
	}

	@Override
	public WareHouseDetailsEvent modifyWareHouse(WareHouseModificationEvent event) throws Exception {
		WareHouse wareHouse = WareHouse.fromWareHouseDetails(event.getWareHouseDetails());
		wareHouse = wareHouseRepository.save(wareHouse);
		return new WareHouseDetailsEvent(wareHouse.toWareHouseDetails());
	}
	
	@Override
	public WareHousePageEvent requestAll() throws Exception {
		List<WareHouse> all = wareHouseRepository.findAll();
		List<WareHouseDetails> list = new ArrayList<WareHouseDetails>();
		for (WareHouse wareHouse : all) {
			list.add(wareHouse.toWareHouseDetails());
		}
		return new WareHousePageEvent(list);
	}
	
	@Override
	public WareHousePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<WareHouse> codes = wareHouseCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<WareHouseDetails> list = new ArrayList<WareHouseDetails>();
		for (WareHouse wareHouse : codes) {
			list.add(wareHouse.toWareHouseDetails());
		}
		return new WareHousePageEvent(list);
	}

	@Override
	public WareHousePageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<WareHouseDetails> list = new ArrayList<WareHouseDetails>();
		List<WareHouse> descriptions = wareHouseCustomRepo.findByContainingDescription(description);
		for (WareHouse wareHouse : descriptions) {
			list.add(wareHouse.toWareHouseDetails());
		}
		return new WareHousePageEvent(list);
	}

}