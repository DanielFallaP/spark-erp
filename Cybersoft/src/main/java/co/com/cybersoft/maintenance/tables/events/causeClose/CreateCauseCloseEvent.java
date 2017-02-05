package co.com.cybersoft.maintenance.tables.events.causeClose;

import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;

/**
 * Event class for CauseClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateCauseCloseEvent {
		
	private CauseCloseDetails causeCloseDetails;
	
	public CreateCauseCloseEvent(CauseCloseDetails causeCloseDetails){
		this.causeCloseDetails=causeCloseDetails;
	}

	public CauseCloseDetails getCauseCloseDetails() {
		return causeCloseDetails;
	}
	
	
}