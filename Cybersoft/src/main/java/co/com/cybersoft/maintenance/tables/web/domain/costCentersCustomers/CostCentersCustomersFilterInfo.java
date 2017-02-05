package co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CostCentersCustomersFilterInfo {
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

	private String nameCostCenter;


	public String getNameCostCenter() {
		return nameCostCenter;	
	}
		
	public void setNameCostCenter(String nameCostCenter) {
		this.nameCostCenter = nameCostCenter;	
	}

	private String subCostCentersCustomers;


	public String getSubCostCentersCustomers() {
		return subCostCentersCustomers;	
	}
		
	public void setSubCostCentersCustomers(String subCostCentersCustomers) {
		this.subCostCentersCustomers = subCostCentersCustomers;	
	}

	private String subDescription;


	public String getSubDescription() {
		return subDescription;	
	}
		
	public void setSubDescription(String subDescription) {
		this.subDescription = subDescription;	
	}

	private String contact;


	public String getContact() {
		return contact;	
	}
		
	public void setContact(String contact) {
		this.contact = contact;	
	}

	private String areaDepartment;


	public String getAreaDepartment() {
		return areaDepartment;	
	}
		
	public void setAreaDepartment(String areaDepartment) {
		this.areaDepartment = areaDepartment;	
	}

	private String address;


	public String getAddress() {
		return address;	
	}
		
	public void setAddress(String address) {
		this.address = address;	
	}

	private String city;


	public String getCity() {
		return city;	
	}
		
	public void setCity(String city) {
		this.city = city;	
	}

	private String phone;


	public String getPhone() {
		return phone;	
	}
		
	public void setPhone(String phone) {
		this.phone = phone;	
	}

	private String classis;


	public String getClassis() {
		return classis;	
	}
		
	public void setClassis(String classis) {
		this.classis = classis;	
	}

	private String state;


	public String getState() {
		return state;	
	}
		
	public void setState(String state) {
		this.state = state;	
	}

	private String comments;


	public String getComments() {
		return comments;	
	}
		
	public void setComments(String comments) {
		this.comments = comments;	
	}

	private String stateCostCenter;


	public String getStateCostCenter() {
		return stateCostCenter;	
	}
		
	public void setStateCostCenter(String stateCostCenter) {
		this.stateCostCenter = stateCostCenter;	
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
	
	private List<CostCentersCustomersFilterInfo> costCentersCustomersFilterList=new ArrayList<CostCentersCustomersFilterInfo>();
	
	public List<CostCentersCustomersFilterInfo> getCostCentersCustomersFilterList() {
		return costCentersCustomersFilterList;
	}

	public void setCostCentersCustomersFilterList(
			List<CostCentersCustomersFilterInfo> costCentersCustomersFilterList) {
		this.costCentersCustomersFilterList = costCentersCustomersFilterList;
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
		for (CostCentersCustomersFilterInfo fil : costCentersCustomersFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getDescription()!=null && !fil.getDescription().equals(""))ffilterAsText+=fil.getDescription()+" ";
			if (fil.getNameCostCenter()!=null && !fil.getNameCostCenter().equals(""))ffilterAsText+=fil.getNameCostCenter()+" ";
			if (fil.getSubCostCentersCustomers()!=null && !fil.getSubCostCentersCustomers().equals(""))ffilterAsText+=fil.getSubCostCentersCustomers()+" ";
			if (fil.getSubDescription()!=null && !fil.getSubDescription().equals(""))ffilterAsText+=fil.getSubDescription()+" ";
			if (fil.getContact()!=null && !fil.getContact().equals(""))ffilterAsText+=fil.getContact()+" ";
			if (fil.getAreaDepartment()!=null && !fil.getAreaDepartment().equals(""))ffilterAsText+=fil.getAreaDepartment()+" ";
			if (fil.getAddress()!=null && !fil.getAddress().equals(""))ffilterAsText+=fil.getAddress()+" ";
			if (fil.getCity()!=null && !fil.getCity().equals(""))ffilterAsText+=fil.getCity()+" ";
			if (fil.getPhone()!=null && !fil.getPhone().equals(""))ffilterAsText+=fil.getPhone()+" ";
			if (fil.getClassis()!=null && !fil.getClassis().equals(""))ffilterAsText+=fil.getClassis()+" ";
			if (fil.getState()!=null && !fil.getState().equals(""))ffilterAsText+=fil.getState()+" ";
			if (fil.getComments()!=null && !fil.getComments().equals(""))ffilterAsText+=fil.getComments()+" ";
			if (fil.getStateCostCenter()!=null && !fil.getStateCostCenter().equals(""))ffilterAsText+=fil.getStateCostCenter()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (description!=null && !description.equals(""))ffilterAsText+=description+" ";if (nameCostCenter!=null && !nameCostCenter.equals(""))ffilterAsText+=nameCostCenter+" ";if (subCostCentersCustomers!=null && !subCostCentersCustomers.equals(""))ffilterAsText+=subCostCentersCustomers+" ";if (subDescription!=null && !subDescription.equals(""))ffilterAsText+=subDescription+" ";if (contact!=null && !contact.equals(""))ffilterAsText+=contact+" ";if (areaDepartment!=null && !areaDepartment.equals(""))ffilterAsText+=areaDepartment+" ";if (address!=null && !address.equals(""))ffilterAsText+=address+" ";if (city!=null && !city.equals(""))ffilterAsText+=city+" ";if (phone!=null && !phone.equals(""))ffilterAsText+=phone+" ";if (classis!=null && !classis.equals(""))ffilterAsText+=classis+" ";if (state!=null && !state.equals(""))ffilterAsText+=state+" ";if (comments!=null && !comments.equals(""))ffilterAsText+=comments+" ";if (stateCostCenter!=null && !stateCostCenter.equals(""))ffilterAsText+=stateCostCenter+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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