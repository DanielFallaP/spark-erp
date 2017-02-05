package co.com.cybersoft.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

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
@EnableJpaRepositories(basePackages={"co.com.cybersoft.maintenance.tables.persistence.repository","co.com.cybersoft.purchase.tables.persistence.repository"},
includeFilters=@ComponentScan.Filter(value={CompanyRepository.class,StateRepository.class,MeasurementUnitRepository.class,TypeSorterRepository.class,SorterRepository.class,OperationRepository.class,ReferenceRepository.class,ReferenceOperationRepository.class,TechnicalActionRepository.class,EffectFailRepository.class,EffectFailTechnicalActionRepository.class,FailureCauseRepository.class,FailureCauseTechnicalactionRepository.class,TypeCauseCloseRepository.class,CauseCloseRepository.class,MaintenanceActivityRepository.class,MaintenanceWorkRepository.class,TypeMaintenanceRepository.class,CausePendingRepository.class,TypeWorkRepository.class,OtherConceptsRepository.class,StateCostCentersRepository.class,CostCentersCustomersRepository.class,ResponsibleCenterRepository.class,JobRepository.class,ThirdRepository.class,JobThirdRepository.class,ResponsibleRepository.class,AuthorizerCostCenterRepository.class,CharacteristicRepository.class,AccountantRepository.class,PhysicalLocationRepository.class,StoreTypeRepository.class,CostingTypeRepository.class,WarehouseRepository.class,CoinRepository.class,TypeConceptKardexRepository.class,ConceptKardexRepository.class,ContractRepository.class,UsersRepository.class,CurrencyRepository.class,ExchangeRateRepository.class,ContinentRepository.class,RegionRepository.class,CustomerTenancyRepository.class,CurrencyCodeRepository.class,CountryRepository.class},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "co.com.cybersoft" });
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		
		return em;
	} 
	
	@Bean
    public DataSource dataSource(){
		DBConfig dbConfig = getDBConfig();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbConfig.getDriverClass());
		dataSource.setUsername(dbConfig.getUsername());
		dataSource.setPassword(dbConfig.getPassword());
		dataSource.setUrl(dbConfig.getAddress());
		return dataSource;
    }

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
	private DBConfig getDBConfig(){
		InputStream stream = getClass().getClassLoader().getResourceAsStream("db.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			DBConfig dbConfig = (DBConfig) mapper.readValue(new InputStreamReader(stream, "UTF8"), DBConfig.class);
			return dbConfig;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}

	
	Properties additionalProperties() {
		  DBConfig dbConfig = getDBConfig();

	      Properties properties = new Properties();
	      properties.setProperty("hibernate.hbm2ddl.auto", "update");
	      properties.setProperty("hibernate.dialect", dbConfig.getDialect());
	      return properties;
   }
	
	@Bean
    public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
	
}