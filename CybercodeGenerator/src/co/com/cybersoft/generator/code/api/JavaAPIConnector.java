package co.com.cybersoft.generator.code.api;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;

import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class JavaAPIConnector {

	public static Object generateOnLoadHeaderValues(Document document) {
		List<Field> fields = document.getHeader();
		String defaults="";
		for (Field field : fields) {
			if (field.getOnLoad()!=null){
				StringTemplate stringTemplate = new StringTemplate("$tableName$Info.set$fieldName$($defaultValue$);\n");
				stringTemplate.setAttribute("tableName", document.getName());
				stringTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
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
				stringTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				stringTemplate.setAttribute("defaultValue", field.getOnLoad().getName()+"."+field.getOnLoad().getMethodName()+"()");
				defaults+=stringTemplate.toString();
			}
		}
		return defaults;
	}

}
