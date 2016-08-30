package co.com.cybersoft.purchase.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CountryDetails {
	
	private Long regionId;


	private String region;


	private String country;


	private String localName;


	private String frenchName;


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
	
	public Long getRegionId() {
		return regionId;	
	}
		
	public void setRegionId(Long regionId) {
		this.regionId = regionId;	
	}
	public String getRegion() {
		return region;	
	}
		
	public void setRegion(String region) {
		this.region = region;	
	}
	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}
	public String getLocalName() {
		return localName;	
	}
		
	public void setLocalName(String localName) {
		this.localName = localName;	
	}
	public String getFrenchName() {
		return frenchName;	
	}
		
	public void setFrenchName(String frenchName) {
		this.frenchName = frenchName;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public CountryDetails toCountryDetails(Country entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = CountryDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.region=entity.getRegion().getRegion()+_embedded;
		this.regionId=entity.getRegion().getId();
		this.country=country+_embedded;
		this.localName=localName+_embedded;
		this.frenchName=frenchName+_embedded;

		return this;
	}
}