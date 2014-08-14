package co.com.cybersoft.generator.code.api;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;

import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class JavaAPIConnector {

	public static Object generateOnLoadHeaderValues(Document document) {
		List<Field> fields = document.getHeader();
		String defaults="";
		for (Field field : fields) {
			if (field.getOnLoad()!=null){
				StringTemplate stringTemplate = new StringTemplate("$tableName$Info.set$fieldName$($defaultValue$);\n");
				stringTemplate.setAttribute("tableName", document.getName());
				stringTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				stringTemplate.setAttribute("defaultValue", field.getOnLoad().getName()+"."+field.getOnLoad().getMethodName()+"()");
				defaults+=stringTemplate.toString();
			}
		}
		return defaults;
	}

	public static Object generateOnLoadBodyValues(Document document) {
		List<Field> fields = document.getBody();
		String defaults="";
		for (Field field : fields) {
			if (field.getOnLoad()!=null){
				StringTemplate stringTemplate = new StringTemplate("$tableName$BodyInfo.set$fieldName$($defaultValue$);\n");
				stringTemplate.setAttribute("tableName", document.getName());
				stringTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				stringTemplate.setAttribute("defaultValue", field.getOnLoad().getName()+"."+field.getOnLoad().getMethodName()+"()");
				defaults+=stringTemplate.toString();
			}
		}
		return defaults;
	}

	public static Object generateOnHeaderSave(Document document) {
		String code="";
		if (document.getOnHeaderSave()!=null){
			if (document.getOnHeaderSave().getReturnVar()!=null){
				code+=document.getOnHeaderSave().getReturnVar()+"=";
			}
			List<String> parameters = document.getOnHeaderSave().getParameters();
			String par="";
			int i=0;
			for (String parameter : parameters) {
				par+=parameter;
				if (i!=parameters.size()-1){
					par+=",";
				}
				i++;
			}		
			code=document.getOnHeaderSave().getName()+"."+document.getOnHeaderSave().getMethodName()+"("+par+");";
			return code;
		}
		return "";
	}
	
	public static Object generateOnHeaderPreSave(Document document){
		String code="";
		if (document.getOnHeaderPreSave()!=null){
			if (document.getOnHeaderPreSave().getReturnVar()!=null){
				code+=document.getOnHeaderPreSave().getReturnVar()+"=";
			}
			List<String> parameters = document.getOnHeaderPreSave().getParameters();
			String par="";
			int i=0;
			for (String parameter : parameters) {
				par+=parameter;
				if (i!=parameters.size()-1){
					par+=",";
				}
				i++;
			}		
			code+=document.getOnHeaderPreSave().getName()+"."+document.getOnHeaderPreSave().getMethodName()+"("+par+");";
			return code;
		}
		return "";
	}

}
