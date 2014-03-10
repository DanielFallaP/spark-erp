package co.com.cybersoft.events.afe;

public class AFECreatedEvent {
	
	private final AFEDetails details;
	
	public AFECreatedEvent(final AFEDetails details){
		this.details=details;
	}
	
}
