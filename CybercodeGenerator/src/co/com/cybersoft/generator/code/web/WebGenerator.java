package co.com.cybersoft.generator.code.web;

import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates the web layer of the application
 * @author daniel, Cybersoft
 *
 */
public class WebGenerator {

	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateSearchController(table);
			generateModifyController(table);
			generateCreateController(table);
			generateDomain(table);
			generateSearchByCodeController(table);
			if (CodeUtil.containsDescriptionField(table))
				generateSearchByDescriptionController(table);
			if (!table.getLabelTable())
				generateExcelController(table);
		}
	}
	
	private void generateExcelController(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("excelController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ExcelController.java");
	}

	private void generateSearchByCodeController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchByCodeController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchByCodeController.java");
	}
	
	private void generateSearchByDescriptionController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchByDescriptionController");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchByDescriptionController.java");
	}
	
	private void generateSearchController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersoft.codePath+"web");
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
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchController.java");
	}
	
	private void generateCreateController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("creationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateControllerReferencesLists(table));
		
		if (CodeUtil.getCodeType(table).equals(Cybersoft.stringType))
			template.setAttribute("code", "code");
		else
			template.setAttribute("code", "Integer.parseInt(code)");
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CreationController.java");
	}
	
	private void generateModifyController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("modificationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		template.setAttribute("codyType", CodeUtil.getCodeType(table));
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateModificationControllerReferencesLists(table));
		
		template.setAttribute("codeType", CodeUtil.getCodeType(table));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ModificationController.java");
	}
	
	private void generateDomain(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainClasses");
		String className = CodeUtil.toCamelCase(table.getName()+"Info");
		template.setAttribute("domainClassName", className);
		template.setAttribute("bodyDomainClass", generateDomainBody(table));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("referencesImports", generateDomainClassImports(table));
		CodeUtil.writeClass(template.toString(),Cybersoft.targetClassPath+"/web/domain/"+table.getName(), className+".java");
	}
	
	
	private String generateDomainClassImports(Table table){
		String imports="";
		List<Field> fields = table.getFields();
		
		//Check for validation constraints
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybersoft.stringType)){
				imports+="import org.hibernate.validator.constraints.Length;\n";
				break;
			}
				
		}
		
		for (Field field : fields) {
			if (!field.isReference() && field.getRequired() && field.getVisible() && field.getType().equals(Cybersoft.stringType)){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
			if (field.isReference()&&field.getRequired()){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
		}
		
		try {
			for (Field field : fields) {
				if(field.getRequired() && field.getVisible() && (field.getType().equals(Cybersoft.integerType) || 
						field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.stringType))){
					imports+="import javax.validation.constraints.NotNull;\n";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Field field : fields) {
			if (field.getLength()!=null && (field.getType().equals(Cybersoft.integerType)
					||field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.doubleType))){
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
				StringTemplate template = new StringTemplate("$entityName$PageEvent all$variableName$Event = $tableName$Service.requestAll();\n"
						+ "$parentTableName$Info.set$variableName$List(all$variableName$Event.get$entityName$List());\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("variableName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("tableName", field.getRefType());
				template.setAttribute("parentTableName", table.getName());
				
				lists+=template.toString();
			}
		}
		
		return lists;
	}
	
	private String generateModificationControllerReferencesLists(Table table){
		List<Field> fields = table.getFields();
		String lists="";
		
		for (Field field : fields) {
			if (field.isReference()){
				StringTemplate template = new StringTemplate("$entityName$PageEvent all$variableName$Event = $tableName$Service.requestAll();\n"
						+ "$parentTableName$Info.set$variableName$List(all$variableName$Event.get$entityName$List());\n"+
						"$parentTableName$Info.rearrange$variableName$List($parentTableName$Info.get$variableName$());\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("variableName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("tableName", field.getRefType());
				template.setAttribute("parentTableName", table.getName());
				
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
			if (!field.isReference() && !field.getType().equals(Cybersoft.booleanType)){
				if (field.getLength()!=null && field.getType().equals(Cybersoft.stringType)){
					body+="@Length(max="+field.getLength()+")\n";
				}
				if (field.getRequired()&&field.getVisible()&&(field.isReference()|| field.getType().equals(Cybersoft.stringType))){
					body+="@NotEmpty\n";
				}
				
				if (field.getRequired()&&field.getVisible()&&!field.isReference()&&(field.getType().equals(Cybersoft.integerType)
						||field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.doubleType))){
					body+="@NotNull\n";
				}
				
				if (field.getLength()!=null && !field.isReference() && (field.getType().equals(Cybersoft.integerType)
						||field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.doubleType))){
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
				fieldTemplate.setAttribute("type", Cybersoft.stringType);
				fieldTemplate.setAttribute("name", field.getName());
				body+=fieldTemplate.toString();
				body+="\n";

				fieldTemplate = new StringTemplate("private List<$entityName$Details> $tableName$List;");
				fieldTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				fieldTemplate.setAttribute("tableName", field.getName());
				body+=fieldTemplate.toString();
				body+="\n";				
			}
		}
				
		//Getters and setters
		for (Field field : fields) {
			if (!field.isReference()){
				StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"util");
				StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
				gettersSettersTemplate.setAttribute("type", field.getType());
				gettersSettersTemplate.setAttribute("name", field.getName());
				gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				body+=gettersSettersTemplate.toString()+"\n\n";
			}
			else{
				StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"util");
				StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
				gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
				gettersSettersTemplate.setAttribute("tableName", field.getName());
				gettersSettersTemplate.setAttribute("refEntityName", CodeUtil.toCamelCase(field.getRefType()));
				body+=gettersSettersTemplate.toString()+"\n\n";			
				
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", Cybersoft.stringType);
				template.setAttribute("name", field.getName());
				template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				body+=template.toString()+"\n\n";			
			}
		}
		
		//Reference list rearrangement
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybersoft.codePath+"web");
		for (Field field : fields) {
			if (field.isReference()){
				StringTemplate template = templateGroup.getInstanceOf("referenceFieldRearrangement");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("tableName", field.getRefType());
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("refFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
				body+=template.toString();
				body+="\n";
			}
		}		
		
		return body;
	}
	
	
}
