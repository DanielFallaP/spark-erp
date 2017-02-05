package co.com.cybersoft.maintenance.tables.web.domain.conceptKardex;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import co.com.cybersoft.util.CyberUtils;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ConceptKardexFilterInfo {
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

	private String store;


	public String getStore() {
		return store;	
	}
		
	public void setStore(String store) {
		this.store = store;	
	}

	private String numberConceptKardex;


	public String getNumberConceptKardex() {
		return numberConceptKardex;	
	}
		
	public void setNumberConceptKardex(String numberConceptKardex) {
		this.numberConceptKardex = numberConceptKardex;	
	}

	private String nameConceptKardex;


	public String getNameConceptKardex() {
		return nameConceptKardex;	
	}
		
	public void setNameConceptKardex(String nameConceptKardex) {
		this.nameConceptKardex = nameConceptKardex;	
	}

	private String typeConceptKardex;


	public String getTypeConceptKardex() {
		return typeConceptKardex;	
	}
		
	public void setTypeConceptKardex(String typeConceptKardex) {
		this.typeConceptKardex = typeConceptKardex;	
	}

	private String indicatorTransfers;


	public String getIndicatorTransfers() {
		return indicatorTransfers;	
	}
		
	public void setIndicatorTransfers(String indicatorTransfers) {
		this.indicatorTransfers = indicatorTransfers;	
	}

	private String workOrderConceptKardex;


	public String getWorkOrderConceptKardex() {
		return workOrderConceptKardex;	
	}
		
	public void setWorkOrderConceptKardex(String workOrderConceptKardex) {
		this.workOrderConceptKardex = workOrderConceptKardex;	
	}

	private String defaultIndicatorConcept;


	public String getDefaultIndicatorConcept() {
		return defaultIndicatorConcept;	
	}
		
	public void setDefaultIndicatorConcept(String defaultIndicatorConcept) {
		this.defaultIndicatorConcept = defaultIndicatorConcept;	
	}

	private String subtypeConceptKardex;


	public String getSubtypeConceptKardex() {
		return subtypeConceptKardex;	
	}
		
	public void setSubtypeConceptKardex(String subtypeConceptKardex) {
		this.subtypeConceptKardex = subtypeConceptKardex;	
	}

	private String deliveryConceptContractor;


	public String getDeliveryConceptContractor() {
		return deliveryConceptContractor;	
	}
		
	public void setDeliveryConceptContractor(String deliveryConceptContractor) {
		this.deliveryConceptContractor = deliveryConceptContractor;	
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
	
	private List<ConceptKardexFilterInfo> conceptKardexFilterList=new ArrayList<ConceptKardexFilterInfo>();
	
	public List<ConceptKardexFilterInfo> getConceptKardexFilterList() {
		return conceptKardexFilterList;
	}

	public void setConceptKardexFilterList(
			List<ConceptKardexFilterInfo> conceptKardexFilterList) {
		this.conceptKardexFilterList = conceptKardexFilterList;
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
		for (ConceptKardexFilterInfo fil : conceptKardexFilterList) {
			ffilterAsText+=(index!=0?(fil.getFffilterNature().equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
			if (fil.getCompany()!=null && !fil.getCompany().equals(""))ffilterAsText+=fil.getCompany()+" ";
			if (fil.getStore()!=null && !fil.getStore().equals(""))ffilterAsText+=fil.getStore()+" ";
			if (fil.getNumberConceptKardex()!=null && !fil.getNumberConceptKardex().equals(""))ffilterAsText+=fil.getNumberConceptKardex()+" ";
			if (fil.getNameConceptKardex()!=null && !fil.getNameConceptKardex().equals(""))ffilterAsText+=fil.getNameConceptKardex()+" ";
			if (fil.getTypeConceptKardex()!=null && !fil.getTypeConceptKardex().equals(""))ffilterAsText+=fil.getTypeConceptKardex()+" ";
			if (fil.getIndicatorTransfers()!=null && !fil.getIndicatorTransfers().equals(""))ffilterAsText+=fil.getIndicatorTransfers()+" ";
			if (fil.getWorkOrderConceptKardex()!=null && !fil.getWorkOrderConceptKardex().equals(""))ffilterAsText+=fil.getWorkOrderConceptKardex()+" ";
			if (fil.getDefaultIndicatorConcept()!=null && !fil.getDefaultIndicatorConcept().equals(""))ffilterAsText+=fil.getDefaultIndicatorConcept()+" ";
			if (fil.getSubtypeConceptKardex()!=null && !fil.getSubtypeConceptKardex().equals(""))ffilterAsText+=fil.getSubtypeConceptKardex()+" ";
			if (fil.getDeliveryConceptContractor()!=null && !fil.getDeliveryConceptContractor().equals(""))ffilterAsText+=fil.getDeliveryConceptContractor()+" ";
			if (fil.getActive()!=null && !fil.getActive().equals(""))ffilterAsText+=fil.getActive()+" ";

			if (fil.getDateOfModification()!=null && !fil.getDateOfModification().equals(""))ffilterAsText+=fil.getDateOfModification()+" ";
			if (fil.getUserName()!=null && !fil.getUserName().equals(""))ffilterAsText+=fil.getUserName()+" ";
			if (fil.getDateOfCreation()!=null && !fil.getDateOfCreation().equals(""))ffilterAsText+=fil.getDateOfCreation()+" ";
			if (fil.getCreatedBy()!=null && !fil.getCreatedBy().equals(""))ffilterAsText+=fil.getCreatedBy()+" ";
			
			index++;
		}
		
		ffilterAsText+=(index!=0?(fffilterNature.equals(CyberUtils.filterUnion)?" UNION ":" MINUS "):"");
		if (company!=null && !company.equals(""))ffilterAsText+=company+" ";if (store!=null && !store.equals(""))ffilterAsText+=store+" ";if (numberConceptKardex!=null && !numberConceptKardex.equals(""))ffilterAsText+=numberConceptKardex+" ";if (nameConceptKardex!=null && !nameConceptKardex.equals(""))ffilterAsText+=nameConceptKardex+" ";if (typeConceptKardex!=null && !typeConceptKardex.equals(""))ffilterAsText+=typeConceptKardex+" ";if (indicatorTransfers!=null && !indicatorTransfers.equals(""))ffilterAsText+=indicatorTransfers+" ";if (workOrderConceptKardex!=null && !workOrderConceptKardex.equals(""))ffilterAsText+=workOrderConceptKardex+" ";if (defaultIndicatorConcept!=null && !defaultIndicatorConcept.equals(""))ffilterAsText+=defaultIndicatorConcept+" ";if (subtypeConceptKardex!=null && !subtypeConceptKardex.equals(""))ffilterAsText+=subtypeConceptKardex+" ";if (deliveryConceptContractor!=null && !deliveryConceptContractor.equals(""))ffilterAsText+=deliveryConceptContractor+" ";if (active!=null && !active.equals(""))ffilterAsText+=active+" ";
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