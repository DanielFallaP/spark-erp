package co.com.cybersoft.core.services.costCenter;

import co.com.cybersoft.events.costCenter.CreateCostCenterEvent;
import co.com.cybersoft.events.costCenter.CostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.CostCenterPageEvent;
import co.com.cybersoft.events.costCenter.CostCenterModificationEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterPageEvent;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CostCenterService {
	CostCenterDetailsEvent createCostCenter(CreateCostCenterEvent event ) throws Exception;
	
	CostCenterPageEvent requestCostCenterPage(RequestCostCenterPageEvent event) throws Exception;

	CostCenterDetailsEvent requestCostCenterDetails(RequestCostCenterDetailsEvent event ) throws Exception;

	CostCenterDetailsEvent modifyCostCenter(CostCenterModificationEvent event) throws Exception;
	
	CostCenterPageEvent requestAll() throws Exception;
	
	CostCenterPageEvent requestByCodePrefix(String code) throws Exception;
	
	CostCenterPageEvent requestByContainingDescription(String description) throws Exception;
	
}