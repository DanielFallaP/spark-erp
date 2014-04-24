package co.com.cybersoft.persistence.repository.costCenter;

import java.util.List;

import co.com.cybersoft.persistence.domain.CostCenter;

public interface CostCenterCustomRepo {

	List<CostCenter> findByStartingCodeNumber(String codePrefix);
	
	List<CostCenter> findByContainingDescription(String descriptionSubstring);
}