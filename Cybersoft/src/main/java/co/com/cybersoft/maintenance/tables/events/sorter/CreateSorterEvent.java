package co.com.cybersoft.maintenance.tables.events.sorter;

import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;

/**
 * Event class for Sorter
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CreateSorterEvent {
		
	private SorterDetails sorterDetails;
	
	public CreateSorterEvent(SorterDetails sorterDetails){
		this.sorterDetails=sorterDetails;
	}

	public SorterDetails getSorterDetails() {
		return sorterDetails;
	}
	
	
}