package co.com.cybersoft.maintenance.tables.persistence.repository.costingType;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CostingTypeRepository extends PagingAndSortingRepository<CostingType, Long>{
	CostingType findByCostingType(String value);

	CostingType findByActive(Boolean value);


}