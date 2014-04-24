package co.com.cybersoft.persistence.repository.branch;

import java.util.List;

import co.com.cybersoft.persistence.domain.Branch;

public interface BranchCustomRepo {

	List<Branch> findByStartingCodeNumber(String codePrefix);
	
	List<Branch> findByContainingDescription(String descriptionSubstring);
}