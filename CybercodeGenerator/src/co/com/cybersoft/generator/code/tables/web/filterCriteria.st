package co.com.cybersoft.tables.web.domain.$tableName$;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $entityName$FilterInfo {
	private String userName;

	private Date dateOfCreation;
	
	private Date dateOfModification;
	
	private String createdBy;
	$filterFields$
	
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
	
	public Date getDateOfModification() {
		return dateOfModification;
	}
	
	public void setDateOfModification(Date dateOfModification) {
		this.dateOfModification = dateOfModification;
	}
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
