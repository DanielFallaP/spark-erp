package co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ResponsibleCenterCustomRepo {

	Long getTotalCount() throws Exception;


	List<ResponsibleCenter> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<ResponsibleCenter> findAllActiveByNameResponsibleCenter(EmbeddedField ...fields) throws Exception;

	
	Page<ResponsibleCenter> findAll(Pageable pageable, ResponsibleCenterFilterInfo filter)throws Exception;

	List<ResponsibleCenter> findAllNoPage(Pageable pageable, ResponsibleCenterFilterInfo filter)throws Exception;
	
}