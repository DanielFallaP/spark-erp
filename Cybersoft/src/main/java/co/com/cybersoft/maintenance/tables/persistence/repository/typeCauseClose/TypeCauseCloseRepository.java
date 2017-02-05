package co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface TypeCauseCloseRepository extends PagingAndSortingRepository<TypeCauseClose, Long>{
	TypeCauseClose findByTypeCauseClose(String value);

	TypeCauseClose findByActive(Boolean value);


}