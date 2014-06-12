package co.com.cybersoft.docs.events.requisition;

import org.springframework.data.domain.Page;

import co.com.cybersoft.docs.persistence.domain.Requisition;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;

import java.util.List;

/**
 * Event class for Requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionPageEvent {
	private Page<Requisition> requisitionPage;
	
	private List<RequisitionInfo> requisitionList;



	
	public RequisitionPageEvent(List<RequisitionInfo>  requisitionList){
			this.requisitionList= requisitionList;
	}



	
	public List<RequisitionInfo> getRequisitionList() {
	return requisitionList;
	}



	
	public RequisitionPageEvent(Page<Requisition>  requisitionPage){
		this.requisitionPage= requisitionPage;
	}

	public Page<Requisition> getRequisitionPage() {
		return requisitionPage;
	}
	
}