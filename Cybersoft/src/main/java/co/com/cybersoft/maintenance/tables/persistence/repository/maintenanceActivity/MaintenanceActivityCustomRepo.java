package co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface MaintenanceActivityCustomRepo {

	Long getTotalCount() throws Exception;


	List<MaintenanceActivity> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<MaintenanceActivity> findAllActiveByNameMaintenanceActivity(EmbeddedField ...fields) throws Exception;
	List<MaintenanceActivity> findAllActiveByDurationUnitStandard(EmbeddedField ...fields) throws Exception;

	
	Page<MaintenanceActivity> findAll(Pageable pageable, MaintenanceActivityFilterInfo filter)throws Exception;

	List<MaintenanceActivity> findAllNoPage(Pageable pageable, MaintenanceActivityFilterInfo filter)throws Exception;
	
}