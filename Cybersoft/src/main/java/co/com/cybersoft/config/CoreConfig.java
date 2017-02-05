package co.com.cybersoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.company.CompanyPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.state.StateService;
import co.com.cybersoft.maintenance.tables.core.services.state.StateServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.state.StatePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit.MeasurementUnitPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterService;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeSorter.TypeSorterPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterService;
import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.sorter.SorterPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.operation.OperationService;
import co.com.cybersoft.maintenance.tables.core.services.operation.OperationServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.operation.OperationPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceService;
import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.reference.ReferencePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationService;
import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation.ReferenceOperationPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionService;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.technicalAction.TechnicalActionPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailService;
import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFail.EffectFailPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionService;
import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction.EffectFailTechnicalActionPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseService;
import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCause.FailureCausePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionService;
import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction.FailureCauseTechnicalactionPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseService;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose.TypeCauseClosePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseService;
import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.causeClose.CauseClosePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityService;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity.MaintenanceActivityPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkService;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceWork.MaintenanceWorkPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceService;
import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeMaintenance.TypeMaintenancePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingService;
import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.causePending.CausePendingPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkService;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeWork.TypeWorkPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsService;
import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts.OtherConceptsPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersService;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters.StateCostCentersPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersService;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.costCentersCustomers.CostCentersCustomersPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterService;
import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter.ResponsibleCenterPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.job.JobService;
import co.com.cybersoft.maintenance.tables.core.services.job.JobServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.job.JobPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.third.ThirdService;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.third.ThirdPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdService;
import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.jobThird.JobThirdPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleService;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.responsible.ResponsiblePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterService;
import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter.AuthorizerCostCenterPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicService;
import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.characteristic.CharacteristicPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantService;
import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.accountant.AccountantPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationService;
import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.physicalLocation.PhysicalLocationPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeService;
import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.storeType.StoreTypePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeService;
import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.costingType.CostingTypePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseService;
import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.warehouse.WarehousePersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.coin.CoinService;
import co.com.cybersoft.maintenance.tables.core.services.coin.CoinServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.coin.CoinPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexService;
import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeConceptKardex.TypeConceptKardexPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexService;
import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.conceptKardex.ConceptKardexPersistenceService;

import co.com.cybersoft.maintenance.tables.core.services.contract.ContractService;
import co.com.cybersoft.maintenance.tables.core.services.contract.ContractServiceImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.contract.ContractPersistenceService;

import co.com.cybersoft.purchase.tables.core.services.users.UsersService;
import co.com.cybersoft.purchase.tables.core.services.users.UsersServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.users.UsersPersistenceService;

import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.currency.CurrencyPersistenceService;

import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateService;
import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.exchangeRate.ExchangeRatePersistenceService;

import co.com.cybersoft.man.maintenance.TechnicalActions;
import co.com.cybersoft.man.maintenance.TechnicalActions;
import co.com.cybersoft.man.maintenance.TechnicalActions;
import co.com.cybersoft.man.maintenance.TechnicalActions;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceService;

import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.core.services.region.RegionServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.region.RegionPersistenceService;

import co.com.cybersoft.purchase.tables.core.services.customerTenancy.CustomerTenancyService;
import co.com.cybersoft.purchase.tables.core.services.customerTenancy.CustomerTenancyServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.customerTenancy.CustomerTenancyPersistenceService;

import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.currencyCode.CurrencyCodePersistenceService;

import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.purchase.tables.core.services.country.CountryServiceImpl;
import co.com.cybersoft.purchase.tables.persistence.services.country.CountryPersistenceService;

