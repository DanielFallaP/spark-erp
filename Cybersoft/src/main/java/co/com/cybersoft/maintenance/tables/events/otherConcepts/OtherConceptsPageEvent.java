package co.com.cybersoft.maintenance.tables.events.otherConcepts;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import java.util.List;

/**
 * Event class for OtherConcepts
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class OtherConceptsPageEvent {
	private Page<OtherConcepts> otherConceptsPage;
	
	private List<OtherConcepts> allList;
	
	private Long totalCount;
	
	private List<OtherConceptsDetails> otherConceptsList;



	
	public OtherConceptsPageEvent(){
    }
	public OtherConceptsPageEvent(List<OtherConceptsDetails>  otherConceptsList){
			this.otherConceptsList= otherConceptsList;
	}



	
	public List<OtherConceptsDetails> getOtherConceptsList() {
	return otherConceptsList;
	}



	
	public List<OtherConcepts> getAllList() {
		return allList;
	}

	public void setAllList(List<OtherConcepts> allList) {
		this.allList = allList;
	}
	
	public OtherConceptsPageEvent(Page<OtherConcepts>  otherConceptsPage){
		this.otherConceptsPage= otherConceptsPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public OtherConceptsPageEvent(Page<OtherConcepts>  otherConceptsPage, Long totalCount){
		this.otherConceptsPage= otherConceptsPage;
		this.totalCount=totalCount;
	}

	public Page<OtherConcepts> getOtherConceptsPage() {
		return otherConceptsPage;
	}
	
	
}