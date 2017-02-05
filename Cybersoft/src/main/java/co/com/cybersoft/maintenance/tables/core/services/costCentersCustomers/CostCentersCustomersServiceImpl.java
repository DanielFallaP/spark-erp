package co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers;

import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CreateCostCentersCustomersEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.CostCentersCustomersModificationEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.costCentersCustomers.RequestCostCentersCustomersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.services.costCentersCustomers.CostCentersCustomersPersistenceService;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCentersCustomersServiceImpl implements CostCentersCustomersService{

	private final CostCentersCustomersPersistenceService costCentersCustomersPersistenceService;
	
	public CostCentersCustomersServiceImpl(final CostCentersCustomersPersistenceService costCentersCustomersPersistenceService){
		this.costCentersCustomersPersistenceService=costCentersCustomersPersistenceService;
	}
	
	/**
	 */
	public CostCentersCustomersDetailsEvent createCostCentersCustomers(CreateCostCentersCustomersEvent event ) throws Exception{
		return costCentersCustomersPersistenceService.createCostCentersCustomers(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	public CostCentersCustomersPageEvent requestCostCentersCustomersPage(RequestCostCentersCustomersPageEvent event) throws Exception{
		return costCentersCustomersPersistenceService.requestCostCentersCustomersPage(event);
	}

	public CostCentersCustomersDetailsEvent requestCostCentersCustomersDetails(RequestCostCentersCustomersDetailsEvent event ) throws Exception{
		return costCentersCustomersPersistenceService.requestCostCentersCustomersDetails(event);
	}

	public CostCentersCustomersDetailsEvent modifyCostCentersCustomers(CostCentersCustomersModificationEvent event) throws Exception {
		return costCentersCustomersPersistenceService.modifyCostCentersCustomers(event);
	}
	
	public CostCentersCustomersDetailsEvent requestOnlyRecord() throws Exception {
		return costCentersCustomersPersistenceService.getOnlyRecord();
	}
	
	public CostCentersCustomersPageEvent requestCostCentersCustomersFilterPage(RequestCostCentersCustomersPageEvent event) throws Exception {
		return costCentersCustomersPersistenceService.requestCostCentersCustomersFilterPage(event);
	}
	
	public CostCentersCustomersPageEvent requestCostCentersCustomersFilter(RequestCostCentersCustomersPageEvent event) throws Exception {
		return costCentersCustomersPersistenceService.requestCostCentersCustomersFilter(event);
	}
	

	public CostCentersCustomersPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByCompany(fields);
	}public CostCentersCustomersPageEvent requestAllByDescription(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByDescription(fields);
	}public CostCentersCustomersPageEvent requestAllByNameCostCenter(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByNameCostCenter(fields);
	}public CostCentersCustomersPageEvent requestAllBySubCostCentersCustomers(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllBySubCostCentersCustomers(fields);
	}public CostCentersCustomersPageEvent requestAllBySubDescription(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllBySubDescription(fields);
	}public CostCentersCustomersPageEvent requestAllByContact(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByContact(fields);
	}public CostCentersCustomersPageEvent requestAllByAreaDepartment(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByAreaDepartment(fields);
	}public CostCentersCustomersPageEvent requestAllByAddress(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByAddress(fields);
	}public CostCentersCustomersPageEvent requestAllByCity(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByCity(fields);
	}public CostCentersCustomersPageEvent requestAllByPhone(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByPhone(fields);
	}public CostCentersCustomersPageEvent requestAllByState(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByState(fields);
	}public CostCentersCustomersPageEvent requestAllByComments(EmbeddedField... fields) throws Exception {
		return costCentersCustomersPersistenceService.requestAllByComments(fields);
	}
	
	
	
}