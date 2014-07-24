package co.com.cybersoft.generator.code.model;

import java.util.Map;

import co.com.cybersoft.generator.code.util.CodeUtil;

public class OnLoad {
	
	private String className;
	
	private String methodName;
	
	private Map<String,String> parameters;
	
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
	
	
}
