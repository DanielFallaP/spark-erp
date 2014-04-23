package co.com.cybersoft.core.services.costCenter;

import java.util.Date;

import co.com.cybersoft.events.costCenter.CreateCostCenterEvent;
import co.com.cybersoft.events.costCenter.CostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.CostCenterPageEvent;
import co.com.cybersoft.events.costCenter.CostCenterModificationEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterPageEvent;
import co.com.cybersoft.persistence.services.costCenter.CostCenterPersistenceService;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCenterServiceImpl implements CostCenterService{

	private final CostCenterPersistenceService costCenterPersistenceService;
	
	public CostCenterServiceImpl(final CostCenterPersistenceService costCenterPersistenceService){
		this.costCenterPersistenceService=costCenterPersistenceService;
	}
	
	/**
	 */
	@Override
	public CostCenterDetailsEvent createCostCenter(CreateCostCenterEvent event ) throws Exception{
		return costCenterPersistenceService.createCostCenter(event);
	}

	/**
	 * Returns all the rows of the page requested
	 */
	@Override
	public CostCenterPageEvent requestCostCenterPage(RequestCostCenterPageEvent event) throws Exception{
		return costCenterPersistenceService.requestCostCenterPage(event);
	}

	@Override
	public CostCenterDetailsEvent requestCostCenterDetails(RequestCostCenterDetailsEvent event ) throws Exception{
		return costCenterPersistenceService.requestCostCenterDetails(event);
	}

	@Override
	public CostCenterDetailsEvent modifyCostCenter(CostCenterModificationEvent event) throws Exception {
		return costCenterPersistenceService.modifyCostCenter(event);
	}
	
	@Override
	public CostCenterPageEvent requestAll() throws Exception {
		return costCenterPersistenceService.requestAll();
	}

}