package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class UsersDetails {
	
	private String login;


	private String password;


	private String role;


	private Long companyId;


	private String company;


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


		
	private Date dateOfModification;
	
	private Long id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDateOfModification() {
		return dateOfModification;
	}
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
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
	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}
	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
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

	
	public UsersDetails toUsersDetails(Users entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = UsersDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.login=login+_embedded;
		this.password=password+_embedded;
		this.role=role+_embedded;
		this.company=entity+_embedded;
		this.companyId=entity.getCompany().getId();

		if (entity.getCompanyPermissions()==null || entity.getCompanyPermissions().equals("")){
			this.companyCreate=false;
			this.companyRead=false;
			this.companyUpdate=false;
			this.companyExport=false;
		}else{
			this.companyCreate=entity.getCompanyPermissions().contains("C");
			this.companyRead=entity.getCompanyPermissions().contains("R");
			this.companyUpdate=entity.getCompanyPermissions().contains("U");
			this.companyExport=entity.getCompanyPermissions().contains("E");
		}if (entity.getStatePermissions()==null || entity.getStatePermissions().equals("")){
			this.stateCreate=false;
			this.stateRead=false;
			this.stateUpdate=false;
			this.stateExport=false;
		}else{
			this.stateCreate=entity.getStatePermissions().contains("C");
			this.stateRead=entity.getStatePermissions().contains("R");
			this.stateUpdate=entity.getStatePermissions().contains("U");
			this.stateExport=entity.getStatePermissions().contains("E");
		}if (entity.getMeasurementUnitPermissions()==null || entity.getMeasurementUnitPermissions().equals("")){
			this.measurementUnitCreate=false;
			this.measurementUnitRead=false;
			this.measurementUnitUpdate=false;
			this.measurementUnitExport=false;
		}else{
			this.measurementUnitCreate=entity.getMeasurementUnitPermissions().contains("C");
			this.measurementUnitRead=entity.getMeasurementUnitPermissions().contains("R");
			this.measurementUnitUpdate=entity.getMeasurementUnitPermissions().contains("U");
			this.measurementUnitExport=entity.getMeasurementUnitPermissions().contains("E");
		}if (entity.getTypeSorterPermissions()==null || entity.getTypeSorterPermissions().equals("")){
			this.typeSorterCreate=false;
			this.typeSorterRead=false;
			this.typeSorterUpdate=false;
			this.typeSorterExport=false;
		}else{
			this.typeSorterCreate=entity.getTypeSorterPermissions().contains("C");
			this.typeSorterRead=entity.getTypeSorterPermissions().contains("R");
			this.typeSorterUpdate=entity.getTypeSorterPermissions().contains("U");
			this.typeSorterExport=entity.getTypeSorterPermissions().contains("E");
		}if (entity.getSorterPermissions()==null || entity.getSorterPermissions().equals("")){
			this.sorterCreate=false;
			this.sorterRead=false;
			this.sorterUpdate=false;
			this.sorterExport=false;
		}else{
			this.sorterCreate=entity.getSorterPermissions().contains("C");
			this.sorterRead=entity.getSorterPermissions().contains("R");
			this.sorterUpdate=entity.getSorterPermissions().contains("U");
			this.sorterExport=entity.getSorterPermissions().contains("E");
		}if (entity.getOperationPermissions()==null || entity.getOperationPermissions().equals("")){
			this.operationCreate=false;
			this.operationRead=false;
			this.operationUpdate=false;
			this.operationExport=false;
		}else{
			this.operationCreate=entity.getOperationPermissions().contains("C");
			this.operationRead=entity.getOperationPermissions().contains("R");
			this.operationUpdate=entity.getOperationPermissions().contains("U");
			this.operationExport=entity.getOperationPermissions().contains("E");
		}if (entity.getReferencePermissions()==null || entity.getReferencePermissions().equals("")){
			this.referenceCreate=false;
			this.referenceRead=false;
			this.referenceUpdate=false;
			this.referenceExport=false;
		}else{
			this.referenceCreate=entity.getReferencePermissions().contains("C");
			this.referenceRead=entity.getReferencePermissions().contains("R");
			this.referenceUpdate=entity.getReferencePermissions().contains("U");
			this.referenceExport=entity.getReferencePermissions().contains("E");
		}if (entity.getReferenceOperationPermissions()==null || entity.getReferenceOperationPermissions().equals("")){
			this.referenceOperationCreate=false;
			this.referenceOperationRead=false;
			this.referenceOperationUpdate=false;
			this.referenceOperationExport=false;
		}else{
			this.referenceOperationCreate=entity.getReferenceOperationPermissions().contains("C");
			this.referenceOperationRead=entity.getReferenceOperationPermissions().contains("R");
			this.referenceOperationUpdate=entity.getReferenceOperationPermissions().contains("U");
			this.referenceOperationExport=entity.getReferenceOperationPermissions().contains("E");
		}if (entity.getTechnicalActionPermissions()==null || entity.getTechnicalActionPermissions().equals("")){
			this.technicalActionCreate=false;
			this.technicalActionRead=false;
			this.technicalActionUpdate=false;
			this.technicalActionExport=false;
		}else{
			this.technicalActionCreate=entity.getTechnicalActionPermissions().contains("C");
			this.technicalActionRead=entity.getTechnicalActionPermissions().contains("R");
			this.technicalActionUpdate=entity.getTechnicalActionPermissions().contains("U");
			this.technicalActionExport=entity.getTechnicalActionPermissions().contains("E");
		}if (entity.getEffectFailPermissions()==null || entity.getEffectFailPermissions().equals("")){
			this.effectFailCreate=false;
			this.effectFailRead=false;
			this.effectFailUpdate=false;
			this.effectFailExport=false;
		}else{
			this.effectFailCreate=entity.getEffectFailPermissions().contains("C");
			this.effectFailRead=entity.getEffectFailPermissions().contains("R");
			this.effectFailUpdate=entity.getEffectFailPermissions().contains("U");
			this.effectFailExport=entity.getEffectFailPermissions().contains("E");
		}if (entity.getEffectFailTechnicalActionPermissions()==null || entity.getEffectFailTechnicalActionPermissions().equals("")){
			this.effectFailTechnicalActionCreate=false;
			this.effectFailTechnicalActionRead=false;
			this.effectFailTechnicalActionUpdate=false;
			this.effectFailTechnicalActionExport=false;
		}else{
			this.effectFailTechnicalActionCreate=entity.getEffectFailTechnicalActionPermissions().contains("C");
			this.effectFailTechnicalActionRead=entity.getEffectFailTechnicalActionPermissions().contains("R");
			this.effectFailTechnicalActionUpdate=entity.getEffectFailTechnicalActionPermissions().contains("U");
			this.effectFailTechnicalActionExport=entity.getEffectFailTechnicalActionPermissions().contains("E");
		}if (entity.getFailureCausePermissions()==null || entity.getFailureCausePermissions().equals("")){
			this.failureCauseCreate=false;
			this.failureCauseRead=false;
			this.failureCauseUpdate=false;
			this.failureCauseExport=false;
		}else{
			this.failureCauseCreate=entity.getFailureCausePermissions().contains("C");
			this.failureCauseRead=entity.getFailureCausePermissions().contains("R");
			this.failureCauseUpdate=entity.getFailureCausePermissions().contains("U");
			this.failureCauseExport=entity.getFailureCausePermissions().contains("E");
		}if (entity.getFailureCauseTechnicalactionPermissions()==null || entity.getFailureCauseTechnicalactionPermissions().equals("")){
			this.failureCauseTechnicalactionCreate=false;
			this.failureCauseTechnicalactionRead=false;
			this.failureCauseTechnicalactionUpdate=false;
			this.failureCauseTechnicalactionExport=false;
		}else{
			this.failureCauseTechnicalactionCreate=entity.getFailureCauseTechnicalactionPermissions().contains("C");
			this.failureCauseTechnicalactionRead=entity.getFailureCauseTechnicalactionPermissions().contains("R");
			this.failureCauseTechnicalactionUpdate=entity.getFailureCauseTechnicalactionPermissions().contains("U");
			this.failureCauseTechnicalactionExport=entity.getFailureCauseTechnicalactionPermissions().contains("E");
		}if (entity.getTypeCauseClosePermissions()==null || entity.getTypeCauseClosePermissions().equals("")){
			this.typeCauseCloseCreate=false;
			this.typeCauseCloseRead=false;
			this.typeCauseCloseUpdate=false;
			this.typeCauseCloseExport=false;
		}else{
			this.typeCauseCloseCreate=entity.getTypeCauseClosePermissions().contains("C");
			this.typeCauseCloseRead=entity.getTypeCauseClosePermissions().contains("R");
			this.typeCauseCloseUpdate=entity.getTypeCauseClosePermissions().contains("U");
			this.typeCauseCloseExport=entity.getTypeCauseClosePermissions().contains("E");
		}if (entity.getCauseClosePermissions()==null || entity.getCauseClosePermissions().equals("")){
			this.causeCloseCreate=false;
			this.causeCloseRead=false;
			this.causeCloseUpdate=false;
			this.causeCloseExport=false;
		}else{
			this.causeCloseCreate=entity.getCauseClosePermissions().contains("C");
			this.causeCloseRead=entity.getCauseClosePermissions().contains("R");
			this.causeCloseUpdate=entity.getCauseClosePermissions().contains("U");
			this.causeCloseExport=entity.getCauseClosePermissions().contains("E");
		}if (entity.getMaintenanceActivityPermissions()==null || entity.getMaintenanceActivityPermissions().equals("")){
			this.maintenanceActivityCreate=false;
			this.maintenanceActivityRead=false;
			this.maintenanceActivityUpdate=false;
			this.maintenanceActivityExport=false;
		}else{
			this.maintenanceActivityCreate=entity.getMaintenanceActivityPermissions().contains("C");
			this.maintenanceActivityRead=entity.getMaintenanceActivityPermissions().contains("R");
			this.maintenanceActivityUpdate=entity.getMaintenanceActivityPermissions().contains("U");
			this.maintenanceActivityExport=entity.getMaintenanceActivityPermissions().contains("E");
		}if (entity.getMaintenanceWorkPermissions()==null || entity.getMaintenanceWorkPermissions().equals("")){
			this.maintenanceWorkCreate=false;
			this.maintenanceWorkRead=false;
			this.maintenanceWorkUpdate=false;
			this.maintenanceWorkExport=false;
		}else{
			this.maintenanceWorkCreate=entity.getMaintenanceWorkPermissions().contains("C");
			this.maintenanceWorkRead=entity.getMaintenanceWorkPermissions().contains("R");
			this.maintenanceWorkUpdate=entity.getMaintenanceWorkPermissions().contains("U");
			this.maintenanceWorkExport=entity.getMaintenanceWorkPermissions().contains("E");
		}if (entity.getTypeMaintenancePermissions()==null || entity.getTypeMaintenancePermissions().equals("")){
			this.typeMaintenanceCreate=false;
			this.typeMaintenanceRead=false;
			this.typeMaintenanceUpdate=false;
			this.typeMaintenanceExport=false;
		}else{
			this.typeMaintenanceCreate=entity.getTypeMaintenancePermissions().contains("C");
			this.typeMaintenanceRead=entity.getTypeMaintenancePermissions().contains("R");
			this.typeMaintenanceUpdate=entity.getTypeMaintenancePermissions().contains("U");
			this.typeMaintenanceExport=entity.getTypeMaintenancePermissions().contains("E");
		}if (entity.getCausePendingPermissions()==null || entity.getCausePendingPermissions().equals("")){
			this.causePendingCreate=false;
			this.causePendingRead=false;
			this.causePendingUpdate=false;
			this.causePendingExport=false;
		}else{
			this.causePendingCreate=entity.getCausePendingPermissions().contains("C");
			this.causePendingRead=entity.getCausePendingPermissions().contains("R");
			this.causePendingUpdate=entity.getCausePendingPermissions().contains("U");
			this.causePendingExport=entity.getCausePendingPermissions().contains("E");
		}if (entity.getTypeWorkPermissions()==null || entity.getTypeWorkPermissions().equals("")){
			this.typeWorkCreate=false;
			this.typeWorkRead=false;
			this.typeWorkUpdate=false;
			this.typeWorkExport=false;
		}else{
			this.typeWorkCreate=entity.getTypeWorkPermissions().contains("C");
			this.typeWorkRead=entity.getTypeWorkPermissions().contains("R");
			this.typeWorkUpdate=entity.getTypeWorkPermissions().contains("U");
			this.typeWorkExport=entity.getTypeWorkPermissions().contains("E");
		}if (entity.getOtherConceptsPermissions()==null || entity.getOtherConceptsPermissions().equals("")){
			this.otherConceptsCreate=false;
			this.otherConceptsRead=false;
			this.otherConceptsUpdate=false;
			this.otherConceptsExport=false;
		}else{
			this.otherConceptsCreate=entity.getOtherConceptsPermissions().contains("C");
			this.otherConceptsRead=entity.getOtherConceptsPermissions().contains("R");
			this.otherConceptsUpdate=entity.getOtherConceptsPermissions().contains("U");
			this.otherConceptsExport=entity.getOtherConceptsPermissions().contains("E");
		}if (entity.getStateCostCentersPermissions()==null || entity.getStateCostCentersPermissions().equals("")){
			this.stateCostCentersCreate=false;
			this.stateCostCentersRead=false;
			this.stateCostCentersUpdate=false;
			this.stateCostCentersExport=false;
		}else{
			this.stateCostCentersCreate=entity.getStateCostCentersPermissions().contains("C");
			this.stateCostCentersRead=entity.getStateCostCentersPermissions().contains("R");
			this.stateCostCentersUpdate=entity.getStateCostCentersPermissions().contains("U");
			this.stateCostCentersExport=entity.getStateCostCentersPermissions().contains("E");
		}if (entity.getCostCentersCustomersPermissions()==null || entity.getCostCentersCustomersPermissions().equals("")){
			this.costCentersCustomersCreate=false;
			this.costCentersCustomersRead=false;
			this.costCentersCustomersUpdate=false;
			this.costCentersCustomersExport=false;
		}else{
			this.costCentersCustomersCreate=entity.getCostCentersCustomersPermissions().contains("C");
			this.costCentersCustomersRead=entity.getCostCentersCustomersPermissions().contains("R");
			this.costCentersCustomersUpdate=entity.getCostCentersCustomersPermissions().contains("U");
			this.costCentersCustomersExport=entity.getCostCentersCustomersPermissions().contains("E");
		}if (entity.getResponsibleCenterPermissions()==null || entity.getResponsibleCenterPermissions().equals("")){
			this.responsibleCenterCreate=false;
			this.responsibleCenterRead=false;
			this.responsibleCenterUpdate=false;
			this.responsibleCenterExport=false;
		}else{
			this.responsibleCenterCreate=entity.getResponsibleCenterPermissions().contains("C");
			this.responsibleCenterRead=entity.getResponsibleCenterPermissions().contains("R");
			this.responsibleCenterUpdate=entity.getResponsibleCenterPermissions().contains("U");
			this.responsibleCenterExport=entity.getResponsibleCenterPermissions().contains("E");
		}if (entity.getJobPermissions()==null || entity.getJobPermissions().equals("")){
			this.jobCreate=false;
			this.jobRead=false;
			this.jobUpdate=false;
			this.jobExport=false;
		}else{
			this.jobCreate=entity.getJobPermissions().contains("C");
			this.jobRead=entity.getJobPermissions().contains("R");
			this.jobUpdate=entity.getJobPermissions().contains("U");
			this.jobExport=entity.getJobPermissions().contains("E");
		}if (entity.getThirdPermissions()==null || entity.getThirdPermissions().equals("")){
			this.thirdCreate=false;
			this.thirdRead=false;
			this.thirdUpdate=false;
			this.thirdExport=false;
		}else{
			this.thirdCreate=entity.getThirdPermissions().contains("C");
			this.thirdRead=entity.getThirdPermissions().contains("R");
			this.thirdUpdate=entity.getThirdPermissions().contains("U");
			this.thirdExport=entity.getThirdPermissions().contains("E");
		}if (entity.getJobThirdPermissions()==null || entity.getJobThirdPermissions().equals("")){
			this.jobThirdCreate=false;
			this.jobThirdRead=false;
			this.jobThirdUpdate=false;
			this.jobThirdExport=false;
		}else{
			this.jobThirdCreate=entity.getJobThirdPermissions().contains("C");
			this.jobThirdRead=entity.getJobThirdPermissions().contains("R");
			this.jobThirdUpdate=entity.getJobThirdPermissions().contains("U");
			this.jobThirdExport=entity.getJobThirdPermissions().contains("E");
		}if (entity.getResponsiblePermissions()==null || entity.getResponsiblePermissions().equals("")){
			this.responsibleCreate=false;
			this.responsibleRead=false;
			this.responsibleUpdate=false;
			this.responsibleExport=false;
		}else{
			this.responsibleCreate=entity.getResponsiblePermissions().contains("C");
			this.responsibleRead=entity.getResponsiblePermissions().contains("R");
			this.responsibleUpdate=entity.getResponsiblePermissions().contains("U");
			this.responsibleExport=entity.getResponsiblePermissions().contains("E");
		}if (entity.getAuthorizerCostCenterPermissions()==null || entity.getAuthorizerCostCenterPermissions().equals("")){
			this.authorizerCostCenterCreate=false;
			this.authorizerCostCenterRead=false;
			this.authorizerCostCenterUpdate=false;
			this.authorizerCostCenterExport=false;
		}else{
			this.authorizerCostCenterCreate=entity.getAuthorizerCostCenterPermissions().contains("C");
			this.authorizerCostCenterRead=entity.getAuthorizerCostCenterPermissions().contains("R");
			this.authorizerCostCenterUpdate=entity.getAuthorizerCostCenterPermissions().contains("U");
			this.authorizerCostCenterExport=entity.getAuthorizerCostCenterPermissions().contains("E");
		}if (entity.getCharacteristicPermissions()==null || entity.getCharacteristicPermissions().equals("")){
			this.characteristicCreate=false;
			this.characteristicRead=false;
			this.characteristicUpdate=false;
			this.characteristicExport=false;
		}else{
			this.characteristicCreate=entity.getCharacteristicPermissions().contains("C");
			this.characteristicRead=entity.getCharacteristicPermissions().contains("R");
			this.characteristicUpdate=entity.getCharacteristicPermissions().contains("U");
			this.characteristicExport=entity.getCharacteristicPermissions().contains("E");
		}if (entity.getAccountantPermissions()==null || entity.getAccountantPermissions().equals("")){
			this.accountantCreate=false;
			this.accountantRead=false;
			this.accountantUpdate=false;
			this.accountantExport=false;
		}else{
			this.accountantCreate=entity.getAccountantPermissions().contains("C");
			this.accountantRead=entity.getAccountantPermissions().contains("R");
			this.accountantUpdate=entity.getAccountantPermissions().contains("U");
			this.accountantExport=entity.getAccountantPermissions().contains("E");
		}if (entity.getPhysicalLocationPermissions()==null || entity.getPhysicalLocationPermissions().equals("")){
			this.physicalLocationCreate=false;
			this.physicalLocationRead=false;
			this.physicalLocationUpdate=false;
			this.physicalLocationExport=false;
		}else{
			this.physicalLocationCreate=entity.getPhysicalLocationPermissions().contains("C");
			this.physicalLocationRead=entity.getPhysicalLocationPermissions().contains("R");
			this.physicalLocationUpdate=entity.getPhysicalLocationPermissions().contains("U");
			this.physicalLocationExport=entity.getPhysicalLocationPermissions().contains("E");
		}if (entity.getStoreTypePermissions()==null || entity.getStoreTypePermissions().equals("")){
			this.storeTypeCreate=false;
			this.storeTypeRead=false;
			this.storeTypeUpdate=false;
			this.storeTypeExport=false;
		}else{
			this.storeTypeCreate=entity.getStoreTypePermissions().contains("C");
			this.storeTypeRead=entity.getStoreTypePermissions().contains("R");
			this.storeTypeUpdate=entity.getStoreTypePermissions().contains("U");
			this.storeTypeExport=entity.getStoreTypePermissions().contains("E");
		}if (entity.getCostingTypePermissions()==null || entity.getCostingTypePermissions().equals("")){
			this.costingTypeCreate=false;
			this.costingTypeRead=false;
			this.costingTypeUpdate=false;
			this.costingTypeExport=false;
		}else{
			this.costingTypeCreate=entity.getCostingTypePermissions().contains("C");
			this.costingTypeRead=entity.getCostingTypePermissions().contains("R");
			this.costingTypeUpdate=entity.getCostingTypePermissions().contains("U");
			this.costingTypeExport=entity.getCostingTypePermissions().contains("E");
		}if (entity.getWarehousePermissions()==null || entity.getWarehousePermissions().equals("")){
			this.warehouseCreate=false;
			this.warehouseRead=false;
			this.warehouseUpdate=false;
			this.warehouseExport=false;
		}else{
			this.warehouseCreate=entity.getWarehousePermissions().contains("C");
			this.warehouseRead=entity.getWarehousePermissions().contains("R");
			this.warehouseUpdate=entity.getWarehousePermissions().contains("U");
			this.warehouseExport=entity.getWarehousePermissions().contains("E");
		}if (entity.getCoinPermissions()==null || entity.getCoinPermissions().equals("")){
			this.coinCreate=false;
			this.coinRead=false;
			this.coinUpdate=false;
			this.coinExport=false;
		}else{
			this.coinCreate=entity.getCoinPermissions().contains("C");
			this.coinRead=entity.getCoinPermissions().contains("R");
			this.coinUpdate=entity.getCoinPermissions().contains("U");
			this.coinExport=entity.getCoinPermissions().contains("E");
		}if (entity.getTypeConceptKardexPermissions()==null || entity.getTypeConceptKardexPermissions().equals("")){
			this.typeConceptKardexCreate=false;
			this.typeConceptKardexRead=false;
			this.typeConceptKardexUpdate=false;
			this.typeConceptKardexExport=false;
		}else{
			this.typeConceptKardexCreate=entity.getTypeConceptKardexPermissions().contains("C");
			this.typeConceptKardexRead=entity.getTypeConceptKardexPermissions().contains("R");
			this.typeConceptKardexUpdate=entity.getTypeConceptKardexPermissions().contains("U");
			this.typeConceptKardexExport=entity.getTypeConceptKardexPermissions().contains("E");
		}if (entity.getConceptKardexPermissions()==null || entity.getConceptKardexPermissions().equals("")){
			this.conceptKardexCreate=false;
			this.conceptKardexRead=false;
			this.conceptKardexUpdate=false;
			this.conceptKardexExport=false;
		}else{
			this.conceptKardexCreate=entity.getConceptKardexPermissions().contains("C");
			this.conceptKardexRead=entity.getConceptKardexPermissions().contains("R");
			this.conceptKardexUpdate=entity.getConceptKardexPermissions().contains("U");
			this.conceptKardexExport=entity.getConceptKardexPermissions().contains("E");
		}if (entity.getContractPermissions()==null || entity.getContractPermissions().equals("")){
			this.contractCreate=false;
			this.contractRead=false;
			this.contractUpdate=false;
			this.contractExport=false;
		}else{
			this.contractCreate=entity.getContractPermissions().contains("C");
			this.contractRead=entity.getContractPermissions().contains("R");
			this.contractUpdate=entity.getContractPermissions().contains("U");
			this.contractExport=entity.getContractPermissions().contains("E");
		}if (entity.getCurrencyPermissions()==null || entity.getCurrencyPermissions().equals("")){
			this.currencyCreate=false;
			this.currencyRead=false;
			this.currencyUpdate=false;
			this.currencyExport=false;
		}else{
			this.currencyCreate=entity.getCurrencyPermissions().contains("C");
			this.currencyRead=entity.getCurrencyPermissions().contains("R");
			this.currencyUpdate=entity.getCurrencyPermissions().contains("U");
			this.currencyExport=entity.getCurrencyPermissions().contains("E");
		}if (entity.getExchangeRatePermissions()==null || entity.getExchangeRatePermissions().equals("")){
			this.exchangeRateCreate=false;
			this.exchangeRateRead=false;
			this.exchangeRateUpdate=false;
			this.exchangeRateExport=false;
		}else{
			this.exchangeRateCreate=entity.getExchangeRatePermissions().contains("C");
			this.exchangeRateRead=entity.getExchangeRatePermissions().contains("R");
			this.exchangeRateUpdate=entity.getExchangeRatePermissions().contains("U");
			this.exchangeRateExport=entity.getExchangeRatePermissions().contains("E");
		}if (entity.getContinentPermissions()==null || entity.getContinentPermissions().equals("")){
			this.continentCreate=false;
			this.continentRead=false;
			this.continentUpdate=false;
			this.continentExport=false;
		}else{
			this.continentCreate=entity.getContinentPermissions().contains("C");
			this.continentRead=entity.getContinentPermissions().contains("R");
			this.continentUpdate=entity.getContinentPermissions().contains("U");
			this.continentExport=entity.getContinentPermissions().contains("E");
		}if (entity.getRegionPermissions()==null || entity.getRegionPermissions().equals("")){
			this.regionCreate=false;
			this.regionRead=false;
			this.regionUpdate=false;
			this.regionExport=false;
		}else{
			this.regionCreate=entity.getRegionPermissions().contains("C");
			this.regionRead=entity.getRegionPermissions().contains("R");
			this.regionUpdate=entity.getRegionPermissions().contains("U");
			this.regionExport=entity.getRegionPermissions().contains("E");
		}if (entity.getCurrencyCodePermissions()==null || entity.getCurrencyCodePermissions().equals("")){
			this.currencyCodeCreate=false;
			this.currencyCodeRead=false;
			this.currencyCodeUpdate=false;
			this.currencyCodeExport=false;
		}else{
			this.currencyCodeCreate=entity.getCurrencyCodePermissions().contains("C");
			this.currencyCodeRead=entity.getCurrencyCodePermissions().contains("R");
			this.currencyCodeUpdate=entity.getCurrencyCodePermissions().contains("U");
			this.currencyCodeExport=entity.getCurrencyCodePermissions().contains("E");
		}if (entity.getCountryPermissions()==null || entity.getCountryPermissions().equals("")){
			this.countryCreate=false;
			this.countryRead=false;
			this.countryUpdate=false;
			this.countryExport=false;
		}else{
			this.countryCreate=entity.getCountryPermissions().contains("C");
			this.countryRead=entity.getCountryPermissions().contains("R");
			this.countryUpdate=entity.getCountryPermissions().contains("U");
			this.countryExport=entity.getCountryPermissions().contains("E");
		}this.usersCreate=true;this.usersRead=true;this.usersUpdate=true;this.usersExport=true;
		return this;
	}
}