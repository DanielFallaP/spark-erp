package co.com.cybersoft.persistence.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.events.afe.AFEDetails;

@Document(collection="afe")
public class AFE {
	
	@Id
	private String id;
	
	private String code;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static AFE fromAFEDetails(AFEDetails afeDetails){
		AFE afe = new AFE();
		BeanUtils.copyProperties(afeDetails, afe);
		return afe;
	}
	
	public AFEDetails toAFEDetails(){
		AFEDetails details = new AFEDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}
	
}
