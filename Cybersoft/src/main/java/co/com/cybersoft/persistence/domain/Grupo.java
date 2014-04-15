package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.GrupoDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="grupo")
public class Grupo {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String code;

	@Indexed(unique=true)
	private String description;

	private Integer number;


	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;
	
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	public Integer getNumber() {
		return number;	
	}
		
	public void setNumber(Integer number) {
		this.number = number;	
	}

	
	public static Grupo fromGrupoDetails(GrupoDetails details){
		Grupo grupo = new Grupo();
		BeanUtils.copyProperties(details, grupo);
		return grupo;
	}
	
	public GrupoDetails toGrupoDetails(){
		GrupoDetails details = new GrupoDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}