package co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface FailureCauseTechnicalactionCustomRepo {

	Long getTotalCount() throws Exception;


	List<FailureCauseTechnicalaction> findAllActiveByFailureCause(EmbeddedField ...fields) throws Exception;
	List<FailureCauseTechnicalaction> findAllActiveByTechnicalAction(EmbeddedField ...fields) throws Exception;

	
	Page<FailureCauseTechnicalaction> findAll(Pageable pageable, FailureCauseTechnicalactionFilterInfo filter)throws Exception;

	List<FailureCauseTechnicalaction> findAllNoPage(Pageable pageable, FailureCauseTechnicalactionFilterInfo filter)throws Exception;
	
}