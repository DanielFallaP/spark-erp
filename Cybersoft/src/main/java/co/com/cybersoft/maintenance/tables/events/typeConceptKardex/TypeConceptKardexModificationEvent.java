package co.com.cybersoft.maintenance.tables.events.typeConceptKardex;

import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;

/**
 * Event class for TypeConceptKardex
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeConceptKardexModificationEvent {

	private TypeConceptKardexDetails typeConceptKardexDetails;
	
	public TypeConceptKardexModificationEvent(TypeConceptKardexDetails typeConceptKardexDetails){
		this.typeConceptKardexDetails=typeConceptKardexDetails;
	}

	public TypeConceptKardexDetails getTypeConceptKardexDetails() {
		return typeConceptKardexDetails;
	}
	
}