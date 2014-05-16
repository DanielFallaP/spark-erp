package co.com.cybersoft.generator.code.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersystems;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;

public class CodeUtil {
	
	
	public static String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
	
	public static BufferedWriter initializeErrorFileWriting(String fileName) throws IOException{
		File file = new File(fileName);
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
		return bufferedWriter;
	}
	
	public static BufferedWriter writeErrorLine(String line, BufferedWriter bufferedWriter) throws IOException{
		bufferedWriter.write(line+"\n");
		return bufferedWriter;
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
		StringTemplateGroup templateGroup = new StringTemplateGroup("util",Cybersystems.codePath+"util");
		List<Field> fields = table.getFields();
		
		String text="";
		for (Field field : fields) {
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", field.isReference()?Cybersystems.stringType:field.getType());
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
			fieldTemplate.setAttribute("type", field.isReference()?Cybersystems.stringType:field.getType());
			fieldTemplate.setAttribute("name", field.getName());
			text+=fieldTemplate.toString();
			text+="\n";
		}
		
		return text;
	}
	
	public static boolean referencesLabelTable(Field referenceField, Cybersystems cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (table.getLabelTable() &&table.getName().equals(referenceField.getRefType())){
				List<Field> fields = table.getFields();
				for (Field field : fields) {
					if (field.getName().equals(referenceField.getDisplayField()) && field.getLabelField())
						return true;
				}
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
		for (Field field : fields) {
				if (!field.isReference() && field.getLabelField() && field.getType().equals(Cybersystems.stringType)){
					labelField=field.getName();
					break;
				}
		}
		return labelField;
	}
	
	public static boolean generateQueryForReferences(Field field){
		if (!field.isReference()){
			return field.getUnique()&field.getType().equals(Cybersystems.stringType);
		}
		else if (field.isReference())
			return true;
		else
			return false;
	}
	
	public static boolean generateDescriptionAutocomplete(Table table){
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.isReference() && field.getName().equals("description") && field.getUnique())
				return true;
			if (!field.isReference() && field.getType().equals(Cybersystems.stringType) && field.getUnique() && field.getAutocomplete())
				return true;
		}
		return false;
	}
	
	public static boolean containsDescriptionField(Table table){
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.isReference() && field.getName().equals("description"))
				return true;
		}
		return false;
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
	
	public static boolean containsTable(Cybersystems cybersoft, String tableName){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName))
				return true;
		}
		return false;
	}
	
	public static boolean isGeneratedFile(Cybersystems cybersoft, String fileName){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (fileName.toLowerCase().startsWith(table.getName().toLowerCase()))
				return true;
		}
		return false;
	}
	
	public static String getDefaultName(String camelCaseName){
		if (camelCaseName!=null){
			String name=new Character(camelCaseName.charAt(0)).toString().toUpperCase();
			for (int i = 1; i < camelCaseName.length(); i++) {
				Character ch = camelCaseName.charAt(i);
				if (ch.toString().equals(ch.toString().toUpperCase()))
					name+=" ";
				name+=ch.toString();
			}
			return name;
		}
		else{
			return null;
		}
	}
	
	public static List<String> getTableNames(Cybersystems cybersoft){
		List<String> tableNames = new ArrayList<String>();
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			tableNames.add(table.getName());
		}
		return tableNames;
	}
	
	public static String getDefaultValue(Field field){
		try {
			if (field.getType().equals(Cybersystems.booleanType)){
				Boolean.parseBoolean(field.getDefaultValue());
				return field.getDefaultValue();
			}
			else if (field.getType().equals(Cybersystems.stringType))
				return "\""+field.getDefaultValue()+"\"";
			else if (field.getType().equals(Cybersystems.longType)){
				Long.parseLong(field.getDefaultValue());
				return field.getDefaultValue()+"L";
			}
			else if (field.getType().equals(Cybersystems.doubleType)){
				Double.parseDouble(field.getDefaultValue());
				return field.getDefaultValue()+"D";
			}
			else if (field.getType().equals(Cybersystems.integerType)){
				Integer.parseInt(field.getDefaultValue());
				return field.getDefaultValue();
			}
			else if (field.getType().equals(Cybersystems.dateType)){
				if (field.getDefaultValue().toLowerCase().equals(Cybersystems.todayValue)){
					return "new Date()";
				}
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
				Date date = simpleDateFormat.parse(field.getDefaultValue());
				String time=((Long)date.getTime()).toString();
				return "new Date("+time+"L)";
			}else{
				throw new Exception("Invalid field format");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static boolean generateAutoComplete(Field field){
		return !field.isReference()&&field.getUnique()&&field.getAutocomplete()&&field.getType().equals(Cybersystems.stringType);
	}
	
}
