package co.com.cybersoft.events.afe;

import co.com.cybersoft.core.domain.AFEDetails;

public class AFECreatedEvent {
	
	private final AFEDetails details;
	
	public AFECreatedEvent(final AFEDetails details){
		this.details=details;
	}
	
}
