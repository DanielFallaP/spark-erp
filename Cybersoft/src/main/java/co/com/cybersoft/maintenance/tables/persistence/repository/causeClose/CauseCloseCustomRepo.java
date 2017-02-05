package co.com.cybersoft.maintenance.tables.persistence.repository.causeClose;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CauseCloseCustomRepo {

	Long getTotalCount() throws Exception;


	List<CauseClose> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<CauseClose> findAllActiveByNameCauseClose(EmbeddedField ...fields) throws Exception;
	List<CauseClose> findAllActiveByTypeCauseClose(EmbeddedField ...fields) throws Exception;

	
	Page<CauseClose> findAll(Pageable pageable, CauseCloseFilterInfo filter)throws Exception;

	List<CauseClose> findAllNoPage(Pageable pageable, CauseCloseFilterInfo filter)throws Exception;
	
}