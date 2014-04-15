package co.com.cybersoft.web.domain.articulo;

import java.io.Serializable;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.hibernate.validator.constraints.NotEmpty;
import co.com.cybersoft.core.domain.UnidadMedidaDetails;
import co.com.cybersoft.core.domain.GrupoDetails;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


/**
 * Controller for articulo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ArticuloInfo implements Serializable{
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;
	
	private String userName;

	@NotEmpty
	private String code;


	@NotEmpty
	private String description;


	@NotEmpty
	private String branch;


	private Date activationDate;


	private List<UnidadMedidaDetails> unidadMedidaList;
	private String unidadMedida;


	private List<GrupoDetails> grupoList;
	private String grupo;


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

	public List<UnidadMedidaDetails> getUnidadMedidaList() {
		return unidadMedidaList;
	}

	public void setUnidadMedidaList(
				List<UnidadMedidaDetails> unidadMedidaList) {
			this.unidadMedidaList = unidadMedidaList;
	}

	public String getUnidadMedida() {
		return unidadMedida;	
	}
		
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;	
	}

	public List<GrupoDetails> getGrupoList() {
		return grupoList;
	}

	public void setGrupoList(
				List<GrupoDetails> grupoList) {
			this.grupoList = grupoList;
	}

	public String getGrupo() {
		return grupo;	
	}
		
	public void setGrupo(String grupo) {
		this.grupo = grupo;	
	}

	public void rearrangeUnidadMedidaList(String selected){
			UnidadMedidaDetails selectedUnidadMedida=null;
			ArrayList<UnidadMedidaDetails> newList = new ArrayList<UnidadMedidaDetails>();
			for(UnidadMedidaDetails unidadMedida:unidadMedidaList){
				if (unidadMedida.getName().equals(selected)){
					selectedUnidadMedida=unidadMedida;
					newList.add(0, selectedUnidadMedida);
				}
				else{
					newList.add(unidadMedida);
				}
			}
			unidadMedidaList=newList;
		
		}
	public void rearrangeGrupoList(String selected){
			GrupoDetails selectedGrupo=null;
			ArrayList<GrupoDetails> newList = new ArrayList<GrupoDetails>();
			for(GrupoDetails grupo:grupoList){
				if (grupo.getDescription().equals(selected)){
					selectedGrupo=grupo;
					newList.add(0, selectedGrupo);
				}
				else{
					newList.add(grupo);
				}
			}
			grupoList=newList;
		
		}

	
	private Date dateOfCreation;
	
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