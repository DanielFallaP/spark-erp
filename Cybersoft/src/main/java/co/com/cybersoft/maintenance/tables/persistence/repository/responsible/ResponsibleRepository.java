package co.com.cybersoft.maintenance.tables.persistence.repository.responsible;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ResponsibleRepository extends PagingAndSortingRepository<Responsible, Long>{
	Responsible findByJob(String value);

	Responsible findByThird(String value);

	Responsible findByStateResponsible(Boolean value);

	Responsible findByActive(Boolean value);


}