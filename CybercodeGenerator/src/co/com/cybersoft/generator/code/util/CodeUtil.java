package co.com.cybersoft.generator.code.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.ReferenceField;
import co.com.cybersoft.generator.code.model.Table;

public class CodeUtil {
	
	
	public static String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
	
	public static void writeClass(String code, String filePath, String fileName){
		BufferedWriter bufferedWriter=null;
		try {
			File directory = new File(filePath);
			if (!directory.exists()){
				directory.mkdirs();
				directory.createNewFile();
			}
			
			File file = new File(directory, fileName);
			
			bufferedWriter=new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bufferedWriter.write(code);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getGettersAndSetters(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("util",Cybersoft.codePath+"util");
		List<Field> fields = table.getFields();
		List<ReferenceField> references = table.getReferences();
		
		String text="";
		for (Field field : fields) {
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", field.getType());
			template.setAttribute("name", field.getName());
			template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			text+=template.toString()+"\n";
		}
		
		for (ReferenceField field : references) {
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", Cybersoft.stringType);
			template.setAttribute("name", field.getName());
			template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			text+=template.toString()+"\n";
		}
		
		return text;
	}
	
	public static String getGetters(List<ReferenceField> fields){
		StringTemplateGroup templateGroup = new StringTemplateGroup("util",Cybersoft.codePath+"util");
		String text="";
		for (ReferenceField field : fields) {
			StringTemplate template = templateGroup.getInstanceOf("listGetters");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
			template.setAttribute("tableName", field.getName());
			text+=template.toString()+"\n";
		}
		
		return text;
	}
	
	public static Long getMaxNumber(Integer digitNumber){
		return (long) Math.pow(10, 3);
	}
	
	public static String getFieldDeclarations(Table table){
		String text="";
		List<Field> fields = table.getFields();
		List<ReferenceField> references = table.getReferences();
		
		for (Field field : fields) {
			StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
			fieldTemplate.setAttribute("type", field.getType());
			fieldTemplate.setAttribute("name", field.getName());
			text+=fieldTemplate.toString();
			text+="\n";
		}
		
		for (ReferenceField field : references) {
			StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
			fieldTemplate.setAttribute("type", Cybersoft.stringType);
			fieldTemplate.setAttribute("name", field.getName());
			text+=fieldTemplate.toString();
			text+="\n";
		}
		
		return text;
	}
}
