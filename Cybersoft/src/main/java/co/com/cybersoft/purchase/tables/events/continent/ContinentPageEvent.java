package co.com.cybersoft.purchase.tables.events.continent;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import java.util.List;

/**
 * Event class for Continent
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ContinentPageEvent {
	private Page<Continent> continentPage;
	
	private List<ContinentDetails> continentList;



	
	public ContinentPageEvent(List<ContinentDetails>  continentList){
			this.continentList= continentList;
	}



	
	public List<ContinentDetails> getContinentList() {
	return continentList;
	}



	
	public ContinentPageEvent(Page<Continent>  continentPage){
		this.continentPage= continentPage;
	}

	public Page<Continent> getContinentPage() {
		return continentPage;
	}
	
}