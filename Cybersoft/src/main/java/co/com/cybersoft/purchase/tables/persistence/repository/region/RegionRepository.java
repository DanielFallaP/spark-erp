package co.com.cybersoft.purchase.tables.persistence.repository.region;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.Region;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RegionRepository extends PagingAndSortingRepository<Region, Long>{
	
	Region findByContinent(String value);

	Region findByRegion(String value);

	Region findByActive(Boolean value);


}