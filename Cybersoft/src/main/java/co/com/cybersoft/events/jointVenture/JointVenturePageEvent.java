package co.com.cybersoft.events.jointVenture;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.JointVenture;
import co.com.cybersoft.core.domain.JointVentureDetails;
import co.com.cybersoft.persistence.domain.JointVenture;
import java.util.List;

/**
 * Event class for JointVenture
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class JointVenturePageEvent {
	private Page<JointVenture> jointVenturePage;
	
	private List<JointVentureDetails> jointVentureList;



	
	public JointVenturePageEvent(List<JointVentureDetails>  jointVentureList){
			this.jointVentureList= jointVentureList;
	}



	
	public List<JointVentureDetails> getJointVentureList() {
	return jointVentureList;
	}



	
	public JointVenturePageEvent(Page<JointVenture>  jointVenturePage){
		this.jointVenturePage= jointVenturePage;
	}

	public Page<JointVenture> getJointVenturePage() {
		return jointVenturePage;
	}
	
}