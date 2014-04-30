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
		
		String text="";
		for (Field field : fields) {
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", field.isReference()?Cybersoft.stringType:field.getType());
			template.setAttribute("name", field.getName());
			template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			text+=template.toString()+"\n";
		}
		
		return text;
	}
	
	public static Long getMaxNumber(Integer digitNumber){
		return (long) Math.pow(10, digitNumber)-1;
	}
	
	public static String getFieldDeclarations(Table table){
		String text="";
		List<Field> fields = table.getFields();
		
		for (Field field : fields) {
			StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
			fieldTemplate.setAttribute("type", field.isReference()?Cybersoft.stringType:field.getType());
			fieldTemplate.setAttribute("name", field.getName());
			text+=fieldTemplate.toString();
			text+="\n";
		}
		
		return text;
	}
	
	public static boolean referencesLabelTable(String tableName, Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName)){
				if (table.getLabelTable())
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	/**
	 * Gets the name of the label field in a label table
	 * @param table
	 * @return
	 */
	public static String getLabelField(Table table){
		List<Field> fields = table.getFields();
		String labelField="";
		int i=0;
		for (Field field : fields) {
			if (i!=0){
				if (!field.isReference() && field.getType().equals(Cybersoft.stringType)){
					labelField=field.getName();
					break;
				}
			}
			i++;
		}
		return labelField;
	}
	
	public static boolean containsDescriptionField(Table table){
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.isReference() && field.getName().equals("description"))
				return true;
		}
		return false;
	}
	
	public static String getCodeType(Table table){
		return table.getFields().get(0).getType();
	}
	
	public static boolean containsReferences(Table table){
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.isReference())
				return true;
		}
		return false;
	}
	
	public static boolean containsField(Table table, String fieldName){
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)){
				return true;
			}
		}
		return false;
	}
}
