package co.com.cybersoft.persistence.repository.bill;

import java.util.List;

import co.com.cybersoft.persistence.domain.Bill;

public interface BillCustomRepo {

	List<Bill> findByStartingCodeNumber(String codePrefix);
	
	List<Bill> findByContainingDescription(String descriptionSubstring);
}