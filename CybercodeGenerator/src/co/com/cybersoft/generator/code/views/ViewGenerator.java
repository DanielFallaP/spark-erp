package co.com.cybersoft.generator.code.views;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Spark;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates all the views
 * @author Daniel Falla
 *
 */
public class ViewGenerator {
	
	private final Spark cybersystems;
	
	public ViewGenerator(Spark cybersoft){
		this.cybersystems=cybersoft;
	}

	public void generate(){
		List<Table> tables = cybersystems.getTables();
		for (Table table : tables) {
			if (!table.getSingletonTable()){
				generateCreateView(table);
				generateModifyView(table);
				generateSearchView(table);
			}
			else{
				generateSetView(table);
			}
		}
		
		generateLinksView(cybersystems);
		generateSettingsView(cybersystems);
	}
	
	private void generateSetView(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("singletonView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("rows", generateFieldRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtil.writeClass(template.toString(), Spark.targetViewPath+"/normal/configuration/"+table.getName(), "set"+CodeUtil.toCamelCase(table.getName())+".html");
	}

	private void generateSettingsView(Spark cybersoft2) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("settings");
		
		List<Table> tables = cybersystems.getTables();
		String links="";
		
		for (Table table : tables) {
			if (table.getLabelTable() || table.getSingletonTable()){
				StringTemplate linkTemplate = templateGroup.getInstanceOf("configurationLink");
				linkTemplate.setAttribute("option", table.getLabelTable()?"search":"set");
				linkTemplate.setAttribute("tableName",table.getName());
				linkTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
				links+=linkTemplate.toString()+"\n";
			}
		}
		
		template.setAttribute("links", links);
		
		CodeUtil.writeClass(template.toString(), Spark.targetViewPath+"normal", "settings.html");
	}

