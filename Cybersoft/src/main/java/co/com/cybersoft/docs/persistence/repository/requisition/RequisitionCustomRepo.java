package co.com.cybersoft.docs.persistence.repository.requisition;

import java.util.List;

import co.com.cybersoft.docs.persistence.domain.Requisition;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RequisitionCustomRepo {

	Requisition save(Requisition requisition) throws Exception;
	List<Requisition> findAllActiveByPriority(EmbeddedField ...fields) throws Exception;
	List<Requisition> findAllActiveByRequestingDepartment(EmbeddedField ...fields) throws Exception;
	List<Requisition> findAllActiveByExpenseType(EmbeddedField ...fields) throws Exception;
	List<Requisition> findAllActiveByTransportationType(EmbeddedField ...fields) throws Exception;
	List<Requisition> findAllActiveByReceivingWarehouse(EmbeddedField ...fields) throws Exception;

}