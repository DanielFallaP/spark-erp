package co.com.cybersoft.docsTest.events.requisition;

import co.com.cybersoft.docsTest.web.domain.requisition.RequisitionInfo;


/**
 * Event class for Requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionEvent {
	
	private RequisitionInfo requisitionInfo;
	
	public RequisitionEvent(RequisitionInfo requisitionInfo){
		this.requisitionInfo=requisitionInfo;
	}

	public RequisitionInfo getRequisition() {
		return requisitionInfo;
	}

}