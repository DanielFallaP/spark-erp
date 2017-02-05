package co.com.cybersoft.maintenance.tables.web.domain.otherConcepts;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class OtherConceptsFilterInfo {
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

	private String nameOtherconcepts;


	public String getNameOtherconcepts() {
		return nameOtherconcepts;	
	}
		
	public void setNameOtherconcepts(String nameOtherconcepts) {
		this.nameOtherconcepts = nameOtherconcepts;	
	}

	private String unitValue;


	public String getUnitValue() {
		return unitValue;	
	}
		
	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;	
	}

	private String unitMeasure;


	public String getUnitMeasure() {
		return unitMeasure;	
	}
		
	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;	
	}

	private String typeWork;


	public String getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;	
	}

	private String informative;


	public String getInformative() {
		return informative;	
	}
		
	public void setInformative(String informative) {
		this.informative = informative;	
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
	
	private List<OtherConceptsFilterInfo> otherConceptsFilterList=new ArrayList<OtherConceptsFilterInfo>();
	
	public List<OtherConceptsFilterInfo> getOtherConceptsFilterList() {
		return otherConceptsFilterList;
	}

	public void setOtherConceptsFilterList(
			List<OtherConceptsFilterInfo> otherConceptsFilterList) {
		this.otherConceptsFilterList = otherConceptsFilterList;
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
		for (OtherConceptsFilterInfo fil : otherConceptsFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getNameOtherconcepts()!=null && !fil.getNameOtherconcepts().equals(""))ffilterAsText+=fil.getNameOtherconcepts()+" ";
			if (fil.getUnitValue()!=null && !fil.getUnitValue().equals(""))ffilterAsText+=fil.getUnitValue()+" ";
			if (fil.getUnitMeasure()!=null && !fil.getUnitMeasure().equals(""))ffilterAsText+=fil.getUnitMeasure()+" ";
			if (fil.getTypeWork()!=null && !fil.getTypeWork().equals(""))ffilterAsText+=fil.getTypeWork()+" ";
			if (fil.getInformative()!=null && !fil.getInformative().equals(""))ffilterAsText+=fil.getInformative()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (nameOtherconcepts!=null && !nameOtherconcepts.equals(""))ffilterAsText+=nameOtherconcepts+" ";if (unitValue!=null && !unitValue.equals(""))ffilterAsText+=unitValue+" ";if (unitMeasure!=null && !unitMeasure.equals(""))ffilterAsText+=unitMeasure+" ";if (typeWork!=null && !typeWork.equals(""))ffilterAsText+=typeWork+" ";if (informative!=null && !informative.equals(""))ffilterAsText+=informative+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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