package co.com.cybersoft.maintenance.tables.events.technicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;

/**
 * Event class for TechnicalAction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateTechnicalActionEvent {
		
	private TechnicalActionDetails technicalActionDetails;
	
	public CreateTechnicalActionEvent(TechnicalActionDetails technicalActionDetails){
		this.technicalActionDetails=technicalActionDetails;
	}

	public TechnicalActionDetails getTechnicalActionDetails() {
		return technicalActionDetails;
	}
	
	
}