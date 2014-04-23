package co.com.cybersoft.events.afeType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.AfeType;
import co.com.cybersoft.core.domain.AfeTypeDetails;
import co.com.cybersoft.persistence.domain.AfeType;
import java.util.List;

/**
 * Event class for AfeType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class AfeTypePageEvent {
	private Page<AfeType> afeTypePage;
	
	private List<AfeTypeDetails> afeTypeList;



	
	public AfeTypePageEvent(List<AfeTypeDetails>  afeTypeList){
			this.afeTypeList= afeTypeList;
	}



	
	public List<AfeTypeDetails> getAfeTypeList() {
	return afeTypeList;
	}



	
	public AfeTypePageEvent(Page<AfeType>  afeTypePage){
		this.afeTypePage= afeTypePage;
	}

	public Page<AfeType> getAfeTypePage() {
		return afeTypePage;
	}
	
}