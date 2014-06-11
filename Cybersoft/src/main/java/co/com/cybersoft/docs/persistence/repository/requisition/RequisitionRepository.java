package co.com.cybersoft.docs.persistence.repository.requisition;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.docs.persistence.domain.Requisition;

import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RequisitionRepository extends MongoRepository<Requisition, String>{
	Requisition findByConsecutive(Integer value);

	Requisition findByDate(Date value);

	Requisition findByStock(Boolean value);

	Requisition findByRequiredOnDate(Date value);

	Requisition findByPriority(String value);

	Requisition findByRequestingDepartment(String value);

	Requisition findByExpenseType(String value);

	Requisition findByTransportationType(String value);

	Requisition findByReceivingWarehouse(String value);

	Requisition findByActive(Boolean value);


}