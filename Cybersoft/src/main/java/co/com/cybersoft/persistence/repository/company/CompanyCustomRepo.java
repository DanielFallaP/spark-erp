package co.com.cybersoft.persistence.repository.company;

import java.util.List;

import co.com.cybersoft.persistence.domain.Company;

public interface CompanyCustomRepo {

	List<Company> findByStartingCodeNumber(String codePrefix);
	
	List<Company> findByContainingDescription(String descriptionSubstring);
}