package co.com.cybersoft.generator.code.docs.views;

import java.util.ArrayList;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.api.JavaScriptAPIConnector;
import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.JavaScriptAPI;
import co.com.cybersoft.generator.code.util.CodeUtils;

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
		template.setAttribute("javaScripts", JavaScriptAPIConnector.generateScriptsTags(document));
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("datePickers", generateDateFieldPickers(document));
		template.setAttribute("modificationCompoundSelectionFunctions", generateCompoundSelectionFunctions(document));
		template.setAttribute("setModificationFormFunction", generateModificationFormValues(document));
		template.setAttribute("additionFormFields", generateAdditionFormFields(document));
		template.setAttribute("modificationFormFields", generateModificationFormFields(document));
		template.setAttribute("bodyHeaderFields", generateBodyHeaderFields(document));
		template.setAttribute("bodyFields", generateBodyFields(document));
		template.setAttribute("headerFields", generateHeaderFields(document));
		template.setAttribute("changeDocReferenceFunctions", generateChangeDocReferencesFunctions(document));
		template.setAttribute("additionButton", generateAdditionButton(document));
		template.setAttribute("deletionButton", generateDeletionButton(document));
		template.setAttribute("readyCondition", generateReadyCondition(document));

		if (!document.getBody().isEmpty())
			template.setAttribute("firstBodyField", document.getBody().get(0).getName());

		template.setAttribute("autoCompleteFunctions", generateAutocompleteReferenceFunctions(document));
		template.setAttribute("checkHeader", generateCheckHeader(document));
		template.setAttribute("checkColumn", generateCheckColumn(document));
		template.setAttribute("enableReferenceFields", enableReferenceFields(document));
		
		List<Field> fields = document.getHeader();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "save"+CodeUtils.toCamelCase(document.getName())+".html");
	}

	

	private Object generateReadyCondition(Document document) {
		String ready="";
		if (document.hasReadyField()){
			StringTemplate template = new StringTemplate("th:if='!\\${$docName$Info.ready}'");
			template.setAttribute("docName", document.getName());
			return template.toString();
		}
		
		return ready;
	}

	private Object enableReferenceFields(Document document) {
		String enable="";
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
			List<String> bodyFields = referenceField.getBodyFields();
			for (String bodyField : bodyFields) {
				StringTemplate template = new StringTemplate("\\$( \"#$docName$BodyModificationInfo\\\\\\.$fieldName$\" ).prop( \"disabled\", false );");
				template.setAttribute("docName", document.getName());
				template.setAttribute("fieldName", bodyField);
				enable+=template.toString()+"\n";
			}
		}
		
		return enable;
	}

	private Object generateCheckHeader(Document document) {
		String column="";
		if (document.getDeletion()||document.getOriginalDeletion()!=null)
			return new StringTemplate("<th ></th>").toString();
		return column;
	}

	private Object generateCheckColumn(Document document) {
		String header="";
		if (document.getDeletion() && document.getOriginalDeletion()==null)
			return new StringTemplate("<td><div align=\"center\" ><input type=\"checkbox\" /></div></td>").toString();
		else if (document.getOriginalDeletion()!=null && !document.getOriginalDeletion())
			return new StringTemplate("<td><div align=\"center\" ><input th:if=\"\\${object._enableDeletion}\" type=\"checkbox\" /></div></td>").toString();
			
		return header;
	}

	private Object generateAdditionButton(Document document) {
		String addition="";
		if (document.getAddition() && document.getOriginalDeletion()==null){
			StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
			StringTemplate template = templateGroup.getInstanceOf("additionButton");
			if (document.hasReadyField()){
				StringTemplate stringTemplate = new StringTemplate("and !\\${$docName$Info.ready}");
				stringTemplate.setAttribute("docName", document.getName());
				template.setAttribute("readyCondition", stringTemplate.toString());
			}
				
			template.setAttribute("docName", document.getName());
			addition=template.toString();
		}
		else if (document.getOriginalDeletion()!=null && !document.getOriginalDeletion()){
			StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
			StringTemplate template = templateGroup.getInstanceOf("additionButton");
			if (document.hasReadyField()){
				StringTemplate stringTemplate = new StringTemplate("and !\\${$docName$Info.ready}");
				stringTemplate.setAttribute("docName", document.getName());
				template.setAttribute("readyCondition", stringTemplate.toString());
			}
			template.setAttribute("docName", document.getName());
			addition=template.toString();
		}
		return addition;
	}

	private Object generateDeletionButton(Document document) {
		String deletion="";
		if (document.getDeletion() && document.getOriginalDeletion()==null){
			StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
			StringTemplate template = templateGroup.getInstanceOf("deletionButton");
			if (document.hasReadyField()){
				StringTemplate stringTemplate = new StringTemplate("and !\\${$docName$Info.ready}");
				stringTemplate.setAttribute("docName", document.getName());
				template.setAttribute("readyCondition", stringTemplate.toString());
			}
			template.setAttribute("docName", document.getName());
			deletion=template.toString();
		}
		else if (document.getOriginalDeletion()!=null && !document.getOriginalDeletion()){
			StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
			StringTemplate template = templateGroup.getInstanceOf("deletionButton");
			if (document.hasReadyField()){
				StringTemplate stringTemplate = new StringTemplate("and !\\${$docName$Info.ready}");
				stringTemplate.setAttribute("docName", document.getName());
				template.setAttribute("readyCondition", stringTemplate.toString());
			}
			template.setAttribute("docName", document.getName());
			deletion=template.toString();
		}
		return deletion;
	}

	private Object generateChangeDocReferencesFunctions(Document document) {
		String functions="";
		Field referenceField = document.getDocReferenceField();
		if (referenceField!=null && referenceField.getBodyFields().size()>1){
			List<Field> bodyFields = CodeUtils.getDocReferenceBodyFields(document, cyberdocs);
			StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
			//Addition fields functions
			for (Field bodyField : bodyFields) {
				if (bodyField.getType().equals(Cyberconstants.stringType)||bodyField.getType().equals(Cyberconstants.doubleType)){
					StringTemplate template = templateGroup.getInstanceOf("changeDocReference");
					String changeSelection="";
					for (Field field : bodyFields) {
						if (field.getType().equals(Cyberconstants.stringType)||field.getType().equals(Cyberconstants.doubleType)){
							StringTemplate changeSelectionTemplate = new StringTemplate("\\$('#$fieldName$ :nth-child('+(\\$(this)[0].selectedIndex+1)+')').prop('selected', true);");
							changeSelectionTemplate.setAttribute("fieldName", field.getName());
							changeSelection+=changeSelectionTemplate.toString()+"\n";
						}
					}
					template.setAttribute("fieldName", bodyField.getName());
					template.setAttribute("references", changeSelection);
					functions+=template.toString()+"\n";
				}
			}
			
			//Modification fields functions
			for (Field bodyField : bodyFields) {
				if (bodyField.getType().equals(Cyberconstants.stringType)){
					StringTemplate template = templateGroup.getInstanceOf("changeDocReference");
					String changeSelection="";
					for (Field field : bodyFields) {
						if (field.getType().equals(Cyberconstants.stringType)){
							StringTemplate changeSelectionTemplate = new StringTemplate("\\$('#$fieldName$ :nth-child('+(\\$(this)[0].selectedIndex+1)+')').prop('selected', true);");
							changeSelectionTemplate.setAttribute("fieldName", document.getName()+"BodyModificationInfo\\\\."+field.getName());
							changeSelection+=changeSelectionTemplate.toString()+"\n";
						}
					}
					template.setAttribute("fieldName", document.getName()+"BodyModificationInfo\\\\."+bodyField.getName());
					template.setAttribute("references", changeSelection);
					functions+=template.toString()+"\n";
				}
			}
		}
		return functions;
	}

	private String generateAutocompleteReferenceFunctions(Document document) {
		List<Field> fields = document.getHeader();
		String functions="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.utilCodePath);
		StringTemplateGroup templateGroup2 = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");

		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);
				template.setAttribute("namespace", Cybertables.docNamespace);
				
				if (field.getAutoCompletePeerFunction()!=null)
					template.setAttribute("onSelectionAPICall", JavaScriptAPIConnector.generateAutoCompletePeerFunction(field.getAutoCompletePeerFunction(), document, false));

				functions+="\n"+template.toString();
			}
			if (CodeUtils.generateAutoCompleteReferenceCompoundField(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);
				template.setAttribute("namespace", Cybertables.docNamespace);
				
				if (field.getAutoCompletePeerFunction()!=null)
					template.setAttribute("onSelectionAPICall", JavaScriptAPIConnector.generateAutoCompletePeerFunction(field.getAutoCompletePeerFunction(), document, false));

				functions+="\n"+template.toString();
			}
			
			if (CodeUtils.generateAutocompleteDocReference(field)){
				StringTemplate template = templateGroup2.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("referenceType", field.getDocRefType());
				template.setAttribute("upperReferenceType", CodeUtils.toCamelCase(field.getDocRefType()));
				template.setAttribute("entityName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);
				template.setAttribute("namespace", Cybertables.docNamespace);
				if (field.getBodyFields()!=null&&!field.getBodyFields().isEmpty()){
					template.setAttribute("submitHeader","submitHeader();");
				}

				functions+="\n"+template.toString();
			}
		}
		
		fields=document.getBody();
		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);
				template.setAttribute("namespace", Cybertables.docNamespace);
				
				if (field.getAutoCompletePeerFunction()!=null)
					template.setAttribute("onSelectionAPICall", JavaScriptAPIConnector.generateAutoCompletePeerFunction(field.getAutoCompletePeerFunction(), document, false));

				functions+="\n"+template.toString();
				
				template = templateGroup.getInstanceOf("autocompleteReferenceFunction");
				template.setAttribute("fieldName", document.getName()+"BodyModificationInfo\\\\."+field.getName());
				template.setAttribute("displayField", field.getDisplayField());
				template.setAttribute("upperDisplayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("referenceType", field.getRefType());
				template.setAttribute("entityName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("arraySeparator", Cybertables.arraySeparator);
				template.setAttribute("namespace", Cybertables.docNamespace);
				
				if (field.getAutoCompletePeerFunction()!=null){
					JavaScriptAPI modAPI=JavaScriptAPIConnector.getModificationAutoCompletePeerFunction(field.getAutoCompletePeerFunction(), document);
					template.setAttribute("onSelectionAPICall", JavaScriptAPIConnector.generateAutoCompletePeerModFunction(modAPI, document, true));
				}

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
		int k=0;
		int hiddenFields=0;
		for (int i=1;i<header.size();i++) {
			Field field=header.get(i);
			if (field.getDocRefType()==null || (field.getDocRefType()!=null&&field.getHeaderFields().size()==0)){
				if (field.getVisible()){
					if ((i+k-hiddenFields)%Cyberconstants.headerColumnsPerRow!=0){
						headerRow.add(field);
					}
					else{
						headerFields+=generateHeaderRow(headerRow, document);
						headerRow.clear();
						headerRow.add(field);
					}
				}
				else{
					hiddenFields++;
				}
			}
			else{
				if (field.getDocRefType()!=null && !field.getHeaderFields().isEmpty()){
					int j=0;
					for (; j < field.getHeaderFields().size(); j++) {
						String fieldName = field.getHeaderFields().get(j);
						Field field2 = new Field();
						field2.setName(fieldName);
						field2.setType(Cyberconstants.stringType);
						field2.setReadOnly(true);
						if ((i+j-hiddenFields)%Cyberconstants.headerColumnsPerRow!=0){
							headerRow.add(field2);
						}
						else{
							headerFields += generateHeaderRow(headerRow,document);
							headerRow.clear();
							headerRow.add(field2);
						}
					}
					if ((i+j-hiddenFields)%Cyberconstants.headerColumnsPerRow!=0){
						headerRow.add(field);
					}
					else{
						headerFields += generateHeaderRow(headerRow,document);
						headerRow.clear();
						headerRow.add(field);
					}
					k+=j;

				}
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
			if (!field.isReference() && field.getDocRefType()==null && field.getVisible()){
				if (!field.getLargeText() && !field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableHeaderField");
				else if (field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableHeaderCheckField");
				else
					template = stringTemplateGroup.getInstanceOf("editableHeaderTextAreaField");
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				if (field.getReadOnly())
					template.setAttribute("disabled", "readonly=\"readonly\"");
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				fields+=template.toString()+"\n";
			}
			
			if (field.getDocRefType()!=null){
				template = stringTemplateGroup.getInstanceOf("editableHeaderField");
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				
				fields+=template.toString();
			}
			
			if (field.isReference() && !field.getCompoundReference()){
				if (CodeUtils.referencesLabelTable(field, cybertables)){
					template = stringTemplateGroup.getInstanceOf("referenceHeaderLabelField");
				}
				else{
					if (CodeUtils.generateAutoCompleteReference(field)){
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
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
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
		String bodyFields="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> body = document.getBody();
		
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
			List<Field> fields = CodeUtils.getDocReferenceBodyFields(document, cyberdocs);
			for (Field field : fields) {
				StringTemplate template;
				if (!CodeUtils.referencesLabelTable(field, cybertables)&& (field.getType()==null || !field.getType().equals(Cyberconstants.booleanType))){
					template = templateGroup.getInstanceOf("bodyColumn");
				}
				else if (CodeUtils.referencesLabelTable(field, cybertables)){
					template = templateGroup.getInstanceOf("labelBodyColumn");
				}
				else{
					template = templateGroup.getInstanceOf("labelBodyBooleanColumn");
				}
				template.setAttribute("fieldName", field.getName());
				if (!field.getDisplayable())
					template.setAttribute("hide", "class=\"hideANDseek\"");
				
				if (field.getType()!=null && (field.getType().equals(Cyberconstants.integerType) || field.getType().equals(Cyberconstants.longType)||field.getType().equals(Cyberconstants.doubleType)))
					template.setAttribute("alignment", "class=\"right\"");
				bodyFields+=template.toString();
			}
		}
		
		for (Field field : body) {
			StringTemplate template;
			if (!CodeUtils.referencesLabelTable(field, cybertables)&& (field.getType()==null || !field.getType().equals(Cyberconstants.booleanType))){
				template = templateGroup.getInstanceOf("bodyColumn");
			}
			else if (CodeUtils.referencesLabelTable(field, cybertables)){
				template = templateGroup.getInstanceOf("labelBodyColumn");
			}
			else{
				template = templateGroup.getInstanceOf("labelBodyBooleanColumn");
			}
			template.setAttribute("fieldName", field.getName());
			if (!field.getDisplayable())
				template.setAttribute("hide", "class=\"hideANDseek\"");
			
			if (field.getType()!=null && (field.getType().equals(Cyberconstants.integerType) || field.getType().equals(Cyberconstants.longType)||field.getType().equals(Cyberconstants.doubleType)))
				template.setAttribute("alignment", "class=\"right\"");
			bodyFields+=template.toString();
		}
		
		return bodyFields;
	}

	private String generateBodyHeaderFields(Document document) {
		String headers="";
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> body = document.getBody();
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
			List<String> bodyFields = referenceField.getBodyFields();
			for (String field : bodyFields) {
				StringTemplate template = templateGroup.getInstanceOf("headerBodyColumn");
				template.setAttribute("docName", referenceField.getDocRefType());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field));
				
				headers+=template.toString();
			}
		}
		
		for (Field field : body) {
			StringTemplate template = templateGroup.getInstanceOf("headerBodyColumn");
			template.setAttribute("docName", document.getName());
			template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
			if (!field.getDisplayable())
				template.setAttribute("hide", "class=\"hideANDseek\"" );
			if (field.getAppend()!=null){
				StringTemplate stringTemplate = new StringTemplate("+' ('+\\${$documentName$BodyInfo.$appendField$}+')'");
				stringTemplate.setAttribute("documentName", document.getName());
				stringTemplate.setAttribute("appendField", "_append_"+field.getAppend().substring(field.getAppend().indexOf('.')+1));
				template.setAttribute("append", stringTemplate.toString());
			}
			headers+=template.toString();
		}
		
		return headers;
	}

	private String generateModificationFormFields(Document document) {
		StringTemplateGroup stringTemplateGroup = new StringTemplateGroup("views", Cybertables.documentCodePath+"views");
		List<Field> fields = document.getBody();
		String text="";
		
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
			List<Field> bodyFields = CodeUtils.getDocReferenceBodyFields(document, cyberdocs);
			for (Field field : bodyFields) {
				if (!document.getAddition()&&!document.getDeletion()){
					
					if (field.getType().equals(Cyberconstants.booleanType)){
						StringTemplate template = stringTemplateGroup.getInstanceOf("editableCheck");
						template.setAttribute("docName", document.getName());
						template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("fieldName", field.getName());
						template.setAttribute("fieldNameId", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("modificationPrefix", "_");
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
						
						text+=template.toString()+"\n";

					}
					else if (field.getType().equals(Cyberconstants.stringType)){
						StringTemplate template;
						template = stringTemplateGroup.getInstanceOf("docReferenceReadOnlyRow");
						template.setAttribute("docName", document.getName());
						template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("fieldName", field.getName());
						template.setAttribute("fieldNameId", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("modificationPrefix", "_");
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
						
						text+=template.toString()+"\n";
					}
					else{
						StringTemplate template = stringTemplateGroup.getInstanceOf("editableTableRow");
						template.setAttribute("docName", document.getName());
						template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("fieldName", field.getName());
						template.setAttribute("fieldNameId", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("modificationPrefix", "_");
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
						text+=template.toString()+"\n";

					}
				}
				else{
					
					if (field.getType().equals(Cyberconstants.booleanType)){
						StringTemplate template = stringTemplateGroup.getInstanceOf("editableCheck");
						template.setAttribute("docName", document.getName());
						template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("fieldName", field.getName());
						template.setAttribute("fieldNameId", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("modificationPrefix", "_");
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
						text+=template.toString()+"\n";

					}
					else if (field.getType().equals(Cyberconstants.stringType)){
						StringTemplate template;
						template = stringTemplateGroup.getInstanceOf("docReferenceRow");
						template.setAttribute("docName", document.getName());
						template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("fieldName", field.getName());
						template.setAttribute("fieldNameId", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("modificationPrefix", "_");
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
						
						text+=template.toString()+"\n";
					}
					else{
						StringTemplate template = stringTemplateGroup.getInstanceOf("editableTableRow");
						template.setAttribute("docName", document.getName());
						template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("fieldName", field.getName());
						template.setAttribute("fieldNameId", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
						template.setAttribute("modificationPrefix", "_");
						template.setAttribute("optionalReference", "<option value=\"\"></option>");
						text+=template.toString()+"\n";

					}
				}
			}
		}
		
		for (Field field : fields) {
			if (!field.isReference() && field.getVisible()){
				StringTemplate template;
				if (!field.getLargeText() && !field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableTableRow");
				else if (field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableCheck");
				else
					template = stringTemplateGroup.getInstanceOf("editableTextAreaRow");
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("fieldNameId", field.getName()+"Modification");
				template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
				template.setAttribute("modificationPrefix", "_");
				if (field.getReadOnly())
					template.setAttribute("disabled", "readonly=\"readonly\"");
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				if (field.getAppend()!=null){
					StringTemplate stringTemplate = new StringTemplate("+' ('+\\${$documentName$BodyInfo.$appendField$}+')'");
					stringTemplate.setAttribute("documentName", document.getName());
					stringTemplate.setAttribute("appendField", "_append_"+field.getAppend().substring(field.getAppend().indexOf('.')+1));
					template.setAttribute("append", stringTemplate.toString());
				}
				
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
//						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
//						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				template.setAttribute("fieldNameId", field.getName()+"Modification");
				template.setAttribute("fieldNamePath", document.getName()+"BodyModificationInfo."+field.getName());
				if (field.getAppend()!=null){
					StringTemplate stringTemplate = new StringTemplate("+' ('+\\${$documentName$BodyInfo.$appendField$}+')'");
					stringTemplate.setAttribute("documentName", document.getName());
					stringTemplate.setAttribute("appendField", "_append_"+field.getAppend().substring(field.getAppend().indexOf('.')+1));
					template.setAttribute("append", stringTemplate.toString());
				}

				text+=template.toString()+"\n";
			}
			
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField: compoundKey) {
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
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
		
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
			List<Field> bodyFields = CodeUtils.getDocReferenceBodyFields(document, cyberdocs);
			for (Field field : bodyFields) {
				if (field.getType().equals(Cyberconstants.booleanType)){
					StringTemplate template = stringTemplateGroup.getInstanceOf("editableCheck");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("fieldNameId", field.getName());
					template.setAttribute("fieldNamePath", field.getName());
					template.setAttribute("modificationPrefix", "");
					text+=template.toString()+"\n";

				}
				else if (field.getType().equals(Cyberconstants.stringType)||field.getType().equals(Cyberconstants.doubleType)){
					StringTemplate template;
					template = stringTemplateGroup.getInstanceOf("docReferenceRow");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("fieldNameId", field.getName());
					template.setAttribute("fieldNamePath", field.getName());
					template.setAttribute("modificationPrefix", "");
					template.setAttribute("optionalReference", "<option value=\"\"></option>");
					
					text+=template.toString()+"\n";
				}
				else{
					StringTemplate template = stringTemplateGroup.getInstanceOf("editableTableRow");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("fieldNameId", field.getName());
					template.setAttribute("fieldNamePath", field.getName());
					template.setAttribute("modificationPrefix", "");
					text+=template.toString()+"\n";

				}
			}
		}
		for (Field field : fields) {
			if (!field.isReference() && field.getVisible() ){
				StringTemplate template;
				if (!field.getLargeText() && !field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableTableRow");
				else if (field.getType().equals(Cybertables.booleanType))
					template = stringTemplateGroup.getInstanceOf("editableCheck");
				else
					template = stringTemplateGroup.getInstanceOf("editableTextAreaRow");
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("fieldNameId", field.getName());
				template.setAttribute("fieldNamePath", field.getName());
				template.setAttribute("modificationPrefix", "");
				if (field.getReadOnly())
					template.setAttribute("disabled", "readonly=\"readonly\"");
				if (field.getType().equals(Cybertables.dateType))
					template.setAttribute("datePicker", "id=\""+field.getName()+"\"");
				if (field.getAppend()!=null){
					StringTemplate stringTemplate = new StringTemplate("+' ('+\\${$documentName$BodyInfo.$appendField$}+')'");
					stringTemplate.setAttribute("documentName", document.getName());
					stringTemplate.setAttribute("appendField", "_append_"+field.getAppend().substring(field.getAppend().indexOf('.')+1));
					template.setAttribute("append", stringTemplate.toString());
				}
				
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
//						if (!field.getRequired()){
							template.setAttribute("optionalReference", "<option value=\"\"></option>");
//						}
					}
				}
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("displayName", field.getDisplayField());
				template.setAttribute("fieldNamePath", field.getName());
				template.setAttribute("fieldNameId", field.getName());
				if (field.getAppend()!=null){
					StringTemplate stringTemplate = new StringTemplate("+' ('+\\${$documentName$BodyInfo.$appendField$}+')'");
					stringTemplate.setAttribute("documentName", document.getName());
					stringTemplate.setAttribute("appendField", "_append_"+field.getAppend().substring(field.getAppend().indexOf('.')+1));
					template.setAttribute("append", stringTemplate.toString());
				}
				
				text+=template.toString()+"\n";
			}
			
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField: compoundKey) {
					StringTemplate template = stringTemplateGroup.getInstanceOf("referenceTableRow");
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
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
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
			List<String> bodyFields = referenceField.getBodyFields();
			for (String field : bodyFields) {
				StringTemplate template2 = templateGroup.getInstanceOf("fieldValue");
				if (document.getDeletion())
					template2.setAttribute("i", i);
				else
					template2.setAttribute("i", i-1);
				template2.setAttribute("varAssignment", "$(field).val($(this).html());$(field).prop( \"disabled\", disable );");
				template2.setAttribute("fieldName", document.getName()+"BodyModificationInfo\\\\."+field);
				fieldValues+=template2.toString();
				i++;
			}
		}
		
		for (Field field : body) {
			StringTemplate template2 = templateGroup.getInstanceOf("fieldValue");
			if (document.getDeletion())
				template2.setAttribute("i", i);
			else
				template2.setAttribute("i", i-1);
			if (!field.isReference()){
				if (field.getType().equals(Cyberconstants.booleanType))
					template2.setAttribute("varAssignment", "$(field).prop('checked', $(this).html()==document.getElementById(\"_trueValue\").value);");
				else
					template2.setAttribute("varAssignment", "$(field).val($(this).html());");
			}
			else{
				if (CodeUtils.referencesLabelTable(field, cybertables))
					template2.setAttribute("varAssignment", "$(field).val($(this).attr(\"title\"));");
				else
					template2.setAttribute("varAssignment", "$(field).val($(this).html());");
			}
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
			if (!field.isReference() && field.getDocRefType()==null && field.getType().equals(Cybertables.dateType)){
				StringTemplate template = templateGroup.getInstanceOf("datePicker");
				template.setAttribute("dateField", field.getName());
				pickers+=template.toString()+"\n";
				
				template = templateGroup.getInstanceOf("datePicker");
				template.setAttribute("dateField", document.getName()+"BodyModificationInfo\\\\."+field.getName());
				pickers+=template.toString()+"\n";
			}
		}
		
		return pickers;
	}

	private void generateSearchView(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("focusCheck", getFocusCheck(document));
		template.setAttribute("searchFieldsHeaders", getHeaderColumns(document));
		template.setAttribute("filterColumnHeaders", getFilterHeaderColumns(document));

		template.setAttribute("searchFieldsColumns", getColumns(document));

		CodeUtils.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "search"+CodeUtils.toCamelCase(document.getName())+".html");
	}

	private Object getFilterHeaderColumns(Document table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> fields = table.getHeader();
		
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && field.getSearchDisplayable()){
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
//			else{
//				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
//				for (Field compoundField : compoundKey) {
//					StringTemplate template = templateGroup.getInstanceOf("filterColumnHeader");
//					template.setAttribute("fieldName", compoundField.getName());
//					template.setAttribute("tableName", table.getName());
//					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
//					text+=template.toString()+"\n";
//				}
//			}
		}
		return text;
	}

	private Object getFocusCheck(Document document) {
		String focusCheck="";
		List<Field> fields = document.getHeader();
		for (Field field : fields) {
			StringTemplate template=new StringTemplate(" || document.getElementById(\"$fieldName$\") === document.activeElement");
			if (!field.getCompoundReference()){
				template.setAttribute("fieldName", field.getName());
				focusCheck+=template.toString()+"\n";
			}
		}
		return focusCheck;
	}

	private Object getColumns(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> fields = document.getHeader();
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && field.getSearchDisplayable()){
				if (!field.isReference() && field.getDocRefType()==null && field.getVisible() && !field.getLargeText() && !field.getType().equals(Cybertables.booleanType)){
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
				
				if (field.getDocRefType()!=null){
					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
					template.setAttribute("fieldName", field.getName());
					text+=template.toString()+"\n";
				}
				
				if (!field.isReference() && field.getDocRefType()==null && field.getVisible() && !field.getLargeText() && field.getType().equals(Cybertables.booleanType)){
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
				//TODO compound reference
//				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
//				for (Field compoundField : compoundKey) {
//					StringTemplate template = templateGroup.getInstanceOf("otherColumn");
//					template.setAttribute("fieldName", compoundField.getName());
//					text+=template.toString()+"\n";
//				}
			}
		}
		
		return text;
	}

	private Object getHeaderColumns(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		List<Field> fields = document.getHeader();
		
		String text="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && field.getSearchDisplayable()){
				if (!field.isReference() && field.getVisible() && !field.getLargeText()){
					StringTemplate template = templateGroup.getInstanceOf("columnHeader");
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
				
				if (field.isReference()){
					StringTemplate template = templateGroup.getInstanceOf("columnHeader");
					template.setAttribute("fieldName", field.getName());
					template.setAttribute("docName", document.getName());
					template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					text+=template.toString()+"\n";
				}
			}
			else{
				//TODO referencias compuestas
//				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
//				for (Field compoundField : compoundKey) {
//					StringTemplate template = templateGroup.getInstanceOf("columnHeader");
//					template.setAttribute("fieldName", compoundField.getName());
//					template.setAttribute("tableName", table.getName());
//					template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
//					text+=template.toString()+"\n";
//				}
			}
		}
		
		return text;
	}
}
