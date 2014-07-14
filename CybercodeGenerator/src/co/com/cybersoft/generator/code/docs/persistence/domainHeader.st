package co.com.cybersoft.docs.persistence.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$BodyInfo;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$Info;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Document(collection="$docName$")
public class $upperDocName$ {

	@Id
	private String id;
	
	@Field(value="_numericId")
	private Long numericId;

	private List<$upperDocName$Body> $docName$BodyEntityList=new ArrayList<$upperDocName$Body>();

	private Boolean active;
	
	private Date dateOfModification;
	
	private Date dateOfCreation;
	
	private String userName;
	
	private String createdBy;

	public Long getNumericId() {
		return numericId;
	}
	public void setNumericId(Long numericId) {
		this.numericId = numericId;
	}
	public List<$upperDocName$Body> get$upperDocName$BodyEntityList() {
		return $docName$BodyEntityList;
	}
	public void set$upperDocName$BodyEntityList(
			List<$upperDocName$Body> $docName$BodyEntityList) {
		this.$docName$BodyEntityList = $docName$BodyEntityList;
	}
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
	public Boolean getActive() {
		return active;	
	}
	
	public void setActive(Boolean active) {
		this.active = active;	
	}
	
	$fields$
	
	$gettersSetters$
	
	public $upperDocName$ from$upperDocName$Info($upperDocName$Info webInfo){
		BeanUtils.copyProperties(webInfo, this);
		ArrayList<$upperDocName$Body> bodyList = new ArrayList<$upperDocName$Body>();
		List<$upperDocName$BodyInfo> $docName$BodyList = webInfo.get$upperDocName$BodyList();
		for ($upperDocName$BodyInfo $docName$BodyInfo : $docName$BodyList) {
			$upperDocName$Body $docName$Body = new $upperDocName$Body();
			BeanUtils.copyProperties($docName$BodyInfo, $docName$Body);
			bodyList.add($docName$Body);
			this.set$upperDocName$BodyEntityList(bodyList);
		}
		return this;
	}

}