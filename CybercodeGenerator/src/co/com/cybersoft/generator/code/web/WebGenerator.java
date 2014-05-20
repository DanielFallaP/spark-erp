package co.com.cybersoft.generator.code.web;

import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersystems;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates the web layer of the application
 * @author daniel, Cybersoft
 *
 */
public class WebGenerator {
	
	private final Cybersystems cybersystems;
	
	public WebGenerator(Cybersystems cybersoft){
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
	
	private void generateSingletonController(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersystems.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("setController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/web/controller/"+table.getName(), "Set"+CodeUtil.toCamelCase(table.getName())+"Controller.java");
	}

	private void generateExcelController(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybersystems.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("excelController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ExcelController.java");
	}

	private void generateSearchByFieldController(Table table, Field field){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybersystems.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchByFieldController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("fieldName", field.getName());
		template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchBy"+CodeUtil.toCamelCase(field.getName())+"Controller.java");
	}
	
	private void generateSearchController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersystems.codePath+"web");
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
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchController.java");
	}
	
	private void generateCreateController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersystems.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("creationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));
		
		//default values
		template.setAttribute("setDefaults", generateDefaults(table));
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CreationController.java");
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
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersystems.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("modificationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ModificationController.java");
	}
	
	private void generateDomain(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersystems.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainClasses");
		String className = CodeUtil.toCamelCase(table.getName()+"Info");
		template.setAttribute("domainClassName", className);
		template.setAttribute("bodyDomainClass", generateDomainBody(table));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("referencesImports", generateDomainClassImports(table));
		CodeUtil.writeClass(template.toString(),Cybersystems.targetClassPath+"/web/domain/"+table.getName(), className+".java");
	}
	
	
	private String generateDomainClassImports(Table table){
		String imports="";
		List<Field> fields = table.getFields();
		
		//Check for validation constraints
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybersystems.stringType)){
				imports+="import org.hibernate.validator.constraints.Length;\n";
				break;
			}
				
		}
		
		for (Field field : fields) {
			if (!field.isReference() && field.getRequired() && field.getVisible() && field.getType().equals(Cybersystems.stringType)){
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
					if(field.getRequired() && field.getVisible() && (field.getType().equals(Cybersystems.integerType) || 
							field.getType().equals(Cybersystems.longType) || field.getType().equals(Cybersystems.doubleType))){
						imports+="import javax.validation.constraints.NotNull;\n";
						break;
					}
				}
			}
		
		for (Field field : fields) {
			if (field.getLength()!=null && (field.getType().equals(Cybersystems.integerType)
					||field.getType().equals(Cybersystems.longType) || field.getType().equals(Cybersystems.doubleType))){
				imports+="import org.hibernate.validator.constraints.Range;\n";
			}
		}
		
		for (Field field : fields) {
			
			if (field.isReference()){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				imports+=template.toString();
			}
		}
		
		if (CodeUtil.containsReferences(table)){
			
			imports+="import java.util.List;\n";
			imports+="import java.util.ArrayList;\n";
		}
		
		return imports;
	}
	
	private String generateControllerReferencesServicesDeclarations(Table table){
		
		String declarations="";
		List<Field> fields = table.getFields();
		HashSet<String> services = new HashSet<String>();
		for (Field field : fields) {
			if (field.isReference()){
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
		
		return declarations;
	}
	
	private String generateControllerReferenceImports(Table table){
		List<Field> fields = table.getFields();
		
		String  imports="";
		HashSet<String> referenceImports = new HashSet<String>();
		for (Field field : fields) {
			if (field.isReference() && !referenceImports.contains(field.getRefType())){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.core.services.$tableName$.$entityName$Service;\n"
						+ "import co.com.cybersoft.events.$tableName$.$entityName$PageEvent;\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("tableName", field.getRefType());
				imports+=template.toString();
				referenceImports.add(field.getRefType());
			}
		}
		
		return imports;
	}
	
	private String generateControllerReferencesLists(Table table){
		
		String lists="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.isReference()){
				if (!field.isEmbeddedReference()){
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
					StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybersystems.codePath+"web");
					StringTemplate template = templateGroup.getInstanceOf("setEmbeddedReferences");
					List<String> embeddedFields=field.getEmbeddedFields();
					String decl="";
					String requestParameters="";
					int i=0;
					for (String embeddedField : embeddedFields) {
						StringTemplate temp = new StringTemplate("EmbeddedField $embeddedField$Field=new EmbeddedField(\"$embeddedField$\", $embeddedFieldType$.class);\n");
						temp.setAttribute("embeddedField", CodeUtil.toCamelCase(embeddedField));
						temp.setAttribute("embeddedFieldType", CodeUtil.getFieldType(cybersystems,field.getRefType(), embeddedField));
						decl+=temp.toString();
						requestParameters+=embeddedField+"Field";
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
				}
			}
		}
		
		return lists;
	}
	
	private String generateModificationControllerReferencesLists(Table table){
		List<Field> fields = table.getFields();
		String lists="";
		
		for (Field field : fields) {
			if (field.isReference()){
				StringTemplate template = new StringTemplate("$entityName$PageEvent all$variableName$Event = $tableName$Service.requestAllBy$referenceField$();\n"
						+ "$parentTableName$Info.set$variableName$List(all$variableName$Event.get$entityName$List());\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("variableName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("tableName", field.getRefType());
				template.setAttribute("parentTableName", table.getName());
				template.setAttribute("referenceField", CodeUtil.toCamelCase(field.getDisplayField()));
				lists+=template.toString();
			}
		}
		
		return lists;
	}
	
	private String generateDomainBody(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		
		//Attributes
		for (Field field : fields) {
			if (!field.isReference() && !field.getType().equals(Cybersystems.booleanType)){
				if (field.getLength()!=null && field.getType().equals(Cybersystems.stringType)){
					body+="@Length(max="+field.getLength()+")\n";
				}
				if (field.getRequired()&&field.getVisible()&&(field.isReference()|| field.getType().equals(Cybersystems.stringType))){
					body+="@NotEmpty\n";
				}
				
				if (field.getRequired()&&field.getVisible()&&!field.isReference()&&(field.getType().equals(Cybersystems.integerType)
						||field.getType().equals(Cybersystems.longType) || field.getType().equals(Cybersystems.doubleType))){
					body+="@NotNull\n";
				}
				
				if (field.getLength()!=null && !field.isReference() && (field.getType().equals(Cybersystems.integerType)
						||field.getType().equals(Cybersystems.longType) || field.getType().equals(Cybersystems.doubleType))){
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
				fieldTemplate.setAttribute("type", Cybersystems.stringType);
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
				
		//Getters and setters
		for (Field field : fields) {
			if (!field.isReference()){
				StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersystems.codePath+"util");
				StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
				gettersSettersTemplate.setAttribute("type", field.getType());
				gettersSettersTemplate.setAttribute("name", field.getName());
				gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				body+=gettersSettersTemplate.toString()+"\n\n";
			}
			else{
				StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersystems.codePath+"util");
				StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
				gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
				gettersSettersTemplate.setAttribute("tableName", field.getName());
				gettersSettersTemplate.setAttribute("refEntityName", CodeUtil.toCamelCase(field.getRefType()));
				body+=gettersSettersTemplate.toString()+"\n\n";			
				
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", Cybersystems.stringType);
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
		
		return body;
	}
	
	
}
