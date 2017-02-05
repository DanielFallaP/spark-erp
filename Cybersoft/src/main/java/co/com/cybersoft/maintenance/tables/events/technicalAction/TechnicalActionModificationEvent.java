package co.com.cybersoft.maintenance.tables.events.technicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;

/**
 * Event class for TechnicalAction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TechnicalActionModificationEvent {

	private TechnicalActionDetails technicalActionDetails;
	
	public TechnicalActionModificationEvent(TechnicalActionDetails technicalActionDetails){
		this.technicalActionDetails=technicalActionDetails;
	}

	public TechnicalActionDetails getTechnicalActionDetails() {
		return technicalActionDetails;
	}
	
}