package co.com.cybersoft.docs.events.requisition;

import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;



/**
 * Event class for RequisitionInfo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class SaveRequisitionEvent {
		
	private RequisitionInfo requisitionInfo;
	
	public SaveRequisitionEvent(RequisitionInfo requisitionInfo){
		this.requisitionInfo=requisitionInfo;
	}

	public RequisitionInfo getRequisitionInfo() {
		return requisitionInfo;
	}
	
	
}