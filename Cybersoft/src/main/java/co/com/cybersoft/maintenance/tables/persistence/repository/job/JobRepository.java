package co.com.cybersoft.maintenance.tables.persistence.repository.job;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface JobRepository extends PagingAndSortingRepository<Job, Long>{
	Job findByResponsibleCenter(String value);

	Job findByNameJob(String value);

	Job findByValueTime1(Double value);

	Job findByValueTime2(Double value);

	Job findByValueTime3(Double value);

	Job findByTypeWork(String value);

	Job findByActive(Boolean value);


}