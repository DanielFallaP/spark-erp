package co.com.cybersoft.docsTest.persistence.services.requisition;

import java.util.List;

import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionPageEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionModificationEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionPageEvent;
import co.com.cybersoft.docsTest.events.requisition.SaveRequisitionEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RequisitionPersistenceService {
	
	RequisitionEvent saveRequisition(SaveRequisitionEvent event) throws Exception;
	
	RequisitionEvent saveRequisitionBody(SaveRequisitionEvent event) throws Exception;
	
	RequisitionEvent updateRequisitionBody(SaveRequisitionEvent event) throws Exception;

	RequisitionEvent createRequisition(SaveRequisitionEvent event) throws Exception;
	
	RequisitionEvent deleteRequisition(SaveRequisitionEvent event, List<String> toDelete) throws Exception;

	RequisitionPageEvent requestRequisitionPage(RequestRequisitionPageEvent event) throws Exception;

	RequisitionEvent requestRequisitionDetails(RequestRequisitionEvent event) throws Exception;
	
	RequisitionEvent modifyRequisition(RequisitionModificationEvent event) throws Exception;
	
	RequisitionPageEvent requestAllByPriority(EmbeddedField... fields) throws Exception;
	RequisitionPageEvent requestAllByRequestingDepartment(EmbeddedField... fields) throws Exception;
	RequisitionPageEvent requestAllByExpenseType(EmbeddedField... fields) throws Exception;
	RequisitionPageEvent requestAllByTransportationType(EmbeddedField... fields) throws Exception;
	RequisitionPageEvent requestAllByReceivingWarehouse(EmbeddedField... fields) throws Exception;
	
}