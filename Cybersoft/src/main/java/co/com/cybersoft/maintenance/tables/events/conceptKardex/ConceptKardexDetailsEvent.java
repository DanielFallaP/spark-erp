package co.com.cybersoft.maintenance.tables.events.conceptKardex;

import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;

/**
 * Event class for ConceptKardex
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ConceptKardexDetailsEvent {
	
	private ConceptKardexDetails conceptKardexDetails;
	
	public ConceptKardexDetailsEvent(ConceptKardexDetails conceptKardexDetails){
		this.conceptKardexDetails=conceptKardexDetails;
	}

	public ConceptKardexDetails getConceptKardexDetails() {
		return conceptKardexDetails;
	}

}