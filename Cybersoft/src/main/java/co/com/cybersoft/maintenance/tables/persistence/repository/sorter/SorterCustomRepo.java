package co.com.cybersoft.maintenance.tables.persistence.repository.sorter;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface SorterCustomRepo {

	Long getTotalCount() throws Exception;


	List<Sorter> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Sorter> findAllActiveByTypeSorter(EmbeddedField ...fields) throws Exception;
	List<Sorter> findAllActiveByNameSorter(EmbeddedField ...fields) throws Exception;

	
	Page<Sorter> findAll(Pageable pageable, SorterFilterInfo filter)throws Exception;

	List<Sorter> findAllNoPage(Pageable pageable, SorterFilterInfo filter)throws Exception;
	
}