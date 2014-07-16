package co.com.cybersoft.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.sql.DataSource;

import co.com.cybersoft.docs.persistence.repository.quotation.QuotationCustomRepo;
import co.com.cybersoft.docs.persistence.repository.quotation.QuotationCustomRepoImpl;
import co.com.cybersoft.docs.persistence.repository.quotation.QuotationRepository;
import co.com.cybersoft.docs.persistence.services.quotation.QuotationPersistenceService;
import co.com.cybersoft.docs.persistence.services.quotation.QuotationPersistenceServiceImpl;
import co.com.cybersoft.docsTest.persistence.repository.requisition.RequisitionCustomRepo;
import co.com.cybersoft.docsTest.persistence.repository.requisition.RequisitionCustomRepoImpl;
import co.com.cybersoft.docsTest.persistence.repository.requisition.RequisitionRepository;
import co.com.cybersoft.docsTest.persistence.services.requisition.RequisitionPersistenceService;
import co.com.cybersoft.docsTest.persistence.services.requisition.RequisitionPersistenceServiceImpl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;

import co.com.cybersoft.tables.persistence.repository.currency.CurrencyRepository;
import co.com.cybersoft.tables.persistence.repository.currency.CurrencyCustomRepo;
import co.com.cybersoft.tables.persistence.repository.currency.CurrencyCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.currency.CurrencyPersistenceService;
import co.com.cybersoft.tables.persistence.services.currency.CurrencyPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.tables.persistence.repository.exchangeRate.ExchangeRateCustomRepo;
import co.com.cybersoft.tables.persistence.repository.exchangeRate.ExchangeRateCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.exchangeRate.ExchangeRatePersistenceService;
import co.com.cybersoft.tables.persistence.services.exchangeRate.ExchangeRatePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.continent.ContinentRepository;
import co.com.cybersoft.tables.persistence.repository.continent.ContinentCustomRepo;
import co.com.cybersoft.tables.persistence.repository.continent.ContinentCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.continent.ContinentPersistenceService;
import co.com.cybersoft.tables.persistence.services.continent.ContinentPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.region.RegionRepository;
import co.com.cybersoft.tables.persistence.repository.region.RegionCustomRepo;
import co.com.cybersoft.tables.persistence.repository.region.RegionCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.region.RegionPersistenceService;
import co.com.cybersoft.tables.persistence.services.region.RegionPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.country.CountryRepository;
import co.com.cybersoft.tables.persistence.repository.country.CountryCustomRepo;
import co.com.cybersoft.tables.persistence.repository.country.CountryCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.country.CountryPersistenceService;
import co.com.cybersoft.tables.persistence.services.country.CountryPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.state.StateRepository;
import co.com.cybersoft.tables.persistence.repository.state.StateCustomRepo;
import co.com.cybersoft.tables.persistence.repository.state.StateCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.state.StatePersistenceService;
import co.com.cybersoft.tables.persistence.services.state.StatePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.populatedPlace.PopulatedPlaceRepository;
import co.com.cybersoft.tables.persistence.repository.populatedPlace.PopulatedPlaceCustomRepo;
import co.com.cybersoft.tables.persistence.repository.populatedPlace.PopulatedPlaceCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.populatedPlace.PopulatedPlacePersistenceService;
import co.com.cybersoft.tables.persistence.services.populatedPlace.PopulatedPlacePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.docSite.DocSiteRepository;
import co.com.cybersoft.tables.persistence.repository.docSite.DocSiteCustomRepo;
import co.com.cybersoft.tables.persistence.repository.docSite.DocSiteCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.docSite.DocSitePersistenceService;
import co.com.cybersoft.tables.persistence.services.docSite.DocSitePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.balanceGroup.BalanceGroupRepository;
import co.com.cybersoft.tables.persistence.repository.balanceGroup.BalanceGroupCustomRepo;
import co.com.cybersoft.tables.persistence.repository.balanceGroup.BalanceGroupCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.balanceGroup.BalanceGroupPersistenceService;
import co.com.cybersoft.tables.persistence.services.balanceGroup.BalanceGroupPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.accountNature.AccountNatureRepository;
import co.com.cybersoft.tables.persistence.repository.accountNature.AccountNatureCustomRepo;
import co.com.cybersoft.tables.persistence.repository.accountNature.AccountNatureCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.accountNature.AccountNaturePersistenceService;
import co.com.cybersoft.tables.persistence.services.accountNature.AccountNaturePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.localAccountTemplate.LocalAccountTemplateRepository;
import co.com.cybersoft.tables.persistence.repository.localAccountTemplate.LocalAccountTemplateCustomRepo;
import co.com.cybersoft.tables.persistence.repository.localAccountTemplate.LocalAccountTemplateCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.localAccountTemplate.LocalAccountTemplatePersistenceService;
import co.com.cybersoft.tables.persistence.services.localAccountTemplate.LocalAccountTemplatePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.localAccountLevel1.LocalAccountLevel1Repository;
import co.com.cybersoft.tables.persistence.repository.localAccountLevel1.LocalAccountLevel1CustomRepo;
import co.com.cybersoft.tables.persistence.repository.localAccountLevel1.LocalAccountLevel1CustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.localAccountLevel1.LocalAccountLevel1PersistenceService;
import co.com.cybersoft.tables.persistence.services.localAccountLevel1.LocalAccountLevel1PersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.localAccountChart.LocalAccountChartRepository;
import co.com.cybersoft.tables.persistence.repository.localAccountChart.LocalAccountChartCustomRepo;
import co.com.cybersoft.tables.persistence.repository.localAccountChart.LocalAccountChartCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.localAccountChart.LocalAccountChartPersistenceService;
import co.com.cybersoft.tables.persistence.services.localAccountChart.LocalAccountChartPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.account.AccountRepository;
import co.com.cybersoft.tables.persistence.repository.account.AccountCustomRepo;
import co.com.cybersoft.tables.persistence.repository.account.AccountCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.account.AccountPersistenceService;
import co.com.cybersoft.tables.persistence.services.account.AccountPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.accountConcept.AccountConceptRepository;
import co.com.cybersoft.tables.persistence.repository.accountConcept.AccountConceptCustomRepo;
import co.com.cybersoft.tables.persistence.repository.accountConcept.AccountConceptCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.accountConcept.AccountConceptPersistenceService;
import co.com.cybersoft.tables.persistence.services.accountConcept.AccountConceptPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.inventoryAccount.InventoryAccountRepository;
import co.com.cybersoft.tables.persistence.repository.inventoryAccount.InventoryAccountCustomRepo;
import co.com.cybersoft.tables.persistence.repository.inventoryAccount.InventoryAccountCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.inventoryAccount.InventoryAccountPersistenceService;
import co.com.cybersoft.tables.persistence.services.inventoryAccount.InventoryAccountPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.taxRegime.TaxRegimeRepository;
import co.com.cybersoft.tables.persistence.repository.taxRegime.TaxRegimeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.taxRegime.TaxRegimeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.taxRegime.TaxRegimePersistenceService;
import co.com.cybersoft.tables.persistence.services.taxRegime.TaxRegimePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.withholding.WithholdingRepository;
import co.com.cybersoft.tables.persistence.repository.withholding.WithholdingCustomRepo;
import co.com.cybersoft.tables.persistence.repository.withholding.WithholdingCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.withholding.WithholdingPersistenceService;
import co.com.cybersoft.tables.persistence.services.withholding.WithholdingPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.tax.TaxRepository;
import co.com.cybersoft.tables.persistence.repository.tax.TaxCustomRepo;
import co.com.cybersoft.tables.persistence.repository.tax.TaxCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.tax.TaxPersistenceService;
import co.com.cybersoft.tables.persistence.services.tax.TaxPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.commerceWithholding.CommerceWithholdingRepository;
import co.com.cybersoft.tables.persistence.repository.commerceWithholding.CommerceWithholdingCustomRepo;
import co.com.cybersoft.tables.persistence.repository.commerceWithholding.CommerceWithholdingCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.commerceWithholding.CommerceWithholdingPersistenceService;
import co.com.cybersoft.tables.persistence.services.commerceWithholding.CommerceWithholdingPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.consignmentWithholding.ConsignmentWithholdingRepository;
import co.com.cybersoft.tables.persistence.repository.consignmentWithholding.ConsignmentWithholdingCustomRepo;
import co.com.cybersoft.tables.persistence.repository.consignmentWithholding.ConsignmentWithholdingCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.consignmentWithholding.ConsignmentWithholdingPersistenceService;
import co.com.cybersoft.tables.persistence.services.consignmentWithholding.ConsignmentWithholdingPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.stampDuty.StampDutyRepository;
import co.com.cybersoft.tables.persistence.repository.stampDuty.StampDutyCustomRepo;
import co.com.cybersoft.tables.persistence.repository.stampDuty.StampDutyCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.stampDuty.StampDutyPersistenceService;
import co.com.cybersoft.tables.persistence.services.stampDuty.StampDutyPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.thirdParty.ThirdPartyRepository;
import co.com.cybersoft.tables.persistence.repository.thirdParty.ThirdPartyCustomRepo;
import co.com.cybersoft.tables.persistence.repository.thirdParty.ThirdPartyCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.thirdParty.ThirdPartyPersistenceService;
import co.com.cybersoft.tables.persistence.services.thirdParty.ThirdPartyPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.thirdPartyType.ThirdPartyTypeRepository;
import co.com.cybersoft.tables.persistence.repository.thirdPartyType.ThirdPartyTypeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.thirdPartyType.ThirdPartyTypeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.thirdPartyType.ThirdPartyTypePersistenceService;
import co.com.cybersoft.tables.persistence.services.thirdPartyType.ThirdPartyTypePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.thirdPartyClass.ThirdPartyClassRepository;
import co.com.cybersoft.tables.persistence.repository.thirdPartyClass.ThirdPartyClassCustomRepo;
import co.com.cybersoft.tables.persistence.repository.thirdPartyClass.ThirdPartyClassCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.thirdPartyClass.ThirdPartyClassPersistenceService;
import co.com.cybersoft.tables.persistence.services.thirdPartyClass.ThirdPartyClassPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.partner.PartnerRepository;
import co.com.cybersoft.tables.persistence.repository.partner.PartnerCustomRepo;
import co.com.cybersoft.tables.persistence.repository.partner.PartnerCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.partner.PartnerPersistenceService;
import co.com.cybersoft.tables.persistence.services.partner.PartnerPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.operatingSystem.OperatingSystemRepository;
import co.com.cybersoft.tables.persistence.repository.operatingSystem.OperatingSystemCustomRepo;
import co.com.cybersoft.tables.persistence.repository.operatingSystem.OperatingSystemCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.operatingSystem.OperatingSystemPersistenceService;
import co.com.cybersoft.tables.persistence.services.operatingSystem.OperatingSystemPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.tables.persistence.repository.customerTenancy.CustomerTenancyCustomRepo;
import co.com.cybersoft.tables.persistence.repository.customerTenancy.CustomerTenancyCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.customerTenancy.CustomerTenancyPersistenceService;
import co.com.cybersoft.tables.persistence.services.customerTenancy.CustomerTenancyPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.corporation.CorporationRepository;
import co.com.cybersoft.tables.persistence.repository.corporation.CorporationCustomRepo;
import co.com.cybersoft.tables.persistence.repository.corporation.CorporationCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.corporation.CorporationPersistenceService;
import co.com.cybersoft.tables.persistence.services.corporation.CorporationPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.company.CompanyRepository;
import co.com.cybersoft.tables.persistence.repository.company.CompanyCustomRepo;
import co.com.cybersoft.tables.persistence.repository.company.CompanyCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.company.CompanyPersistenceService;
import co.com.cybersoft.tables.persistence.services.company.CompanyPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.branch.BranchRepository;
import co.com.cybersoft.tables.persistence.repository.branch.BranchCustomRepo;
import co.com.cybersoft.tables.persistence.repository.branch.BranchCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.branch.BranchPersistenceService;
import co.com.cybersoft.tables.persistence.services.branch.BranchPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.department.DepartmentRepository;
import co.com.cybersoft.tables.persistence.repository.department.DepartmentCustomRepo;
import co.com.cybersoft.tables.persistence.repository.department.DepartmentCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.department.DepartmentPersistenceService;
import co.com.cybersoft.tables.persistence.services.department.DepartmentPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.section.SectionRepository;
import co.com.cybersoft.tables.persistence.repository.section.SectionCustomRepo;
import co.com.cybersoft.tables.persistence.repository.section.SectionCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.section.SectionPersistenceService;
import co.com.cybersoft.tables.persistence.services.section.SectionPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.transportationType.TransportationTypeRepository;
import co.com.cybersoft.tables.persistence.repository.transportationType.TransportationTypeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.transportationType.TransportationTypeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.transportationType.TransportationTypePersistenceService;
import co.com.cybersoft.tables.persistence.services.transportationType.TransportationTypePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.priority.PriorityRepository;
import co.com.cybersoft.tables.persistence.repository.priority.PriorityCustomRepo;
import co.com.cybersoft.tables.persistence.repository.priority.PriorityCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.priority.PriorityPersistenceService;
import co.com.cybersoft.tables.persistence.services.priority.PriorityPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.doc.DocRepository;
import co.com.cybersoft.tables.persistence.repository.doc.DocCustomRepo;
import co.com.cybersoft.tables.persistence.repository.doc.DocCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.doc.DocPersistenceService;
import co.com.cybersoft.tables.persistence.services.doc.DocPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.stockTransaction.StockTransactionRepository;
import co.com.cybersoft.tables.persistence.repository.stockTransaction.StockTransactionCustomRepo;
import co.com.cybersoft.tables.persistence.repository.stockTransaction.StockTransactionCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.stockTransaction.StockTransactionPersistenceService;
import co.com.cybersoft.tables.persistence.services.stockTransaction.StockTransactionPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.contract.ContractRepository;
import co.com.cybersoft.tables.persistence.repository.contract.ContractCustomRepo;
import co.com.cybersoft.tables.persistence.repository.contract.ContractCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.contract.ContractPersistenceService;
import co.com.cybersoft.tables.persistence.services.contract.ContractPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.field.FieldRepository;
import co.com.cybersoft.tables.persistence.repository.field.FieldCustomRepo;
import co.com.cybersoft.tables.persistence.repository.field.FieldCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.field.FieldPersistenceService;
import co.com.cybersoft.tables.persistence.services.field.FieldPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.oilWell.OilWellRepository;
import co.com.cybersoft.tables.persistence.repository.oilWell.OilWellCustomRepo;
import co.com.cybersoft.tables.persistence.repository.oilWell.OilWellCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.oilWell.OilWellPersistenceService;
import co.com.cybersoft.tables.persistence.services.oilWell.OilWellPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.itemCondition.ItemConditionRepository;
import co.com.cybersoft.tables.persistence.repository.itemCondition.ItemConditionCustomRepo;
import co.com.cybersoft.tables.persistence.repository.itemCondition.ItemConditionCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.itemCondition.ItemConditionPersistenceService;
import co.com.cybersoft.tables.persistence.services.itemCondition.ItemConditionPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.billingCode.BillingCodeRepository;
import co.com.cybersoft.tables.persistence.repository.billingCode.BillingCodeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.billingCode.BillingCodeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.billingCode.BillingCodePersistenceService;
import co.com.cybersoft.tables.persistence.services.billingCode.BillingCodePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.jointVenture.JointVentureRepository;
import co.com.cybersoft.tables.persistence.repository.jointVenture.JointVentureCustomRepo;
import co.com.cybersoft.tables.persistence.repository.jointVenture.JointVentureCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.jointVenture.JointVenturePersistenceService;
import co.com.cybersoft.tables.persistence.services.jointVenture.JointVenturePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.afeType.AfeTypeRepository;
import co.com.cybersoft.tables.persistence.repository.afeType.AfeTypeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.afeType.AfeTypeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.afeType.AfeTypePersistenceService;
import co.com.cybersoft.tables.persistence.services.afeType.AfeTypePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.afe.AfeRepository;
import co.com.cybersoft.tables.persistence.repository.afe.AfeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.afe.AfeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.afe.AfePersistenceService;
import co.com.cybersoft.tables.persistence.services.afe.AfePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.afeBudget.AfeBudgetRepository;
import co.com.cybersoft.tables.persistence.repository.afeBudget.AfeBudgetCustomRepo;
import co.com.cybersoft.tables.persistence.repository.afeBudget.AfeBudgetCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.afeBudget.AfeBudgetPersistenceService;
import co.com.cybersoft.tables.persistence.services.afeBudget.AfeBudgetPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.costCenter.CostCenterRepository;
import co.com.cybersoft.tables.persistence.repository.costCenter.CostCenterCustomRepo;
import co.com.cybersoft.tables.persistence.repository.costCenter.CostCenterCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.costCenter.CostCenterPersistenceService;
import co.com.cybersoft.tables.persistence.services.costCenter.CostCenterPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.measurementUnit.MeasurementUnitRepository;
import co.com.cybersoft.tables.persistence.repository.measurementUnit.MeasurementUnitCustomRepo;
import co.com.cybersoft.tables.persistence.repository.measurementUnit.MeasurementUnitCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.tables.persistence.services.measurementUnit.MeasurementUnitPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.stockGroup.StockGroupRepository;
import co.com.cybersoft.tables.persistence.repository.stockGroup.StockGroupCustomRepo;
import co.com.cybersoft.tables.persistence.repository.stockGroup.StockGroupCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.stockGroup.StockGroupPersistenceService;
import co.com.cybersoft.tables.persistence.services.stockGroup.StockGroupPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.stockSubGroup.StockSubGroupRepository;
import co.com.cybersoft.tables.persistence.repository.stockSubGroup.StockSubGroupCustomRepo;
import co.com.cybersoft.tables.persistence.repository.stockSubGroup.StockSubGroupCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.stockSubGroup.StockSubGroupPersistenceService;
import co.com.cybersoft.tables.persistence.services.stockSubGroup.StockSubGroupPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.division.DivisionRepository;
import co.com.cybersoft.tables.persistence.repository.division.DivisionCustomRepo;
import co.com.cybersoft.tables.persistence.repository.division.DivisionCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.division.DivisionPersistenceService;
import co.com.cybersoft.tables.persistence.services.division.DivisionPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.warehouse.WarehouseRepository;
import co.com.cybersoft.tables.persistence.repository.warehouse.WarehouseCustomRepo;
import co.com.cybersoft.tables.persistence.repository.warehouse.WarehouseCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.warehouse.WarehousePersistenceService;
import co.com.cybersoft.tables.persistence.services.warehouse.WarehousePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.businessRules.BusinessRulesRepository;
import co.com.cybersoft.tables.persistence.repository.businessRules.BusinessRulesCustomRepo;
import co.com.cybersoft.tables.persistence.repository.businessRules.BusinessRulesCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.businessRules.BusinessRulesPersistenceService;
import co.com.cybersoft.tables.persistence.services.businessRules.BusinessRulesPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.module.ModuleRepository;
import co.com.cybersoft.tables.persistence.repository.module.ModuleCustomRepo;
import co.com.cybersoft.tables.persistence.repository.module.ModuleCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.module.ModulePersistenceService;
import co.com.cybersoft.tables.persistence.services.module.ModulePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.periodType.PeriodTypeRepository;
import co.com.cybersoft.tables.persistence.repository.periodType.PeriodTypeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.periodType.PeriodTypeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.periodType.PeriodTypePersistenceService;
import co.com.cybersoft.tables.persistence.services.periodType.PeriodTypePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.period.PeriodRepository;
import co.com.cybersoft.tables.persistence.repository.period.PeriodCustomRepo;
import co.com.cybersoft.tables.persistence.repository.period.PeriodCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.period.PeriodPersistenceService;
import co.com.cybersoft.tables.persistence.services.period.PeriodPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.quality.QualityRepository;
import co.com.cybersoft.tables.persistence.repository.quality.QualityCustomRepo;
import co.com.cybersoft.tables.persistence.repository.quality.QualityCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.quality.QualityPersistenceService;
import co.com.cybersoft.tables.persistence.services.quality.QualityPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.expenseType.ExpenseTypeRepository;
import co.com.cybersoft.tables.persistence.repository.expenseType.ExpenseTypeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.expenseType.ExpenseTypeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.expenseType.ExpenseTypePersistenceService;
import co.com.cybersoft.tables.persistence.services.expenseType.ExpenseTypePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.holiday.HolidayRepository;
import co.com.cybersoft.tables.persistence.repository.holiday.HolidayCustomRepo;
import co.com.cybersoft.tables.persistence.repository.holiday.HolidayCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.holiday.HolidayPersistenceService;
import co.com.cybersoft.tables.persistence.services.holiday.HolidayPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.methodOfPayment.MethodOfPaymentRepository;
import co.com.cybersoft.tables.persistence.repository.methodOfPayment.MethodOfPaymentCustomRepo;
import co.com.cybersoft.tables.persistence.repository.methodOfPayment.MethodOfPaymentCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.methodOfPayment.MethodOfPaymentPersistenceService;
import co.com.cybersoft.tables.persistence.services.methodOfPayment.MethodOfPaymentPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.supplierSelectionReason.SupplierSelectionReasonRepository;
import co.com.cybersoft.tables.persistence.repository.supplierSelectionReason.SupplierSelectionReasonCustomRepo;
import co.com.cybersoft.tables.persistence.repository.supplierSelectionReason.SupplierSelectionReasonCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.supplierSelectionReason.SupplierSelectionReasonPersistenceService;
import co.com.cybersoft.tables.persistence.services.supplierSelectionReason.SupplierSelectionReasonPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.deliveryLocation.DeliveryLocationRepository;
import co.com.cybersoft.tables.persistence.repository.deliveryLocation.DeliveryLocationCustomRepo;
import co.com.cybersoft.tables.persistence.repository.deliveryLocation.DeliveryLocationCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.deliveryLocation.DeliveryLocationPersistenceService;
import co.com.cybersoft.tables.persistence.services.deliveryLocation.DeliveryLocationPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.person.PersonRepository;
import co.com.cybersoft.tables.persistence.repository.person.PersonCustomRepo;
import co.com.cybersoft.tables.persistence.repository.person.PersonCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.person.PersonPersistenceService;
import co.com.cybersoft.tables.persistence.services.person.PersonPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.itemType.ItemTypeRepository;
import co.com.cybersoft.tables.persistence.repository.itemType.ItemTypeCustomRepo;
import co.com.cybersoft.tables.persistence.repository.itemType.ItemTypeCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.itemType.ItemTypePersistenceService;
import co.com.cybersoft.tables.persistence.services.itemType.ItemTypePersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.item.ItemRepository;
import co.com.cybersoft.tables.persistence.repository.item.ItemCustomRepo;
import co.com.cybersoft.tables.persistence.repository.item.ItemCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.item.ItemPersistenceService;
import co.com.cybersoft.tables.persistence.services.item.ItemPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.itemTradeMark.ItemTradeMarkRepository;
import co.com.cybersoft.tables.persistence.repository.itemTradeMark.ItemTradeMarkCustomRepo;
import co.com.cybersoft.tables.persistence.repository.itemTradeMark.ItemTradeMarkCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.itemTradeMark.ItemTradeMarkPersistenceService;
import co.com.cybersoft.tables.persistence.services.itemTradeMark.ItemTradeMarkPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.miniMax.MiniMaxRepository;
import co.com.cybersoft.tables.persistence.repository.miniMax.MiniMaxCustomRepo;
import co.com.cybersoft.tables.persistence.repository.miniMax.MiniMaxCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.miniMax.MiniMaxPersistenceService;
import co.com.cybersoft.tables.persistence.services.miniMax.MiniMaxPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.itemPurchaseHistory.ItemPurchaseHistoryRepository;
import co.com.cybersoft.tables.persistence.repository.itemPurchaseHistory.ItemPurchaseHistoryCustomRepo;
import co.com.cybersoft.tables.persistence.repository.itemPurchaseHistory.ItemPurchaseHistoryCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.itemPurchaseHistory.ItemPurchaseHistoryPersistenceService;
import co.com.cybersoft.tables.persistence.services.itemPurchaseHistory.ItemPurchaseHistoryPersistenceServiceImpl;
import co.com.cybersoft.tables.persistence.repository.otherPurchaseValue.OtherPurchaseValueRepository;
import co.com.cybersoft.tables.persistence.repository.otherPurchaseValue.OtherPurchaseValueCustomRepo;
import co.com.cybersoft.tables.persistence.repository.otherPurchaseValue.OtherPurchaseValueCustomRepoImpl;
import co.com.cybersoft.tables.persistence.services.otherPurchaseValue.OtherPurchaseValuePersistenceService;
import co.com.cybersoft.tables.persistence.services.otherPurchaseValue.OtherPurchaseValuePersistenceServiceImpl;








