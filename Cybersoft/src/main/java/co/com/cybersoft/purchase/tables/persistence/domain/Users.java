package co.com.cybersoft.purchase.tables.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import co.com.cybersoft.maintenance.tables.persistence.domain.Company;

import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String companyPermissions;
	private String statePermissions;
	private String measurementUnitPermissions;
	private String typeSorterPermissions;
	private String sorterPermissions;
	private String operationPermissions;
	private String referencePermissions;
	private String referenceOperationPermissions;
	private String technicalActionPermissions;
	private String effectFailPermissions;
	@Column(name="effectFailTechnicalActionPermi")
	private String effectFailTechnicalActionPermissions;
	private String failureCausePermissions;
	@Column(name="failureCauseTechnicalactionPer")
	private String failureCauseTechnicalactionPermissions;
	private String typeCauseClosePermissions;
	private String causeClosePermissions;
	private String maintenanceActivityPermissions;
	private String maintenanceWorkPermissions;
	private String typeMaintenancePermissions;
	private String causePendingPermissions;
	private String typeWorkPermissions;
	private String otherConceptsPermissions;
	private String stateCostCentersPermissions;
	@Column(name="costCentersCustomersPermission")
	private String costCentersCustomersPermissions;
	private String responsibleCenterPermissions;
	private String jobPermissions;
	private String thirdPermissions;
	private String jobThirdPermissions;
	private String responsiblePermissions;
	@Column(name="authorizerCostCenterPermission")
	private String authorizerCostCenterPermissions;
	private String characteristicPermissions;
	private String accountantPermissions;
	private String physicalLocationPermissions;
	private String storeTypePermissions;
	private String costingTypePermissions;
	private String warehousePermissions;
	private String coinPermissions;
	private String typeConceptKardexPermissions;
	private String conceptKardexPermissions;
	private String contractPermissions;
	private String usersPermissions;
	private String currencyPermissions;
	private String exchangeRatePermissions;
	private String continentPermissions;
	private String regionPermissions;
	private String currencyCodePermissions;
	private String countryPermissions;
	private String login;

	private String password;

	private String role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	private Boolean active;


	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCompanyPermissions() {
		return companyPermissions;	
	}
		
	public void setCompanyPermissions(String companyPermissions) {
		this.companyPermissions = companyPermissions;	
	}
	public String getStatePermissions() {
		return statePermissions;	
	}
		
	public void setStatePermissions(String statePermissions) {
		this.statePermissions = statePermissions;	
	}
	public String getMeasurementUnitPermissions() {
		return measurementUnitPermissions;	
	}
		
	public void setMeasurementUnitPermissions(String measurementUnitPermissions) {
		this.measurementUnitPermissions = measurementUnitPermissions;	
	}
	public String getTypeSorterPermissions() {
		return typeSorterPermissions;	
	}
		
	public void setTypeSorterPermissions(String typeSorterPermissions) {
		this.typeSorterPermissions = typeSorterPermissions;	
	}
	public String getSorterPermissions() {
		return sorterPermissions;	
	}
		
	public void setSorterPermissions(String sorterPermissions) {
		this.sorterPermissions = sorterPermissions;	
	}
	public String getOperationPermissions() {
		return operationPermissions;	
	}
		
	public void setOperationPermissions(String operationPermissions) {
		this.operationPermissions = operationPermissions;	
	}
	public String getReferencePermissions() {
		return referencePermissions;	
	}
		
	public void setReferencePermissions(String referencePermissions) {
		this.referencePermissions = referencePermissions;	
	}
	public String getReferenceOperationPermissions() {
		return referenceOperationPermissions;	
	}
		
	public void setReferenceOperationPermissions(String referenceOperationPermissions) {
		this.referenceOperationPermissions = referenceOperationPermissions;	
	}
	public String getTechnicalActionPermissions() {
		return technicalActionPermissions;	
	}
		
	public void setTechnicalActionPermissions(String technicalActionPermissions) {
		this.technicalActionPermissions = technicalActionPermissions;	
	}
	public String getEffectFailPermissions() {
		return effectFailPermissions;	
	}
		
	public void setEffectFailPermissions(String effectFailPermissions) {
		this.effectFailPermissions = effectFailPermissions;	
	}
	public String getEffectFailTechnicalActionPermissions() {
		return effectFailTechnicalActionPermissions;	
	}
		
	public void setEffectFailTechnicalActionPermissions(String effectFailTechnicalActionPermissions) {
		this.effectFailTechnicalActionPermissions = effectFailTechnicalActionPermissions;	
	}
	public String getFailureCausePermissions() {
		return failureCausePermissions;	
	}
		
	public void setFailureCausePermissions(String failureCausePermissions) {
		this.failureCausePermissions = failureCausePermissions;	
	}
	public String getFailureCauseTechnicalactionPermissions() {
		return failureCauseTechnicalactionPermissions;	
	}
		
	public void setFailureCauseTechnicalactionPermissions(String failureCauseTechnicalactionPermissions) {
		this.failureCauseTechnicalactionPermissions = failureCauseTechnicalactionPermissions;	
	}
	public String getTypeCauseClosePermissions() {
		return typeCauseClosePermissions;	
	}
		
	public void setTypeCauseClosePermissions(String typeCauseClosePermissions) {
		this.typeCauseClosePermissions = typeCauseClosePermissions;	
	}
	public String getCauseClosePermissions() {
		return causeClosePermissions;	
	}
		
	public void setCauseClosePermissions(String causeClosePermissions) {
		this.causeClosePermissions = causeClosePermissions;	
	}
	public String getMaintenanceActivityPermissions() {
		return maintenanceActivityPermissions;	
	}
		
	public void setMaintenanceActivityPermissions(String maintenanceActivityPermissions) {
		this.maintenanceActivityPermissions = maintenanceActivityPermissions;	
	}
	public String getMaintenanceWorkPermissions() {
		return maintenanceWorkPermissions;	
	}
		
	public void setMaintenanceWorkPermissions(String maintenanceWorkPermissions) {
		this.maintenanceWorkPermissions = maintenanceWorkPermissions;	
	}
	public String getTypeMaintenancePermissions() {
		return typeMaintenancePermissions;	
	}
		
	public void setTypeMaintenancePermissions(String typeMaintenancePermissions) {
		this.typeMaintenancePermissions = typeMaintenancePermissions;	
	}
	public String getCausePendingPermissions() {
		return causePendingPermissions;	
	}
		
	public void setCausePendingPermissions(String causePendingPermissions) {
		this.causePendingPermissions = causePendingPermissions;	
	}
	public String getTypeWorkPermissions() {
		return typeWorkPermissions;	
	}
		
	public void setTypeWorkPermissions(String typeWorkPermissions) {
		this.typeWorkPermissions = typeWorkPermissions;	
	}
	public String getOtherConceptsPermissions() {
		return otherConceptsPermissions;	
	}
		
	public void setOtherConceptsPermissions(String otherConceptsPermissions) {
		this.otherConceptsPermissions = otherConceptsPermissions;	
	}
	public String getStateCostCentersPermissions() {
		return stateCostCentersPermissions;	
	}
		
	public void setStateCostCentersPermissions(String stateCostCentersPermissions) {
		this.stateCostCentersPermissions = stateCostCentersPermissions;	
	}
	public String getCostCentersCustomersPermissions() {
		return costCentersCustomersPermissions;	
	}
		
	public void setCostCentersCustomersPermissions(String costCentersCustomersPermissions) {
		this.costCentersCustomersPermissions = costCentersCustomersPermissions;	
	}
	public String getResponsibleCenterPermissions() {
		return responsibleCenterPermissions;	
	}
		
	public void setResponsibleCenterPermissions(String responsibleCenterPermissions) {
		this.responsibleCenterPermissions = responsibleCenterPermissions;	
	}
	public String getJobPermissions() {
		return jobPermissions;	
	}
		
	public void setJobPermissions(String jobPermissions) {
		this.jobPermissions = jobPermissions;	
	}
	public String getThirdPermissions() {
		return thirdPermissions;	
	}
		
	public void setThirdPermissions(String thirdPermissions) {
		this.thirdPermissions = thirdPermissions;	
	}
	public String getJobThirdPermissions() {
		return jobThirdPermissions;	
	}
		
	public void setJobThirdPermissions(String jobThirdPermissions) {
		this.jobThirdPermissions = jobThirdPermissions;	
	}
	public String getResponsiblePermissions() {
		return responsiblePermissions;	
	}
		
	public void setResponsiblePermissions(String responsiblePermissions) {
		this.responsiblePermissions = responsiblePermissions;	
	}
	public String getAuthorizerCostCenterPermissions() {
		return authorizerCostCenterPermissions;	
	}
		
	public void setAuthorizerCostCenterPermissions(String authorizerCostCenterPermissions) {
		this.authorizerCostCenterPermissions = authorizerCostCenterPermissions;	
	}
	public String getCharacteristicPermissions() {
		return characteristicPermissions;	
	}
		
	public void setCharacteristicPermissions(String characteristicPermissions) {
		this.characteristicPermissions = characteristicPermissions;	
	}
	public String getAccountantPermissions() {
		return accountantPermissions;	
	}
		
	public void setAccountantPermissions(String accountantPermissions) {
		this.accountantPermissions = accountantPermissions;	
	}
	public String getPhysicalLocationPermissions() {
		return physicalLocationPermissions;	
	}
		
	public void setPhysicalLocationPermissions(String physicalLocationPermissions) {
		this.physicalLocationPermissions = physicalLocationPermissions;	
	}
	public String getStoreTypePermissions() {
		return storeTypePermissions;	
	}
		
	public void setStoreTypePermissions(String storeTypePermissions) {
		this.storeTypePermissions = storeTypePermissions;	
	}
	public String getCostingTypePermissions() {
		return costingTypePermissions;	
	}
		
	public void setCostingTypePermissions(String costingTypePermissions) {
		this.costingTypePermissions = costingTypePermissions;	
	}
	public String getWarehousePermissions() {
		return warehousePermissions;	
	}
		
	public void setWarehousePermissions(String warehousePermissions) {
		this.warehousePermissions = warehousePermissions;	
	}
	public String getCoinPermissions() {
		return coinPermissions;	
	}
		
	public void setCoinPermissions(String coinPermissions) {
		this.coinPermissions = coinPermissions;	
	}
	public String getTypeConceptKardexPermissions() {
		return typeConceptKardexPermissions;	
	}
		
	public void setTypeConceptKardexPermissions(String typeConceptKardexPermissions) {
		this.typeConceptKardexPermissions = typeConceptKardexPermissions;	
	}
	public String getConceptKardexPermissions() {
		return conceptKardexPermissions;	
	}
		
	public void setConceptKardexPermissions(String conceptKardexPermissions) {
		this.conceptKardexPermissions = conceptKardexPermissions;	
	}
	public String getContractPermissions() {
		return contractPermissions;	
	}
		
	public void setContractPermissions(String contractPermissions) {
		this.contractPermissions = contractPermissions;	
	}
	public String getUsersPermissions() {
		return usersPermissions;	
	}
		
	public void setUsersPermissions(String usersPermissions) {
		this.usersPermissions = usersPermissions;	
	}
	public String getCurrencyPermissions() {
		return currencyPermissions;	
	}
		
	public void setCurrencyPermissions(String currencyPermissions) {
		this.currencyPermissions = currencyPermissions;	
	}
	public String getExchangeRatePermissions() {
		return exchangeRatePermissions;	
	}
		
	public void setExchangeRatePermissions(String exchangeRatePermissions) {
		this.exchangeRatePermissions = exchangeRatePermissions;	
	}
	public String getContinentPermissions() {
		return continentPermissions;	
	}
		
	public void setContinentPermissions(String continentPermissions) {
		this.continentPermissions = continentPermissions;	
	}
	public String getRegionPermissions() {
		return regionPermissions;	
	}
		
	public void setRegionPermissions(String regionPermissions) {
		this.regionPermissions = regionPermissions;	
	}
	public String getCurrencyCodePermissions() {
		return currencyCodePermissions;	
	}
		
	public void setCurrencyCodePermissions(String currencyCodePermissions) {
		this.currencyCodePermissions = currencyCodePermissions;	
	}
	public String getCountryPermissions() {
		return countryPermissions;	
	}
		
	public void setCountryPermissions(String countryPermissions) {
		this.countryPermissions = countryPermissions;	
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
	public Company getCompany() {
		return company;	
	}
		
	public void setCompany(Company company) {
		this.company = company;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Users fromUsersDetails(UsersDetails details){
		BeanUtils.copyProperties(details, this);
		
		this.companyPermissions=(details.getCompanyCreate()?"C":"")+(details.getCompanyRead()?"R":"")+(details.getCompanyUpdate()?"U":"")+(details.getCompanyExport()?"E":"");
		this.statePermissions=(details.getStateCreate()?"C":"")+(details.getStateRead()?"R":"")+(details.getStateUpdate()?"U":"")+(details.getStateExport()?"E":"");
		this.measurementUnitPermissions=(details.getMeasurementUnitCreate()?"C":"")+(details.getMeasurementUnitRead()?"R":"")+(details.getMeasurementUnitUpdate()?"U":"")+(details.getMeasurementUnitExport()?"E":"");
		this.typeSorterPermissions=(details.getTypeSorterCreate()?"C":"")+(details.getTypeSorterRead()?"R":"")+(details.getTypeSorterUpdate()?"U":"")+(details.getTypeSorterExport()?"E":"");
		this.sorterPermissions=(details.getSorterCreate()?"C":"")+(details.getSorterRead()?"R":"")+(details.getSorterUpdate()?"U":"")+(details.getSorterExport()?"E":"");
		this.operationPermissions=(details.getOperationCreate()?"C":"")+(details.getOperationRead()?"R":"")+(details.getOperationUpdate()?"U":"")+(details.getOperationExport()?"E":"");
		this.referencePermissions=(details.getReferenceCreate()?"C":"")+(details.getReferenceRead()?"R":"")+(details.getReferenceUpdate()?"U":"")+(details.getReferenceExport()?"E":"");
		this.referenceOperationPermissions=(details.getReferenceOperationCreate()?"C":"")+(details.getReferenceOperationRead()?"R":"")+(details.getReferenceOperationUpdate()?"U":"")+(details.getReferenceOperationExport()?"E":"");
		this.technicalActionPermissions=(details.getTechnicalActionCreate()?"C":"")+(details.getTechnicalActionRead()?"R":"")+(details.getTechnicalActionUpdate()?"U":"")+(details.getTechnicalActionExport()?"E":"");
		this.effectFailPermissions=(details.getEffectFailCreate()?"C":"")+(details.getEffectFailRead()?"R":"")+(details.getEffectFailUpdate()?"U":"")+(details.getEffectFailExport()?"E":"");
		this.effectFailTechnicalActionPermissions=(details.getEffectFailTechnicalActionCreate()?"C":"")+(details.getEffectFailTechnicalActionRead()?"R":"")+(details.getEffectFailTechnicalActionUpdate()?"U":"")+(details.getEffectFailTechnicalActionExport()?"E":"");
		this.failureCausePermissions=(details.getFailureCauseCreate()?"C":"")+(details.getFailureCauseRead()?"R":"")+(details.getFailureCauseUpdate()?"U":"")+(details.getFailureCauseExport()?"E":"");
		this.failureCauseTechnicalactionPermissions=(details.getFailureCauseTechnicalactionCreate()?"C":"")+(details.getFailureCauseTechnicalactionRead()?"R":"")+(details.getFailureCauseTechnicalactionUpdate()?"U":"")+(details.getFailureCauseTechnicalactionExport()?"E":"");
		this.typeCauseClosePermissions=(details.getTypeCauseCloseCreate()?"C":"")+(details.getTypeCauseCloseRead()?"R":"")+(details.getTypeCauseCloseUpdate()?"U":"")+(details.getTypeCauseCloseExport()?"E":"");
		this.causeClosePermissions=(details.getCauseCloseCreate()?"C":"")+(details.getCauseCloseRead()?"R":"")+(details.getCauseCloseUpdate()?"U":"")+(details.getCauseCloseExport()?"E":"");
		this.maintenanceActivityPermissions=(details.getMaintenanceActivityCreate()?"C":"")+(details.getMaintenanceActivityRead()?"R":"")+(details.getMaintenanceActivityUpdate()?"U":"")+(details.getMaintenanceActivityExport()?"E":"");
		this.maintenanceWorkPermissions=(details.getMaintenanceWorkCreate()?"C":"")+(details.getMaintenanceWorkRead()?"R":"")+(details.getMaintenanceWorkUpdate()?"U":"")+(details.getMaintenanceWorkExport()?"E":"");
		this.typeMaintenancePermissions=(details.getTypeMaintenanceCreate()?"C":"")+(details.getTypeMaintenanceRead()?"R":"")+(details.getTypeMaintenanceUpdate()?"U":"")+(details.getTypeMaintenanceExport()?"E":"");
		this.causePendingPermissions=(details.getCausePendingCreate()?"C":"")+(details.getCausePendingRead()?"R":"")+(details.getCausePendingUpdate()?"U":"")+(details.getCausePendingExport()?"E":"");
		this.typeWorkPermissions=(details.getTypeWorkCreate()?"C":"")+(details.getTypeWorkRead()?"R":"")+(details.getTypeWorkUpdate()?"U":"")+(details.getTypeWorkExport()?"E":"");
		this.otherConceptsPermissions=(details.getOtherConceptsCreate()?"C":"")+(details.getOtherConceptsRead()?"R":"")+(details.getOtherConceptsUpdate()?"U":"")+(details.getOtherConceptsExport()?"E":"");
		this.stateCostCentersPermissions=(details.getStateCostCentersCreate()?"C":"")+(details.getStateCostCentersRead()?"R":"")+(details.getStateCostCentersUpdate()?"U":"")+(details.getStateCostCentersExport()?"E":"");
		this.costCentersCustomersPermissions=(details.getCostCentersCustomersCreate()?"C":"")+(details.getCostCentersCustomersRead()?"R":"")+(details.getCostCentersCustomersUpdate()?"U":"")+(details.getCostCentersCustomersExport()?"E":"");
		this.responsibleCenterPermissions=(details.getResponsibleCenterCreate()?"C":"")+(details.getResponsibleCenterRead()?"R":"")+(details.getResponsibleCenterUpdate()?"U":"")+(details.getResponsibleCenterExport()?"E":"");
		this.jobPermissions=(details.getJobCreate()?"C":"")+(details.getJobRead()?"R":"")+(details.getJobUpdate()?"U":"")+(details.getJobExport()?"E":"");
		this.thirdPermissions=(details.getThirdCreate()?"C":"")+(details.getThirdRead()?"R":"")+(details.getThirdUpdate()?"U":"")+(details.getThirdExport()?"E":"");
		this.jobThirdPermissions=(details.getJobThirdCreate()?"C":"")+(details.getJobThirdRead()?"R":"")+(details.getJobThirdUpdate()?"U":"")+(details.getJobThirdExport()?"E":"");
		this.responsiblePermissions=(details.getResponsibleCreate()?"C":"")+(details.getResponsibleRead()?"R":"")+(details.getResponsibleUpdate()?"U":"")+(details.getResponsibleExport()?"E":"");
		this.authorizerCostCenterPermissions=(details.getAuthorizerCostCenterCreate()?"C":"")+(details.getAuthorizerCostCenterRead()?"R":"")+(details.getAuthorizerCostCenterUpdate()?"U":"")+(details.getAuthorizerCostCenterExport()?"E":"");
		this.characteristicPermissions=(details.getCharacteristicCreate()?"C":"")+(details.getCharacteristicRead()?"R":"")+(details.getCharacteristicUpdate()?"U":"")+(details.getCharacteristicExport()?"E":"");
		this.accountantPermissions=(details.getAccountantCreate()?"C":"")+(details.getAccountantRead()?"R":"")+(details.getAccountantUpdate()?"U":"")+(details.getAccountantExport()?"E":"");
		this.physicalLocationPermissions=(details.getPhysicalLocationCreate()?"C":"")+(details.getPhysicalLocationRead()?"R":"")+(details.getPhysicalLocationUpdate()?"U":"")+(details.getPhysicalLocationExport()?"E":"");
		this.storeTypePermissions=(details.getStoreTypeCreate()?"C":"")+(details.getStoreTypeRead()?"R":"")+(details.getStoreTypeUpdate()?"U":"")+(details.getStoreTypeExport()?"E":"");
		this.costingTypePermissions=(details.getCostingTypeCreate()?"C":"")+(details.getCostingTypeRead()?"R":"")+(details.getCostingTypeUpdate()?"U":"")+(details.getCostingTypeExport()?"E":"");
		this.warehousePermissions=(details.getWarehouseCreate()?"C":"")+(details.getWarehouseRead()?"R":"")+(details.getWarehouseUpdate()?"U":"")+(details.getWarehouseExport()?"E":"");
		this.coinPermissions=(details.getCoinCreate()?"C":"")+(details.getCoinRead()?"R":"")+(details.getCoinUpdate()?"U":"")+(details.getCoinExport()?"E":"");
		this.typeConceptKardexPermissions=(details.getTypeConceptKardexCreate()?"C":"")+(details.getTypeConceptKardexRead()?"R":"")+(details.getTypeConceptKardexUpdate()?"U":"")+(details.getTypeConceptKardexExport()?"E":"");
		this.conceptKardexPermissions=(details.getConceptKardexCreate()?"C":"")+(details.getConceptKardexRead()?"R":"")+(details.getConceptKardexUpdate()?"U":"")+(details.getConceptKardexExport()?"E":"");
		this.contractPermissions=(details.getContractCreate()?"C":"")+(details.getContractRead()?"R":"")+(details.getContractUpdate()?"U":"")+(details.getContractExport()?"E":"");
		this.currencyPermissions=(details.getCurrencyCreate()?"C":"")+(details.getCurrencyRead()?"R":"")+(details.getCurrencyUpdate()?"U":"")+(details.getCurrencyExport()?"E":"");
		this.exchangeRatePermissions=(details.getExchangeRateCreate()?"C":"")+(details.getExchangeRateRead()?"R":"")+(details.getExchangeRateUpdate()?"U":"")+(details.getExchangeRateExport()?"E":"");
		this.continentPermissions=(details.getContinentCreate()?"C":"")+(details.getContinentRead()?"R":"")+(details.getContinentUpdate()?"U":"")+(details.getContinentExport()?"E":"");
		this.regionPermissions=(details.getRegionCreate()?"C":"")+(details.getRegionRead()?"R":"")+(details.getRegionUpdate()?"U":"")+(details.getRegionExport()?"E":"");
		this.currencyCodePermissions=(details.getCurrencyCodeCreate()?"C":"")+(details.getCurrencyCodeRead()?"R":"")+(details.getCurrencyCodeUpdate()?"U":"")+(details.getCurrencyCodeExport()?"E":"");
		this.countryPermissions=(details.getCountryCreate()?"C":"")+(details.getCountryRead()?"R":"")+(details.getCountryUpdate()?"U":"")+(details.getCountryExport()?"E":"");


		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		
		return this;
	}

}