package co.com.cybersoft.maintenance.tables.core.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.util.EmbeddedField;
import java.lang.reflect.Method;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;
import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class ConceptKardexDetails {
	
	private Long companyId;


	private String company;


	private Long storeId;


	private String store;


	private Integer numberConceptKardex;


	private Integer nameConceptKardex;


	private Long typeConceptKardexId;


	private String typeConceptKardex;


	private Boolean indicatorTransfers;


	private Integer workOrderConceptKardex;


	private Boolean defaultIndicatorConcept;


	private Boolean subtypeConceptKardex;


	private Boolean deliveryConceptContractor;


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
	public Long getStoreId() {
		return storeId;	
	}
		
	public void setStoreId(Long storeId) {
		this.storeId = storeId;	
	}
	public String getStore() {
		return store;	
	}
		
	public void setStore(String store) {
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
	public Long getTypeConceptKardexId() {
		return typeConceptKardexId;	
	}
		
	public void setTypeConceptKardexId(Long typeConceptKardexId) {
		this.typeConceptKardexId = typeConceptKardexId;	
	}
	public String getTypeConceptKardex() {
		return typeConceptKardex;	
	}
		
	public void setTypeConceptKardex(String typeConceptKardex) {
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

	
	public ConceptKardexDetails toConceptKardexDetails(ConceptKardex entity, EmbeddedField... fields){
		BeanUtils.copyProperties(entity, this);
		String _embedded="";
		for (EmbeddedField embeddedField : fields) {
			try {
				Method _method = ConceptKardexDetails.class.getMethod("get"+embeddedField.getName());
				String _invoke = (String) _method.invoke(this);
				_embedded+=" - "+_invoke;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.company=entity.getCompany().getCompanyName()+_embedded;
		this.companyId=entity.getCompany().getId();
		this.store=entity.getStore().getStoreName()+_embedded;
		this.storeId=entity.getStore().getId();
		this.typeConceptKardex=entity.getTypeConceptKardex().getTypeConceptKardex()+_embedded;
		this.typeConceptKardexId=entity.getTypeConceptKardex().getId();

		return this;
	}
}