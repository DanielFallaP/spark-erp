package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CharacteristicDetails {
	
	private Long companyId;


	private String company;


	private String nameCharacteristic;


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
	public String getNameCharacteristic() {
		return nameCharacteristic;	
	}
		
	public void setNameCharacteristic(String nameCharacteristic) {
		this.nameCharacteristic = nameCharacteristic;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public CharacteristicDetails toCharacteristicDetails(Characteristic entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = CharacteristicDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.nameCharacteristic=nameCharacteristic+_embedded;

		return this;
	}
}