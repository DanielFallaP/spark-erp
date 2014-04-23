package co.com.cybersoft.persistence.services.costCenter;

import org.springframework.data.domain.Page;

import co.com.cybersoft.core.domain.CostCenterDetails;
import co.com.cybersoft.events.costCenter.CreateCostCenterEvent;
import co.com.cybersoft.events.costCenter.CostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.CostCenterPageEvent;
import co.com.cybersoft.events.costCenter.CostCenterModificationEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterDetailsEvent;
import co.com.cybersoft.events.costCenter.RequestCostCenterPageEvent;
import co.com.cybersoft.persistence.domain.CostCenter;
import co.com.cybersoft.persistence.repository.CostCenterRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CostCenterPersistenceServiceImpl implements CostCenterPersistenceService{

	private final CostCenterRepository costCenterRepository;
	
	public CostCenterPersistenceServiceImpl(final CostCenterRepository costCenterRepository) {
		this.costCenterRepository=costCenterRepository;
	}
	
	@Override
	public CostCenterDetailsEvent createCostCenter(CreateCostCenterEvent event) throws Exception{
		CostCenter costCenter = CostCenter.fromCostCenterDetails(event.getCostCenterDetails());
		costCenter = costCenterRepository.save(costCenter);
		return new CostCenterDetailsEvent(costCenter.toCostCenterDetails());
	}

	@Override
	public CostCenterPageEvent requestCostCenterPage(RequestCostCenterPageEvent event) throws Exception {
		Page<CostCenter> costCenters = costCenterRepository.findAll(event.getPageable());
		return new CostCenterPageEvent(costCenters);
	}

	@Override
	public CostCenterDetailsEvent requestCostCenterDetails(RequestCostCenterDetailsEvent event) throws Exception {
		CostCenter costCenter = costCenterRepository.findByCode(event.getId());
		CostCenterDetails costCenterDetails = costCenter.toCostCenterDetails();
		return new CostCenterDetailsEvent(costCenterDetails);
	}

	@Override
	public CostCenterDetailsEvent modifyCostCenter(CostCenterModificationEvent event) throws Exception {
		CostCenter costCenter = CostCenter.fromCostCenterDetails(event.getCostCenterDetails());
		costCenter = costCenterRepository.save(costCenter);
		return new CostCenterDetailsEvent(costCenter.toCostCenterDetails());
	}
	
	@Override
	public CostCenterPageEvent requestAll() throws Exception {
		List<CostCenter> all = costCenterRepository.findAll();
		List<CostCenterDetails> list = new ArrayList<CostCenterDetails>();
		for (CostCenter costCenter : all) {
			list.add(costCenter.toCostCenterDetails());
		}
		return new CostCenterPageEvent(list);
	}

}