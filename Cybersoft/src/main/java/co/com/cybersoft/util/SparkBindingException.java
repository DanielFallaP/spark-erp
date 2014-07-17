package co.com.cybersoft.util;

import java.util.List;

import org.springframework.validation.ObjectError;

public class SparkBindingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7647817299432271310L;
	
	
	private final List<ObjectError> errors;
	
	private final String origin;
	
	public SparkBindingException(List<ObjectError> errors, String origin){
		this.errors=errors;
		this.origin=origin;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public String getOrigin() {
		return origin;
	}
	
}
