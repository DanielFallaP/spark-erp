package co.com.cybersoft.maintenance.tables.events.typeSorter;

import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;

/**
 * Event class for TypeSorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateTypeSorterEvent {
		
	private TypeSorterDetails typeSorterDetails;
	
	public CreateTypeSorterEvent(TypeSorterDetails typeSorterDetails){
		this.typeSorterDetails=typeSorterDetails;
	}

	public TypeSorterDetails getTypeSorterDetails() {
		return typeSorterDetails;
	}
	
	
}