package co.com.cybersoft.persistence.repository.calculusType;

import java.util.List;

import co.com.cybersoft.persistence.domain.CalculusType;

public interface CalculusTypeCustomRepo {

	List<CalculusType> findByStartingCodeNumber(String codePrefix);
	
	List<CalculusType> findByContainingDescription(String descriptionSubstring);
}