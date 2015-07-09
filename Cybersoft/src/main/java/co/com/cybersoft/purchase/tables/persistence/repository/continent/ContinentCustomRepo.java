package co.com.cybersoft.purchase.tables.persistence.repository.continent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public interface ContinentCustomRepo {

	List<Continent> findByContainingContinent(String substring);


	List<Continent> findAllActiveByContinent(EmbeddedField ...fields) throws Exception;

	
	Page<Continent> findAll(Pageable pageable, ContinentFilterInfo filter)throws Exception;
	
}