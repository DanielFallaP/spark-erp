package co.com.cybersoft.maintenance.tables.persistence.repository.storeType;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface StoreTypeRepository extends PagingAndSortingRepository<StoreType, Long>{
	StoreType findByStoreName(String value);

	StoreType findByActive(Boolean value);


}