package co.com.cybersoft.maintenance.tables.persistence.repository.operation;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.operation.OperationFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface OperationCustomRepo {

	Long getTotalCount() throws Exception;


	List<Operation> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Operation> findAllActiveByCodeOperation(EmbeddedField ...fields) throws Exception;
	List<Operation> findAllActiveByNameOperation(EmbeddedField ...fields) throws Exception;

	
	Page<Operation> findAll(Pageable pageable, OperationFilterInfo filter)throws Exception;

	List<Operation> findAllNoPage(Pageable pageable, OperationFilterInfo filter)throws Exception;
	
}