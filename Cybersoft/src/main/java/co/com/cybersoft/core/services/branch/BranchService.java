package co.com.cybersoft.core.services.branch;

import co.com.cybersoft.events.branch.CreateBranchEvent;
import co.com.cybersoft.events.branch.BranchDetailsEvent;
import co.com.cybersoft.events.branch.BranchPageEvent;
import co.com.cybersoft.events.branch.BranchModificationEvent;
import co.com.cybersoft.events.branch.RequestBranchDetailsEvent;
import co.com.cybersoft.events.branch.RequestBranchPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface BranchService {
	BranchDetailsEvent createBranch(CreateBranchEvent event ) throws Exception;
	
	BranchPageEvent requestBranchPage(RequestBranchPageEvent event) throws Exception;

	BranchDetailsEvent requestBranchDetails(RequestBranchDetailsEvent event ) throws Exception;

	BranchDetailsEvent modifyBranch(BranchModificationEvent event) throws Exception;
	
	BranchPageEvent requestAll() throws Exception;
	
	BranchPageEvent requestByCodePrefix(String code) throws Exception;
	
	BranchPageEvent requestByContainingDescription(String description) throws Exception;
	
}