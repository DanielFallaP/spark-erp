package co.com.cybersoft.maintenance.tables.events.reference;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import java.util.List;

/**
 * Event class for Reference
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ReferencePageEvent {
	private Page<Reference> referencePage;
	
	private List<Reference> allList;
	
	private Long totalCount;
	
	private List<ReferenceDetails> referenceList;



	
	public ReferencePageEvent(){
    }
	public ReferencePageEvent(List<ReferenceDetails>  referenceList){
			this.referenceList= referenceList;
	}



	
	public List<ReferenceDetails> getReferenceList() {
	return referenceList;
	}



	
	public List<Reference> getAllList() {
		return allList;
	}

	public void setAllList(List<Reference> allList) {
		this.allList = allList;
	}
	
	public ReferencePageEvent(Page<Reference>  referencePage){
		this.referencePage= referencePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ReferencePageEvent(Page<Reference>  referencePage, Long totalCount){
		this.referencePage= referencePage;
		this.totalCount=totalCount;
	}

	public Page<Reference> getReferencePage() {
		return referencePage;
	}
	
	
}