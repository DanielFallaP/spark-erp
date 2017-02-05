package co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MaintenanceActivityRepository extends PagingAndSortingRepository<MaintenanceActivity, Long>{
	MaintenanceActivity findByCompany(String value);

	MaintenanceActivity findByNameMaintenanceActivity(String value);

	MaintenanceActivity findByStandardCost(Double value);

	MaintenanceActivity findByStandarDuration(Integer value);

	MaintenanceActivity findByDurationUnitStandard(String value);

	MaintenanceActivity findByActive(Boolean value);


}