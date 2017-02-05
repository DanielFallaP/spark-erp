package co.com.cybersoft.maintenance.tables.events.otherConcepts;

import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;

/**
 * Event class for OtherConcepts
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OtherConceptsDetailsEvent {
	
	private OtherConceptsDetails otherConceptsDetails;
	
	public OtherConceptsDetailsEvent(OtherConceptsDetails otherConceptsDetails){
		this.otherConceptsDetails=otherConceptsDetails;
	}

	public OtherConceptsDetails getOtherConceptsDetails() {
		return otherConceptsDetails;
	}

}