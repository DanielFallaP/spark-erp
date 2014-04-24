package co.com.cybersoft.persistence.repository.contract;

import java.util.List;

import co.com.cybersoft.persistence.domain.Contract;

public interface ContractCustomRepo {

	List<Contract> findByStartingCodeNumber(String codePrefix);
	
	List<Contract> findByContainingDescription(String descriptionSubstring);
}