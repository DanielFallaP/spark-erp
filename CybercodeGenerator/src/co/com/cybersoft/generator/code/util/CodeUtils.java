package co.com.cybersoft.generator.code.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.springframework.beans.BeanUtils;

import co.com.cybersoft.generator.code.model.ArithmeticExpression;
import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;

public class CodeUtils {
	
	public static List<Table> allTables;
	
	public static int maxColumnSize=30;
	
	public static String getReferenceChain(Cybertables cybertables, Table table, Field field){
		List<Table> tables = allTables;
		
		String chain="";
		StringTemplate stringTemplate = new StringTemplate(".$reference$");
		stringTemplate.setAttribute("reference", field.getName());
		chain=stringTemplate.toString();
		for (Table tab : tables) {
			if (tab.getName().equals(field.getRefType())){
				List<Field> fields = tab.getFields();
				for (Field field2 : fields) {
					if (field2.getName().equals(field.getDisplayField())){
						if (field2.isReference())
							return chain+getReferenceChain(cybertables, tab, field2);
						else{
							stringTemplate = new StringTemplate(".$reference$");
							stringTemplate.setAttribute("reference", field2.getName());
							return chain+stringTemplate.toString();
						}
					}
				}
			}
		}
		return "";
	}
	
	public static String getGetChain(Cybertables cybertables, Table table, Field field){
		List<Table> tables = cybertables.getTables();
			String chain="";
			StringTemplate stringTemplate = new StringTemplate(".get$upperReference$()");
			stringTemplate.setAttribute("upperReference", CodeUtils.toCamelCase(field.getName()));
			chain=stringTemplate.toString();
			for (Table tab : tables) {
				if (tab.getName().equals(field.getRefType())){
					List<Field> fields = tab.getFields();
					for (Field field2 : fields) {
						if (field2.getName().equals(field.getDisplayField())){
							if (field2.isReference())
								return chain+getGetChain(cybertables, tab, field2);
							else{
								stringTemplate = new StringTemplate(".get$upperFieldName$()");
								stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field2.getName()));
								return chain+stringTemplate.toString();
							}
						}
					}
				}
			}
			return "";
	}

	public final static List<String> reservedSQLWords=Arrays.asList("date","comment","language");

	public static String getTableModule(String tableName){
		for (Table table : allTables) {
			if (table.getName().equals(tableName))
				return table.getModule();
		}
		return null;
	}
	
	public static String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
	
	public static String toLowerCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toLowerCase()+name.substring(1);
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
	
	public static List<String> getFieldNames(List<Field> fields){
		ArrayList<String> names = new ArrayList<String>();
		for (Field field : fields) {
			names.add(field.getName());
		}
		return names;
	}
	
	public static String getGettersAndSetters(Cybertables spark,Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("util",Cybertables.utilCodePath);
		List<Field> fields = table.getFields();
		
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
				if (!field.isReference()){
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", field.getType());
					template.setAttribute("name", field.getName());
					template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
				else{
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");

					template.setAttribute("type", Cybertables.longType);
					template.setAttribute("name", field.getName()+"Id");
					template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()+"Id"));
					text+=template.toString()+"\n";
					
					template=templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", field.getName());
					template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(spark, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
					
					template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.longType);
					template.setAttribute("name", compoundField.getName()+"Id");
					template.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()+"Id"));
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
				if (!field.isReference()){
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", field.getType());
					fieldTemplate.setAttribute("name", field.getName());
					text+=fieldTemplate.toString();
					text+="\n";
				}
				else{
					StringTemplate fieldTemplate = new StringTemplate("private Long $name$Id;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					text+=fieldTemplate.toString();
					text+="\n";
					
					fieldTemplate = new StringTemplate("private String $name$;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					text+=fieldTemplate.toString();
					text+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(spark, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate fieldTemplate = new StringTemplate("private Long $name$Id;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.longType);
					fieldTemplate.setAttribute("name", compoundField.getName());
					text+=fieldTemplate.toString();
					text+="\n";
					
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
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
			return field.getType().equals(Cybertables.stringType);
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
	
	public static boolean containsReferences(Document document){
		List<Field> fields = document.getHeader();
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
		return field.isReference()&&field.getAutocomplete()&&!field.getCompoundReference();
	}
	
	public static boolean generateAutocompleteDocReference(Field field){
		return field.getDocRefType()!=null;
	}
	
	public static boolean generateAutoCompleteReferenceCompoundField(Field field){
		return field.isReference()&&field.getAutocomplete()&&field.getCompoundReference();
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
	
	public static List<Field> getCompoundKey(Cybertables cybertables, String tableName){
		List<Table> tables = cybertables.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName))
				return getCompoundReference(cybertables, tableName);
		}
		return new ArrayList<Field>();
	}
	
	public static List<Field> getCompoundReference(Cybertables cybertables, String tableName){
		List<Table> tables = cybertables.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName)){
					ArrayList<Field> reference = new ArrayList<Field>();
					List<Field> fields = table.getFields();
					for (Field field : fields) {
						if (field.getCompoundReference()&& field.getKeyCompound())
							reference.addAll(getCompoundReference(cybertables, field.getRefType()));
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

	public static String getTextFileContent(String filePath){
		File file = new File(filePath);
	    FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			byte[] data = new byte[(int)file.length()];
			fis.read(data);
			fis.close();
			//
			String s = new String(data, "UTF-8");
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static List<String> getFieldsFromExpression(ArithmeticExpression expression){
		if (expression.getLeftOperand()==null && expression.getRightOperand()==null){
			List<String> fieldsFromExpression = getFieldsFromExpression(expression.getLhs());
			fieldsFromExpression.addAll(getFieldsFromExpression(expression.getRhs()));
			return fieldsFromExpression;
		}
		else{
			if (expression.getLeftOperand()!=null && expression.getRightOperand()!=null){
				return Arrays.asList(expression.getLeftOperand(),expression.getRightOperand());
			}
			else if (expression.getRhs()!=null){
				List<String> fieldsFromExpression = getFieldsFromExpression(expression.getRhs());
				fieldsFromExpression.add(expression.getLeftOperand());
				return fieldsFromExpression;
			}
			else{
				List<String> fieldsFromExpression = getFieldsFromExpression(expression.getLhs());
				fieldsFromExpression.add(expression.getRightOperand());
				return fieldsFromExpression;
			}
				
		}
	}
	
	public static List<Field> getDocReferenceHeaderFields(Document document, Cyberdocs cyberdocs) {
		Field referenceField = document.getHeaderDocReferenceField();
		if (referenceField!=null && !referenceField.getHeaderFields().isEmpty()){
			List<Field> fields = new ArrayList<>();
			List<String> headerFields = referenceField.getHeaderFields();
			for (String headerField : headerFields) {
				fields.add(getDocReferenceHeaderField(cyberdocs, referenceField, headerField));
			}
			return fields;
		}
		return null;
	}
	
	private static Field getDocReferenceHeaderField(Cyberdocs cyberdocs, Field referenceField, String fieldName){
		Document referencedDoc=null;
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			if (document.getName().equals(referenceField.getDocRefType())){
				referencedDoc=document;
				break;
			}
		}
		
		List<Field> header = referencedDoc.getHeader();
		for (Field field : header) {
			if (field.getName().equals(fieldName)){
				if (field.getType()!=null)
					return field;
				else{
					Field field2 = new Field();
					BeanUtils.copyProperties(field, field2);
					field2.setType(Cyberconstants.stringType);
					return field2;
				}
			}
		}
		
		referenceField = referencedDoc.getHeaderDocReferenceField();
		return getDocReferenceHeaderField(cyberdocs, referenceField, fieldName);
	}

	public static List<Field> getDocReferenceBodyFields(Document document, Cyberdocs cyberdocs) {
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null && !referenceField.getBodyFields().isEmpty()){
			List<Field> fields = new ArrayList<>();
			List<String> bodyFields = referenceField.getBodyFields();
			for (String bodyField : bodyFields) {
				fields.add(getDocReferenceBodyField(cyberdocs, referenceField, bodyField));
			}
			return fields;
		}
		return null;
	}
	
	private static Field getDocReferenceBodyField(Cyberdocs cyberdocs, Field referenceField, String fieldName){
		Document referencedDoc=null;
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			if (document.getName().equals(referenceField.getDocRefType())){
				referencedDoc=document;
				break;
			}
		}
		
		List<Field> body = referencedDoc.getBody();
		for (Field field : body) {
			if (field.getName().equals(fieldName)){
				if (field.getRefType()==null)
					return field;
				else{
					Field field2 = new Field();
					BeanUtils.copyProperties(field, field2);
					field2.setType(Cyberconstants.stringType);
					return field2;
				}
			}
		}
		
		referenceField = referencedDoc.getBodyDocReferenceField();
		return getDocReferenceBodyField(cyberdocs, referenceField, fieldName);
	}
	
}
