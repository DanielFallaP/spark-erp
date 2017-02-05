package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class OtherConceptsDetails {
	
	private Long companyId;


	private String company;


	private String nameOtherconcepts;


	private Double unitValue;


	private Long unitMeasureId;


	private String unitMeasure;


	private Long typeWorkId;


	private String typeWork;


	private Integer informative;


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
	public String getNameOtherconcepts() {
		return nameOtherconcepts;	
	}
		
	public void setNameOtherconcepts(String nameOtherconcepts) {
		this.nameOtherconcepts = nameOtherconcepts;	
	}
	public Double getUnitValue() {
		return unitValue;	
	}
		
	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;	
	}
	public Long getUnitMeasureId() {
		return unitMeasureId;	
	}
		
	public void setUnitMeasureId(Long unitMeasureId) {
		this.unitMeasureId = unitMeasureId;	
	}
	public String getUnitMeasure() {
		return unitMeasure;	
	}
		
	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;	
	}
	public Long getTypeWorkId() {
		return typeWorkId;	
	}
		
	public void setTypeWorkId(Long typeWorkId) {
		this.typeWorkId = typeWorkId;	
	}
	public String getTypeWork() {
		return typeWork;	
	}
		
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;	
	}
	public Integer getInformative() {
		return informative;	
	}
		
	public void setInformative(Integer informative) {
		this.informative = informative;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public OtherConceptsDetails toOtherConceptsDetails(OtherConcepts entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = OtherConceptsDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.nameOtherconcepts=nameOtherconcepts+_embedded;
		this.unitMeasure=entity.getUnitMeasure().getNameUnit()+_embedded;
		this.unitMeasureId=entity.getUnitMeasure().getId();
		this.typeWork=entity.getTypeWork().getTypeWork()+_embedded;
		this.typeWorkId=entity.getTypeWork().getId();

		return this;
	}
}