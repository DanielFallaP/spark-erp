package co.com.cybersoft.events.jointVenture;

import co.com.cybersoft.core.domain.JointVentureDetails;

/**
 * Event class for JointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JointVentureModificationEvent {

	private JointVentureDetails jointVentureDetails;
	
	public JointVentureModificationEvent(JointVentureDetails jointVentureDetails){
		this.jointVentureDetails=jointVentureDetails;
	}

	public JointVentureDetails getJointVentureDetails() {
		return jointVentureDetails;
	}
	
}