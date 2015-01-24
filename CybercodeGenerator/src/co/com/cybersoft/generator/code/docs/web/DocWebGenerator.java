package co.com.cybersoft.generator.code.docs.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.api.JavaAPIConnector;
import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.JavaArithmeticExpression;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class DocWebGenerator {
	
	private final Cyberdocs cyberdocs;
	
	private final Cybertables cybertables;

	
	public DocWebGenerator(Cyberdocs cyberdocs, Cybertables cybertables){
		this.cyberdocs=cyberdocs;
		this.cybertables=cybertables;
	}
	
	public void generate(){
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			generateSaveController(document);
			generateSearchController(document);
			generateSearchByIdController(document);
			generateDomainHeader(document);
			generateDomainBody(document);
			generateExcelController(document);
			generateFilterFields(document);
		}
	}

	private void generateFilterFields(Document document) {
		List<Field> fields = document.getHeader();
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate fieldTemplate = templateGroup.getInstanceOf("filterCriteria");
		String filterFields="";
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (!field.isReference()){
					StringTemplate template = new StringTemplate("private $type$ $name$;\n\n");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", field.getName());
					filterFields+=template.toString();
					filterFields+="\n";
					
					StringTemplateGroup templateGroup2 = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup2.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", Cybertables.stringType);
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					filterFields+=gettersSettersTemplate.toString()+"\n\n";
				}
				else{
					StringTemplate template = new StringTemplate("private $type$ $name$;\n\n");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", field.getName());
					filterFields+=template.toString();
					filterFields+="\n";
					
					StringTemplateGroup templateGroup2 = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup2.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", Cybertables.stringType);
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					filterFields+=gettersSettersTemplate.toString()+"\n\n";
				}
				
			}
		}
		
		fieldTemplate.setAttribute("filterFields", filterFields);
		fieldTemplate.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		fieldTemplate.setAttribute("docName", document.getName());
		
		CodeUtils.writeClass(fieldTemplate.toString(), Cybertables.targetDocumentClassPath+"/web/domain/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"FilterInfo.java");
	}

	private void generateExcelController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("excelController");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/web/controller/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"ExcelController.java");
	}

	private void generateSearchByIdController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchControllerById");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/web/controller/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"SearchByNumericIdController.java");
	}

	private void generateSearchController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchController");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/web/controller/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"SearchController.java");
	}

	private void generateSaveController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("saveController");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		
		template.setAttribute("imports", generateSaveControllerImports(document));
		template.setAttribute("fields", generateControllerReferencesServicesDeclarations(document));
		template.setAttribute("setListsAndDefaults", generateReferencesImports(document.getHeader(),document.getName()));
		template.setAttribute("bodyAdditionLists", generateReferencesImports(document.getBody(),document.getName()+"Body"));
		template.setAttribute("bodyModificationLists", generateReferencesImports(document.getBody(),document.getName()+"BodyModification"));
		template.setAttribute("setDefaults", generateDefaults(document));
		template.setAttribute("setOnLoadHeaderValues", JavaAPIConnector.generateOnLoadHeaderValues(document));
		template.setAttribute("setOnLoadBodyValues", JavaAPIConnector.generateOnLoadBodyValues(document));
		template.setAttribute("setBodyDefaults", generateBodyDefaults(document));
		template.setAttribute("rowModificationOperations", generateRowModificationOperations(document));
		template.setAttribute("rowAdditionOperations", generateRowAdditonOperations(document));
		template.setAttribute("onHeaderSave", JavaAPIConnector.generateOnHeaderSave(document));
		template.setAttribute("onHeaderPreSave", JavaAPIConnector.generateOnHeaderPreSave(document));
		template.setAttribute("onBodyPreAdd", JavaAPIConnector.generateOnBodyPreAddition(document));
		template.setAttribute("onBodyPreMod", JavaAPIConnector.generateOnBodyPreModification(document));
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/web/controller/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"Controller.java");
	}
	
	private Object generateRowAdditonOperations(Document document) {
		String variables="";
		String operations="";
		
		List<Field> body = document.getBody();
		HashSet<String> set = new HashSet<String>();
		for (Field field : body) {
			if (field.getValue()!=null){
				List<String> fieldsFromExpression = CodeUtils.getFieldsFromExpression(field.getValue());
				for (String fieldFromExp : fieldsFromExpression) {
					if (!set.contains(fieldFromExp)){
						StringTemplate stringTemplate = new StringTemplate("$fieldType$ $fieldName$=current.get$upperFieldName$();\n");
						stringTemplate.setAttribute("fieldName", fieldFromExp);
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(fieldFromExp));
						stringTemplate.setAttribute("fieldType", field.getType());
						
						variables+=stringTemplate.toString();
						set.add(fieldFromExp);
					}
				}
				StringTemplate template = new StringTemplate("if($nonNullCondition$)current.set$upperFieldName$($arithmeticExpression$);\n");
				template.setAttribute("nonNullCondition", JavaArithmeticExpression.getNonNullCondition(field.getValue()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("arithmeticExpression", JavaArithmeticExpression.getJavaArithmeticExpression(field.getValue()));
				operations+=template.toString();
			}
		}
		return variables+operations;	}

	private Object generateRowModificationOperations(Document document) {
		String variables="";
		String operations="";
		
		List<Field> body = document.getBody();
		HashSet<String> set = new HashSet<String>();
		for (Field field : body) {
			if (field.getValue()!=null){
				List<String> fieldsFromExpression = CodeUtils.getFieldsFromExpression(field.getValue());
				for (String fieldFromExp : fieldsFromExpression) {
					if (!set.contains(fieldFromExp)){
						StringTemplate stringTemplate = new StringTemplate("$fieldType$ $fieldName$=modified.get$upperDocName$BodyModificationInfo().get$upperFieldName$();\n");
						stringTemplate.setAttribute("fieldName", fieldFromExp);
						stringTemplate.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(fieldFromExp));
						stringTemplate.setAttribute("fieldType", field.getType());
						
						variables+=stringTemplate.toString();
						set.add(fieldFromExp);
					}
				}
				StringTemplate template = new StringTemplate("if($nonNullCondition$)modified.get$upperDocName$BodyModificationInfo().set$upperFieldName$($arithmeticExpression$);\n");
				template.setAttribute("nonNullCondition", JavaArithmeticExpression.getNonNullCondition(field.getValue()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("arithmeticExpression", JavaArithmeticExpression.getJavaArithmeticExpression(field.getValue()));
				operations+=template.toString();
			}
		}
		return variables+operations;
	}

	private Object generateBodyDefaults(Document document) {
		List<Field> fields = document.getBody();
		String defaults="";
		for (Field field : fields) {
			if (field.getDefaultValue()!=null){
				StringTemplate stringTemplate = new StringTemplate("$tableName$BodyInfo.set$fieldName$($defaultValue$);\n");
				stringTemplate.setAttribute("tableName", document.getName());
				stringTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				stringTemplate.setAttribute("defaultValue", CodeUtils.getDefaultValue(field));
				defaults+=stringTemplate.toString();
			}
		}
		defaults+=generateBodyAppendValues(document);
		return defaults;
	}

	private String generateBodyAppendValues(Document document) {
		String append="";
		List<Field> body = document.getBody();
		Set<String> services = new HashSet<>();
		for (Field field : body) {
			if (field.getAppend()!=null){
				services.add(field.getAppend().substring(0,field.getAppend().indexOf(".")));
			}
		}
		
		if (!services.isEmpty()){
			StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
			StringTemplate template = templateGroup.getInstanceOf("setAppendValues");
			
			String details="";
			String values="";
			for (String service : services) {
				StringTemplate detailsTemplate = new StringTemplate("$entityName$Details $tableName$Details = $tableName$Service.requestOnlyRecord().get$entityName$Details();\n");
				detailsTemplate.setAttribute("entityName", CodeUtils.toCamelCase(service));
				detailsTemplate.setAttribute("tableName", service);
				details+=detailsTemplate.toString();
			}
			
			for (Field field : body) {
				if (field.getAppend()!=null ){
					StringTemplate valueTemplate = new StringTemplate("$docName$BodyInfo.set$upperAppendField$($appendMethod$);\n");
					valueTemplate.setAttribute("docName", document.getName());
					valueTemplate.setAttribute("upperAppendField", "_append_"+field.getAppend().substring(field.getAppend().indexOf(".")+1));
					valueTemplate.setAttribute("appendMethod", field.getAppend().substring(0,field.getAppend().indexOf("."))+"Details.get"+CodeUtils.toCamelCase(field.getAppend().substring(field.getAppend().indexOf(".")+1))+"()");
					values+=valueTemplate.toString();
				}
			}
			
			template.setAttribute("details", details);
			template.setAttribute("values", values);
			return template.toString();
		}
		return append;
	}

	private Object generateDefaults(Document document) {
		List<Field> fields = document.getHeader();
		String defaults="";
		for (Field field : fields) {
			if (field.getDefaultValue()!=null){
				StringTemplate stringTemplate = new StringTemplate("$tableName$Info.set$fieldName$($defaultValue$);\n");
				stringTemplate.setAttribute("tableName", document.getName());
				stringTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				stringTemplate.setAttribute("defaultValue", CodeUtils.getDefaultValue(field));
				defaults+=stringTemplate.toString();
			}
		}
		return defaults;
	}

	private String generateControllerReferencesServicesDeclarations(Document document){
		
		String declarations="";
		
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(document.getHeader());
		fields.addAll(document.getBody());
		HashSet<String> services = new HashSet<String>();
		for (Field field : fields) {
			if (field.isReference() && !field.getCompoundReference()){
				if (!services.contains(field.getRefType())){
					StringTemplate template = new StringTemplate("@Autowired\n"
							+ "	private $entityName$Service $tableName$Service;");
					template.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
					template.setAttribute("tableName", field.getRefType());
					declarations+=template.toString()+"\n\n";
					services.add(field.getRefType());
				}
			}
		}
		
		List<Field> compositeFields = document.getCompositeFields();
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtils.getCompoundKey(cybertables, compositeField.getRefType());
			for (Field field : compoundReference) {
				if (!services.contains(field.getName())){
					StringTemplate template = new StringTemplate("@Autowired\n"
							+ "	private $entityName$Service $tableName$Service;");
					template.setAttribute("entityName", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getName());
					declarations+=template.toString()+"\n\n";
					services.add(field.getName());
				}
			}
		}
		
		List<Field> allFields = document.getAllFields();
		for (Field field : allFields) {
			if (field.getOnLoad()!=null ){
				StringTemplate template = new StringTemplate("@Autowired\n"
						+ "	private $upperServiceName$ $serviceName$;");
				template.setAttribute("upperServiceName", field.getOnLoad().getClassName());
				template.setAttribute("serviceName", field.getOnLoad().getName());
				declarations+=template.toString()+"\n\n";
				services.add(field.getOnLoad().getName());
			}
		}
		
		if (document.getOnHeaderSave()!=null&&!services.contains(document.getOnHeaderSave().getName())){
			StringTemplate template = new StringTemplate("@Autowired\n"
					+ "	private $upperServiceName$ $serviceName$;");
			template.setAttribute("upperServiceName", document.getOnHeaderSave().getClassName());
			template.setAttribute("serviceName", document.getOnHeaderSave().getName());
			declarations+=template.toString()+"\n\n";
			services.add(document.getOnHeaderSave().getName());
		}
		
		if (document.getOnHeaderPreSave()!=null&&!services.contains(document.getOnHeaderPreSave().getName())){
			StringTemplate template = new StringTemplate("@Autowired\n"
					+ "	private $upperServiceName$ $serviceName$;");
			template.setAttribute("upperServiceName", document.getOnHeaderPreSave().getClassName());
			template.setAttribute("serviceName", document.getOnHeaderPreSave().getName());
			declarations+=template.toString()+"\n\n";
			services.add(document.getOnHeaderPreSave().getName());
		}
		
		if (document.getOnBodyPreModification()!=null&&!services.contains(document.getOnBodyPreModification().getName())){
			StringTemplate template = new StringTemplate("@Autowired\n"
					+ "	private $upperServiceName$ $serviceName$;");
			template.setAttribute("upperServiceName", document.getOnBodyPreModification().getClassName());
			template.setAttribute("serviceName", document.getOnBodyPreModification().getName());
			declarations+=template.toString()+"\n\n";
			services.add(document.getOnBodyPreModification().getName());
		}
		
		if (document.getOnBodyPreAddition()!=null&&!services.contains(document.getOnBodyPreAddition().getName())){
			StringTemplate template = new StringTemplate("@Autowired\n"
					+ "	private $upperServiceName$ $serviceName$;");
			template.setAttribute("upperServiceName", document.getOnBodyPreAddition().getClassName());
			template.setAttribute("serviceName", document.getOnBodyPreAddition().getName());
			declarations+=template.toString()+"\n\n";
			services.add(document.getOnBodyPreAddition().getName());
		}
		
		for (Field field : allFields) {
			if (field.getAppend()!=null && !services.contains(field.getAppend().substring(0,field.getAppend().indexOf(".")))){
				StringTemplate template = new StringTemplate("@Autowired\n"
						+ "	private $upperServiceName$Service $serviceName$Service;");
				template.setAttribute("upperServiceName", CodeUtils.toCamelCase(field.getAppend().substring(0,field.getAppend().indexOf("."))));
				template.setAttribute("serviceName", field.getAppend().substring(0,field.getAppend().indexOf(".")));
				declarations+=template.toString()+"\n\n";
				services.add(field.getAppend().substring(0,field.getAppend().indexOf(".")));
			}
		}
		
		return declarations;
	}
	
	private String generateReferencesImports(List<Field> fields, String parent) {
		String lists="";
		for (Field field : fields) {
			if (field.isReference()){
				if (!field.isEmbeddedReference() && !field.getCompoundReference() && !CodeUtils.generateAutoCompleteReference(field)){
					StringTemplate template = new StringTemplate("$entityName$PageEvent all$variableName$Event = $tableName$Service.requestAllBy$referenceField$();\n"
							+ "$parentTableName$Info.set$variableName$List(all$variableName$Event.get$entityName$List());\n");
					template.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
					template.setAttribute("variableName", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getRefType());
					template.setAttribute("parentTableName", parent);
					template.setAttribute("referenceField", CodeUtils.toCamelCase(field.getDisplayField()));
					lists+=template.toString();
				}
				else{
					if (field.isEmbeddedReference() && !CodeUtils.generateAutoCompleteReference(field)){
						StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
						StringTemplate template = templateGroup.getInstanceOf("setEmbeddedReferences");
						List<String> embeddedFields=field.getEmbeddedFields();
						String decl="";
						String requestParameters="";
						int i=0;
						for (String embeddedField : embeddedFields) {
							StringTemplate temp = new StringTemplate("EmbeddedField $embeddedField$Field=new EmbeddedField(\"$fieldName$\", $embeddedFieldType$.class);\n");
							temp.setAttribute("embeddedField", embeddedField+CodeUtils.toCamelCase(field.getName()));
							temp.setAttribute("fieldName", embeddedField);
							temp.setAttribute("embeddedFieldType", CodeUtils.getFieldType(cybertables,field.getRefType(), embeddedField));
							decl+=temp.toString();
							requestParameters+=embeddedField+CodeUtils.toCamelCase(field.getName())+"Field";
							if (i!=embeddedFields.size()-1){
								requestParameters+=",";
							}
							i++;
						}
						
						template.setAttribute("embeddedFields", requestParameters);
						template.setAttribute("embeddedFieldsDeclarations", decl);
						template.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
						template.setAttribute("variableName", CodeUtils.toCamelCase(field.getName()));
						template.setAttribute("tableName", field.getRefType());
						template.setAttribute("parentTableName", parent);
						template.setAttribute("referenceField", CodeUtils.toCamelCase(field.getDisplayField()));
						lists+=template.toString();
					}
					
					
				}
			}
		}
		
		return lists;
	}

	private String generateSaveControllerImports(Document document) {
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(document.getHeader());
		fields.addAll(document.getBody());
		
		String  imports="";
		Set<String> referenceImports = new HashSet<String>();
		for (Field field : fields) {
			if (field.isReference() && !referenceImports.contains(field.getRefType())){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;\n"
						+ "import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;\n");
				template.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("tableName", field.getRefType());
				imports+=template.toString();
				referenceImports.add(field.getRefType());
			}
		}
		
		List<Field> compositeFields = document.getCompositeFields();
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtils.getCompoundKey(cybertables, compositeField.getRefType());
			for (Field field : compoundReference) {
				if (!referenceImports.contains(field.getName())){
					StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;\n"
							+ "import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;\n");
					template.setAttribute("entityName", CodeUtils.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getName());
					imports+=template.toString();
					referenceImports.add(field.getName());
				}
			}
		}
		
		for (Field field : fields) {
			if (field.getAppend()!=null&& !referenceImports.contains(field.getAppend().substring(0,field.getAppend().indexOf(".")))){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;\n"
						+ "import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;\n"
						+ "import co.com.cybersoft.tables.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtils.toCamelCase(field.getAppend().substring(0,field.getAppend().indexOf("."))));
				template.setAttribute("tableName", field.getAppend().substring(0,field.getAppend().indexOf(".")));
				imports+=template.toString();
				referenceImports.add(field.getAppend().substring(0,field.getAppend().indexOf(".")));
			}else if (field.getAppend()!=null&& referenceImports.contains(field.getAppend().substring(0,field.getAppend().indexOf(".")))){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtils.toCamelCase(field.getAppend().substring(0,field.getAppend().indexOf("."))));
				template.setAttribute("tableName", field.getAppend().substring(0,field.getAppend().indexOf(".")));
				imports+=template.toString();
				referenceImports.add(field.getAppend().substring(0,field.getAppend().indexOf(".")));
			}
		}
		
		return imports;
	}
	
	private void generateDomainBody(Document document){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainBody");
		template.setAttribute("fields", generateDomainFieldsAndGS(document.getBody()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("referenceFields", generateReferenceDomainFieldsAndGS(document));
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("imports", generateDomainClassImports(document.getBody()));
		template.setAttribute("overrideEquals", generateEqualsOverride(document));
		template.setAttribute("appendFields", generateAppendFieldsAndGS(document));

		CodeUtils.writeClass(template.toString(),Cybertables.targetDocumentClassPath+"/web/domain/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"BodyInfo.java");
	}
	

	private Object generateAppendFieldsAndGS(Document document) {
		String appendFields="";
		List<Field> body = document.getBody();
		Set<String> appendF = new HashSet<>();
		for (Field field : body) {
			if (field.getAppend()!=null){
				appendF.add(field.getAppend());
			}
		}
		for (String string : appendF) {
			appendFields+="private String _append_"+string.substring(string.indexOf('.')+1)+";\n";
			StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
			StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
			gettersSettersTemplate.setAttribute("type", Cyberconstants.stringType);
			gettersSettersTemplate.setAttribute("name", "_append_"+string.substring(string.indexOf('.')+1));
			gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase("_append_"+string.substring(string.indexOf('.')+1)));
			appendFields+=gettersSettersTemplate.toString()+"\n\n";
		}
		
		return appendFields;
	}
	
	private Object generateEqualsOverride(Document document) {
		String override="";
		Field bodyKey = document.getBodyKey();
		if (bodyKey!=null){
			StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.documentCodePath+"web");
			StringTemplate template = templateGroup.getInstanceOf("equals");
			template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
			template.setAttribute("upperKeyName", CodeUtils.toCamelCase(bodyKey.getName()));
			template.setAttribute("keyName", bodyKey.getName());
			override=template.toString();
		}
		return override;
	}

	private void generateDomainHeader(Document document){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainHeader");
		template.setAttribute("fields", generateDomainFieldsAndGS(document.getHeader()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("referenceFields", generateHeaderReferenceDomainFieldsAndGS(document));
		template.setAttribute("imports", generateDomainClassImports(document.getHeader()));
		CodeUtils.writeClass(template.toString(),Cybertables.targetDocumentClassPath+"/web/domain/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"Info.java");
	}

	private Object generateReferenceDomainFieldsAndGS(Document document) {
		String fields="";
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
				List<Field> referenceBodyFields = CodeUtils.getDocReferenceBodyFields(document,cyberdocs);
				for (Field field : referenceBodyFields) {
					StringTemplate fieldTemplate = new StringTemplate("@$validation$\nprivate $referenceType$ $name$;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					fieldTemplate.setAttribute("referenceType", field.getType());
					fieldTemplate.setAttribute("validation", field.getType().equals(Cyberconstants.stringType)?"NotEmpty":"NotNull");
					fields+=fieldTemplate.toString();
					fields+="\n";
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					fields+=gettersSettersTemplate.toString()+"\n\n";
				}
		}
		return fields;
	}
	
	private Object generateHeaderReferenceDomainFieldsAndGS(Document document) {
		String fields="";
		Field referenceField = document.getHeaderDocReferenceField();
		if (referenceField!=null){
				List<Field> headerFields = CodeUtils.getDocReferenceHeaderFields(document, cyberdocs);
				for (Field field : headerFields) {
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					fieldTemplate.setAttribute("type", field.getType());
					fields+=fieldTemplate.toString();
					fields+="\n";
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					fields+=gettersSettersTemplate.toString()+"\n\n";
				}
		}
		return fields;
	}


	private String generateDomainClassImports(List<Field> fields) {
		String imports="";
		
		//Check for validation constraints
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybertables.stringType)){
				imports+="import org.hibernate.validator.constraints.Length;\n";
				break;
			}
				
		}
		
		imports+="import javax.validation.constraints.NotNull;\n";
		imports+="import org.hibernate.validator.constraints.NotEmpty;\n";

		
		for (Field field : fields) {
			if (field.getLength()!=null && (field.getType().equals(Cybertables.integerType)
					||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
				imports+="import org.hibernate.validator.constraints.Range;\n";
			}
		}
		
		for (Field field : fields) {
			
			if (field.isReference()){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
				imports+=template.toString();
			}
		}
		
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate stringTemplate = new StringTemplate("import co.com.cybersoft.tables.core.domain.$fieldName$Details;\n");
					stringTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()));
					imports+=stringTemplate.toString();
				}
			}
		}
		
		return imports;
	}

	private String generateDomainFieldsAndGS(List<Field> fields) {
		String body="";
		
		//Attributes
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
				if (field.getDocRefType()!=null){
					if (field.getRequired())
						body+="@NotEmpty\n";
				}
				else{
					
					if (!field.isReference() && !field.getType().equals(Cybertables.booleanType)){
						if (field.getLength()!=null && field.getType().equals(Cybertables.stringType)){
							body+="@Length(max="+field.getLength()+")\n";
						}
						if (field.getRequired()&&field.getVisible()&&(field.isReference()|| field.getType().equals(Cybertables.stringType))){
							body+="@NotEmpty\n";
						}
						
						if (field.getRequired()&&field.getVisible()&&!field.isReference()&&(field.getType().equals(Cybertables.integerType)
								||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
							body+="@NotNull\n";
						}
						
						if (field.getLength()!=null && !field.isReference() && (field.getType().equals(Cybertables.integerType)
								||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
							body+="@Range(max="+CodeUtils.getMaxNumber(field.getLength())+")\n";
						}
					}
					
					if (field.isReference()&&field.getRequired()){
						body+="@NotEmpty\n";
					}
				}
				
				StringTemplate fieldTemplate;
				
				if (!field.isReference()&&field.getDocRefType()==null){
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", field.getType());
					fieldTemplate.setAttribute("name", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";			
				}
				else if (field.getDocRefType()!=null){
					fieldTemplate = new StringTemplate("private String $name$;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";
				}
				else{
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.stringType);
					fieldTemplate.setAttribute("name", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";
					
					fieldTemplate = new StringTemplate("private List<$entityName$Details> $tableName$List;");
					fieldTemplate.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
					fieldTemplate.setAttribute("tableName", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";				
					
					if (field.isEmbeddedReference()){
						fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
						fieldTemplate.setAttribute("type", CodeUtils.toCamelCase(field.getRefType())+"Details");
						fieldTemplate.setAttribute("name", field.getName()+"Details");
						body+=fieldTemplate.toString();
						body+="\n";				
					}
					
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					if (field.getRequired())
						body+="@NotEmpty\n";
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.stringType);
					fieldTemplate.setAttribute("name", compoundField.getName());
					body+=fieldTemplate.toString();
					body+="\n";
					
					fieldTemplate = new StringTemplate("private List<$entityName$Details> $tableName$List;");
					fieldTemplate.setAttribute("entityName", CodeUtils.toCamelCase(compoundField.getTableName()));
					fieldTemplate.setAttribute("tableName", compoundField.getTableName());
					body+=fieldTemplate.toString();
					body+="\n";				
					
				}
			}
		}
				
		//Getters and setters
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
				if (!field.isReference()&&field.getDocRefType()==null){
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					body+=gettersSettersTemplate.toString()+"\n\n";
				}else if(field.getDocRefType()!=null){
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", Cyberconstants.stringType);
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					body+=gettersSettersTemplate.toString()+"\n\n";
				}				
				else{
						StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
						StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
						gettersSettersTemplate.setAttribute("entityName", CodeUtils.toCamelCase(field.getName()));
						gettersSettersTemplate.setAttribute("tableName", field.getName());
						gettersSettersTemplate.setAttribute("refEntityName", CodeUtils.toCamelCase(field.getRefType()));
						body+=gettersSettersTemplate.toString()+"\n\n";			
						
						StringTemplate template = templateGroup.getInstanceOf("getterSetter");
						template.setAttribute("type", Cybertables.stringType);
						template.setAttribute("name", field.getName());
						template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
						
						if (field.isEmbeddedReference()){
							StringTemplate addOps = new StringTemplate("\nfor ($referenceType$Details $referenceField$Details : $referenceField$List) {\n"+
									"if ($referenceField$Details.get$upperFieldName$().equals($referenceField$))\n"+
									"this.$referenceField$Details=$referenceField$Details;}\n");
							addOps.setAttribute("referenceType", CodeUtils.toCamelCase(field.getRefType()));
							addOps.setAttribute("referenceField", field.getName());
							addOps.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getDisplayField()));
							addOps.setAttribute("upperDisplayName", CodeUtils.toCamelCase(field.getName()));
							template.setAttribute("addOps", addOps.toString());
						}
						
						body+=template.toString()+"\n\n";
						
						if (field.isEmbeddedReference()){
							StringTemplate addGetterSetter=templateGroup.getInstanceOf("getterSetter");
							addGetterSetter.setAttribute("type", CodeUtils.toCamelCase(field.getRefType())+"Details");
							addGetterSetter.setAttribute("name", field.getName()+"Details");
							addGetterSetter.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName())+"Details");
							body+=addGetterSetter.toString()+"\n\n";
						}
				}
			}
			else{
				
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
					gettersSettersTemplate.setAttribute("entityName", CodeUtils.toCamelCase(compoundField.getTableName()));
					gettersSettersTemplate.setAttribute("tableName", compoundField.getTableName());
					gettersSettersTemplate.setAttribute("refEntityName", CodeUtils.toCamelCase(compoundField.getTableName()));
					body+=gettersSettersTemplate.toString()+"\n\n";			
					
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()));
					
					body+=template.toString()+"\n\n";
				}
			}
				
		}
		
		return body;
	}
	

}
