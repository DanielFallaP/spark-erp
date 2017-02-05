package co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface StateCostCentersRepository extends PagingAndSortingRepository<StateCostCenters, Long>{
	StateCostCenters findByStateCostCenters(String value);

	StateCostCenters findByActive(Boolean value);


}