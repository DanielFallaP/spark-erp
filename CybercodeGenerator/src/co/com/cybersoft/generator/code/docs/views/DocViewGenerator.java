package co.com.cybersoft.generator.code.docs.views;

import java.util.ArrayList;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberconstants;
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
		template.setAttribute("datePickers", generateDateFieldPickers(document));
		template.setAttribute("modificationCompoundSelectionFunctions", generateCompoundSelectionFunctions(document));
		template.setAttribute("setModificationFormFunction", generateModificationFormValues(document));
		template.setAttribute("additionFormFields", generateAdditionFormFields(document));
		template.setAttribute("modificationFormFields", generateModificationFormFields(document));
		template.setAttribute("bodyHeaderFields", generateBodyHeaderFields(document));
		template.setAttribute("bodyFields", generateBodyFields(document));
		template.setAttribute("headerFields", generateHeaderFields(document));
		template.setAttribute("autoCompleteFunctions", generateAutocompleteReferenceFunctions(document));
		
		List<Field> fields = document.getHeader();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "save"+CodeUtil.toCamelCase(document.getName())+".html");
	}

	private String generateAutocompleteReferenceFunctions(Document document) {
		List<Field> fields = document.getHeader();
		String functions="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.utilCodePath);
		for (Field field : fields) {
			if (CodeUtil.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtil.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);

				functions+="\n"+template.toString();
			}
		}
		
		fields=document.getBody();
		for (Field field : fields) {
			if (CodeUtil.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtil.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);

				functions+="\n"+template.toString();
				
				template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", document.getName()+"BodyModificationInfo\\\\."+field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtil.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);

				functions+="\n"+template.toString();
			}
		}
		
		return functions;
	}

	private String generateHeaderFields(Document document) {
		String headerFields="";
		List<Field> header = document.getHeader();
		List<Field> headerRow=new ArrayList<Field>();
		headerRow.add(header.get(0));
		for (int i=1;i<header.size();i++) {
			Field field=header.get(i);
			if (i%Cyberconstants.headerColumnsPerRow!=0){
				headerRow.add(field);
			}
			else{
				headerFields+=generateHeaderRow(headerRow, document);
				headerRow.clear();
				headerRow.add(field);
			}
		}
		
		if (headerRow.size()!=0)
			headerFields+=generateHeaderRow(headerRow, document);
		return headerFields;
	}

	private String generateHeaderRow(List<Field> headerRow, Document document) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate headerRowTemplate = stringTemplateGroup.getInstanceOf("headerFieldRow");
		String exceptions="";
		String fields="";
		for (Field field : headerRow) {
			StringTemplate temp = stringTemplateGroup.getInstanceOf("headerFieldException");
			temp.setAttribute("fieldName", field.getName());
			exceptions+=temp.toString();
			
			StringTemplate template;
			if (!field.isReference() && field.getVisible() && !field.getReadOnly()){
				if (!field.getLargeText() && !field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableHeaderField");
				else if (field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableHeaderCheckField");
				else
					template = stringTemplateGroup.getInstanceOf("editableHeaderTextAreaField");
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				fields+=template.toString()+"\n";
			}
			
			if (field.isReference() && !field.getCompoundReference()){
				if (CodeUtil.referencesLabelTable(field, cybertables)){
					template = stringTemplateGroup.getInstanceOf("referenceHeaderLabelField");
				}
				else{
					if (CodeUtil.generateAutoCompleteReference(field)){
						template = stringTemplateGroup.getInstanceOf("referenceHeaderAutocompleteRow");
					}
					else{
						template = stringTemplateGroup.getInstanceOf("referenceHeaderField");
//						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
//						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				fields+=template.toString()+"\n";
			}
			
			//TODO compound reference
//			if (field.getCompoundReference()){
//				List<Field> compoundKey = CodeUtil.getCompoundKey(cybertables, field.getRefType());
//				for (Field compoundField: compoundKey) {
//					template = stringTemplateGroup.getInstanceOf("referenceTableRow");
//					template.setAttribute("docName", document.getName());
//					template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
//					template.setAttribute("fieldName", compoundField.getName());
//					template.setAttribute("displayName", compoundField.getName());
//					fields+=template.toString()+"\n";
//				}
//			}
		}
		
		headerRowTemplate.setAttribute("fieldsExceptions", exceptions);
		headerRowTemplate.setAttribute("fields", fields);
		headerRowTemplate.setAttribute("rowExceptions", generateExceptionDisjunction(headerRow));

		return headerRowTemplate.toString();
	}

	private String generateExceptionDisjunction(List<Field> headerRow) {
		String disjunction="";
		int i=0;
		for (Field field : headerRow) {
			StringTemplate stringTemplate = new StringTemplate("\\${$fieldName$Exception}");
			stringTemplate.setAttribute("fieldName", field.getName());
			disjunction+=stringTemplate.toString();
			if (i!=headerRow.size()-1)
				disjunction+=" or ";
			i++;
		}
		return disjunction;
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
//						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
//						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				template.setAttribute("fieldNameId", field.getName()+"Modification");
				template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());

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
//						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
//						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				template.setAttribute("fieldNamePath", field.getName());
				template.setAttribute("fieldNameId", field.getName());
				
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
			if (field.getType()!=null)
				template2.setAttribute("varAssignment", field.getType().equals(Cyberconstants.booleanType)?"$(field).prop('checked', $(this).html()==\"true\");":"$(field).val($(this).html());");
			else
				template2.setAttribute("varAssignment", "$(field).val($(this).html());");
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

	private String generateDateFieldPickers(Document document) {
		String pickers="";
		List<Field> fields = document.getAllFields();
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

	private void generateSearchView(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "search"+CodeUtil.toCamelCase(document.getName())+".html");
	}
}
