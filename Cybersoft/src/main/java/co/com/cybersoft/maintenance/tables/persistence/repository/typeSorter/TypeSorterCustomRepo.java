package co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeSorterCustomRepo {

	Long getTotalCount() throws Exception;


	List<TypeSorter> findAllActiveByNameTypeSorter(EmbeddedField ...fields) throws Exception;

	
	Page<TypeSorter> findAll(Pageable pageable, TypeSorterFilterInfo filter)throws Exception;

	List<TypeSorter> findAllNoPage(Pageable pageable, TypeSorterFilterInfo filter)throws Exception;
	
}