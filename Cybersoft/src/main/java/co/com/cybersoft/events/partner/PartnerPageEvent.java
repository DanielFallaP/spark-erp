package co.com.cybersoft.events.partner;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Partner;
import co.com.cybersoft.core.domain.PartnerDetails;
import co.com.cybersoft.persistence.domain.Partner;
import java.util.List;

/**
 * Event class for Partner
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class PartnerPageEvent {
	private Page<Partner> partnerPage;
	
	private List<PartnerDetails> partnerList;



	
	public PartnerPageEvent(List<PartnerDetails>  partnerList){
			this.partnerList= partnerList;
	}



	
	public List<PartnerDetails> getPartnerList() {
	return partnerList;
	}



	
	public PartnerPageEvent(Page<Partner>  partnerPage){
		this.partnerPage= partnerPage;
	}

	public Page<Partner> getPartnerPage() {
		return partnerPage;
	}
	
}