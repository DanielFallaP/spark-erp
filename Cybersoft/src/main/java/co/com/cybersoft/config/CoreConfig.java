package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.core.services.items.ItemServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.core.services.active.ActiveService;
import co.com.cybersoft.core.services.active.ActiveServiceImpl;
import co.com.cybersoft.persistence.services.active.ActivePersistenceService;

import co.com.cybersoft.core.services.partner.PartnerService;
import co.com.cybersoft.core.services.partner.PartnerServiceImpl;
import co.com.cybersoft.persistence.services.partner.PartnerPersistenceService;

import co.com.cybersoft.core.services.corporation.CorporationService;
import co.com.cybersoft.core.services.corporation.CorporationServiceImpl;
import co.com.cybersoft.persistence.services.corporation.CorporationPersistenceService;

import co.com.cybersoft.core.services.company.CompanyService;
import co.com.cybersoft.core.services.company.CompanyServiceImpl;
import co.com.cybersoft.persistence.services.company.CompanyPersistenceService;

import co.com.cybersoft.core.services.branch.BranchService;
import co.com.cybersoft.core.services.branch.BranchServiceImpl;
import co.com.cybersoft.persistence.services.branch.BranchPersistenceService;

import co.com.cybersoft.core.services.measurementUnits.MeasurementUnitsService;
import co.com.cybersoft.core.services.measurementUnits.MeasurementUnitsServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnits.MeasurementUnitsPersistenceService;

import co.com.cybersoft.core.services.items.ItemsService;
import co.com.cybersoft.core.services.items.ItemsServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemsPersistenceService;

import co.com.cybersoft.core.services.bill.BillService;
import co.com.cybersoft.core.services.bill.BillServiceImpl;
import co.com.cybersoft.persistence.services.bill.BillPersistenceService;

import co.com.cybersoft.core.services.jointVenture.JointVentureService;
import co.com.cybersoft.core.services.jointVenture.JointVentureServiceImpl;
import co.com.cybersoft.persistence.services.jointVenture.JointVenturePersistenceService;

import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.core.services.contract.ContractServiceImpl;
import co.com.cybersoft.persistence.services.contract.ContractPersistenceService;

import co.com.cybersoft.core.services.afeType.AfeTypeService;
import co.com.cybersoft.core.services.afeType.AfeTypeServiceImpl;
import co.com.cybersoft.persistence.services.afeType.AfeTypePersistenceService;

import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.core.services.afe.AfeServiceImpl;
import co.com.cybersoft.persistence.services.afe.AfePersistenceService;

import co.com.cybersoft.core.services.costCenter.CostCenterService;
import co.com.cybersoft.core.services.costCenter.CostCenterServiceImpl;
import co.com.cybersoft.persistence.services.costCenter.CostCenterPersistenceService;

import co.com.cybersoft.core.services.calculusType.CalculusTypeService;
import co.com.cybersoft.core.services.calculusType.CalculusTypeServiceImpl;
import co.com.cybersoft.persistence.services.calculusType.CalculusTypePersistenceService;

import co.com.cybersoft.core.services.operationType.OperationTypeService;
import co.com.cybersoft.core.services.operationType.OperationTypeServiceImpl;
import co.com.cybersoft.persistence.services.operationType.OperationTypePersistenceService;

import co.com.cybersoft.core.services.wareHouse.WareHouseService;
import co.com.cybersoft.core.services.wareHouse.WareHouseServiceImpl;
import co.com.cybersoft.persistence.services.wareHouse.WareHousePersistenceService;



@Configuration
public class CoreConfig {

	@Bean 
	public ItemService itemService(ItemPersistenceService itemPersistenceService){
		return new ItemServiceImpl(itemPersistenceService);
	}
	
	@Bean
	public MeasurementUnitService measurementUnitService(MeasurementUnitPersistenceService measurementUnitPersistenceService){
		return new MeasurementUnitServiceImpl(measurementUnitPersistenceService);
	}
	
	@Bean
	public ActiveService activeService(ActivePersistenceService activePersistenceService){
		return new ActiveServiceImpl(activePersistenceService);
	}

	@Bean
	public PartnerService partnerService(PartnerPersistenceService partnerPersistenceService){
		return new PartnerServiceImpl(partnerPersistenceService);
	}

	@Bean
	public CorporationService corporationService(CorporationPersistenceService corporationPersistenceService){
		return new CorporationServiceImpl(corporationPersistenceService);
	}

	@Bean
	public CompanyService companyService(CompanyPersistenceService companyPersistenceService){
		return new CompanyServiceImpl(companyPersistenceService);
	}

	@Bean
	public BranchService branchService(BranchPersistenceService branchPersistenceService){
		return new BranchServiceImpl(branchPersistenceService);
	}

	@Bean
	public MeasurementUnitsService measurementUnitsService(MeasurementUnitsPersistenceService measurementUnitsPersistenceService){
		return new MeasurementUnitsServiceImpl(measurementUnitsPersistenceService);
	}

	@Bean
	public ItemsService itemsService(ItemsPersistenceService itemsPersistenceService){
		return new ItemsServiceImpl(itemsPersistenceService);
	}

	@Bean
	public BillService billService(BillPersistenceService billPersistenceService){
		return new BillServiceImpl(billPersistenceService);
	}

	@Bean
	public JointVentureService jointVentureService(JointVenturePersistenceService jointVenturePersistenceService){
		return new JointVentureServiceImpl(jointVenturePersistenceService);
	}

	@Bean
	public ContractService contractService(ContractPersistenceService contractPersistenceService){
		return new ContractServiceImpl(contractPersistenceService);
	}

	@Bean
	public AfeTypeService afeTypeService(AfeTypePersistenceService afeTypePersistenceService){
		return new AfeTypeServiceImpl(afeTypePersistenceService);
	}

	@Bean
	public AfeService afeService(AfePersistenceService afePersistenceService){
		return new AfeServiceImpl(afePersistenceService);
	}

	@Bean
	public CostCenterService costCenterService(CostCenterPersistenceService costCenterPersistenceService){
		return new CostCenterServiceImpl(costCenterPersistenceService);
	}

	@Bean
	public CalculusTypeService calculusTypeService(CalculusTypePersistenceService calculusTypePersistenceService){
		return new CalculusTypeServiceImpl(calculusTypePersistenceService);
	}

	@Bean
	public OperationTypeService operationTypeService(OperationTypePersistenceService operationTypePersistenceService){
		return new OperationTypeServiceImpl(operationTypePersistenceService);
	}

	@Bean
	public WareHouseService wareHouseService(WareHousePersistenceService wareHousePersistenceService){
		return new WareHouseServiceImpl(wareHousePersistenceService);
	}


}