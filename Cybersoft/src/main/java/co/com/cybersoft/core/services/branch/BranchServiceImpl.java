package co.com.cybersoft.core.services.branch;

import co.com.cybersoft.events.branch.CreateBranchEvent;
import co.com.cybersoft.events.branch.BranchDetailsEvent;
import co.com.cybersoft.events.branch.BranchPageEvent;
import co.com.cybersoft.events.branch.BranchModificationEvent;
import co.com.cybersoft.events.branch.RequestBranchDetailsEvent;
import co.com.cybersoft.events.branch.RequestBranchPageEvent;
import co.com.cybersoft.persistence.services.branch.BranchPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BranchServiceImpl implements BranchService{

	private final BranchPersistenceService branchPersistenceService;
	
	public BranchServiceImpl(final BranchPersistenceService branchPersistenceService){
		this.branchPersistenceService=branchPersistenceService;
	}
	
	/**
	 */
	@Override
	public BranchDetailsEvent createBranch(CreateBranchEvent event ) throws Exception{
		return branchPersistenceService.createBranch(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public BranchPageEvent requestBranchPage(RequestBranchPageEvent event) throws Exception{
		return branchPersistenceService.requestBranchPage(event);
	}

	@Override
	public BranchDetailsEvent requestBranchDetails(RequestBranchDetailsEvent event ) throws Exception{
		return branchPersistenceService.requestBranchDetails(event);
	}

	@Override
	public BranchDetailsEvent modifyBranch(BranchModificationEvent event) throws Exception {
		return branchPersistenceService.modifyBranch(event);
	}
	
	@Override
	public BranchPageEvent requestAll() throws Exception {
		return branchPersistenceService.requestAll();
	}
	
	@Override
	public BranchPageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return branchPersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public BranchPageEvent requestByContainingDescription(String description) throws Exception {
		return branchPersistenceService.requestByContainingDescription(description);
	}
	
}