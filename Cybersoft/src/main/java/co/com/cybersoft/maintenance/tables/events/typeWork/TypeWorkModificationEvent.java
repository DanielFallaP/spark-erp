package co.com.cybersoft.maintenance.tables.events.typeWork;

import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;

/**
 * Event class for TypeWork
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeWorkModificationEvent {

	private TypeWorkDetails typeWorkDetails;
	
	public TypeWorkModificationEvent(TypeWorkDetails typeWorkDetails){
		this.typeWorkDetails=typeWorkDetails;
	}

	public TypeWorkDetails getTypeWorkDetails() {
		return typeWorkDetails;
	}
	
}