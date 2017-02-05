package co.com.cybersoft.maintenance.tables.web.domain.company;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Controller for company
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CompanyInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotNull
	private Integer code;


	@Length(max=12)
	@NotEmpty
	private String codeNit;


	@Length(max=30)
	@NotEmpty
	private String companyName;


	@Length(max=25)
	@NotEmpty
	private String companyLicense;


	@Length(max=4000)
	@NotEmpty
	private String comment;


	private Date homeRangesOfDatesOfTheLastGeneration;


	private Date endDateRangesOfTheLastGeneration;


	@Length(max=50)
	@NotEmpty
	private String userLoginCompany;


	private Boolean stateAnalysisCompany;


	private Boolean stateGenerationCompany;


	private Date startDateCompanyProcess;


	private Date endDateProcessCompany;


	@NotNull
	private Integer numberPartialWorkOrderCompany;


	@NotNull
	private Integer totalNumberofWorkOrderintheCompany;


	private Boolean cancelProcessCompany;


	@Length(max=50)
	@NotEmpty
	private String userSendHistory;


	@NotEmpty
	private String stateShippingHistory;


	private Date startDateSendingHistory;


	private Date endingDatelSendingHistory;


	@NotNull
	private Integer partialNumberOfSendingHistory;


	@NotNull
	private Integer totalNumberOfSendingHistory;


	private Boolean cancelSendingHistory;


	@Length(max=17)
	@NotEmpty
	private String stateCompany;


	@Length(max=3)
	@NotEmpty
	private String codeOfWIN;


	@Length(max=50)
	@NotEmpty
	private String inventoryConditionPermanent;


	@Length(max=50)
	@NotEmpty
	private String permanentInventoryWorkOrder;


	@NotNull
	private Integer language;


	@NotNull
	private Integer activeCompany;


	private Boolean active;


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