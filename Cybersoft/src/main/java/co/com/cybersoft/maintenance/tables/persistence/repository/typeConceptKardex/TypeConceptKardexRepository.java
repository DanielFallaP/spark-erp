package co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface TypeConceptKardexRepository extends PagingAndSortingRepository<TypeConceptKardex, Long>{
	TypeConceptKardex findByTypeConceptKardex(String value);

	TypeConceptKardex findByActive(Boolean value);


}