import co.com.cybersoft.maintenance.tables.persistence.repository.company.CompanyRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.company.CompanyCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.company.CompanyCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.company.CompanyPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.company.CompanyPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyReportingService;
import co.com.cybersoft.maintenance.tables.core.services.company.CompanyReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.state.StateRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.state.StateCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.state.StateCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.state.StatePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.state.StatePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.state.StateReportingService;
import co.com.cybersoft.maintenance.tables.core.services.state.StateReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit.MeasurementUnitRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit.MeasurementUnitCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.measurementUnit.MeasurementUnitCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.measurementUnit.MeasurementUnitPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitReportingService;
import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter.TypeSorterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter.TypeSorterCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeSorter.TypeSorterCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeSorter.TypeSorterPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.typeSorter.TypeSorterPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterReportingService;
import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.sorter.SorterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.sorter.SorterCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.sorter.SorterCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.sorter.SorterPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.sorter.SorterPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterReportingService;
import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.operation.OperationRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.operation.OperationCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.operation.OperationCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.operation.OperationPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.operation.OperationPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.operation.OperationReportingService;
import co.com.cybersoft.maintenance.tables.core.services.operation.OperationReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.reference.ReferenceRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.reference.ReferenceCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.reference.ReferenceCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.reference.ReferencePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.reference.ReferencePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceReportingService;
import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation.ReferenceOperationRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation.ReferenceOperationCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.referenceOperation.ReferenceOperationCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation.ReferenceOperationPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.referenceOperation.ReferenceOperationPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationReportingService;
import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction.TechnicalActionRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction.TechnicalActionCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.technicalAction.TechnicalActionCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.technicalAction.TechnicalActionPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.technicalAction.TechnicalActionPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionReportingService;
import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.effectFail.EffectFailRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFail.EffectFailCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFail.EffectFailCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFail.EffectFailPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFail.EffectFailPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailReportingService;
import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction.EffectFailTechnicalActionRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction.EffectFailTechnicalActionCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.effectFailTechnicalAction.EffectFailTechnicalActionCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction.EffectFailTechnicalActionPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.effectFailTechnicalAction.EffectFailTechnicalActionPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionReportingService;
import co.com.cybersoft.maintenance.tables.core.services.effectFailTechnicalAction.EffectFailTechnicalActionReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.failureCause.FailureCauseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCause.FailureCauseCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCause.FailureCauseCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCause.FailureCausePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCause.FailureCausePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseReportingService;
import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction.FailureCauseTechnicalactionRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction.FailureCauseTechnicalactionCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.failureCauseTechnicalaction.FailureCauseTechnicalactionCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction.FailureCauseTechnicalactionPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.failureCauseTechnicalaction.FailureCauseTechnicalactionPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionReportingService;
import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose.TypeCauseCloseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose.TypeCauseCloseCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeCauseClose.TypeCauseCloseCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose.TypeCauseClosePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.typeCauseClose.TypeCauseClosePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseReportingService;
import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.causeClose.CauseCloseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.causeClose.CauseCloseCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.causeClose.CauseCloseCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.causeClose.CauseClosePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.causeClose.CauseClosePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseReportingService;
import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity.MaintenanceActivityRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity.MaintenanceActivityCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceActivity.MaintenanceActivityCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity.MaintenanceActivityPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceActivity.MaintenanceActivityPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityReportingService;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork.MaintenanceWorkRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork.MaintenanceWorkCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceWork.MaintenanceWorkCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceWork.MaintenanceWorkPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.maintenanceWork.MaintenanceWorkPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkReportingService;
import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance.TypeMaintenanceRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance.TypeMaintenanceCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeMaintenance.TypeMaintenanceCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeMaintenance.TypeMaintenancePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.typeMaintenance.TypeMaintenancePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceReportingService;
import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.causePending.CausePendingRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.causePending.CausePendingCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.causePending.CausePendingCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.causePending.CausePendingPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.causePending.CausePendingPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingReportingService;
import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeWork.TypeWorkRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeWork.TypeWorkCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeWork.TypeWorkCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeWork.TypeWorkPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.typeWork.TypeWorkPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkReportingService;
import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts.OtherConceptsRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts.OtherConceptsCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.otherConcepts.OtherConceptsCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts.OtherConceptsPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.otherConcepts.OtherConceptsPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsReportingService;
import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters.StateCostCentersRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters.StateCostCentersCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.stateCostCenters.StateCostCentersCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters.StateCostCentersPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.stateCostCenters.StateCostCentersPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersReportingService;
import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers.CostCentersCustomersRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers.CostCentersCustomersCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.costCentersCustomers.CostCentersCustomersCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.costCentersCustomers.CostCentersCustomersPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.costCentersCustomers.CostCentersCustomersPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersReportingService;
import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter.ResponsibleCenterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter.ResponsibleCenterCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsibleCenter.ResponsibleCenterCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter.ResponsibleCenterPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.responsibleCenter.ResponsibleCenterPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterReportingService;
import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.job.JobRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.job.JobCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.job.JobCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.job.JobPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.job.JobPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.job.JobReportingService;
import co.com.cybersoft.maintenance.tables.core.services.job.JobReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.third.ThirdRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.third.ThirdCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.third.ThirdCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.third.ThirdPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.third.ThirdPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdReportingService;
import co.com.cybersoft.maintenance.tables.core.services.third.ThirdReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.jobThird.JobThirdRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.jobThird.JobThirdCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.jobThird.JobThirdCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.jobThird.JobThirdPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.jobThird.JobThirdPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdReportingService;
import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.responsible.ResponsibleRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsible.ResponsibleCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.responsible.ResponsibleCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.responsible.ResponsiblePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.responsible.ResponsiblePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleReportingService;
import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter.AuthorizerCostCenterRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter.AuthorizerCostCenterCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.authorizerCostCenter.AuthorizerCostCenterCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter.AuthorizerCostCenterPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.authorizerCostCenter.AuthorizerCostCenterPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterReportingService;
import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.characteristic.CharacteristicRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.characteristic.CharacteristicCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.characteristic.CharacteristicCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.characteristic.CharacteristicPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.characteristic.CharacteristicPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicReportingService;
import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.accountant.AccountantRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.accountant.AccountantCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.accountant.AccountantCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.accountant.AccountantPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.accountant.AccountantPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantReportingService;
import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation.PhysicalLocationRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation.PhysicalLocationCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.physicalLocation.PhysicalLocationCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.physicalLocation.PhysicalLocationPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.physicalLocation.PhysicalLocationPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationReportingService;
import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.storeType.StoreTypeRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.storeType.StoreTypeCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.storeType.StoreTypeCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.storeType.StoreTypePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.storeType.StoreTypePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeReportingService;
import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.costingType.CostingTypeRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.costingType.CostingTypeCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.costingType.CostingTypeCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.costingType.CostingTypePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.costingType.CostingTypePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeReportingService;
import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.warehouse.WarehouseRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.warehouse.WarehouseCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.warehouse.WarehouseCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.warehouse.WarehousePersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.warehouse.WarehousePersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseReportingService;
import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.coin.CoinRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.coin.CoinCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.coin.CoinCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.coin.CoinPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.coin.CoinPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.coin.CoinReportingService;
import co.com.cybersoft.maintenance.tables.core.services.coin.CoinReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex.TypeConceptKardexRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex.TypeConceptKardexCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.typeConceptKardex.TypeConceptKardexCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.typeConceptKardex.TypeConceptKardexPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.typeConceptKardex.TypeConceptKardexPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexReportingService;
import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex.ConceptKardexRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex.ConceptKardexCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.conceptKardex.ConceptKardexCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.conceptKardex.ConceptKardexPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.conceptKardex.ConceptKardexPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexReportingService;
import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexReportingServiceImpl;

