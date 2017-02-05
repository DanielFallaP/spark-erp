package co.com.cybersoft.maintenance.tables.events.responsible;

import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;

/**
 * Event class for Responsible
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ResponsibleModificationEvent {

	private ResponsibleDetails responsibleDetails;
	
	public ResponsibleModificationEvent(ResponsibleDetails responsibleDetails){
		this.responsibleDetails=responsibleDetails;
	}

	public ResponsibleDetails getResponsibleDetails() {
		return responsibleDetails;
	}
	
}