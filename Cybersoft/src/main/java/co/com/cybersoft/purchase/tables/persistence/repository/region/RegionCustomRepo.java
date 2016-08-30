package co.com.cybersoft.purchase.tables.persistence.repository.region;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface RegionCustomRepo {

	Long getTotalCount() throws Exception;

	List<Region> findByContainingRegion(String substring);


	List<Region> findAllActiveByContinent(EmbeddedField ...fields) throws Exception;
	List<Region> findAllActiveByRegion(EmbeddedField ...fields) throws Exception;

	
	Page<Region> findAll(Pageable pageable, RegionFilterInfo filter)throws Exception;

	List<Region> findAllNoPage(Pageable pageable, RegionFilterInfo filter)throws Exception;
	
}