package co.com.cybersoft.maintenance.tables.core.services.typeSorter;

import co.com.cybersoft.maintenance.tables.events.typeSorter.CreateTypeSorterEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.typeSorter.TypeSorterPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeSorterServiceImpl implements TypeSorterService{

	private final TypeSorterPersistenceService typeSorterPersistenceService;
	
	public TypeSorterServiceImpl(final TypeSorterPersistenceService typeSorterPersistenceService){
		this.typeSorterPersistenceService=typeSorterPersistenceService;
	}
	
	/**
	 */
	public TypeSorterDetailsEvent createTypeSorter(CreateTypeSorterEvent event ) throws Exception{
		return typeSorterPersistenceService.createTypeSorter(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public TypeSorterPageEvent requestTypeSorterPage(RequestTypeSorterPageEvent event) throws Exception{
		return typeSorterPersistenceService.requestTypeSorterPage(event);
	}

	public TypeSorterDetailsEvent requestTypeSorterDetails(RequestTypeSorterDetailsEvent event ) throws Exception{
		return typeSorterPersistenceService.requestTypeSorterDetails(event);
	}

	public TypeSorterDetailsEvent modifyTypeSorter(TypeSorterModificationEvent event) throws Exception {
		return typeSorterPersistenceService.modifyTypeSorter(event);
	}
	
	public TypeSorterDetailsEvent requestOnlyRecord() throws Exception {
		return typeSorterPersistenceService.getOnlyRecord();
	}
	
	public TypeSorterPageEvent requestTypeSorterFilterPage(RequestTypeSorterPageEvent event) throws Exception {
		return typeSorterPersistenceService.requestTypeSorterFilterPage(event);
	}
	
	public TypeSorterPageEvent requestTypeSorterFilter(RequestTypeSorterPageEvent event) throws Exception {
		return typeSorterPersistenceService.requestTypeSorterFilter(event);
	}
	

	public TypeSorterPageEvent requestAllByNameTypeSorter(EmbeddedField... fields) throws Exception {
		return typeSorterPersistenceService.requestAllByNameTypeSorter(fields);
	}
	
	
	
}