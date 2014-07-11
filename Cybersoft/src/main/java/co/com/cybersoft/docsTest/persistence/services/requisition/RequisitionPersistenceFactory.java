package co.com.cybersoft.docsTest.persistence.services.requisition;

import co.com.cybersoft.docsTest.persistence.repository.requisition.RequisitionRepository;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionPersistenceFactory {

	RequisitionRepository requisitionRepository;
	
	public RequisitionPersistenceFactory(RequisitionRepository requisitionRepository){
		this.requisitionRepository=requisitionRepository;
	}
	
	public co.com.cybersoft.docsTest.persistence.domain.Requisition getRequisitionByField(String field, String value){
		
		return null;
	}
}