package co.com.cybersoft.purchase.tables.persistence.repository.continent;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ContinentCustomRepo {

	Long getTotalCount() throws Exception;

	List<Continent> findByContainingContinent(String substring);


	List<Continent> findAllActiveByContinent(EmbeddedField ...fields) throws Exception;

	
	Page<Continent> findAll(Pageable pageable, ContinentFilterInfo filter)throws Exception;

	List<Continent> findAllNoPage(Pageable pageable, ContinentFilterInfo filter)throws Exception;
	
}