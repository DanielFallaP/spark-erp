package co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex;

import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.CreateTypeConceptKardexEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.TypeConceptKardexModificationEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.typeConceptKardex.RequestTypeConceptKardexPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.typeConceptKardex.TypeConceptKardexPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class TypeConceptKardexServiceImpl implements TypeConceptKardexService{

	private final TypeConceptKardexPersistenceService typeConceptKardexPersistenceService;
	
	public TypeConceptKardexServiceImpl(final TypeConceptKardexPersistenceService typeConceptKardexPersistenceService){
		this.typeConceptKardexPersistenceService=typeConceptKardexPersistenceService;
	}
	
	/**
	 */
	public TypeConceptKardexDetailsEvent createTypeConceptKardex(CreateTypeConceptKardexEvent event ) throws Exception{
		return typeConceptKardexPersistenceService.createTypeConceptKardex(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public TypeConceptKardexPageEvent requestTypeConceptKardexPage(RequestTypeConceptKardexPageEvent event) throws Exception{
		return typeConceptKardexPersistenceService.requestTypeConceptKardexPage(event);
	}

	public TypeConceptKardexDetailsEvent requestTypeConceptKardexDetails(RequestTypeConceptKardexDetailsEvent event ) throws Exception{
		return typeConceptKardexPersistenceService.requestTypeConceptKardexDetails(event);
	}

	public TypeConceptKardexDetailsEvent modifyTypeConceptKardex(TypeConceptKardexModificationEvent event) throws Exception {
		return typeConceptKardexPersistenceService.modifyTypeConceptKardex(event);
	}
	
	public TypeConceptKardexDetailsEvent requestOnlyRecord() throws Exception {
		return typeConceptKardexPersistenceService.getOnlyRecord();
	}
	
	public TypeConceptKardexPageEvent requestTypeConceptKardexFilterPage(RequestTypeConceptKardexPageEvent event) throws Exception {
		return typeConceptKardexPersistenceService.requestTypeConceptKardexFilterPage(event);
	}
	
	public TypeConceptKardexPageEvent requestTypeConceptKardexFilter(RequestTypeConceptKardexPageEvent event) throws Exception {
		return typeConceptKardexPersistenceService.requestTypeConceptKardexFilter(event);
	}
	

	public TypeConceptKardexPageEvent requestAllByTypeConceptKardex(EmbeddedField... fields) throws Exception {
		return typeConceptKardexPersistenceService.requestAllByTypeConceptKardex(fields);
	}
	
	
	
}