package co.com.cybersoft.persistence.repository.active;

import java.util.List;

import co.com.cybersoft.persistence.domain.Active;

public interface ActiveCustomRepo {

	List<Active> findByStartingCodeNumber(String codePrefix);
	
	List<Active> findByContainingDescription(String descriptionSubstring);
}