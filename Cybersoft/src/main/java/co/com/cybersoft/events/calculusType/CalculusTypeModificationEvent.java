package co.com.cybersoft.events.calculusType;

import co.com.cybersoft.core.domain.CalculusTypeDetails;

/**
 * Event class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CalculusTypeModificationEvent {

	private CalculusTypeDetails calculusTypeDetails;
	
	public CalculusTypeModificationEvent(CalculusTypeDetails calculusTypeDetails){
		this.calculusTypeDetails=calculusTypeDetails;
	}

	public CalculusTypeDetails getCalculusTypeDetails() {
		return calculusTypeDetails;
	}
	
}