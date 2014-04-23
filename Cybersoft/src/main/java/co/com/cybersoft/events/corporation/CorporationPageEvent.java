package co.com.cybersoft.events.corporation;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Corporation;
import co.com.cybersoft.core.domain.CorporationDetails;
import co.com.cybersoft.persistence.domain.Corporation;
import java.util.List;

/**
 * Event class for Corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CorporationPageEvent {
	private Page<Corporation> corporationPage;
	
	private List<CorporationDetails> corporationList;



	
	public CorporationPageEvent(List<CorporationDetails>  corporationList){
			this.corporationList= corporationList;
	}



	
	public List<CorporationDetails> getCorporationList() {
	return corporationList;
	}



	
	public CorporationPageEvent(Page<Corporation>  corporationPage){
		this.corporationPage= corporationPage;
	}

	public Page<Corporation> getCorporationPage() {
		return corporationPage;
	}
	
}