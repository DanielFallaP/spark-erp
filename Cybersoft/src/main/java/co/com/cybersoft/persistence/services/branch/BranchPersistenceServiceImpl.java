package co.com.cybersoft.persistence.services.branch;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.BranchDetails;
import co.com.cybersoft.events.branch.CreateBranchEvent;
import co.com.cybersoft.events.branch.BranchDetailsEvent;
import co.com.cybersoft.events.branch.BranchPageEvent;
import co.com.cybersoft.events.branch.BranchModificationEvent;
import co.com.cybersoft.events.branch.RequestBranchDetailsEvent;
import co.com.cybersoft.events.branch.RequestBranchPageEvent;
import co.com.cybersoft.persistence.domain.Branch;
import co.com.cybersoft.persistence.repository.BranchRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class BranchPersistenceServiceImpl implements BranchPersistenceService{

	private final BranchRepository branchRepository;
	
	public BranchPersistenceServiceImpl(final BranchRepository branchRepository) {
		this.branchRepository=branchRepository;
	}
	
	@Override
	public BranchDetailsEvent createBranch(CreateBranchEvent event) throws Exception{
		Branch branch = Branch.fromBranchDetails(event.getBranchDetails());
		branch = branchRepository.save(branch);
		return new BranchDetailsEvent(branch.toBranchDetails());
	}

	@Override
	public BranchPageEvent requestBranchPage(RequestBranchPageEvent event) throws Exception {
		Page<Branch> branchs = branchRepository.findAll(event.getPageable());
		return new BranchPageEvent(branchs);
	}

	@Override
	public BranchDetailsEvent requestBranchDetails(RequestBranchDetailsEvent event) throws Exception {
		Branch branch = branchRepository.findByCode(event.getId());
		BranchDetails branchDetails = branch.toBranchDetails();
		return new BranchDetailsEvent(branchDetails);
	}

	@Override
	public BranchDetailsEvent modifyBranch(BranchModificationEvent event) throws Exception {
		Branch branch = Branch.fromBranchDetails(event.getBranchDetails());
		branch = branchRepository.save(branch);
		return new BranchDetailsEvent(branch.toBranchDetails());
	}
	
	@Override
	public BranchPageEvent requestAll() throws Exception {
		List<Branch> all = branchRepository.findAll();
		List<BranchDetails> list = new ArrayList<BranchDetails>();
		for (Branch branch : all) {
			list.add(branch.toBranchDetails());
		}
		return new BranchPageEvent(list);
	}

}