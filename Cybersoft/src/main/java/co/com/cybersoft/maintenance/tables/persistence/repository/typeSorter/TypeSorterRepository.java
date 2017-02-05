package co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface TypeSorterRepository extends PagingAndSortingRepository<TypeSorter, Long>{
	TypeSorter findByNameTypeSorter(String value);

	TypeSorter findByActive(Boolean value);


}