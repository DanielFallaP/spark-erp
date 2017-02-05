package co.com.cybersoft.maintenance.tables.events.effectFailTechnicalAction;

import co.com.cybersoft.maintenance.tables.core.domain.EffectFailTechnicalActionDetails;

/**
 * Event class for EffectFailTechnicalAction
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateEffectFailTechnicalActionEvent {
		
	private EffectFailTechnicalActionDetails effectFailTechnicalActionDetails;
	
	public CreateEffectFailTechnicalActionEvent(EffectFailTechnicalActionDetails effectFailTechnicalActionDetails){
		this.effectFailTechnicalActionDetails=effectFailTechnicalActionDetails;
	}

	public EffectFailTechnicalActionDetails getEffectFailTechnicalActionDetails() {
		return effectFailTechnicalActionDetails;
	}
	
	
}