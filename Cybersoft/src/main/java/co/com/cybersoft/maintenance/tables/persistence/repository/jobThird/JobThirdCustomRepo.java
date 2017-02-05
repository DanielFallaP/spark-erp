package co.com.cybersoft.maintenance.tables.persistence.repository.jobThird;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.jobThird.JobThirdFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface JobThirdCustomRepo {

	Long getTotalCount() throws Exception;


	List<JobThird> findAllActiveByJob(EmbeddedField ...fields) throws Exception;
	List<JobThird> findAllActiveByThird(EmbeddedField ...fields) throws Exception;

	
	Page<JobThird> findAll(Pageable pageable, JobThirdFilterInfo filter)throws Exception;

	List<JobThird> findAllNoPage(Pageable pageable, JobThirdFilterInfo filter)throws Exception;
	
}