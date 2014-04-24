package co.com.cybersoft.core.services.jointVenture;

import co.com.cybersoft.events.jointVenture.CreateJointVentureEvent;
import co.com.cybersoft.events.jointVenture.JointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.JointVenturePageEvent;
import co.com.cybersoft.events.jointVenture.JointVentureModificationEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVenturePageEvent;
import co.com.cybersoft.persistence.services.jointVenture.JointVenturePersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JointVentureServiceImpl implements JointVentureService{

	private final JointVenturePersistenceService jointVenturePersistenceService;
	
	public JointVentureServiceImpl(final JointVenturePersistenceService jointVenturePersistenceService){
		this.jointVenturePersistenceService=jointVenturePersistenceService;
	}
	
	/**
	 */
	@Override
	public JointVentureDetailsEvent createJointVenture(CreateJointVentureEvent event ) throws Exception{
		return jointVenturePersistenceService.createJointVenture(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public JointVenturePageEvent requestJointVenturePage(RequestJointVenturePageEvent event) throws Exception{
		return jointVenturePersistenceService.requestJointVenturePage(event);
	}

	@Override
	public JointVentureDetailsEvent requestJointVentureDetails(RequestJointVentureDetailsEvent event ) throws Exception{
		return jointVenturePersistenceService.requestJointVentureDetails(event);
	}

	@Override
	public JointVentureDetailsEvent modifyJointVenture(JointVentureModificationEvent event) throws Exception {
		return jointVenturePersistenceService.modifyJointVenture(event);
	}
	
	@Override
	public JointVenturePageEvent requestAll() throws Exception {
		return jointVenturePersistenceService.requestAll();
	}
	
	@Override
	public JointVenturePageEvent requestByCodePrefix(String codePrefix) throws Exception {
		return jointVenturePersistenceService.requestByCodePrefix(codePrefix);
	}

	@Override
	public JointVenturePageEvent requestByContainingDescription(String description) throws Exception {
		return jointVenturePersistenceService.requestByContainingDescription(description);
	}
	
}