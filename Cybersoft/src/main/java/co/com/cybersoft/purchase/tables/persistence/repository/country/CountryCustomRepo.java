package co.com.cybersoft.purchase.tables.persistence.repository.country;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CountryCustomRepo {

	Long getTotalCount() throws Exception;

	List<Country> findByContainingCountry(String substring);
	List<Country> findByContainingLocalName(String substring);
	List<Country> findByContainingFrenchName(String substring);


	List<Country> findAllActiveByRegion(EmbeddedField ...fields) throws Exception;
	List<Country> findAllActiveByCountry(EmbeddedField ...fields) throws Exception;
	List<Country> findAllActiveByLocalName(EmbeddedField ...fields) throws Exception;
	List<Country> findAllActiveByFrenchName(EmbeddedField ...fields) throws Exception;

	
	Page<Country> findAll(Pageable pageable, CountryFilterInfo filter)throws Exception;

	List<Country> findAllNoPage(Pageable pageable, CountryFilterInfo filter)throws Exception;
	
}