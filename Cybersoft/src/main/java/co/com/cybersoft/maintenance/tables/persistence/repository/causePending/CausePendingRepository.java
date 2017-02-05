package co.com.cybersoft.maintenance.tables.persistence.repository.causePending;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CausePendingRepository extends PagingAndSortingRepository<CausePending, Long>{
	CausePending findByCompany(String value);

	CausePending findByNameCausePending(String value);

	CausePending findByActive(Boolean value);


}