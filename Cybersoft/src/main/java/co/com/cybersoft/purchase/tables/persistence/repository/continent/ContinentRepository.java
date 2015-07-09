package co.com.cybersoft.purchase.tables.persistence.repository.continent;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ContinentRepository extends PagingAndSortingRepository<Continent, Long>{
	Continent findByContinent(String value);

	Continent findByActive(Boolean value);


}