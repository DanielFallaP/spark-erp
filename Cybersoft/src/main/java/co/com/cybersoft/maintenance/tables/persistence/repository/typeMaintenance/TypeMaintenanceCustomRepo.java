package co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeMaintenance.TypeMaintenanceFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeMaintenanceCustomRepo {

	Long getTotalCount() throws Exception;


	List<TypeMaintenance> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<TypeMaintenance> findAllActiveByNameTypeMaintenance(EmbeddedField ...fields) throws Exception;

	
	Page<TypeMaintenance> findAll(Pageable pageable, TypeMaintenanceFilterInfo filter)throws Exception;

	List<TypeMaintenance> findAllNoPage(Pageable pageable, TypeMaintenanceFilterInfo filter)throws Exception;
	
}