package co.com.cybersoft.purchase.tables.web.domain.users;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UsersFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String login;


	public String getLogin() {
		return login;	
	}
		
	public void setLogin(String login) {
		this.login = login;	
	}

	private String password;


	public String getPassword() {
		return password;	
	}
		
	public void setPassword(String password) {
		this.password = password;	
	}

	private String role;


	public String getRole() {
		return role;	
	}
		
	public void setRole(String role) {
		this.role = role;	
	}

	private String company;


	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	private String companyCreate;


	public String getCompanyCreate() {
		return companyCreate;	
	}
		
	public void setCompanyCreate(String companyCreate) {
		this.companyCreate = companyCreate;	
	}

	private String companyRead;


	public String getCompanyRead() {
		return companyRead;	
	}
		
	public void setCompanyRead(String companyRead) {
		this.companyRead = companyRead;	
	}

	private String companyUpdate;


	public String getCompanyUpdate() {
		return companyUpdate;	
	}
		
	public void setCompanyUpdate(String companyUpdate) {
		this.companyUpdate = companyUpdate;	
	}

	private String companyExport;


	public String getCompanyExport() {
		return companyExport;	
	}
		
	public void setCompanyExport(String companyExport) {
		this.companyExport = companyExport;	
	}

	private String stateCreate;


	public String getStateCreate() {
		return stateCreate;	
	}
		
	public void setStateCreate(String stateCreate) {
		this.stateCreate = stateCreate;	
	}

	private String stateRead;


	public String getStateRead() {
		return stateRead;	
	}
		
	public void setStateRead(String stateRead) {
		this.stateRead = stateRead;	
	}

	private String stateUpdate;


	public String getStateUpdate() {
		return stateUpdate;	
	}
		
	public void setStateUpdate(String stateUpdate) {
		this.stateUpdate = stateUpdate;	
	}

	private String stateExport;


	public String getStateExport() {
		return stateExport;	
	}
		
	public void setStateExport(String stateExport) {
		this.stateExport = stateExport;	
	}

	private String measurementUnitCreate;


	public String getMeasurementUnitCreate() {
		return measurementUnitCreate;	
	}
		
	public void setMeasurementUnitCreate(String measurementUnitCreate) {
		this.measurementUnitCreate = measurementUnitCreate;	
	}

	private String measurementUnitRead;


	public String getMeasurementUnitRead() {
		return measurementUnitRead;	
	}
		
	public void setMeasurementUnitRead(String measurementUnitRead) {
		this.measurementUnitRead = measurementUnitRead;	
	}

	private String measurementUnitUpdate;


	public String getMeasurementUnitUpdate() {
		return measurementUnitUpdate;	
	}
		
	public void setMeasurementUnitUpdate(String measurementUnitUpdate) {
		this.measurementUnitUpdate = measurementUnitUpdate;	
	}

	private String measurementUnitExport;


	public String getMeasurementUnitExport() {
		return measurementUnitExport;	
	}
		
	public void setMeasurementUnitExport(String measurementUnitExport) {
		this.measurementUnitExport = measurementUnitExport;	
	}

	private String typeSorterCreate;


	public String getTypeSorterCreate() {
		return typeSorterCreate;	
	}
		
	public void setTypeSorterCreate(String typeSorterCreate) {
		this.typeSorterCreate = typeSorterCreate;	
	}

	private String typeSorterRead;


	public String getTypeSorterRead() {
		return typeSorterRead;	
	}
		
	public void setTypeSorterRead(String typeSorterRead) {
		this.typeSorterRead = typeSorterRead;	
	}

	private String typeSorterUpdate;


	public String getTypeSorterUpdate() {
		return typeSorterUpdate;	
	}
		
	public void setTypeSorterUpdate(String typeSorterUpdate) {
		this.typeSorterUpdate = typeSorterUpdate;	
	}

	private String typeSorterExport;


	public String getTypeSorterExport() {
		return typeSorterExport;	
	}
		
	public void setTypeSorterExport(String typeSorterExport) {
		this.typeSorterExport = typeSorterExport;	
	}

	private String sorterCreate;


	public String getSorterCreate() {
		return sorterCreate;	
	}
		
	public void setSorterCreate(String sorterCreate) {
		this.sorterCreate = sorterCreate;	
	}

	private String sorterRead;


	public String getSorterRead() {
		return sorterRead;	
	}
		
	public void setSorterRead(String sorterRead) {
		this.sorterRead = sorterRead;	
	}

	private String sorterUpdate;


	public String getSorterUpdate() {
		return sorterUpdate;	
	}
		
	public void setSorterUpdate(String sorterUpdate) {
		this.sorterUpdate = sorterUpdate;	
	}

	private String sorterExport;


	public String getSorterExport() {
		return sorterExport;	
	}
		
	public void setSorterExport(String sorterExport) {
		this.sorterExport = sorterExport;	
	}

	private String operationCreate;


	public String getOperationCreate() {
		return operationCreate;	
	}
		
	public void setOperationCreate(String operationCreate) {
		this.operationCreate = operationCreate;	
	}

	private String operationRead;


	public String getOperationRead() {
		return operationRead;	
	}
		
	public void setOperationRead(String operationRead) {
		this.operationRead = operationRead;	
	}

	private String operationUpdate;


	public String getOperationUpdate() {
		return operationUpdate;	
	}
		
	public void setOperationUpdate(String operationUpdate) {
		this.operationUpdate = operationUpdate;	
	}

	private String operationExport;


	public String getOperationExport() {
		return operationExport;	
	}
		
	public void setOperationExport(String operationExport) {
		this.operationExport = operationExport;	
	}

	private String referenceCreate;


	public String getReferenceCreate() {
		return referenceCreate;	
	}
		
	public void setReferenceCreate(String referenceCreate) {
		this.referenceCreate = referenceCreate;	
	}

	private String referenceRead;


	public String getReferenceRead() {
		return referenceRead;	
	}
		
	public void setReferenceRead(String referenceRead) {
		this.referenceRead = referenceRead;	
	}

	private String referenceUpdate;


	public String getReferenceUpdate() {
		return referenceUpdate;	
	}
		
	public void setReferenceUpdate(String referenceUpdate) {
		this.referenceUpdate = referenceUpdate;	
	}

	private String referenceExport;


	public String getReferenceExport() {
		return referenceExport;	
	}
		
	public void setReferenceExport(String referenceExport) {
		this.referenceExport = referenceExport;	
	}

	private String referenceOperationCreate;


	public String getReferenceOperationCreate() {
		return referenceOperationCreate;	
	}
		
	public void setReferenceOperationCreate(String referenceOperationCreate) {
		this.referenceOperationCreate = referenceOperationCreate;	
	}

	private String referenceOperationRead;


	public String getReferenceOperationRead() {
		return referenceOperationRead;	
	}
		
	public void setReferenceOperationRead(String referenceOperationRead) {
		this.referenceOperationRead = referenceOperationRead;	
	}

	private String referenceOperationUpdate;


	public String getReferenceOperationUpdate() {
		return referenceOperationUpdate;	
	}
		
	public void setReferenceOperationUpdate(String referenceOperationUpdate) {
		this.referenceOperationUpdate = referenceOperationUpdate;	
	}

	private String referenceOperationExport;


	public String getReferenceOperationExport() {
		return referenceOperationExport;	
	}
		
	public void setReferenceOperationExport(String referenceOperationExport) {
		this.referenceOperationExport = referenceOperationExport;	
	}

	private String technicalActionCreate;


	public String getTechnicalActionCreate() {
		return technicalActionCreate;	
	}
		
	public void setTechnicalActionCreate(String technicalActionCreate) {
		this.technicalActionCreate = technicalActionCreate;	
	}

	private String technicalActionRead;


	public String getTechnicalActionRead() {
		return technicalActionRead;	
	}
		
	public void setTechnicalActionRead(String technicalActionRead) {
		this.technicalActionRead = technicalActionRead;	
	}

	private String technicalActionUpdate;


	public String getTechnicalActionUpdate() {
		return technicalActionUpdate;	
	}
		
	public void setTechnicalActionUpdate(String technicalActionUpdate) {
		this.technicalActionUpdate = technicalActionUpdate;	
	}

	private String technicalActionExport;


	public String getTechnicalActionExport() {
		return technicalActionExport;	
	}
		
	public void setTechnicalActionExport(String technicalActionExport) {
		this.technicalActionExport = technicalActionExport;	
	}

	private String effectFailCreate;


	public String getEffectFailCreate() {
		return effectFailCreate;	
	}
		
	public void setEffectFailCreate(String effectFailCreate) {
		this.effectFailCreate = effectFailCreate;	
	}

	private String effectFailRead;


	public String getEffectFailRead() {
		return effectFailRead;	
	}
		
	public void setEffectFailRead(String effectFailRead) {
		this.effectFailRead = effectFailRead;	
	}

	private String effectFailUpdate;


	public String getEffectFailUpdate() {
		return effectFailUpdate;	
	}
		
	public void setEffectFailUpdate(String effectFailUpdate) {
		this.effectFailUpdate = effectFailUpdate;	
	}

	private String effectFailExport;


	public String getEffectFailExport() {
		return effectFailExport;	
	}
		
	public void setEffectFailExport(String effectFailExport) {
		this.effectFailExport = effectFailExport;	
	}

	private String effectFailTechnicalActionCreate;


	public String getEffectFailTechnicalActionCreate() {
		return effectFailTechnicalActionCreate;	
	}
		
	public void setEffectFailTechnicalActionCreate(String effectFailTechnicalActionCreate) {
		this.effectFailTechnicalActionCreate = effectFailTechnicalActionCreate;	
	}

	private String effectFailTechnicalActionRead;


	public String getEffectFailTechnicalActionRead() {
		return effectFailTechnicalActionRead;	
	}
		
	public void setEffectFailTechnicalActionRead(String effectFailTechnicalActionRead) {
		this.effectFailTechnicalActionRead = effectFailTechnicalActionRead;	
	}

	private String effectFailTechnicalActionUpdate;


	public String getEffectFailTechnicalActionUpdate() {
		return effectFailTechnicalActionUpdate;	
	}
		
	public void setEffectFailTechnicalActionUpdate(String effectFailTechnicalActionUpdate) {
		this.effectFailTechnicalActionUpdate = effectFailTechnicalActionUpdate;	
	}

	private String effectFailTechnicalActionExport;


	public String getEffectFailTechnicalActionExport() {
		return effectFailTechnicalActionExport;	
	}
		
	public void setEffectFailTechnicalActionExport(String effectFailTechnicalActionExport) {
		this.effectFailTechnicalActionExport = effectFailTechnicalActionExport;	
	}

	private String failureCauseCreate;


	public String getFailureCauseCreate() {
		return failureCauseCreate;	
	}
		
	public void setFailureCauseCreate(String failureCauseCreate) {
		this.failureCauseCreate = failureCauseCreate;	
	}

	private String failureCauseRead;


	public String getFailureCauseRead() {
		return failureCauseRead;	
	}
		
	public void setFailureCauseRead(String failureCauseRead) {
		this.failureCauseRead = failureCauseRead;	
	}

	private String failureCauseUpdate;


	public String getFailureCauseUpdate() {
		return failureCauseUpdate;	
	}
		
	public void setFailureCauseUpdate(String failureCauseUpdate) {
		this.failureCauseUpdate = failureCauseUpdate;	
	}

	private String failureCauseExport;


	public String getFailureCauseExport() {
		return failureCauseExport;	
	}
		
	public void setFailureCauseExport(String failureCauseExport) {
		this.failureCauseExport = failureCauseExport;	
	}

	private String failureCauseTechnicalactionCreate;


	public String getFailureCauseTechnicalactionCreate() {
		return failureCauseTechnicalactionCreate;	
	}
		
	public void setFailureCauseTechnicalactionCreate(String failureCauseTechnicalactionCreate) {
		this.failureCauseTechnicalactionCreate = failureCauseTechnicalactionCreate;	
	}

	private String failureCauseTechnicalactionRead;


	public String getFailureCauseTechnicalactionRead() {
		return failureCauseTechnicalactionRead;	
	}
		
	public void setFailureCauseTechnicalactionRead(String failureCauseTechnicalactionRead) {
		this.failureCauseTechnicalactionRead = failureCauseTechnicalactionRead;	
	}

	private String failureCauseTechnicalactionUpdate;


	public String getFailureCauseTechnicalactionUpdate() {
		return failureCauseTechnicalactionUpdate;	
	}
		
	public void setFailureCauseTechnicalactionUpdate(String failureCauseTechnicalactionUpdate) {
		this.failureCauseTechnicalactionUpdate = failureCauseTechnicalactionUpdate;	
	}

	private String failureCauseTechnicalactionExport;


	public String getFailureCauseTechnicalactionExport() {
		return failureCauseTechnicalactionExport;	
	}
		
	public void setFailureCauseTechnicalactionExport(String failureCauseTechnicalactionExport) {
		this.failureCauseTechnicalactionExport = failureCauseTechnicalactionExport;	
	}

	private String typeCauseCloseCreate;


	public String getTypeCauseCloseCreate() {
		return typeCauseCloseCreate;	
	}
		
	public void setTypeCauseCloseCreate(String typeCauseCloseCreate) {
		this.typeCauseCloseCreate = typeCauseCloseCreate;	
	}

	private String typeCauseCloseRead;


	public String getTypeCauseCloseRead() {
		return typeCauseCloseRead;	
	}
		
	public void setTypeCauseCloseRead(String typeCauseCloseRead) {
		this.typeCauseCloseRead = typeCauseCloseRead;	
	}

	private String typeCauseCloseUpdate;


	public String getTypeCauseCloseUpdate() {
		return typeCauseCloseUpdate;	
	}
		
	public void setTypeCauseCloseUpdate(String typeCauseCloseUpdate) {
		this.typeCauseCloseUpdate = typeCauseCloseUpdate;	
	}

	private String typeCauseCloseExport;


	public String getTypeCauseCloseExport() {
		return typeCauseCloseExport;	
	}
		
	public void setTypeCauseCloseExport(String typeCauseCloseExport) {
		this.typeCauseCloseExport = typeCauseCloseExport;	
	}

	private String causeCloseCreate;


	public String getCauseCloseCreate() {
		return causeCloseCreate;	
	}
		
	public void setCauseCloseCreate(String causeCloseCreate) {
		this.causeCloseCreate = causeCloseCreate;	
	}

	private String causeCloseRead;


	public String getCauseCloseRead() {
		return causeCloseRead;	
	}
		
	public void setCauseCloseRead(String causeCloseRead) {
		this.causeCloseRead = causeCloseRead;	
	}

	private String causeCloseUpdate;


	public String getCauseCloseUpdate() {
		return causeCloseUpdate;	
	}
		
	public void setCauseCloseUpdate(String causeCloseUpdate) {
		this.causeCloseUpdate = causeCloseUpdate;	
	}

	private String causeCloseExport;


	public String getCauseCloseExport() {
		return causeCloseExport;	
	}
		
	public void setCauseCloseExport(String causeCloseExport) {
		this.causeCloseExport = causeCloseExport;	
	}

	private String maintenanceActivityCreate;


	public String getMaintenanceActivityCreate() {
		return maintenanceActivityCreate;	
	}
		
	public void setMaintenanceActivityCreate(String maintenanceActivityCreate) {
		this.maintenanceActivityCreate = maintenanceActivityCreate;	
	}

	private String maintenanceActivityRead;


	public String getMaintenanceActivityRead() {
		return maintenanceActivityRead;	
	}
		
	public void setMaintenanceActivityRead(String maintenanceActivityRead) {
		this.maintenanceActivityRead = maintenanceActivityRead;	
	}

	private String maintenanceActivityUpdate;


	public String getMaintenanceActivityUpdate() {
		return maintenanceActivityUpdate;	
	}
		
	public void setMaintenanceActivityUpdate(String maintenanceActivityUpdate) {
		this.maintenanceActivityUpdate = maintenanceActivityUpdate;	
	}

	private String maintenanceActivityExport;


	public String getMaintenanceActivityExport() {
		return maintenanceActivityExport;	
	}
		
	public void setMaintenanceActivityExport(String maintenanceActivityExport) {
		this.maintenanceActivityExport = maintenanceActivityExport;	
	}

	private String maintenanceWorkCreate;


	public String getMaintenanceWorkCreate() {
		return maintenanceWorkCreate;	
	}
		
	public void setMaintenanceWorkCreate(String maintenanceWorkCreate) {
		this.maintenanceWorkCreate = maintenanceWorkCreate;	
	}

	private String maintenanceWorkRead;


	public String getMaintenanceWorkRead() {
		return maintenanceWorkRead;	
	}
		
	public void setMaintenanceWorkRead(String maintenanceWorkRead) {
		this.maintenanceWorkRead = maintenanceWorkRead;	
	}

	private String maintenanceWorkUpdate;


	public String getMaintenanceWorkUpdate() {
		return maintenanceWorkUpdate;	
	}
		
	public void setMaintenanceWorkUpdate(String maintenanceWorkUpdate) {
		this.maintenanceWorkUpdate = maintenanceWorkUpdate;	
	}

	private String maintenanceWorkExport;


	public String getMaintenanceWorkExport() {
		return maintenanceWorkExport;	
	}
		
	public void setMaintenanceWorkExport(String maintenanceWorkExport) {
		this.maintenanceWorkExport = maintenanceWorkExport;	
	}

	private String typeMaintenanceCreate;


	public String getTypeMaintenanceCreate() {
		return typeMaintenanceCreate;	
	}
		
	public void setTypeMaintenanceCreate(String typeMaintenanceCreate) {
		this.typeMaintenanceCreate = typeMaintenanceCreate;	
	}

	private String typeMaintenanceRead;


	public String getTypeMaintenanceRead() {
		return typeMaintenanceRead;	
	}
		
	public void setTypeMaintenanceRead(String typeMaintenanceRead) {
		this.typeMaintenanceRead = typeMaintenanceRead;	
	}

	private String typeMaintenanceUpdate;


	public String getTypeMaintenanceUpdate() {
		return typeMaintenanceUpdate;	
	}
		
	public void setTypeMaintenanceUpdate(String typeMaintenanceUpdate) {
		this.typeMaintenanceUpdate = typeMaintenanceUpdate;	
	}

	private String typeMaintenanceExport;


	public String getTypeMaintenanceExport() {
		return typeMaintenanceExport;	
	}
		
	public void setTypeMaintenanceExport(String typeMaintenanceExport) {
		this.typeMaintenanceExport = typeMaintenanceExport;	
	}

	private String causePendingCreate;


	public String getCausePendingCreate() {
		return causePendingCreate;	
	}
		
	public void setCausePendingCreate(String causePendingCreate) {
		this.causePendingCreate = causePendingCreate;	
	}

	private String causePendingRead;


	public String getCausePendingRead() {
		return causePendingRead;	
	}
		
	public void setCausePendingRead(String causePendingRead) {
		this.causePendingRead = causePendingRead;	
	}

	private String causePendingUpdate;


	public String getCausePendingUpdate() {
		return causePendingUpdate;	
	}
		
	public void setCausePendingUpdate(String causePendingUpdate) {
		this.causePendingUpdate = causePendingUpdate;	
	}

	private String causePendingExport;


	public String getCausePendingExport() {
		return causePendingExport;	
	}
		
	public void setCausePendingExport(String causePendingExport) {
		this.causePendingExport = causePendingExport;	
	}

	private String typeWorkCreate;


	public String getTypeWorkCreate() {
		return typeWorkCreate;	
	}
		
	public void setTypeWorkCreate(String typeWorkCreate) {
		this.typeWorkCreate = typeWorkCreate;	
	}

	private String typeWorkRead;


	public String getTypeWorkRead() {
		return typeWorkRead;	
	}
		
	public void setTypeWorkRead(String typeWorkRead) {
		this.typeWorkRead = typeWorkRead;	
	}

	private String typeWorkUpdate;


	public String getTypeWorkUpdate() {
		return typeWorkUpdate;	
	}
		
	public void setTypeWorkUpdate(String typeWorkUpdate) {
		this.typeWorkUpdate = typeWorkUpdate;	
	}

	private String typeWorkExport;


	public String getTypeWorkExport() {
		return typeWorkExport;	
	}
		
	public void setTypeWorkExport(String typeWorkExport) {
		this.typeWorkExport = typeWorkExport;	
	}

	private String otherConceptsCreate;


	public String getOtherConceptsCreate() {
		return otherConceptsCreate;	
	}
		
	public void setOtherConceptsCreate(String otherConceptsCreate) {
		this.otherConceptsCreate = otherConceptsCreate;	
	}

	private String otherConceptsRead;


	public String getOtherConceptsRead() {
		return otherConceptsRead;	
	}
		
	public void setOtherConceptsRead(String otherConceptsRead) {
		this.otherConceptsRead = otherConceptsRead;	
	}

	private String otherConceptsUpdate;


	public String getOtherConceptsUpdate() {
		return otherConceptsUpdate;	
	}
		
	public void setOtherConceptsUpdate(String otherConceptsUpdate) {
		this.otherConceptsUpdate = otherConceptsUpdate;	
	}

	private String otherConceptsExport;


	public String getOtherConceptsExport() {
		return otherConceptsExport;	
	}
		
	public void setOtherConceptsExport(String otherConceptsExport) {
		this.otherConceptsExport = otherConceptsExport;	
	}

	private String stateCostCentersCreate;


	public String getStateCostCentersCreate() {
		return stateCostCentersCreate;	
	}
		
	public void setStateCostCentersCreate(String stateCostCentersCreate) {
		this.stateCostCentersCreate = stateCostCentersCreate;	
	}

	private String stateCostCentersRead;


	public String getStateCostCentersRead() {
		return stateCostCentersRead;	
	}
		
	public void setStateCostCentersRead(String stateCostCentersRead) {
		this.stateCostCentersRead = stateCostCentersRead;	
	}

	private String stateCostCentersUpdate;


	public String getStateCostCentersUpdate() {
		return stateCostCentersUpdate;	
	}
		
	public void setStateCostCentersUpdate(String stateCostCentersUpdate) {
		this.stateCostCentersUpdate = stateCostCentersUpdate;	
	}

	private String stateCostCentersExport;


	public String getStateCostCentersExport() {
		return stateCostCentersExport;	
	}
		
	public void setStateCostCentersExport(String stateCostCentersExport) {
		this.stateCostCentersExport = stateCostCentersExport;	
	}

	private String costCentersCustomersCreate;


	public String getCostCentersCustomersCreate() {
		return costCentersCustomersCreate;	
	}
		
	public void setCostCentersCustomersCreate(String costCentersCustomersCreate) {
		this.costCentersCustomersCreate = costCentersCustomersCreate;	
	}

	private String costCentersCustomersRead;


	public String getCostCentersCustomersRead() {
		return costCentersCustomersRead;	
	}
		
	public void setCostCentersCustomersRead(String costCentersCustomersRead) {
		this.costCentersCustomersRead = costCentersCustomersRead;	
	}

	private String costCentersCustomersUpdate;


	public String getCostCentersCustomersUpdate() {
		return costCentersCustomersUpdate;	
	}
		
	public void setCostCentersCustomersUpdate(String costCentersCustomersUpdate) {
		this.costCentersCustomersUpdate = costCentersCustomersUpdate;	
	}

	private String costCentersCustomersExport;


	public String getCostCentersCustomersExport() {
		return costCentersCustomersExport;	
	}
		
	public void setCostCentersCustomersExport(String costCentersCustomersExport) {
		this.costCentersCustomersExport = costCentersCustomersExport;	
	}

	private String responsibleCenterCreate;


	public String getResponsibleCenterCreate() {
		return responsibleCenterCreate;	
	}
		
	public void setResponsibleCenterCreate(String responsibleCenterCreate) {
		this.responsibleCenterCreate = responsibleCenterCreate;	
	}

	private String responsibleCenterRead;


	public String getResponsibleCenterRead() {
		return responsibleCenterRead;	
	}
		
	public void setResponsibleCenterRead(String responsibleCenterRead) {
		this.responsibleCenterRead = responsibleCenterRead;	
	}

	private String responsibleCenterUpdate;


	public String getResponsibleCenterUpdate() {
		return responsibleCenterUpdate;	
	}
		
	public void setResponsibleCenterUpdate(String responsibleCenterUpdate) {
		this.responsibleCenterUpdate = responsibleCenterUpdate;	
	}

	private String responsibleCenterExport;


	public String getResponsibleCenterExport() {
		return responsibleCenterExport;	
	}
		
	public void setResponsibleCenterExport(String responsibleCenterExport) {
		this.responsibleCenterExport = responsibleCenterExport;	
	}

	private String jobCreate;


	public String getJobCreate() {
		return jobCreate;	
	}
		
	public void setJobCreate(String jobCreate) {
		this.jobCreate = jobCreate;	
	}

	private String jobRead;


	public String getJobRead() {
		return jobRead;	
	}
		
	public void setJobRead(String jobRead) {
		this.jobRead = jobRead;	
	}

	private String jobUpdate;


	public String getJobUpdate() {
		return jobUpdate;	
	}
		
	public void setJobUpdate(String jobUpdate) {
		this.jobUpdate = jobUpdate;	
	}

	private String jobExport;


	public String getJobExport() {
		return jobExport;	
	}
		
	public void setJobExport(String jobExport) {
		this.jobExport = jobExport;	
	}

	private String thirdCreate;


	public String getThirdCreate() {
		return thirdCreate;	
	}
		
	public void setThirdCreate(String thirdCreate) {
		this.thirdCreate = thirdCreate;	
	}

	private String thirdRead;


	public String getThirdRead() {
		return thirdRead;	
	}
		
	public void setThirdRead(String thirdRead) {
		this.thirdRead = thirdRead;	
	}

	private String thirdUpdate;


	public String getThirdUpdate() {
		return thirdUpdate;	
	}
		
	public void setThirdUpdate(String thirdUpdate) {
		this.thirdUpdate = thirdUpdate;	
	}

	private String thirdExport;


	public String getThirdExport() {
		return thirdExport;	
	}
		
	public void setThirdExport(String thirdExport) {
		this.thirdExport = thirdExport;	
	}

	private String jobThirdCreate;


	public String getJobThirdCreate() {
		return jobThirdCreate;	
	}
		
	public void setJobThirdCreate(String jobThirdCreate) {
		this.jobThirdCreate = jobThirdCreate;	
	}

	private String jobThirdRead;


	public String getJobThirdRead() {
		return jobThirdRead;	
	}
		
	public void setJobThirdRead(String jobThirdRead) {
		this.jobThirdRead = jobThirdRead;	
	}

	private String jobThirdUpdate;


	public String getJobThirdUpdate() {
		return jobThirdUpdate;	
	}
		
	public void setJobThirdUpdate(String jobThirdUpdate) {
		this.jobThirdUpdate = jobThirdUpdate;	
	}

	private String jobThirdExport;


	public String getJobThirdExport() {
		return jobThirdExport;	
	}
		
	public void setJobThirdExport(String jobThirdExport) {
		this.jobThirdExport = jobThirdExport;	
	}

	private String responsibleCreate;


	public String getResponsibleCreate() {
		return responsibleCreate;	
	}
		
	public void setResponsibleCreate(String responsibleCreate) {
		this.responsibleCreate = responsibleCreate;	
	}

	private String responsibleRead;


	public String getResponsibleRead() {
		return responsibleRead;	
	}
		
	public void setResponsibleRead(String responsibleRead) {
		this.responsibleRead = responsibleRead;	
	}

	private String responsibleUpdate;


	public String getResponsibleUpdate() {
		return responsibleUpdate;	
	}
		
	public void setResponsibleUpdate(String responsibleUpdate) {
		this.responsibleUpdate = responsibleUpdate;	
	}

	private String responsibleExport;


	public String getResponsibleExport() {
		return responsibleExport;	
	}
		
	public void setResponsibleExport(String responsibleExport) {
		this.responsibleExport = responsibleExport;	
	}

	private String authorizerCostCenterCreate;


	public String getAuthorizerCostCenterCreate() {
		return authorizerCostCenterCreate;	
	}
		
	public void setAuthorizerCostCenterCreate(String authorizerCostCenterCreate) {
		this.authorizerCostCenterCreate = authorizerCostCenterCreate;	
	}

	private String authorizerCostCenterRead;


	public String getAuthorizerCostCenterRead() {
		return authorizerCostCenterRead;	
	}
		
	public void setAuthorizerCostCenterRead(String authorizerCostCenterRead) {
		this.authorizerCostCenterRead = authorizerCostCenterRead;	
	}

	private String authorizerCostCenterUpdate;


	public String getAuthorizerCostCenterUpdate() {
		return authorizerCostCenterUpdate;	
	}
		
	public void setAuthorizerCostCenterUpdate(String authorizerCostCenterUpdate) {
		this.authorizerCostCenterUpdate = authorizerCostCenterUpdate;	
	}

	private String authorizerCostCenterExport;


	public String getAuthorizerCostCenterExport() {
		return authorizerCostCenterExport;	
	}
		
	public void setAuthorizerCostCenterExport(String authorizerCostCenterExport) {
		this.authorizerCostCenterExport = authorizerCostCenterExport;	
	}

	private String characteristicCreate;


	public String getCharacteristicCreate() {
		return characteristicCreate;	
	}
		
	public void setCharacteristicCreate(String characteristicCreate) {
		this.characteristicCreate = characteristicCreate;	
	}

	private String characteristicRead;


	public String getCharacteristicRead() {
		return characteristicRead;	
	}
		
	public void setCharacteristicRead(String characteristicRead) {
		this.characteristicRead = characteristicRead;	
	}

	private String characteristicUpdate;


	public String getCharacteristicUpdate() {
		return characteristicUpdate;	
	}
		
	public void setCharacteristicUpdate(String characteristicUpdate) {
		this.characteristicUpdate = characteristicUpdate;	
	}

	private String characteristicExport;


	public String getCharacteristicExport() {
		return characteristicExport;	
	}
		
	public void setCharacteristicExport(String characteristicExport) {
		this.characteristicExport = characteristicExport;	
	}

	private String accountantCreate;


	public String getAccountantCreate() {
		return accountantCreate;	
	}
		
	public void setAccountantCreate(String accountantCreate) {
		this.accountantCreate = accountantCreate;	
	}

	private String accountantRead;


	public String getAccountantRead() {
		return accountantRead;	
	}
		
	public void setAccountantRead(String accountantRead) {
		this.accountantRead = accountantRead;	
	}

	private String accountantUpdate;


	public String getAccountantUpdate() {
		return accountantUpdate;	
	}
		
	public void setAccountantUpdate(String accountantUpdate) {
		this.accountantUpdate = accountantUpdate;	
	}

	private String accountantExport;


	public String getAccountantExport() {
		return accountantExport;	
	}
		
	public void setAccountantExport(String accountantExport) {
		this.accountantExport = accountantExport;	
	}

	private String physicalLocationCreate;


	public String getPhysicalLocationCreate() {
		return physicalLocationCreate;	
	}
		
	public void setPhysicalLocationCreate(String physicalLocationCreate) {
		this.physicalLocationCreate = physicalLocationCreate;	
	}

	private String physicalLocationRead;


	public String getPhysicalLocationRead() {
		return physicalLocationRead;	
	}
		
	public void setPhysicalLocationRead(String physicalLocationRead) {
		this.physicalLocationRead = physicalLocationRead;	
	}

	private String physicalLocationUpdate;


	public String getPhysicalLocationUpdate() {
		return physicalLocationUpdate;	
	}
		
	public void setPhysicalLocationUpdate(String physicalLocationUpdate) {
		this.physicalLocationUpdate = physicalLocationUpdate;	
	}

	private String physicalLocationExport;


	public String getPhysicalLocationExport() {
		return physicalLocationExport;	
	}
		
	public void setPhysicalLocationExport(String physicalLocationExport) {
		this.physicalLocationExport = physicalLocationExport;	
	}

	private String storeTypeCreate;


	public String getStoreTypeCreate() {
		return storeTypeCreate;	
	}
		
	public void setStoreTypeCreate(String storeTypeCreate) {
		this.storeTypeCreate = storeTypeCreate;	
	}

	private String storeTypeRead;


	public String getStoreTypeRead() {
		return storeTypeRead;	
	}
		
	public void setStoreTypeRead(String storeTypeRead) {
		this.storeTypeRead = storeTypeRead;	
	}

	private String storeTypeUpdate;


	public String getStoreTypeUpdate() {
		return storeTypeUpdate;	
	}
		
	public void setStoreTypeUpdate(String storeTypeUpdate) {
		this.storeTypeUpdate = storeTypeUpdate;	
	}

	private String storeTypeExport;


	public String getStoreTypeExport() {
		return storeTypeExport;	
	}
		
	public void setStoreTypeExport(String storeTypeExport) {
		this.storeTypeExport = storeTypeExport;	
	}

	private String costingTypeCreate;


	public String getCostingTypeCreate() {
		return costingTypeCreate;	
	}
		
	public void setCostingTypeCreate(String costingTypeCreate) {
		this.costingTypeCreate = costingTypeCreate;	
	}

	private String costingTypeRead;


	public String getCostingTypeRead() {
		return costingTypeRead;	
	}
		
	public void setCostingTypeRead(String costingTypeRead) {
		this.costingTypeRead = costingTypeRead;	
	}

	private String costingTypeUpdate;


	public String getCostingTypeUpdate() {
		return costingTypeUpdate;	
	}
		
	public void setCostingTypeUpdate(String costingTypeUpdate) {
		this.costingTypeUpdate = costingTypeUpdate;	
	}

	private String costingTypeExport;


	public String getCostingTypeExport() {
		return costingTypeExport;	
	}
		
	public void setCostingTypeExport(String costingTypeExport) {
		this.costingTypeExport = costingTypeExport;	
	}

	private String warehouseCreate;


	public String getWarehouseCreate() {
		return warehouseCreate;	
	}
		
	public void setWarehouseCreate(String warehouseCreate) {
		this.warehouseCreate = warehouseCreate;	
	}

	private String warehouseRead;


	public String getWarehouseRead() {
		return warehouseRead;	
	}
		
	public void setWarehouseRead(String warehouseRead) {
		this.warehouseRead = warehouseRead;	
	}

	private String warehouseUpdate;


	public String getWarehouseUpdate() {
		return warehouseUpdate;	
	}
		
	public void setWarehouseUpdate(String warehouseUpdate) {
		this.warehouseUpdate = warehouseUpdate;	
	}

	private String warehouseExport;


	public String getWarehouseExport() {
		return warehouseExport;	
	}
		
	public void setWarehouseExport(String warehouseExport) {
		this.warehouseExport = warehouseExport;	
	}

	private String coinCreate;


	public String getCoinCreate() {
		return coinCreate;	
	}
		
	public void setCoinCreate(String coinCreate) {
		this.coinCreate = coinCreate;	
	}

	private String coinRead;


	public String getCoinRead() {
		return coinRead;	
	}
		
	public void setCoinRead(String coinRead) {
		this.coinRead = coinRead;	
	}

	private String coinUpdate;


	public String getCoinUpdate() {
		return coinUpdate;	
	}
		
	public void setCoinUpdate(String coinUpdate) {
		this.coinUpdate = coinUpdate;	
	}

	private String coinExport;


	public String getCoinExport() {
		return coinExport;	
	}
		
	public void setCoinExport(String coinExport) {
		this.coinExport = coinExport;	
	}

	private String typeConceptKardexCreate;


	public String getTypeConceptKardexCreate() {
		return typeConceptKardexCreate;	
	}
		
	public void setTypeConceptKardexCreate(String typeConceptKardexCreate) {
		this.typeConceptKardexCreate = typeConceptKardexCreate;	
	}

	private String typeConceptKardexRead;


	public String getTypeConceptKardexRead() {
		return typeConceptKardexRead;	
	}
		
	public void setTypeConceptKardexRead(String typeConceptKardexRead) {
		this.typeConceptKardexRead = typeConceptKardexRead;	
	}

	private String typeConceptKardexUpdate;


	public String getTypeConceptKardexUpdate() {
		return typeConceptKardexUpdate;	
	}
		
	public void setTypeConceptKardexUpdate(String typeConceptKardexUpdate) {
		this.typeConceptKardexUpdate = typeConceptKardexUpdate;	
	}

	private String typeConceptKardexExport;


	public String getTypeConceptKardexExport() {
		return typeConceptKardexExport;	
	}
		
	public void setTypeConceptKardexExport(String typeConceptKardexExport) {
		this.typeConceptKardexExport = typeConceptKardexExport;	
	}

	private String conceptKardexCreate;


	public String getConceptKardexCreate() {
		return conceptKardexCreate;	
	}
		
	public void setConceptKardexCreate(String conceptKardexCreate) {
		this.conceptKardexCreate = conceptKardexCreate;	
	}

	private String conceptKardexRead;


	public String getConceptKardexRead() {
		return conceptKardexRead;	
	}
		
	public void setConceptKardexRead(String conceptKardexRead) {
		this.conceptKardexRead = conceptKardexRead;	
	}

	private String conceptKardexUpdate;


	public String getConceptKardexUpdate() {
		return conceptKardexUpdate;	
	}
		
	public void setConceptKardexUpdate(String conceptKardexUpdate) {
		this.conceptKardexUpdate = conceptKardexUpdate;	
	}

	private String conceptKardexExport;


	public String getConceptKardexExport() {
		return conceptKardexExport;	
	}
		
	public void setConceptKardexExport(String conceptKardexExport) {
		this.conceptKardexExport = conceptKardexExport;	
	}

	private String contractCreate;


	public String getContractCreate() {
		return contractCreate;	
	}
		
	public void setContractCreate(String contractCreate) {
		this.contractCreate = contractCreate;	
	}

	private String contractRead;


	public String getContractRead() {
		return contractRead;	
	}
		
	public void setContractRead(String contractRead) {
		this.contractRead = contractRead;	
	}

	private String contractUpdate;


	public String getContractUpdate() {
		return contractUpdate;	
	}
		
	public void setContractUpdate(String contractUpdate) {
		this.contractUpdate = contractUpdate;	
	}

	private String contractExport;


	public String getContractExport() {
		return contractExport;	
	}
		
	public void setContractExport(String contractExport) {
		this.contractExport = contractExport;	
	}

	private String usersCreate;


	public String getUsersCreate() {
		return usersCreate;	
	}
		
	public void setUsersCreate(String usersCreate) {
		this.usersCreate = usersCreate;	
	}

	private String usersRead;


	public String getUsersRead() {
		return usersRead;	
	}
		
	public void setUsersRead(String usersRead) {
		this.usersRead = usersRead;	
	}

	private String usersUpdate;


	public String getUsersUpdate() {
		return usersUpdate;	
	}
		
	public void setUsersUpdate(String usersUpdate) {
		this.usersUpdate = usersUpdate;	
	}

	private String usersExport;


	public String getUsersExport() {
		return usersExport;	
	}
		
	public void setUsersExport(String usersExport) {
		this.usersExport = usersExport;	
	}

	private String currencyCreate;


	public String getCurrencyCreate() {
		return currencyCreate;	
	}
		
	public void setCurrencyCreate(String currencyCreate) {
		this.currencyCreate = currencyCreate;	
	}

	private String currencyRead;


	public String getCurrencyRead() {
		return currencyRead;	
	}
		
	public void setCurrencyRead(String currencyRead) {
		this.currencyRead = currencyRead;	
	}

	private String currencyUpdate;


	public String getCurrencyUpdate() {
		return currencyUpdate;	
	}
		
	public void setCurrencyUpdate(String currencyUpdate) {
		this.currencyUpdate = currencyUpdate;	
	}

	private String currencyExport;


	public String getCurrencyExport() {
		return currencyExport;	
	}
		
	public void setCurrencyExport(String currencyExport) {
		this.currencyExport = currencyExport;	
	}

	private String exchangeRateCreate;


	public String getExchangeRateCreate() {
		return exchangeRateCreate;	
	}
		
	public void setExchangeRateCreate(String exchangeRateCreate) {
		this.exchangeRateCreate = exchangeRateCreate;	
	}

	private String exchangeRateRead;


	public String getExchangeRateRead() {
		return exchangeRateRead;	
	}
		
	public void setExchangeRateRead(String exchangeRateRead) {
		this.exchangeRateRead = exchangeRateRead;	
	}

	private String exchangeRateUpdate;


	public String getExchangeRateUpdate() {
		return exchangeRateUpdate;	
	}
		
	public void setExchangeRateUpdate(String exchangeRateUpdate) {
		this.exchangeRateUpdate = exchangeRateUpdate;	
	}

	private String exchangeRateExport;


	public String getExchangeRateExport() {
		return exchangeRateExport;	
	}
		
	public void setExchangeRateExport(String exchangeRateExport) {
		this.exchangeRateExport = exchangeRateExport;	
	}

	private String continentCreate;


	public String getContinentCreate() {
		return continentCreate;	
	}
		
	public void setContinentCreate(String continentCreate) {
		this.continentCreate = continentCreate;	
	}

	private String continentRead;


	public String getContinentRead() {
		return continentRead;	
	}
		
	public void setContinentRead(String continentRead) {
		this.continentRead = continentRead;	
	}

	private String continentUpdate;


	public String getContinentUpdate() {
		return continentUpdate;	
	}
		
	public void setContinentUpdate(String continentUpdate) {
		this.continentUpdate = continentUpdate;	
	}

	private String continentExport;


	public String getContinentExport() {
		return continentExport;	
	}
		
	public void setContinentExport(String continentExport) {
		this.continentExport = continentExport;	
	}

	private String regionCreate;


	public String getRegionCreate() {
		return regionCreate;	
	}
		
	public void setRegionCreate(String regionCreate) {
		this.regionCreate = regionCreate;	
	}

	private String regionRead;


	public String getRegionRead() {
		return regionRead;	
	}
		
	public void setRegionRead(String regionRead) {
		this.regionRead = regionRead;	
	}

	private String regionUpdate;


	public String getRegionUpdate() {
		return regionUpdate;	
	}
		
	public void setRegionUpdate(String regionUpdate) {
		this.regionUpdate = regionUpdate;	
	}

	private String regionExport;


	public String getRegionExport() {
		return regionExport;	
	}
		
	public void setRegionExport(String regionExport) {
		this.regionExport = regionExport;	
	}

	private String currencyCodeCreate;


	public String getCurrencyCodeCreate() {
		return currencyCodeCreate;	
	}
		
	public void setCurrencyCodeCreate(String currencyCodeCreate) {
		this.currencyCodeCreate = currencyCodeCreate;	
	}

	private String currencyCodeRead;


	public String getCurrencyCodeRead() {
		return currencyCodeRead;	
	}
		
	public void setCurrencyCodeRead(String currencyCodeRead) {
		this.currencyCodeRead = currencyCodeRead;	
	}

	private String currencyCodeUpdate;


	public String getCurrencyCodeUpdate() {
		return currencyCodeUpdate;	
	}
		
	public void setCurrencyCodeUpdate(String currencyCodeUpdate) {
		this.currencyCodeUpdate = currencyCodeUpdate;	
	}

	private String currencyCodeExport;


	public String getCurrencyCodeExport() {
		return currencyCodeExport;	
	}
		
	public void setCurrencyCodeExport(String currencyCodeExport) {
		this.currencyCodeExport = currencyCodeExport;	
	}

	private String countryCreate;


	public String getCountryCreate() {
		return countryCreate;	
	}
		
	public void setCountryCreate(String countryCreate) {
		this.countryCreate = countryCreate;	
	}

	private String countryRead;


	public String getCountryRead() {
		return countryRead;	
	}
		
	public void setCountryRead(String countryRead) {
		this.countryRead = countryRead;	
	}

	private String countryUpdate;


	public String getCountryUpdate() {
		return countryUpdate;	
	}
		
	public void setCountryUpdate(String countryUpdate) {
		this.countryUpdate = countryUpdate;	
	}

	private String countryExport;


	public String getCountryExport() {
		return countryExport;	
	}
		
	public void setCountryExport(String countryExport) {
		this.countryExport = countryExport;	
	}


	
	private String fffilterNature;
	
	public String getFffilterNature() {
		return fffilterNature;
	}

	public void setFffilterNature(String fffilterNature) {
		this.fffilterNature = fffilterNature;
	}
		
	private String ffilterAsText="All";
	
	private Boolean aaddFilter=Boolean.FALSE;
	
	private List<UsersFilterInfo> usersFilterList=new ArrayList<UsersFilterInfo>();
	
	public List<UsersFilterInfo> getUsersFilterList() {
		return usersFilterList;
	}

	public void setUsersFilterList(
			List<UsersFilterInfo> usersFilterList) {
		this.usersFilterList = usersFilterList;
	}

	public Boolean getAaddFilter() {
		return aaddFilter;
	}

	public void setAaddFilter(Boolean aaddFilter) {
		this.aaddFilter = aaddFilter;
	}

	public String getFfilterAsText() {
		ffilterAsText="";
		int index=0;
		for (UsersFilterInfo fil : usersFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getLogin()!=null && !fil.getLogin().equals(""))ffilterAsText+=fil.getLogin()+" ";
			if (fil.getPassword()!=null && !fil.getPassword().equals(""))ffilterAsText+=fil.getPassword()+" ";
			if (fil.getRole()!=null && !fil.getRole().equals(""))ffilterAsText+=fil.getRole()+" ";
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";
			if (fil.getCompanyCreate()!=null && !fil.getCompanyCreate().equals(""))ffilterAsText+=fil.getCompanyCreate()+" ";
			if (fil.getCompanyRead()!=null && !fil.getCompanyRead().equals(""))ffilterAsText+=fil.getCompanyRead()+" ";
			if (fil.getCompanyUpdate()!=null && !fil.getCompanyUpdate().equals(""))ffilterAsText+=fil.getCompanyUpdate()+" ";
			if (fil.getCompanyExport()!=null && !fil.getCompanyExport().equals(""))ffilterAsText+=fil.getCompanyExport()+" ";
			if (fil.getStateCreate()!=null && !fil.getStateCreate().equals(""))ffilterAsText+=fil.getStateCreate()+" ";
			if (fil.getStateRead()!=null && !fil.getStateRead().equals(""))ffilterAsText+=fil.getStateRead()+" ";
			if (fil.getStateUpdate()!=null && !fil.getStateUpdate().equals(""))ffilterAsText+=fil.getStateUpdate()+" ";
			if (fil.getStateExport()!=null && !fil.getStateExport().equals(""))ffilterAsText+=fil.getStateExport()+" ";
			if (fil.getMeasurementUnitCreate()!=null && !fil.getMeasurementUnitCreate().equals(""))ffilterAsText+=fil.getMeasurementUnitCreate()+" ";
			if (fil.getMeasurementUnitRead()!=null && !fil.getMeasurementUnitRead().equals(""))ffilterAsText+=fil.getMeasurementUnitRead()+" ";
			if (fil.getMeasurementUnitUpdate()!=null && !fil.getMeasurementUnitUpdate().equals(""))ffilterAsText+=fil.getMeasurementUnitUpdate()+" ";
			if (fil.getMeasurementUnitExport()!=null && !fil.getMeasurementUnitExport().equals(""))ffilterAsText+=fil.getMeasurementUnitExport()+" ";
			if (fil.getTypeSorterCreate()!=null && !fil.getTypeSorterCreate().equals(""))ffilterAsText+=fil.getTypeSorterCreate()+" ";
			if (fil.getTypeSorterRead()!=null && !fil.getTypeSorterRead().equals(""))ffilterAsText+=fil.getTypeSorterRead()+" ";
			if (fil.getTypeSorterUpdate()!=null && !fil.getTypeSorterUpdate().equals(""))ffilterAsText+=fil.getTypeSorterUpdate()+" ";
			if (fil.getTypeSorterExport()!=null && !fil.getTypeSorterExport().equals(""))ffilterAsText+=fil.getTypeSorterExport()+" ";
			if (fil.getSorterCreate()!=null && !fil.getSorterCreate().equals(""))ffilterAsText+=fil.getSorterCreate()+" ";
			if (fil.getSorterRead()!=null && !fil.getSorterRead().equals(""))ffilterAsText+=fil.getSorterRead()+" ";
			if (fil.getSorterUpdate()!=null && !fil.getSorterUpdate().equals(""))ffilterAsText+=fil.getSorterUpdate()+" ";
			if (fil.getSorterExport()!=null && !fil.getSorterExport().equals(""))ffilterAsText+=fil.getSorterExport()+" ";
			if (fil.getOperationCreate()!=null && !fil.getOperationCreate().equals(""))ffilterAsText+=fil.getOperationCreate()+" ";
			if (fil.getOperationRead()!=null && !fil.getOperationRead().equals(""))ffilterAsText+=fil.getOperationRead()+" ";
			if (fil.getOperationUpdate()!=null && !fil.getOperationUpdate().equals(""))ffilterAsText+=fil.getOperationUpdate()+" ";
			if (fil.getOperationExport()!=null && !fil.getOperationExport().equals(""))ffilterAsText+=fil.getOperationExport()+" ";
			if (fil.getReferenceCreate()!=null && !fil.getReferenceCreate().equals(""))ffilterAsText+=fil.getReferenceCreate()+" ";
			if (fil.getReferenceRead()!=null && !fil.getReferenceRead().equals(""))ffilterAsText+=fil.getReferenceRead()+" ";
			if (fil.getReferenceUpdate()!=null && !fil.getReferenceUpdate().equals(""))ffilterAsText+=fil.getReferenceUpdate()+" ";
			if (fil.getReferenceExport()!=null && !fil.getReferenceExport().equals(""))ffilterAsText+=fil.getReferenceExport()+" ";
			if (fil.getReferenceOperationCreate()!=null && !fil.getReferenceOperationCreate().equals(""))ffilterAsText+=fil.getReferenceOperationCreate()+" ";
			if (fil.getReferenceOperationRead()!=null && !fil.getReferenceOperationRead().equals(""))ffilterAsText+=fil.getReferenceOperationRead()+" ";
			if (fil.getReferenceOperationUpdate()!=null && !fil.getReferenceOperationUpdate().equals(""))ffilterAsText+=fil.getReferenceOperationUpdate()+" ";
			if (fil.getReferenceOperationExport()!=null && !fil.getReferenceOperationExport().equals(""))ffilterAsText+=fil.getReferenceOperationExport()+" ";
			if (fil.getTechnicalActionCreate()!=null && !fil.getTechnicalActionCreate().equals(""))ffilterAsText+=fil.getTechnicalActionCreate()+" ";
			if (fil.getTechnicalActionRead()!=null && !fil.getTechnicalActionRead().equals(""))ffilterAsText+=fil.getTechnicalActionRead()+" ";
			if (fil.getTechnicalActionUpdate()!=null && !fil.getTechnicalActionUpdate().equals(""))ffilterAsText+=fil.getTechnicalActionUpdate()+" ";
			if (fil.getTechnicalActionExport()!=null && !fil.getTechnicalActionExport().equals(""))ffilterAsText+=fil.getTechnicalActionExport()+" ";
			if (fil.getEffectFailCreate()!=null && !fil.getEffectFailCreate().equals(""))ffilterAsText+=fil.getEffectFailCreate()+" ";
			if (fil.getEffectFailRead()!=null && !fil.getEffectFailRead().equals(""))ffilterAsText+=fil.getEffectFailRead()+" ";
			if (fil.getEffectFailUpdate()!=null && !fil.getEffectFailUpdate().equals(""))ffilterAsText+=fil.getEffectFailUpdate()+" ";
			if (fil.getEffectFailExport()!=null && !fil.getEffectFailExport().equals(""))ffilterAsText+=fil.getEffectFailExport()+" ";
			if (fil.getEffectFailTechnicalActionCreate()!=null && !fil.getEffectFailTechnicalActionCreate().equals(""))ffilterAsText+=fil.getEffectFailTechnicalActionCreate()+" ";
			if (fil.getEffectFailTechnicalActionRead()!=null && !fil.getEffectFailTechnicalActionRead().equals(""))ffilterAsText+=fil.getEffectFailTechnicalActionRead()+" ";
			if (fil.getEffectFailTechnicalActionUpdate()!=null && !fil.getEffectFailTechnicalActionUpdate().equals(""))ffilterAsText+=fil.getEffectFailTechnicalActionUpdate()+" ";
			if (fil.getEffectFailTechnicalActionExport()!=null && !fil.getEffectFailTechnicalActionExport().equals(""))ffilterAsText+=fil.getEffectFailTechnicalActionExport()+" ";
			if (fil.getFailureCauseCreate()!=null && !fil.getFailureCauseCreate().equals(""))ffilterAsText+=fil.getFailureCauseCreate()+" ";
			if (fil.getFailureCauseRead()!=null && !fil.getFailureCauseRead().equals(""))ffilterAsText+=fil.getFailureCauseRead()+" ";
			if (fil.getFailureCauseUpdate()!=null && !fil.getFailureCauseUpdate().equals(""))ffilterAsText+=fil.getFailureCauseUpdate()+" ";
			if (fil.getFailureCauseExport()!=null && !fil.getFailureCauseExport().equals(""))ffilterAsText+=fil.getFailureCauseExport()+" ";
			if (fil.getFailureCauseTechnicalactionCreate()!=null && !fil.getFailureCauseTechnicalactionCreate().equals(""))ffilterAsText+=fil.getFailureCauseTechnicalactionCreate()+" ";
			if (fil.getFailureCauseTechnicalactionRead()!=null && !fil.getFailureCauseTechnicalactionRead().equals(""))ffilterAsText+=fil.getFailureCauseTechnicalactionRead()+" ";
			if (fil.getFailureCauseTechnicalactionUpdate()!=null && !fil.getFailureCauseTechnicalactionUpdate().equals(""))ffilterAsText+=fil.getFailureCauseTechnicalactionUpdate()+" ";
			if (fil.getFailureCauseTechnicalactionExport()!=null && !fil.getFailureCauseTechnicalactionExport().equals(""))ffilterAsText+=fil.getFailureCauseTechnicalactionExport()+" ";
			if (fil.getTypeCauseCloseCreate()!=null && !fil.getTypeCauseCloseCreate().equals(""))ffilterAsText+=fil.getTypeCauseCloseCreate()+" ";
			if (fil.getTypeCauseCloseRead()!=null && !fil.getTypeCauseCloseRead().equals(""))ffilterAsText+=fil.getTypeCauseCloseRead()+" ";
			if (fil.getTypeCauseCloseUpdate()!=null && !fil.getTypeCauseCloseUpdate().equals(""))ffilterAsText+=fil.getTypeCauseCloseUpdate()+" ";
			if (fil.getTypeCauseCloseExport()!=null && !fil.getTypeCauseCloseExport().equals(""))ffilterAsText+=fil.getTypeCauseCloseExport()+" ";
			if (fil.getCauseCloseCreate()!=null && !fil.getCauseCloseCreate().equals(""))ffilterAsText+=fil.getCauseCloseCreate()+" ";
			if (fil.getCauseCloseRead()!=null && !fil.getCauseCloseRead().equals(""))ffilterAsText+=fil.getCauseCloseRead()+" ";
			if (fil.getCauseCloseUpdate()!=null && !fil.getCauseCloseUpdate().equals(""))ffilterAsText+=fil.getCauseCloseUpdate()+" ";
			if (fil.getCauseCloseExport()!=null && !fil.getCauseCloseExport().equals(""))ffilterAsText+=fil.getCauseCloseExport()+" ";
			if (fil.getMaintenanceActivityCreate()!=null && !fil.getMaintenanceActivityCreate().equals(""))ffilterAsText+=fil.getMaintenanceActivityCreate()+" ";
			if (fil.getMaintenanceActivityRead()!=null && !fil.getMaintenanceActivityRead().equals(""))ffilterAsText+=fil.getMaintenanceActivityRead()+" ";
			if (fil.getMaintenanceActivityUpdate()!=null && !fil.getMaintenanceActivityUpdate().equals(""))ffilterAsText+=fil.getMaintenanceActivityUpdate()+" ";
			if (fil.getMaintenanceActivityExport()!=null && !fil.getMaintenanceActivityExport().equals(""))ffilterAsText+=fil.getMaintenanceActivityExport()+" ";
			if (fil.getMaintenanceWorkCreate()!=null && !fil.getMaintenanceWorkCreate().equals(""))ffilterAsText+=fil.getMaintenanceWorkCreate()+" ";
			if (fil.getMaintenanceWorkRead()!=null && !fil.getMaintenanceWorkRead().equals(""))ffilterAsText+=fil.getMaintenanceWorkRead()+" ";
			if (fil.getMaintenanceWorkUpdate()!=null && !fil.getMaintenanceWorkUpdate().equals(""))ffilterAsText+=fil.getMaintenanceWorkUpdate()+" ";
			if (fil.getMaintenanceWorkExport()!=null && !fil.getMaintenanceWorkExport().equals(""))ffilterAsText+=fil.getMaintenanceWorkExport()+" ";
			if (fil.getTypeMaintenanceCreate()!=null && !fil.getTypeMaintenanceCreate().equals(""))ffilterAsText+=fil.getTypeMaintenanceCreate()+" ";
			if (fil.getTypeMaintenanceRead()!=null && !fil.getTypeMaintenanceRead().equals(""))ffilterAsText+=fil.getTypeMaintenanceRead()+" ";
			if (fil.getTypeMaintenanceUpdate()!=null && !fil.getTypeMaintenanceUpdate().equals(""))ffilterAsText+=fil.getTypeMaintenanceUpdate()+" ";
			if (fil.getTypeMaintenanceExport()!=null && !fil.getTypeMaintenanceExport().equals(""))ffilterAsText+=fil.getTypeMaintenanceExport()+" ";
			if (fil.getCausePendingCreate()!=null && !fil.getCausePendingCreate().equals(""))ffilterAsText+=fil.getCausePendingCreate()+" ";
			if (fil.getCausePendingRead()!=null && !fil.getCausePendingRead().equals(""))ffilterAsText+=fil.getCausePendingRead()+" ";
			if (fil.getCausePendingUpdate()!=null && !fil.getCausePendingUpdate().equals(""))ffilterAsText+=fil.getCausePendingUpdate()+" ";
			if (fil.getCausePendingExport()!=null && !fil.getCausePendingExport().equals(""))ffilterAsText+=fil.getCausePendingExport()+" ";
			if (fil.getTypeWorkCreate()!=null && !fil.getTypeWorkCreate().equals(""))ffilterAsText+=fil.getTypeWorkCreate()+" ";
			if (fil.getTypeWorkRead()!=null && !fil.getTypeWorkRead().equals(""))ffilterAsText+=fil.getTypeWorkRead()+" ";
			if (fil.getTypeWorkUpdate()!=null && !fil.getTypeWorkUpdate().equals(""))ffilterAsText+=fil.getTypeWorkUpdate()+" ";
			if (fil.getTypeWorkExport()!=null && !fil.getTypeWorkExport().equals(""))ffilterAsText+=fil.getTypeWorkExport()+" ";
			if (fil.getOtherConceptsCreate()!=null && !fil.getOtherConceptsCreate().equals(""))ffilterAsText+=fil.getOtherConceptsCreate()+" ";
			if (fil.getOtherConceptsRead()!=null && !fil.getOtherConceptsRead().equals(""))ffilterAsText+=fil.getOtherConceptsRead()+" ";
			if (fil.getOtherConceptsUpdate()!=null && !fil.getOtherConceptsUpdate().equals(""))ffilterAsText+=fil.getOtherConceptsUpdate()+" ";
			if (fil.getOtherConceptsExport()!=null && !fil.getOtherConceptsExport().equals(""))ffilterAsText+=fil.getOtherConceptsExport()+" ";
			if (fil.getStateCostCentersCreate()!=null && !fil.getStateCostCentersCreate().equals(""))ffilterAsText+=fil.getStateCostCentersCreate()+" ";
			if (fil.getStateCostCentersRead()!=null && !fil.getStateCostCentersRead().equals(""))ffilterAsText+=fil.getStateCostCentersRead()+" ";
			if (fil.getStateCostCentersUpdate()!=null && !fil.getStateCostCentersUpdate().equals(""))ffilterAsText+=fil.getStateCostCentersUpdate()+" ";
			if (fil.getStateCostCentersExport()!=null && !fil.getStateCostCentersExport().equals(""))ffilterAsText+=fil.getStateCostCentersExport()+" ";
			if (fil.getCostCentersCustomersCreate()!=null && !fil.getCostCentersCustomersCreate().equals(""))ffilterAsText+=fil.getCostCentersCustomersCreate()+" ";
			if (fil.getCostCentersCustomersRead()!=null && !fil.getCostCentersCustomersRead().equals(""))ffilterAsText+=fil.getCostCentersCustomersRead()+" ";
			if (fil.getCostCentersCustomersUpdate()!=null && !fil.getCostCentersCustomersUpdate().equals(""))ffilterAsText+=fil.getCostCentersCustomersUpdate()+" ";
			if (fil.getCostCentersCustomersExport()!=null && !fil.getCostCentersCustomersExport().equals(""))ffilterAsText+=fil.getCostCentersCustomersExport()+" ";
			if (fil.getResponsibleCenterCreate()!=null && !fil.getResponsibleCenterCreate().equals(""))ffilterAsText+=fil.getResponsibleCenterCreate()+" ";
			if (fil.getResponsibleCenterRead()!=null && !fil.getResponsibleCenterRead().equals(""))ffilterAsText+=fil.getResponsibleCenterRead()+" ";
			if (fil.getResponsibleCenterUpdate()!=null && !fil.getResponsibleCenterUpdate().equals(""))ffilterAsText+=fil.getResponsibleCenterUpdate()+" ";
			if (fil.getResponsibleCenterExport()!=null && !fil.getResponsibleCenterExport().equals(""))ffilterAsText+=fil.getResponsibleCenterExport()+" ";
			if (fil.getJobCreate()!=null && !fil.getJobCreate().equals(""))ffilterAsText+=fil.getJobCreate()+" ";
			if (fil.getJobRead()!=null && !fil.getJobRead().equals(""))ffilterAsText+=fil.getJobRead()+" ";
			if (fil.getJobUpdate()!=null && !fil.getJobUpdate().equals(""))ffilterAsText+=fil.getJobUpdate()+" ";
			if (fil.getJobExport()!=null && !fil.getJobExport().equals(""))ffilterAsText+=fil.getJobExport()+" ";
			if (fil.getThirdCreate()!=null && !fil.getThirdCreate().equals(""))ffilterAsText+=fil.getThirdCreate()+" ";
			if (fil.getThirdRead()!=null && !fil.getThirdRead().equals(""))ffilterAsText+=fil.getThirdRead()+" ";
			if (fil.getThirdUpdate()!=null && !fil.getThirdUpdate().equals(""))ffilterAsText+=fil.getThirdUpdate()+" ";
			if (fil.getThirdExport()!=null && !fil.getThirdExport().equals(""))ffilterAsText+=fil.getThirdExport()+" ";
			if (fil.getJobThirdCreate()!=null && !fil.getJobThirdCreate().equals(""))ffilterAsText+=fil.getJobThirdCreate()+" ";
			if (fil.getJobThirdRead()!=null && !fil.getJobThirdRead().equals(""))ffilterAsText+=fil.getJobThirdRead()+" ";
			if (fil.getJobThirdUpdate()!=null && !fil.getJobThirdUpdate().equals(""))ffilterAsText+=fil.getJobThirdUpdate()+" ";
			if (fil.getJobThirdExport()!=null && !fil.getJobThirdExport().equals(""))ffilterAsText+=fil.getJobThirdExport()+" ";
			if (fil.getResponsibleCreate()!=null && !fil.getResponsibleCreate().equals(""))ffilterAsText+=fil.getResponsibleCreate()+" ";
			if (fil.getResponsibleRead()!=null && !fil.getResponsibleRead().equals(""))ffilterAsText+=fil.getResponsibleRead()+" ";
			if (fil.getResponsibleUpdate()!=null && !fil.getResponsibleUpdate().equals(""))ffilterAsText+=fil.getResponsibleUpdate()+" ";
			if (fil.getResponsibleExport()!=null && !fil.getResponsibleExport().equals(""))ffilterAsText+=fil.getResponsibleExport()+" ";
			if (fil.getAuthorizerCostCenterCreate()!=null && !fil.getAuthorizerCostCenterCreate().equals(""))ffilterAsText+=fil.getAuthorizerCostCenterCreate()+" ";
			if (fil.getAuthorizerCostCenterRead()!=null && !fil.getAuthorizerCostCenterRead().equals(""))ffilterAsText+=fil.getAuthorizerCostCenterRead()+" ";
			if (fil.getAuthorizerCostCenterUpdate()!=null && !fil.getAuthorizerCostCenterUpdate().equals(""))ffilterAsText+=fil.getAuthorizerCostCenterUpdate()+" ";
			if (fil.getAuthorizerCostCenterExport()!=null && !fil.getAuthorizerCostCenterExport().equals(""))ffilterAsText+=fil.getAuthorizerCostCenterExport()+" ";
			if (fil.getCharacteristicCreate()!=null && !fil.getCharacteristicCreate().equals(""))ffilterAsText+=fil.getCharacteristicCreate()+" ";
			if (fil.getCharacteristicRead()!=null && !fil.getCharacteristicRead().equals(""))ffilterAsText+=fil.getCharacteristicRead()+" ";
			if (fil.getCharacteristicUpdate()!=null && !fil.getCharacteristicUpdate().equals(""))ffilterAsText+=fil.getCharacteristicUpdate()+" ";
			if (fil.getCharacteristicExport()!=null && !fil.getCharacteristicExport().equals(""))ffilterAsText+=fil.getCharacteristicExport()+" ";
			if (fil.getAccountantCreate()!=null && !fil.getAccountantCreate().equals(""))ffilterAsText+=fil.getAccountantCreate()+" ";
			if (fil.getAccountantRead()!=null && !fil.getAccountantRead().equals(""))ffilterAsText+=fil.getAccountantRead()+" ";
			if (fil.getAccountantUpdate()!=null && !fil.getAccountantUpdate().equals(""))ffilterAsText+=fil.getAccountantUpdate()+" ";
			if (fil.getAccountantExport()!=null && !fil.getAccountantExport().equals(""))ffilterAsText+=fil.getAccountantExport()+" ";
			if (fil.getPhysicalLocationCreate()!=null && !fil.getPhysicalLocationCreate().equals(""))ffilterAsText+=fil.getPhysicalLocationCreate()+" ";
			if (fil.getPhysicalLocationRead()!=null && !fil.getPhysicalLocationRead().equals(""))ffilterAsText+=fil.getPhysicalLocationRead()+" ";
			if (fil.getPhysicalLocationUpdate()!=null && !fil.getPhysicalLocationUpdate().equals(""))ffilterAsText+=fil.getPhysicalLocationUpdate()+" ";
			if (fil.getPhysicalLocationExport()!=null && !fil.getPhysicalLocationExport().equals(""))ffilterAsText+=fil.getPhysicalLocationExport()+" ";
			if (fil.getStoreTypeCreate()!=null && !fil.getStoreTypeCreate().equals(""))ffilterAsText+=fil.getStoreTypeCreate()+" ";
			if (fil.getStoreTypeRead()!=null && !fil.getStoreTypeRead().equals(""))ffilterAsText+=fil.getStoreTypeRead()+" ";
			if (fil.getStoreTypeUpdate()!=null && !fil.getStoreTypeUpdate().equals(""))ffilterAsText+=fil.getStoreTypeUpdate()+" ";
			if (fil.getStoreTypeExport()!=null && !fil.getStoreTypeExport().equals(""))ffilterAsText+=fil.getStoreTypeExport()+" ";
			if (fil.getCostingTypeCreate()!=null && !fil.getCostingTypeCreate().equals(""))ffilterAsText+=fil.getCostingTypeCreate()+" ";
			if (fil.getCostingTypeRead()!=null && !fil.getCostingTypeRead().equals(""))ffilterAsText+=fil.getCostingTypeRead()+" ";
			if (fil.getCostingTypeUpdate()!=null && !fil.getCostingTypeUpdate().equals(""))ffilterAsText+=fil.getCostingTypeUpdate()+" ";
			if (fil.getCostingTypeExport()!=null && !fil.getCostingTypeExport().equals(""))ffilterAsText+=fil.getCostingTypeExport()+" ";
			if (fil.getWarehouseCreate()!=null && !fil.getWarehouseCreate().equals(""))ffilterAsText+=fil.getWarehouseCreate()+" ";
			if (fil.getWarehouseRead()!=null && !fil.getWarehouseRead().equals(""))ffilterAsText+=fil.getWarehouseRead()+" ";
			if (fil.getWarehouseUpdate()!=null && !fil.getWarehouseUpdate().equals(""))ffilterAsText+=fil.getWarehouseUpdate()+" ";
			if (fil.getWarehouseExport()!=null && !fil.getWarehouseExport().equals(""))ffilterAsText+=fil.getWarehouseExport()+" ";
			if (fil.getCoinCreate()!=null && !fil.getCoinCreate().equals(""))ffilterAsText+=fil.getCoinCreate()+" ";
			if (fil.getCoinRead()!=null && !fil.getCoinRead().equals(""))ffilterAsText+=fil.getCoinRead()+" ";
			if (fil.getCoinUpdate()!=null && !fil.getCoinUpdate().equals(""))ffilterAsText+=fil.getCoinUpdate()+" ";
			if (fil.getCoinExport()!=null && !fil.getCoinExport().equals(""))ffilterAsText+=fil.getCoinExport()+" ";
			if (fil.getTypeConceptKardexCreate()!=null && !fil.getTypeConceptKardexCreate().equals(""))ffilterAsText+=fil.getTypeConceptKardexCreate()+" ";
			if (fil.getTypeConceptKardexRead()!=null && !fil.getTypeConceptKardexRead().equals(""))ffilterAsText+=fil.getTypeConceptKardexRead()+" ";
			if (fil.getTypeConceptKardexUpdate()!=null && !fil.getTypeConceptKardexUpdate().equals(""))ffilterAsText+=fil.getTypeConceptKardexUpdate()+" ";
			if (fil.getTypeConceptKardexExport()!=null && !fil.getTypeConceptKardexExport().equals(""))ffilterAsText+=fil.getTypeConceptKardexExport()+" ";
			if (fil.getConceptKardexCreate()!=null && !fil.getConceptKardexCreate().equals(""))ffilterAsText+=fil.getConceptKardexCreate()+" ";
			if (fil.getConceptKardexRead()!=null && !fil.getConceptKardexRead().equals(""))ffilterAsText+=fil.getConceptKardexRead()+" ";
			if (fil.getConceptKardexUpdate()!=null && !fil.getConceptKardexUpdate().equals(""))ffilterAsText+=fil.getConceptKardexUpdate()+" ";
			if (fil.getConceptKardexExport()!=null && !fil.getConceptKardexExport().equals(""))ffilterAsText+=fil.getConceptKardexExport()+" ";
			if (fil.getContractCreate()!=null && !fil.getContractCreate().equals(""))ffilterAsText+=fil.getContractCreate()+" ";
			if (fil.getContractRead()!=null && !fil.getContractRead().equals(""))ffilterAsText+=fil.getContractRead()+" ";
			if (fil.getContractUpdate()!=null && !fil.getContractUpdate().equals(""))ffilterAsText+=fil.getContractUpdate()+" ";
			if (fil.getContractExport()!=null && !fil.getContractExport().equals(""))ffilterAsText+=fil.getContractExport()+" ";
			if (fil.getUsersCreate()!=null && !fil.getUsersCreate().equals(""))ffilterAsText+=fil.getUsersCreate()+" ";
			if (fil.getUsersRead()!=null && !fil.getUsersRead().equals(""))ffilterAsText+=fil.getUsersRead()+" ";
			if (fil.getUsersUpdate()!=null && !fil.getUsersUpdate().equals(""))ffilterAsText+=fil.getUsersUpdate()+" ";
			if (fil.getUsersExport()!=null && !fil.getUsersExport().equals(""))ffilterAsText+=fil.getUsersExport()+" ";
			if (fil.getCurrencyCreate()!=null && !fil.getCurrencyCreate().equals(""))ffilterAsText+=fil.getCurrencyCreate()+" ";
			if (fil.getCurrencyRead()!=null && !fil.getCurrencyRead().equals(""))ffilterAsText+=fil.getCurrencyRead()+" ";
			if (fil.getCurrencyUpdate()!=null && !fil.getCurrencyUpdate().equals(""))ffilterAsText+=fil.getCurrencyUpdate()+" ";
			if (fil.getCurrencyExport()!=null && !fil.getCurrencyExport().equals(""))ffilterAsText+=fil.getCurrencyExport()+" ";
			if (fil.getExchangeRateCreate()!=null && !fil.getExchangeRateCreate().equals(""))ffilterAsText+=fil.getExchangeRateCreate()+" ";
			if (fil.getExchangeRateRead()!=null && !fil.getExchangeRateRead().equals(""))ffilterAsText+=fil.getExchangeRateRead()+" ";
			if (fil.getExchangeRateUpdate()!=null && !fil.getExchangeRateUpdate().equals(""))ffilterAsText+=fil.getExchangeRateUpdate()+" ";
			if (fil.getExchangeRateExport()!=null && !fil.getExchangeRateExport().equals(""))ffilterAsText+=fil.getExchangeRateExport()+" ";
			if (fil.getContinentCreate()!=null && !fil.getContinentCreate().equals(""))ffilterAsText+=fil.getContinentCreate()+" ";
			if (fil.getContinentRead()!=null && !fil.getContinentRead().equals(""))ffilterAsText+=fil.getContinentRead()+" ";
			if (fil.getContinentUpdate()!=null && !fil.getContinentUpdate().equals(""))ffilterAsText+=fil.getContinentUpdate()+" ";
			if (fil.getContinentExport()!=null && !fil.getContinentExport().equals(""))ffilterAsText+=fil.getContinentExport()+" ";
			if (fil.getRegionCreate()!=null && !fil.getRegionCreate().equals(""))ffilterAsText+=fil.getRegionCreate()+" ";
			if (fil.getRegionRead()!=null && !fil.getRegionRead().equals(""))ffilterAsText+=fil.getRegionRead()+" ";
			if (fil.getRegionUpdate()!=null && !fil.getRegionUpdate().equals(""))ffilterAsText+=fil.getRegionUpdate()+" ";
			if (fil.getRegionExport()!=null && !fil.getRegionExport().equals(""))ffilterAsText+=fil.getRegionExport()+" ";
			if (fil.getCurrencyCodeCreate()!=null && !fil.getCurrencyCodeCreate().equals(""))ffilterAsText+=fil.getCurrencyCodeCreate()+" ";
			if (fil.getCurrencyCodeRead()!=null && !fil.getCurrencyCodeRead().equals(""))ffilterAsText+=fil.getCurrencyCodeRead()+" ";
			if (fil.getCurrencyCodeUpdate()!=null && !fil.getCurrencyCodeUpdate().equals(""))ffilterAsText+=fil.getCurrencyCodeUpdate()+" ";
			if (fil.getCurrencyCodeExport()!=null && !fil.getCurrencyCodeExport().equals(""))ffilterAsText+=fil.getCurrencyCodeExport()+" ";
			if (fil.getCountryCreate()!=null && !fil.getCountryCreate().equals(""))ffilterAsText+=fil.getCountryCreate()+" ";
			if (fil.getCountryRead()!=null && !fil.getCountryRead().equals(""))ffilterAsText+=fil.getCountryRead()+" ";
			if (fil.getCountryUpdate()!=null && !fil.getCountryUpdate().equals(""))ffilterAsText+=fil.getCountryUpdate()+" ";
			if (fil.getCountryExport()!=null && !fil.getCountryExport().equals(""))ffilterAsText+=fil.getCountryExport()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (login!=null && !login.equals(""))ffilterAsText+=login+" ";if (password!=null && !password.equals(""))ffilterAsText+=password+" ";if (role!=null && !role.equals(""))ffilterAsText+=role+" ";if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";if (companyCreate!=null && !companyCreate.equals(""))ffilterAsText+=companyCreate+" ";if (companyRead!=null && !companyRead.equals(""))ffilterAsText+=companyRead+" ";if (companyUpdate!=null && !companyUpdate.equals(""))ffilterAsText+=companyUpdate+" ";if (companyExport!=null && !companyExport.equals(""))ffilterAsText+=companyExport+" ";if (stateCreate!=null && !stateCreate.equals(""))ffilterAsText+=stateCreate+" ";if (stateRead!=null && !stateRead.equals(""))ffilterAsText+=stateRead+" ";if (stateUpdate!=null && !stateUpdate.equals(""))ffilterAsText+=stateUpdate+" ";if (stateExport!=null && !stateExport.equals(""))ffilterAsText+=stateExport+" ";if (measurementUnitCreate!=null && !measurementUnitCreate.equals(""))ffilterAsText+=measurementUnitCreate+" ";if (measurementUnitRead!=null && !measurementUnitRead.equals(""))ffilterAsText+=measurementUnitRead+" ";if (measurementUnitUpdate!=null && !measurementUnitUpdate.equals(""))ffilterAsText+=measurementUnitUpdate+" ";if (measurementUnitExport!=null && !measurementUnitExport.equals(""))ffilterAsText+=measurementUnitExport+" ";if (typeSorterCreate!=null && !typeSorterCreate.equals(""))ffilterAsText+=typeSorterCreate+" ";if (typeSorterRead!=null && !typeSorterRead.equals(""))ffilterAsText+=typeSorterRead+" ";if (typeSorterUpdate!=null && !typeSorterUpdate.equals(""))ffilterAsText+=typeSorterUpdate+" ";if (typeSorterExport!=null && !typeSorterExport.equals(""))ffilterAsText+=typeSorterExport+" ";if (sorterCreate!=null && !sorterCreate.equals(""))ffilterAsText+=sorterCreate+" ";if (sorterRead!=null && !sorterRead.equals(""))ffilterAsText+=sorterRead+" ";if (sorterUpdate!=null && !sorterUpdate.equals(""))ffilterAsText+=sorterUpdate+" ";if (sorterExport!=null && !sorterExport.equals(""))ffilterAsText+=sorterExport+" ";if (operationCreate!=null && !operationCreate.equals(""))ffilterAsText+=operationCreate+" ";if (operationRead!=null && !operationRead.equals(""))ffilterAsText+=operationRead+" ";if (operationUpdate!=null && !operationUpdate.equals(""))ffilterAsText+=operationUpdate+" ";if (operationExport!=null && !operationExport.equals(""))ffilterAsText+=operationExport+" ";if (referenceCreate!=null && !referenceCreate.equals(""))ffilterAsText+=referenceCreate+" ";if (referenceRead!=null && !referenceRead.equals(""))ffilterAsText+=referenceRead+" ";if (referenceUpdate!=null && !referenceUpdate.equals(""))ffilterAsText+=referenceUpdate+" ";if (referenceExport!=null && !referenceExport.equals(""))ffilterAsText+=referenceExport+" ";if (referenceOperationCreate!=null && !referenceOperationCreate.equals(""))ffilterAsText+=referenceOperationCreate+" ";if (referenceOperationRead!=null && !referenceOperationRead.equals(""))ffilterAsText+=referenceOperationRead+" ";if (referenceOperationUpdate!=null && !referenceOperationUpdate.equals(""))ffilterAsText+=referenceOperationUpdate+" ";if (referenceOperationExport!=null && !referenceOperationExport.equals(""))ffilterAsText+=referenceOperationExport+" ";if (technicalActionCreate!=null && !technicalActionCreate.equals(""))ffilterAsText+=technicalActionCreate+" ";if (technicalActionRead!=null && !technicalActionRead.equals(""))ffilterAsText+=technicalActionRead+" ";if (technicalActionUpdate!=null && !technicalActionUpdate.equals(""))ffilterAsText+=technicalActionUpdate+" ";if (technicalActionExport!=null && !technicalActionExport.equals(""))ffilterAsText+=technicalActionExport+" ";if (effectFailCreate!=null && !effectFailCreate.equals(""))ffilterAsText+=effectFailCreate+" ";if (effectFailRead!=null && !effectFailRead.equals(""))ffilterAsText+=effectFailRead+" ";if (effectFailUpdate!=null && !effectFailUpdate.equals(""))ffilterAsText+=effectFailUpdate+" ";if (effectFailExport!=null && !effectFailExport.equals(""))ffilterAsText+=effectFailExport+" ";if (effectFailTechnicalActionCreate!=null && !effectFailTechnicalActionCreate.equals(""))ffilterAsText+=effectFailTechnicalActionCreate+" ";if (effectFailTechnicalActionRead!=null && !effectFailTechnicalActionRead.equals(""))ffilterAsText+=effectFailTechnicalActionRead+" ";if (effectFailTechnicalActionUpdate!=null && !effectFailTechnicalActionUpdate.equals(""))ffilterAsText+=effectFailTechnicalActionUpdate+" ";if (effectFailTechnicalActionExport!=null && !effectFailTechnicalActionExport.equals(""))ffilterAsText+=effectFailTechnicalActionExport+" ";if (failureCauseCreate!=null && !failureCauseCreate.equals(""))ffilterAsText+=failureCauseCreate+" ";if (failureCauseRead!=null && !failureCauseRead.equals(""))ffilterAsText+=failureCauseRead+" ";if (failureCauseUpdate!=null && !failureCauseUpdate.equals(""))ffilterAsText+=failureCauseUpdate+" ";if (failureCauseExport!=null && !failureCauseExport.equals(""))ffilterAsText+=failureCauseExport+" ";if (failureCauseTechnicalactionCreate!=null && !failureCauseTechnicalactionCreate.equals(""))ffilterAsText+=failureCauseTechnicalactionCreate+" ";if (failureCauseTechnicalactionRead!=null && !failureCauseTechnicalactionRead.equals(""))ffilterAsText+=failureCauseTechnicalactionRead+" ";if (failureCauseTechnicalactionUpdate!=null && !failureCauseTechnicalactionUpdate.equals(""))ffilterAsText+=failureCauseTechnicalactionUpdate+" ";if (failureCauseTechnicalactionExport!=null && !failureCauseTechnicalactionExport.equals(""))ffilterAsText+=failureCauseTechnicalactionExport+" ";if (typeCauseCloseCreate!=null && !typeCauseCloseCreate.equals(""))ffilterAsText+=typeCauseCloseCreate+" ";if (typeCauseCloseRead!=null && !typeCauseCloseRead.equals(""))ffilterAsText+=typeCauseCloseRead+" ";if (typeCauseCloseUpdate!=null && !typeCauseCloseUpdate.equals(""))ffilterAsText+=typeCauseCloseUpdate+" ";if (typeCauseCloseExport!=null && !typeCauseCloseExport.equals(""))ffilterAsText+=typeCauseCloseExport+" ";if (causeCloseCreate!=null && !causeCloseCreate.equals(""))ffilterAsText+=causeCloseCreate+" ";if (causeCloseRead!=null && !causeCloseRead.equals(""))ffilterAsText+=causeCloseRead+" ";if (causeCloseUpdate!=null && !causeCloseUpdate.equals(""))ffilterAsText+=causeCloseUpdate+" ";if (causeCloseExport!=null && !causeCloseExport.equals(""))ffilterAsText+=causeCloseExport+" ";if (maintenanceActivityCreate!=null && !maintenanceActivityCreate.equals(""))ffilterAsText+=maintenanceActivityCreate+" ";if (maintenanceActivityRead!=null && !maintenanceActivityRead.equals(""))ffilterAsText+=maintenanceActivityRead+" ";if (maintenanceActivityUpdate!=null && !maintenanceActivityUpdate.equals(""))ffilterAsText+=maintenanceActivityUpdate+" ";if (maintenanceActivityExport!=null && !maintenanceActivityExport.equals(""))ffilterAsText+=maintenanceActivityExport+" ";if (maintenanceWorkCreate!=null && !maintenanceWorkCreate.equals(""))ffilterAsText+=maintenanceWorkCreate+" ";if (maintenanceWorkRead!=null && !maintenanceWorkRead.equals(""))ffilterAsText+=maintenanceWorkRead+" ";if (maintenanceWorkUpdate!=null && !maintenanceWorkUpdate.equals(""))ffilterAsText+=maintenanceWorkUpdate+" ";if (maintenanceWorkExport!=null && !maintenanceWorkExport.equals(""))ffilterAsText+=maintenanceWorkExport+" ";if (typeMaintenanceCreate!=null && !typeMaintenanceCreate.equals(""))ffilterAsText+=typeMaintenanceCreate+" ";if (typeMaintenanceRead!=null && !typeMaintenanceRead.equals(""))ffilterAsText+=typeMaintenanceRead+" ";if (typeMaintenanceUpdate!=null && !typeMaintenanceUpdate.equals(""))ffilterAsText+=typeMaintenanceUpdate+" ";if (typeMaintenanceExport!=null && !typeMaintenanceExport.equals(""))ffilterAsText+=typeMaintenanceExport+" ";if (causePendingCreate!=null && !causePendingCreate.equals(""))ffilterAsText+=causePendingCreate+" ";if (causePendingRead!=null && !causePendingRead.equals(""))ffilterAsText+=causePendingRead+" ";if (causePendingUpdate!=null && !causePendingUpdate.equals(""))ffilterAsText+=causePendingUpdate+" ";if (causePendingExport!=null && !causePendingExport.equals(""))ffilterAsText+=causePendingExport+" ";if (typeWorkCreate!=null && !typeWorkCreate.equals(""))ffilterAsText+=typeWorkCreate+" ";if (typeWorkRead!=null && !typeWorkRead.equals(""))ffilterAsText+=typeWorkRead+" ";if (typeWorkUpdate!=null && !typeWorkUpdate.equals(""))ffilterAsText+=typeWorkUpdate+" ";if (typeWorkExport!=null && !typeWorkExport.equals(""))ffilterAsText+=typeWorkExport+" ";if (otherConceptsCreate!=null && !otherConceptsCreate.equals(""))ffilterAsText+=otherConceptsCreate+" ";if (otherConceptsRead!=null && !otherConceptsRead.equals(""))ffilterAsText+=otherConceptsRead+" ";if (otherConceptsUpdate!=null && !otherConceptsUpdate.equals(""))ffilterAsText+=otherConceptsUpdate+" ";if (otherConceptsExport!=null && !otherConceptsExport.equals(""))ffilterAsText+=otherConceptsExport+" ";if (stateCostCentersCreate!=null && !stateCostCentersCreate.equals(""))ffilterAsText+=stateCostCentersCreate+" ";if (stateCostCentersRead!=null && !stateCostCentersRead.equals(""))ffilterAsText+=stateCostCentersRead+" ";if (stateCostCentersUpdate!=null && !stateCostCentersUpdate.equals(""))ffilterAsText+=stateCostCentersUpdate+" ";if (stateCostCentersExport!=null && !stateCostCentersExport.equals(""))ffilterAsText+=stateCostCentersExport+" ";if (costCentersCustomersCreate!=null && !costCentersCustomersCreate.equals(""))ffilterAsText+=costCentersCustomersCreate+" ";if (costCentersCustomersRead!=null && !costCentersCustomersRead.equals(""))ffilterAsText+=costCentersCustomersRead+" ";if (costCentersCustomersUpdate!=null && !costCentersCustomersUpdate.equals(""))ffilterAsText+=costCentersCustomersUpdate+" ";if (costCentersCustomersExport!=null && !costCentersCustomersExport.equals(""))ffilterAsText+=costCentersCustomersExport+" ";if (responsibleCenterCreate!=null && !responsibleCenterCreate.equals(""))ffilterAsText+=responsibleCenterCreate+" ";if (responsibleCenterRead!=null && !responsibleCenterRead.equals(""))ffilterAsText+=responsibleCenterRead+" ";if (responsibleCenterUpdate!=null && !responsibleCenterUpdate.equals(""))ffilterAsText+=responsibleCenterUpdate+" ";if (responsibleCenterExport!=null && !responsibleCenterExport.equals(""))ffilterAsText+=responsibleCenterExport+" ";if (jobCreate!=null && !jobCreate.equals(""))ffilterAsText+=jobCreate+" ";if (jobRead!=null && !jobRead.equals(""))ffilterAsText+=jobRead+" ";if (jobUpdate!=null && !jobUpdate.equals(""))ffilterAsText+=jobUpdate+" ";if (jobExport!=null && !jobExport.equals(""))ffilterAsText+=jobExport+" ";if (thirdCreate!=null && !thirdCreate.equals(""))ffilterAsText+=thirdCreate+" ";if (thirdRead!=null && !thirdRead.equals(""))ffilterAsText+=thirdRead+" ";if (thirdUpdate!=null && !thirdUpdate.equals(""))ffilterAsText+=thirdUpdate+" ";if (thirdExport!=null && !thirdExport.equals(""))ffilterAsText+=thirdExport+" ";if (jobThirdCreate!=null && !jobThirdCreate.equals(""))ffilterAsText+=jobThirdCreate+" ";if (jobThirdRead!=null && !jobThirdRead.equals(""))ffilterAsText+=jobThirdRead+" ";if (jobThirdUpdate!=null && !jobThirdUpdate.equals(""))ffilterAsText+=jobThirdUpdate+" ";if (jobThirdExport!=null && !jobThirdExport.equals(""))ffilterAsText+=jobThirdExport+" ";if (responsibleCreate!=null && !responsibleCreate.equals(""))ffilterAsText+=responsibleCreate+" ";if (responsibleRead!=null && !responsibleRead.equals(""))ffilterAsText+=responsibleRead+" ";if (responsibleUpdate!=null && !responsibleUpdate.equals(""))ffilterAsText+=responsibleUpdate+" ";if (responsibleExport!=null && !responsibleExport.equals(""))ffilterAsText+=responsibleExport+" ";if (authorizerCostCenterCreate!=null && !authorizerCostCenterCreate.equals(""))ffilterAsText+=authorizerCostCenterCreate+" ";if (authorizerCostCenterRead!=null && !authorizerCostCenterRead.equals(""))ffilterAsText+=authorizerCostCenterRead+" ";if (authorizerCostCenterUpdate!=null && !authorizerCostCenterUpdate.equals(""))ffilterAsText+=authorizerCostCenterUpdate+" ";if (authorizerCostCenterExport!=null && !authorizerCostCenterExport.equals(""))ffilterAsText+=authorizerCostCenterExport+" ";if (characteristicCreate!=null && !characteristicCreate.equals(""))ffilterAsText+=characteristicCreate+" ";if (characteristicRead!=null && !characteristicRead.equals(""))ffilterAsText+=characteristicRead+" ";if (characteristicUpdate!=null && !characteristicUpdate.equals(""))ffilterAsText+=characteristicUpdate+" ";if (characteristicExport!=null && !characteristicExport.equals(""))ffilterAsText+=characteristicExport+" ";if (accountantCreate!=null && !accountantCreate.equals(""))ffilterAsText+=accountantCreate+" ";if (accountantRead!=null && !accountantRead.equals(""))ffilterAsText+=accountantRead+" ";if (accountantUpdate!=null && !accountantUpdate.equals(""))ffilterAsText+=accountantUpdate+" ";if (accountantExport!=null && !accountantExport.equals(""))ffilterAsText+=accountantExport+" ";if (physicalLocationCreate!=null && !physicalLocationCreate.equals(""))ffilterAsText+=physicalLocationCreate+" ";if (physicalLocationRead!=null && !physicalLocationRead.equals(""))ffilterAsText+=physicalLocationRead+" ";if (physicalLocationUpdate!=null && !physicalLocationUpdate.equals(""))ffilterAsText+=physicalLocationUpdate+" ";if (physicalLocationExport!=null && !physicalLocationExport.equals(""))ffilterAsText+=physicalLocationExport+" ";if (storeTypeCreate!=null && !storeTypeCreate.equals(""))ffilterAsText+=storeTypeCreate+" ";if (storeTypeRead!=null && !storeTypeRead.equals(""))ffilterAsText+=storeTypeRead+" ";if (storeTypeUpdate!=null && !storeTypeUpdate.equals(""))ffilterAsText+=storeTypeUpdate+" ";if (storeTypeExport!=null && !storeTypeExport.equals(""))ffilterAsText+=storeTypeExport+" ";if (costingTypeCreate!=null && !costingTypeCreate.equals(""))ffilterAsText+=costingTypeCreate+" ";if (costingTypeRead!=null && !costingTypeRead.equals(""))ffilterAsText+=costingTypeRead+" ";if (costingTypeUpdate!=null && !costingTypeUpdate.equals(""))ffilterAsText+=costingTypeUpdate+" ";if (costingTypeExport!=null && !costingTypeExport.equals(""))ffilterAsText+=costingTypeExport+" ";if (warehouseCreate!=null && !warehouseCreate.equals(""))ffilterAsText+=warehouseCreate+" ";if (warehouseRead!=null && !warehouseRead.equals(""))ffilterAsText+=warehouseRead+" ";if (warehouseUpdate!=null && !warehouseUpdate.equals(""))ffilterAsText+=warehouseUpdate+" ";if (warehouseExport!=null && !warehouseExport.equals(""))ffilterAsText+=warehouseExport+" ";if (coinCreate!=null && !coinCreate.equals(""))ffilterAsText+=coinCreate+" ";if (coinRead!=null && !coinRead.equals(""))ffilterAsText+=coinRead+" ";if (coinUpdate!=null && !coinUpdate.equals(""))ffilterAsText+=coinUpdate+" ";if (coinExport!=null && !coinExport.equals(""))ffilterAsText+=coinExport+" ";if (typeConceptKardexCreate!=null && !typeConceptKardexCreate.equals(""))ffilterAsText+=typeConceptKardexCreate+" ";if (typeConceptKardexRead!=null && !typeConceptKardexRead.equals(""))ffilterAsText+=typeConceptKardexRead+" ";if (typeConceptKardexUpdate!=null && !typeConceptKardexUpdate.equals(""))ffilterAsText+=typeConceptKardexUpdate+" ";if (typeConceptKardexExport!=null && !typeConceptKardexExport.equals(""))ffilterAsText+=typeConceptKardexExport+" ";if (conceptKardexCreate!=null && !conceptKardexCreate.equals(""))ffilterAsText+=conceptKardexCreate+" ";if (conceptKardexRead!=null && !conceptKardexRead.equals(""))ffilterAsText+=conceptKardexRead+" ";if (conceptKardexUpdate!=null && !conceptKardexUpdate.equals(""))ffilterAsText+=conceptKardexUpdate+" ";if (conceptKardexExport!=null && !conceptKardexExport.equals(""))ffilterAsText+=conceptKardexExport+" ";if (contractCreate!=null && !contractCreate.equals(""))ffilterAsText+=contractCreate+" ";if (contractRead!=null && !contractRead.equals(""))ffilterAsText+=contractRead+" ";if (contractUpdate!=null && !contractUpdate.equals(""))ffilterAsText+=contractUpdate+" ";if (contractExport!=null && !contractExport.equals(""))ffilterAsText+=contractExport+" ";if (usersCreate!=null && !usersCreate.equals(""))ffilterAsText+=usersCreate+" ";if (usersRead!=null && !usersRead.equals(""))ffilterAsText+=usersRead+" ";if (usersUpdate!=null && !usersUpdate.equals(""))ffilterAsText+=usersUpdate+" ";if (usersExport!=null && !usersExport.equals(""))ffilterAsText+=usersExport+" ";if (currencyCreate!=null && !currencyCreate.equals(""))ffilterAsText+=currencyCreate+" ";if (currencyRead!=null && !currencyRead.equals(""))ffilterAsText+=currencyRead+" ";if (currencyUpdate!=null && !currencyUpdate.equals(""))ffilterAsText+=currencyUpdate+" ";if (currencyExport!=null && !currencyExport.equals(""))ffilterAsText+=currencyExport+" ";if (exchangeRateCreate!=null && !exchangeRateCreate.equals(""))ffilterAsText+=exchangeRateCreate+" ";if (exchangeRateRead!=null && !exchangeRateRead.equals(""))ffilterAsText+=exchangeRateRead+" ";if (exchangeRateUpdate!=null && !exchangeRateUpdate.equals(""))ffilterAsText+=exchangeRateUpdate+" ";if (exchangeRateExport!=null && !exchangeRateExport.equals(""))ffilterAsText+=exchangeRateExport+" ";if (continentCreate!=null && !continentCreate.equals(""))ffilterAsText+=continentCreate+" ";if (continentRead!=null && !continentRead.equals(""))ffilterAsText+=continentRead+" ";if (continentUpdate!=null && !continentUpdate.equals(""))ffilterAsText+=continentUpdate+" ";if (continentExport!=null && !continentExport.equals(""))ffilterAsText+=continentExport+" ";if (regionCreate!=null && !regionCreate.equals(""))ffilterAsText+=regionCreate+" ";if (regionRead!=null && !regionRead.equals(""))ffilterAsText+=regionRead+" ";if (regionUpdate!=null && !regionUpdate.equals(""))ffilterAsText+=regionUpdate+" ";if (regionExport!=null && !regionExport.equals(""))ffilterAsText+=regionExport+" ";if (currencyCodeCreate!=null && !currencyCodeCreate.equals(""))ffilterAsText+=currencyCodeCreate+" ";if (currencyCodeRead!=null && !currencyCodeRead.equals(""))ffilterAsText+=currencyCodeRead+" ";if (currencyCodeUpdate!=null && !currencyCodeUpdate.equals(""))ffilterAsText+=currencyCodeUpdate+" ";if (currencyCodeExport!=null && !currencyCodeExport.equals(""))ffilterAsText+=currencyCodeExport+" ";if (countryCreate!=null && !countryCreate.equals(""))ffilterAsText+=countryCreate+" ";if (countryRead!=null && !countryRead.equals(""))ffilterAsText+=countryRead+" ";if (countryUpdate!=null && !countryUpdate.equals(""))ffilterAsText+=countryUpdate+" ";if (countryExport!=null && !countryExport.equals(""))ffilterAsText+=countryExport+" ";
		if (this.getDateOfModification()!=null && !this.getDateOfModification().equals(""))ffilterAsText+=this.getDateOfModification()+" ";
		if (this.getUserName()!=null && !this.getUserName().equals(""))ffilterAsText+=this.getUserName()+" ";
		if (this.getDateOfCreation()!=null && !this.getDateOfCreation().equals(""))ffilterAsText+=this.getDateOfCreation()+" ";
		if (this.getCreatedBy()!=null && !this.getCreatedBy().equals(""))ffilterAsText+=this.getCreatedBy()+" ";
		
		return ffilterAsText;
	}

	public void setFfilterAsText(String ffilterAsText) {
		this.ffilterAsText = ffilterAsText;
	}
	
	public String getAaaaction() {
		return aaaaction;
	}

	public void setAaaaction(String aaaaction) {
		this.aaaaction = aaaaction;
	}
	
	public Boolean getChangeSortingFieldDirection() {
		return changeSortingFieldDirection;
	}

	public void setChangeSortingFieldDirection(Boolean changeSortingFieldDirection) {
		this.changeSortingFieldDirection = changeSortingFieldDirection;
	}
	
	public Integer getSelectedFilterPage() {
		return selectedFilterPage;
	}

	public void setSelectedFilterPage(Integer selectedFilterPage) {
		this.selectedFilterPage = selectedFilterPage;
	}

	public String getSelectedFilterField() {
		return selectedFilterField;
	}

	public void setSelectedFilterField(String selectedFilterField) {
		this.selectedFilterField = selectedFilterField;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public String getDateOfModification() {
		return dateOfModification;
	}
	
	public void setDateOfModification(String dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}