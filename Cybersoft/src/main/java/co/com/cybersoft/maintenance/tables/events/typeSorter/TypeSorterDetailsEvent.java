package co.com.cybersoft.maintenance.tables.events.typeSorter;

import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;

/**
 * Event class for TypeSorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeSorterDetailsEvent {
	
	private TypeSorterDetails typeSorterDetails;
	
	public TypeSorterDetailsEvent(TypeSorterDetails typeSorterDetails){
		this.typeSorterDetails=typeSorterDetails;
	}

	public TypeSorterDetails getTypeSorterDetails() {
		return typeSorterDetails;
	}

}