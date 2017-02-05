package co.com.cybersoft.maintenance.tables.persistence.services.sorter;

import co.com.cybersoft.maintenance.tables.events.sorter.CreateSorterEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterPageEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.SorterModificationEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.sorter.RequestSorterPageEvent;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface SorterPersistenceService {

	SorterDetailsEvent createSorter(CreateSorterEvent event) throws Exception;

	SorterPageEvent requestSorterPage(RequestSorterPageEvent event) throws Exception;

	SorterDetailsEvent requestSorterDetails(RequestSorterDetailsEvent event) throws Exception;
	
	SorterDetailsEvent modifySorter(SorterModificationEvent event) throws Exception;
	SorterPageEvent requestSorterFilterPage(RequestSorterPageEvent event) throws Exception;
	SorterPageEvent requestSorterFilter(RequestSorterPageEvent event) throws Exception;
	
	SorterPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	SorterPageEvent requestAllByTypeSorter(EmbeddedField... fields) throws Exception;
	SorterPageEvent requestAllByNameSorter(EmbeddedField... fields) throws Exception;

	
	
	SorterDetailsEvent getOnlyRecord() throws Exception;
	
}