package co.com.cybersoft.maintenance.tables.events.otherConcepts;

import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;

/**
 * Event class for OtherConcepts
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OtherConceptsModificationEvent {

	private OtherConceptsDetails otherConceptsDetails;
	
	public OtherConceptsModificationEvent(OtherConceptsDetails otherConceptsDetails){
		this.otherConceptsDetails=otherConceptsDetails;
	}

	public OtherConceptsDetails getOtherConceptsDetails() {
		return otherConceptsDetails;
	}
	
}