package co.com.cybersoft.util;

import java.io.Serializable;

/**
 * This class represents an embedded field in 
 * a document
 * @author daniel, Cybersystems 2014
 *
 */
public class EmbeddedField implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6071501974337032516L;

	private String name;
	
	private Class<?> type;
	
	public EmbeddedField(String name, Class<?> type){
		this.name=name;
		this.type=type;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}
	
}
