package co.com.cybersoft.web.domain.corporation;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import co.com.cybersoft.core.domain.ActiveDetails;
import java.util.List;
import java.util.ArrayList;


/**
 * Controller for corporation
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CorporationInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	@NotNull
	@Range(max=99)
	private Integer code;


	@NotEmpty
	private String description;


	@Length(max=15)
	@NotEmpty
	private String nit;


	private List<ActiveDetails> activeList;
	private String active;


	public Integer getCode() {
		return code;	
	}
		
	public void setCode(Integer code) {
		this.code = code;	
	}

	public String getDescription() {
		return description;	
	}
		
	public void setDescription(String description) {
		this.description = description;	
	}

	public String getNit() {
		return nit;	
	}
		
	public void setNit(String nit) {
		this.nit = nit;	
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