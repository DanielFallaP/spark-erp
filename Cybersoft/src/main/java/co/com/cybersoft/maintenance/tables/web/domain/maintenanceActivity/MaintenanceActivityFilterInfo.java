package co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MaintenanceActivityFilterInfo {
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

	private String nameMaintenanceActivity;


	public String getNameMaintenanceActivity() {
		return nameMaintenanceActivity;	
	}
		
	public void setNameMaintenanceActivity(String nameMaintenanceActivity) {
		this.nameMaintenanceActivity = nameMaintenanceActivity;	
	}

	private String standardCost;


	public String getStandardCost() {
		return standardCost;	
	}
		
	public void setStandardCost(String standardCost) {
		this.standardCost = standardCost;	
	}

	private String standarDuration;


	public String getStandarDuration() {
		return standarDuration;	
	}
		
	public void setStandarDuration(String standarDuration) {
		this.standarDuration = standarDuration;	
	}

	private String durationUnitStandard;


	public String getDurationUnitStandard() {
		return durationUnitStandard;	
	}
		
	public void setDurationUnitStandard(String durationUnitStandard) {
		this.durationUnitStandard = durationUnitStandard;	
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
	
	private List<MaintenanceActivityFilterInfo> maintenanceActivityFilterList=new ArrayList<MaintenanceActivityFilterInfo>();
	
	public List<MaintenanceActivityFilterInfo> getMaintenanceActivityFilterList() {
		return maintenanceActivityFilterList;
	}

	public void setMaintenanceActivityFilterList(
			List<MaintenanceActivityFilterInfo> maintenanceActivityFilterList) {
		this.maintenanceActivityFilterList = maintenanceActivityFilterList;
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
		for (MaintenanceActivityFilterInfo fil : maintenanceActivityFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getNameMaintenanceActivity()!=null && !fil.getNameMaintenanceActivity().equals(""))ffilterAsText+=fil.getNameMaintenanceActivity()+" ";
			if (fil.getStandardCost()!=null && !fil.getStandardCost().equals(""))ffilterAsText+=fil.getStandardCost()+" ";
			if (fil.getStandarDuration()!=null && !fil.getStandarDuration().equals(""))ffilterAsText+=fil.getStandarDuration()+" ";
			if (fil.getDurationUnitStandard()!=null && !fil.getDurationUnitStandard().equals(""))ffilterAsText+=fil.getDurationUnitStandard()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (nameMaintenanceActivity!=null && !nameMaintenanceActivity.equals(""))ffilterAsText+=nameMaintenanceActivity+" ";if (standardCost!=null && !standardCost.equals(""))ffilterAsText+=standardCost+" ";if (standarDuration!=null && !standarDuration.equals(""))ffilterAsText+=standarDuration+" ";if (durationUnitStandard!=null && !durationUnitStandard.equals(""))ffilterAsText+=durationUnitStandard+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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