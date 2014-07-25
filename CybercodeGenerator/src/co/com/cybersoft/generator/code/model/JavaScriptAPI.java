package co.com.cybersoft.generator.code.model;

import java.util.List;

public class JavaScriptAPI {
	private String scriptLocation;
	
	private String methodName;
	
	private List<String> parameters;
	
	private List<String> constantParameters;
	
	
	public List<String> getConstantParameters() {
		return constantParameters;
	}

	public void setConstantParameters(List<String> constantParameters) {
		this.constantParameters = constantParameters;
	}

	public String getScriptLocation() {
		return scriptLocation;
	}

	public void setScriptLocation(String scriptLocation) {
		this.scriptLocation = scriptLocation;
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

	
}
