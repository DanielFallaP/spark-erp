package co.com.cybersoft.generator.code.tables.web;

import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates the web layer of the application
 * @author daniel, Cybersoft
 *
 */
public class TableWebGenerator {
	
	private final Cybertables cybersystems;
	
	public TableWebGenerator(Cybertables cybersoft){
		this.cybersystems=cybersoft;
	}
	
	public void generate(){
		List<Table> tables = cybersystems.getTables();
		for (Table table : tables) {
			if (!table.getSingletonTable()){
				generateSearchController(table);
				generateModifyController(table);
				generateCreateController(table);
			}
			else{
				generateSingletonController(table);
			}
			generateCompoundReferenceControllers(table);

			generateDomain(table);
			List<Field> fields = table.getFields();
			for (Field field : fields) {
				if (CodeUtil.generateAutoComplete(field)){
					generateSearchByFieldController(table,field);
				}
			}
			if (!table.getLabelTable() && !table.getSingletonTable())
				generateExcelController(table);
		}
	}
	
	private void generateCompoundReferenceControllers(Table table) {
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.getCompoundReference()){
					StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.tableCodePath+"web");
					StringTemplate template = templateGroup.getInstanceOf("compoundFieldController");
					template.setAttribute("tableName", table.getName());
					template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					template.setAttribute("referenceServicesDeclarations", generateCompoundControllerReferenceServicesDeclarations(table));
					template.setAttribute("referenceServicesImports", generateCompoundControllerImports(table));
					template.setAttribute("compoundReferences", generateCompoundReferenceVariables(table));
					template.setAttribute("setCompoundLists", generateCompoundControllerLists(field,table));

					CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+CodeUtil.toCamelCase(field.getName())+"Controller.java");
			}
		}
	}


	private void generateSingletonController(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("setController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), "Set"+CodeUtil.toCamelCase(table.getName())+"Controller.java");
	}

	private void generateExcelController(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("excelController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ExcelController.java");
	}

	private void generateSearchByFieldController(Table table, Field field){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchByFieldController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("fieldName", field.getName());
		template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchBy"+CodeUtil.toCamelCase(field.getName())+"Controller.java");
	}
	
	private void generateSearchController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchController");
		template.setAttribute("entityName", table.getName());
		template.setAttribute("coreService", CodeUtil.toCamelCase(table.getName())+"Service");
		template.setAttribute("responseEvent", CodeUtil.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("requestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("entityUpperCaseName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("url", "/configuration/"+table.getName()+"/search"+CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("searchControllerName",CodeUtil.toCamelCase(table.getName())+"SearchController" );
		template.setAttribute("getListMethod", "get"+CodeUtil.toCamelCase(table.getName())+"Page");
		template.setAttribute("requestMethodName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("viewURL", "/configuration/"+table.getName()+"/search"+CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchController.java");
	}
	
	private void generateCreateController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("creationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		
		if (table.getName().equals("populatedPlace")){
			System.out.println();
		}
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));
		
		//default values
		template.setAttribute("setDefaults", generateDefaults(table));
		
		//compound reference lists
		template.setAttribute("setCompoundLists", generateControllerCompoundLists(table));

		CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CreationController.java");
	}

	
	private String generateCompoundReferenceParameters(Table table) {
		List<Field> compositeFields = table.getCompositeFields();
		String parameters="";
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtil.getCompoundKey(cybersystems, compositeField.getRefType());
			int i=0;
			for (Field field : compoundReference) {
				if (i<compoundReference.size()-1){
					StringTemplate stringTemplate = new StringTemplate("		String $fieldName$ = request.getParameter(\"$fieldName$\");\n");
					stringTemplate.setAttribute("fieldName", field.getName());
					parameters+=stringTemplate.toString();
				}
				
				i++;
			}
		}
		return parameters;
	}

	private String generateCompoundReferenceVariables(Table table) {
		List<Field> compositeFields = table.getCompositeFields();
		String variables="";
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtil.getCompoundKey(cybersystems, compositeField.getRefType());
			int i=0;
			for (Field field : compoundReference) {
				if (i<compoundReference.size()-1){
					StringTemplate stringTemplate = new StringTemplate("String $fieldName$ = request.getParameter(\"$fieldName$\");\n");
					stringTemplate.setAttribute("fieldName", field.getName());
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					variables+=stringTemplate.toString();
					
				}
				
				i++;
			}
		}
		return variables;
	}

	private String generateDefaults(Table table){
		List<Field> fields = table.getFields();
		String defaults="";
		for (Field field : fields) {
			if (field.getDefaultValue()!=null){
				StringTemplate stringTemplate = new StringTemplate("$tableName$Info.set$fieldName$($defaultValue$);\n");
				stringTemplate.setAttribute("tableName", table.getName());
				stringTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				stringTemplate.setAttribute("defaultValue", CodeUtil.getDefaultValue(field));
				defaults+=stringTemplate.toString();
			}
		}
		return defaults;
	}
	
	private void generateModifyController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("modificationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));

		//compound reference lists
		template.setAttribute("setCompoundLists", generateModificationControllerCompoundLists(table));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetTableClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ModificationController.java");
	}
	
	private String generateCompoundControllerLists(Field field, Table table) {
		String compound="";
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
					if (compoundKey.size()>1){
						StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
						StringTemplate stringTemplate = templateGroup.getInstanceOf("compoundControllerLists");
						String compoundOps="";
						String fieldStateEvents="";
						
						for (Field compoundField : compoundKey) {
							StringTemplate template = new StringTemplate("$upperFieldName$PageEvent all$upperFieldName$PageEvent = null;\n");
							template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
							fieldStateEvents+=template.toString();
							
						}
						
						for (int i=0;i<compoundKey.size()-1;i++) {
								Field compoundField=compoundKey.get(i);
								Field nextCompoundField=compoundKey.get(i+1);
								
							    StringTemplate template = templateGroup.getInstanceOf("compoundControllerOp");
								template.setAttribute("fieldName", compoundField.getName());
								template.setAttribute("nextField", nextCompoundField.getName());
								template.setAttribute("tableName", table.getName());
								template.setAttribute("nextUpperField", CodeUtil.toCamelCase(nextCompoundField.getName()));
								template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));


								compoundOps+=template.toString()+"\n";
						}
						
						stringTemplate.setAttribute("compoundOps", compoundOps);
						stringTemplate.setAttribute("fieldStateEvents", fieldStateEvents);
						compound=stringTemplate.toString();
						
					}
			}
		return compound;
	}
	
	
	private Object generateModificationControllerCompoundLists(Table table) {
		List<Field> fields = table.getFields();
		
		String compound="";
				
//		int suffix=1;
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				if (compoundKey.size()>1){
					StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
					StringTemplate stringTemplate = templateGroup.getInstanceOf("compoundReferenceList");
					String compoundOps="";
					String fieldStateEvents="";
					
					for (Field compoundField : compoundKey) {
						StringTemplate template = new StringTemplate("$upperFieldName$PageEvent all$upperFieldName$PageEvent = null;\n");
						template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						fieldStateEvents+=template.toString();
						
					}
					
					for (int i=0;i<compoundKey.size()-1;i++) {
							Field compoundField=compoundKey.get(i);
							Field nextCompoundField=compoundKey.get(i+1);
							
						    StringTemplate template = templateGroup.getInstanceOf("compoundOp");
							template.setAttribute("fieldName", compoundField.getName());
							template.setAttribute("nextField", nextCompoundField.getName());
							template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
							template.setAttribute("tableName", table.getName());
							template.setAttribute("nextUpperField", CodeUtil.toCamelCase(nextCompoundField.getName()));
							template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));


							compoundOps+=template.toString()+"\n";
					}
					
					stringTemplate.setAttribute("compoundOps", compoundOps);
					stringTemplate.setAttribute("fieldStateEvents", fieldStateEvents);
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperFirstField", CodeUtil.toCamelCase(compoundKey.get(0).getName()));
					stringTemplate.setAttribute("firstField", compoundKey.get(0).getName());
					stringTemplate.setAttribute("upperLastField", CodeUtil.toCamelCase(compoundKey.get(compoundKey.size()-1).getName()));

					
					compound+=stringTemplate.toString()+"\n";
					
