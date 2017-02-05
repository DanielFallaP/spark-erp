package co.com.cybersoft.maintenance.tables.persistence.repository.failureCause;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface FailureCauseRepository extends PagingAndSortingRepository<FailureCause, Long>{
	FailureCause findByCompany(String value);

	FailureCause findByNameFailureCause(String value);

	FailureCause findByActive(Boolean value);


}