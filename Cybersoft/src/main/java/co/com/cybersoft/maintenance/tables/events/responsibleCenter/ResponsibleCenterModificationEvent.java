package co.com.cybersoft.maintenance.tables.events.responsibleCenter;

import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;

/**
 * Event class for ResponsibleCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ResponsibleCenterModificationEvent {

	private ResponsibleCenterDetails responsibleCenterDetails;
	
	public ResponsibleCenterModificationEvent(ResponsibleCenterDetails responsibleCenterDetails){
		this.responsibleCenterDetails=responsibleCenterDetails;
	}

	public ResponsibleCenterDetails getResponsibleCenterDetails() {
		return responsibleCenterDetails;
	}
	
}