import co.com.cybersoft.maintenance.tables.persistence.repository.contract.ContractRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.contract.ContractCustomRepo;
import co.com.cybersoft.maintenance.tables.persistence.repository.contract.ContractCustomRepoImpl;
import co.com.cybersoft.maintenance.tables.persistence.services.contract.ContractPersistenceService;
import co.com.cybersoft.maintenance.tables.persistence.services.contract.ContractPersistenceServiceImpl;
import co.com.cybersoft.maintenance.tables.core.services.contract.ContractReportingService;
import co.com.cybersoft.maintenance.tables.core.services.contract.ContractReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.users.UsersRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.users.UsersCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.users.UsersCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.users.UsersPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.users.UsersPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.users.UsersReportingService;
import co.com.cybersoft.purchase.tables.core.services.users.UsersReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.currency.CurrencyRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.currency.CurrencyCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.currency.CurrencyCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.currency.CurrencyPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.currency.CurrencyPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyReportingService;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.exchangeRate.ExchangeRatePersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.exchangeRate.ExchangeRatePersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateReportingService;
import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.continent.ContinentCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.continent.ContinentPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentReportingService;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.region.RegionCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.region.RegionPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.region.RegionPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.region.RegionReportingService;
import co.com.cybersoft.purchase.tables.core.services.region.RegionReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.customerTenancy.CustomerTenancyPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.customerTenancy.CustomerTenancyPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.customerTenancy.CustomerTenancyReportingService;
import co.com.cybersoft.purchase.tables.core.services.customerTenancy.CustomerTenancyReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.currencyCode.CurrencyCodeRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.currencyCode.CurrencyCodeCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.currencyCode.CurrencyCodeCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.currencyCode.CurrencyCodePersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.currencyCode.CurrencyCodePersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeReportingService;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeReportingServiceImpl;

import co.com.cybersoft.purchase.tables.persistence.repository.country.CountryRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.country.CountryCustomRepo;
import co.com.cybersoft.purchase.tables.persistence.repository.country.CountryCustomRepoImpl;
import co.com.cybersoft.purchase.tables.persistence.services.country.CountryPersistenceService;
import co.com.cybersoft.purchase.tables.persistence.services.country.CountryPersistenceServiceImpl;
import co.com.cybersoft.purchase.tables.core.services.country.CountryReportingService;
import co.com.cybersoft.purchase.tables.core.services.country.CountryReportingServiceImpl;



@Configuration
public class CoreConfig {
	@Bean
	public TechnicalActions technicalActionsAPI(){
		return new TechnicalActions();
	}

	
	@Bean
	public CompanyService companyService(CompanyPersistenceService companyPersistenceService){
		return new CompanyServiceImpl(companyPersistenceService);
	}

	@Bean
	public StateService stateService(StatePersistenceService statePersistenceService){
		return new StateServiceImpl(statePersistenceService);
	}

	@Bean
	public MeasurementUnitService measurementUnitService(MeasurementUnitPersistenceService measurementUnitPersistenceService){
		return new MeasurementUnitServiceImpl(measurementUnitPersistenceService);
	}

	@Bean
	public TypeSorterService typeSorterService(TypeSorterPersistenceService typeSorterPersistenceService){
		return new TypeSorterServiceImpl(typeSorterPersistenceService);
	}

	@Bean
	public SorterService sorterService(SorterPersistenceService sorterPersistenceService){
		return new SorterServiceImpl(sorterPersistenceService);
	}

	@Bean
	public OperationService operationService(OperationPersistenceService operationPersistenceService){
		return new OperationServiceImpl(operationPersistenceService);
	}

	@Bean
	public ReferenceService referenceService(ReferencePersistenceService referencePersistenceService){
		return new ReferenceServiceImpl(referencePersistenceService);
	}

	@Bean
	public ReferenceOperationService referenceOperationService(ReferenceOperationPersistenceService referenceOperationPersistenceService){
		return new ReferenceOperationServiceImpl(referenceOperationPersistenceService);
	}

	@Bean
	public TechnicalActionService technicalActionService(TechnicalActionPersistenceService technicalActionPersistenceService){
		return new TechnicalActionServiceImpl(technicalActionPersistenceService);
	}

	@Bean
	public EffectFailService effectFailService(EffectFailPersistenceService effectFailPersistenceService){
		return new EffectFailServiceImpl(effectFailPersistenceService);
	}

	@Bean
	public EffectFailTechnicalActionService effectFailTechnicalActionService(EffectFailTechnicalActionPersistenceService effectFailTechnicalActionPersistenceService){
		return new EffectFailTechnicalActionServiceImpl(effectFailTechnicalActionPersistenceService);
	}

	@Bean
	public FailureCauseService failureCauseService(FailureCausePersistenceService failureCausePersistenceService){
		return new FailureCauseServiceImpl(failureCausePersistenceService);
	}

	@Bean
	public FailureCauseTechnicalactionService failureCauseTechnicalactionService(FailureCauseTechnicalactionPersistenceService failureCauseTechnicalactionPersistenceService){
		return new FailureCauseTechnicalactionServiceImpl(failureCauseTechnicalactionPersistenceService);
	}

	@Bean
	public TypeCauseCloseService typeCauseCloseService(TypeCauseClosePersistenceService typeCauseClosePersistenceService){
		return new TypeCauseCloseServiceImpl(typeCauseClosePersistenceService);
	}

	@Bean
	public CauseCloseService causeCloseService(CauseClosePersistenceService causeClosePersistenceService){
		return new CauseCloseServiceImpl(causeClosePersistenceService);
	}

	@Bean
	public MaintenanceActivityService maintenanceActivityService(MaintenanceActivityPersistenceService maintenanceActivityPersistenceService){
		return new MaintenanceActivityServiceImpl(maintenanceActivityPersistenceService);
	}

	@Bean
	public MaintenanceWorkService maintenanceWorkService(MaintenanceWorkPersistenceService maintenanceWorkPersistenceService){
		return new MaintenanceWorkServiceImpl(maintenanceWorkPersistenceService);
	}

	@Bean
	public TypeMaintenanceService typeMaintenanceService(TypeMaintenancePersistenceService typeMaintenancePersistenceService){
		return new TypeMaintenanceServiceImpl(typeMaintenancePersistenceService);
	}

	@Bean
	public CausePendingService causePendingService(CausePendingPersistenceService causePendingPersistenceService){
		return new CausePendingServiceImpl(causePendingPersistenceService);
	}

	@Bean
	public TypeWorkService typeWorkService(TypeWorkPersistenceService typeWorkPersistenceService){
		return new TypeWorkServiceImpl(typeWorkPersistenceService);
	}

	@Bean
	public OtherConceptsService otherConceptsService(OtherConceptsPersistenceService otherConceptsPersistenceService){
		return new OtherConceptsServiceImpl(otherConceptsPersistenceService);
	}

