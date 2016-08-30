package co.com.cybersoft.purchase.tables.core.services.region;

import co.com.cybersoft.purchase.tables.events.region.CreateRegionEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionModificationEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionPageEvent;
import co.com.cybersoft.purchase.tables.persistence.services.region.RegionPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RegionServiceImpl implements RegionService{

	private final RegionPersistenceService regionPersistenceService;
	
	public RegionServiceImpl(final RegionPersistenceService regionPersistenceService){
		this.regionPersistenceService=regionPersistenceService;
	}
	
	/**
	 */
	public RegionDetailsEvent createRegion(CreateRegionEvent event ) throws Exception{
		return regionPersistenceService.createRegion(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public RegionPageEvent requestRegionPage(RequestRegionPageEvent event) throws Exception{
		return regionPersistenceService.requestRegionPage(event);
	}

	public RegionDetailsEvent requestRegionDetails(RequestRegionDetailsEvent event ) throws Exception{
		return regionPersistenceService.requestRegionDetails(event);
	}

	public RegionDetailsEvent modifyRegion(RegionModificationEvent event) throws Exception {
		return regionPersistenceService.modifyRegion(event);
	}
	
	public RegionDetailsEvent requestOnlyRecord() throws Exception {
		return regionPersistenceService.getOnlyRecord();
	}
	
	public RegionPageEvent requestRegionFilterPage(RequestRegionPageEvent event) throws Exception {
		return regionPersistenceService.requestRegionFilterPage(event);
	}
	
	public RegionPageEvent requestRegionFilter(RequestRegionPageEvent event) throws Exception {
		return regionPersistenceService.requestRegionFilter(event);
	}
	

	public RegionPageEvent requestAllByContinent(EmbeddedField... fields) throws Exception {
		return regionPersistenceService.requestAllByContinent(fields);
	}public RegionPageEvent requestAllByRegion(EmbeddedField... fields) throws Exception {
		return regionPersistenceService.requestAllByRegion(fields);
	}
	
	public RegionPageEvent requestByContainingRegion(String region) throws Exception {
				return regionPersistenceService.requestByContainingRegion(region);
			}
	
	
}