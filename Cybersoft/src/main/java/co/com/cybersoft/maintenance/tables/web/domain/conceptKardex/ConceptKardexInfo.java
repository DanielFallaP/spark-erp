package co.com.cybersoft.maintenance.tables.web.domain.conceptKardex;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for conceptKardex
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ConceptKardexInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	private Long storeId;


	private String store;


	private List<WarehouseDetails> storeList;
	@NotNull
	private Integer numberConceptKardex;


	@NotNull
	private Integer nameConceptKardex;


	private Long typeConceptKardexId;


	private String typeConceptKardex;


	private List<TypeConceptKardexDetails> typeConceptKardexList;
	private Boolean indicatorTransfers;


	@NotNull
	private Integer workOrderConceptKardex;


	private Boolean defaultIndicatorConcept;


	private Boolean subtypeConceptKardex;


	private Boolean deliveryConceptContractor;


	private Boolean active;


	public List<CompanyDetails> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(
				List<CompanyDetails> companyList) {
			this.companyList = companyList;
	}

	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}

	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}

	public List<WarehouseDetails> getStoreList() {
		return storeList;
	}
	public void setStoreList(
				List<WarehouseDetails> storeList) {
			this.storeList = storeList;
	}

	public String getStore() {
		return store;	
	}
		
	public void setStore(String store) {
		this.store = store;	
	}

	public Long getStoreId() {
		return storeId;	
	}
		
	public void setStoreId(Long storeId) {
		this.storeId = storeId;	
	}

	public Integer getNumberConceptKardex() {
		return numberConceptKardex;	
	}
		
	public void setNumberConceptKardex(Integer numberConceptKardex) {
		this.numberConceptKardex = numberConceptKardex;	
	}

	public Integer getNameConceptKardex() {
		return nameConceptKardex;	
	}
		
	public void setNameConceptKardex(Integer nameConceptKardex) {
		this.nameConceptKardex = nameConceptKardex;	
	}

	public List<TypeConceptKardexDetails> getTypeConceptKardexList() {
		return typeConceptKardexList;
	}
	public void setTypeConceptKardexList(
				List<TypeConceptKardexDetails> typeConceptKardexList) {
			this.typeConceptKardexList = typeConceptKardexList;
	}

	public String getTypeConceptKardex() {
		return typeConceptKardex;	
	}
		
	public void setTypeConceptKardex(String typeConceptKardex) {
		this.typeConceptKardex = typeConceptKardex;	
	}

	public Long getTypeConceptKardexId() {
		return typeConceptKardexId;	
	}
		
	public void setTypeConceptKardexId(Long typeConceptKardexId) {
		this.typeConceptKardexId = typeConceptKardexId;	
	}

	public Boolean getIndicatorTransfers() {
		return indicatorTransfers;	
	}
		
	public void setIndicatorTransfers(Boolean indicatorTransfers) {
		this.indicatorTransfers = indicatorTransfers;	
	}

	public Integer getWorkOrderConceptKardex() {
		return workOrderConceptKardex;	
	}
		
	public void setWorkOrderConceptKardex(Integer workOrderConceptKardex) {
		this.workOrderConceptKardex = workOrderConceptKardex;	
	}

	public Boolean getDefaultIndicatorConcept() {
		return defaultIndicatorConcept;	
	}
		
	public void setDefaultIndicatorConcept(Boolean defaultIndicatorConcept) {
		this.defaultIndicatorConcept = defaultIndicatorConcept;	
	}

	public Boolean getSubtypeConceptKardex() {
		return subtypeConceptKardex;	
	}
		
	public void setSubtypeConceptKardex(Boolean subtypeConceptKardex) {
		this.subtypeConceptKardex = subtypeConceptKardex;	
	}

	public Boolean getDeliveryConceptContractor() {
		return deliveryConceptContractor;	
	}
		
	public void setDeliveryConceptContractor(Boolean deliveryConceptContractor) {
		this.deliveryConceptContractor = deliveryConceptContractor;	
	}

	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}


	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}
	
}