	private void generateCreateView(Table table){
		
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("createView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("rows", generateFieldRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		template.setAttribute("autocompleteFunctions", generateAutocompleteFunctions(table));
		template.setAttribute("compoundSelectionFunctions", generateCompoundSelectionFunctions(table));
		
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtil.writeClass(template.toString(), Spark.targetViewPath+"/normal/configuration/"+table.getName(), "create"+CodeUtil.toCamelCase(table.getName())+".html");
	}
	
	private String generateCompoundSelectionFunctions(Table table) {
		String functions="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				for (int i=0; i< compoundKey.size()-1; i++) {
					Field compoundField = compoundKey.get(i);
					StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
					StringTemplate template = templateGroup.getInstanceOf("compoundSelection");
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					
					functions+=template.toString()+"\n";
				}
			}
		}
		return functions;
	}

	private String generateAutocompleteFunctions(Table table){
		List<Field> fields = table.getFields();
		String functions="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		for (Field field : fields) {
			if (CodeUtil.generateAutoComplete(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("tableName", table.getName());
				template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
				template.setAttribute("arraySeparator", Spark.arraySeparator);

				functions+="\n"+template.toString();
			}
		}
		
		return functions;
	}
	
	private String generateDateFieldPickers(Table table){
		String pickers="";
		List<Field> fields = table.getFields();
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Spark.codePath+"views");
		for (Field field : fields) {
			if (!field.isReference() && field.getType().equals(Spark.dateType)){
				StringTemplate template = templateGroup.getInstanceOf("datePicker");
				template.setAttribute("dateField", field.getName());
				pickers+=template.toString()+"\n";
			}
		}
		
		return pickers;
	}
	
	private void generateModifyView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("modifyView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("rows", generateFieldRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtil.writeClass(template.toString(), Spark.targetViewPath+"/normal/configuration/"+table.getName(), "modify"+CodeUtil.toCamelCase(table.getName())+".html");
	}
	

	private void generateSearchView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("columns", getOtherColumns(table));
		template.setAttribute("columnHeaders", getHeaderColumns(table));
		template.setAttribute("excel", table.getLabelTable()?"<div></div>":generateExcelLink(table));
		
		CodeUtil.writeClass(template.toString(), Spark.targetViewPath+"/normal/configuration/"+table.getName(), "search"+CodeUtil.toCamelCase(table.getName())+".html");
	}
	
	private String generateExcelLink(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("excel");
		template.setAttribute("tableName", table.getName());
		return template.toString();
	}
	
	private String generateFieldRows(Table table) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Spark.codePath+"views");
		List<Field> fields = table.getFields();
		String text="";
		for (Field field : fields) {
			if (!field.isReference() && field.getVisible() && !field.getReadOnly()){
				StringTemplate template;
				if (!field.getLargeText() && !field.getType().equals(Spark.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableTableRow");
				else if (field.getType().equals(Spark.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableCheck");
				else
					template = stringTemplateGroup.getInstanceOf("editableTextAreaRow");
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				if (field.getType().equals(Spark.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				text+=template.toString()+"\n";
			}
			
			if (field.isReference() && !field.getCompoundReference()){
				StringTemplate template;
				if (CodeUtil.referencesLabelTable(field, cybersystems)){
					template = stringTemplateGroup.getInstanceOf("referenceLabelTableRow");
				}
				else{
					template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					if (!field.getRequired()){
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
					}
				}
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				text+=template.toString()+"\n";
			}
			
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				for (Field compoundField: compoundKey) {
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("displayName", compoundField.getName());
					text+=template.toString()+"\n";
				}
			}
		}
		return text;
	}
	
	private String getOtherColumns(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		List<Field> fields = table.getFields();
		String text="";
		for (Field field : fields) {
				if (!field.isReference() && field.getVisible() && !field.getLargeText() && !field.getType().equals(Spark.booleanType)){
					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
				
				if (!field.isReference() && field.getVisible() && !field.getLargeText() && field.getType().equals(Spark.booleanType)){
					StringTemplate template = templateGroup.getInstanceOf("booleanColumn");
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
				
				if (field.isReference()){
					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
					if (CodeUtil.referencesLabelTable(field, cybersystems)){
						template = templateGroup.getInstanceOf("otherLabelTableColumn");
					}
					else{
						template = templateGroup.getInstanceOf("otherColumn");
					}
					
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
		}
		
		
		//Generation of audit fields columns (date of last modification and user of last modification)
		StringTemplate template = templateGroup.getInstanceOf("otherColumn");
		template.setAttribute("fieldName", "dateOfModification");
		text+=template.toString()+"\n";
		
		template = templateGroup.getInstanceOf("otherColumn");
		template.setAttribute("fieldName", "userName");
		text+=template.toString()+"\n";
		
		//Generation of audit fields columns (date of creation and user of creation)
		template = templateGroup.getInstanceOf("otherColumn");
		template.setAttribute("fieldName", "dateOfCreation");
		text+=template.toString()+"\n";

		template = templateGroup.getInstanceOf("otherColumn");
		template.setAttribute("fieldName", "createdBy");
		text+=template.toString()+"\n";
		
		return text;
	}
	
	private String getHeaderColumns(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Spark.codePath+"views");
		List<Field> fields = table.getFields();
		
		String text="";
		for (Field field : fields) {
			if (!field.isReference() && field.getVisible() && !field.getLargeText()){
				StringTemplate template = templateGroup.getInstanceOf("columnHeader");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
			}
			
			if (field.isReference()){
				StringTemplate template = templateGroup.getInstanceOf("columnHeader");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
			}
		}
		
		//Generation of audit fields columns (date of last modification and user of last modification)
		StringTemplate template = templateGroup.getInstanceOf("columnHeader");
		template.setAttribute("fieldName", "dateOfModification");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "dateOfModification");
		text+=template.toString()+"\n";
		
		template = templateGroup.getInstanceOf("columnHeader");
		template.setAttribute("fieldName", "userName");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "userOfModification");
		text+=template.toString()+"\n";
		
		//Generation of audit fields columns (date of creation and user of creation)
		template = templateGroup.getInstanceOf("columnHeader");
		template.setAttribute("fieldName", "dateOfCreation");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "dateOfCreation");
		text+=template.toString()+"\n";
		
		template = templateGroup.getInstanceOf("columnHeader");
		template.setAttribute("fieldName", "createdBy");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "createdBy");
		text+=template.toString()+"\n";
		
		return text;
	}
	
	private void generateLinksView(Spark cybersoft){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Spark.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("viewConfiguration");
		
		List<Table> tables = cybersoft.getTables();
		String links="";
		
		for (Table table : tables) {
			if (!table.getLabelTable() && !table.getSingletonTable()){
				StringTemplate linkTemplate = templateGroup.getInstanceOf("configurationLink");
				linkTemplate.setAttribute("option", "search");
				linkTemplate.setAttribute("tableName",table.getName());
				linkTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
				links+=linkTemplate.toString()+"\n";
			}
		}
		
		template.setAttribute("links", links);
		
		CodeUtil.writeClass(template.toString(), Spark.targetViewPath+"normal", "configuration.html");
	}
}
