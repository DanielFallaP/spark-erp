package co.com.cybersoft.generator.code.core;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Spark;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class CoreGenerator {
	
	private final Spark spark;
	
	public CoreGenerator(Spark cybersoft){
		this.spark=cybersoft;
	}

	public void generate(){
		List<Table> tables = spark.getTables();
		for (Table table : tables) {
			generateCoreServiceInterface(table);
			generateCoreServiceImplementation(table);
			generateCoreDomainClass(table);
		}
	}
	
	private void generateCoreServiceInterface(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Spark.codePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreServiceInterface");
		String className=CodeUtil.toCamelCase(table.getName())+"Service";
		template.setAttribute("entityName", table.getName());
		template.setAttribute("createEvent", "Create"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleResponseEvent", CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allResponseEvent", CodeUtil.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("modifyEvent", CodeUtil.toCamelCase(table.getName())+"ModificationEvent");
		template.setAttribute("singleRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("className", className);
		template.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("upperEntityName", CodeUtil.toCamelCase(table.getName()));
		
		//Generate all reference methods for all fields that can be referenced by other tables
		List<Field> fields = table.getFields();
		String requestDeclarations="";
		String autocompleteRequests="";
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtil.generateQueryForReferences(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityUppercaseName$PageEvent requestAllBy$referencedField$(EmbeddedField... fields) throws Exception;\n");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("referencedField", CodeUtil.toCamelCase(field.getName()));
					requestDeclarations+=stringTemplate.toString();
				}
				
				if (CodeUtil.generateAutoComplete(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityUppercaseName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception;");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					stringTemplate.setAttribute("fieldName", field.getName());
					
					autocompleteRequests+=stringTemplate.toString();
				}
				
				if (field.isReference()){
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
					stringTemplate.setAttribute("fieldName", field.getDisplayField());
					requestDeclarations+=stringTemplate.toString();
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(spark, field.getRefType());
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())){
						StringTemplate stringTemplate = new StringTemplate("$entityName$Event requestAllBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						requestDeclarations+=stringTemplate.toString();
					}
					
				}
			}
		}
		template.setAttribute("requestAll", requestDeclarations);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/core/services/"+table.getName(), className+".java");
	}
	
	private void generateCoreServiceImplementation(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Spark.codePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreServiceImpl");
		String className=CodeUtil.toCamelCase(table.getName())+"Service";
		template.setAttribute("entityName", table.getName());
		template.setAttribute("createdEvent", CodeUtil.toCamelCase(table.getName())+"CreatedEvent");
		template.setAttribute("createEvent", "Create"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleResponseEvent", CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allResponseEvent", CodeUtil.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("modifyEvent", CodeUtil.toCamelCase(table.getName())+"ModificationEvent");
		template.setAttribute("singleRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("upperEntityName", CodeUtil.toCamelCase(table.getName()));
		
		//Generate all reference methods for all fields that can be referenced by other tables
		String requestImpl="";
		String autocompleteRequest="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtil.generateQueryForReferences(field)){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("requestAll");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("entityName", table.getName());
					stringTemplate.setAttribute("referencedField", CodeUtil.toCamelCase(field.getName()));
					requestImpl+=stringTemplate.toString();
				}
				
				if (CodeUtil.generateAutoComplete(field)){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("autocompleteRequest");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("entityName", table.getName());
					stringTemplate.setAttribute("fieldName", field.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					autocompleteRequest+=stringTemplate.toString();
				}
				
				if (field.isReference()){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
					stringTemplate.setAttribute("fieldName", field.getDisplayField());
					stringTemplate.setAttribute("tableName", table.getName());
					requestImpl+=stringTemplate.toString();
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(spark, field.getRefType());
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
						stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						stringTemplate.setAttribute("tableName", table.getName());
						requestImpl+=stringTemplate.toString();
					}
					
				}
			}
		}
		
		
		
		template.setAttribute("requestAll", requestImpl);
		template.setAttribute("autocompleteRequest", autocompleteRequest);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/core/services/"+table.getName(), className+"Impl.java");
	}
	
	private void generateCoreDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Spark.codePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreDomain");
		template.setAttribute("fieldDeclaration", CodeUtil.getFieldDeclarations(table));
		template.setAttribute("gettersAndSetters", CodeUtil.getGettersAndSetters(table));
		template.setAttribute("coreDomainClass", CodeUtil.toCamelCase(table.getName())+"Details");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		
		//Write embedded references transformations
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.isEmbeddedReference()){
				StringTemplate subTemp = new StringTemplate("if (entity.get$upperFieldName$Entity()!=null){\n"
						+ "$fieldName$Details = new $refType$Details();\n"+
								"BeanUtils.copyProperties(entity.get$upperFieldName$Entity(), $fieldName$Details);}\n");
				subTemp.setAttribute("fieldName", field.getName());
				subTemp.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				subTemp.setAttribute("refType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("embeddedReferences", subTemp.toString());
			}
		}
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/core/domain", CodeUtil.toCamelCase(table.getName())+"Details.java");
	}
}
