package co.com.cybersoft.events.active;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Active;
import co.com.cybersoft.core.domain.ActiveDetails;
import co.com.cybersoft.persistence.domain.Active;
import java.util.List;

/**
 * Event class for Active
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ActivePageEvent {
	private Page<Active> activePage;
	
	private List<ActiveDetails> activeList;



	
	public ActivePageEvent(List<ActiveDetails>  activeList){
			this.activeList= activeList;
	}



	
	public List<ActiveDetails> getActiveList() {
	return activeList;
	}



	
	public ActivePageEvent(Page<Active>  activePage){
		this.activePage= activePage;
	}

	public Page<Active> getActivePage() {
		return activePage;
	}
	
}