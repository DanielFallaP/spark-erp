package co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MaintenanceWorkRepository extends PagingAndSortingRepository<MaintenanceWork, Long>{
	MaintenanceWork findByCompany(String value);

	MaintenanceWork findByNameMaintenanceWork(String value);

	MaintenanceWork findByActive(Boolean value);


}