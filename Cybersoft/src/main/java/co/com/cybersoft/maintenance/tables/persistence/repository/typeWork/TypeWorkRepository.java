package co.com.cybersoft.maintenance.tables.persistence.repository.typeWork;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface TypeWorkRepository extends PagingAndSortingRepository<TypeWork, Long>{
	TypeWork findByTypeWork(String value);

	TypeWork findByActive(Boolean value);


}