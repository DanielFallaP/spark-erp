package co.com.cybersoft.maintenance.tables.persistence.repository.state;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface StateRepository extends PagingAndSortingRepository<State, Long>{
	State findByCompany(String value);

	State findByNameState(String value);

	State findByTypeState(Integer value);

	State findByClassState(Boolean value);

	State findByActive(Boolean value);


}