package co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ResponsibleCenterRepository extends PagingAndSortingRepository<ResponsibleCenter, Long>{
	ResponsibleCenter findByCompany(String value);

	ResponsibleCenter findByNameResponsibleCenter(String value);

	ResponsibleCenter findByActive(Boolean value);


}