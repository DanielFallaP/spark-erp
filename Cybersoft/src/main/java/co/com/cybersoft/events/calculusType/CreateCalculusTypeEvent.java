package co.com.cybersoft.events.calculusType;

import co.com.cybersoft.core.domain.CalculusTypeDetails;

/**
 * Event class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateCalculusTypeEvent {
		
	private CalculusTypeDetails calculusTypeDetails;
	
	public CreateCalculusTypeEvent(CalculusTypeDetails calculusTypeDetails){
		this.calculusTypeDetails=calculusTypeDetails;
	}

	public CalculusTypeDetails getCalculusTypeDetails() {
		return calculusTypeDetails;
	}
	
	
}