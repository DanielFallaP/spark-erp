package co.com.cybersoft.purchase.tables.web.domain.users;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for users
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UsersInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String login;


	@NotEmpty
	private String password;


	@NotEmpty
	private String role;


	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	private Boolean active;


	private Boolean companyCreate;


	private Boolean companyRead;


	private Boolean companyUpdate;


	private Boolean companyExport;


	private Boolean stateCreate;


	private Boolean stateRead;


	private Boolean stateUpdate;


	private Boolean stateExport;


	private Boolean measurementUnitCreate;


	private Boolean measurementUnitRead;


	private Boolean measurementUnitUpdate;


	private Boolean measurementUnitExport;


	private Boolean typeSorterCreate;


	private Boolean typeSorterRead;


	private Boolean typeSorterUpdate;


	private Boolean typeSorterExport;


	private Boolean sorterCreate;


	private Boolean sorterRead;


	private Boolean sorterUpdate;


	private Boolean sorterExport;


	private Boolean operationCreate;


	private Boolean operationRead;


	private Boolean operationUpdate;


	private Boolean operationExport;


	private Boolean referenceCreate;


	private Boolean referenceRead;


	private Boolean referenceUpdate;


	private Boolean referenceExport;


	private Boolean referenceOperationCreate;


	private Boolean referenceOperationRead;


	private Boolean referenceOperationUpdate;


	private Boolean referenceOperationExport;


	private Boolean technicalActionCreate;


	private Boolean technicalActionRead;


	private Boolean technicalActionUpdate;


	private Boolean technicalActionExport;


	private Boolean effectFailCreate;


	private Boolean effectFailRead;


	private Boolean effectFailUpdate;


	private Boolean effectFailExport;


	private Boolean effectFailTechnicalActionCreate;


	private Boolean effectFailTechnicalActionRead;


	private Boolean effectFailTechnicalActionUpdate;


	private Boolean effectFailTechnicalActionExport;


	private Boolean failureCauseCreate;


	private Boolean failureCauseRead;


	private Boolean failureCauseUpdate;


	private Boolean failureCauseExport;


	private Boolean failureCauseTechnicalactionCreate;


	private Boolean failureCauseTechnicalactionRead;


	private Boolean failureCauseTechnicalactionUpdate;


	private Boolean failureCauseTechnicalactionExport;


	private Boolean typeCauseCloseCreate;


	private Boolean typeCauseCloseRead;


	private Boolean typeCauseCloseUpdate;


	private Boolean typeCauseCloseExport;


	private Boolean causeCloseCreate;


	private Boolean causeCloseRead;


	private Boolean causeCloseUpdate;


	private Boolean causeCloseExport;


	private Boolean maintenanceActivityCreate;


	private Boolean maintenanceActivityRead;


	private Boolean maintenanceActivityUpdate;


	private Boolean maintenanceActivityExport;


	private Boolean maintenanceWorkCreate;


	private Boolean maintenanceWorkRead;


	private Boolean maintenanceWorkUpdate;


	private Boolean maintenanceWorkExport;


	private Boolean typeMaintenanceCreate;


	private Boolean typeMaintenanceRead;


	private Boolean typeMaintenanceUpdate;


	private Boolean typeMaintenanceExport;


	private Boolean causePendingCreate;


	private Boolean causePendingRead;


	private Boolean causePendingUpdate;


	private Boolean causePendingExport;


	private Boolean typeWorkCreate;


	private Boolean typeWorkRead;


	private Boolean typeWorkUpdate;


	private Boolean typeWorkExport;


	private Boolean otherConceptsCreate;


	private Boolean otherConceptsRead;


	private Boolean otherConceptsUpdate;


	private Boolean otherConceptsExport;


	private Boolean stateCostCentersCreate;


	private Boolean stateCostCentersRead;


	private Boolean stateCostCentersUpdate;


	private Boolean stateCostCentersExport;


	private Boolean costCentersCustomersCreate;


	private Boolean costCentersCustomersRead;


	private Boolean costCentersCustomersUpdate;


	private Boolean costCentersCustomersExport;


	private Boolean responsibleCenterCreate;


	private Boolean responsibleCenterRead;


	private Boolean responsibleCenterUpdate;


	private Boolean responsibleCenterExport;


	private Boolean jobCreate;


	private Boolean jobRead;


	private Boolean jobUpdate;


	private Boolean jobExport;


	private Boolean thirdCreate;


	private Boolean thirdRead;


	private Boolean thirdUpdate;


	private Boolean thirdExport;


	private Boolean jobThirdCreate;


	private Boolean jobThirdRead;


	private Boolean jobThirdUpdate;


	private Boolean jobThirdExport;


	private Boolean responsibleCreate;


	private Boolean responsibleRead;


	private Boolean responsibleUpdate;


	private Boolean responsibleExport;


	private Boolean authorizerCostCenterCreate;


	private Boolean authorizerCostCenterRead;


	private Boolean authorizerCostCenterUpdate;


	private Boolean authorizerCostCenterExport;


	private Boolean characteristicCreate;


	private Boolean characteristicRead;


	private Boolean characteristicUpdate;


	private Boolean characteristicExport;


	private Boolean accountantCreate;


	private Boolean accountantRead;


	private Boolean accountantUpdate;


	private Boolean accountantExport;


	private Boolean physicalLocationCreate;


	private Boolean physicalLocationRead;


	private Boolean physicalLocationUpdate;


	private Boolean physicalLocationExport;


	private Boolean storeTypeCreate;


	private Boolean storeTypeRead;


	private Boolean storeTypeUpdate;


	private Boolean storeTypeExport;


	private Boolean costingTypeCreate;


	private Boolean costingTypeRead;


	private Boolean costingTypeUpdate;


	private Boolean costingTypeExport;


	private Boolean warehouseCreate;


	private Boolean warehouseRead;


	private Boolean warehouseUpdate;


	private Boolean warehouseExport;


	private Boolean coinCreate;


	private Boolean coinRead;


	private Boolean coinUpdate;


	private Boolean coinExport;


	private Boolean typeConceptKardexCreate;


	private Boolean typeConceptKardexRead;


	private Boolean typeConceptKardexUpdate;


	private Boolean typeConceptKardexExport;


	private Boolean conceptKardexCreate;


	private Boolean conceptKardexRead;


	private Boolean conceptKardexUpdate;


	private Boolean conceptKardexExport;


	private Boolean contractCreate;


	private Boolean contractRead;


	private Boolean contractUpdate;


	private Boolean contractExport;


	private Boolean usersCreate;


	private Boolean usersRead;


	private Boolean usersUpdate;


	private Boolean usersExport;


	private Boolean currencyCreate;


	private Boolean currencyRead;


	private Boolean currencyUpdate;


	private Boolean currencyExport;


	private Boolean exchangeRateCreate;


	private Boolean exchangeRateRead;


	private Boolean exchangeRateUpdate;


	private Boolean exchangeRateExport;


	private Boolean continentCreate;


	private Boolean continentRead;


	private Boolean continentUpdate;


	private Boolean continentExport;


	private Boolean regionCreate;


	private Boolean regionRead;


	private Boolean regionUpdate;


	private Boolean regionExport;


	private Boolean currencyCodeCreate;


	private Boolean currencyCodeRead;


	private Boolean currencyCodeUpdate;


	private Boolean currencyCodeExport;


	private Boolean countryCreate;


	private Boolean countryRead;


	private Boolean countryUpdate;


	private Boolean countryExport;


	public String getLogin() {
		return login;	
	}
		
	public void setLogin(String login) {
		this.login = login;	
	}

	public String getPassword() {
		return password;	
	}
		
	public void setPassword(String password) {
		this.password = password;	
	}

	public String getRole() {
		return role;	
	}
		
	public void setRole(String role) {
		this.role = role;	
	}

	public List<CompanyDetails> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(
				List<CompanyDetails> companyList) {
			this.companyList = companyList;
	}

	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}

	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	public Boolean getCompanyCreate() {
		return companyCreate;	
	}
		
	public void setCompanyCreate(Boolean companyCreate) {
		this.companyCreate = companyCreate;	
	}

	public Boolean getCompanyRead() {
		return companyRead;	
	}
		
	public void setCompanyRead(Boolean companyRead) {
		this.companyRead = companyRead;	
	}

	public Boolean getCompanyUpdate() {
		return companyUpdate;	
	}
		
	public void setCompanyUpdate(Boolean companyUpdate) {
		this.companyUpdate = companyUpdate;	
	}

	public Boolean getCompanyExport() {
		return companyExport;	
	}
		
	public void setCompanyExport(Boolean companyExport) {
		this.companyExport = companyExport;	
	}

	public Boolean getStateCreate() {
		return stateCreate;	
	}
		
	public void setStateCreate(Boolean stateCreate) {
		this.stateCreate = stateCreate;	
	}

	public Boolean getStateRead() {
		return stateRead;	
	}
		
	public void setStateRead(Boolean stateRead) {
		this.stateRead = stateRead;	
	}

	public Boolean getStateUpdate() {
		return stateUpdate;	
	}
		
	public void setStateUpdate(Boolean stateUpdate) {
		this.stateUpdate = stateUpdate;	
	}

	public Boolean getStateExport() {
		return stateExport;	
	}
		
	public void setStateExport(Boolean stateExport) {
		this.stateExport = stateExport;	
	}

	public Boolean getMeasurementUnitCreate() {
		return measurementUnitCreate;	
	}
		
	public void setMeasurementUnitCreate(Boolean measurementUnitCreate) {
		this.measurementUnitCreate = measurementUnitCreate;	
	}

	public Boolean getMeasurementUnitRead() {
		return measurementUnitRead;	
	}
		
	public void setMeasurementUnitRead(Boolean measurementUnitRead) {
		this.measurementUnitRead = measurementUnitRead;	
	}

	public Boolean getMeasurementUnitUpdate() {
		return measurementUnitUpdate;	
	}
		
	public void setMeasurementUnitUpdate(Boolean measurementUnitUpdate) {
		this.measurementUnitUpdate = measurementUnitUpdate;	
	}

	public Boolean getMeasurementUnitExport() {
		return measurementUnitExport;	
	}
		
	public void setMeasurementUnitExport(Boolean measurementUnitExport) {
		this.measurementUnitExport = measurementUnitExport;	
	}

	public Boolean getTypeSorterCreate() {
		return typeSorterCreate;	
	}
		
	public void setTypeSorterCreate(Boolean typeSorterCreate) {
		this.typeSorterCreate = typeSorterCreate;	
	}

	public Boolean getTypeSorterRead() {
		return typeSorterRead;	
	}
		
	public void setTypeSorterRead(Boolean typeSorterRead) {
		this.typeSorterRead = typeSorterRead;	
	}

	public Boolean getTypeSorterUpdate() {
		return typeSorterUpdate;	
	}
		
	public void setTypeSorterUpdate(Boolean typeSorterUpdate) {
		this.typeSorterUpdate = typeSorterUpdate;	
	}

	public Boolean getTypeSorterExport() {
		return typeSorterExport;	
	}
		
	public void setTypeSorterExport(Boolean typeSorterExport) {
		this.typeSorterExport = typeSorterExport;	
	}

	public Boolean getSorterCreate() {
		return sorterCreate;	
	}
		
	public void setSorterCreate(Boolean sorterCreate) {
		this.sorterCreate = sorterCreate;	
	}

	public Boolean getSorterRead() {
		return sorterRead;	
	}
		
	public void setSorterRead(Boolean sorterRead) {
		this.sorterRead = sorterRead;	
	}

	public Boolean getSorterUpdate() {
		return sorterUpdate;	
	}
		
	public void setSorterUpdate(Boolean sorterUpdate) {
		this.sorterUpdate = sorterUpdate;	
	}

	public Boolean getSorterExport() {
		return sorterExport;	
	}
		
	public void setSorterExport(Boolean sorterExport) {
		this.sorterExport = sorterExport;	
	}

	public Boolean getOperationCreate() {
		return operationCreate;	
	}
		
	public void setOperationCreate(Boolean operationCreate) {
		this.operationCreate = operationCreate;	
	}

	public Boolean getOperationRead() {
		return operationRead;	
	}
		
	public void setOperationRead(Boolean operationRead) {
		this.operationRead = operationRead;	
	}

	public Boolean getOperationUpdate() {
		return operationUpdate;	
	}
		
	public void setOperationUpdate(Boolean operationUpdate) {
		this.operationUpdate = operationUpdate;	
	}

	public Boolean getOperationExport() {
		return operationExport;	
	}
		
	public void setOperationExport(Boolean operationExport) {
		this.operationExport = operationExport;	
	}

	public Boolean getReferenceCreate() {
		return referenceCreate;	
	}
		
	public void setReferenceCreate(Boolean referenceCreate) {
		this.referenceCreate = referenceCreate;	
	}

	public Boolean getReferenceRead() {
		return referenceRead;	
	}
		
	public void setReferenceRead(Boolean referenceRead) {
		this.referenceRead = referenceRead;	
	}

	public Boolean getReferenceUpdate() {
		return referenceUpdate;	
	}
		
	public void setReferenceUpdate(Boolean referenceUpdate) {
		this.referenceUpdate = referenceUpdate;	
	}

	public Boolean getReferenceExport() {
		return referenceExport;	
	}
		
	public void setReferenceExport(Boolean referenceExport) {
		this.referenceExport = referenceExport;	
	}

	public Boolean getReferenceOperationCreate() {
		return referenceOperationCreate;	
	}
		
	public void setReferenceOperationCreate(Boolean referenceOperationCreate) {
		this.referenceOperationCreate = referenceOperationCreate;	
	}

	public Boolean getReferenceOperationRead() {
		return referenceOperationRead;	
	}
		
	public void setReferenceOperationRead(Boolean referenceOperationRead) {
		this.referenceOperationRead = referenceOperationRead;	
	}

	public Boolean getReferenceOperationUpdate() {
		return referenceOperationUpdate;	
	}
		
	public void setReferenceOperationUpdate(Boolean referenceOperationUpdate) {
		this.referenceOperationUpdate = referenceOperationUpdate;	
	}

	public Boolean getReferenceOperationExport() {
		return referenceOperationExport;	
	}
		
	public void setReferenceOperationExport(Boolean referenceOperationExport) {
		this.referenceOperationExport = referenceOperationExport;	
	}

	public Boolean getTechnicalActionCreate() {
		return technicalActionCreate;	
	}
		
	public void setTechnicalActionCreate(Boolean technicalActionCreate) {
		this.technicalActionCreate = technicalActionCreate;	
	}

	public Boolean getTechnicalActionRead() {
		return technicalActionRead;	
	}
		
	public void setTechnicalActionRead(Boolean technicalActionRead) {
		this.technicalActionRead = technicalActionRead;	
	}

	public Boolean getTechnicalActionUpdate() {
		return technicalActionUpdate;	
	}
		
	public void setTechnicalActionUpdate(Boolean technicalActionUpdate) {
		this.technicalActionUpdate = technicalActionUpdate;	
	}

	public Boolean getTechnicalActionExport() {
		return technicalActionExport;	
	}
		
	public void setTechnicalActionExport(Boolean technicalActionExport) {
		this.technicalActionExport = technicalActionExport;	
	}

	public Boolean getEffectFailCreate() {
		return effectFailCreate;	
	}
		
	public void setEffectFailCreate(Boolean effectFailCreate) {
		this.effectFailCreate = effectFailCreate;	
	}

	public Boolean getEffectFailRead() {
		return effectFailRead;	
	}
		
	public void setEffectFailRead(Boolean effectFailRead) {
		this.effectFailRead = effectFailRead;	
	}

	public Boolean getEffectFailUpdate() {
		return effectFailUpdate;	
	}
		
	public void setEffectFailUpdate(Boolean effectFailUpdate) {
		this.effectFailUpdate = effectFailUpdate;	
	}

	public Boolean getEffectFailExport() {
		return effectFailExport;	
	}
		
	public void setEffectFailExport(Boolean effectFailExport) {
		this.effectFailExport = effectFailExport;	
	}

	public Boolean getEffectFailTechnicalActionCreate() {
		return effectFailTechnicalActionCreate;	
	}
		
	public void setEffectFailTechnicalActionCreate(Boolean effectFailTechnicalActionCreate) {
		this.effectFailTechnicalActionCreate = effectFailTechnicalActionCreate;	
	}

	public Boolean getEffectFailTechnicalActionRead() {
		return effectFailTechnicalActionRead;	
	}
		
	public void setEffectFailTechnicalActionRead(Boolean effectFailTechnicalActionRead) {
		this.effectFailTechnicalActionRead = effectFailTechnicalActionRead;	
	}

	public Boolean getEffectFailTechnicalActionUpdate() {
		return effectFailTechnicalActionUpdate;	
	}
		
	public void setEffectFailTechnicalActionUpdate(Boolean effectFailTechnicalActionUpdate) {
		this.effectFailTechnicalActionUpdate = effectFailTechnicalActionUpdate;	
	}

	public Boolean getEffectFailTechnicalActionExport() {
		return effectFailTechnicalActionExport;	
	}
		
	public void setEffectFailTechnicalActionExport(Boolean effectFailTechnicalActionExport) {
		this.effectFailTechnicalActionExport = effectFailTechnicalActionExport;	
	}

	public Boolean getFailureCauseCreate() {
		return failureCauseCreate;	
	}
		
	public void setFailureCauseCreate(Boolean failureCauseCreate) {
		this.failureCauseCreate = failureCauseCreate;	
	}

	public Boolean getFailureCauseRead() {
		return failureCauseRead;	
	}
		
	public void setFailureCauseRead(Boolean failureCauseRead) {
		this.failureCauseRead = failureCauseRead;	
	}

	public Boolean getFailureCauseUpdate() {
		return failureCauseUpdate;	
	}
		
	public void setFailureCauseUpdate(Boolean failureCauseUpdate) {
		this.failureCauseUpdate = failureCauseUpdate;	
	}

	public Boolean getFailureCauseExport() {
		return failureCauseExport;	
	}
		
	public void setFailureCauseExport(Boolean failureCauseExport) {
		this.failureCauseExport = failureCauseExport;	
	}

	public Boolean getFailureCauseTechnicalactionCreate() {
		return failureCauseTechnicalactionCreate;	
	}
		
	public void setFailureCauseTechnicalactionCreate(Boolean failureCauseTechnicalactionCreate) {
		this.failureCauseTechnicalactionCreate = failureCauseTechnicalactionCreate;	
	}

	public Boolean getFailureCauseTechnicalactionRead() {
		return failureCauseTechnicalactionRead;	
	}
		
	public void setFailureCauseTechnicalactionRead(Boolean failureCauseTechnicalactionRead) {
		this.failureCauseTechnicalactionRead = failureCauseTechnicalactionRead;	
	}

	public Boolean getFailureCauseTechnicalactionUpdate() {
		return failureCauseTechnicalactionUpdate;	
	}
		
	public void setFailureCauseTechnicalactionUpdate(Boolean failureCauseTechnicalactionUpdate) {
		this.failureCauseTechnicalactionUpdate = failureCauseTechnicalactionUpdate;	
	}

	public Boolean getFailureCauseTechnicalactionExport() {
		return failureCauseTechnicalactionExport;	
	}
		
	public void setFailureCauseTechnicalactionExport(Boolean failureCauseTechnicalactionExport) {
		this.failureCauseTechnicalactionExport = failureCauseTechnicalactionExport;	
	}

	public Boolean getTypeCauseCloseCreate() {
		return typeCauseCloseCreate;	
	}
		
	public void setTypeCauseCloseCreate(Boolean typeCauseCloseCreate) {
		this.typeCauseCloseCreate = typeCauseCloseCreate;	
	}

	public Boolean getTypeCauseCloseRead() {
		return typeCauseCloseRead;	
	}
		
	public void setTypeCauseCloseRead(Boolean typeCauseCloseRead) {
		this.typeCauseCloseRead = typeCauseCloseRead;	
	}

	public Boolean getTypeCauseCloseUpdate() {
		return typeCauseCloseUpdate;	
	}
		
	public void setTypeCauseCloseUpdate(Boolean typeCauseCloseUpdate) {
		this.typeCauseCloseUpdate = typeCauseCloseUpdate;	
	}

	public Boolean getTypeCauseCloseExport() {
		return typeCauseCloseExport;	
	}
		
	public void setTypeCauseCloseExport(Boolean typeCauseCloseExport) {
		this.typeCauseCloseExport = typeCauseCloseExport;	
	}

	public Boolean getCauseCloseCreate() {
		return causeCloseCreate;	
	}
		
	public void setCauseCloseCreate(Boolean causeCloseCreate) {
		this.causeCloseCreate = causeCloseCreate;	
	}

	public Boolean getCauseCloseRead() {
		return causeCloseRead;	
	}
		
	public void setCauseCloseRead(Boolean causeCloseRead) {
		this.causeCloseRead = causeCloseRead;	
	}

	public Boolean getCauseCloseUpdate() {
		return causeCloseUpdate;	
	}
		
	public void setCauseCloseUpdate(Boolean causeCloseUpdate) {
		this.causeCloseUpdate = causeCloseUpdate;	
	}

	public Boolean getCauseCloseExport() {
		return causeCloseExport;	
	}
		
	public void setCauseCloseExport(Boolean causeCloseExport) {
		this.causeCloseExport = causeCloseExport;	
	}

	public Boolean getMaintenanceActivityCreate() {
		return maintenanceActivityCreate;	
	}
		
	public void setMaintenanceActivityCreate(Boolean maintenanceActivityCreate) {
		this.maintenanceActivityCreate = maintenanceActivityCreate;	
	}

	public Boolean getMaintenanceActivityRead() {
		return maintenanceActivityRead;	
	}
		
	public void setMaintenanceActivityRead(Boolean maintenanceActivityRead) {
		this.maintenanceActivityRead = maintenanceActivityRead;	
	}

	public Boolean getMaintenanceActivityUpdate() {
		return maintenanceActivityUpdate;	
	}
		
	public void setMaintenanceActivityUpdate(Boolean maintenanceActivityUpdate) {
		this.maintenanceActivityUpdate = maintenanceActivityUpdate;	
	}

	public Boolean getMaintenanceActivityExport() {
		return maintenanceActivityExport;	
	}
		
	public void setMaintenanceActivityExport(Boolean maintenanceActivityExport) {
		this.maintenanceActivityExport = maintenanceActivityExport;	
	}

	public Boolean getMaintenanceWorkCreate() {
		return maintenanceWorkCreate;	
	}
		
	public void setMaintenanceWorkCreate(Boolean maintenanceWorkCreate) {
		this.maintenanceWorkCreate = maintenanceWorkCreate;	
	}

	public Boolean getMaintenanceWorkRead() {
		return maintenanceWorkRead;	
	}
		
	public void setMaintenanceWorkRead(Boolean maintenanceWorkRead) {
		this.maintenanceWorkRead = maintenanceWorkRead;	
	}

	public Boolean getMaintenanceWorkUpdate() {
		return maintenanceWorkUpdate;	
	}
		
	public void setMaintenanceWorkUpdate(Boolean maintenanceWorkUpdate) {
		this.maintenanceWorkUpdate = maintenanceWorkUpdate;	
	}

	public Boolean getMaintenanceWorkExport() {
		return maintenanceWorkExport;	
	}
		
	public void setMaintenanceWorkExport(Boolean maintenanceWorkExport) {
		this.maintenanceWorkExport = maintenanceWorkExport;	
	}

	public Boolean getTypeMaintenanceCreate() {
		return typeMaintenanceCreate;	
	}
		
	public void setTypeMaintenanceCreate(Boolean typeMaintenanceCreate) {
		this.typeMaintenanceCreate = typeMaintenanceCreate;	
	}

	public Boolean getTypeMaintenanceRead() {
		return typeMaintenanceRead;	
	}
		
	public void setTypeMaintenanceRead(Boolean typeMaintenanceRead) {
		this.typeMaintenanceRead = typeMaintenanceRead;	
	}

	public Boolean getTypeMaintenanceUpdate() {
		return typeMaintenanceUpdate;	
	}
		
	public void setTypeMaintenanceUpdate(Boolean typeMaintenanceUpdate) {
		this.typeMaintenanceUpdate = typeMaintenanceUpdate;	
	}

	public Boolean getTypeMaintenanceExport() {
		return typeMaintenanceExport;	
	}
		
	public void setTypeMaintenanceExport(Boolean typeMaintenanceExport) {
		this.typeMaintenanceExport = typeMaintenanceExport;	
	}

	public Boolean getCausePendingCreate() {
		return causePendingCreate;	
	}
		
	public void setCausePendingCreate(Boolean causePendingCreate) {
		this.causePendingCreate = causePendingCreate;	
	}

	public Boolean getCausePendingRead() {
		return causePendingRead;	
	}
		
	public void setCausePendingRead(Boolean causePendingRead) {
		this.causePendingRead = causePendingRead;	
	}

	public Boolean getCausePendingUpdate() {
		return causePendingUpdate;	
	}
		
	public void setCausePendingUpdate(Boolean causePendingUpdate) {
		this.causePendingUpdate = causePendingUpdate;	
	}

	public Boolean getCausePendingExport() {
		return causePendingExport;	
	}
		
	public void setCausePendingExport(Boolean causePendingExport) {
		this.causePendingExport = causePendingExport;	
	}

	public Boolean getTypeWorkCreate() {
		return typeWorkCreate;	
	}
		
	public void setTypeWorkCreate(Boolean typeWorkCreate) {
		this.typeWorkCreate = typeWorkCreate;	
	}

	public Boolean getTypeWorkRead() {
		return typeWorkRead;	
	}
		
	public void setTypeWorkRead(Boolean typeWorkRead) {
		this.typeWorkRead = typeWorkRead;	
	}

	public Boolean getTypeWorkUpdate() {
		return typeWorkUpdate;	
	}
		
	public void setTypeWorkUpdate(Boolean typeWorkUpdate) {
		this.typeWorkUpdate = typeWorkUpdate;	
	}

	public Boolean getTypeWorkExport() {
		return typeWorkExport;	
	}
		
	public void setTypeWorkExport(Boolean typeWorkExport) {
		this.typeWorkExport = typeWorkExport;	
	}

	public Boolean getOtherConceptsCreate() {
		return otherConceptsCreate;	
	}
		
	public void setOtherConceptsCreate(Boolean otherConceptsCreate) {
		this.otherConceptsCreate = otherConceptsCreate;	
	}

	public Boolean getOtherConceptsRead() {
		return otherConceptsRead;	
	}
		
	public void setOtherConceptsRead(Boolean otherConceptsRead) {
		this.otherConceptsRead = otherConceptsRead;	
	}

	public Boolean getOtherConceptsUpdate() {
		return otherConceptsUpdate;	
	}
		
	public void setOtherConceptsUpdate(Boolean otherConceptsUpdate) {
		this.otherConceptsUpdate = otherConceptsUpdate;	
	}

	public Boolean getOtherConceptsExport() {
		return otherConceptsExport;	
	}
		
	public void setOtherConceptsExport(Boolean otherConceptsExport) {
		this.otherConceptsExport = otherConceptsExport;	
	}

	public Boolean getStateCostCentersCreate() {
		return stateCostCentersCreate;	
	}
		
	public void setStateCostCentersCreate(Boolean stateCostCentersCreate) {
		this.stateCostCentersCreate = stateCostCentersCreate;	
	}

	public Boolean getStateCostCentersRead() {
		return stateCostCentersRead;	
	}
		
	public void setStateCostCentersRead(Boolean stateCostCentersRead) {
		this.stateCostCentersRead = stateCostCentersRead;	
	}

	public Boolean getStateCostCentersUpdate() {
		return stateCostCentersUpdate;	
	}
		
	public void setStateCostCentersUpdate(Boolean stateCostCentersUpdate) {
		this.stateCostCentersUpdate = stateCostCentersUpdate;	
	}

	public Boolean getStateCostCentersExport() {
		return stateCostCentersExport;	
	}
		
	public void setStateCostCentersExport(Boolean stateCostCentersExport) {
		this.stateCostCentersExport = stateCostCentersExport;	
	}

	public Boolean getCostCentersCustomersCreate() {
		return costCentersCustomersCreate;	
	}
		
	public void setCostCentersCustomersCreate(Boolean costCentersCustomersCreate) {
		this.costCentersCustomersCreate = costCentersCustomersCreate;	
	}

	public Boolean getCostCentersCustomersRead() {
		return costCentersCustomersRead;	
	}
		
	public void setCostCentersCustomersRead(Boolean costCentersCustomersRead) {
		this.costCentersCustomersRead = costCentersCustomersRead;	
	}

	public Boolean getCostCentersCustomersUpdate() {
		return costCentersCustomersUpdate;	
	}
		
	public void setCostCentersCustomersUpdate(Boolean costCentersCustomersUpdate) {
		this.costCentersCustomersUpdate = costCentersCustomersUpdate;	
	}

	public Boolean getCostCentersCustomersExport() {
		return costCentersCustomersExport;	
	}
		
	public void setCostCentersCustomersExport(Boolean costCentersCustomersExport) {
		this.costCentersCustomersExport = costCentersCustomersExport;	
	}

	public Boolean getResponsibleCenterCreate() {
		return responsibleCenterCreate;	
	}
		
	public void setResponsibleCenterCreate(Boolean responsibleCenterCreate) {
		this.responsibleCenterCreate = responsibleCenterCreate;	
	}

	public Boolean getResponsibleCenterRead() {
		return responsibleCenterRead;	
	}
		
	public void setResponsibleCenterRead(Boolean responsibleCenterRead) {
		this.responsibleCenterRead = responsibleCenterRead;	
	}

	public Boolean getResponsibleCenterUpdate() {
		return responsibleCenterUpdate;	
	}
		
	public void setResponsibleCenterUpdate(Boolean responsibleCenterUpdate) {
		this.responsibleCenterUpdate = responsibleCenterUpdate;	
	}

	public Boolean getResponsibleCenterExport() {
		return responsibleCenterExport;	
	}
		
	public void setResponsibleCenterExport(Boolean responsibleCenterExport) {
		this.responsibleCenterExport = responsibleCenterExport;	
	}

	public Boolean getJobCreate() {
		return jobCreate;	
	}
		
	public void setJobCreate(Boolean jobCreate) {
		this.jobCreate = jobCreate;	
	}

	public Boolean getJobRead() {
		return jobRead;	
	}
		
	public void setJobRead(Boolean jobRead) {
		this.jobRead = jobRead;	
	}

	public Boolean getJobUpdate() {
		return jobUpdate;	
	}
		
	public void setJobUpdate(Boolean jobUpdate) {
		this.jobUpdate = jobUpdate;	
	}

	public Boolean getJobExport() {
		return jobExport;	
	}
		
	public void setJobExport(Boolean jobExport) {
		this.jobExport = jobExport;	
	}

	public Boolean getThirdCreate() {
		return thirdCreate;	
	}
		
	public void setThirdCreate(Boolean thirdCreate) {
		this.thirdCreate = thirdCreate;	
	}

	public Boolean getThirdRead() {
		return thirdRead;	
	}
		
	public void setThirdRead(Boolean thirdRead) {
		this.thirdRead = thirdRead;	
	}

	public Boolean getThirdUpdate() {
		return thirdUpdate;	
	}
		
	public void setThirdUpdate(Boolean thirdUpdate) {
		this.thirdUpdate = thirdUpdate;	
	}

	public Boolean getThirdExport() {
		return thirdExport;	
	}
		
	public void setThirdExport(Boolean thirdExport) {
		this.thirdExport = thirdExport;	
	}

	public Boolean getJobThirdCreate() {
		return jobThirdCreate;	
	}
		
	public void setJobThirdCreate(Boolean jobThirdCreate) {
		this.jobThirdCreate = jobThirdCreate;	
	}

	public Boolean getJobThirdRead() {
		return jobThirdRead;	
	}
		
	public void setJobThirdRead(Boolean jobThirdRead) {
		this.jobThirdRead = jobThirdRead;	
	}

	public Boolean getJobThirdUpdate() {
		return jobThirdUpdate;	
	}
		
	public void setJobThirdUpdate(Boolean jobThirdUpdate) {
		this.jobThirdUpdate = jobThirdUpdate;	
	}

	public Boolean getJobThirdExport() {
		return jobThirdExport;	
	}
		
	public void setJobThirdExport(Boolean jobThirdExport) {
		this.jobThirdExport = jobThirdExport;	
	}

	public Boolean getResponsibleCreate() {
		return responsibleCreate;	
	}
		
	public void setResponsibleCreate(Boolean responsibleCreate) {
		this.responsibleCreate = responsibleCreate;	
	}

	public Boolean getResponsibleRead() {
		return responsibleRead;	
	}
		
	public void setResponsibleRead(Boolean responsibleRead) {
		this.responsibleRead = responsibleRead;	
	}

	public Boolean getResponsibleUpdate() {
		return responsibleUpdate;	
	}
		
	public void setResponsibleUpdate(Boolean responsibleUpdate) {
		this.responsibleUpdate = responsibleUpdate;	
	}

	public Boolean getResponsibleExport() {
		return responsibleExport;	
	}
		
	public void setResponsibleExport(Boolean responsibleExport) {
		this.responsibleExport = responsibleExport;	
	}

	public Boolean getAuthorizerCostCenterCreate() {
		return authorizerCostCenterCreate;	
	}
		
	public void setAuthorizerCostCenterCreate(Boolean authorizerCostCenterCreate) {
		this.authorizerCostCenterCreate = authorizerCostCenterCreate;	
	}

	public Boolean getAuthorizerCostCenterRead() {
		return authorizerCostCenterRead;	
	}
		
	public void setAuthorizerCostCenterRead(Boolean authorizerCostCenterRead) {
		this.authorizerCostCenterRead = authorizerCostCenterRead;	
	}

	public Boolean getAuthorizerCostCenterUpdate() {
		return authorizerCostCenterUpdate;	
	}
		
	public void setAuthorizerCostCenterUpdate(Boolean authorizerCostCenterUpdate) {
		this.authorizerCostCenterUpdate = authorizerCostCenterUpdate;	
	}

	public Boolean getAuthorizerCostCenterExport() {
		return authorizerCostCenterExport;	
	}
		
	public void setAuthorizerCostCenterExport(Boolean authorizerCostCenterExport) {
		this.authorizerCostCenterExport = authorizerCostCenterExport;	
	}

	public Boolean getCharacteristicCreate() {
		return characteristicCreate;	
	}
		
	public void setCharacteristicCreate(Boolean characteristicCreate) {
		this.characteristicCreate = characteristicCreate;	
	}

	public Boolean getCharacteristicRead() {
		return characteristicRead;	
	}
		
	public void setCharacteristicRead(Boolean characteristicRead) {
		this.characteristicRead = characteristicRead;	
	}

	public Boolean getCharacteristicUpdate() {
		return characteristicUpdate;	
	}
		
	public void setCharacteristicUpdate(Boolean characteristicUpdate) {
		this.characteristicUpdate = characteristicUpdate;	
	}

	public Boolean getCharacteristicExport() {
		return characteristicExport;	
	}
		
	public void setCharacteristicExport(Boolean characteristicExport) {
		this.characteristicExport = characteristicExport;	
	}

	public Boolean getAccountantCreate() {
		return accountantCreate;	
	}
		
	public void setAccountantCreate(Boolean accountantCreate) {
		this.accountantCreate = accountantCreate;	
	}

	public Boolean getAccountantRead() {
		return accountantRead;	
	}
		
	public void setAccountantRead(Boolean accountantRead) {
		this.accountantRead = accountantRead;	
	}

	public Boolean getAccountantUpdate() {
		return accountantUpdate;	
	}
		
	public void setAccountantUpdate(Boolean accountantUpdate) {
		this.accountantUpdate = accountantUpdate;	
	}

	public Boolean getAccountantExport() {
		return accountantExport;	
	}
		
	public void setAccountantExport(Boolean accountantExport) {
		this.accountantExport = accountantExport;	
	}

	public Boolean getPhysicalLocationCreate() {
		return physicalLocationCreate;	
	}
		
	public void setPhysicalLocationCreate(Boolean physicalLocationCreate) {
		this.physicalLocationCreate = physicalLocationCreate;	
	}

	public Boolean getPhysicalLocationRead() {
		return physicalLocationRead;	
	}
		
	public void setPhysicalLocationRead(Boolean physicalLocationRead) {
		this.physicalLocationRead = physicalLocationRead;	
	}

	public Boolean getPhysicalLocationUpdate() {
		return physicalLocationUpdate;	
	}
		
	public void setPhysicalLocationUpdate(Boolean physicalLocationUpdate) {
		this.physicalLocationUpdate = physicalLocationUpdate;	
	}

	public Boolean getPhysicalLocationExport() {
		return physicalLocationExport;	
	}
		
	public void setPhysicalLocationExport(Boolean physicalLocationExport) {
		this.physicalLocationExport = physicalLocationExport;	
	}

	public Boolean getStoreTypeCreate() {
		return storeTypeCreate;	
	}
		
	public void setStoreTypeCreate(Boolean storeTypeCreate) {
		this.storeTypeCreate = storeTypeCreate;	
	}

	public Boolean getStoreTypeRead() {
		return storeTypeRead;	
	}
		
	public void setStoreTypeRead(Boolean storeTypeRead) {
		this.storeTypeRead = storeTypeRead;	
	}

	public Boolean getStoreTypeUpdate() {
		return storeTypeUpdate;	
	}
		
	public void setStoreTypeUpdate(Boolean storeTypeUpdate) {
		this.storeTypeUpdate = storeTypeUpdate;	
	}

	public Boolean getStoreTypeExport() {
		return storeTypeExport;	
	}
		
	public void setStoreTypeExport(Boolean storeTypeExport) {
		this.storeTypeExport = storeTypeExport;	
	}

	public Boolean getCostingTypeCreate() {
		return costingTypeCreate;	
	}
		
	public void setCostingTypeCreate(Boolean costingTypeCreate) {
		this.costingTypeCreate = costingTypeCreate;	
	}

	public Boolean getCostingTypeRead() {
		return costingTypeRead;	
	}
		
	public void setCostingTypeRead(Boolean costingTypeRead) {
		this.costingTypeRead = costingTypeRead;	
	}

	public Boolean getCostingTypeUpdate() {
		return costingTypeUpdate;	
	}
		
	public void setCostingTypeUpdate(Boolean costingTypeUpdate) {
		this.costingTypeUpdate = costingTypeUpdate;	
	}

	public Boolean getCostingTypeExport() {
		return costingTypeExport;	
	}
		
	public void setCostingTypeExport(Boolean costingTypeExport) {
		this.costingTypeExport = costingTypeExport;	
	}

	public Boolean getWarehouseCreate() {
		return warehouseCreate;	
	}
		
	public void setWarehouseCreate(Boolean warehouseCreate) {
		this.warehouseCreate = warehouseCreate;	
	}

	public Boolean getWarehouseRead() {
		return warehouseRead;	
	}
		
	public void setWarehouseRead(Boolean warehouseRead) {
		this.warehouseRead = warehouseRead;	
	}

	public Boolean getWarehouseUpdate() {
		return warehouseUpdate;	
	}
		
	public void setWarehouseUpdate(Boolean warehouseUpdate) {
		this.warehouseUpdate = warehouseUpdate;	
	}

	public Boolean getWarehouseExport() {
		return warehouseExport;	
	}
		
	public void setWarehouseExport(Boolean warehouseExport) {
		this.warehouseExport = warehouseExport;	
	}

	public Boolean getCoinCreate() {
		return coinCreate;	
	}
		
	public void setCoinCreate(Boolean coinCreate) {
		this.coinCreate = coinCreate;	
	}

	public Boolean getCoinRead() {
		return coinRead;	
	}
		
	public void setCoinRead(Boolean coinRead) {
		this.coinRead = coinRead;	
	}

	public Boolean getCoinUpdate() {
		return coinUpdate;	
	}
		
	public void setCoinUpdate(Boolean coinUpdate) {
		this.coinUpdate = coinUpdate;	
	}

	public Boolean getCoinExport() {
		return coinExport;	
	}
		
	public void setCoinExport(Boolean coinExport) {
		this.coinExport = coinExport;	
	}

	public Boolean getTypeConceptKardexCreate() {
		return typeConceptKardexCreate;	
	}
		
	public void setTypeConceptKardexCreate(Boolean typeConceptKardexCreate) {
		this.typeConceptKardexCreate = typeConceptKardexCreate;	
	}

	public Boolean getTypeConceptKardexRead() {
		return typeConceptKardexRead;	
	}
		
	public void setTypeConceptKardexRead(Boolean typeConceptKardexRead) {
		this.typeConceptKardexRead = typeConceptKardexRead;	
	}

	public Boolean getTypeConceptKardexUpdate() {
		return typeConceptKardexUpdate;	
	}
		
	public void setTypeConceptKardexUpdate(Boolean typeConceptKardexUpdate) {
		this.typeConceptKardexUpdate = typeConceptKardexUpdate;	
	}

	public Boolean getTypeConceptKardexExport() {
		return typeConceptKardexExport;	
	}
		
	public void setTypeConceptKardexExport(Boolean typeConceptKardexExport) {
		this.typeConceptKardexExport = typeConceptKardexExport;	
	}

	public Boolean getConceptKardexCreate() {
		return conceptKardexCreate;	
	}
		
	public void setConceptKardexCreate(Boolean conceptKardexCreate) {
		this.conceptKardexCreate = conceptKardexCreate;	
	}

	public Boolean getConceptKardexRead() {
		return conceptKardexRead;	
	}
		
	public void setConceptKardexRead(Boolean conceptKardexRead) {
		this.conceptKardexRead = conceptKardexRead;	
	}

	public Boolean getConceptKardexUpdate() {
		return conceptKardexUpdate;	
	}
		
	public void setConceptKardexUpdate(Boolean conceptKardexUpdate) {
		this.conceptKardexUpdate = conceptKardexUpdate;	
	}

	public Boolean getConceptKardexExport() {
		return conceptKardexExport;	
	}
		
	public void setConceptKardexExport(Boolean conceptKardexExport) {
		this.conceptKardexExport = conceptKardexExport;	
	}

	public Boolean getContractCreate() {
		return contractCreate;	
	}
		
	public void setContractCreate(Boolean contractCreate) {
		this.contractCreate = contractCreate;	
	}

	public Boolean getContractRead() {
		return contractRead;	
	}
		
	public void setContractRead(Boolean contractRead) {
		this.contractRead = contractRead;	
	}

	public Boolean getContractUpdate() {
		return contractUpdate;	
	}
		
	public void setContractUpdate(Boolean contractUpdate) {
		this.contractUpdate = contractUpdate;	
	}

	public Boolean getContractExport() {
		return contractExport;	
	}
		
	public void setContractExport(Boolean contractExport) {
		this.contractExport = contractExport;	
	}

	public Boolean getUsersCreate() {
		return usersCreate;	
	}
		
	public void setUsersCreate(Boolean usersCreate) {
		this.usersCreate = usersCreate;	
	}

	public Boolean getUsersRead() {
		return usersRead;	
	}
		
	public void setUsersRead(Boolean usersRead) {
		this.usersRead = usersRead;	
	}

	public Boolean getUsersUpdate() {
		return usersUpdate;	
	}
		
	public void setUsersUpdate(Boolean usersUpdate) {
		this.usersUpdate = usersUpdate;	
	}

	public Boolean getUsersExport() {
		return usersExport;	
	}
		
	public void setUsersExport(Boolean usersExport) {
		this.usersExport = usersExport;	
	}

	public Boolean getCurrencyCreate() {
		return currencyCreate;	
	}
		
	public void setCurrencyCreate(Boolean currencyCreate) {
		this.currencyCreate = currencyCreate;	
	}

	public Boolean getCurrencyRead() {
		return currencyRead;	
	}
		
	public void setCurrencyRead(Boolean currencyRead) {
		this.currencyRead = currencyRead;	
	}

	public Boolean getCurrencyUpdate() {
		return currencyUpdate;	
	}
		
	public void setCurrencyUpdate(Boolean currencyUpdate) {
		this.currencyUpdate = currencyUpdate;	
	}

	public Boolean getCurrencyExport() {
		return currencyExport;	
	}
		
	public void setCurrencyExport(Boolean currencyExport) {
		this.currencyExport = currencyExport;	
	}

	public Boolean getExchangeRateCreate() {
		return exchangeRateCreate;	
	}
		
	public void setExchangeRateCreate(Boolean exchangeRateCreate) {
		this.exchangeRateCreate = exchangeRateCreate;	
	}

	public Boolean getExchangeRateRead() {
		return exchangeRateRead;	
	}
		
	public void setExchangeRateRead(Boolean exchangeRateRead) {
		this.exchangeRateRead = exchangeRateRead;	
	}

	public Boolean getExchangeRateUpdate() {
		return exchangeRateUpdate;	
	}
		
	public void setExchangeRateUpdate(Boolean exchangeRateUpdate) {
		this.exchangeRateUpdate = exchangeRateUpdate;	
	}

	public Boolean getExchangeRateExport() {
		return exchangeRateExport;	
	}
		
	public void setExchangeRateExport(Boolean exchangeRateExport) {
		this.exchangeRateExport = exchangeRateExport;	
	}

	public Boolean getContinentCreate() {
		return continentCreate;	
	}
		
	public void setContinentCreate(Boolean continentCreate) {
		this.continentCreate = continentCreate;	
	}

	public Boolean getContinentRead() {
		return continentRead;	
	}
		
	public void setContinentRead(Boolean continentRead) {
		this.continentRead = continentRead;	
	}

	public Boolean getContinentUpdate() {
		return continentUpdate;	
	}
		
	public void setContinentUpdate(Boolean continentUpdate) {
		this.continentUpdate = continentUpdate;	
	}

	public Boolean getContinentExport() {
		return continentExport;	
	}
		
	public void setContinentExport(Boolean continentExport) {
		this.continentExport = continentExport;	
	}

	public Boolean getRegionCreate() {
		return regionCreate;	
	}
		
	public void setRegionCreate(Boolean regionCreate) {
		this.regionCreate = regionCreate;	
	}

	public Boolean getRegionRead() {
		return regionRead;	
	}
		
	public void setRegionRead(Boolean regionRead) {
		this.regionRead = regionRead;	
	}

	public Boolean getRegionUpdate() {
		return regionUpdate;	
	}
		
	public void setRegionUpdate(Boolean regionUpdate) {
		this.regionUpdate = regionUpdate;	
	}

	public Boolean getRegionExport() {
		return regionExport;	
	}
		
	public void setRegionExport(Boolean regionExport) {
		this.regionExport = regionExport;	
	}

	public Boolean getCurrencyCodeCreate() {
		return currencyCodeCreate;	
	}
		
	public void setCurrencyCodeCreate(Boolean currencyCodeCreate) {
		this.currencyCodeCreate = currencyCodeCreate;	
	}

	public Boolean getCurrencyCodeRead() {
		return currencyCodeRead;	
	}
		
	public void setCurrencyCodeRead(Boolean currencyCodeRead) {
		this.currencyCodeRead = currencyCodeRead;	
	}

	public Boolean getCurrencyCodeUpdate() {
		return currencyCodeUpdate;	
	}
		
	public void setCurrencyCodeUpdate(Boolean currencyCodeUpdate) {
		this.currencyCodeUpdate = currencyCodeUpdate;	
	}

	public Boolean getCurrencyCodeExport() {
		return currencyCodeExport;	
	}
		
	public void setCurrencyCodeExport(Boolean currencyCodeExport) {
		this.currencyCodeExport = currencyCodeExport;	
	}

	public Boolean getCountryCreate() {
		return countryCreate;	
	}
		
	public void setCountryCreate(Boolean countryCreate) {
		this.countryCreate = countryCreate;	
	}

	public Boolean getCountryRead() {
		return countryRead;	
	}
		
	public void setCountryRead(Boolean countryRead) {
		this.countryRead = countryRead;	
	}

	public Boolean getCountryUpdate() {
		return countryUpdate;	
	}
		
	public void setCountryUpdate(Boolean countryUpdate) {
		this.countryUpdate = countryUpdate;	
	}

	public Boolean getCountryExport() {
		return countryExport;	
	}
		
	public void setCountryExport(Boolean countryExport) {
		this.countryExport = countryExport;	
	}


	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}
	
}