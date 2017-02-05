package co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface MeasurementUnitCustomRepo {

	Long getTotalCount() throws Exception;


	List<MeasurementUnit> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<MeasurementUnit> findAllActiveByNameUnit(EmbeddedField ...fields) throws Exception;
	List<MeasurementUnit> findAllActiveByAbbreviationUnit(EmbeddedField ...fields) throws Exception;

	
	Page<MeasurementUnit> findAll(Pageable pageable, MeasurementUnitFilterInfo filter)throws Exception;

	List<MeasurementUnit> findAllNoPage(Pageable pageable, MeasurementUnitFilterInfo filter)throws Exception;
	
}