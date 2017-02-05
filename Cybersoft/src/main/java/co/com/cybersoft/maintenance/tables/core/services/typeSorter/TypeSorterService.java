package co.com.cybersoft.maintenance.tables.core.services.typeSorter;

import co.com.cybersoft.maintenance.tables.events.typeSorter.CreateTypeSorterEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.TypeSorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeSorter.RequestTypeSorterPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeSorterService {
	TypeSorterDetailsEvent requestOnlyRecord() throws Exception;

	TypeSorterDetailsEvent createTypeSorter(CreateTypeSorterEvent event ) throws Exception;
	
	TypeSorterPageEvent requestTypeSorterPage(RequestTypeSorterPageEvent event) throws Exception;

	TypeSorterDetailsEvent requestTypeSorterDetails(RequestTypeSorterDetailsEvent event ) throws Exception;

	TypeSorterDetailsEvent modifyTypeSorter(TypeSorterModificationEvent event) throws Exception;
	
	TypeSorterPageEvent requestAllByNameTypeSorter(EmbeddedField... fields) throws Exception;

	
	
	TypeSorterPageEvent requestTypeSorterFilterPage(RequestTypeSorterPageEvent event) throws Exception;

	TypeSorterPageEvent requestTypeSorterFilter(RequestTypeSorterPageEvent event) throws Exception;
	
}