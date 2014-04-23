package co.com.cybersoft.events.calculusType;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.CalculusType;
import co.com.cybersoft.core.domain.CalculusTypeDetails;
import co.com.cybersoft.persistence.domain.CalculusType;
import java.util.List;

/**
 * Event class for CalculusType
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CalculusTypePageEvent {
	private Page<CalculusType> calculusTypePage;
	
	private List<CalculusTypeDetails> calculusTypeList;



	
	public CalculusTypePageEvent(List<CalculusTypeDetails>  calculusTypeList){
			this.calculusTypeList= calculusTypeList;
	}



	
	public List<CalculusTypeDetails> getCalculusTypeList() {
	return calculusTypeList;
	}



	
	public CalculusTypePageEvent(Page<CalculusType>  calculusTypePage){
		this.calculusTypePage= calculusTypePage;
	}

	public Page<CalculusType> getCalculusTypePage() {
		return calculusTypePage;
	}
	
}