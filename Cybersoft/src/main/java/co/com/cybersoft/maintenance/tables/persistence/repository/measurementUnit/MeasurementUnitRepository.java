package co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface MeasurementUnitRepository extends PagingAndSortingRepository<MeasurementUnit, Long>{
	MeasurementUnit findByCompany(String value);

	MeasurementUnit findByNameUnit(String value);

	MeasurementUnit findByAbbreviationUnit(String value);

	MeasurementUnit findByTypeUnit(Integer value);

	MeasurementUnit findByConversionPattern(Integer value);

	MeasurementUnit findByActive(Boolean value);


}