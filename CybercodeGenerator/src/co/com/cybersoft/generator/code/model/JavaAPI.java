package co.com.cybersoft.generator.code.model;

import java.util.List;

import co.com.cybersoft.generator.code.util.CodeUtil;

public class JavaAPI {
	protected String className;
	
	protected String methodName;
	
	protected List<String> parameters;
	
	protected List<String> constantParameters;
	
	private String returnVar;
	
	protected String coordinates;
	
	public String getName(){
		return CodeUtil.toLowerCamelCase(className.substring(className.lastIndexOf('.')+1));
	}
	
	public String getReturnVar() {
		return returnVar;
	}

	public void setReturnVar(String returnVar) {
		this.returnVar = returnVar;
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

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public List<String> getConstantParameters() {
		return constantParameters;
	}

	public void setConstantParameters(List<String> constantParameters) {
		this.constantParameters = constantParameters;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
}
