package co.com.cybersoft.maintenance.tables.web.domain.third;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for third
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ThirdInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private Long id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Long companyId;


	private String company;


	private List<CompanyDetails> companyList;
	private Long costCenterId;


	private String costCenter;


	private List<CostCentersCustomersDetails> costCenterList;
	@Length(max=15)
	@NotEmpty
	private String code;


	@Length(max=40)
	@NotEmpty
	private String nameThird;


	@NotNull
	private Integer typeThird;


	@Length(max=30)
	@NotEmpty
	private String contact;


	@Length(max=30)
	@NotEmpty
	private String address;


	@Length(max=20)
	@NotEmpty
	private String country;


	@Length(max=25)
	@NotEmpty
	private String phoneOne;


	@Length(max=25)
	@NotEmpty
	private String phoneTwo;


	@Length(max=50)
	@NotEmpty
	private String email;


	private Date dateEntry;


	private Date dateRetirement;


	@Length(max=4000)
	@NotEmpty
	private String comment;


	@NotNull
	private Integer bigContributor;


	@NotNull
	private Integer autorretenedor;


	@Length(max=2)
	@NotEmpty
	private String typeRegime;


	@Length(max=2)
	@NotEmpty
	private String externalAccess;


	@Length(max=35)
	@NotEmpty
	private String keyExternalAccess;


	private Boolean stateThird;


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

	public List<CostCentersCustomersDetails> getCostCenterList() {
		return costCenterList;
	}
	public void setCostCenterList(
				List<CostCentersCustomersDetails> costCenterList) {
			this.costCenterList = costCenterList;
	}

	public String getCostCenter() {
		return costCenter;	
	}
		
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;	
	}

	public Long getCostCenterId() {
		return costCenterId;	
	}
		
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;	
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