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

import co.com.cybersoft.generator.code.model.Cybertables;
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
	
	public static String getGettersAndSetters(Cybertables spark,Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("util",Cybertables.utilCodePath);
		List<Field> fields = table.getFields();
		
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
				
				if (field.isEmbeddedReference()){
					StringTemplate temp = templateGroup.getInstanceOf("getterSetter");
					temp.setAttribute("type", CodeUtil.toCamelCase(field.getRefType())+"Details");
					temp.setAttribute("name", field.getName()+"Details");
					temp.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName())+"Details");
					text+=temp.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(spark, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
				}
			}
		}
		
		return text;
	}
	
	public static Long getMaxNumber(Integer digitNumber){
		return (long) Math.pow(10, digitNumber)-1;
	}
	
	public static String getFieldDeclarations(Cybertables spark, Table table){
		String text="";
		List<Field> fields = table.getFields();
		
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
				fieldTemplate.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				fieldTemplate.setAttribute("name", field.getName());
				text+=fieldTemplate.toString();
				text+="\n";
				
				if (field.isEmbeddedReference()){
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", CodeUtil.toCamelCase(field.getRefType())+"Details");
					fieldTemplate.setAttribute("name", field.getName()+"Details");
					text+=fieldTemplate.toString();
					text+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(spark, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.stringType);
					fieldTemplate.setAttribute("name", compoundField.getName());
					text+=fieldTemplate.toString();
					text+="\n";
				}
			}
		}
		
		return text;
	}
	
	public static boolean referencesLabelTable(Field referenceField, Cybertables cybersoft){
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
				if (!field.isReference() && field.getLabelField() && field.getType().equals(Cybertables.stringType)){
					labelField=field.getName();
					break;
				}
		}
		return labelField;
	}
	
	public static boolean generateQueryForReferences(Field field){
		if (!field.isReference()&&!field.getCompoundReference()){
			return field.getUnique()&field.getType().equals(Cybertables.stringType);
		}
		else if (field.isReference()&&!field.getCompoundReference())
			return true;
		else
			return false;
	}
	
	public static boolean generateDescriptionAutocomplete(Table table){
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.isReference() && field.getName().equals("description") && field.getUnique())
				return true;
			if (!field.isReference() && field.getType().equals(Cybertables.stringType) && field.getUnique() && field.getAutocomplete())
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
			if (!field.getCompoundReference() && field.getName().equals(fieldName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsTable(Cybertables cybersoft, String tableName){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName))
				return true;
		}
		return false;
	}
	
	public static boolean isGeneratedFile(Cybertables cybersoft, String fileName){
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
	
	public static List<String> getTableNames(Cybertables cybersoft){
		List<String> tableNames = new ArrayList<String>();
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			tableNames.add(table.getName());
		}
		return tableNames;
	}
	
	public static String getDefaultValue(Field field){
		try {
			if (field.getType().equals(Cybertables.booleanType)){
				Boolean.parseBoolean(field.getDefaultValue());
				return field.getDefaultValue();
			}
			else if (field.getType().equals(Cybertables.stringType))
				return "\""+field.getDefaultValue()+"\"";
			else if (field.getType().equals(Cybertables.longType)){
				Long.parseLong(field.getDefaultValue());
				return field.getDefaultValue()+"L";
			}
			else if (field.getType().equals(Cybertables.doubleType)){
				Double.parseDouble(field.getDefaultValue());
				return field.getDefaultValue()+"D";
			}
			else if (field.getType().equals(Cybertables.integerType)){
				Integer.parseInt(field.getDefaultValue());
				return field.getDefaultValue();
			}
			else if (field.getType().equals(Cybertables.dateType)){
				if (field.getDefaultValue().toLowerCase().equals(Cybertables.todayValue)){
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
		return !field.isReference()&&field.getUnique()&&field.getAutocomplete()&&field.getType().equals(Cybertables.stringType);
	}
	
	public static boolean generateAutoCompleteReference(Field field){
		return field.isReference()&&field.getAutocomplete();
	}
	
	public static String getFieldType(Cybertables cybersystems, String tableName, String fieldName){
		List<Table> tables = cybersystems.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName)){
				List<Field> fields = table.getFields();
				for (Field field : fields) {
					if (field.getName().equals(fieldName))
						return field.getType();
				}
			}
		}
		return "";
	}
	
	public static List<Field> getCompoundKey(Cybertables spark, String tableName){
		List<Table> tables = spark.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName))
				return getCompoundReference(spark, tableName);
		}
		return new ArrayList<Field>();
	}

	public static List<Field> getCompoundReference(Cybertables spark, String tableName){
		List<Table> tables = spark.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName)){
					ArrayList<Field> reference = new ArrayList<Field>();
					List<Field> fields = table.getFields();
					for (Field field : fields) {
						if (field.getCompoundReference()&& field.getKeyCompound())
							reference.addAll(getCompoundReference(spark, field.getRefType()));
						else if(field.getKeyCompound()){
							field.setTableName(field.getRefType()==null?table.getName():field.getRefType());
							reference.add(field);
						}
					}
					return reference;
			}
		}
		return new ArrayList<Field>();
	}
	
}
