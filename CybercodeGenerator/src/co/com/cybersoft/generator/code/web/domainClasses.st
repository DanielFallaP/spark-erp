package co.com.cybersoft.web.domain.$tableName$;

import java.io.Serializable;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Cybersoft 2014. All rights reserved.
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class $domainClassName$ implements Serializable{

	private String userName;
	
	private Boolean created;
	
	private String calledFrom;
	
	private String id;

	$bodyDomainClass$
	
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