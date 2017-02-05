package co.com.cybersoft.maintenance.tables.persistence.repository.causePending;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CausePendingCustomRepo {

	Long getTotalCount() throws Exception;


	List<CausePending> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<CausePending> findAllActiveByNameCausePending(EmbeddedField ...fields) throws Exception;

	
	Page<CausePending> findAll(Pageable pageable, CausePendingFilterInfo filter)throws Exception;

	List<CausePending> findAllNoPage(Pageable pageable, CausePendingFilterInfo filter)throws Exception;
	
}