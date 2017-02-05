package co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface FailureCauseTechnicalactionRepository extends PagingAndSortingRepository<FailureCauseTechnicalaction, Long>{
	FailureCauseTechnicalaction findByFailureCause(String value);

	FailureCauseTechnicalaction findByTechnicalAction(String value);

	FailureCauseTechnicalaction findByOrderFailureCauseTechnicalactions(Integer value);

	FailureCauseTechnicalaction findByActive(Boolean value);


}