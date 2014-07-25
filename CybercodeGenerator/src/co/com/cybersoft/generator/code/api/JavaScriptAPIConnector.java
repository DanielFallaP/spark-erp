package co.com.cybersoft.generator.code.api;

import java.util.ArrayList;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;

import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.JavaScriptAPI;

public class JavaScriptAPIConnector {

	public static Object generateAutoCompletePeerFunction(JavaScriptAPI api) {
		String codeCall=api.getMethodName()+"(";
		List<String> parameters = api.getParameters();
		int i=0;
		for (String parameter : parameters) {
			codeCall+=parameter;
			if (i!=parameters.size()-1){
				codeCall+=",";
			}
			i++;
		}
		
		i=0;
		List<String> constantParameters = api.getConstantParameters();
		if (!constantParameters.isEmpty())
			codeCall+=",";
		
		for (String constantParameter : constantParameters) {
			codeCall+="\""+constantParameter+"\"";
			if (i!=constantParameters.size()-1){
				codeCall+=",";
			}
			i++;
		}
		
		return codeCall+")\n";
	}

	public static Object generateIncludeScripts(Document document) {
		List<Field> allFields = document.getAllFields();
		String javaScripts="";
		for (Field field : allFields) {
			if (field.getAutoCompletePeerFunction()!=null){
				StringTemplate template = new StringTemplate("<script th:src=\"@{$url$}\"></script>\n");
				template.setAttribute("url", field.getAutoCompletePeerFunction().getScriptLocation());
				javaScripts+=template.toString();
			}
		}
		return javaScripts;
	}

	public static JavaScriptAPI getModificationAutoCompletePeerFunction(JavaScriptAPI autoCompletePeerFunction,Document document) {
		JavaScriptAPI scriptAPI = new JavaScriptAPI();
		
		List<String> constantParameters = new ArrayList<String>(autoCompletePeerFunction.getConstantParameters());
		
		String peerField = constantParameters.get(0);
		peerField=document.getName()+"BodyModificationInfo\\\\."+peerField;
		constantParameters.remove(0);
		constantParameters.add(0, peerField);
		scriptAPI.setParameters(autoCompletePeerFunction.getParameters());
		scriptAPI.setConstantParameters(constantParameters);
		scriptAPI.setMethodName(autoCompletePeerFunction.getMethodName());
		scriptAPI.setScriptLocation(autoCompletePeerFunction.getScriptLocation());
		
		return scriptAPI;
	}

}
