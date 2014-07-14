package co.com.cybersoft.docs.web.domain.$docName$;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

$imports$
import co.com.cybersoft.tables.core.domain.PriorityDetails;


/**
 * Controller for $docName$Item
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $upperDocName$BodyInfo implements Serializable{

	$fields$
	
	private Boolean created;
	
	private String submit;
	
	private String id;
	
	private $upperDocName$BodyInfo $docName$BodyModificationInfo;

	private Boolean selected;


	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public $upperDocName$BodyInfo get$upperDocName$BodyModificationInfo() {
		return $docName$BodyModificationInfo;
	}

	public void set$upperDocName$BodyModificationInfo(
			$upperDocName$BodyInfo $docName$BodyModificationInfo) {
		this.$docName$BodyModificationInfo = $docName$BodyModificationInfo;
	}
	
		public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	$gettersSetters$

}