package co.com.cybersoft.maintenance.tables.core.services.costingType;

import co.com.cybersoft.maintenance.tables.events.costingType.CreateCostingTypeEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypePageEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.CostingTypeModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypeDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costingType.RequestCostingTypePageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CostingTypeService {
	CostingTypeDetailsEvent requestOnlyRecord() throws Exception;

	CostingTypeDetailsEvent createCostingType(CreateCostingTypeEvent event ) throws Exception;
	
	CostingTypePageEvent requestCostingTypePage(RequestCostingTypePageEvent event) throws Exception;

	CostingTypeDetailsEvent requestCostingTypeDetails(RequestCostingTypeDetailsEvent event ) throws Exception;

	CostingTypeDetailsEvent modifyCostingType(CostingTypeModificationEvent event) throws Exception;
	
	CostingTypePageEvent requestAllByCostingType(EmbeddedField... fields) throws Exception;

	
	
	CostingTypePageEvent requestCostingTypeFilterPage(RequestCostingTypePageEvent event) throws Exception;

	CostingTypePageEvent requestCostingTypeFilter(RequestCostingTypePageEvent event) throws Exception;
	
}