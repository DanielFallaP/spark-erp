package co.com.cybersoft.events.branch;

import co.com.cybersoft.core.domain.BranchDetails;

/**
 * Event class for Branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BranchDetailsEvent {
	
	private BranchDetails branchDetails;
	
	public BranchDetailsEvent(BranchDetails branchDetails){
		this.branchDetails=branchDetails;
	}

	public BranchDetails getBranchDetails() {
		return branchDetails;
	}

}