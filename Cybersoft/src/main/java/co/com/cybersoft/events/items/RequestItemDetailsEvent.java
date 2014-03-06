package co.com.cybersoft.events.items;

/**
 * 
 * @author daniel
 *
 */
public class RequestItemDetailsEvent {

	private String code;
	
	public RequestItemDetailsEvent(String code){
		this.code=code;
	}

	public String getCode() {
		return code;
	}

}
