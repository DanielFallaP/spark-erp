package co.com.cybersoft.generator.code.docs.views;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class DocViewGenerator {

	private final Cyberdocs cyberdocs;

	private final Cybertables cybertables;

	public DocViewGenerator(Cyberdocs cyberdocs, Cybertables cybertables){
		this.cyberdocs=cyberdocs;
		this.cybertables=cybertables;
	}
	
	public void generate(){
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			generateSearchView(document);
			generateSaveView(document);
		}
	}

	private void generateSaveView(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("saveView");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("rows", generateFieldRows(document));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(document));
		template.setAttribute("autocompleteReferenceFunctions", generateAutocompleteReferenceFunctions(document));
		template.setAttribute("modificationCompoundSelectionFunctions", generateCompoundSelectionFunctions(document));
		template.setAttribute("setModificationFormFunction", generateModificationFormValues(document));
		template.setAttribute("additionFormFields", generateAdditionFormFields(document));
		template.setAttribute("modificationFormFields", generateModificationFormFields(document));
		template.setAttribute("bodyHeaderFields", generateBodyHeaderFields(document));
		template.setAttribute("bodyFields", generateBodyFields(document));
		template.setAttribute("headerFields", generateHeaderFields(document));
		
		List<Field> fields = document.getHeader();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "save"+CodeUtil.toCamelCase(document.getName())+".html");
	}

	private String generateHeaderFields(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateBodyFields(Document document) {
		String headers="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> body = document.getBody();
		
		for (Field field : body) {
			if (!CodeUtil.referencesLabelTable(field, cybertables)){
				StringTemplate template = templateGroup.getInstanceOf("bodyColumn");
				template.setAttribute("fieldName", field.getName());
				headers+=template.toString();
			}
			else{
				StringTemplate template = templateGroup.getInstanceOf("labelBodyColumn");
				template.setAttribute("fieldName", field.getName());
				headers+=template.toString();
			}
		}
		
		return headers;
	}

	private String generateBodyHeaderFields(Document document) {
		String headers="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> body = document.getBody();
		
		for (Field field : body) {
			StringTemplate template = templateGroup.getInstanceOf("headerBodyColumn");
			template.setAttribute("docName", document.getName());
			template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
			headers+=template.toString();
		}
		
		return headers;
	}

	private String generateModificationFormFields(Document document) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybertables.documentCodePath+"views");
		List<Field> fields = document.getBody();
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
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("fieldNameId", field.getName()+"Modification");
				template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
				template.setAttribute("modificationPrefix", "_");
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				text+=template.toString()+"\n";
			}
			
			if (field.isReference() && !field.getCompoundReference()){
				StringTemplate template;
				if (CodeUtil.referencesLabelTable(field, cybertables)){
					template = stringTemplateGroup.getInstanceOf("referenceLabelTableRow");
				}
				else{
					if (CodeUtil.generateAutoCompleteReference(field)){
						template = stringTemplateGroup.getInstanceOf("referenceTableAutocompleteRow");
					}
					else{
						template = stringTemplateGroup.getInstanceOf("referenceTableRow");
						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				text+=template.toString()+"\n";
			}
			
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField: compoundKey) {
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("displayName", compoundField.getName());
					text+=template.toString()+"\n";
				}
			}
		}
		return text;
	}

	private String generateAdditionFormFields(Document document) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybertables.documentCodePath+"views");
		List<Field> fields = document.getBody();
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
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("fieldNameId", field.getName());
				template.setAttribute("fieldNamePath", field.getName());
				template.setAttribute("modificationPrefix", "");
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				text+=template.toString()+"\n";
			}
			
			if (field.isReference() && !field.getCompoundReference()){
				StringTemplate template;
				if (CodeUtil.referencesLabelTable(field, cybertables)){
					template = stringTemplateGroup.getInstanceOf("referenceLabelTableRow");
				}
				else{
					if (CodeUtil.generateAutoCompleteReference(field)){
						template = stringTemplateGroup.getInstanceOf("referenceTableAutocompleteRow");
					}
					else{
						template = stringTemplateGroup.getInstanceOf("referenceTableRow");
						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				text+=template.toString()+"\n";
			}
			
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField: compoundKey) {
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
					template.setAttribute("fieldName", compoundField.getName());
					template.setAttribute("displayName", compoundField.getName());
					text+=template.toString()+"\n";
				}
			}
		}
		return text;
	}

	private String generateModificationFormValues(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("setModificationFormFunction");
		template.setAttribute("docName", document.getName());
		
		String fieldValues="";
		
		List<Field> body = document.getBody();
		
		int i=1;
		for (Field field : body) {
			StringTemplate template2 = templateGroup.getInstanceOf("fieldValue");
			template2.setAttribute("i", i);
			template2.setAttribute("fieldName", document.getName()+"BodyModificationInfo\\\\."+field.getName());
			fieldValues+=template2.toString();
			i++;
		}
		
		template.setAttribute("fieldValues", fieldValues);
		
		return template.toString();
	}

	private String generateCompoundSelectionFunctions(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateAutocompleteReferenceFunctions(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateDateFieldPickers(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateFieldRows(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private void generateSearchView(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "search"+CodeUtil.toCamelCase(document.getName())+".html");
	}
}
