package co.com.cybersoft.man.services.security;

import java.util.Date;

/**
 * Represents an action carried by a logged user
 * in the system
 * @author daniel
 *
 */
public class SessionAction {
	private String user;
	private String entity;
	private String sessionId;
	private String action;
	private Long entityId;
	private Date actionDate;
	
	public SessionAction(String user, String sessionId, String action, Date actionDate, String entity, Long entityId){
		this.user=user;
		this.sessionId=sessionId;
		this.action=action;
		this.actionDate=actionDate;
		this.entity=entity;
		this.entityId=entityId;
	}
	
	public String getEntity() {
		return entity;
	}



	public void setEntity(String entity) {
		this.entity = entity;
	}



	public Long getEntityId() {
		return entityId;
	}



	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}



	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
