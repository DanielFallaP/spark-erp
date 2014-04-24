package co.com.cybersoft.persistence.repository.jointVenture;

import java.util.List;

import co.com.cybersoft.persistence.domain.JointVenture;

public interface JointVentureCustomRepo {

	List<JointVenture> findByStartingCodeNumber(String codePrefix);
	
	List<JointVenture> findByContainingDescription(String descriptionSubstring);
}