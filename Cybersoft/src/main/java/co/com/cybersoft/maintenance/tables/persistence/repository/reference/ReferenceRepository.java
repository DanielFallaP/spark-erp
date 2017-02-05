package co.com.cybersoft.maintenance.tables.persistence.repository.reference;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ReferenceRepository extends PagingAndSortingRepository<Reference, Long>{
	Reference findByCompany(String value);

	Reference findByCodeReference(String value);

	Reference findByNameReference(String value);

	Reference findByStateReference(Boolean value);

	Reference findByActive(Boolean value);


}