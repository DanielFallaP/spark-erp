package co.com.cybersoft.persistence.repository.afe;

import java.util.List;

import co.com.cybersoft.persistence.domain.Afe;

public interface AfeCustomRepo {

	List<Afe> findByStartingCodeNumber(String codePrefix);
	
	List<Afe> findByContainingDescription(String descriptionSubstring);
}