package co.com.cybersoft.events.jointVenture;

import co.com.cybersoft.core.domain.JointVentureDetails;

/**
 * Event class for JointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JointVentureDetailsEvent {
	
	private JointVentureDetails jointVentureDetails;
	
	public JointVentureDetailsEvent(JointVentureDetails jointVentureDetails){
		this.jointVentureDetails=jointVentureDetails;
	}

	public JointVentureDetails getJointVentureDetails() {
		return jointVentureDetails;
	}

}