//					suffix++;
				}
			}
		}
				
		return compound;
	}

	private String generateControllerCompoundLists(Table table) {
		List<Field> fields = table.getFields();
				
		String compound="";
			
//		int suffix=1;
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				if (compoundKey.size()>1){
					StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
					StringTemplate stringTemplate = templateGroup.getInstanceOf("compoundReferenceList");
					String compoundOps="";
					String fieldStateEvents="";
					
					for (Field compoundField : compoundKey) {
						StringTemplate template = new StringTemplate("$upperFieldName$PageEvent all$upperFieldName$PageEvent = null;\n");
						template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						fieldStateEvents+=template.toString();
						
					}
					
					for (int i=0;i<compoundKey.size()-1;i++) {
							Field compoundField=compoundKey.get(i);
							Field nextCompoundField=compoundKey.get(i+1);
							
						    StringTemplate template = templateGroup.getInstanceOf("compoundCreationOp");
							template.setAttribute("fieldName", compoundField.getName());
							template.setAttribute("nextField", nextCompoundField.getName());
							template.setAttribute("tableName", table.getName());
							template.setAttribute("nextUpperField", CodeUtil.toCamelCase(nextCompoundField.getName()));
							template.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));


							compoundOps+=template.toString()+"\n";
					}
					
					
					stringTemplate.setAttribute("compoundOps", compoundOps);
					stringTemplate.setAttribute("fieldStateEvents", fieldStateEvents);
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperFirstField", CodeUtil.toCamelCase(compoundKey.get(0).getName()));
					stringTemplate.setAttribute("firstField", compoundKey.get(0).getName());
					stringTemplate.setAttribute("upperLastField", CodeUtil.toCamelCase(compoundKey.get(compoundKey.size()-1).getName()));

					
					compound+=stringTemplate.toString()+"\n";
					
