package co.com.cybersoft.events.branch;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Branch;
import co.com.cybersoft.core.domain.BranchDetails;
import co.com.cybersoft.persistence.domain.Branch;
import java.util.List;

/**
 * Event class for Branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BranchPageEvent {
	private Page<Branch> branchPage;
	
	private List<BranchDetails> branchList;



	
	public BranchPageEvent(List<BranchDetails>  branchList){
			this.branchList= branchList;
	}



	
	public List<BranchDetails> getBranchList() {
	return branchList;
	}



	
	public BranchPageEvent(Page<Branch>  branchPage){
		this.branchPage= branchPage;
	}

	public Page<Branch> getBranchPage() {
		return branchPage;
	}
	
}