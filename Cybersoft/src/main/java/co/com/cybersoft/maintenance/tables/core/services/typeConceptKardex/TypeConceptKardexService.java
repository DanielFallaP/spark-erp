package co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex;

import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.CreateTypeConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface TypeConceptKardexService {
	TypeConceptKardexDetailsEvent requestOnlyRecord() throws Exception;

	TypeConceptKardexDetailsEvent createTypeConceptKardex(CreateTypeConceptKardexEvent event ) throws Exception;
	
	TypeConceptKardexPageEvent requestTypeConceptKardexPage(RequestTypeConceptKardexPageEvent event) throws Exception;

	TypeConceptKardexDetailsEvent requestTypeConceptKardexDetails(RequestTypeConceptKardexDetailsEvent event ) throws Exception;

	TypeConceptKardexDetailsEvent modifyTypeConceptKardex(TypeConceptKardexModificationEvent event) throws Exception;
	
	TypeConceptKardexPageEvent requestAllByTypeConceptKardex(EmbeddedField... fields) throws Exception;

	
	
	TypeConceptKardexPageEvent requestTypeConceptKardexFilterPage(RequestTypeConceptKardexPageEvent event) throws Exception;

	TypeConceptKardexPageEvent requestTypeConceptKardexFilter(RequestTypeConceptKardexPageEvent event) throws Exception;
	
}