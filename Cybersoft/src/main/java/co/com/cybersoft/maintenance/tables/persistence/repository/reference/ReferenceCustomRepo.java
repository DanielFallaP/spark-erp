package co.com.cybersoft.maintenance.tables.persistence.repository.reference;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ReferenceCustomRepo {

	Long getTotalCount() throws Exception;


	List<Reference> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Reference> findAllActiveByCodeReference(EmbeddedField ...fields) throws Exception;
	List<Reference> findAllActiveByNameReference(EmbeddedField ...fields) throws Exception;

	
	Page<Reference> findAll(Pageable pageable, ReferenceFilterInfo filter)throws Exception;

	List<Reference> findAllNoPage(Pageable pageable, ReferenceFilterInfo filter)throws Exception;
	
}