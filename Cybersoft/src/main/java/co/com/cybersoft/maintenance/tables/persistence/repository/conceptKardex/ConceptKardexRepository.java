package co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ConceptKardexRepository extends PagingAndSortingRepository<ConceptKardex, Long>{
	ConceptKardex findByCompany(String value);

	ConceptKardex findByStore(String value);

	ConceptKardex findByNumberConceptKardex(Integer value);

	ConceptKardex findByNameConceptKardex(Integer value);

	ConceptKardex findByTypeConceptKardex(String value);

	ConceptKardex findByIndicatorTransfers(Boolean value);

	ConceptKardex findByWorkOrderConceptKardex(Integer value);

	ConceptKardex findByDefaultIndicatorConcept(Boolean value);

	ConceptKardex findBySubtypeConceptKardex(Boolean value);

	ConceptKardex findByDeliveryConceptContractor(Boolean value);

	ConceptKardex findByActive(Boolean value);


}