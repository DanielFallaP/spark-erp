package co.com.cybersoft.generator.code.model;

import java.util.List;

public class JavaScriptAPI {
	private String scriptLocation;
	
	private String methodName;
	
	private List<Object> parameters;
	
	private List<Object> constantParameters;
	
	private String embeddedCodeFile;
	
	private String embeddedModificationCodeFile;
	
	public String getEmbeddedModificationCodeFile() {
		return embeddedModificationCodeFile;
	}

	public void setEmbeddedModificationCodeFile(String embeddedModificationCodeFile) {
		this.embeddedModificationCodeFile = embeddedModificationCodeFile;
	}

	public String getEmbeddedCodeFile() {
		return embeddedCodeFile;
	}

	public void setEmbeddedCodeFile(String embeddedCodeFile) {
		this.embeddedCodeFile = embeddedCodeFile;
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

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public List<Object> getConstantParameters() {
		return constantParameters;
	}

	public void setConstantParameters(List<Object> constantParameters) {
		this.constantParameters = constantParameters;
	}

	
}
