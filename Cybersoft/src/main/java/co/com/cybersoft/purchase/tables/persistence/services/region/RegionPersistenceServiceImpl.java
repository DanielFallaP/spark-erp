package co.com.cybersoft.purchase.tables.persistence.services.region;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.events.region.CreateRegionEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionModificationEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionCustomRepo;
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
public class RegionPersistenceServiceImpl implements RegionPersistenceService{

	private final RegionRepository regionRepository;
	
	private final RegionCustomRepo regionCustomRepo;
	
	
	public RegionPersistenceServiceImpl(final RegionRepository regionRepository, final RegionCustomRepo regionCustomRepo) {
		this.regionRepository=regionRepository;
		this.regionCustomRepo=regionCustomRepo;
	}
	
	public RegionDetailsEvent createRegion(CreateRegionEvent event) throws Exception{
		Region region = new Region().fromRegionDetails(event.getRegionDetails());
		region = regionRepository.save(region);
		region = regionRepository.findOne(region.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",region.getId());
		return new RegionDetailsEvent(new RegionDetails().toRegionDetails(region));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"region", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public RegionPageEvent requestRegionPage(RequestRegionPageEvent event) throws Exception {
		Page<Region> regions = regionRepository.findAll(event.getPageable());
		return new RegionPageEvent(regions);
	}

	public RegionDetailsEvent requestRegionDetails(RequestRegionDetailsEvent event) throws Exception {
		RegionDetails regionDetails=null;
		if (event.getId()!=null){
			Region region = regionRepository.findOne(event.getId());
			if (region!=null)
				regionDetails = new RegionDetails().toRegionDetails(region);
		}
		else{
							Region region = new RegionPersistenceFactory(regionRepository).getRegionByField(event.getField(),event.getValue());
							if (region!=null)
								regionDetails = new RegionDetails().toRegionDetails(region);
						}
		return new RegionDetailsEvent(regionDetails);
		
	}

	public RegionDetailsEvent modifyRegion(RegionModificationEvent event) throws Exception {
		Region region = new Region().fromRegionDetails(event.getRegionDetails());
		region = regionRepository.save(region);
		region = regionRepository.findOne(region.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",region.getId());
		return new RegionDetailsEvent(new RegionDetails().toRegionDetails(region));
	}
	
		public RegionDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Region> all = regionRepository.findAll();
			RegionDetails single = new RegionDetails();
			for (Region region : all) {
				single=new RegionDetails().toRegionDetails(region);
				break;
			}
			return new RegionDetailsEvent(single);
		}
	
	public RegionPageEvent requestAllByContinent(EmbeddedField... fields) throws Exception {
			List<Region> all = regionCustomRepo.findAllActiveByContinent(fields);
			List<RegionDetails> list = new ArrayList<RegionDetails>();
			for (Region region : all) {
				list.add(new RegionDetails().toRegionDetails(region, fields));
			}
			return new RegionPageEvent(list);
		}public RegionPageEvent requestAllByRegion(EmbeddedField... fields) throws Exception {
			List<Region> all = regionCustomRepo.findAllActiveByRegion(fields);
			List<RegionDetails> list = new ArrayList<RegionDetails>();
			for (Region region : all) {
				list.add(new RegionDetails().toRegionDetails(region, fields));
			}
			return new RegionPageEvent(list);
		}
	
	public RegionPageEvent requestByContainingRegion(String region) throws Exception {
			ArrayList<RegionDetails> list = new ArrayList<RegionDetails>();
			List<Region> regionList = regionCustomRepo.findByContainingRegion(region);
			for (Region regionEntity : regionList) {
				list.add(new RegionDetails().toRegionDetails(regionEntity));
			}
			return new RegionPageEvent(list);
		}

	public RegionPageEvent requestRegionFilterPage(RequestRegionPageEvent event) throws Exception {
		Page<Region> page = regionCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new RegionPageEvent(page, regionCustomRepo.getTotalCount());
	}
	
	public RegionPageEvent requestRegionFilter(
			RequestRegionPageEvent event) throws Exception {
		List<Region> all = regionCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		RegionPageEvent pageEvent = new RegionPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}