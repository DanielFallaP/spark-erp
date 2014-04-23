package co.com.cybersoft.persistence.repository;

import java.util.List;

import co.com.cybersoft.persistence.domain.WareHouse;

public interface WareHouseCustomRepo {

	List<WareHouse> findByStartingCodeNumber(String codePrefix);
	
	List<WareHouse> findByContainingDescription(String descriptionSubstring);
}
