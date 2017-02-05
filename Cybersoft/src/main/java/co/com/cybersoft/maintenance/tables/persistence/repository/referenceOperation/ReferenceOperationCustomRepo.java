package co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ReferenceOperationCustomRepo {

	Long getTotalCount() throws Exception;


	List<ReferenceOperation> findAllActiveByReference(EmbeddedField ...fields) throws Exception;
	List<ReferenceOperation> findAllActiveByOperation(EmbeddedField ...fields) throws Exception;

	
	Page<ReferenceOperation> findAll(Pageable pageable, ReferenceOperationFilterInfo filter)throws Exception;

	List<ReferenceOperation> findAllNoPage(Pageable pageable, ReferenceOperationFilterInfo filter)throws Exception;
	
}