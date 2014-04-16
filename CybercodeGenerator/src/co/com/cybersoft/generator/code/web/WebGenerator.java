package co.com.cybersoft.generator.code.web;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.ReferenceField;
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
		}
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
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CreationController.java");
	}
	
	private void generateModifyController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("modificationController");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));		
		template.setAttribute("tableName", table.getName());
		
		//imports for references
		template.setAttribute("referenceServicesImports", generateControllerReferenceImports(table));
		
		//reference services declarations
		template.setAttribute("referenceServicesDeclarations", generateControllerReferencesServicesDeclarations(table));
		
		//reference lists
		template.setAttribute("setReferencesLists", generateModificationControllerReferencesLists(table));
		
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
		List<ReferenceField> references = table.getReferences();
		List<Field> fields = table.getFields();
		
		//Check for validation constraints
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybersoft.stringType)){
				imports+="import org.hibernate.validator.constraints.Length;\n";
				break;
			}
				
		}
		
		for (Field field : fields) {
			if (field.getRequired() && field.getVisible() && field.getType().equals(Cybersoft.stringType)){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
		}
		
		for (Field field : fields) {
			if(field.getRequired() && field.getVisible() && (field.getType().equals(Cybersoft.integerType) || 
					field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.stringType))){
				imports+="import javax.validation.constraints.NotNull;\n";
				break;
			}
		}
		
		for (Field field : fields) {
			if (field.getLength()!=null && (field.getType().equals(Cybersoft.integerType)
					||field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.doubleType))){
				imports+="import import org.hibernate.validator.constraints.Range;\n";
			}
		}
		
		for (ReferenceField field : references) {
			StringTemplate template = new StringTemplate("import co.com.cybersoft.core.domain.$entityName$Details;\n");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			imports+=template.toString();
		}
		
		if (!references.isEmpty()){
			
			imports+="import java.util.List;\n";
			imports+="import java.util.ArrayList;\n";
		}
		
		boolean containsDate=false;
		for (Field field : fields) {
			if (field.getType().equals(Cybersoft.dateType)){
				containsDate=true;
			}
		}
		
		if (containsDate){
			imports+="import java.util.Date;\n";
		}
		
		return imports;
	}
	
	private String generateControllerReferencesServicesDeclarations(Table table){
		List<ReferenceField> references = table.getReferences();
		
		String declarations="";
		
		for (ReferenceField field : references) {
			StringTemplate template = new StringTemplate("@Autowired\n"
					+ "	private $entityName$Service $tableName$Service;");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			template.setAttribute("tableName", field.getRefType());
			declarations+=template.toString()+"\n\n";
		}
		
		return declarations;
	}
	
	private String generateControllerReferenceImports(Table table){
		List<ReferenceField> references = table.getReferences();
		
		String  imports="";
		for (ReferenceField field : references) {
			StringTemplate template = new StringTemplate("import co.com.cybersoft.core.services.$tableName$.$entityName$Service;\n"
					+ "import co.com.cybersoft.events.$tableName$.$entityName$PageEvent;\n");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			template.setAttribute("tableName", field.getRefType());
			imports+=template.toString();
		}
		
		return imports;
	}
	
	private String generateControllerReferencesLists(Table table){
		List<ReferenceField> references = table.getReferences();
		
		String lists="";
		
		for (ReferenceField field : references) {
			StringTemplate template = new StringTemplate("$entityName$PageEvent all$entityName$Event = $tableName$Service.requestAll();\n"
					+ "$parentTableName$Info.set$entityName$List(all$entityName$Event.get$entityName$List());\n");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			template.setAttribute("tableName", field.getRefType());
			template.setAttribute("parentTableName", table.getName());
			
			lists+=template.toString();
		}
		
		return lists;
	}
	
	private String generateModificationControllerReferencesLists(Table table){
List<ReferenceField> references = table.getReferences();
		
		String lists="";
		
		for (ReferenceField field : references) {
			StringTemplate template = new StringTemplate("$entityName$PageEvent all$entityName$Event = $tableName$Service.requestAll();\n"
					+ "$parentTableName$Info.set$entityName$List(all$entityName$Event.get$entityName$List());\n"+
					"$parentTableName$Info.rearrange$entityName$List($parentTableName$Info.get$entityName$());\n");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			template.setAttribute("tableName", field.getRefType());
			template.setAttribute("parentTableName", table.getName());
			
			lists+=template.toString();
		}
		
		return lists;
	}
	
	private String generateDomainBody(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		List<ReferenceField> references = table.getReferences();
		
		//Attributes
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybersoft.stringType)){
				body+="@Length(max="+field.getLength()+")\n";
			}
			if (field.getRequired()&&field.getVisible()&&field.getType().equals(Cybersoft.stringType)){
				body+="@NotEmpty\n";
			}
			
			if (field.getRequired()&&field.getVisible()&&(field.getType().equals(Cybersoft.integerType)
					||field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.doubleType))){
				body+="@NotNull\n";
			}
			
			if (field.getLength()!=null && (field.getType().equals(Cybersoft.integerType)
					||field.getType().equals(Cybersoft.longType) || field.getType().equals(Cybersoft.doubleType))){
				body+="@Range(max="+CodeUtil.getMaxNumber(field.getLength())+")\n";
			}
			
			StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
			fieldTemplate.setAttribute("type", field.getType());
			fieldTemplate.setAttribute("name", field.getName());
			body+=fieldTemplate.toString();
			body+="\n";
		}
		
		//Referencing fields declarations
		for (ReferenceField field : references) {
			StringTemplate template = new StringTemplate("private List<$entityName$Details> $tableName$List;");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
			template.setAttribute("tableName", field.getName());
			body+=template.toString();
			body+="\n";
			
			template = new StringTemplate("private $type$ $name$;\n\n");
			template.setAttribute("type", Cybersoft.stringType);
			template.setAttribute("name", field.getName());
			body+=template.toString();
			body+="\n";
		}
		
		//Getters and setters
		for (Field field : fields) {
			StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"util");
			StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
			gettersSettersTemplate.setAttribute("type", field.getType());
			gettersSettersTemplate.setAttribute("name", field.getName());
			gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			body+=gettersSettersTemplate.toString()+"\n\n";
		}
		for (ReferenceField field : references) {
			StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"util");
			StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
			gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			gettersSettersTemplate.setAttribute("tableName", field.getName());
			body+=gettersSettersTemplate.toString()+"\n\n";			
			
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", Cybersoft.stringType);
			template.setAttribute("name", field.getName());
			template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getRefType()));
			body+=template.toString()+"\n\n";			
		}
		
		//Reference list rearrangement
		StringTemplateGroup templateGroup = new StringTemplateGroup("views", Cybersoft.codePath+"web");
		for (ReferenceField field : references) {
			StringTemplate template = templateGroup.getInstanceOf("referenceFieldRearrangement");
			template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
			template.setAttribute("tableName", field.getRefType());
			template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getDisplayField()));
			body+=template.toString();
			body+="\n";
		}		
		
		return body;
	}
	
	
}
