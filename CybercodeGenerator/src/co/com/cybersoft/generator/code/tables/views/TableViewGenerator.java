package co.com.cybersoft.generator.code.tables.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Action;
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
	
	private Cybertables cybertables;
	
	public TableViewGenerator(Cybertables cybertables){
		this.cybertables=cybertables;
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
		generateLayout(cybertables);
		generateLanguage(cybertables);
	}
	
	private void generateLanguage(Cybertables cybertables2) {
		// TODO Auto-generated method stub
		
	}

	private void generateLayout(Cybertables cybertables2) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("layout");
		template.setAttribute("module", cybertables.getModuleName());

		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/", "layout"+cybertables.getModuleName()+".html");
	}

	private void generateSetView(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("singletonView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("rows", generateSettingsRows(table, table.getFields()));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		template.setAttribute("module", cybertables.getModuleName());

		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/"+cybertables.getModuleName()+"/"+table.getName(), "set"+CodeUtils.toCamelCase(table.getName())+".html");
	}
	
	

	private void generateSettingsView(Cybertables cybersoft2) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("settings");
				
		template.setAttribute("links", generateSettingsLinks());
		template.setAttribute("module", cybertables.getModuleName());
	
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"normal", cybertables.getModuleName()+"Settings.html");
	}

	private void generateLinksView(Cybertables cybertables){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("viewConfiguration");
		template.setAttribute("firstOption", cybertables.getTables().get(0).getName());
		template.setAttribute("links", generateDataConfigLinks());
		template.setAttribute("module", cybertables.getModuleName());
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"normal", cybertables.getModuleName()+".html");
	}

	private void generateCreateView(Table table){
		
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("createView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
//		template.setAttribute("rows", generateFieldRows(table, table.getFields()));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		template.setAttribute("autocompleteFunctions", generateAutocompleteFunctions(table));
		template.setAttribute("autocompleteReferenceFunctions", generateAutocompleteReferenceFunctions(table));
		template.setAttribute("compoundSelectionFunctions", generateCompoundSelectionFunctions(table));
		template.setAttribute("module", cybertables.getModuleName());
		template.setAttribute("tabsDef", generateTabsDef(table));
		template.setAttribute("tabs", generateTabs(table, "Create"));
		
		
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/"+cybertables.getModuleName()+"/"+table.getName(), "create"+CodeUtils.toCamelCase(table.getName())+".html");
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
				template.setAttribute("module", cybertables.getModuleName());

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
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		template.setAttribute("autocompleteReferenceFunctions", generateAutocompleteReferenceFunctions(table));
		template.setAttribute("modificationCompoundSelectionFunctions", generateCompoundSelectionFunctions(table));
		template.setAttribute("module", cybertables.getModuleName());
		template.setAttribute("tabsDef", generateTabsDef(table));
		template.setAttribute("tabs", generateTabs(table,"Update"));
		
		List<Field> fields = table.getFields();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/"+cybertables.getModuleName()+"/"+table.getName(), "modify"+CodeUtils.toCamelCase(table.getName())+".html");
	}

	private Object generateTabsDef(Table table) {

		if (table.hasFieldGroups()){
			List<String> fieldGroups = table.getFieldGroups();
			StringTemplate template = new StringTemplate(" <ul class=\"nav nav-tabs\">   <li class=\"active\"><a data-toggle=\"tab\" href=\"#$groupId$\">$group$</a></li>");
			String[] split = fieldGroups.get(0).split(" ");
			String groupId="";
			
			for (String string : split) {
				groupId+=string;
			}
			template.setAttribute("group", fieldGroups.get(0));
			template.setAttribute("groupId", groupId);
			
			String defs=template.toString();
			
			for (int i = 1; i < fieldGroups.size(); i++) {
				split = fieldGroups.get(i).split(" ");
				groupId="";
				
				for (String string : split) {
					groupId+=string;
				}
				template = new StringTemplate("<li><a data-toggle=\"tab\" href=\"#$groupId$\">$group$</a></li>");
			
				template.setAttribute("group", fieldGroups.get(i));
				template.setAttribute("groupId", groupId);
				
				defs+=template.toString();
			}
			
			defs+="</ul>";
			
			return defs;
		}
		else{
			return "";
		}
	}

	private Object generateTabs(Table table, String operation) {
		if (table.hasFieldGroups()){
			String tabs="<div class=\"tab-content\">";
			
			List<String> fieldGroups = table.getFieldGroups();
			int i=0;
			for (String string : fieldGroups) {
				StringTemplateGroup templateGroup = new StringTemplateGroup("rows",Cybertables.tableCodePath+"views");
				StringTemplate template = templateGroup.getInstanceOf("tabbedRows");
				
				String[] split = string.split(" ");
				String groupId="";
				
				for (String st : split) {
					groupId+=st;
				}
				template.setAttribute("groupId", groupId);
				if (i==0)
					template.setAttribute("append", "in active");
				
				template.setAttribute("rows", generateTabFieldRows(table, string, operation));
				tabs+=template.toString();
				
				i++;
			}

			tabs+="</div>";
			return tabs;
		}else{
			StringTemplateGroup templateGroup = new StringTemplateGroup("rows",Cybertables.tableCodePath+"views");
			StringTemplate template = templateGroup.getInstanceOf("rows");
			template.setAttribute("rows", generateFieldRows(table, table.getFields(), operation));
			return template.toString();
		}
	}

	private Object generateTabFieldRows(Table table, String fieldGroup, String operation) {
		List<String> fieldGroups = table.getFieldGroups();
		List<Field> fields = table.getFields();
		String rows="";
		List<Field> fieldG=new ArrayList<>();
		for (Field field:fields){
			if (fieldGroup.equals(field.getFieldGroup()))
				fieldG.add(field);
		}
		rows+=generateFieldRows(table,fieldG,operation);
		return rows;
	}

	private void generateSearchView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("backURL", table.getLabelTable()?cybertables.getModuleName()+"Settings":cybertables.getModuleName());
		template.setAttribute("columns", getOtherColumns(table));
		template.setAttribute("filterColumnHeaders", getFilterHeaderColumns(table));
		template.setAttribute("focusCheck", getFocusCheck(table));
		template.setAttribute("columnHeaders", getHeaderColumns(table));
		template.setAttribute("module", cybertables.getModuleName());
		template.setAttribute("actions", getActionDropdown(table));
		
		template.setAttribute("clearFields", getClearFields(table));
		template.setAttribute("excel", table.getLabelTable()?"<div></div>":generateExcelLink(table));
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/"+cybertables.getModuleName()+"/"+table.getName(), "search"+CodeUtils.toCamelCase(table.getName())+".html");
	}
	

	private String getClearFields(Table table) {
		List<Field> fields = table.getFields();
		String clearFields="";
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate stringTemplate = new StringTemplate("if(document.getElementById('$name$')!=undefined)document.getElementById('$name$').value='';\n");
				stringTemplate.setAttribute("name", field.getName());
				clearFields+=stringTemplate.toString();
			}else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate stringTemplate = new StringTemplate("if(document.getElementById('$name$')!=undefined)document.getElementById('$name$').value='';\n");
					stringTemplate.setAttribute("name", compoundField.getName());
					clearFields+=stringTemplate.toString();
				}
			}
		}
		return clearFields;
	}

	private Object getActionDropdown(Table table) {
		if (table.getActions()!=null){
			List<Action> actions = table.getActions();
			StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.tableCodePath+"views");
			StringTemplate template = templateGroup.getInstanceOf("dropdown");
			
			String act="";
			for(Action action: actions){
				StringTemplate template2 = new StringTemplate("<li ><a href=\"#\" tabindex=\"-1\" ><label onclick=\"submitAction('$actionName$');\" >$actionName$</label></a></li>\n");
				template2.setAttribute("actionName", action.getActionName());
				act+=template2.toString();
			}
			template.setAttribute("actions", act);
			return template.toString();
		}
		return "";
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
		template.setAttribute("module", cybertables.getModuleName());

		return template.toString();
	}
	
	private String generateFieldRows(Table table, List<Field> fields, String operation) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
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
				template.setAttribute("operation", operation);

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
				template.setAttribute("operation", operation);
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
					template.setAttribute("operation", operation);
					text+=template.toString()+"\n";
				}
			}
		}
		return text;
	}

	
	private String generateSettingsRows(Table table, List<Field> fields) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		String text="";
		for (Field field : fields) {
			if (!field.isReference() && field.getVisible() && !field.getReadOnly()){
				StringTemplate template;
				if (!field.getLargeText() && !field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableSettingTableRow");
				else if (field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableSettingCheck");
				else
					template = stringTemplateGroup.getInstanceOf("editableSettingTextAreaRow");
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
					template = stringTemplateGroup.getInstanceOf("referenceSettingLabelTableRow");
				}
				else{
					if (CodeUtils.generateAutoCompleteReference(field)){
						template = stringTemplateGroup.getInstanceOf("referenceSettingTableAutocompleteRow");
					}
					else{
						template = stringTemplateGroup.getInstanceOf("referenceSettingTableRow");
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
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceSettingTableRow");
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
		System.out.println("Table: "+table.getName());
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
						if (!field.getAdditionalFields().isEmpty()){
							String additional="";
							List<String> additionalFields = field.getAdditionalFields();
							for (String string : additionalFields) {
								StringTemplate stringTemplate = new StringTemplate("+' - '+\\${object.$fieldName$.$additionalField$}");
								stringTemplate.setAttribute("fieldName", field.getName());
								stringTemplate.setAttribute("additionalField", string);
								additional+=stringTemplate.toString();
							}
							template.setAttribute("additionalFields", additional);
						}
					}
					
					System.out.println("Field name "+field.getName());
					template.setAttribute("fieldName", CodeUtils.getReferenceChain(cybertables, table, field).substring(1));
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
		if (!tables.get(0).getName().equals(CodeUtils.usersTableName) && !tables.get(0).getLabelTable() && !tables.get(0).getSingletonTable()){
			configurationRow.add(tables.get(0));
			i++;
			j++;
		}
		for (; i<tables.size(); i++){
			Table table = tables.get(i);
			if (!table.getName().equals(CodeUtils.usersTableName) && !table.getLabelTable() && !table.getSingletonTable()){
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
		if (!tables.get(0).getName().equals(CodeUtils.usersTableName) && (tables.get(0).getLabelTable() || tables.get(0).getSingletonTable())){
			configurationRow.add(tables.get(0));
			i++;
			j++;
		}
		for (; i<tables.size(); i++){
			Table table = tables.get(i);
			if (!table.getName().equals(CodeUtils.usersTableName) && (table.getLabelTable() || table.getSingletonTable())){
				if (j%Cyberconstants.configPageColumns!=0){
					configurationRow.add(table);
				}
				else{
					links+=generateSettingRow(configurationRow);
					configurationRow.clear();
					configurationRow.add(table);
				}
				j++;
			}
		}
		
		if (!configurationRow.isEmpty()){
			links+=generateSettingRow(configurationRow);					
		}
		
		return links;
	}

	private String generateSettingRow(List<Table> tables) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybertables.tableCodePath+"views");
		StringTemplate confRowTemp = templateGroup.getInstanceOf("configurationRow");
		
		String links="";
		for (Table table : tables) {
				StringTemplate linkTemplate = templateGroup.getInstanceOf("settingLink");
				
				linkTemplate.setAttribute("option", table.getSingletonTable()?"set":"search");
				linkTemplate.setAttribute("tableName",table.getName());
				linkTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				linkTemplate.setAttribute("module", cybertables.getModuleName());
				
				links+=linkTemplate.toString()+"\n";
		}	
		
		confRowTemp.setAttribute("links", links);
		return confRowTemp.toString();
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
				linkTemplate.setAttribute("module", cybertables.getModuleName());
				
				links+=linkTemplate.toString()+"\n";
		}	
		
		confRowTemp.setAttribute("links", links);
		return confRowTemp.toString();
	}
}
