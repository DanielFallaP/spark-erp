package co.com.cybersoft.maintenance.tables.web.domain.physicalLocation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PhysicalLocationFilterInfo {
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

	private String namePhysicalLocation;


	public String getNamePhysicalLocation() {
		return namePhysicalLocation;	
	}
		
	public void setNamePhysicalLocation(String namePhysicalLocation) {
		this.namePhysicalLocation = namePhysicalLocation;	
	}

	private String description;


	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	private String descriptionEnglish;


	public String getDescriptionEnglish() {
		return descriptionEnglish;	
	}
		
	public void setDescriptionEnglish(String descriptionEnglish) {
		this.descriptionEnglish = descriptionEnglish;	
	}

	private String descriptionShort;


	public String getDescriptionShort() {
		return descriptionShort;	
	}
		
	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;	
	}

	private String physicalLocationArea;


	public String getPhysicalLocationArea() {
		return physicalLocationArea;	
	}
		
	public void setPhysicalLocationArea(String physicalLocationArea) {
		this.physicalLocationArea = physicalLocationArea;	
	}

	private String measurementUnit;


	public String getMeasurementUnit() {
		return measurementUnit;	
	}
		
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;	
	}

	private String capacityPhysicalLocation;


	public String getCapacityPhysicalLocation() {
		return capacityPhysicalLocation;	
	}
		
	public void setCapacityPhysicalLocation(String capacityPhysicalLocation) {
		this.capacityPhysicalLocation = capacityPhysicalLocation;	
	}

	private String statePhysicalLocation;


	public String getStatePhysicalLocation() {
		return statePhysicalLocation;	
	}
		
	public void setStatePhysicalLocation(String statePhysicalLocation) {
		this.statePhysicalLocation = statePhysicalLocation;	
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
	
	private List<PhysicalLocationFilterInfo> physicalLocationFilterList=new ArrayList<PhysicalLocationFilterInfo>();
	
	public List<PhysicalLocationFilterInfo> getPhysicalLocationFilterList() {
		return physicalLocationFilterList;
	}

	public void setPhysicalLocationFilterList(
			List<PhysicalLocationFilterInfo> physicalLocationFilterList) {
		this.physicalLocationFilterList = physicalLocationFilterList;
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
		for (PhysicalLocationFilterInfo fil : physicalLocationFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getNamePhysicalLocation()!=null && !fil.getNamePhysicalLocation().equals(""))ffilterAsText+=fil.getNamePhysicalLocation()+" ";
			if (fil.getDescription()!=null && !fil.getDescription().equals(""))ffilterAsText+=fil.getDescription()+" ";
			if (fil.getDescriptionEnglish()!=null && !fil.getDescriptionEnglish().equals(""))ffilterAsText+=fil.getDescriptionEnglish()+" ";
			if (fil.getDescriptionShort()!=null && !fil.getDescriptionShort().equals(""))ffilterAsText+=fil.getDescriptionShort()+" ";
			if (fil.getPhysicalLocationArea()!=null && !fil.getPhysicalLocationArea().equals(""))ffilterAsText+=fil.getPhysicalLocationArea()+" ";
			if (fil.getMeasurementUnit()!=null && !fil.getMeasurementUnit().equals(""))ffilterAsText+=fil.getMeasurementUnit()+" ";
			if (fil.getCapacityPhysicalLocation()!=null && !fil.getCapacityPhysicalLocation().equals(""))ffilterAsText+=fil.getCapacityPhysicalLocation()+" ";
			if (fil.getStatePhysicalLocation()!=null && !fil.getStatePhysicalLocation().equals(""))ffilterAsText+=fil.getStatePhysicalLocation()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (namePhysicalLocation!=null && !namePhysicalLocation.equals(""))ffilterAsText+=namePhysicalLocation+" ";if (description!=null && !description.equals(""))ffilterAsText+=description+" ";if (descriptionEnglish!=null && !descriptionEnglish.equals(""))ffilterAsText+=descriptionEnglish+" ";if (descriptionShort!=null && !descriptionShort.equals(""))ffilterAsText+=descriptionShort+" ";if (physicalLocationArea!=null && !physicalLocationArea.equals(""))ffilterAsText+=physicalLocationArea+" ";if (measurementUnit!=null && !measurementUnit.equals(""))ffilterAsText+=measurementUnit+" ";if (capacityPhysicalLocation!=null && !capacityPhysicalLocation.equals(""))ffilterAsText+=capacityPhysicalLocation+" ";if (statePhysicalLocation!=null && !statePhysicalLocation.equals(""))ffilterAsText+=statePhysicalLocation+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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