package co.com.cybersoft.web.domain.items;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import co.com.cybersoft.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.core.domain.ActiveDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for items
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ItemsInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotEmpty
	private String code;


	@NotEmpty
	private String name;


	private List<MeasurementUnitDetails> measurementUnitList;
	private String measurementUnit;


	private List<ActiveDetails> activeList;
	private String active;


	public String getCode() {
		return code;	
	}
		
	public void setCode(String code) {
		this.code = code;	
	}

	public String getName() {
		return name;	
	}
		
	public void setName(String name) {
		this.name = name;	
	}

	public List<MeasurementUnitDetails> getMeasurementUnitList() {
		return measurementUnitList;
	}

	public void setMeasurementUnitList(
				List<MeasurementUnitDetails> measurementUnitList) {
			this.measurementUnitList = measurementUnitList;
	}

	public String getMeasurementUnit() {
		return measurementUnit;	
	}
		
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;	
	}

	public List<ActiveDetails> getActiveList() {
		return activeList;
	}

	public void setActiveList(
				List<ActiveDetails> activeList) {
			this.activeList = activeList;
	}

	public String getActive() {
		return active;	
	}
		
	public void setActive(String active) {
		this.active = active;	
	}

	public void rearrangeMeasurementUnitList(String selected){
			MeasurementUnitDetails selectedMeasurementUnit=null;
			ArrayList<MeasurementUnitDetails> newList = new ArrayList<MeasurementUnitDetails>();
			for(MeasurementUnitDetails measurementUnit:measurementUnitList){
				if (measurementUnit.getName().equals(selected)){
					selectedMeasurementUnit=measurementUnit;
					newList.add(0, selectedMeasurementUnit);
				}
				else{
					newList.add(measurementUnit);
				}
			}
			measurementUnitList=newList;
		
		}
	public void rearrangeActiveList(String selected){
			ActiveDetails selectedActive=null;
			ArrayList<ActiveDetails> newList = new ArrayList<ActiveDetails>();
			for(ActiveDetails active:activeList){
				if (active.getName().equals(selected)){
					selectedActive=active;
					newList.add(0, selectedActive);
				}
				else{
					newList.add(active);
				}
			}
			activeList=newList;
		
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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