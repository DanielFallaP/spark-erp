package co.com.cybersoft.purchase.tables.persistence.repository.country;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, Long>{
	Country findByRegion(String value);

	Country findByCountry(String value);

	Country findByLocalName(String value);

	Country findByFrenchName(String value);

	Country findByActive(Boolean value);


}