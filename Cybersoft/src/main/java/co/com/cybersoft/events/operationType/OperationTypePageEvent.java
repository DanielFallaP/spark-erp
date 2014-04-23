package co.com.cybersoft.events.operationType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.OperationType;
import co.com.cybersoft.core.domain.OperationTypeDetails;
import co.com.cybersoft.persistence.domain.OperationType;
import java.util.List;

/**
 * Event class for OperationType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OperationTypePageEvent {
	private Page<OperationType> operationTypePage;
	
	private List<OperationTypeDetails> operationTypeList;



	
	public OperationTypePageEvent(List<OperationTypeDetails>  operationTypeList){
			this.operationTypeList= operationTypeList;
	}



	
	public List<OperationTypeDetails> getOperationTypeList() {
	return operationTypeList;
	}



	
	public OperationTypePageEvent(Page<OperationType>  operationTypePage){
		this.operationTypePage= operationTypePage;
	}

	public Page<OperationType> getOperationTypePage() {
		return operationTypePage;
	}
	
}