//					suffix++;
				}
			}
		}
				
		return compound;
	}

	private void generateDomain(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.tableCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainClasses");
		String className = CodeUtil.toCamelCase(table.getName()+"Info");
		template.setAttribute("domainClassName", className);
		template.setAttribute("bodyDomainClass", generateDomainBody(table));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("referencesImports", generateDomainClassImports(table));
		CodeUtil.writeClass(template.toString(),Cybertables.targetTableClassPath+"/web/domain/"+table.getName(), className+".java");
	}
	
	
	private String generateDomainClassImports(Table table){
		String imports="";
		List<Field> fields = table.getFields();
		
		//Check for validation constraints
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybertables.stringType)){
				imports+="import org.hibernate.validator.constraints.Length;\n";
				break;
			}
				
		}
		
		for (Field field : fields) {
			if (!field.isReference() && field.getRequired() && field.getVisible() && field.getType().equals(Cybertables.stringType)){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
			if (field.isReference()&&field.getRequired()){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
		}
		
			for (Field field : fields) {
				if (!field.isReference()){
					if(field.getRequired() && field.getVisible() && (field.getType().equals(Cybertables.integerType) || 
							field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
						imports+="import javax.validation.constraints.NotNull;\n";
						break;
					}
				}
			}
		
		for (Field field : fields) {
			if (field.getLength()!=null && (field.getType().equals(Cybertables.integerType)
					||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
				imports+="import org.hibernate.validator.constraints.Range;\n";
			}
		}
		
		for (Field field : fields) {
			
			if (field.isReference()){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				imports+=template.toString();
			}
		}
		
		if (CodeUtil.containsReferences(table)){
			
			imports+="import java.util.List;\n";
			imports+="import java.util.ArrayList;\n";
		}
		
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate stringTemplate = new StringTemplate("import co.com.cybersoft.tables.core.domain.$fieldName$Details;\n");
					stringTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					imports+=stringTemplate.toString();
				}
			}
		}
		
		return imports;
	}
	
	private String generateCompoundControllerReferenceServicesDeclarations(Table table){
		String declarations="";

		List<Field> compositeFields = table.getCompositeFields();
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtil.getCompoundKey(cybersystems, compositeField.getRefType());
			for (Field field : compoundReference) {
					StringTemplate template = new StringTemplate("@Autowired\n"
							+ "	private $entityName$Service $tableName$Service;");
					template.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getName());
					declarations+=template.toString()+"\n\n";
				}
		}
		
		
		return declarations;
	}
	
	private String generateControllerReferencesServicesDeclarations(Table table){
		
		String declarations="";
		List<Field> fields = table.getFields();
		HashSet<String> services = new HashSet<String>();
		for (Field field : fields) {
			if (field.isReference() && !field.getCompoundReference()){
				if (!services.contains(field.getRefType())){
					StringTemplate template = new StringTemplate("@Autowired\n"
							+ "	private $entityName$Service $tableName$Service;");
					template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
					template.setAttribute("tableName", field.getRefType());
					declarations+=template.toString()+"\n\n";
					services.add(field.getRefType());
				}
			}
		}
		
		List<Field> compositeFields = table.getCompositeFields();
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtil.getCompoundKey(cybersystems, compositeField.getRefType());
			for (Field field : compoundReference) {
				if (!services.contains(field.getName())){
					StringTemplate template = new StringTemplate("@Autowired\n"
							+ "	private $entityName$Service $tableName$Service;");
					template.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getName());
					declarations+=template.toString()+"\n\n";
					services.add(field.getName());
				}
			}
		}
		
		return declarations;
	}
	
	private String generateCompoundControllerImports(Table table){
		String  imports="";
		HashSet<String> referenceImports = new HashSet<String>();
		List<Field> compositeFields = table.getCompositeFields();
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtil.getCompoundKey(cybersystems, compositeField.getRefType());
			for (Field field : compoundReference) {
				if (!referenceImports.contains(field.getName())){
					StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;\n"
							+ "import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;\n");
					template.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getName());
					imports+=template.toString();
					referenceImports.add(field.getName());
				}
			}
		}
		
		return imports;
	}
	
	private String generateControllerReferenceImports(Table table){
		List<Field> fields = table.getFields();
		
		String  imports="";
		HashSet<String> referenceImports = new HashSet<String>();
		for (Field field : fields) {
			if (field.isReference() && !referenceImports.contains(field.getRefType())){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;\n"
						+ "import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("tableName", field.getRefType());
				imports+=template.toString();
				referenceImports.add(field.getRefType());
			}
		}
		
		List<Field> compositeFields = table.getCompositeFields();
		for (Field compositeField : compositeFields) {
			List<Field> compoundReference = CodeUtil.getCompoundKey(cybersystems, compositeField.getRefType());
			for (Field field : compoundReference) {
				if (!referenceImports.contains(field.getName())){
					StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;\n"
							+ "import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;\n");
					template.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getName());
					imports+=template.toString();
					referenceImports.add(field.getName());
				}
			}
		}
		
		return imports;
	}
	
	private String generateControllerReferencesLists(Table table){
		
		String lists="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.isReference()){
				if (!field.isEmbeddedReference() && !field.getCompoundReference() && !CodeUtil.generateAutoCompleteReference(field)){
					StringTemplate template = new StringTemplate("$entityName$PageEvent all$variableName$Event = $tableName$Service.requestAllBy$referenceField$();\n"
							+ "$parentTableName$Info.set$variableName$List(all$variableName$Event.get$entityName$List());\n");
					template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
					template.setAttribute("variableName", CodeUtil.toCamelCase(field.getName()));
					template.setAttribute("tableName", field.getRefType());
					template.setAttribute("parentTableName", table.getName());
					template.setAttribute("referenceField", CodeUtil.toCamelCase(field.getDisplayField()));
					lists+=template.toString();
				}
				else{
					if (field.isEmbeddedReference() && !CodeUtil.generateAutoCompleteReference(field)){
						StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.tableCodePath+"web");
						StringTemplate template = templateGroup.getInstanceOf("setEmbeddedReferences");
						List<String> embeddedFields=field.getEmbeddedFields();
						String decl="";
						String requestParameters="";
						int i=0;
						for (String embeddedField : embeddedFields) {
							StringTemplate temp = new StringTemplate("EmbeddedField $embeddedField$Field=new EmbeddedField(\"$fieldName$\", $embeddedFieldType$.class);\n");
							temp.setAttribute("embeddedField", embeddedField+CodeUtil.toCamelCase(field.getName()));
							temp.setAttribute("fieldName", embeddedField);
							temp.setAttribute("embeddedFieldType", CodeUtil.getFieldType(cybersystems,field.getRefType(), embeddedField));
							decl+=temp.toString();
							requestParameters+=embeddedField+CodeUtil.toCamelCase(field.getName())+"Field";
							if (i!=embeddedFields.size()-1){
								requestParameters+=",";
							}
							i++;
						}
						
						template.setAttribute("embeddedFields", requestParameters);
						template.setAttribute("embeddedFieldsDeclarations", decl);
						template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
						template.setAttribute("variableName", CodeUtil.toCamelCase(field.getName()));
						template.setAttribute("tableName", field.getRefType());
						template.setAttribute("parentTableName", table.getName());
						template.setAttribute("referenceField", CodeUtil.toCamelCase(field.getDisplayField()));
						lists+=template.toString();
					}
					
					
				}
			}
		}
		
		return lists;
	}
	
	private String generateDomainBody(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		
		//Attributes
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
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
						body+="@Range(max="+CodeUtil.getMaxNumber(field.getLength())+")\n";
					}
				}
				
				if (field.isReference()&&field.getRequired()){
					body+="@NotEmpty\n";
				}
				
				StringTemplate fieldTemplate;
				
				if (!field.isReference()){
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", field.getType());
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
					fieldTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
					fieldTemplate.setAttribute("tableName", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";				
					
					if (field.isEmbeddedReference()){
						fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
						fieldTemplate.setAttribute("type", CodeUtil.toCamelCase(field.getRefType())+"Details");
						fieldTemplate.setAttribute("name", field.getName()+"Details");
						body+=fieldTemplate.toString();
						body+="\n";				
					}
					
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				for (Field compoundField : compoundKey) {
					if (field.getRequired())
						body+="@NotEmpty\n";
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.stringType);
					fieldTemplate.setAttribute("name", compoundField.getName());
					body+=fieldTemplate.toString();
					body+="\n";
					
					fieldTemplate = new StringTemplate("private List<$entityName$Details> $tableName$List;");
					fieldTemplate.setAttribute("entityName", CodeUtil.toCamelCase(compoundField.getTableName()));
					fieldTemplate.setAttribute("tableName", compoundField.getTableName());
					body+=fieldTemplate.toString();
					body+="\n";				
					
				}
			}
		}
				
		//Getters and setters
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
				if (!field.isReference()){
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
					body+=gettersSettersTemplate.toString()+"\n\n";
				}
				else{
						StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
						StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
						gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
						gettersSettersTemplate.setAttribute("tableName", field.getName());
						gettersSettersTemplate.setAttribute("refEntityName", CodeUtil.toCamelCase(field.getRefType()));
						body+=gettersSettersTemplate.toString()+"\n\n";			
						
						StringTemplate template = templateGroup.getInstanceOf("getterSetter");
						template.setAttribute("type", Cybertables.stringType);
						template.setAttribute("name", field.getName());
						template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
						
						if (field.isEmbeddedReference()){
							StringTemplate addOps = new StringTemplate("\nfor ($referenceType$Details $referenceField$Details : $referenceField$List) {\n"+
									"if ($referenceField$Details.get$upperFieldName$().equals($referenceField$))\n"+
									"this.$referenceField$Details=$referenceField$Details;}\n");
							addOps.setAttribute("referenceType", CodeUtil.toCamelCase(field.getRefType()));
							addOps.setAttribute("referenceField", field.getName());
							addOps.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
							addOps.setAttribute("upperDisplayName", CodeUtil.toCamelCase(field.getName()));
							template.setAttribute("addOps", addOps.toString());
						}
						
						body+=template.toString()+"\n\n";
						
						if (field.isEmbeddedReference()){
							StringTemplate addGetterSetter=templateGroup.getInstanceOf("getterSetter");
							addGetterSetter.setAttribute("type", CodeUtil.toCamelCase(field.getRefType())+"Details");
							addGetterSetter.setAttribute("name", field.getName()+"Details");
							addGetterSetter.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName())+"Details");
							body+=addGetterSetter.toString()+"\n\n";
						}
				}
			}
			else{
				
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersystems, field.getRefType());
				for (Field compoundField : compoundKey) {
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
					gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(compoundField.getTableName()));
					gettersSettersTemplate.setAttribute("tableName", compoundField.getTableName());
					gettersSettersTemplate.setAttribute("refEntityName", CodeUtil.toCamelCase(compoundField.getTableName()));
					body+=gettersSettersTemplate.toString()+"\n\n";			
					
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					
					body+=template.toString()+"\n\n";
				}
			}
				
		}
		
		return body;
	}
	
	
}
