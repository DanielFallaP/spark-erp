package co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface TechnicalActionRepository extends PagingAndSortingRepository<TechnicalAction, Long>{
	TechnicalAction findByCompany(String value);

	TechnicalAction findByCode(String value);

	TechnicalAction findByTechnicalActionName(String value);

	TechnicalAction findByActive(Boolean value);


}