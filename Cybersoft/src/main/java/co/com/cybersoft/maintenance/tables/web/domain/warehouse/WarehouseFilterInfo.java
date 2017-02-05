package co.com.cybersoft.maintenance.tables.web.domain.warehouse;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class WarehouseFilterInfo {
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

	private String code;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	private String storeName;


	public String getStoreName() {
		return storeName;	
	}
		
	public void setStoreName(String storeName) {
		this.storeName = storeName;	
	}

	private String physicalLocation;


	public String getPhysicalLocation() {
		return physicalLocation;	
	}
		
	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;	
	}

	private String costingType;


	public String getCostingType() {
		return costingType;	
	}
		
	public void setCostingType(String costingType) {
		this.costingType = costingType;	
	}

	private String comment;


	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}

	private String storeType;


	public String getStoreType() {
		return storeType;	
	}
		
	public void setStoreType(String storeType) {
		this.storeType = storeType;	
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
	
	private List<WarehouseFilterInfo> warehouseFilterList=new ArrayList<WarehouseFilterInfo>();
	
	public List<WarehouseFilterInfo> getWarehouseFilterList() {
		return warehouseFilterList;
	}

	public void setWarehouseFilterList(
			List<WarehouseFilterInfo> warehouseFilterList) {
		this.warehouseFilterList = warehouseFilterList;
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
		for (WarehouseFilterInfo fil : warehouseFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getCode()!=null && !fil.getCode().equals(""))ffilterAsText+=fil.getCode()+" ";
			if (fil.getStoreName()!=null && !fil.getStoreName().equals(""))ffilterAsText+=fil.getStoreName()+" ";
			if (fil.getPhysicalLocation()!=null && !fil.getPhysicalLocation().equals(""))ffilterAsText+=fil.getPhysicalLocation()+" ";
			if (fil.getCostingType()!=null && !fil.getCostingType().equals(""))ffilterAsText+=fil.getCostingType()+" ";
			if (fil.getComment()!=null && !fil.getComment().equals(""))ffilterAsText+=fil.getComment()+" ";
			if (fil.getStoreType()!=null && !fil.getStoreType().equals(""))ffilterAsText+=fil.getStoreType()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (code!=null && !code.equals(""))ffilterAsText+=code+" ";if (storeName!=null && !storeName.equals(""))ffilterAsText+=storeName+" ";if (physicalLocation!=null && !physicalLocation.equals(""))ffilterAsText+=physicalLocation+" ";if (costingType!=null && !costingType.equals(""))ffilterAsText+=costingType+" ";if (comment!=null && !comment.equals(""))ffilterAsText+=comment+" ";if (storeType!=null && !storeType.equals(""))ffilterAsText+=storeType+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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