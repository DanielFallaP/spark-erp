package co.com.cybersoft.generator.code.tables.core;

import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class TableCoreGenerator {
	
	private final Cybertables cybertables;
	
	public TableCoreGenerator(Cybertables cybersoft){
		this.cybertables=cybersoft;
	}

	public void generate(){
		List<Table> tables = cybertables.getTables();
		for (Table table : tables) {
			generateCoreServiceInterface(table);
			generateCoreServiceImplementation(table);
			generateCoreDomainClass(table);
			generateExcelServiceImp(table);
			generateExcelServiceInterface(table);
		}
	}
	
	private void generateExcelServiceInterface(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybertables.tableCodePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("excelReportingServiceInterface");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("module", cybertables.getModuleName());
		
		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/core/services/"+table.getName()).replace("{{module}}", cybertables.getModuleName()), CodeUtils.toCamelCase(table.getName())+"ReportingService.java");
		
	}

	private void generateExcelServiceImp(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybertables.tableCodePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("excelReportingServiceImpl");
		template.setAttribute("tableName", table.getName());
		template.setAttribute("module", cybertables.getModuleName());
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		
		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/core/services/"+table.getName()).replace("{{module}}", cybertables.getModuleName()), CodeUtils.toCamelCase(table.getName())+"ReportingServiceImpl.java");
	}

	private void generateCoreServiceInterface(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybertables.tableCodePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreServiceInterface");
		String className=CodeUtils.toCamelCase(table.getName())+"Service";
		template.setAttribute("entityName", table.getName());
		template.setAttribute("createEvent", "Create"+CodeUtils.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleResponseEvent", CodeUtils.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allResponseEvent", CodeUtils.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("modifyEvent", CodeUtils.toCamelCase(table.getName())+"ModificationEvent");
		template.setAttribute("singleRequestEvent", "Request"+CodeUtils.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allRequestEvent", "Request"+CodeUtils.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("className", className);
		template.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("upperEntityName", CodeUtils.toCamelCase(table.getName()));
		
		//Generate all reference methods for all fields that can be referenced by other tables
		List<Field> fields = table.getFields();
		String requestDeclarations="";
		String autocompleteRequests="";
		
		HashSet<String> references = new HashSet<String>();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtils.generateQueryForReferences(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityUppercaseName$PageEvent requestAllBy$referencedField$(EmbeddedField... fields) throws Exception;\n");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("referencedField", CodeUtils.toCamelCase(field.getName()));
					requestDeclarations+=stringTemplate.toString();
				}
				
				if (CodeUtils.generateAutoComplete(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityUppercaseName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception;");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					stringTemplate.setAttribute("fieldName", field.getName());
					
					autocompleteRequests+=stringTemplate.toString();
				}
				
				if (i<fields.size()-1){
					Field keyCompound = fields.get(i+1);
					if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField()) && keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getDisplayField()));
						stringTemplate.setAttribute("fieldName", field.getDisplayField());
						requestDeclarations+=stringTemplate.toString();
						
						references.add(field.getRefType()+field.getDisplayField());
					}
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						requestDeclarations+=stringTemplate.toString();
					}
					
					StringTemplate stringTemplate = new StringTemplate("$entityUppercaseName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception;");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					stringTemplate.setAttribute("fieldName", compoundField.getName());
					
					autocompleteRequests+=stringTemplate.toString()+"\n";					
				}
			}
			i++;
		}
		template.setAttribute("requestAll", requestDeclarations);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		template.setAttribute("module", cybertables.getModuleName());
			
		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/core/services/"+table.getName()).replace("{{module}}", cybertables.getModuleName()), className+".java");
	}
	
	
	private void generateCoreServiceImplementation(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybertables.tableCodePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreServiceImpl");
		String className=CodeUtils.toCamelCase(table.getName())+"Service";
		template.setAttribute("entityName", table.getName());
		template.setAttribute("createdEvent", CodeUtils.toCamelCase(table.getName())+"CreatedEvent");
		template.setAttribute("createEvent", "Create"+CodeUtils.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleResponseEvent", CodeUtils.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allResponseEvent", CodeUtils.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("modifyEvent", CodeUtils.toCamelCase(table.getName())+"ModificationEvent");
		template.setAttribute("singleRequestEvent", "Request"+CodeUtils.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allRequestEvent", "Request"+CodeUtils.toCamelCase(table.getName())+"PageEvent");
		template.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("upperEntityName", CodeUtils.toCamelCase(table.getName()));
		
		//Generate all reference methods for all fields that can be referenced by other tables
		HashSet<String> references = new HashSet<String>();
		String requestImpl="";
		String autocompleteRequest="";
		List<Field> fields = table.getFields();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtils.generateQueryForReferences(field)){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("requestAll");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("entityName", table.getName());
					stringTemplate.setAttribute("referencedField", CodeUtils.toCamelCase(field.getName()));
					requestImpl+=stringTemplate.toString();
				}
				
				if (CodeUtils.generateAutoComplete(field)){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("autocompleteRequest");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("entityName", table.getName());
					stringTemplate.setAttribute("fieldName", field.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					autocompleteRequest+=stringTemplate.toString();
				}
				
				if (i<fields.size()-1){
					Field keyCompound = fields.get(i+1);
					if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField()) && keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getDisplayField()));
						stringTemplate.setAttribute("fieldName", field.getDisplayField());
						stringTemplate.setAttribute("tableName", table.getName());
						requestImpl+=stringTemplate.toString();
						
						references.add(field.getRefType()+field.getDisplayField());
					}
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						stringTemplate.setAttribute("tableName", table.getName());
						requestImpl+=stringTemplate.toString();
					}
					
					StringTemplate stringTemplate = templateGroup.getInstanceOf("autocompleteRequest");
					stringTemplate.setAttribute("entityUppercaseName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("entityName", table.getName());
					stringTemplate.setAttribute("fieldName", compoundField.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					autocompleteRequest+=stringTemplate.toString();
					
				}
			}
			i++;
		}
		
		
		
		template.setAttribute("requestAll", requestImpl);
		template.setAttribute("autocompleteRequest", autocompleteRequest);
		template.setAttribute("module", cybertables.getModuleName());
		
		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/core/services/"+table.getName()).replace("{{module}}", cybertables.getModuleName()), className+"Impl.java");
	}
	
	private void generateCoreDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybertables.tableCodePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreDomain");
		template.setAttribute("fieldDeclaration", CodeUtils.getFieldDeclarations(cybertables,table));
		template.setAttribute("gettersAndSetters", CodeUtils.getGettersAndSetters(cybertables,table));
		template.setAttribute("coreDomainClass", CodeUtils.toCamelCase(table.getName())+"Details");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("imports", generateDomainImports(table));
		template.setAttribute("references", generateReferences(table));
		
		//Write embedded references transformations
