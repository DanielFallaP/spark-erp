package co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ConceptKardexCustomRepo {

	Long getTotalCount() throws Exception;


	List<ConceptKardex> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<ConceptKardex> findAllActiveByStore(EmbeddedField ...fields) throws Exception;
	List<ConceptKardex> findAllActiveByTypeConceptKardex(EmbeddedField ...fields) throws Exception;

	
	Page<ConceptKardex> findAll(Pageable pageable, ConceptKardexFilterInfo filter)throws Exception;

	List<ConceptKardex> findAllNoPage(Pageable pageable, ConceptKardexFilterInfo filter)throws Exception;
	
}