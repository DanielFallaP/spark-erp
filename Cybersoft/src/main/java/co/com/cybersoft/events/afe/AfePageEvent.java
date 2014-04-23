package co.com.cybersoft.events.afe;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Afe;
import co.com.cybersoft.core.domain.AfeDetails;
import co.com.cybersoft.persistence.domain.Afe;
import java.util.List;

/**
 * Event class for Afe
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfePageEvent {
	private Page<Afe> afePage;
	
	private List<AfeDetails> afeList;



	
	public AfePageEvent(List<AfeDetails>  afeList){
			this.afeList= afeList;
	}



	
	public List<AfeDetails> getAfeList() {
	return afeList;
	}



	
	public AfePageEvent(Page<Afe>  afePage){
		this.afePage= afePage;
	}

	public Page<Afe> getAfePage() {
		return afePage;
	}
	
}