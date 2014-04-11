package co.com.cybersoft.web.domain.measurementUnit;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Controller for measurementUnit
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MeasurementUnitInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private String code;


	private String name;


	private String description;


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