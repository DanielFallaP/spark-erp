package co.com.cybersoft.maintenance.tables.web.domain.storeType;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class StoreTypeFilterInfo {
	private String userName;

	private String dateOfCreation;
	
	private String dateOfModification;
	
	private String createdBy;
	
	private String aaaaction;
	
	private Integer selectedFilterPage;
	
	private String selectedFilterField;
	
	private Boolean changeSortingFieldDirection;

	private String storeName;


	public String getStoreName() {
		return storeName;	
	}
		
	public void setStoreName(String storeName) {
		this.storeName = storeName;	
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
	
	private List<StoreTypeFilterInfo> storeTypeFilterList=new ArrayList<StoreTypeFilterInfo>();
	
	public List<StoreTypeFilterInfo> getStoreTypeFilterList() {
		return storeTypeFilterList;
	}

	public void setStoreTypeFilterList(
			List<StoreTypeFilterInfo> storeTypeFilterList) {
		this.storeTypeFilterList = storeTypeFilterList;
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
		for (StoreTypeFilterInfo fil : storeTypeFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getStoreName()!=null && !fil.getStoreName().equals(""))ffilterAsText+=fil.getStoreName()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (storeName!=null && !storeName.equals(""))ffilterAsText+=storeName+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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