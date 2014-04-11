package co.com.cybersoft.events.afe;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Afe;

/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfePageEvent {
	private Page<Afe> afePage;
	
	public AfePageEvent(Page<Afe>  afePage){
		this.afePage= afePage;
	}

	public Page<Afe> getAfePage() {
		return afePage;
	}
	
}