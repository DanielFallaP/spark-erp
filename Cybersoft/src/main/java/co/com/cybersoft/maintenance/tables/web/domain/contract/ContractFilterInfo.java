package co.com.cybersoft.maintenance.tables.web.domain.contract;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ContractFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String company;


	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	private String description;


	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	private String contractStartDate;


	public String getContractStartDate() {
		return contractStartDate;	
	}
		
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;	
	}

	private String contractEndDate;


	public String getContractEndDate() {
		return contractEndDate;	
	}
		
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;	
	}

	private String yearContractValue;


	public String getYearContractValue() {
		return yearContractValue;	
	}
		
	public void setYearContractValue(String yearContractValue) {
		this.yearContractValue = yearContractValue;	
	}

	private String responsible;


	public String getResponsible() {
		return responsible;	
	}
		
	public void setResponsible(String responsible) {
		this.responsible = responsible;	
	}

	private String costCenter;


	public String getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;	
	}

	private String stateContract;


	public String getStateContract() {
		return stateContract;	
	}
		
	public void setStateContract(String stateContract) {
		this.stateContract = stateContract;	
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
	
	private List<ContractFilterInfo> contractFilterList=new ArrayList<ContractFilterInfo>();
	
	public List<ContractFilterInfo> getContractFilterList() {
		return contractFilterList;
	}

	public void setContractFilterList(
			List<ContractFilterInfo> contractFilterList) {
		this.contractFilterList = contractFilterList;
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
		for (ContractFilterInfo fil : contractFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getDescription()!=null && !fil.getDescription().equals(""))ffilterAsText+=fil.getDescription()+" ";
			if (fil.getContractStartDate()!=null && !fil.getContractStartDate().equals(""))ffilterAsText+=fil.getContractStartDate()+" ";
			if (fil.getContractEndDate()!=null && !fil.getContractEndDate().equals(""))ffilterAsText+=fil.getContractEndDate()+" ";
			if (fil.getYearContractValue()!=null && !fil.getYearContractValue().equals(""))ffilterAsText+=fil.getYearContractValue()+" ";
			if (fil.getResponsible()!=null && !fil.getResponsible().equals(""))ffilterAsText+=fil.getResponsible()+" ";
			if (fil.getCostCenter()!=null && !fil.getCostCenter().equals(""))ffilterAsText+=fil.getCostCenter()+" ";
			if (fil.getStateContract()!=null && !fil.getStateContract().equals(""))ffilterAsText+=fil.getStateContract()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (description!=null && !description.equals(""))ffilterAsText+=description+" ";if (contractStartDate!=null && !contractStartDate.equals(""))ffilterAsText+=contractStartDate+" ";if (contractEndDate!=null && !contractEndDate.equals(""))ffilterAsText+=contractEndDate+" ";if (yearContractValue!=null && !yearContractValue.equals(""))ffilterAsText+=yearContractValue+" ";if (responsible!=null && !responsible.equals(""))ffilterAsText+=responsible+" ";if (costCenter!=null && !costCenter.equals(""))ffilterAsText+=costCenter+" ";if (stateContract!=null && !stateContract.equals(""))ffilterAsText+=stateContract+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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