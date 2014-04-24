package co.com.cybersoft.persistence.repository.afeType;

import java.util.List;

import co.com.cybersoft.persistence.domain.AfeType;

public interface AfeTypeCustomRepo {

	List<AfeType> findByStartingCodeNumber(String codePrefix);
	
	List<AfeType> findByContainingDescription(String descriptionSubstring);
}