package co.com.cybersoft.maintenance.tables.events.responsible;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import java.util.List;

/**
 * Event class for Responsible
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ResponsiblePageEvent {
	private Page<Responsible> responsiblePage;
	
	private List<Responsible> allList;
	
	private Long totalCount;
	
	private List<ResponsibleDetails> responsibleList;



	
	public ResponsiblePageEvent(){
    }
	public ResponsiblePageEvent(List<ResponsibleDetails>  responsibleList){
			this.responsibleList= responsibleList;
	}



	
	public List<ResponsibleDetails> getResponsibleList() {
	return responsibleList;
	}



	
	public List<Responsible> getAllList() {
		return allList;
	}

	public void setAllList(List<Responsible> allList) {
		this.allList = allList;
	}
	
	public ResponsiblePageEvent(Page<Responsible>  responsiblePage){
		this.responsiblePage= responsiblePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ResponsiblePageEvent(Page<Responsible>  responsiblePage, Long totalCount){
		this.responsiblePage= responsiblePage;
		this.totalCount=totalCount;
	}

	public Page<Responsible> getResponsiblePage() {
		return responsiblePage;
	}
	
	
}