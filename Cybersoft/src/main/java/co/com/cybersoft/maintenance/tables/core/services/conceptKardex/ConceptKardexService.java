package co.com.cybersoft.maintenance.tables.core.services.conceptKardex;

import co.com.cybersoft.maintenance.tables.events.conceptKardex.CreateConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.ConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.conceptKardex.RequestConceptKardexPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ConceptKardexService {
	ConceptKardexDetailsEvent requestOnlyRecord() throws Exception;

	ConceptKardexDetailsEvent createConceptKardex(CreateConceptKardexEvent event ) throws Exception;
	
	ConceptKardexPageEvent requestConceptKardexPage(RequestConceptKardexPageEvent event) throws Exception;

	ConceptKardexDetailsEvent requestConceptKardexDetails(RequestConceptKardexDetailsEvent event ) throws Exception;

	ConceptKardexDetailsEvent modifyConceptKardex(ConceptKardexModificationEvent event) throws Exception;
	
	ConceptKardexPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	ConceptKardexPageEvent requestAllByStore(EmbeddedField... fields) throws Exception;
	ConceptKardexPageEvent requestAllByTypeConceptKardex(EmbeddedField... fields) throws Exception;

	
	
	ConceptKardexPageEvent requestConceptKardexFilterPage(RequestConceptKardexPageEvent event) throws Exception;

	ConceptKardexPageEvent requestConceptKardexFilter(RequestConceptKardexPageEvent event) throws Exception;
	
}