package co.com.cybersoft.maintenance.tables.core.services.sorter;

import co.com.cybersoft.maintenance.tables.events.sorter.CreateSorterEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.sorter.SorterPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class SorterServiceImpl implements SorterService{

	private final SorterPersistenceService sorterPersistenceService;
	
	public SorterServiceImpl(final SorterPersistenceService sorterPersistenceService){
		this.sorterPersistenceService=sorterPersistenceService;
	}
	
	/**
	 */
	public SorterDetailsEvent createSorter(CreateSorterEvent event ) throws Exception{
		return sorterPersistenceService.createSorter(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public SorterPageEvent requestSorterPage(RequestSorterPageEvent event) throws Exception{
		return sorterPersistenceService.requestSorterPage(event);
	}

	public SorterDetailsEvent requestSorterDetails(RequestSorterDetailsEvent event ) throws Exception{
		return sorterPersistenceService.requestSorterDetails(event);
	}

	public SorterDetailsEvent modifySorter(SorterModificationEvent event) throws Exception {
		return sorterPersistenceService.modifySorter(event);
	}
	
	public SorterDetailsEvent requestOnlyRecord() throws Exception {
		return sorterPersistenceService.getOnlyRecord();
	}
	
	public SorterPageEvent requestSorterFilterPage(RequestSorterPageEvent event) throws Exception {
		return sorterPersistenceService.requestSorterFilterPage(event);
	}
	
	public SorterPageEvent requestSorterFilter(RequestSorterPageEvent event) throws Exception {
		return sorterPersistenceService.requestSorterFilter(event);
	}
	

	public SorterPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return sorterPersistenceService.requestAllByCompany(fields);
	}public SorterPageEvent requestAllByTypeSorter(EmbeddedField... fields) throws Exception {
		return sorterPersistenceService.requestAllByTypeSorter(fields);
	}public SorterPageEvent requestAllByNameSorter(EmbeddedField... fields) throws Exception {
		return sorterPersistenceService.requestAllByNameSorter(fields);
	}
	
	
	
}