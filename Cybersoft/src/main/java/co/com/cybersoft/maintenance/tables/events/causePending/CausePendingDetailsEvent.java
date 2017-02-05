package co.com.cybersoft.maintenance.tables.events.causePending;

import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;

/**
 * Event class for CausePending
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CausePendingDetailsEvent {
	
	private CausePendingDetails causePendingDetails;
	
	public CausePendingDetailsEvent(CausePendingDetails causePendingDetails){
		this.causePendingDetails=causePendingDetails;
	}

	public CausePendingDetails getCausePendingDetails() {
		return causePendingDetails;
	}

}