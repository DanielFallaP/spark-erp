package co.com.cybersoft.maintenance.tables.events.effectFail;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;

/**
 * Event class for EffectFail
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class EffectFailDetailsEvent {
	
	private EffectFailDetails effectFailDetails;
	
	public EffectFailDetailsEvent(EffectFailDetails effectFailDetails){
		this.effectFailDetails=effectFailDetails;
	}

	public EffectFailDetails getEffectFailDetails() {
		return effectFailDetails;
	}

}