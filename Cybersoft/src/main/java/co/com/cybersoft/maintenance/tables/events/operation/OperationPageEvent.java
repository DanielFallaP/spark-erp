package co.com.cybersoft.maintenance.tables.events.operation;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import java.util.List;

/**
 * Event class for Operation
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class OperationPageEvent {
	private Page<Operation> operationPage;
	
	private List<Operation> allList;
	
	private Long totalCount;
	
	private List<OperationDetails> operationList;



	
	public OperationPageEvent(){
    }
	public OperationPageEvent(List<OperationDetails>  operationList){
			this.operationList= operationList;
	}



	
	public List<OperationDetails> getOperationList() {
	return operationList;
	}



	
	public List<Operation> getAllList() {
		return allList;
	}

	public void setAllList(List<Operation> allList) {
		this.allList = allList;
	}
	
	public OperationPageEvent(Page<Operation>  operationPage){
		this.operationPage= operationPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public OperationPageEvent(Page<Operation>  operationPage, Long totalCount){
		this.operationPage= operationPage;
		this.totalCount=totalCount;
	}

	public Page<Operation> getOperationPage() {
		return operationPage;
	}
	
	
}