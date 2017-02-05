package co.com.cybersoft.maintenance.tables.persistence.repository.job;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface JobCustomRepo {

	Long getTotalCount() throws Exception;


	List<Job> findAllActiveByResponsibleCenter(EmbeddedField ...fields) throws Exception;
	List<Job> findAllActiveByNameJob(EmbeddedField ...fields) throws Exception;
	List<Job> findAllActiveByTypeWork(EmbeddedField ...fields) throws Exception;

	
	Page<Job> findAll(Pageable pageable, JobFilterInfo filter)throws Exception;

	List<Job> findAllNoPage(Pageable pageable, JobFilterInfo filter)throws Exception;
	
}