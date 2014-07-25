package co.com.cybersoft.generator.code.model;

import java.util.Map;

import co.com.cybersoft.generator.code.util.CodeUtil;

public class JavaAPI {
	protected String className;
	
	protected String methodName;
	
	protected Map<String,String> parameters;
	
	protected String coordinates;
	
	public String getName(){
		return CodeUtil.toLowerCamelCase(className.substring(className.lastIndexOf('.')+1));
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
}
