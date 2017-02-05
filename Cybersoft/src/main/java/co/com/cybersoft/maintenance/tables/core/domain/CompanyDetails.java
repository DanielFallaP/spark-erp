package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CompanyDetails {
	
	private Integer code;


	private String codeNit;


	private String companyName;


	private String companyLicense;


	private String comment;


	private Date homeRangesOfDatesOfTheLastGeneration;


	private Date endDateRangesOfTheLastGeneration;


	private String userLoginCompany;


	private Boolean stateAnalysisCompany;


	private Boolean stateGenerationCompany;


	private Date startDateCompanyProcess;


	private Date endDateProcessCompany;


	private Integer numberPartialWorkOrderCompany;


	private Integer totalNumberofWorkOrderintheCompany;


	private Boolean cancelProcessCompany;


	private String userSendHistory;


	private String stateShippingHistory;


	private Date startDateSendingHistory;


	private Date endingDatelSendingHistory;


	private Integer partialNumberOfSendingHistory;


	private Integer totalNumberOfSendingHistory;


	private Boolean cancelSendingHistory;


	private String stateCompany;


	private String codeOfWIN;


	private String inventoryConditionPermanent;


	private String permanentInventoryWorkOrder;


	private Integer language;


	private Integer activeCompany;


	private Boolean active;


		
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
	
	public Integer getCode() {
		return code;	
	}
		
	public void setCode(Integer code) {
		this.code = code;	
	}
	public String getCodeNit() {
		return codeNit;	
	}
		
	public void setCodeNit(String codeNit) {
		this.codeNit = codeNit;	
	}
	public String getCompanyName() {
		return companyName;	
	}
		
	public void setCompanyName(String companyName) {
		this.companyName = companyName;	
	}
	public String getCompanyLicense() {
		return companyLicense;	
	}
		
	public void setCompanyLicense(String companyLicense) {
		this.companyLicense = companyLicense;	
	}
	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}
	public Date getHomeRangesOfDatesOfTheLastGeneration() {
		return homeRangesOfDatesOfTheLastGeneration;	
	}
		
	public void setHomeRangesOfDatesOfTheLastGeneration(Date homeRangesOfDatesOfTheLastGeneration) {
		this.homeRangesOfDatesOfTheLastGeneration = homeRangesOfDatesOfTheLastGeneration;	
	}
	public Date getEndDateRangesOfTheLastGeneration() {
		return endDateRangesOfTheLastGeneration;	
	}
		
	public void setEndDateRangesOfTheLastGeneration(Date endDateRangesOfTheLastGeneration) {
		this.endDateRangesOfTheLastGeneration = endDateRangesOfTheLastGeneration;	
	}
	public String getUserLoginCompany() {
		return userLoginCompany;	
	}
		
	public void setUserLoginCompany(String userLoginCompany) {
		this.userLoginCompany = userLoginCompany;	
	}
	public Boolean getStateAnalysisCompany() {
		return stateAnalysisCompany;	
	}
		
	public void setStateAnalysisCompany(Boolean stateAnalysisCompany) {
		this.stateAnalysisCompany = stateAnalysisCompany;	
	}
	public Boolean getStateGenerationCompany() {
		return stateGenerationCompany;	
	}
		
	public void setStateGenerationCompany(Boolean stateGenerationCompany) {
		this.stateGenerationCompany = stateGenerationCompany;	
	}
	public Date getStartDateCompanyProcess() {
		return startDateCompanyProcess;	
	}
		
	public void setStartDateCompanyProcess(Date startDateCompanyProcess) {
		this.startDateCompanyProcess = startDateCompanyProcess;	
	}
	public Date getEndDateProcessCompany() {
		return endDateProcessCompany;	
	}
		
	public void setEndDateProcessCompany(Date endDateProcessCompany) {
		this.endDateProcessCompany = endDateProcessCompany;	
	}
	public Integer getNumberPartialWorkOrderCompany() {
		return numberPartialWorkOrderCompany;	
	}
		
	public void setNumberPartialWorkOrderCompany(Integer numberPartialWorkOrderCompany) {
		this.numberPartialWorkOrderCompany = numberPartialWorkOrderCompany;	
	}
	public Integer getTotalNumberofWorkOrderintheCompany() {
		return totalNumberofWorkOrderintheCompany;	
	}
		
	public void setTotalNumberofWorkOrderintheCompany(Integer totalNumberofWorkOrderintheCompany) {
		this.totalNumberofWorkOrderintheCompany = totalNumberofWorkOrderintheCompany;	
	}
	public Boolean getCancelProcessCompany() {
		return cancelProcessCompany;	
	}
		
	public void setCancelProcessCompany(Boolean cancelProcessCompany) {
		this.cancelProcessCompany = cancelProcessCompany;	
	}
	public String getUserSendHistory() {
		return userSendHistory;	
	}
		
	public void setUserSendHistory(String userSendHistory) {
		this.userSendHistory = userSendHistory;	
	}
	public String getStateShippingHistory() {
		return stateShippingHistory;	
	}
		
	public void setStateShippingHistory(String stateShippingHistory) {
		this.stateShippingHistory = stateShippingHistory;	
	}
	public Date getStartDateSendingHistory() {
		return startDateSendingHistory;	
	}
		
	public void setStartDateSendingHistory(Date startDateSendingHistory) {
		this.startDateSendingHistory = startDateSendingHistory;	
	}
	public Date getEndingDatelSendingHistory() {
		return endingDatelSendingHistory;	
	}
		
	public void setEndingDatelSendingHistory(Date endingDatelSendingHistory) {
		this.endingDatelSendingHistory = endingDatelSendingHistory;	
	}
	public Integer getPartialNumberOfSendingHistory() {
		return partialNumberOfSendingHistory;	
	}
		
	public void setPartialNumberOfSendingHistory(Integer partialNumberOfSendingHistory) {
		this.partialNumberOfSendingHistory = partialNumberOfSendingHistory;	
	}
	public Integer getTotalNumberOfSendingHistory() {
		return totalNumberOfSendingHistory;	
	}
		
	public void setTotalNumberOfSendingHistory(Integer totalNumberOfSendingHistory) {
		this.totalNumberOfSendingHistory = totalNumberOfSendingHistory;	
	}
	public Boolean getCancelSendingHistory() {
		return cancelSendingHistory;	
	}
		
	public void setCancelSendingHistory(Boolean cancelSendingHistory) {
		this.cancelSendingHistory = cancelSendingHistory;	
	}
	public String getStateCompany() {
		return stateCompany;	
	}
		
	public void setStateCompany(String stateCompany) {
		this.stateCompany = stateCompany;	
	}
	public String getCodeOfWIN() {
		return codeOfWIN;	
	}
		
	public void setCodeOfWIN(String codeOfWIN) {
		this.codeOfWIN = codeOfWIN;	
	}
	public String getInventoryConditionPermanent() {
		return inventoryConditionPermanent;	
	}
		
	public void setInventoryConditionPermanent(String inventoryConditionPermanent) {
		this.inventoryConditionPermanent = inventoryConditionPermanent;	
	}
	public String getPermanentInventoryWorkOrder() {
		return permanentInventoryWorkOrder;	
	}
		
	public void setPermanentInventoryWorkOrder(String permanentInventoryWorkOrder) {
		this.permanentInventoryWorkOrder = permanentInventoryWorkOrder;	
	}
	public Integer getLanguage() {
		return language;	
	}
		
	public void setLanguage(Integer language) {
		this.language = language;	
	}
	public Integer getActiveCompany() {
		return activeCompany;	
	}
		
	public void setActiveCompany(Integer activeCompany) {
		this.activeCompany = activeCompany;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public CompanyDetails toCompanyDetails(Company entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = CompanyDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.codeNit=codeNit+_embedded;
		this.companyName=companyName+_embedded;
		this.companyLicense=companyLicense+_embedded;
		this.comment=comment+_embedded;
		this.userLoginCompany=userLoginCompany+_embedded;
		this.userSendHistory=userSendHistory+_embedded;
		this.stateShippingHistory=stateShippingHistory+_embedded;
		this.stateCompany=stateCompany+_embedded;
		this.codeOfWIN=codeOfWIN+_embedded;
		this.inventoryConditionPermanent=inventoryConditionPermanent+_embedded;
		this.permanentInventoryWorkOrder=permanentInventoryWorkOrder+_embedded;

		return this;
	}
}