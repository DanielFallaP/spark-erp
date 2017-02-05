package co.com.cybersoft.maintenance.tables.events.responsibleCenter;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import java.util.List;

/**
 * Event class for ResponsibleCenter
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ResponsibleCenterPageEvent {
	private Page<ResponsibleCenter> responsibleCenterPage;
	
	private List<ResponsibleCenter> allList;
	
	private Long totalCount;
	
	private List<ResponsibleCenterDetails> responsibleCenterList;



	
	public ResponsibleCenterPageEvent(){
    }
	public ResponsibleCenterPageEvent(List<ResponsibleCenterDetails>  responsibleCenterList){
			this.responsibleCenterList= responsibleCenterList;
	}



	
	public List<ResponsibleCenterDetails> getResponsibleCenterList() {
	return responsibleCenterList;
	}



	
	public List<ResponsibleCenter> getAllList() {
		return allList;
	}

	public void setAllList(List<ResponsibleCenter> allList) {
		this.allList = allList;
	}
	
	public ResponsibleCenterPageEvent(Page<ResponsibleCenter>  responsibleCenterPage){
		this.responsibleCenterPage= responsibleCenterPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ResponsibleCenterPageEvent(Page<ResponsibleCenter>  responsibleCenterPage, Long totalCount){
		this.responsibleCenterPage= responsibleCenterPage;
		this.totalCount=totalCount;
	}

	public Page<ResponsibleCenter> getResponsibleCenterPage() {
		return responsibleCenterPage;
	}
	
	
}