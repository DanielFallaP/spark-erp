package co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.typeConceptKardex.TypeConceptKardexFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeConceptKardexCustomRepo {

	Long getTotalCount() throws Exception;


	List<TypeConceptKardex> findAllActiveByTypeConceptKardex(EmbeddedField ...fields) throws Exception;

	
	Page<TypeConceptKardex> findAll(Pageable pageable, TypeConceptKardexFilterInfo filter)throws Exception;

	List<TypeConceptKardex> findAllNoPage(Pageable pageable, TypeConceptKardexFilterInfo filter)throws Exception;
	
}