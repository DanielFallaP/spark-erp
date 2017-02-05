package co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface MaintenanceWorkCustomRepo {

	Long getTotalCount() throws Exception;


	List<MaintenanceWork> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<MaintenanceWork> findAllActiveByNameMaintenanceWork(EmbeddedField ...fields) throws Exception;

	
	Page<MaintenanceWork> findAll(Pageable pageable, MaintenanceWorkFilterInfo filter)throws Exception;

	List<MaintenanceWork> findAllNoPage(Pageable pageable, MaintenanceWorkFilterInfo filter)throws Exception;
	
}