package co.com.cybersoft.maintenance.tables.web.domain.job;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class JobFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String responsibleCenter;


	public String getResponsibleCenter() {
		return responsibleCenter;	
	}
		
	public void setResponsibleCenter(String responsibleCenter) {
		this.responsibleCenter = responsibleCenter;	
	}

	private String nameJob;


	public String getNameJob() {
		return nameJob;	
	}
		
	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;	
	}

	private String valueTime1;


	public String getValueTime1() {
		return valueTime1;	
	}
		
	public void setValueTime1(String valueTime1) {
		this.valueTime1 = valueTime1;	
	}

	private String valueTime2;


	public String getValueTime2() {
		return valueTime2;	
	}
		
	public void setValueTime2(String valueTime2) {
		this.valueTime2 = valueTime2;	
	}

	private String valueTime3;


	public String getValueTime3() {
		return valueTime3;	
	}
		
	public void setValueTime3(String valueTime3) {
		this.valueTime3 = valueTime3;	
	}

	private String typeWork;


	public String getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;	
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
	
	private List<JobFilterInfo> jobFilterList=new ArrayList<JobFilterInfo>();
	
	public List<JobFilterInfo> getJobFilterList() {
		return jobFilterList;
	}

	public void setJobFilterList(
			List<JobFilterInfo> jobFilterList) {
		this.jobFilterList = jobFilterList;
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
		for (JobFilterInfo fil : jobFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getResponsibleCenter()!=null && !fil.getResponsibleCenter().equals(""))ffilterAsText+=fil.getResponsibleCenter()+" ";
			if (fil.getNameJob()!=null && !fil.getNameJob().equals(""))ffilterAsText+=fil.getNameJob()+" ";
			if (fil.getValueTime1()!=null && !fil.getValueTime1().equals(""))ffilterAsText+=fil.getValueTime1()+" ";
			if (fil.getValueTime2()!=null && !fil.getValueTime2().equals(""))ffilterAsText+=fil.getValueTime2()+" ";
			if (fil.getValueTime3()!=null && !fil.getValueTime3().equals(""))ffilterAsText+=fil.getValueTime3()+" ";
			if (fil.getTypeWork()!=null && !fil.getTypeWork().equals(""))ffilterAsText+=fil.getTypeWork()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (responsibleCenter!=null && !responsibleCenter.equals(""))ffilterAsText+=responsibleCenter+" ";if (nameJob!=null && !nameJob.equals(""))ffilterAsText+=nameJob+" ";if (valueTime1!=null && !valueTime1.equals(""))ffilterAsText+=valueTime1+" ";if (valueTime2!=null && !valueTime2.equals(""))ffilterAsText+=valueTime2+" ";if (valueTime3!=null && !valueTime3.equals(""))ffilterAsText+=valueTime3+" ";if (typeWork!=null && !typeWork.equals(""))ffilterAsText+=typeWork+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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