import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;


@Configuration
@EnableMongoRepositories(basePackages={"co.com.cybersoft.tables.persistence.repository","co.com.cybersoft.docs.persistence.repository","co.com.cybersoft.docsTest.persistence.repository"},
includeFilters=@ComponentScan.Filter(value={RequisitionRepository.class,CurrencyRepository.class,ExchangeRateRepository.class,ContinentRepository.class,RegionRepository.class,CountryRepository.class,StateRepository.class,PopulatedPlaceRepository.class,DocSiteRepository.class,BalanceGroupRepository.class,AccountNatureRepository.class,LocalAccountTemplateRepository.class,LocalAccountLevel1Repository.class,LocalAccountChartRepository.class,AccountRepository.class,AccountConceptRepository.class,InventoryAccountRepository.class,TaxRegimeRepository.class,WithholdingRepository.class,TaxRepository.class,CommerceWithholdingRepository.class,ConsignmentWithholdingRepository.class,StampDutyRepository.class,ThirdPartyRepository.class,ThirdPartyTypeRepository.class,ThirdPartyClassRepository.class,PartnerRepository.class,OperatingSystemRepository.class,CustomerTenancyRepository.class,CorporationRepository.class,CompanyRepository.class,BranchRepository.class,DepartmentRepository.class,SectionRepository.class,TransportationTypeRepository.class,PriorityRepository.class,DocRepository.class,StockTransactionRepository.class,ContractRepository.class,FieldRepository.class,OilWellRepository.class,ItemConditionRepository.class,BillingCodeRepository.class,JointVentureRepository.class,AfeTypeRepository.class,AfeRepository.class,AfeBudgetRepository.class,CostCenterRepository.class,MeasurementUnitRepository.class,StockGroupRepository.class,StockSubGroupRepository.class,DivisionRepository.class,WarehouseRepository.class,BusinessRulesRepository.class,ModuleRepository.class,PeriodTypeRepository.class,PeriodRepository.class,QualityRepository.class,ExpenseTypeRepository.class,HolidayRepository.class,MethodOfPaymentRepository.class,SupplierSelectionReasonRepository.class,DeliveryLocationRepository.class,PersonRepository.class,ItemTypeRepository.class,ItemRepository.class,ItemTradeMarkRepository.class,MiniMaxRepository.class,ItemPurchaseHistoryRepository.class,OtherPurchaseValueRepository.class,QuotationRepository.class},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException{
		return new MongoTemplate(mongo,"cybersoft");
	}
	
	@Bean
	public DB DB() throws UnknownHostException{
		//Mongo setup. For replica set configurations, it is necessary to supply
		//the seed members to auto-discover the primary instance
		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress(getDBAddress(),27017)));
		return mongoClient.getDB("cybersoft");
	}
	
	@Bean
	public  Mongo mongo() throws UnknownHostException{
		return new Mongo(getDBAddress());
	}
	
	private String getDBAddress(){
		InputStream stream = getClass().getClassLoader().getResourceAsStream("db.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			DBConfig dbConfig = (DBConfig) mapper.readValue(new InputStreamReader(stream, "UTF8"), DBConfig.class);
			return dbConfig.getAddress();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	@Autowired
	private RequisitionRepository requisitionRepository;
	
	@Bean
	public RequisitionCustomRepo requisitionCustomRepo(){
		return new RequisitionCustomRepoImpl();
	}
	
	@Autowired
	private QuotationRepository quotationRepository;
	
	@Bean
	public QuotationCustomRepo quotationCustomRepo(){
		return new QuotationCustomRepoImpl();
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
	private CountryRepository countryRepository;

	@Bean
	public CountryCustomRepo countryCustomRepo(){
		return new CountryCustomRepoImpl();
	}

	@Autowired
	private StateRepository stateRepository;

	@Bean
	public StateCustomRepo stateCustomRepo(){
		return new StateCustomRepoImpl();
	}

	@Autowired
	private PopulatedPlaceRepository populatedPlaceRepository;

	@Bean
	public PopulatedPlaceCustomRepo populatedPlaceCustomRepo(){
		return new PopulatedPlaceCustomRepoImpl();
	}

	@Autowired
	private DocSiteRepository docSiteRepository;

	@Bean
	public DocSiteCustomRepo docSiteCustomRepo(){
		return new DocSiteCustomRepoImpl();
	}

	@Autowired
	private BalanceGroupRepository balanceGroupRepository;

	@Bean
	public BalanceGroupCustomRepo balanceGroupCustomRepo(){
		return new BalanceGroupCustomRepoImpl();
	}

	@Autowired
	private AccountNatureRepository accountNatureRepository;

	@Bean
	public AccountNatureCustomRepo accountNatureCustomRepo(){
		return new AccountNatureCustomRepoImpl();
	}

	@Autowired
	private LocalAccountTemplateRepository localAccountTemplateRepository;

	@Bean
	public LocalAccountTemplateCustomRepo localAccountTemplateCustomRepo(){
		return new LocalAccountTemplateCustomRepoImpl();
	}

	@Autowired
	private LocalAccountLevel1Repository localAccountLevel1Repository;

	@Bean
	public LocalAccountLevel1CustomRepo localAccountLevel1CustomRepo(){
		return new LocalAccountLevel1CustomRepoImpl();
	}

	@Autowired
	private LocalAccountChartRepository localAccountChartRepository;

	@Bean
	public LocalAccountChartCustomRepo localAccountChartCustomRepo(){
		return new LocalAccountChartCustomRepoImpl();
	}

	@Autowired
	private AccountRepository accountRepository;

	@Bean
	public AccountCustomRepo accountCustomRepo(){
		return new AccountCustomRepoImpl();
	}

	@Autowired
	private AccountConceptRepository accountConceptRepository;

	@Bean
	public AccountConceptCustomRepo accountConceptCustomRepo(){
		return new AccountConceptCustomRepoImpl();
	}

	@Autowired
	private InventoryAccountRepository inventoryAccountRepository;

	@Bean
	public InventoryAccountCustomRepo inventoryAccountCustomRepo(){
		return new InventoryAccountCustomRepoImpl();
	}

	@Autowired
	private TaxRegimeRepository taxRegimeRepository;

	@Bean
	public TaxRegimeCustomRepo taxRegimeCustomRepo(){
		return new TaxRegimeCustomRepoImpl();
	}

	@Autowired
	private WithholdingRepository withholdingRepository;

	@Bean
	public WithholdingCustomRepo withholdingCustomRepo(){
		return new WithholdingCustomRepoImpl();
	}

	@Autowired
	private TaxRepository taxRepository;

	@Bean
	public TaxCustomRepo taxCustomRepo(){
		return new TaxCustomRepoImpl();
	}

	@Autowired
	private CommerceWithholdingRepository commerceWithholdingRepository;

	@Bean
	public CommerceWithholdingCustomRepo commerceWithholdingCustomRepo(){
		return new CommerceWithholdingCustomRepoImpl();
	}

	@Autowired
	private ConsignmentWithholdingRepository consignmentWithholdingRepository;

	@Bean
	public ConsignmentWithholdingCustomRepo consignmentWithholdingCustomRepo(){
		return new ConsignmentWithholdingCustomRepoImpl();
	}

	@Autowired
	private StampDutyRepository stampDutyRepository;

	@Bean
	public StampDutyCustomRepo stampDutyCustomRepo(){
		return new StampDutyCustomRepoImpl();
	}

	@Autowired
	private ThirdPartyRepository thirdPartyRepository;

	@Bean
	public ThirdPartyCustomRepo thirdPartyCustomRepo(){
		return new ThirdPartyCustomRepoImpl();
	}

	@Autowired
	private ThirdPartyTypeRepository thirdPartyTypeRepository;

	@Bean
	public ThirdPartyTypeCustomRepo thirdPartyTypeCustomRepo(){
		return new ThirdPartyTypeCustomRepoImpl();
	}

	@Autowired
	private ThirdPartyClassRepository thirdPartyClassRepository;

	@Bean
	public ThirdPartyClassCustomRepo thirdPartyClassCustomRepo(){
		return new ThirdPartyClassCustomRepoImpl();
	}

	@Autowired
	private PartnerRepository partnerRepository;

	@Bean
	public PartnerCustomRepo partnerCustomRepo(){
		return new PartnerCustomRepoImpl();
	}

	@Autowired
	private OperatingSystemRepository operatingSystemRepository;

	@Bean
	public OperatingSystemCustomRepo operatingSystemCustomRepo(){
		return new OperatingSystemCustomRepoImpl();
	}

	@Autowired
	private CustomerTenancyRepository customerTenancyRepository;

	@Bean
	public CustomerTenancyCustomRepo customerTenancyCustomRepo(){
		return new CustomerTenancyCustomRepoImpl();
	}

	@Autowired
	private CorporationRepository corporationRepository;

	@Bean
	public CorporationCustomRepo corporationCustomRepo(){
		return new CorporationCustomRepoImpl();
	}

	@Autowired
	private CompanyRepository companyRepository;

	@Bean
	public CompanyCustomRepo companyCustomRepo(){
		return new CompanyCustomRepoImpl();
	}

	@Autowired
	private BranchRepository branchRepository;

	@Bean
	public BranchCustomRepo branchCustomRepo(){
		return new BranchCustomRepoImpl();
	}

	@Autowired
	private DepartmentRepository departmentRepository;

	@Bean
	public DepartmentCustomRepo departmentCustomRepo(){
		return new DepartmentCustomRepoImpl();
	}

	@Autowired
	private SectionRepository sectionRepository;

	@Bean
	public SectionCustomRepo sectionCustomRepo(){
		return new SectionCustomRepoImpl();
	}

	@Autowired
	private TransportationTypeRepository transportationTypeRepository;

	@Bean
	public TransportationTypeCustomRepo transportationTypeCustomRepo(){
		return new TransportationTypeCustomRepoImpl();
	}

	@Autowired
	private PriorityRepository priorityRepository;

	@Bean
	public PriorityCustomRepo priorityCustomRepo(){
		return new PriorityCustomRepoImpl();
	}

	@Autowired
	private DocRepository docRepository;

	@Bean
	public DocCustomRepo docCustomRepo(){
		return new DocCustomRepoImpl();
	}

	@Autowired
	private StockTransactionRepository stockTransactionRepository;

	@Bean
	public StockTransactionCustomRepo stockTransactionCustomRepo(){
		return new StockTransactionCustomRepoImpl();
	}

	@Autowired
	private ContractRepository contractRepository;

	@Bean
	public ContractCustomRepo contractCustomRepo(){
		return new ContractCustomRepoImpl();
	}

	@Autowired
	private FieldRepository fieldRepository;

	@Bean
	public FieldCustomRepo fieldCustomRepo(){
		return new FieldCustomRepoImpl();
	}

	@Autowired
	private OilWellRepository oilWellRepository;

	@Bean
	public OilWellCustomRepo oilWellCustomRepo(){
		return new OilWellCustomRepoImpl();
	}

	@Autowired
	private ItemConditionRepository itemConditionRepository;

	@Bean
	public ItemConditionCustomRepo itemConditionCustomRepo(){
		return new ItemConditionCustomRepoImpl();
	}

	@Autowired
	private BillingCodeRepository billingCodeRepository;

	@Bean
	public BillingCodeCustomRepo billingCodeCustomRepo(){
		return new BillingCodeCustomRepoImpl();
	}

	@Autowired
	private JointVentureRepository jointVentureRepository;

	@Bean
	public JointVentureCustomRepo jointVentureCustomRepo(){
		return new JointVentureCustomRepoImpl();
	}

	@Autowired
	private AfeTypeRepository afeTypeRepository;

	@Bean
	public AfeTypeCustomRepo afeTypeCustomRepo(){
		return new AfeTypeCustomRepoImpl();
	}

	@Autowired
	private AfeRepository afeRepository;

	@Bean
	public AfeCustomRepo afeCustomRepo(){
		return new AfeCustomRepoImpl();
	}

	@Autowired
	private AfeBudgetRepository afeBudgetRepository;

	@Bean
	public AfeBudgetCustomRepo afeBudgetCustomRepo(){
		return new AfeBudgetCustomRepoImpl();
	}

	@Autowired
	private CostCenterRepository costCenterRepository;

	@Bean
	public CostCenterCustomRepo costCenterCustomRepo(){
		return new CostCenterCustomRepoImpl();
	}

	@Autowired
	private MeasurementUnitRepository measurementUnitRepository;

	@Bean
	public MeasurementUnitCustomRepo measurementUnitCustomRepo(){
		return new MeasurementUnitCustomRepoImpl();
	}

	@Autowired
	private StockGroupRepository stockGroupRepository;

	@Bean
	public StockGroupCustomRepo stockGroupCustomRepo(){
		return new StockGroupCustomRepoImpl();
	}

	@Autowired
	private StockSubGroupRepository stockSubGroupRepository;

	@Bean
	public StockSubGroupCustomRepo stockSubGroupCustomRepo(){
		return new StockSubGroupCustomRepoImpl();
	}

	@Autowired
	private DivisionRepository divisionRepository;

	@Bean
	public DivisionCustomRepo divisionCustomRepo(){
		return new DivisionCustomRepoImpl();
	}

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Bean
	public WarehouseCustomRepo warehouseCustomRepo(){
		return new WarehouseCustomRepoImpl();
	}

	@Autowired
	private BusinessRulesRepository businessRulesRepository;

	@Bean
	public BusinessRulesCustomRepo businessRulesCustomRepo(){
		return new BusinessRulesCustomRepoImpl();
	}

	@Autowired
	private ModuleRepository moduleRepository;

	@Bean
	public ModuleCustomRepo moduleCustomRepo(){
		return new ModuleCustomRepoImpl();
	}

	@Autowired
	private PeriodTypeRepository periodTypeRepository;

	@Bean
	public PeriodTypeCustomRepo periodTypeCustomRepo(){
		return new PeriodTypeCustomRepoImpl();
	}

	@Autowired
	private PeriodRepository periodRepository;

	@Bean
	public PeriodCustomRepo periodCustomRepo(){
		return new PeriodCustomRepoImpl();
	}

	@Autowired
	private QualityRepository qualityRepository;

	@Bean
	public QualityCustomRepo qualityCustomRepo(){
		return new QualityCustomRepoImpl();
	}

	@Autowired
	private ExpenseTypeRepository expenseTypeRepository;

	@Bean
	public ExpenseTypeCustomRepo expenseTypeCustomRepo(){
		return new ExpenseTypeCustomRepoImpl();
	}

	@Autowired
	private HolidayRepository holidayRepository;

	@Bean
	public HolidayCustomRepo holidayCustomRepo(){
		return new HolidayCustomRepoImpl();
	}

	@Autowired
	private MethodOfPaymentRepository methodOfPaymentRepository;

	@Bean
	public MethodOfPaymentCustomRepo methodOfPaymentCustomRepo(){
		return new MethodOfPaymentCustomRepoImpl();
	}

	@Autowired
	private SupplierSelectionReasonRepository supplierSelectionReasonRepository;

	@Bean
	public SupplierSelectionReasonCustomRepo supplierSelectionReasonCustomRepo(){
		return new SupplierSelectionReasonCustomRepoImpl();
	}

	@Autowired
	private DeliveryLocationRepository deliveryLocationRepository;

	@Bean
	public DeliveryLocationCustomRepo deliveryLocationCustomRepo(){
		return new DeliveryLocationCustomRepoImpl();
	}

	@Autowired
	private PersonRepository personRepository;

	@Bean
	public PersonCustomRepo personCustomRepo(){
		return new PersonCustomRepoImpl();
	}

	@Autowired
	private ItemTypeRepository itemTypeRepository;

	@Bean
	public ItemTypeCustomRepo itemTypeCustomRepo(){
		return new ItemTypeCustomRepoImpl();
	}

	@Autowired
	private ItemRepository itemRepository;

	@Bean
	public ItemCustomRepo itemCustomRepo(){
		return new ItemCustomRepoImpl();
	}

	@Autowired
	private ItemTradeMarkRepository itemTradeMarkRepository;

	@Bean
	public ItemTradeMarkCustomRepo itemTradeMarkCustomRepo(){
		return new ItemTradeMarkCustomRepoImpl();
	}

	@Autowired
	private MiniMaxRepository miniMaxRepository;

	@Bean
	public MiniMaxCustomRepo miniMaxCustomRepo(){
		return new MiniMaxCustomRepoImpl();
	}

	@Autowired
	private ItemPurchaseHistoryRepository itemPurchaseHistoryRepository;

	@Bean
	public ItemPurchaseHistoryCustomRepo itemPurchaseHistoryCustomRepo(){
		return new ItemPurchaseHistoryCustomRepoImpl();
	}

	@Autowired
	private OtherPurchaseValueRepository otherPurchaseValueRepository;

	@Bean
	public OtherPurchaseValueCustomRepo otherPurchaseValueCustomRepo(){
		return new OtherPurchaseValueCustomRepoImpl();
	}


	
	@Bean 
	public CurrencyPersistenceService currencyPersistenceService(){
		return new CurrencyPersistenceServiceImpl(currencyRepository, currencyCustomRepo());
	}

	@Bean 
	public ExchangeRatePersistenceService exchangeRatePersistenceService(){
		return new ExchangeRatePersistenceServiceImpl(exchangeRateRepository, exchangeRateCustomRepo());
	}

	@Bean 
	public ContinentPersistenceService continentPersistenceService(){
		return new ContinentPersistenceServiceImpl(continentRepository, continentCustomRepo());
	}

	@Bean 
	public RegionPersistenceService regionPersistenceService(){
		return new RegionPersistenceServiceImpl(regionRepository, regionCustomRepo());
	}

	@Bean 
	public CountryPersistenceService countryPersistenceService(){
		return new CountryPersistenceServiceImpl(countryRepository, countryCustomRepo());
	}

	@Bean 
	public StatePersistenceService statePersistenceService(){
		return new StatePersistenceServiceImpl(stateRepository, stateCustomRepo());
	}

	@Bean 
	public PopulatedPlacePersistenceService populatedPlacePersistenceService(){
		return new PopulatedPlacePersistenceServiceImpl(populatedPlaceRepository, populatedPlaceCustomRepo());
	}

	@Bean 
	public DocSitePersistenceService docSitePersistenceService(){
		return new DocSitePersistenceServiceImpl(docSiteRepository, docSiteCustomRepo());
	}

	@Bean 
	public BalanceGroupPersistenceService balanceGroupPersistenceService(){
		return new BalanceGroupPersistenceServiceImpl(balanceGroupRepository, balanceGroupCustomRepo());
	}

	@Bean 
	public AccountNaturePersistenceService accountNaturePersistenceService(){
		return new AccountNaturePersistenceServiceImpl(accountNatureRepository, accountNatureCustomRepo());
	}

	@Bean 
	public LocalAccountTemplatePersistenceService localAccountTemplatePersistenceService(){
		return new LocalAccountTemplatePersistenceServiceImpl(localAccountTemplateRepository, localAccountTemplateCustomRepo());
	}

	@Bean 
	public LocalAccountLevel1PersistenceService localAccountLevel1PersistenceService(){
		return new LocalAccountLevel1PersistenceServiceImpl(localAccountLevel1Repository, localAccountLevel1CustomRepo());
	}

	@Bean 
	public LocalAccountChartPersistenceService localAccountChartPersistenceService(){
		return new LocalAccountChartPersistenceServiceImpl(localAccountChartRepository, localAccountChartCustomRepo());
	}

	@Bean 
	public AccountPersistenceService accountPersistenceService(){
		return new AccountPersistenceServiceImpl(accountRepository, accountCustomRepo());
	}

	@Bean 
	public AccountConceptPersistenceService accountConceptPersistenceService(){
		return new AccountConceptPersistenceServiceImpl(accountConceptRepository, accountConceptCustomRepo());
	}

	@Bean 
	public InventoryAccountPersistenceService inventoryAccountPersistenceService(){
		return new InventoryAccountPersistenceServiceImpl(inventoryAccountRepository, inventoryAccountCustomRepo());
	}

	@Bean 
	public TaxRegimePersistenceService taxRegimePersistenceService(){
		return new TaxRegimePersistenceServiceImpl(taxRegimeRepository, taxRegimeCustomRepo());
	}

	@Bean 
	public WithholdingPersistenceService withholdingPersistenceService(){
		return new WithholdingPersistenceServiceImpl(withholdingRepository, withholdingCustomRepo());
	}

	@Bean 
	public TaxPersistenceService taxPersistenceService(){
		return new TaxPersistenceServiceImpl(taxRepository, taxCustomRepo());
	}

	@Bean 
	public CommerceWithholdingPersistenceService commerceWithholdingPersistenceService(){
		return new CommerceWithholdingPersistenceServiceImpl(commerceWithholdingRepository, commerceWithholdingCustomRepo());
	}

	@Bean 
	public ConsignmentWithholdingPersistenceService consignmentWithholdingPersistenceService(){
		return new ConsignmentWithholdingPersistenceServiceImpl(consignmentWithholdingRepository, consignmentWithholdingCustomRepo());
	}

	@Bean 
	public StampDutyPersistenceService stampDutyPersistenceService(){
		return new StampDutyPersistenceServiceImpl(stampDutyRepository, stampDutyCustomRepo());
	}

	@Bean 
	public ThirdPartyPersistenceService thirdPartyPersistenceService(){
		return new ThirdPartyPersistenceServiceImpl(thirdPartyRepository, thirdPartyCustomRepo());
	}

	@Bean 
	public ThirdPartyTypePersistenceService thirdPartyTypePersistenceService(){
		return new ThirdPartyTypePersistenceServiceImpl(thirdPartyTypeRepository, thirdPartyTypeCustomRepo());
	}

	@Bean 
	public ThirdPartyClassPersistenceService thirdPartyClassPersistenceService(){
		return new ThirdPartyClassPersistenceServiceImpl(thirdPartyClassRepository, thirdPartyClassCustomRepo());
	}

	@Bean 
	public PartnerPersistenceService partnerPersistenceService(){
		return new PartnerPersistenceServiceImpl(partnerRepository, partnerCustomRepo());
	}

	@Bean 
	public OperatingSystemPersistenceService operatingSystemPersistenceService(){
		return new OperatingSystemPersistenceServiceImpl(operatingSystemRepository, operatingSystemCustomRepo());
	}

	@Bean 
	public CustomerTenancyPersistenceService customerTenancyPersistenceService(){
		return new CustomerTenancyPersistenceServiceImpl(customerTenancyRepository, customerTenancyCustomRepo());
	}

	@Bean 
	public CorporationPersistenceService corporationPersistenceService(){
		return new CorporationPersistenceServiceImpl(corporationRepository, corporationCustomRepo());
	}

	@Bean 
	public CompanyPersistenceService companyPersistenceService(){
		return new CompanyPersistenceServiceImpl(companyRepository, companyCustomRepo());
	}

	@Bean 
	public BranchPersistenceService branchPersistenceService(){
		return new BranchPersistenceServiceImpl(branchRepository, branchCustomRepo());
	}

	@Bean 
	public DepartmentPersistenceService departmentPersistenceService(){
		return new DepartmentPersistenceServiceImpl(departmentRepository, departmentCustomRepo());
	}

	@Bean 
	public SectionPersistenceService sectionPersistenceService(){
		return new SectionPersistenceServiceImpl(sectionRepository, sectionCustomRepo());
	}

	@Bean 
	public TransportationTypePersistenceService transportationTypePersistenceService(){
		return new TransportationTypePersistenceServiceImpl(transportationTypeRepository, transportationTypeCustomRepo());
	}

	@Bean 
	public PriorityPersistenceService priorityPersistenceService(){
		return new PriorityPersistenceServiceImpl(priorityRepository, priorityCustomRepo());
	}

	@Bean 
	public DocPersistenceService docPersistenceService(){
		return new DocPersistenceServiceImpl(docRepository, docCustomRepo());
	}

	@Bean 
	public StockTransactionPersistenceService stockTransactionPersistenceService(){
		return new StockTransactionPersistenceServiceImpl(stockTransactionRepository, stockTransactionCustomRepo());
	}

	@Bean 
	public ContractPersistenceService contractPersistenceService(){
		return new ContractPersistenceServiceImpl(contractRepository, contractCustomRepo());
	}

	@Bean 
	public FieldPersistenceService fieldPersistenceService(){
		return new FieldPersistenceServiceImpl(fieldRepository, fieldCustomRepo());
	}

	@Bean 
	public OilWellPersistenceService oilWellPersistenceService(){
		return new OilWellPersistenceServiceImpl(oilWellRepository, oilWellCustomRepo());
	}

	@Bean 
	public ItemConditionPersistenceService itemConditionPersistenceService(){
		return new ItemConditionPersistenceServiceImpl(itemConditionRepository, itemConditionCustomRepo());
	}

	@Bean 
	public BillingCodePersistenceService billingCodePersistenceService(){
		return new BillingCodePersistenceServiceImpl(billingCodeRepository, billingCodeCustomRepo());
	}

	@Bean 
	public JointVenturePersistenceService jointVenturePersistenceService(){
		return new JointVenturePersistenceServiceImpl(jointVentureRepository, jointVentureCustomRepo());
	}

	@Bean 
	public AfeTypePersistenceService afeTypePersistenceService(){
		return new AfeTypePersistenceServiceImpl(afeTypeRepository, afeTypeCustomRepo());
	}

	@Bean 
	public AfePersistenceService afePersistenceService(){
		return new AfePersistenceServiceImpl(afeRepository, afeCustomRepo());
	}

	@Bean 
	public AfeBudgetPersistenceService afeBudgetPersistenceService(){
		return new AfeBudgetPersistenceServiceImpl(afeBudgetRepository, afeBudgetCustomRepo());
	}

	@Bean 
	public CostCenterPersistenceService costCenterPersistenceService(){
		return new CostCenterPersistenceServiceImpl(costCenterRepository, costCenterCustomRepo());
	}

	@Bean 
	public MeasurementUnitPersistenceService measurementUnitPersistenceService(){
		return new MeasurementUnitPersistenceServiceImpl(measurementUnitRepository, measurementUnitCustomRepo());
	}

	@Bean 
	public StockGroupPersistenceService stockGroupPersistenceService(){
		return new StockGroupPersistenceServiceImpl(stockGroupRepository, stockGroupCustomRepo());
	}

	@Bean 
	public StockSubGroupPersistenceService stockSubGroupPersistenceService(){
		return new StockSubGroupPersistenceServiceImpl(stockSubGroupRepository, stockSubGroupCustomRepo());
	}

	@Bean 
	public DivisionPersistenceService divisionPersistenceService(){
		return new DivisionPersistenceServiceImpl(divisionRepository, divisionCustomRepo());
	}

	@Bean 
	public WarehousePersistenceService warehousePersistenceService(){
		return new WarehousePersistenceServiceImpl(warehouseRepository, warehouseCustomRepo());
	}

	@Bean 
	public BusinessRulesPersistenceService businessRulesPersistenceService(){
		return new BusinessRulesPersistenceServiceImpl(businessRulesRepository, businessRulesCustomRepo());
	}

	@Bean 
	public ModulePersistenceService modulePersistenceService(){
		return new ModulePersistenceServiceImpl(moduleRepository, moduleCustomRepo());
	}

	@Bean 
	public PeriodTypePersistenceService periodTypePersistenceService(){
		return new PeriodTypePersistenceServiceImpl(periodTypeRepository, periodTypeCustomRepo());
	}

	@Bean 
	public PeriodPersistenceService periodPersistenceService(){
		return new PeriodPersistenceServiceImpl(periodRepository, periodCustomRepo());
	}

	@Bean 
	public QualityPersistenceService qualityPersistenceService(){
		return new QualityPersistenceServiceImpl(qualityRepository, qualityCustomRepo());
	}

	@Bean 
	public ExpenseTypePersistenceService expenseTypePersistenceService(){
		return new ExpenseTypePersistenceServiceImpl(expenseTypeRepository, expenseTypeCustomRepo());
	}

	@Bean 
	public HolidayPersistenceService holidayPersistenceService(){
		return new HolidayPersistenceServiceImpl(holidayRepository, holidayCustomRepo());
	}

	@Bean 
	public MethodOfPaymentPersistenceService methodOfPaymentPersistenceService(){
		return new MethodOfPaymentPersistenceServiceImpl(methodOfPaymentRepository, methodOfPaymentCustomRepo());
	}

	@Bean 
	public SupplierSelectionReasonPersistenceService supplierSelectionReasonPersistenceService(){
		return new SupplierSelectionReasonPersistenceServiceImpl(supplierSelectionReasonRepository, supplierSelectionReasonCustomRepo());
	}

	@Bean 
	public DeliveryLocationPersistenceService deliveryLocationPersistenceService(){
		return new DeliveryLocationPersistenceServiceImpl(deliveryLocationRepository, deliveryLocationCustomRepo());
	}

	@Bean 
	public PersonPersistenceService personPersistenceService(){
		return new PersonPersistenceServiceImpl(personRepository, personCustomRepo());
	}

	@Bean 
	public ItemTypePersistenceService itemTypePersistenceService(){
		return new ItemTypePersistenceServiceImpl(itemTypeRepository, itemTypeCustomRepo());
	}

	@Bean 
	public ItemPersistenceService itemPersistenceService(){
		return new ItemPersistenceServiceImpl(itemRepository, itemCustomRepo());
	}

	@Bean 
	public ItemTradeMarkPersistenceService itemTradeMarkPersistenceService(){
		return new ItemTradeMarkPersistenceServiceImpl(itemTradeMarkRepository, itemTradeMarkCustomRepo());
	}

	@Bean 
	public MiniMaxPersistenceService miniMaxPersistenceService(){
		return new MiniMaxPersistenceServiceImpl(miniMaxRepository, miniMaxCustomRepo());
	}

	@Bean 
	public ItemPurchaseHistoryPersistenceService itemPurchaseHistoryPersistenceService(){
		return new ItemPurchaseHistoryPersistenceServiceImpl(itemPurchaseHistoryRepository, itemPurchaseHistoryCustomRepo());
	}

	@Bean 
	public OtherPurchaseValuePersistenceService otherPurchaseValuePersistenceService(){
		return new OtherPurchaseValuePersistenceServiceImpl(otherPurchaseValueRepository, otherPurchaseValueCustomRepo());
	}


	
	@Bean 
	public RequisitionPersistenceService requisitionPersistenceService(){
		return new RequisitionPersistenceServiceImpl(requisitionRepository, requisitionCustomRepo());
	}
	
	@Bean 
	public QuotationPersistenceService quotationPersistenceService(){
		return new QuotationPersistenceServiceImpl(quotationRepository, quotationCustomRepo());
	}

}