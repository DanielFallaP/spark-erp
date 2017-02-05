package co.com.cybersoft.maintenance.tables.persistence.repository.typeWork;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeWorkCustomRepo {

	Long getTotalCount() throws Exception;


	List<TypeWork> findAllActiveByTypeWork(EmbeddedField ...fields) throws Exception;

	
	Page<TypeWork> findAll(Pageable pageable, TypeWorkFilterInfo filter)throws Exception;

	List<TypeWork> findAllNoPage(Pageable pageable, TypeWorkFilterInfo filter)throws Exception;
	
}