	@Bean
	public StateCostCentersService stateCostCentersService(StateCostCentersPersistenceService stateCostCentersPersistenceService){
		return new StateCostCentersServiceImpl(stateCostCentersPersistenceService);
	}

	@Bean
	public CostCentersCustomersService costCentersCustomersService(CostCentersCustomersPersistenceService costCentersCustomersPersistenceService){
		return new CostCentersCustomersServiceImpl(costCentersCustomersPersistenceService);
	}

	@Bean
	public ResponsibleCenterService responsibleCenterService(ResponsibleCenterPersistenceService responsibleCenterPersistenceService){
		return new ResponsibleCenterServiceImpl(responsibleCenterPersistenceService);
	}

	@Bean
	public JobService jobService(JobPersistenceService jobPersistenceService){
		return new JobServiceImpl(jobPersistenceService);
	}

	@Bean
	public ThirdService thirdService(ThirdPersistenceService thirdPersistenceService){
		return new ThirdServiceImpl(thirdPersistenceService);
	}

	@Bean
	public JobThirdService jobThirdService(JobThirdPersistenceService jobThirdPersistenceService){
		return new JobThirdServiceImpl(jobThirdPersistenceService);
	}

	@Bean
	public ResponsibleService responsibleService(ResponsiblePersistenceService responsiblePersistenceService){
		return new ResponsibleServiceImpl(responsiblePersistenceService);
	}

	@Bean
	public AuthorizerCostCenterService authorizerCostCenterService(AuthorizerCostCenterPersistenceService authorizerCostCenterPersistenceService){
		return new AuthorizerCostCenterServiceImpl(authorizerCostCenterPersistenceService);
	}

	@Bean
	public CharacteristicService characteristicService(CharacteristicPersistenceService characteristicPersistenceService){
		return new CharacteristicServiceImpl(characteristicPersistenceService);
	}

	@Bean
	public AccountantService accountantService(AccountantPersistenceService accountantPersistenceService){
		return new AccountantServiceImpl(accountantPersistenceService);
	}

	@Bean
	public PhysicalLocationService physicalLocationService(PhysicalLocationPersistenceService physicalLocationPersistenceService){
		return new PhysicalLocationServiceImpl(physicalLocationPersistenceService);
	}

	@Bean
	public StoreTypeService storeTypeService(StoreTypePersistenceService storeTypePersistenceService){
		return new StoreTypeServiceImpl(storeTypePersistenceService);
	}

	@Bean
	public CostingTypeService costingTypeService(CostingTypePersistenceService costingTypePersistenceService){
		return new CostingTypeServiceImpl(costingTypePersistenceService);
	}

	@Bean
	public WarehouseService warehouseService(WarehousePersistenceService warehousePersistenceService){
		return new WarehouseServiceImpl(warehousePersistenceService);
	}

	@Bean
	public CoinService coinService(CoinPersistenceService coinPersistenceService){
		return new CoinServiceImpl(coinPersistenceService);
	}

	@Bean
	public TypeConceptKardexService typeConceptKardexService(TypeConceptKardexPersistenceService typeConceptKardexPersistenceService){
		return new TypeConceptKardexServiceImpl(typeConceptKardexPersistenceService);
	}

	@Bean
	public ConceptKardexService conceptKardexService(ConceptKardexPersistenceService conceptKardexPersistenceService){
		return new ConceptKardexServiceImpl(conceptKardexPersistenceService);
	}

	@Bean
	public ContractService contractService(ContractPersistenceService contractPersistenceService){
		return new ContractServiceImpl(contractPersistenceService);
	}

	@Bean
	public UsersService usersService(UsersPersistenceService usersPersistenceService){
		return new UsersServiceImpl(usersPersistenceService);
	}

	@Bean
	public CurrencyService currencyService(CurrencyPersistenceService currencyPersistenceService){
		return new CurrencyServiceImpl(currencyPersistenceService);
	}

	@Bean
	public ExchangeRateService exchangeRateService(ExchangeRatePersistenceService exchangeRatePersistenceService){
		return new ExchangeRateServiceImpl(exchangeRatePersistenceService);
	}

	@Bean
	public ContinentService continentService(ContinentPersistenceService continentPersistenceService){
		return new ContinentServiceImpl(continentPersistenceService);
	}

	@Bean
	public RegionService regionService(RegionPersistenceService regionPersistenceService){
		return new RegionServiceImpl(regionPersistenceService);
	}

	@Bean
	public CustomerTenancyService customerTenancyService(CustomerTenancyPersistenceService customerTenancyPersistenceService){
		return new CustomerTenancyServiceImpl(customerTenancyPersistenceService);
	}

	@Bean
	public CurrencyCodeService currencyCodeService(CurrencyCodePersistenceService currencyCodePersistenceService){
		return new CurrencyCodeServiceImpl(currencyCodePersistenceService);
	}

	@Bean
	public CountryService countryService(CountryPersistenceService countryPersistenceService){
		return new CountryServiceImpl(countryPersistenceService);
	}


	
	@Autowired
	private CompanyRepository companyRepository;

	@Bean
	public CompanyCustomRepo companyCustomRepo(){
		return new CompanyCustomRepoImpl();
	}

	@Autowired
	private StateRepository stateRepository;

	@Bean
	public StateCustomRepo stateCustomRepo(){
		return new StateCustomRepoImpl();
	}

	@Autowired
	private MeasurementUnitRepository measurementUnitRepository;

	@Bean
	public MeasurementUnitCustomRepo measurementUnitCustomRepo(){
		return new MeasurementUnitCustomRepoImpl();
	}

	@Autowired
	private TypeSorterRepository typeSorterRepository;

	@Bean
	public TypeSorterCustomRepo typeSorterCustomRepo(){
		return new TypeSorterCustomRepoImpl();
	}

	@Autowired
	private SorterRepository sorterRepository;

	@Bean
	public SorterCustomRepo sorterCustomRepo(){
		return new SorterCustomRepoImpl();
	}

	@Autowired
	private OperationRepository operationRepository;

	@Bean
	public OperationCustomRepo operationCustomRepo(){
		return new OperationCustomRepoImpl();
	}

