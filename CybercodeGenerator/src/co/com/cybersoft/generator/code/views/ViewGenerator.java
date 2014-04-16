package co.com.cybersoft.generator.code.views;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.ReferenceField;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates all the views
 * @author Daniel Falla
 *
 */
public class ViewGenerator {

	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateCreateView(table);
			generateModifyView(table);
			generateSearchView(table);
		}
		
		generateLinksView(cybersoft);
	}
	
	private void generateCreateView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybersoft.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("createView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("editableTableRows", generateEditableRows(table));
		template.setAttribute("editableReferenceRows", generateReferenceRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetViewPath+"/normal/configuration/"+table.getName(), "create"+CodeUtil.toCamelCase(table.getName())+".html");
	}
	
	private String generateDateFieldPickers(Table table){
		String pickers="";
		List<Field> fields = table.getFields();
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybersoft.codePath+"views");
		for (Field field : fields) {
			if (field.getType().equals(Cybersoft.dateType)){
				StringTemplate template = templateGroup.getInstanceOf("datePicker");
				template.setAttribute("dateField", field.getName());
				pickers+=template.toString()+"\n";
			}
		}
		
		return pickers;
	}
	
	private void generateModifyView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybersoft.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("modifyView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("editableTableRows", generateEditableRows(table));
		template.setAttribute("editableReferenceRows", generateReferenceRows(table));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(table));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetViewPath+"/normal/configuration/"+table.getName(), "modify"+CodeUtil.toCamelCase(table.getName())+".html");
	}
	
	private void generateSearchView(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybersoft.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("codeColumn", getCodeColumn(table));
		template.setAttribute("otherColumns", getOtherColumns(table));
		template.setAttribute("columnHeaders", getHeaderColumns(table));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetViewPath+"/normal/configuration/"+table.getName(), "search"+CodeUtil.toCamelCase(table.getName())+".html");
	}
	
	private String generateEditableRows(Table table){
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybersoft.codePath+"views");
		List<Field> fields = table.getFields();
	
		String text="";
		for (Field field : fields) {
			if (field.getVisible()){
				StringTemplate template;
				if (!field.getLargeText())
					template = stringTemplateGroup.getInstanceOf("editableTableRow");
				else
					template = stringTemplateGroup.getInstanceOf("editableTextAreaRow");
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				if (field.getType().equals(Cybersoft.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				text+=template.toString()+"\n";
			}
		}
		
		return text;
	}
	
	private String generateReferenceRows(Table table){
		String text="";
		
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybersoft.codePath+"views");
		List<ReferenceField> fields = table.getReferences();
		for (ReferenceField field : fields) {
			StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
			template.setAttribute("tableName", table.getName());
			template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
			template.setAttribute("fieldName", field.getName());
			template.setAttribute("displayName", field.getDisplayField());
			text+=template.toString()+"\n";
		}
		
		return text;
	}
	
	private String getCodeColumn(Table table){
		
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybersoft.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("codeColumn");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		return template.toString();
	}
	
	private String getOtherColumns(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybersoft.codePath+"views");
		List<Field> fields = table.getFields();
		List<ReferenceField> references = table.getReferences();
		String text="";
		int i=0;
		for (Field field : fields) {
			if (i!=0){
				if (field.getVisible() && !field.getLargeText()){
					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
			}
			i++;
		}
		
		for (ReferenceField field : references) {
			StringTemplate template = templateGroup.getInstanceOf("otherColumn");
			template.setAttribute("fieldName", field.getName());
			text+=template.toString()+"\n";
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
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybersoft.codePath+"views");
		List<Field> fields = table.getFields();
		List<ReferenceField> references = table.getReferences();
		
		String text="";
		for (Field field : fields) {
			if (field.getVisible() && !field.getLargeText()){
				StringTemplate template = templateGroup.getInstanceOf("columnHeader");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("tableName", table.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
			}
		}
		
		for (ReferenceField field : references) {
			StringTemplate template = templateGroup.getInstanceOf("columnHeader");
			template.setAttribute("fieldName", field.getName());
			template.setAttribute("tableName", table.getName());
			template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
			text+=template.toString()+"\n";
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
	
	private void generateLinksView(Cybersoft cybersoft){
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybersoft.codePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("viewConfiguration");
		
		List<Table> tables = cybersoft.getTables();
		String links="";
		
		for (Table table : tables) {
			StringTemplate linkTemplate = templateGroup.getInstanceOf("configurationLink");
			
			linkTemplate.setAttribute("tableName",table.getName());
			linkTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			links+=linkTemplate.toString()+"\n";
		}
		
		template.setAttribute("links", links);
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetViewPath+"normal", "configuration.html");
	}
}
