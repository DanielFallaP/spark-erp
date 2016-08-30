package co.com.cybersoft.purchase.tables.core.services.region;

import co.com.cybersoft.purchase.tables.events.region.CreateRegionEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;
import co.com.cybersoft.purchase.tables.events.region.RegionModificationEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionDetailsEvent;
import co.com.cybersoft.purchase.tables.events.region.RequestRegionPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface RegionService {
	RegionDetailsEvent requestOnlyRecord() throws Exception;

	RegionDetailsEvent createRegion(CreateRegionEvent event ) throws Exception;
	
	RegionPageEvent requestRegionPage(RequestRegionPageEvent event) throws Exception;

	RegionDetailsEvent requestRegionDetails(RequestRegionDetailsEvent event ) throws Exception;

	RegionDetailsEvent modifyRegion(RegionModificationEvent event) throws Exception;
	
	RegionPageEvent requestAllByContinent(EmbeddedField... fields) throws Exception;
	RegionPageEvent requestAllByRegion(EmbeddedField... fields) throws Exception;

	
	RegionPageEvent requestByContainingRegion(String region) throws Exception;
	
	RegionPageEvent requestRegionFilterPage(RequestRegionPageEvent event) throws Exception;

	RegionPageEvent requestRegionFilter(RequestRegionPageEvent event) throws Exception;
	
}