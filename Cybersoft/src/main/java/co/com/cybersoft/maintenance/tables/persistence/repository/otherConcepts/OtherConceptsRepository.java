package co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface OtherConceptsRepository extends PagingAndSortingRepository<OtherConcepts, Long>{
	OtherConcepts findByCompany(String value);

	OtherConcepts findByNameOtherconcepts(String value);

	OtherConcepts findByUnitValue(Double value);

	OtherConcepts findByUnitMeasure(String value);

	OtherConcepts findByTypeWork(String value);

	OtherConcepts findByInformative(Integer value);

	OtherConcepts findByActive(Boolean value);


}