package co.com.cybersoft.maintenance.tables.persistence.repository.responsible;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.responsible.ResponsibleFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ResponsibleCustomRepo {

	Long getTotalCount() throws Exception;


	List<Responsible> findAllActiveByJob(EmbeddedField ...fields) throws Exception;
	List<Responsible> findAllActiveByThird(EmbeddedField ...fields) throws Exception;

	
	Page<Responsible> findAll(Pageable pageable, ResponsibleFilterInfo filter)throws Exception;

	List<Responsible> findAllNoPage(Pageable pageable, ResponsibleFilterInfo filter)throws Exception;
	
}