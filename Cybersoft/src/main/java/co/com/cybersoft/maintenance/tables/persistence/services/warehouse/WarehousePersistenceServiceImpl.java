package co.com.cybersoft.maintenance.tables.persistence.services.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;
import co.com.cybersoft.maintenance.tables.events.warehouse.CreateWarehouseEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehousePageEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.WarehouseModificationEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehouseDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.warehouse.RequestWarehousePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.maintenance.tables.persistence.repository.warehouse.WarehouseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.warehouse.WarehouseCustomRepo;
//import co.com.cybersoft.man.services.security.SessionAction;
//import co.com.cybersoft.man.services.security.SessionLogger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class WarehousePersistenceServiceImpl implements WarehousePersistenceService{

	private final WarehouseRepository warehouseRepository;
	
	private final WarehouseCustomRepo warehouseCustomRepo;
	
	
	public WarehousePersistenceServiceImpl(final WarehouseRepository warehouseRepository, final WarehouseCustomRepo warehouseCustomRepo) {
		this.warehouseRepository=warehouseRepository;
		this.warehouseCustomRepo=warehouseCustomRepo;
	}
	
	public WarehouseDetailsEvent createWarehouse(CreateWarehouseEvent event) throws Exception{
		Warehouse warehouse = new Warehouse().fromWarehouseDetails(event.getWarehouseDetails());
		warehouse = warehouseRepository.save(warehouse);
		warehouse = warehouseRepository.findOne(warehouse.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",warehouse.getId());
		return new WarehouseDetailsEvent(new WarehouseDetails().toWarehouseDetails(warehouse));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"warehouse", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public WarehousePageEvent requestWarehousePage(RequestWarehousePageEvent event) throws Exception {
		Page<Warehouse> warehouses = warehouseRepository.findAll(event.getPageable());
		return new WarehousePageEvent(warehouses);
	}

	public WarehouseDetailsEvent requestWarehouseDetails(RequestWarehouseDetailsEvent event) throws Exception {
		WarehouseDetails warehouseDetails=null;
		if (event.getId()!=null){
			Warehouse warehouse = warehouseRepository.findOne(event.getId());
			if (warehouse!=null)
				warehouseDetails = new WarehouseDetails().toWarehouseDetails(warehouse);
		}
		return new WarehouseDetailsEvent(warehouseDetails);
		
	}

	public WarehouseDetailsEvent modifyWarehouse(WarehouseModificationEvent event) throws Exception {
		Warehouse warehouse = new Warehouse().fromWarehouseDetails(event.getWarehouseDetails());
		warehouse = warehouseRepository.save(warehouse);
		warehouse = warehouseRepository.findOne(warehouse.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",warehouse.getId());
		return new WarehouseDetailsEvent(new WarehouseDetails().toWarehouseDetails(warehouse));
	}
	
		public WarehouseDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Warehouse> all = warehouseRepository.findAll();
			WarehouseDetails single = new WarehouseDetails();
			for (Warehouse warehouse : all) {
				single=new WarehouseDetails().toWarehouseDetails(warehouse);
				break;
			}
			return new WarehouseDetailsEvent(single);
		}
	
	public WarehousePageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Warehouse> all = warehouseCustomRepo.findAllActiveByCompany(fields);
			List<WarehouseDetails> list = new ArrayList<WarehouseDetails>();
			for (Warehouse warehouse : all) {
				list.add(new WarehouseDetails().toWarehouseDetails(warehouse, fields));
			}
			return new WarehousePageEvent(list);
		}public WarehousePageEvent requestAllByStoreName(EmbeddedField... fields) throws Exception {
			List<Warehouse> all = warehouseCustomRepo.findAllActiveByStoreName(fields);
			List<WarehouseDetails> list = new ArrayList<WarehouseDetails>();
			for (Warehouse warehouse : all) {
				list.add(new WarehouseDetails().toWarehouseDetails(warehouse, fields));
			}
			return new WarehousePageEvent(list);
		}public WarehousePageEvent requestAllByPhysicalLocation(EmbeddedField... fields) throws Exception {
			List<Warehouse> all = warehouseCustomRepo.findAllActiveByPhysicalLocation(fields);
			List<WarehouseDetails> list = new ArrayList<WarehouseDetails>();
			for (Warehouse warehouse : all) {
				list.add(new WarehouseDetails().toWarehouseDetails(warehouse, fields));
			}
			return new WarehousePageEvent(list);
		}public WarehousePageEvent requestAllByCostingType(EmbeddedField... fields) throws Exception {
			List<Warehouse> all = warehouseCustomRepo.findAllActiveByCostingType(fields);
			List<WarehouseDetails> list = new ArrayList<WarehouseDetails>();
			for (Warehouse warehouse : all) {
				list.add(new WarehouseDetails().toWarehouseDetails(warehouse, fields));
			}
			return new WarehousePageEvent(list);
		}public WarehousePageEvent requestAllByComment(EmbeddedField... fields) throws Exception {
			List<Warehouse> all = warehouseCustomRepo.findAllActiveByComment(fields);
			List<WarehouseDetails> list = new ArrayList<WarehouseDetails>();
			for (Warehouse warehouse : all) {
				list.add(new WarehouseDetails().toWarehouseDetails(warehouse, fields));
			}
			return new WarehousePageEvent(list);
		}public WarehousePageEvent requestAllByStoreType(EmbeddedField... fields) throws Exception {
			List<Warehouse> all = warehouseCustomRepo.findAllActiveByStoreType(fields);
			List<WarehouseDetails> list = new ArrayList<WarehouseDetails>();
			for (Warehouse warehouse : all) {
				list.add(new WarehouseDetails().toWarehouseDetails(warehouse, fields));
			}
			return new WarehousePageEvent(list);
		}
	

	public WarehousePageEvent requestWarehouseFilterPage(RequestWarehousePageEvent event) throws Exception {
		Page<Warehouse> page = warehouseCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new WarehousePageEvent(page, warehouseCustomRepo.getTotalCount());
	}
	
	public WarehousePageEvent requestWarehouseFilter(
			RequestWarehousePageEvent event) throws Exception {
		List<Warehouse> all = warehouseCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		WarehousePageEvent pageEvent = new WarehousePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}