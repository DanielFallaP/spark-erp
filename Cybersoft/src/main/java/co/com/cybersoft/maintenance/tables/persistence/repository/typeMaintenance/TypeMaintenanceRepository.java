package co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface TypeMaintenanceRepository extends PagingAndSortingRepository<TypeMaintenance, Long>{
	TypeMaintenance findByCompany(String value);

	TypeMaintenance findByNameTypeMaintenance(String value);

	TypeMaintenance findByActive(Boolean value);


}