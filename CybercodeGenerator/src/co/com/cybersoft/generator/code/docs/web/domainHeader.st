package co.com.cybersoft.docs.web.domain.$docName$;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.docs.persistence.domain.$upperDocName$Body;

$imports$


/**
 * Controller for $docName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $upperDocName$Info implements Serializable{
	
	private Long numericId;
	
	private Boolean created=Boolean.FALSE;
	
	private Boolean showBody=Boolean.FALSE;
	
	private Boolean _toSave=Boolean.FALSE;

	private String id;
	
	private String userName;

	private Date dateOfCreation;
	
	private String createdBy;
	
	private Date dateOfModification;
	
	private List<$upperDocName$BodyInfo> $docName$BodyList=new ArrayList<$upperDocName$BodyInfo>();
	
	private $upperDocName$BodyInfo current$upperDocName$BodyInfo;
	
	private $upperDocName$BodyInfo $docName$BodyModificationInfo;
	
	private String deletion;
	
	private String stringId;
	
	public String getStringId() {
		return stringId;
	}
	
	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	$fields$
	
	public $upperDocName$Info(){
	}
	
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
	
	public List<$upperDocName$BodyInfo> get$upperDocName$BodyList() {
		return $docName$BodyList;
	}
	
	public void set$upperDocName$BodyList(List<$upperDocName$BodyInfo> $docName$BodyList) {
		this.$docName$BodyList = $docName$BodyList;
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
	
	public $upperDocName$Info to$upperDocName$Info($upperDocName$ entity){
		BeanUtils.copyProperties(entity, this);
		List<$upperDocName$BodyInfo> bodyList = new ArrayList<$upperDocName$BodyInfo>();
		List<$upperDocName$Body> $docName$ItemEntityList = entity.get$upperDocName$BodyEntityList();
		for ($upperDocName$Body $docName$Item : $docName$ItemEntityList) {
			$upperDocName$BodyInfo $docName$ItemInfo = new $upperDocName$BodyInfo();
			BeanUtils.copyProperties($docName$Item, $docName$ItemInfo);
			bodyList.add($docName$ItemInfo);
			this.set$upperDocName$BodyList(bodyList);
		}
		return this;
	}

}