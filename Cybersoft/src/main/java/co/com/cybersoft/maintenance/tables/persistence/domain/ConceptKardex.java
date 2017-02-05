package co.com.cybersoft.maintenance.tables.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;

import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class ConceptKardex {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STORE_ID" , nullable=false)
	private Warehouse store;

	private Integer numberConceptKardex;

	private Integer nameConceptKardex;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TYPECONCEPTKARDEX_ID" , nullable=false)
	private TypeConceptKardex typeConceptKardex;

	private Boolean indicatorTransfers;

	private Integer workOrderConceptKardex;

	private Boolean defaultIndicatorConcept;

	private Boolean subtypeConceptKardex;

	private Boolean deliveryConceptContractor;

	private Boolean active;


	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
	public Date getDateOfModification() {
		return dateOfModification;
	}
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
	
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Company getCompany() {
		return company;	
	}
		
	public void setCompany(Company company) {
		this.company = company;	
	}
	public Warehouse getStore() {
		return store;	
	}
		
	public void setStore(Warehouse store) {
		this.store = store;	
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
	public TypeConceptKardex getTypeConceptKardex() {
		return typeConceptKardex;	
	}
		
	public void setTypeConceptKardex(TypeConceptKardex typeConceptKardex) {
		this.typeConceptKardex = typeConceptKardex;	
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

	
	public ConceptKardex fromConceptKardexDetails(ConceptKardexDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		Warehouse warehouse1=new Warehouse();warehouse1.setId(details.getStoreId());this.store=warehouse1; 
		TypeConceptKardex typeConceptKardex2=new TypeConceptKardex();typeConceptKardex2.setId(details.getTypeConceptKardexId());this.typeConceptKardex=typeConceptKardex2; 
		
		return this;
	}

}