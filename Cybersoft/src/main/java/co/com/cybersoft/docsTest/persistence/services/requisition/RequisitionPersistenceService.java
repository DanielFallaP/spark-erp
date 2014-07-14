package co.com.cybersoft.docsTest.persistence.services.requisition;

import java.util.List;

import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequestRequisitionPageEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionEvent;
import co.com.cybersoft.docsTest.events.requisition.RequisitionPageEvent;
import co.com.cybersoft.docsTest.events.requisition.SaveRequisitionEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface RequisitionPersistenceService {
	
	RequisitionEvent saveRequisition(SaveRequisitionEvent event) throws Exception;
	
	RequisitionEvent createRequisitionBodyRecord(SaveRequisitionEvent event) throws Exception;
	
	RequisitionEvent updateRequisitionBody(SaveRequisitionEvent event) throws Exception;
	
	RequisitionEvent deleteRequisitionBodyRecords(SaveRequisitionEvent event, List<String> toDelete) throws Exception;

	RequisitionPageEvent requestRequisitionPage(RequestRequisitionPageEvent event) throws Exception;

	RequisitionEvent requestRequisitionDetails(RequestRequisitionEvent event) throws Exception;
	
}