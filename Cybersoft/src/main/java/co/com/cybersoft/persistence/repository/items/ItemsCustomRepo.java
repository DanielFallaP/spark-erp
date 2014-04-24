package co.com.cybersoft.persistence.repository.items;

import java.util.List;

import co.com.cybersoft.persistence.domain.Items;

public interface ItemsCustomRepo {

	List<Items> findByStartingCodeNumber(String codePrefix);
	
	List<Items> findByContainingDescription(String descriptionSubstring);
}