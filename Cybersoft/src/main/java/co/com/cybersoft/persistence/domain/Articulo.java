package co.com.cybersoft.persistence.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.com.cybersoft.core.domain.ArticuloDetails;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="articulo")
public class Articulo {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String code;

	@Indexed(unique=true)
	private String description;

	private String branch;

	private Date activationDate;

	private String unidadMedida;

	private String grupo;


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
	public String getBranch() {
		return branch;	
	}
		
	public void setBranch(String branch) {
		this.branch = branch;	
	}
	public Date getActivationDate() {
		return activationDate;	
	}
		
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;	
	}
	public String getUnidadMedida() {
		return unidadMedida;	
	}
		
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;	
	}
	public String getGrupo() {
		return grupo;	
	}
		
	public void setGrupo(String grupo) {
		this.grupo = grupo;	
	}

	
	public static Articulo fromArticuloDetails(ArticuloDetails details){
		Articulo articulo = new Articulo();
		BeanUtils.copyProperties(details, articulo);
		return articulo;
	}
	
	public ArticuloDetails toArticuloDetails(){
		ArticuloDetails details = new ArticuloDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

}