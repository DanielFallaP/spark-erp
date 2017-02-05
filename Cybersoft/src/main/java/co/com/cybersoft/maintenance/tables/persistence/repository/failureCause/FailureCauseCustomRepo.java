package co.com.cybersoft.maintenance.tables.persistence.repository.failureCause;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface FailureCauseCustomRepo {

	Long getTotalCount() throws Exception;


	List<FailureCause> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<FailureCause> findAllActiveByNameFailureCause(EmbeddedField ...fields) throws Exception;

	
	Page<FailureCause> findAll(Pageable pageable, FailureCauseFilterInfo filter)throws Exception;

	List<FailureCause> findAllNoPage(Pageable pageable, FailureCauseFilterInfo filter)throws Exception;
	
}