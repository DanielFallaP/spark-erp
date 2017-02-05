package co.com.cybersoft.maintenance.tables.events.referenceOperation;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import java.util.List;

/**
 * Event class for ReferenceOperation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class ReferenceOperationPageEvent {
	private Page<ReferenceOperation> referenceOperationPage;
	
	private List<ReferenceOperation> allList;
	
	private Long totalCount;
	
	private List<ReferenceOperationDetails> referenceOperationList;



	
	public ReferenceOperationPageEvent(){
    }
	public ReferenceOperationPageEvent(List<ReferenceOperationDetails>  referenceOperationList){
			this.referenceOperationList= referenceOperationList;
	}



	
	public List<ReferenceOperationDetails> getReferenceOperationList() {
	return referenceOperationList;
	}



	
	public List<ReferenceOperation> getAllList() {
		return allList;
	}

	public void setAllList(List<ReferenceOperation> allList) {
		this.allList = allList;
	}
	
	public ReferenceOperationPageEvent(Page<ReferenceOperation>  referenceOperationPage){
		this.referenceOperationPage= referenceOperationPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public ReferenceOperationPageEvent(Page<ReferenceOperation>  referenceOperationPage, Long totalCount){
		this.referenceOperationPage= referenceOperationPage;
		this.totalCount=totalCount;
	}

	public Page<ReferenceOperation> getReferenceOperationPage() {
		return referenceOperationPage;
	}
	
	
}