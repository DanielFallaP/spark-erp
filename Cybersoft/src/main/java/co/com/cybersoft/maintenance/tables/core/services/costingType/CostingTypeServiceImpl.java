package co.com.cybersoft.maintenance.tables.core.services.costingType;

import co.com.cybersoft.maintenance.tables.events.costingType.CreateCostingTypeEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.costingType.CostingTypePersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostingTypeServiceImpl implements CostingTypeService{

	private final CostingTypePersistenceService costingTypePersistenceService;
	
	public CostingTypeServiceImpl(final CostingTypePersistenceService costingTypePersistenceService){
		this.costingTypePersistenceService=costingTypePersistenceService;
	}
	
	/**
	 */
	public CostingTypeDetailsEvent createCostingType(CreateCostingTypeEvent event ) throws Exception{
		return costingTypePersistenceService.createCostingType(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CostingTypePageEvent requestCostingTypePage(RequestCostingTypePageEvent event) throws Exception{
		return costingTypePersistenceService.requestCostingTypePage(event);
	}

	public CostingTypeDetailsEvent requestCostingTypeDetails(RequestCostingTypeDetailsEvent event ) throws Exception{
		return costingTypePersistenceService.requestCostingTypeDetails(event);
	}

	public CostingTypeDetailsEvent modifyCostingType(CostingTypeModificationEvent event) throws Exception {
		return costingTypePersistenceService.modifyCostingType(event);
	}
	
	public CostingTypeDetailsEvent requestOnlyRecord() throws Exception {
		return costingTypePersistenceService.getOnlyRecord();
	}
	
	public CostingTypePageEvent requestCostingTypeFilterPage(RequestCostingTypePageEvent event) throws Exception {
		return costingTypePersistenceService.requestCostingTypeFilterPage(event);
	}
	
	public CostingTypePageEvent requestCostingTypeFilter(RequestCostingTypePageEvent event) throws Exception {
		return costingTypePersistenceService.requestCostingTypeFilter(event);
	}
	

	public CostingTypePageEvent requestAllByCostingType(EmbeddedField... fields) throws Exception {
		return costingTypePersistenceService.requestAllByCostingType(fields);
	}
	
	
	
}