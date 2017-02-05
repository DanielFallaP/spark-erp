package co.com.cybersoft.maintenance.tables.persistence.repository.operation;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long>{
	Operation findByCompany(String value);

	Operation findByCodeOperation(String value);

	Operation findByNameOperation(String value);

	Operation findByStateOperation(Boolean value);

	Operation findByActive(Boolean value);


}