package co.com.cybersoft.persistence.repository.corporation;

import java.util.List;

import co.com.cybersoft.persistence.domain.Corporation;

public interface CorporationCustomRepo {

	List<Corporation> findByStartingCodeNumber(String codePrefix);
	
	List<Corporation> findByContainingDescription(String descriptionSubstring);
}