package co.com.cybersoft.docs.persistence.services.requisition;

import co.com.cybersoft.docs.persistence.repository.requisition.RequisitionRepository;


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
	
	public co.com.cybersoft.docs.persistence.domain.Requisition getRequisitionByField(String field, String value){
		
		return null;
	}
}