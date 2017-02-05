package co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface OtherConceptsCustomRepo {

	Long getTotalCount() throws Exception;


	List<OtherConcepts> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<OtherConcepts> findAllActiveByNameOtherconcepts(EmbeddedField ...fields) throws Exception;
	List<OtherConcepts> findAllActiveByUnitMeasure(EmbeddedField ...fields) throws Exception;
	List<OtherConcepts> findAllActiveByTypeWork(EmbeddedField ...fields) throws Exception;

	
	Page<OtherConcepts> findAll(Pageable pageable, OtherConceptsFilterInfo filter)throws Exception;

	List<OtherConcepts> findAllNoPage(Pageable pageable, OtherConceptsFilterInfo filter)throws Exception;
	
}