	@Autowired
	private ReferenceRepository referenceRepository;

	@Bean
	public ReferenceCustomRepo referenceCustomRepo(){
		return new ReferenceCustomRepoImpl();
	}

	@Autowired
	private ReferenceOperationRepository referenceOperationRepository;

	@Bean
	public ReferenceOperationCustomRepo referenceOperationCustomRepo(){
		return new ReferenceOperationCustomRepoImpl();
	}

	@Autowired
	private TechnicalActionRepository technicalActionRepository;

	@Bean
	public TechnicalActionCustomRepo technicalActionCustomRepo(){
		return new TechnicalActionCustomRepoImpl();
	}

	@Autowired
	private EffectFailRepository effectFailRepository;

	@Bean
	public EffectFailCustomRepo effectFailCustomRepo(){
		return new EffectFailCustomRepoImpl();
	}

	@Autowired
	private EffectFailTechnicalActionRepository effectFailTechnicalActionRepository;

	@Bean
	public EffectFailTechnicalActionCustomRepo effectFailTechnicalActionCustomRepo(){
		return new EffectFailTechnicalActionCustomRepoImpl();
	}

	@Autowired
	private FailureCauseRepository failureCauseRepository;

	@Bean
	public FailureCauseCustomRepo failureCauseCustomRepo(){
		return new FailureCauseCustomRepoImpl();
	}

	@Autowired
	private FailureCauseTechnicalactionRepository failureCauseTechnicalactionRepository;

	@Bean
	public FailureCauseTechnicalactionCustomRepo failureCauseTechnicalactionCustomRepo(){
		return new FailureCauseTechnicalactionCustomRepoImpl();
	}

	@Autowired
	private TypeCauseCloseRepository typeCauseCloseRepository;

	@Bean
	public TypeCauseCloseCustomRepo typeCauseCloseCustomRepo(){
		return new TypeCauseCloseCustomRepoImpl();
	}

	@Autowired
	private CauseCloseRepository causeCloseRepository;

	@Bean
	public CauseCloseCustomRepo causeCloseCustomRepo(){
		return new CauseCloseCustomRepoImpl();
	}

	@Autowired
	private MaintenanceActivityRepository maintenanceActivityRepository;

	@Bean
	public MaintenanceActivityCustomRepo maintenanceActivityCustomRepo(){
		return new MaintenanceActivityCustomRepoImpl();
	}

	@Autowired
	private MaintenanceWorkRepository maintenanceWorkRepository;

	@Bean
	public MaintenanceWorkCustomRepo maintenanceWorkCustomRepo(){
		return new MaintenanceWorkCustomRepoImpl();
	}

	@Autowired
	private TypeMaintenanceRepository typeMaintenanceRepository;

	@Bean
	public TypeMaintenanceCustomRepo typeMaintenanceCustomRepo(){
		return new TypeMaintenanceCustomRepoImpl();
	}

	@Autowired
	private CausePendingRepository causePendingRepository;

	@Bean
	public CausePendingCustomRepo causePendingCustomRepo(){
		return new CausePendingCustomRepoImpl();
	}

	@Autowired
	private TypeWorkRepository typeWorkRepository;

	@Bean
	public TypeWorkCustomRepo typeWorkCustomRepo(){
		return new TypeWorkCustomRepoImpl();
	}

	@Autowired
	private OtherConceptsRepository otherConceptsRepository;

	@Bean
	public OtherConceptsCustomRepo otherConceptsCustomRepo(){
		return new OtherConceptsCustomRepoImpl();
	}

	@Autowired
	private StateCostCentersRepository stateCostCentersRepository;

	@Bean
	public StateCostCentersCustomRepo stateCostCentersCustomRepo(){
		return new StateCostCentersCustomRepoImpl();
	}

	@Autowired
	private CostCentersCustomersRepository costCentersCustomersRepository;

	@Bean
	public CostCentersCustomersCustomRepo costCentersCustomersCustomRepo(){
		return new CostCentersCustomersCustomRepoImpl();
	}

	@Autowired
	private ResponsibleCenterRepository responsibleCenterRepository;

	@Bean
	public ResponsibleCenterCustomRepo responsibleCenterCustomRepo(){
		return new ResponsibleCenterCustomRepoImpl();
	}

	@Autowired
	private JobRepository jobRepository;

	@Bean
	public JobCustomRepo jobCustomRepo(){
		return new JobCustomRepoImpl();
	}

	@Autowired
	private ThirdRepository thirdRepository;

	@Bean
	public ThirdCustomRepo thirdCustomRepo(){
		return new ThirdCustomRepoImpl();
	}

	@Autowired
	private JobThirdRepository jobThirdRepository;

	@Bean
	public JobThirdCustomRepo jobThirdCustomRepo(){
		return new JobThirdCustomRepoImpl();
	}

	@Autowired
	private ResponsibleRepository responsibleRepository;

	@Bean
	public ResponsibleCustomRepo responsibleCustomRepo(){
		return new ResponsibleCustomRepoImpl();
	}

	@Autowired
	private AuthorizerCostCenterRepository authorizerCostCenterRepository;

	@Bean
	public AuthorizerCostCenterCustomRepo authorizerCostCenterCustomRepo(){
		return new AuthorizerCostCenterCustomRepoImpl();
	}

	@Autowired
	private CharacteristicRepository characteristicRepository;

	@Bean
	public CharacteristicCustomRepo characteristicCustomRepo(){
		return new CharacteristicCustomRepoImpl();
	}

	@Autowired
	private AccountantRepository accountantRepository;

	@Bean
	public AccountantCustomRepo accountantCustomRepo(){
		return new AccountantCustomRepoImpl();
	}

	@Autowired
	private PhysicalLocationRepository physicalLocationRepository;

	@Bean
	public PhysicalLocationCustomRepo physicalLocationCustomRepo(){
		return new PhysicalLocationCustomRepoImpl();
	}

	@Autowired
	private StoreTypeRepository storeTypeRepository;

	@Bean
	public StoreTypeCustomRepo storeTypeCustomRepo(){
		return new StoreTypeCustomRepoImpl();
	}

	@Autowired
	private CostingTypeRepository costingTypeRepository;

