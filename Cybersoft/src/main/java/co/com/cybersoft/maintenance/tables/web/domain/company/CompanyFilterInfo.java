package co.com.cybersoft.maintenance.tables.web.domain.company;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CompanyFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String code;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	private String codeNit;


	public String getCodeNit() {
		return codeNit;	
	}
		
	public void setCodeNit(String codeNit) {
		this.codeNit = codeNit;	
	}

	private String companyName;


	public String getCompanyName() {
		return companyName;	
	}
		
	public void setCompanyName(String companyName) {
		this.companyName = companyName;	
	}

	private String companyLicense;


	public String getCompanyLicense() {
		return companyLicense;	
	}
		
	public void setCompanyLicense(String companyLicense) {
		this.companyLicense = companyLicense;	
	}

	private String comment;


	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}

	private String homeRangesOfDatesOfTheLastGeneration;


	public String getHomeRangesOfDatesOfTheLastGeneration() {
		return homeRangesOfDatesOfTheLastGeneration;	
	}
		
	public void setHomeRangesOfDatesOfTheLastGeneration(String homeRangesOfDatesOfTheLastGeneration) {
		this.homeRangesOfDatesOfTheLastGeneration = homeRangesOfDatesOfTheLastGeneration;	
	}

	private String endDateRangesOfTheLastGeneration;


	public String getEndDateRangesOfTheLastGeneration() {
		return endDateRangesOfTheLastGeneration;	
	}
		
	public void setEndDateRangesOfTheLastGeneration(String endDateRangesOfTheLastGeneration) {
		this.endDateRangesOfTheLastGeneration = endDateRangesOfTheLastGeneration;	
	}

	private String userLoginCompany;


	public String getUserLoginCompany() {
		return userLoginCompany;	
	}
		
	public void setUserLoginCompany(String userLoginCompany) {
		this.userLoginCompany = userLoginCompany;	
	}

	private String stateAnalysisCompany;


	public String getStateAnalysisCompany() {
		return stateAnalysisCompany;	
	}
		
	public void setStateAnalysisCompany(String stateAnalysisCompany) {
		this.stateAnalysisCompany = stateAnalysisCompany;	
	}

	private String stateGenerationCompany;


	public String getStateGenerationCompany() {
		return stateGenerationCompany;	
	}
		
	public void setStateGenerationCompany(String stateGenerationCompany) {
		this.stateGenerationCompany = stateGenerationCompany;	
	}

	private String startDateCompanyProcess;


	public String getStartDateCompanyProcess() {
		return startDateCompanyProcess;	
	}
		
	public void setStartDateCompanyProcess(String startDateCompanyProcess) {
		this.startDateCompanyProcess = startDateCompanyProcess;	
	}

	private String endDateProcessCompany;


	public String getEndDateProcessCompany() {
		return endDateProcessCompany;	
	}
		
	public void setEndDateProcessCompany(String endDateProcessCompany) {
		this.endDateProcessCompany = endDateProcessCompany;	
	}

	private String numberPartialWorkOrderCompany;


	public String getNumberPartialWorkOrderCompany() {
		return numberPartialWorkOrderCompany;	
	}
		
	public void setNumberPartialWorkOrderCompany(String numberPartialWorkOrderCompany) {
		this.numberPartialWorkOrderCompany = numberPartialWorkOrderCompany;	
	}

	private String totalNumberofWorkOrderintheCompany;


	public String getTotalNumberofWorkOrderintheCompany() {
		return totalNumberofWorkOrderintheCompany;	
	}
		
	public void setTotalNumberofWorkOrderintheCompany(String totalNumberofWorkOrderintheCompany) {
		this.totalNumberofWorkOrderintheCompany = totalNumberofWorkOrderintheCompany;	
	}

	private String cancelProcessCompany;


	public String getCancelProcessCompany() {
		return cancelProcessCompany;	
	}
		
	public void setCancelProcessCompany(String cancelProcessCompany) {
		this.cancelProcessCompany = cancelProcessCompany;	
	}

	private String userSendHistory;


	public String getUserSendHistory() {
		return userSendHistory;	
	}
		
	public void setUserSendHistory(String userSendHistory) {
		this.userSendHistory = userSendHistory;	
	}

	private String stateShippingHistory;


	public String getStateShippingHistory() {
		return stateShippingHistory;	
	}
		
	public void setStateShippingHistory(String stateShippingHistory) {
		this.stateShippingHistory = stateShippingHistory;	
	}

	private String startDateSendingHistory;


	public String getStartDateSendingHistory() {
		return startDateSendingHistory;	
	}
		
	public void setStartDateSendingHistory(String startDateSendingHistory) {
		this.startDateSendingHistory = startDateSendingHistory;	
	}

	private String endingDatelSendingHistory;


	public String getEndingDatelSendingHistory() {
		return endingDatelSendingHistory;	
	}
		
	public void setEndingDatelSendingHistory(String endingDatelSendingHistory) {
		this.endingDatelSendingHistory = endingDatelSendingHistory;	
	}

	private String partialNumberOfSendingHistory;


	public String getPartialNumberOfSendingHistory() {
		return partialNumberOfSendingHistory;	
	}
		
	public void setPartialNumberOfSendingHistory(String partialNumberOfSendingHistory) {
		this.partialNumberOfSendingHistory = partialNumberOfSendingHistory;	
	}

	private String totalNumberOfSendingHistory;


	public String getTotalNumberOfSendingHistory() {
		return totalNumberOfSendingHistory;	
	}
		
	public void setTotalNumberOfSendingHistory(String totalNumberOfSendingHistory) {
		this.totalNumberOfSendingHistory = totalNumberOfSendingHistory;	
	}

	private String cancelSendingHistory;


	public String getCancelSendingHistory() {
		return cancelSendingHistory;	
	}
		
	public void setCancelSendingHistory(String cancelSendingHistory) {
		this.cancelSendingHistory = cancelSendingHistory;	
	}

	private String stateCompany;


	public String getStateCompany() {
		return stateCompany;	
	}
		
	public void setStateCompany(String stateCompany) {
		this.stateCompany = stateCompany;	
	}

	private String codeOfWIN;


	public String getCodeOfWIN() {
		return codeOfWIN;	
	}
		
	public void setCodeOfWIN(String codeOfWIN) {
		this.codeOfWIN = codeOfWIN;	
	}

	private String inventoryConditionPermanent;


	public String getInventoryConditionPermanent() {
		return inventoryConditionPermanent;	
	}
		
	public void setInventoryConditionPermanent(String inventoryConditionPermanent) {
		this.inventoryConditionPermanent = inventoryConditionPermanent;	
	}

	private String permanentInventoryWorkOrder;


	public String getPermanentInventoryWorkOrder() {
		return permanentInventoryWorkOrder;	
	}
		
	public void setPermanentInventoryWorkOrder(String permanentInventoryWorkOrder) {
		this.permanentInventoryWorkOrder = permanentInventoryWorkOrder;	
	}

	private String language;


	public String getLanguage() {
		return language;	
	}
		
	public void setLanguage(String language) {
		this.language = language;	
	}

	private String activeCompany;


	public String getActiveCompany() {
		return activeCompany;	
	}
		
	public void setActiveCompany(String activeCompany) {
		this.activeCompany = activeCompany;	
	}

	private String active;


	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
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
	
	private List<CompanyFilterInfo> companyFilterList=new ArrayList<CompanyFilterInfo>();
	
	public List<CompanyFilterInfo> getCompanyFilterList() {
		return companyFilterList;
	}

	public void setCompanyFilterList(
			List<CompanyFilterInfo> companyFilterList) {
		this.companyFilterList = companyFilterList;
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
		for (CompanyFilterInfo fil : companyFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCode()!=null && !fil.getCode().equals(""))ffilterAsText+=fil.getCode()+" ";
			if (fil.getCodeNit()!=null && !fil.getCodeNit().equals(""))ffilterAsText+=fil.getCodeNit()+" ";
			if (fil.getCompanyName()!=null && !fil.getCompanyName().equals(""))ffilterAsText+=fil.getCompanyName()+" ";
			if (fil.getCompanyLicense()!=null && !fil.getCompanyLicense().equals(""))ffilterAsText+=fil.getCompanyLicense()+" ";
			if (fil.getComment()!=null && !fil.getComment().equals(""))ffilterAsText+=fil.getComment()+" ";
			if (fil.getHomeRangesOfDatesOfTheLastGeneration()!=null && !fil.getHomeRangesOfDatesOfTheLastGeneration().equals(""))ffilterAsText+=fil.getHomeRangesOfDatesOfTheLastGeneration()+" ";
			if (fil.getEndDateRangesOfTheLastGeneration()!=null && !fil.getEndDateRangesOfTheLastGeneration().equals(""))ffilterAsText+=fil.getEndDateRangesOfTheLastGeneration()+" ";
			if (fil.getUserLoginCompany()!=null && !fil.getUserLoginCompany().equals(""))ffilterAsText+=fil.getUserLoginCompany()+" ";
			if (fil.getStateAnalysisCompany()!=null && !fil.getStateAnalysisCompany().equals(""))ffilterAsText+=fil.getStateAnalysisCompany()+" ";
			if (fil.getStateGenerationCompany()!=null && !fil.getStateGenerationCompany().equals(""))ffilterAsText+=fil.getStateGenerationCompany()+" ";
			if (fil.getStartDateCompanyProcess()!=null && !fil.getStartDateCompanyProcess().equals(""))ffilterAsText+=fil.getStartDateCompanyProcess()+" ";
			if (fil.getEndDateProcessCompany()!=null && !fil.getEndDateProcessCompany().equals(""))ffilterAsText+=fil.getEndDateProcessCompany()+" ";
			if (fil.getNumberPartialWorkOrderCompany()!=null && !fil.getNumberPartialWorkOrderCompany().equals(""))ffilterAsText+=fil.getNumberPartialWorkOrderCompany()+" ";
			if (fil.getTotalNumberofWorkOrderintheCompany()!=null && !fil.getTotalNumberofWorkOrderintheCompany().equals(""))ffilterAsText+=fil.getTotalNumberofWorkOrderintheCompany()+" ";
			if (fil.getCancelProcessCompany()!=null && !fil.getCancelProcessCompany().equals(""))ffilterAsText+=fil.getCancelProcessCompany()+" ";
			if (fil.getUserSendHistory()!=null && !fil.getUserSendHistory().equals(""))ffilterAsText+=fil.getUserSendHistory()+" ";
			if (fil.getStateShippingHistory()!=null && !fil.getStateShippingHistory().equals(""))ffilterAsText+=fil.getStateShippingHistory()+" ";
			if (fil.getStartDateSendingHistory()!=null && !fil.getStartDateSendingHistory().equals(""))ffilterAsText+=fil.getStartDateSendingHistory()+" ";
			if (fil.getEndingDatelSendingHistory()!=null && !fil.getEndingDatelSendingHistory().equals(""))ffilterAsText+=fil.getEndingDatelSendingHistory()+" ";
			if (fil.getPartialNumberOfSendingHistory()!=null && !fil.getPartialNumberOfSendingHistory().equals(""))ffilterAsText+=fil.getPartialNumberOfSendingHistory()+" ";
			if (fil.getTotalNumberOfSendingHistory()!=null && !fil.getTotalNumberOfSendingHistory().equals(""))ffilterAsText+=fil.getTotalNumberOfSendingHistory()+" ";
			if (fil.getCancelSendingHistory()!=null && !fil.getCancelSendingHistory().equals(""))ffilterAsText+=fil.getCancelSendingHistory()+" ";
			if (fil.getStateCompany()!=null && !fil.getStateCompany().equals(""))ffilterAsText+=fil.getStateCompany()+" ";
			if (fil.getCodeOfWIN()!=null && !fil.getCodeOfWIN().equals(""))ffilterAsText+=fil.getCodeOfWIN()+" ";
			if (fil.getInventoryConditionPermanent()!=null && !fil.getInventoryConditionPermanent().equals(""))ffilterAsText+=fil.getInventoryConditionPermanent()+" ";
			if (fil.getPermanentInventoryWorkOrder()!=null && !fil.getPermanentInventoryWorkOrder().equals(""))ffilterAsText+=fil.getPermanentInventoryWorkOrder()+" ";
			if (fil.getLanguage()!=null && !fil.getLanguage().equals(""))ffilterAsText+=fil.getLanguage()+" ";
			if (fil.getActiveCompany()!=null && !fil.getActiveCompany().equals(""))ffilterAsText+=fil.getActiveCompany()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (code!=null && !code.equals(""))ffilterAsText+=code+" ";if (codeNit!=null && !codeNit.equals(""))ffilterAsText+=codeNit+" ";if (companyName!=null && !companyName.equals(""))ffilterAsText+=companyName+" ";if (companyLicense!=null && !companyLicense.equals(""))ffilterAsText+=companyLicense+" ";if (comment!=null && !comment.equals(""))ffilterAsText+=comment+" ";if (homeRangesOfDatesOfTheLastGeneration!=null && !homeRangesOfDatesOfTheLastGeneration.equals(""))ffilterAsText+=homeRangesOfDatesOfTheLastGeneration+" ";if (endDateRangesOfTheLastGeneration!=null && !endDateRangesOfTheLastGeneration.equals(""))ffilterAsText+=endDateRangesOfTheLastGeneration+" ";if (userLoginCompany!=null && !userLoginCompany.equals(""))ffilterAsText+=userLoginCompany+" ";if (stateAnalysisCompany!=null && !stateAnalysisCompany.equals(""))ffilterAsText+=stateAnalysisCompany+" ";if (stateGenerationCompany!=null && !stateGenerationCompany.equals(""))ffilterAsText+=stateGenerationCompany+" ";if (startDateCompanyProcess!=null && !startDateCompanyProcess.equals(""))ffilterAsText+=startDateCompanyProcess+" ";if (endDateProcessCompany!=null && !endDateProcessCompany.equals(""))ffilterAsText+=endDateProcessCompany+" ";if (numberPartialWorkOrderCompany!=null && !numberPartialWorkOrderCompany.equals(""))ffilterAsText+=numberPartialWorkOrderCompany+" ";if (totalNumberofWorkOrderintheCompany!=null && !totalNumberofWorkOrderintheCompany.equals(""))ffilterAsText+=totalNumberofWorkOrderintheCompany+" ";if (cancelProcessCompany!=null && !cancelProcessCompany.equals(""))ffilterAsText+=cancelProcessCompany+" ";if (userSendHistory!=null && !userSendHistory.equals(""))ffilterAsText+=userSendHistory+" ";if (stateShippingHistory!=null && !stateShippingHistory.equals(""))ffilterAsText+=stateShippingHistory+" ";if (startDateSendingHistory!=null && !startDateSendingHistory.equals(""))ffilterAsText+=startDateSendingHistory+" ";if (endingDatelSendingHistory!=null && !endingDatelSendingHistory.equals(""))ffilterAsText+=endingDatelSendingHistory+" ";if (partialNumberOfSendingHistory!=null && !partialNumberOfSendingHistory.equals(""))ffilterAsText+=partialNumberOfSendingHistory+" ";if (totalNumberOfSendingHistory!=null && !totalNumberOfSendingHistory.equals(""))ffilterAsText+=totalNumberOfSendingHistory+" ";if (cancelSendingHistory!=null && !cancelSendingHistory.equals(""))ffilterAsText+=cancelSendingHistory+" ";if (stateCompany!=null && !stateCompany.equals(""))ffilterAsText+=stateCompany+" ";if (codeOfWIN!=null && !codeOfWIN.equals(""))ffilterAsText+=codeOfWIN+" ";if (inventoryConditionPermanent!=null && !inventoryConditionPermanent.equals(""))ffilterAsText+=inventoryConditionPermanent+" ";if (permanentInventoryWorkOrder!=null && !permanentInventoryWorkOrder.equals(""))ffilterAsText+=permanentInventoryWorkOrder+" ";if (language!=null && !language.equals(""))ffilterAsText+=language+" ";if (activeCompany!=null && !activeCompany.equals(""))ffilterAsText+=activeCompany+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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