package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.Third;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ThirdDetails {
	
	private Long companyId;


	private String company;


	private Long costCenterId;


	private String costCenter;


	private String code;


	private String nameThird;


	private Integer typeThird;


	private String contact;


	private String address;


	private String country;


	private String phoneOne;


	private String phoneTwo;


	private String email;


	private Date dateEntry;


	private Date dateRetirement;


	private String comment;


	private Integer bigContributor;


	private Integer autorretenedor;


	private String typeRegime;


	private String externalAccess;


	private String keyExternalAccess;


	private Boolean stateThird;


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
	public Long getCostCenterId() {
		return costCenterId;	
	}
		
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;	
	}
	public String getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;	
	}
	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}
	public String getNameThird() {
		return nameThird;	
	}
		
	public void setNameThird(String nameThird) {
		this.nameThird = nameThird;	
	}
	public Integer getTypeThird() {
		return typeThird;	
	}
		
	public void setTypeThird(Integer typeThird) {
		this.typeThird = typeThird;	
	}
	public String getContact() {
		return contact;	
	}
		
	public void setContact(String contact) {
		this.contact = contact;	
	}
	public String getAddress() {
		return address;	
	}
		
	public void setAddress(String address) {
		this.address = address;	
	}
	public String getCountry() {
		return country;	
	}
		
	public void setCountry(String country) {
		this.country = country;	
	}
	public String getPhoneOne() {
		return phoneOne;	
	}
		
	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;	
	}
	public String getPhoneTwo() {
		return phoneTwo;	
	}
		
	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;	
	}
	public String getEmail() {
		return email;	
	}
		
	public void setEmail(String email) {
		this.email = email;	
	}
	public Date getDateEntry() {
		return dateEntry;	
	}
		
	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;	
	}
	public Date getDateRetirement() {
		return dateRetirement;	
	}
		
	public void setDateRetirement(Date dateRetirement) {
		this.dateRetirement = dateRetirement;	
	}
	public String getComment() {
		return comment;	
	}
		
	public void setComment(String comment) {
		this.comment = comment;	
	}
	public Integer getBigContributor() {
		return bigContributor;	
	}
		
	public void setBigContributor(Integer bigContributor) {
		this.bigContributor = bigContributor;	
	}
	public Integer getAutorretenedor() {
		return autorretenedor;	
	}
		
	public void setAutorretenedor(Integer autorretenedor) {
		this.autorretenedor = autorretenedor;	
	}
	public String getTypeRegime() {
		return typeRegime;	
	}
		
	public void setTypeRegime(String typeRegime) {
		this.typeRegime = typeRegime;	
	}
	public String getExternalAccess() {
		return externalAccess;	
	}
		
	public void setExternalAccess(String externalAccess) {
		this.externalAccess = externalAccess;	
	}
	public String getKeyExternalAccess() {
		return keyExternalAccess;	
	}
		
	public void setKeyExternalAccess(String keyExternalAccess) {
		this.keyExternalAccess = keyExternalAccess;	
	}
	public Boolean getStateThird() {
		return stateThird;	
	}
		
	public void setStateThird(Boolean stateThird) {
		this.stateThird = stateThird;	
	}
	public Boolean getActive() {
		return active;	
	}
		
	public void setActive(Boolean active) {
		this.active = active;	
	}

	
	public ThirdDetails toThirdDetails(Third entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = ThirdDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.costCenter=entity.getCostCenter().getNameCostCenter()+_embedded;
		this.costCenterId=entity.getCostCenter().getId();
		this.code=code+_embedded;
		this.nameThird=nameThird+_embedded;
		this.contact=contact+_embedded;
		this.address=address+_embedded;
		this.country=country+_embedded;
		this.phoneOne=phoneOne+_embedded;
		this.phoneTwo=phoneTwo+_embedded;
		this.email=email+_embedded;
		this.comment=comment+_embedded;
		this.typeRegime=typeRegime+_embedded;
		this.externalAccess=externalAccess+_embedded;
		this.keyExternalAccess=keyExternalAccess+_embedded;

		return this;
	}
}