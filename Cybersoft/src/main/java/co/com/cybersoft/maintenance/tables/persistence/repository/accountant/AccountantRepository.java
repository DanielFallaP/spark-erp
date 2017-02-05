package co.com.cybersoft.maintenance.tables.persistence.repository.accountant;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface AccountantRepository extends PagingAndSortingRepository<Accountant, Long>{
	Accountant findByCompany(String value);

	Accountant findByNameAccountant(String value);

	Accountant findByNumber(Double value);

	Accountant findByActive(Boolean value);


}