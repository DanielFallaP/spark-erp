package co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ReferenceOperationRepository extends PagingAndSortingRepository<ReferenceOperation, Long>{
	ReferenceOperation findByReference(String value);

	ReferenceOperation findByOperation(String value);

	ReferenceOperation findByNumOrderRefenceOperation(Integer value);

	ReferenceOperation findByActive(Boolean value);


}