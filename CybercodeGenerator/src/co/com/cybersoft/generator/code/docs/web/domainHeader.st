package co.com.cybersoft.docs.web.domain.$docName$;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.docs.persistence.domain.$upperDocName$Body;

$imports$
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import co.com.cybersoft.tables.core.domain.CountryDetails;
import co.com.cybersoft.tables.core.domain.DepartmentDetails;
import co.com.cybersoft.tables.core.domain.ExpenseTypeDetails;
import co.com.cybersoft.tables.core.domain.PopulatedPlaceDetails;
import co.com.cybersoft.tables.core.domain.PriorityDetails;
import co.com.cybersoft.tables.core.domain.StateDetails;
import co.com.cybersoft.tables.core.domain.TransportationTypeDetails;
import co.com.cybersoft.tables.core.domain.WarehouseDetails;


/**
 * Controller for $docName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $upperDocName$Info implements Serializable{

	$fields$

	private Long numericId;
	
	private Boolean created=Boolean.FALSE;
	
	private Boolean showBody=Boolean.FALSE;
	
	private Boolean _toSave=Boolean.FALSE;

	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Date dateOfModification;
	
	private List<$upperDocName$BodyInfo> $docName$Body=new ArrayList<$upperDocName$BodyInfo>();
	
	private $upperDocName$BodyInfo current$upperDocName$BodyInfo;
	
	private $upperDocName$BodyInfo $docName$BodyModificationInfo;
	
	private String deletion;

	private Boolean active;
	
	$gettersSetters$

		public Long getNumericId() {
		return numericId;
	}



	public void setNumericId(Long numericId) {
		this.numericId = numericId;
	}



	public Boolean getCreated() {
		return created;
	}



	public void setCreated(Boolean created) {
		this.created = created;
	}



	public Boolean getShowBody() {
		return showBody;
	}



	public void setShowBody(Boolean showBody) {
		this.showBody = showBody;
	}



	public Boolean get_toSave() {
		return _toSave;
	}



	public void set_toSave(Boolean _toSave) {
		this._toSave = _toSave;
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



	public Date getDateOfModification() {
		return dateOfModification;
	}



	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}



	public List<$upperDocName$BodyInfo> get$upperDocName$Body() {
		return $docName$Body;
	}



	public void set$upperDocName$Body(List<$upperDocName$BodyInfo> $docName$Body) {
		this.$docName$Body = $docName$Body;
	}



	public $upperDocName$BodyInfo getCurrent$upperDocName$BodyInfo() {
		return current$upperDocName$BodyInfo;
	}



	public void setCurrent$upperDocName$BodyInfo(
			$upperDocName$BodyInfo current$upperDocName$BodyInfo) {
		this.current$upperDocName$BodyInfo = current$upperDocName$BodyInfo;
	}



	public $upperDocName$BodyInfo get$upperDocName$BodyModificationInfo() {
		return $docName$BodyModificationInfo;
	}



	public void set$upperDocName$BodyModificationInfo(
			$upperDocName$BodyInfo $docName$BodyModificationInfo) {
		this.$docName$BodyModificationInfo = $docName$BodyModificationInfo;
	}



	public String getDeletion() {
		return deletion;
	}



	public void setDeletion(String deletion) {
		this.deletion = deletion;
	}


	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}

	public $upperDocName$Info to$upperDocName$Info($upperDocName$ entity){
		BeanUtils.copyProperties(entity, this);
		List<$upperDocName$BodyInfo> bodyList = new ArrayList<$upperDocName$BodyInfo>();
		List<$upperDocName$Body> $docName$EntityBody = entity.get$upperDocName$EntityBody();
		for ($upperDocName$Body $docName$Body : $docName$EntityBody) {
			$upperDocName$BodyInfo $docName$BodyInfo = new $upperDocName$BodyInfo();
			BeanUtils.copyProperties($docName$Body, $docName$BodyInfo);
			bodyList.add($docName$BodyInfo);
			this.set$upperDocName$Body(bodyList);
		}
		return this;
	}
	
}