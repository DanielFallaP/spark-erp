package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class SorterDetails {
	
	private Long companyId;


	private String company;


	private Long typeSorterId;


	private String typeSorter;


	private String nameSorter;


	private Boolean active;


		
	private Date dateOfModification;
	
	private Long id;
	
	private String userName;
	
	private Date dateOfCreation;
	
	private String createdBy;
	
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Long getCompanyId() {
		return companyId;	
	}
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;	
	}
	public String getCompany() {
		return company;	
	}
		
	public void setCompany(String company) {
		this.company = company;	
	}
	public Long getTypeSorterId() {
		return typeSorterId;	
	}
		
	public void setTypeSorterId(Long typeSorterId) {
		this.typeSorterId = typeSorterId;	
	}
	public String getTypeSorter() {
		return typeSorter;	
	}
		
	public void setTypeSorter(String typeSorter) {
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

	
	public SorterDetails toSorterDetails(Sorter entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = SorterDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.typeSorter=entity.getTypeSorter().getNameTypeSorter()+_embedded;
		this.typeSorterId=entity.getTypeSorter().getId();
		this.nameSorter=nameSorter+_embedded;

		return this;
	}
}