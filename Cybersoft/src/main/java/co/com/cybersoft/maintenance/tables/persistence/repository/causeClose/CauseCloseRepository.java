package co.com.cybersoft.maintenance.tables.persistence.repository.causeClose;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CauseCloseRepository extends PagingAndSortingRepository<CauseClose, Long>{
	CauseClose findByCompany(String value);

	CauseClose findByNameCauseClose(String value);

	CauseClose findByTypeCauseClose(String value);

	CauseClose findByActive(Boolean value);


}