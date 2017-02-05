package co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface PhysicalLocationCustomRepo {

	Long getTotalCount() throws Exception;


	List<PhysicalLocation> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<PhysicalLocation> findAllActiveByNamePhysicalLocation(EmbeddedField ...fields) throws Exception;
	List<PhysicalLocation> findAllActiveByDescription(EmbeddedField ...fields) throws Exception;
	List<PhysicalLocation> findAllActiveByDescriptionEnglish(EmbeddedField ...fields) throws Exception;
	List<PhysicalLocation> findAllActiveByDescriptionShort(EmbeddedField ...fields) throws Exception;
	List<PhysicalLocation> findAllActiveByMeasurementUnit(EmbeddedField ...fields) throws Exception;
	List<PhysicalLocation> findAllActiveByCapacityPhysicalLocation(EmbeddedField ...fields) throws Exception;

	
	Page<PhysicalLocation> findAll(Pageable pageable, PhysicalLocationFilterInfo filter)throws Exception;

	List<PhysicalLocation> findAllNoPage(Pageable pageable, PhysicalLocationFilterInfo filter)throws Exception;
	
}