	@Bean
	public CostingTypeCustomRepo costingTypeCustomRepo(){
		return new CostingTypeCustomRepoImpl();
	}

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Bean
	public WarehouseCustomRepo warehouseCustomRepo(){
		return new WarehouseCustomRepoImpl();
	}

	@Autowired
	private CoinRepository coinRepository;

	@Bean
	public CoinCustomRepo coinCustomRepo(){
		return new CoinCustomRepoImpl();
	}

	@Autowired
	private TypeConceptKardexRepository typeConceptKardexRepository;

	@Bean
	public TypeConceptKardexCustomRepo typeConceptKardexCustomRepo(){
		return new TypeConceptKardexCustomRepoImpl();
	}

	@Autowired
	private ConceptKardexRepository conceptKardexRepository;

	@Bean
	public ConceptKardexCustomRepo conceptKardexCustomRepo(){
		return new ConceptKardexCustomRepoImpl();
	}

	@Autowired
	private ContractRepository contractRepository;

	@Bean
	public ContractCustomRepo contractCustomRepo(){
		return new ContractCustomRepoImpl();
	}

	@Autowired
	private UsersRepository usersRepository;

	@Bean
	public UsersCustomRepo usersCustomRepo(){
		return new UsersCustomRepoImpl();
	}

	@Autowired
	private CurrencyRepository currencyRepository;

	@Bean
	public CurrencyCustomRepo currencyCustomRepo(){
		return new CurrencyCustomRepoImpl();
	}

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@Bean
	public ExchangeRateCustomRepo exchangeRateCustomRepo(){
		return new ExchangeRateCustomRepoImpl();
	}

	@Autowired
	private ContinentRepository continentRepository;

	@Bean
	public ContinentCustomRepo continentCustomRepo(){
		return new ContinentCustomRepoImpl();
	}

	@Autowired
	private RegionRepository regionRepository;

	@Bean
	public RegionCustomRepo regionCustomRepo(){
		return new RegionCustomRepoImpl();
	}

	@Autowired
	private CustomerTenancyRepository customerTenancyRepository;

	@Bean
	public CustomerTenancyCustomRepo customerTenancyCustomRepo(){
		return new CustomerTenancyCustomRepoImpl();
	}

	@Autowired
	private CurrencyCodeRepository currencyCodeRepository;

	@Bean
	public CurrencyCodeCustomRepo currencyCodeCustomRepo(){
		return new CurrencyCodeCustomRepoImpl();
	}

	@Autowired
	private CountryRepository countryRepository;

	@Bean
	public CountryCustomRepo countryCustomRepo(){
		return new CountryCustomRepoImpl();
	}


	
	@Bean 
	public CompanyPersistenceService companyPersistenceService(){
		return new CompanyPersistenceServiceImpl(companyRepository, companyCustomRepo());
	}

	@Bean 
	public CompanyReportingService companyReportingService(){
		return new CompanyReportingServiceImpl();
	}

	@Bean 
	public StatePersistenceService statePersistenceService(){
		return new StatePersistenceServiceImpl(stateRepository, stateCustomRepo());
	}

	@Bean 
	public StateReportingService stateReportingService(){
		return new StateReportingServiceImpl();
	}

	@Bean 
	public MeasurementUnitPersistenceService measurementUnitPersistenceService(){
		return new MeasurementUnitPersistenceServiceImpl(measurementUnitRepository, measurementUnitCustomRepo());
	}

	@Bean 
	public MeasurementUnitReportingService measurementUnitReportingService(){
		return new MeasurementUnitReportingServiceImpl();
	}

	@Bean 
	public TypeSorterPersistenceService typeSorterPersistenceService(){
		return new TypeSorterPersistenceServiceImpl(typeSorterRepository, typeSorterCustomRepo());
	}

	@Bean 
	public TypeSorterReportingService typeSorterReportingService(){
		return new TypeSorterReportingServiceImpl();
	}

	@Bean 
	public SorterPersistenceService sorterPersistenceService(){
		return new SorterPersistenceServiceImpl(sorterRepository, sorterCustomRepo());
	}

	@Bean 
	public SorterReportingService sorterReportingService(){
		return new SorterReportingServiceImpl();
	}

	@Bean 
	public OperationPersistenceService operationPersistenceService(){
		return new OperationPersistenceServiceImpl(operationRepository, operationCustomRepo());
	}

	@Bean 
	public OperationReportingService operationReportingService(){
		return new OperationReportingServiceImpl();
	}

	@Bean 
	public ReferencePersistenceService referencePersistenceService(){
		return new ReferencePersistenceServiceImpl(referenceRepository, referenceCustomRepo());
	}

	@Bean 
	public ReferenceReportingService referenceReportingService(){
		return new ReferenceReportingServiceImpl();
	}

	@Bean 
	public ReferenceOperationPersistenceService referenceOperationPersistenceService(){
		return new ReferenceOperationPersistenceServiceImpl(referenceOperationRepository, referenceOperationCustomRepo());
	}

	@Bean 
	public ReferenceOperationReportingService referenceOperationReportingService(){
		return new ReferenceOperationReportingServiceImpl();
	}

	@Bean 
	public TechnicalActionPersistenceService technicalActionPersistenceService(){
		return new TechnicalActionPersistenceServiceImpl(technicalActionRepository, technicalActionCustomRepo());
	}

	@Bean 
	public TechnicalActionReportingService technicalActionReportingService(){
		return new TechnicalActionReportingServiceImpl();
	}

	@Bean 
	public EffectFailPersistenceService effectFailPersistenceService(){
		return new EffectFailPersistenceServiceImpl(effectFailRepository, effectFailCustomRepo());
	}

	@Bean 
	public EffectFailReportingService effectFailReportingService(){
		return new EffectFailReportingServiceImpl();
	}

	@Bean 
	public EffectFailTechnicalActionPersistenceService effectFailTechnicalActionPersistenceService(){
		return new EffectFailTechnicalActionPersistenceServiceImpl(effectFailTechnicalActionRepository, effectFailTechnicalActionCustomRepo());
	}

	@Bean 
	public EffectFailTechnicalActionReportingService effectFailTechnicalActionReportingService(){
		return new EffectFailTechnicalActionReportingServiceImpl();
	}

