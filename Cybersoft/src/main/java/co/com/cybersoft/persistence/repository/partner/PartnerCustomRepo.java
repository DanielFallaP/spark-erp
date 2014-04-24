package co.com.cybersoft.persistence.repository.partner;

import java.util.List;

import co.com.cybersoft.persistence.domain.Partner;

public interface PartnerCustomRepo {

	List<Partner> findByStartingCodeNumber(String codePrefix);
	
	List<Partner> findByContainingDescription(String descriptionSubstring);
}