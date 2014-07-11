package co.com.cybersoft.docsTest.events.requisition;

import co.com.cybersoft.docsTest.web.domain.requisition.RequisitionInfo;


/**
 * Event class for Requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionModificationEvent {

	private RequisitionInfo requisitionInfo;
	
	public RequisitionModificationEvent(RequisitionInfo requisition){
		this.requisitionInfo=requisition;
	}

	public RequisitionInfo getRequisition() {
		return requisitionInfo;
	}
	
}