//		List<Field> fields = table.getFields();
//		for (Field field : fields) {
//			if (field.isEmbeddedReference()){
//				StringTemplate subTemp = new StringTemplate("if (entity.get$upperFieldName$Entity()!=null){\n"
//						+ "$fieldName$Details = new $refType$Details();\n"+
//								"BeanUtils.copyProperties(entity.get$upperFieldName$Entity(), $fieldName$Details);}\n");
//				subTemp.setAttribute("fieldName", field.getName());
//				subTemp.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
//				subTemp.setAttribute("refType", CodeUtils.toCamelCase(field.getRefType()));
//				template.setAttribute("embeddedReferences", subTemp.toString());
//			}
//		}
		template.setAttribute("module", cybertables.getModuleName());
		
		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/core/domain").replace("{{module}}", cybertables.getModuleName()), CodeUtils.toCamelCase(table.getName())+"Details.java");
	}

	private Object generateReferences(Table table) {
		String references="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
				if (field.isReference()){
					StringTemplate stringTemplate = new StringTemplate("this.$reference$=entity$getChain$$additionalFields$+_embedded;\n");
					stringTemplate.setAttribute("reference", field.getName());
					stringTemplate.setAttribute("upperReference", CodeUtils.toCamelCase(field.getName()));
					stringTemplate.setAttribute("getChain", CodeUtils.getGetChain(cybertables, table, field));
					String addFields="";
					if (!field.getAdditionalFields().isEmpty()){
						List<String> additionalFields = field.getAdditionalFields();
						for (String addField : additionalFields) {
							addFields+="+\" - \"+entity.get"+CodeUtils.toCamelCase(field.getName())+"().get"+CodeUtils.toCamelCase(addField)+"()";
						}
					}
					stringTemplate.setAttribute("additionalFields", addFields);
					references+=stringTemplate.toString();
					
					stringTemplate = new StringTemplate("this.$reference$Id=entity.get$upperReference$().getId();\n");
					stringTemplate.setAttribute("reference", field.getName());
					stringTemplate.setAttribute("upperReference", CodeUtils.toCamelCase(field.getName()));
					references+=stringTemplate.toString();
				}
				else{
					if (field.getType().equals("String")){
						StringTemplate stringTemplate = new StringTemplate("this.$field$=$field$+_embedded;\n");
						stringTemplate.setAttribute("field", field.getName());
						references+=stringTemplate.toString();
					}
				}
				
				
		}
		
		return references;
	}

	private Object generateDomainImports(Table table) {
		List<Field> fields = table.getFields();
		HashSet<String> referenceImports = new HashSet<String>();
		String  imports="";
		
		for (Field field : fields) {
			if (field.isReference() && !referenceImports.contains(field.getRefType())){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("module", CodeUtils.getTableModule(field.getRefType()));

				imports+=template.toString();
				referenceImports.add(field.getRefType());
			}
		}
		return imports;
	}
}
