package co.com.cybersoft.maintenance.tables.events.typeCauseClose;

import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;

/**
 * Event class for TypeCauseClose
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeCauseCloseModificationEvent {

	private TypeCauseCloseDetails typeCauseCloseDetails;
	
	public TypeCauseCloseModificationEvent(TypeCauseCloseDetails typeCauseCloseDetails){
		this.typeCauseCloseDetails=typeCauseCloseDetails;
	}

	public TypeCauseCloseDetails getTypeCauseCloseDetails() {
		return typeCauseCloseDetails;
	}
	
}