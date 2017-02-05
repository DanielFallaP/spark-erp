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
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;

import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Entity
public class Sorter {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_ID" , nullable=false)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TYPESORTER_ID" , nullable=false)
	private TypeSorter typeSorter;

	private String nameSorter;

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
	public TypeSorter getTypeSorter() {
		return typeSorter;	
	}
		
	public void setTypeSorter(TypeSorter typeSorter) {
		this.typeSorter = typeSorter;	
	}
	public String getNameSorter() {
		return nameSorter;	
	}
		
	public void setNameSorter(String nameSorter) {
		this.nameSorter = nameSorter;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public Sorter fromSorterDetails(SorterDetails details){
		BeanUtils.copyProperties(details, this);
		

		Company company0=new Company();company0.setId(details.getCompanyId());this.company=company0; 
		TypeSorter typeSorter1=new TypeSorter();typeSorter1.setId(details.getTypeSorterId());this.typeSorter=typeSorter1; 
		
		return this;
	}

}