package co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers;

import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CreateCostCentersCustomersEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersPageEvent;
import co.com.cybersoft.util.EmbeddedField;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CostCentersCustomersService {
	CostCentersCustomersDetailsEvent requestOnlyRecord() throws Exception;

	CostCentersCustomersDetailsEvent createCostCentersCustomers(CreateCostCentersCustomersEvent event ) throws Exception;
	
	CostCentersCustomersPageEvent requestCostCentersCustomersPage(RequestCostCentersCustomersPageEvent event) throws Exception;

	CostCentersCustomersDetailsEvent requestCostCentersCustomersDetails(RequestCostCentersCustomersDetailsEvent event ) throws Exception;

	CostCentersCustomersDetailsEvent modifyCostCentersCustomers(CostCentersCustomersModificationEvent event) throws Exception;
	
	CostCentersCustomersPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByNameCostCenter(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllBySubCostCentersCustomers(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllBySubDescription(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByContact(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByAreaDepartment(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByAddress(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByCity(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByPhone(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByState(EmbeddedField... fields) throws Exception;
	CostCentersCustomersPageEvent requestAllByComments(EmbeddedField... fields) throws Exception;

	
	
	CostCentersCustomersPageEvent requestCostCentersCustomersFilterPage(RequestCostCentersCustomersPageEvent event) throws Exception;

	CostCentersCustomersPageEvent requestCostCentersCustomersFilter(RequestCostCentersCustomersPageEvent event) throws Exception;
	
}