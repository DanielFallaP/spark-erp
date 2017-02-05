package co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.CreateMeasurementUnitEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.MeasurementUnitModificationEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.measurementUnit.RequestMeasurementUnitPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit.MeasurementUnitRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit.MeasurementUnitCustomRepo;
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
public class MeasurementUnitPersistenceServiceImpl implements MeasurementUnitPersistenceService{

	private final MeasurementUnitRepository measurementUnitRepository;
	
	private final MeasurementUnitCustomRepo measurementUnitCustomRepo;
	
	
	public MeasurementUnitPersistenceServiceImpl(final MeasurementUnitRepository measurementUnitRepository, final MeasurementUnitCustomRepo measurementUnitCustomRepo) {
		this.measurementUnitRepository=measurementUnitRepository;
		this.measurementUnitCustomRepo=measurementUnitCustomRepo;
	}
	
	public MeasurementUnitDetailsEvent createMeasurementUnit(CreateMeasurementUnitEvent event) throws Exception{
		MeasurementUnit measurementUnit = new MeasurementUnit().fromMeasurementUnitDetails(event.getMeasurementUnitDetails());
		measurementUnit = measurementUnitRepository.save(measurementUnit);
		measurementUnit = measurementUnitRepository.findOne(measurementUnit.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",measurementUnit.getId());
		return new MeasurementUnitDetailsEvent(new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"measurementUnit", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public MeasurementUnitPageEvent requestMeasurementUnitPage(RequestMeasurementUnitPageEvent event) throws Exception {
		Page<MeasurementUnit> measurementUnits = measurementUnitRepository.findAll(event.getPageable());
		return new MeasurementUnitPageEvent(measurementUnits);
	}

	public MeasurementUnitDetailsEvent requestMeasurementUnitDetails(RequestMeasurementUnitDetailsEvent event) throws Exception {
		MeasurementUnitDetails measurementUnitDetails=null;
		if (event.getId()!=null){
			MeasurementUnit measurementUnit = measurementUnitRepository.findOne(event.getId());
			if (measurementUnit!=null)
				measurementUnitDetails = new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit);
		}
		return new MeasurementUnitDetailsEvent(measurementUnitDetails);
		
	}

	public MeasurementUnitDetailsEvent modifyMeasurementUnit(MeasurementUnitModificationEvent event) throws Exception {
		MeasurementUnit measurementUnit = new MeasurementUnit().fromMeasurementUnitDetails(event.getMeasurementUnitDetails());
		measurementUnit = measurementUnitRepository.save(measurementUnit);
		measurementUnit = measurementUnitRepository.findOne(measurementUnit.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",measurementUnit.getId());
		return new MeasurementUnitDetailsEvent(new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit));
	}
	
		public MeasurementUnitDetailsEvent getOnlyRecord() throws Exception {
			Iterable<MeasurementUnit> all = measurementUnitRepository.findAll();
			MeasurementUnitDetails single = new MeasurementUnitDetails();
			for (MeasurementUnit measurementUnit : all) {
				single=new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit);
				break;
			}
			return new MeasurementUnitDetailsEvent(single);
		}
	
	public MeasurementUnitPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<MeasurementUnit> all = measurementUnitCustomRepo.findAllActiveByCompany(fields);
			List<MeasurementUnitDetails> list = new ArrayList<MeasurementUnitDetails>();
			for (MeasurementUnit measurementUnit : all) {
				list.add(new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit, fields));
			}
			return new MeasurementUnitPageEvent(list);
		}public MeasurementUnitPageEvent requestAllByNameUnit(EmbeddedField... fields) throws Exception {
			List<MeasurementUnit> all = measurementUnitCustomRepo.findAllActiveByNameUnit(fields);
			List<MeasurementUnitDetails> list = new ArrayList<MeasurementUnitDetails>();
			for (MeasurementUnit measurementUnit : all) {
				list.add(new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit, fields));
			}
			return new MeasurementUnitPageEvent(list);
		}public MeasurementUnitPageEvent requestAllByAbbreviationUnit(EmbeddedField... fields) throws Exception {
			List<MeasurementUnit> all = measurementUnitCustomRepo.findAllActiveByAbbreviationUnit(fields);
			List<MeasurementUnitDetails> list = new ArrayList<MeasurementUnitDetails>();
			for (MeasurementUnit measurementUnit : all) {
				list.add(new MeasurementUnitDetails().toMeasurementUnitDetails(measurementUnit, fields));
			}
			return new MeasurementUnitPageEvent(list);
		}
	

	public MeasurementUnitPageEvent requestMeasurementUnitFilterPage(RequestMeasurementUnitPageEvent event) throws Exception {
		Page<MeasurementUnit> page = measurementUnitCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new MeasurementUnitPageEvent(page, measurementUnitCustomRepo.getTotalCount());
	}
	
	public MeasurementUnitPageEvent requestMeasurementUnitFilter(
			RequestMeasurementUnitPageEvent event) throws Exception {
		List<MeasurementUnit> all = measurementUnitCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		MeasurementUnitPageEvent pageEvent = new MeasurementUnitPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}