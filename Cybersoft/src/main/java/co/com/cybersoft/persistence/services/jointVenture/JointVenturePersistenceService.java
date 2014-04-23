package co.com.cybersoft.persistence.services.jointVenture;

import co.com.cybersoft.events.jointVenture.CreateJointVentureEvent;
import co.com.cybersoft.events.jointVenture.JointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.JointVenturePageEvent;
import co.com.cybersoft.events.jointVenture.JointVentureModificationEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVentureDetailsEvent;
import co.com.cybersoft.events.jointVenture.RequestJointVenturePageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface JointVenturePersistenceService {

	JointVentureDetailsEvent createJointVenture(CreateJointVentureEvent event) throws Exception;

	JointVenturePageEvent requestJointVenturePage(RequestJointVenturePageEvent event) throws Exception;

	JointVentureDetailsEvent requestJointVentureDetails(RequestJointVentureDetailsEvent event) throws Exception;
	
	JointVentureDetailsEvent modifyJointVenture(JointVentureModificationEvent event) throws Exception;
	
	JointVenturePageEvent requestAll() throws Exception;
	
}