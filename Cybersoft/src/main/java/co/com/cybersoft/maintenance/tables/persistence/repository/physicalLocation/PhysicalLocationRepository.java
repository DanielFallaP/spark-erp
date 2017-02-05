package co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface PhysicalLocationRepository extends PagingAndSortingRepository<PhysicalLocation, Long>{
	PhysicalLocation findByCompany(String value);

	PhysicalLocation findByNamePhysicalLocation(String value);

	PhysicalLocation findByDescription(String value);

	PhysicalLocation findByDescriptionEnglish(String value);

	PhysicalLocation findByDescriptionShort(String value);

	PhysicalLocation findByPhysicalLocationArea(Double value);

	PhysicalLocation findByMeasurementUnit(String value);

	PhysicalLocation findByCapacityPhysicalLocation(String value);

	PhysicalLocation findByStatePhysicalLocation(Boolean value);

	PhysicalLocation findByActive(Boolean value);


}