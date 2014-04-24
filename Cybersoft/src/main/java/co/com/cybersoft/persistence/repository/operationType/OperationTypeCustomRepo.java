package co.com.cybersoft.persistence.repository.operationType;

import java.util.List;

import co.com.cybersoft.persistence.domain.OperationType;

public interface OperationTypeCustomRepo {

	List<OperationType> findByStartingCodeNumber(String codePrefix);
	
	List<OperationType> findByContainingDescription(String descriptionSubstring);
}