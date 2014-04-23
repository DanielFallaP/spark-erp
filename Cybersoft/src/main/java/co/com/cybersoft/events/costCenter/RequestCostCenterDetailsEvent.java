package co.com.cybersoft.events.costCenter;

/**
 * Event class for CostCenter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequestCostCenterDetailsEvent {

	private String id;
	
	public RequestCostCenterDetailsEvent(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
}