	@Bean 
	public FailureCausePersistenceService failureCausePersistenceService(){
		return new FailureCausePersistenceServiceImpl(failureCauseRepository, failureCauseCustomRepo());
	}

	@Bean 
	public FailureCauseReportingService failureCauseReportingService(){
		return new FailureCauseReportingServiceImpl();
	}

	@Bean 
	public FailureCauseTechnicalactionPersistenceService failureCauseTechnicalactionPersistenceService(){
		return new FailureCauseTechnicalactionPersistenceServiceImpl(failureCauseTechnicalactionRepository, failureCauseTechnicalactionCustomRepo());
	}

	@Bean 
	public FailureCauseTechnicalactionReportingService failureCauseTechnicalactionReportingService(){
		return new FailureCauseTechnicalactionReportingServiceImpl();
	}

	@Bean 
	public TypeCauseClosePersistenceService typeCauseClosePersistenceService(){
		return new TypeCauseClosePersistenceServiceImpl(typeCauseCloseRepository, typeCauseCloseCustomRepo());
	}

	@Bean 
	public TypeCauseCloseReportingService typeCauseCloseReportingService(){
		return new TypeCauseCloseReportingServiceImpl();
	}

	@Bean 
	public CauseClosePersistenceService causeClosePersistenceService(){
		return new CauseClosePersistenceServiceImpl(causeCloseRepository, causeCloseCustomRepo());
	}

	@Bean 
	public CauseCloseReportingService causeCloseReportingService(){
		return new CauseCloseReportingServiceImpl();
	}

	@Bean 
	public MaintenanceActivityPersistenceService maintenanceActivityPersistenceService(){
		return new MaintenanceActivityPersistenceServiceImpl(maintenanceActivityRepository, maintenanceActivityCustomRepo());
	}

	@Bean 
	public MaintenanceActivityReportingService maintenanceActivityReportingService(){
		return new MaintenanceActivityReportingServiceImpl();
	}

	@Bean 
	public MaintenanceWorkPersistenceService maintenanceWorkPersistenceService(){
		return new MaintenanceWorkPersistenceServiceImpl(maintenanceWorkRepository, maintenanceWorkCustomRepo());
	}

	@Bean 
	public MaintenanceWorkReportingService maintenanceWorkReportingService(){
		return new MaintenanceWorkReportingServiceImpl();
	}

	@Bean 
	public TypeMaintenancePersistenceService typeMaintenancePersistenceService(){
		return new TypeMaintenancePersistenceServiceImpl(typeMaintenanceRepository, typeMaintenanceCustomRepo());
	}

	@Bean 
	public TypeMaintenanceReportingService typeMaintenanceReportingService(){
		return new TypeMaintenanceReportingServiceImpl();
	}

	@Bean 
	public CausePendingPersistenceService causePendingPersistenceService(){
		return new CausePendingPersistenceServiceImpl(causePendingRepository, causePendingCustomRepo());
	}

	@Bean 
	public CausePendingReportingService causePendingReportingService(){
		return new CausePendingReportingServiceImpl();
	}

	@Bean 
	public TypeWorkPersistenceService typeWorkPersistenceService(){
		return new TypeWorkPersistenceServiceImpl(typeWorkRepository, typeWorkCustomRepo());
	}

	@Bean 
	public TypeWorkReportingService typeWorkReportingService(){
		return new TypeWorkReportingServiceImpl();
	}

	@Bean 
	public OtherConceptsPersistenceService otherConceptsPersistenceService(){
		return new OtherConceptsPersistenceServiceImpl(otherConceptsRepository, otherConceptsCustomRepo());
	}

	@Bean 
	public OtherConceptsReportingService otherConceptsReportingService(){
		return new OtherConceptsReportingServiceImpl();
	}

	@Bean 
	public StateCostCentersPersistenceService stateCostCentersPersistenceService(){
		return new StateCostCentersPersistenceServiceImpl(stateCostCentersRepository, stateCostCentersCustomRepo());
	}

	@Bean 
	public StateCostCentersReportingService stateCostCentersReportingService(){
		return new StateCostCentersReportingServiceImpl();
	}

	@Bean 
	public CostCentersCustomersPersistenceService costCentersCustomersPersistenceService(){
		return new CostCentersCustomersPersistenceServiceImpl(costCentersCustomersRepository, costCentersCustomersCustomRepo());
	}

	@Bean 
	public CostCentersCustomersReportingService costCentersCustomersReportingService(){
		return new CostCentersCustomersReportingServiceImpl();
	}

	@Bean 
	public ResponsibleCenterPersistenceService responsibleCenterPersistenceService(){
		return new ResponsibleCenterPersistenceServiceImpl(responsibleCenterRepository, responsibleCenterCustomRepo());
	}

	@Bean 
	public ResponsibleCenterReportingService responsibleCenterReportingService(){
		return new ResponsibleCenterReportingServiceImpl();
	}

	@Bean 
	public JobPersistenceService jobPersistenceService(){
		return new JobPersistenceServiceImpl(jobRepository, jobCustomRepo());
	}

	@Bean 
	public JobReportingService jobReportingService(){
		return new JobReportingServiceImpl();
	}

	@Bean 
	public ThirdPersistenceService thirdPersistenceService(){
		return new ThirdPersistenceServiceImpl(thirdRepository, thirdCustomRepo());
	}

	@Bean 
	public ThirdReportingService thirdReportingService(){
		return new ThirdReportingServiceImpl();
	}

	@Bean 
	public JobThirdPersistenceService jobThirdPersistenceService(){
		return new JobThirdPersistenceServiceImpl(jobThirdRepository, jobThirdCustomRepo());
	}

	@Bean 
	public JobThirdReportingService jobThirdReportingService(){
		return new JobThirdReportingServiceImpl();
	}

	@Bean 
	public ResponsiblePersistenceService responsiblePersistenceService(){
		return new ResponsiblePersistenceServiceImpl(responsibleRepository, responsibleCustomRepo());
	}

	@Bean 
	public ResponsibleReportingService responsibleReportingService(){
		return new ResponsibleReportingServiceImpl();
	}

