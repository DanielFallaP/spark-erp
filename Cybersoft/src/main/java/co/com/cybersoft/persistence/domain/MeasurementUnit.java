package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.MeasurementUnitDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="measurementUnit")
public class MeasurementUnit {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String code;

	@Indexed(unique=true)
	private String name;

	private String description;


	private Date dateOfModification;
	
	private String userName;
	
	public Date getDateOfModification() {
		return dateOfModification;
	}
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
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
	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	
	public static MeasurementUnit fromMeasurementUnitDetails(MeasurementUnitDetails details){
		MeasurementUnit measurementUnit = new MeasurementUnit();
		BeanUtils.copyProperties(details, measurementUnit);
		return measurementUnit;
	}
	
	public MeasurementUnitDetails toMeasurementUnitDetails(){
		MeasurementUnitDetails details = new MeasurementUnitDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}