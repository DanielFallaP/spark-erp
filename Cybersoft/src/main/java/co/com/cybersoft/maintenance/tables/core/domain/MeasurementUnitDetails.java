package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class MeasurementUnitDetails {
	
	private Long companyId;


	private String company;


	private String nameUnit;


	private String abbreviationUnit;


	private Integer typeUnit;


	private Integer conversionPattern;


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
	public String getNameUnit() {
		return nameUnit;	
	}
		
	public void setNameUnit(String nameUnit) {
		this.nameUnit = nameUnit;	
	}
	public String getAbbreviationUnit() {
		return abbreviationUnit;	
	}
		
	public void setAbbreviationUnit(String abbreviationUnit) {
		this.abbreviationUnit = abbreviationUnit;	
	}
	public Integer getTypeUnit() {
		return typeUnit;	
	}
		
	public void setTypeUnit(Integer typeUnit) {
		this.typeUnit = typeUnit;	
	}
	public Integer getConversionPattern() {
		return conversionPattern;	
	}
		
	public void setConversionPattern(Integer conversionPattern) {
		this.conversionPattern = conversionPattern;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public MeasurementUnitDetails toMeasurementUnitDetails(MeasurementUnit entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = MeasurementUnitDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.nameUnit=nameUnit+_embedded;
		this.abbreviationUnit=abbreviationUnit+_embedded;

		return this;
	}
}