	@Bean 
	public AuthorizerCostCenterPersistenceService authorizerCostCenterPersistenceService(){
		return new AuthorizerCostCenterPersistenceServiceImpl(authorizerCostCenterRepository, authorizerCostCenterCustomRepo());
	}

	@Bean 
	public AuthorizerCostCenterReportingService authorizerCostCenterReportingService(){
		return new AuthorizerCostCenterReportingServiceImpl();
	}

	@Bean 
	public CharacteristicPersistenceService characteristicPersistenceService(){
		return new CharacteristicPersistenceServiceImpl(characteristicRepository, characteristicCustomRepo());
	}

	@Bean 
	public CharacteristicReportingService characteristicReportingService(){
		return new CharacteristicReportingServiceImpl();
	}

	@Bean 
	public AccountantPersistenceService accountantPersistenceService(){
		return new AccountantPersistenceServiceImpl(accountantRepository, accountantCustomRepo());
	}

	@Bean 
	public AccountantReportingService accountantReportingService(){
		return new AccountantReportingServiceImpl();
	}

	@Bean 
	public PhysicalLocationPersistenceService physicalLocationPersistenceService(){
		return new PhysicalLocationPersistenceServiceImpl(physicalLocationRepository, physicalLocationCustomRepo());
	}

	@Bean 
	public PhysicalLocationReportingService physicalLocationReportingService(){
		return new PhysicalLocationReportingServiceImpl();
	}

	@Bean 
	public StoreTypePersistenceService storeTypePersistenceService(){
		return new StoreTypePersistenceServiceImpl(storeTypeRepository, storeTypeCustomRepo());
	}

	@Bean 
	public StoreTypeReportingService storeTypeReportingService(){
		return new StoreTypeReportingServiceImpl();
	}

	@Bean 
	public CostingTypePersistenceService costingTypePersistenceService(){
		return new CostingTypePersistenceServiceImpl(costingTypeRepository, costingTypeCustomRepo());
	}

	@Bean 
	public CostingTypeReportingService costingTypeReportingService(){
		return new CostingTypeReportingServiceImpl();
	}

	@Bean 
	public WarehousePersistenceService warehousePersistenceService(){
		return new WarehousePersistenceServiceImpl(warehouseRepository, warehouseCustomRepo());
	}

	@Bean 
	public WarehouseReportingService warehouseReportingService(){
		return new WarehouseReportingServiceImpl();
	}

	@Bean 
	public CoinPersistenceService coinPersistenceService(){
		return new CoinPersistenceServiceImpl(coinRepository, coinCustomRepo());
	}

	@Bean 
	public CoinReportingService coinReportingService(){
		return new CoinReportingServiceImpl();
	}

	@Bean 
	public TypeConceptKardexPersistenceService typeConceptKardexPersistenceService(){
		return new TypeConceptKardexPersistenceServiceImpl(typeConceptKardexRepository, typeConceptKardexCustomRepo());
	}

	@Bean 
	public TypeConceptKardexReportingService typeConceptKardexReportingService(){
		return new TypeConceptKardexReportingServiceImpl();
	}

	@Bean 
	public ConceptKardexPersistenceService conceptKardexPersistenceService(){
		return new ConceptKardexPersistenceServiceImpl(conceptKardexRepository, conceptKardexCustomRepo());
	}

	@Bean 
	public ConceptKardexReportingService conceptKardexReportingService(){
		return new ConceptKardexReportingServiceImpl();
	}

	@Bean 
	public ContractPersistenceService contractPersistenceService(){
		return new ContractPersistenceServiceImpl(contractRepository, contractCustomRepo());
	}

	@Bean 
	public ContractReportingService contractReportingService(){
		return new ContractReportingServiceImpl();
	}

	@Bean 
	public UsersPersistenceService usersPersistenceService(){
		return new UsersPersistenceServiceImpl(usersRepository, usersCustomRepo());
	}

	@Bean 
	public UsersReportingService usersReportingService(){
		return new UsersReportingServiceImpl();
	}

	@Bean 
	public CurrencyPersistenceService currencyPersistenceService(){
		return new CurrencyPersistenceServiceImpl(currencyRepository, currencyCustomRepo());
	}

	@Bean 
	public CurrencyReportingService currencyReportingService(){
		return new CurrencyReportingServiceImpl();
	}

	@Bean 
	public ExchangeRatePersistenceService exchangeRatePersistenceService(){
		return new ExchangeRatePersistenceServiceImpl(exchangeRateRepository, exchangeRateCustomRepo());
	}

	@Bean 
	public ExchangeRateReportingService exchangeRateReportingService(){
		return new ExchangeRateReportingServiceImpl();
	}

	@Bean 
	public ContinentPersistenceService continentPersistenceService(){
		return new ContinentPersistenceServiceImpl(continentRepository, continentCustomRepo());
	}

	@Bean 
	public ContinentReportingService continentReportingService(){
		return new ContinentReportingServiceImpl();
	}

	@Bean 
	public RegionPersistenceService regionPersistenceService(){
		return new RegionPersistenceServiceImpl(regionRepository, regionCustomRepo());
	}

	@Bean 
	public RegionReportingService regionReportingService(){
		return new RegionReportingServiceImpl();
	}

	@Bean 
	public CustomerTenancyPersistenceService customerTenancyPersistenceService(){
		return new CustomerTenancyPersistenceServiceImpl(customerTenancyRepository, customerTenancyCustomRepo());
	}

	@Bean 
	public CustomerTenancyReportingService customerTenancyReportingService(){
		return new CustomerTenancyReportingServiceImpl();
	}

	@Bean 
	public CurrencyCodePersistenceService currencyCodePersistenceService(){
		return new CurrencyCodePersistenceServiceImpl(currencyCodeRepository, currencyCodeCustomRepo());
	}

	@Bean 
	public CurrencyCodeReportingService currencyCodeReportingService(){
		return new CurrencyCodeReportingServiceImpl();
	}

	@Bean 
	public CountryPersistenceService countryPersistenceService(){
		return new CountryPersistenceServiceImpl(countryRepository, countryCustomRepo());
	}

	@Bean 
	public CountryReportingService countryReportingService(){
		return new CountryReportingServiceImpl();
	}


}