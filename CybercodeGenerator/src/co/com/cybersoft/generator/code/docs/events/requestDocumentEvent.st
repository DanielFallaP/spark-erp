package co.com.cybersoft.$module$.docs.events.$docName$;

/**
 * Event class for $upperDocName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class Request$upperDocName$Event {

	private String id;
	
	private Long numericId;
	
	private String field;
	
	private String value;
	
	public Request$upperDocName$Event(String id){
		this.id=id;
	}
	
	public Request$upperDocName$Event(Long numericId){
		this.numericId=numericId;
	}

	public String getId() {
		return id;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getNumericId() {
		return numericId;
	}	
	
	
	
}