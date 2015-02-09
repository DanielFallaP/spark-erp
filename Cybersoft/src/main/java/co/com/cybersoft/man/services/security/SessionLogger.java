package co.com.cybersoft.man.services.security;

import org.springframework.data.mongodb.core.MongoOperations;

public class SessionLogger implements Runnable{
	private MongoOperations mongo;
	
	private SessionAction sessionAction;

	public SessionLogger(SessionAction sessionAction, MongoOperations mongo){
		this.sessionAction=sessionAction;
		this.mongo=mongo;
	}
	
	public SessionAction getSessionAction() {
		return sessionAction;
	}

	public void setSessionAction(SessionAction sessionAction) {
		this.sessionAction = sessionAction;
	}

	@Override
	public void run() {
		mongo.insert(sessionAction);
	}

}
