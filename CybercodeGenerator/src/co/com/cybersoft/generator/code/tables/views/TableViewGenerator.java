package co.com.cybersoft.generator.code.tables.views;

import java.util.ArrayList;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

/**
 * Generates all the views
 * @author Daniel Falla
 *
 */
public class TableViewGenerator {
	
	private final Cybertables cybertables;
	
	public TableViewGenerator(Cybertables cybersoft){
		this.cybertables=cybersoft;
	}

	public void generate(){
		List<Table> tables = cybertables.getTables();
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
		
		generateLinksView(cybertables);
		generateSettingsView(cybertables);
	}
	
	private void generateSetView(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("singletonView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("rows", generateFieldRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/configuration/"+table.getName(), "set"+CodeUtils.toCamelCase(table.getName())+".html");
	}

	private void generateSettingsView(Cybertables cybersoft2) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("settings");
				
		template.setAttribute("links", generateSettingsLinks());
				
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"normal", "settings.html");
	}

	private void generateLinksView(Cybertables cybertables){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("viewConfiguration");
		template.setAttribute("firstOption", cybertables.getTables().get(0).getName());
		template.setAttribute("links", generateDataConfigLinks());
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"normal", "configuration.html");
	}

	private void generateCreateView(Table table){
		
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("createView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("rows", generateFieldRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		template.setAttribute("autocompleteFunctions", generateAutocompleteFunctions(table));
		template.setAttribute("autocompleteReferenceFunctions", generateAutocompleteReferenceFunctions(table));
		template.setAttribute("compoundSelectionFunctions", generateCompoundSelectionFunctions(table));
		
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/configuration/"+table.getName(), "create"+CodeUtils.toCamelCase(table.getName())+".html");
	}
	

	private String generateCompoundSelectionFunctions(Table table) {
		String functions="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (int i=0; i< compoundKey.size()-1; i++) {
					Field compoundField = compoundKey.get(i);
					StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
					StringTemplate template = templateGroup.getInstanceOf("compoundSelection");
					template.setAttribute("field", compoundField.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("compoundReference", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("setOptions", generateSelectFunctions(field, i+1));
					
					functions+=template.toString()+"\n";
				}
			}
		}
		return functions;
	}

	private Object generateAutocompleteReferenceFunctions(Table table) {
		List<Field> fields = table.getFields();
		String functions="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.utilCodePath);
		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);
				template.setAttribute("namespace", Cybertables.tableNamespace);


				functions+="\n"+template.toString();
			}
		}
		
		return functions;
	}

	private String generateSelectFunctions(Field compoundField, int i) {
		List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, compoundField.getRefType());
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		String functions="";
		for (;i<compoundKey.size();i++) {
			StringTemplate template = templateGroup.getInstanceOf("selectOptions");
			Field field = compoundKey.get(i);
			template.setAttribute("field", field.getName());
			functions+=template.toString()+"\n";
		}
		return functions;
	}

	private String generateAutocompleteFunctions(Table table){
		List<Field> fields = table.getFields();
		String functions="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		for (Field field : fields) {
			if (CodeUtils.generateAutoComplete(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("tableName", table.getName());
				template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);

				functions+="\n"+template.toString();
			}
		}
		
		return functions;
	}
	
	private String generateDateFieldPickers(Table table){
		String pickers="";
		List<Field> fields = table.getFields();
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.utilCodePath);
		for (Field field : fields) {
			if (!field.isReference() && field.getType().equals(Cybertables.dateType)){
				StringTemplate template = templateGroup.getInstanceOf("datePicker");
				template.setAttribute("dateField", field.getName());
				pickers+=template.toString()+"\n";
			}
		}
		
		return pickers;
	}
	
	private void generateModifyView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("modifyView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("rows", generateFieldRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		template.setAttribute("autocompleteReferenceFunctions", generateAutocompleteReferenceFunctions(table));
		template.setAttribute("modificationCompoundSelectionFunctions", generateCompoundSelectionFunctions(table));

		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/configuration/"+table.getName(), "modify"+CodeUtils.toCamelCase(table.getName())+".html");
	}

	private void generateSearchView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("backURL", table.getLabelTable()?Cybertables.settingsURL:Cybertables.tableNamespace);
		template.setAttribute("columns", getOtherColumns(table));
		template.setAttribute("filterColumnHeaders", getFilterHeaderColumns(table));
		template.setAttribute("focusCheck", getFocusCheck(table));
		template.setAttribute("columnHeaders", getHeaderColumns(table));

		template.setAttribute("excel", table.getLabelTable()?"<div></div>":generateExcelLink(table));
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/configuration/"+table.getName(), "search"+CodeUtils.toCamelCase(table.getName())+".html");
	}
	

	private Object getFocusCheck(Table table) {
		String focusCheck="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			StringTemplate template=new StringTemplate(" || document.getElementById(\"$fieldName$\") === document.activeElement");
			if (!field.getCompoundReference()){
				template.setAttribute("fieldName", field.getName());
				focusCheck+=template.toString()+"\n";
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					template=new StringTemplate(" || document.getElementById(\"$fieldName$\") === document.activeElement");
					template.setAttribute("fieldName", compoundField.getName());
					focusCheck+=template.toString()+"\n";
				}
			}
		}
		return focusCheck;
	}

	private String generateExcelLink(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("excel");
		template.setAttribute("tableName", table.getName());
		return template.toString();
	}
	
	private String generateFieldRows(Table table) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		List<Field> fields = table.getFields();
		String text="";
		for (Field field : fields) {
			if (!field.isReference() && field.getVisible() && !field.getReadOnly()){
				StringTemplate template;
				if (!field.getLargeText() && !field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableTableRow");
				else if (field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableCheck");
				else
					template = stringTemplateGroup.getInstanceOf("editableTextAreaRow");
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				text+=template.toString()+"\n";
			}
			
			if (field.isReference() && !field.getCompoundReference()){
				StringTemplate template;
				if (CodeUtils.referencesLabelTable(field, cybertables)){
					template = stringTemplateGroup.getInstanceOf("referenceLabelTableRow");
				}
				else{
					if (CodeUtils.generateAutoCompleteReference(field)){
						template = stringTemplateGroup.getInstanceOf("referenceTableAutocompleteRow");
					}
					else{
						template = stringTemplateGroup.getInstanceOf("referenceTableRow");
						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
						}
					}
				}
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				text+=template.toString()+"\n";
			}
			
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField: compoundKey) {
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("displayName", compoundField.getName());
					text+=template.toString()+"\n";
				}
			}
		}
		return text;
	}
	
	private String getOtherColumns(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		List<Field> fields = table.getFields();
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && field.getDisplayable()){
				if (!field.isReference() && field.getVisible() && !field.getLargeText() && !field.getType().equals(Cybertables.booleanType)){
					StringTemplate template;
					if (field.getType().equals(Cybertables.dateType)){
						template = templateGroup.getInstanceOf("otherDateColumn");
					}
					else{
						template = templateGroup.getInstanceOf("otherColumn");
					}
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
				
				if (!field.isReference() && field.getVisible() && !field.getLargeText() && field.getType().equals(Cybertables.booleanType)){
					StringTemplate template = templateGroup.getInstanceOf("booleanColumn");
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
				if (field.isReference()){
					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
					if (CodeUtils.referencesLabelTable(field, cybertables)){
						template = templateGroup.getInstanceOf("otherLabelTableColumn");
					}
					else{
						template = templateGroup.getInstanceOf("otherColumn");
					}
					
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
					template.setAttribute("fieldName", compoundField.getName());
					text+=template.toString()+"\n";
				}
			}
		}
		
		
		//Generation of audit fields columns (date of last modification and user of last modification)
		StringTemplate template = templateGroup.getInstanceOf("readOnlyDateColumn");
		template.setAttribute("fieldName", "dateOfModification");
		text+=template.toString()+"\n";
		
		template = templateGroup.getInstanceOf("otherColumn");
		template.setAttribute("fieldName", "userName");
		text+=template.toString()+"\n";
		
		//Generation of audit fields columns (date of creation and user of creation)
		template = templateGroup.getInstanceOf("readOnlyDateColumn");
		template.setAttribute("fieldName", "dateOfCreation");
		text+=template.toString()+"\n";

		template = templateGroup.getInstanceOf("otherColumn");
		template.setAttribute("fieldName", "createdBy");
		text+=template.toString()+"\n";
		
		return text;
	}
	
	private Object getFilterHeaderColumns(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		List<Field> fields = table.getFields();
		
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && field.getDisplayable()){
				if (!field.isReference() && field.getVisible() && !field.getLargeText()){
					StringTemplate template = templateGroup.getInstanceOf("filterColumnHeader");
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
				
				if (field.isReference()){
					StringTemplate template = templateGroup.getInstanceOf("filterColumnHeader");
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("filterColumnHeader");
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
				}
			}
		}
		
		//Generation of audit fields columns (date of last modification and user of last modification)
		StringTemplate template = templateGroup.getInstanceOf("filterColumnHeader");
		template.setAttribute("fieldName", "dateOfModification");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "dateOfModification");
		text+=template.toString()+"\n";
		
		template = templateGroup.getInstanceOf("filterColumnHeader");
		template.setAttribute("fieldName", "userName");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "userOfModification");
		text+=template.toString()+"\n";
		
		//Generation of audit fields columns (date of creation and user of creation)
		template = templateGroup.getInstanceOf("filterColumnHeader");
		template.setAttribute("fieldName", "dateOfCreation");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "dateOfCreation");
		text+=template.toString()+"\n";
		
		template = templateGroup.getInstanceOf("filterColumnHeader");
		template.setAttribute("fieldName", "createdBy");
		template.setAttribute("tableName", "");
		template.setAttribute("upperFieldName", "createdBy");
		text+=template.toString()+"\n";
		
		return text;
	}
	
	private String getHeaderColumns(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		List<Field> fields = table.getFields();
		
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && field.getDisplayable()){
				if (!field.isReference() && field.getVisible() && !field.getLargeText()){
					StringTemplate template = templateGroup.getInstanceOf("columnHeader");
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
				
				if (field.isReference()){
					StringTemplate template = templateGroup.getInstanceOf("columnHeader");
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("columnHeader");
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("tableName", table.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
				}
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
	

	private String generateDataConfigLinks() {
		String links="";
		List<Table> tables = cybertables.getTables();
		List<Table> configurationRow = new ArrayList<Table>();
		int i=0;
		int j=0;
		if (!tables.get(0).getLabelTable() && !tables.get(0).getSingletonTable()){
			configurationRow.add(tables.get(0));
			i++;
			j++;
		}
		for (; i<tables.size(); i++){
			Table table = tables.get(i);
			if (!table.getLabelTable() && !table.getSingletonTable()){
				if (j%Cyberconstants.configPageColumns!=0){
					configurationRow.add(table);
				}
				else{
					links+=generateConfigurationRow(configurationRow);
					configurationRow.clear();
					configurationRow.add(table);
				}
				j++;
			}
		}
		
		if (!configurationRow.isEmpty()){
			links+=generateConfigurationRow(configurationRow);					
		}
		
		return links;
	}
	
	private String generateSettingsLinks() {
		String links="";
		List<Table> tables = cybertables.getTables();
		List<Table> configurationRow = new ArrayList<Table>();
		int i=0;
		int j=0;
		if (tables.get(0).getLabelTable() || tables.get(0).getSingletonTable()){
			configurationRow.add(tables.get(0));
			i++;
			j++;
		}
		for (; i<tables.size(); i++){
			Table table = tables.get(i);
			if (table.getLabelTable() || table.getSingletonTable()){
				if (j%Cyberconstants.configPageColumns!=0){
					configurationRow.add(table);
				}
				else{
					links+=generateConfigurationRow(configurationRow);
					configurationRow.clear();
					configurationRow.add(table);
				}
				j++;
			}
		}
		
		if (!configurationRow.isEmpty()){
			links+=generateConfigurationRow(configurationRow);					
		}
		
		return links;
	}

	private String generateConfigurationRow(List<Table> tables) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		StringTemplate confRowTemp = templateGroup.getInstanceOf("configurationRow");
		
		String links="";
		for (Table table : tables) {
				StringTemplate linkTemplate = templateGroup.getInstanceOf("configurationLink");
				
				linkTemplate.setAttribute("option", table.getSingletonTable()?"set":"search");
				linkTemplate.setAttribute("tableName",table.getName());
				linkTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				links+=linkTemplate.toString()+"\n";
		}	
		
		confRowTemp.setAttribute("links", links);
		return confRowTemp.toString();
	}
}
