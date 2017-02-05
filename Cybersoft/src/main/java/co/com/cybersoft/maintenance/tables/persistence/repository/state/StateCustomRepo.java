package co.com.cybersoft.maintenance.tables.persistence.repository.state;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface StateCustomRepo {

	Long getTotalCount() throws Exception;


	List<State> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<State> findAllActiveByNameState(EmbeddedField ...fields) throws Exception;

	
	Page<State> findAll(Pageable pageable, StateFilterInfo filter)throws Exception;

	List<State> findAllNoPage(Pageable pageable, StateFilterInfo filter)throws Exception;
	
}