package co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeCauseCloseCustomRepo {

	Long getTotalCount() throws Exception;


	List<TypeCauseClose> findAllActiveByTypeCauseClose(EmbeddedField ...fields) throws Exception;

	
	Page<TypeCauseClose> findAll(Pageable pageable, TypeCauseCloseFilterInfo filter)throws Exception;

	List<TypeCauseClose> findAllNoPage(Pageable pageable, TypeCauseCloseFilterInfo filter)throws Exception;
	
}