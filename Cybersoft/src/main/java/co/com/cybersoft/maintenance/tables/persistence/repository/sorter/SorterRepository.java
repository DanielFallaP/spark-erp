package co.com.cybersoft.maintenance.tables.persistence.repository.sorter;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface SorterRepository extends PagingAndSortingRepository<Sorter, Long>{
	Sorter findByCompany(String value);

	Sorter findByTypeSorter(String value);

	Sorter findByNameSorter(String value);

	Sorter findByActive(Boolean value);


}