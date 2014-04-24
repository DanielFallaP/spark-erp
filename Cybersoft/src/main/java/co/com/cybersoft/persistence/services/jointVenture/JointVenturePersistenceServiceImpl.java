package co.com.cybersoft.persistence.services.jointVenture;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.JointVentureDetails;
import co.com.cybersoft.events.jointVenture.CreateJointVentureEvent;
import co.com.cybersoft.events.jointVenture.JointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.JointVenturePageEvent;
import co.com.cybersoft.events.jointVenture.JointVentureModificationEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVenturePageEvent;
import co.com.cybersoft.persistence.domain.JointVenture;
import co.com.cybersoft.persistence.repository.jointVenture.JointVentureRepository;
import co.com.cybersoft.persistence.repository.jointVenture.JointVentureCustomRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JointVenturePersistenceServiceImpl implements JointVenturePersistenceService{

	private final JointVentureRepository jointVentureRepository;
	
	private final JointVentureCustomRepo jointVentureCustomRepo;
	
	public JointVenturePersistenceServiceImpl(final JointVentureRepository jointVentureRepository, final JointVentureCustomRepo jointVentureCustomRepo) {
		this.jointVentureRepository=jointVentureRepository;
		this.jointVentureCustomRepo=jointVentureCustomRepo;
	}
	
	@Override
	public JointVentureDetailsEvent createJointVenture(CreateJointVentureEvent event) throws Exception{
		JointVenture jointVenture = JointVenture.fromJointVentureDetails(event.getJointVentureDetails());
		jointVenture = jointVentureRepository.save(jointVenture);
		return new JointVentureDetailsEvent(jointVenture.toJointVentureDetails());
	}

	@Override
	public JointVenturePageEvent requestJointVenturePage(RequestJointVenturePageEvent event) throws Exception {
		Page<JointVenture> jointVentures = jointVentureRepository.findAll(event.getPageable());
		return new JointVenturePageEvent(jointVentures);
	}

	@Override
	public JointVentureDetailsEvent requestJointVentureDetails(RequestJointVentureDetailsEvent event) throws Exception {
		JointVentureDetails jointVentureDetails=null;
		if (event.getId()!=null){
			JointVenture jointVenture = jointVentureRepository.findByCode(event.getId());
			if (jointVenture!=null)
				jointVentureDetails = jointVenture.toJointVentureDetails();
		}
		return new JointVentureDetailsEvent(jointVentureDetails);
		
	}

	@Override
	public JointVentureDetailsEvent modifyJointVenture(JointVentureModificationEvent event) throws Exception {
		JointVenture jointVenture = JointVenture.fromJointVentureDetails(event.getJointVentureDetails());
		jointVenture = jointVentureRepository.save(jointVenture);
		return new JointVentureDetailsEvent(jointVenture.toJointVentureDetails());
	}
	
	@Override
	public JointVenturePageEvent requestAll() throws Exception {
		List<JointVenture> all = jointVentureRepository.findAll();
		List<JointVentureDetails> list = new ArrayList<JointVentureDetails>();
		for (JointVenture jointVenture : all) {
			list.add(jointVenture.toJointVentureDetails());
		}
		return new JointVenturePageEvent(list);
	}
	
	@Override
	public JointVenturePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		List<JointVenture> codes = jointVentureCustomRepo.findByStartingCodeNumber(codePrefix);
		ArrayList<JointVentureDetails> list = new ArrayList<JointVentureDetails>();
		for (JointVenture jointVenture : codes) {
			list.add(jointVenture.toJointVentureDetails());
		}
		return new JointVenturePageEvent(list);
	}

	@Override
	public JointVenturePageEvent requestByContainingDescription(String description) throws Exception {
		ArrayList<JointVentureDetails> list = new ArrayList<JointVentureDetails>();
		List<JointVenture> descriptions = jointVentureCustomRepo.findByContainingDescription(description);
		for (JointVenture jointVenture : descriptions) {
			list.add(jointVenture.toJointVentureDetails());
		}
		return new JointVenturePageEvent(list);
	}

}