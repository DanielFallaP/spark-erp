package co.com.cybersoft.events.afe;

public class CreateAFEEvent {
		
	private AFEDetails afeDetails;
	
	public CreateAFEEvent(AFEDetails afeDetails){
		this.afeDetails=afeDetails;
	}

	public AFEDetails getAfeDetails() {
		return afeDetails;
	}
	
	
}
