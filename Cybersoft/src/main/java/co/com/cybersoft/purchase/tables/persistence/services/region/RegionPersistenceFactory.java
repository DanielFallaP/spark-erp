package co.com.cybersoft.purchase.tables.persistence.services.region;

import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionRepository;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RegionPersistenceFactory {

	RegionRepository regionRepository;
	
	public RegionPersistenceFactory(RegionRepository regionRepository){
		this.regionRepository=regionRepository;
	}
	
	public co.com.cybersoft.purchase.tables.persistence.domain.Region getRegionByField(String field, String value){
		if (field.equals("region"))
					return regionRepository.findByRegion(value);		
		return null;
	}
}