package co.com.cybersoft.maintenance.tables.persistence.repository.jobThird;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface JobThirdRepository extends PagingAndSortingRepository<JobThird, Long>{
	JobThird findByJob(String value);

	JobThird findByThird(String value);

	JobThird findByStateJobThird(Boolean value);

	JobThird findByActive(Boolean value);


}