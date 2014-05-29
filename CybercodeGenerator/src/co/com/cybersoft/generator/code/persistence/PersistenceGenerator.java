package co.com.cybersoft.generator.code.persistence;

import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Spark;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class PersistenceGenerator {
	
	private final Spark cybersoft;
	
	public PersistenceGenerator(Spark cybersoft){
		this.cybersoft=cybersoft;
	}
	
	public void generate(){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateDomainClass(table);
			generatePersistenceInterface(table);
			generatePersistenceImpl(table);
			generateRepositories(table);
			generatePersistenceFactory(table);
		}
	}
	
	private void generatePersistenceFactory(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Spark.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceFactory");
		List<Field> fields = table.getFields();
		String byFields="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && !field.isReference() && field.getType().equals(Spark.stringType)){
				StringTemplate stringTemplate = templateGroup.getInstanceOf("queryByField");
				stringTemplate.setAttribute("fieldName", field.getName());
				stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				stringTemplate.setAttribute("tableName", table.getName());
				byFields+=stringTemplate.toString();
			}
		}
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("queriesByFields", byFields);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceFactory.java");
	}

	public void generateDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Spark.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceDomain");
		template.setAttribute("fieldDeclaration", generateDomainClassFieldDeclaration(table));
		template.setAttribute("gettersAndSetters", generateGettersSetters(table));
		template.setAttribute("coreDomainClass",CodeUtil.toCamelCase(table.getName())+"Details");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("variableName", table.getName());
		
		if (table.hasCompoundIndex()){
			StringTemplate subTemplate = templateGroup.getInstanceOf("compoundIndex");
			List<Field> compoundIndex = table.getCompoundIndex(cybersoft);
			String dec="";
			int i=0;
			for (Field field : compoundIndex) {
				dec+="'"+field.getName()+"':1";
				if (i!=compoundIndex.size()-1)
					dec+=",";
				i++;
			}
			subTemplate.setAttribute("tableName", table.getName());
			subTemplate.setAttribute("fields", dec);
			
			template.setAttribute("compoundIndex", subTemplate.toString());
		}
		
		//Write embedded references transformations
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.isEmbeddedReference()){
				StringTemplate subTemp = new StringTemplate("if (details.get$upperFieldName$Details()!=null){\n"
						+ "$fieldName$Entity = new $refType$();\n"+
								"BeanUtils.copyProperties(details.get$upperFieldName$Details(), $fieldName$Entity);}\n");
				subTemp.setAttribute("fieldName", field.getName());
				subTemp.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				subTemp.setAttribute("refType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("embeddedReferences", subTemp.toString());
			}
		}
		
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/domain", CodeUtil.toCamelCase(table.getName())+".java");
	}
	
	private String generateDomainClassFieldDeclaration(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (!field.isReference()&&field.getUnique()!=null && field.getUnique()){
					body+="@Indexed(unique=true)\n";
				}
				StringTemplate template = new StringTemplate("private $type$ $name$;\n");
				template.setAttribute("type", field.isReference()?Spark.stringType:field.getType());
				template.setAttribute("name", field.getName());
				body+=template.toString();
				body+="\n";
				
				if (field.isEmbeddedReference()){
					StringTemplate subTemp = new StringTemplate("private $type$ $name$;\n");
					subTemp.setAttribute("type", CodeUtil.toCamelCase(field.getRefType()));
					subTemp.setAttribute("name", field.getName()+"Entity");
					body+=subTemp.toString();
					body+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersoft, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = new StringTemplate("private $type$ $name$;\n");
					template.setAttribute("type", Spark.stringType);
					template.setAttribute("name", compoundField.getName());
					body+=template.toString();
					body+="\n";
				}
			}
		}
		
		
		return body;
	}
	
	private String generateGettersSetters(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Spark.codePath+"util");
		List<Field> fields = table.getFields();
		
		String text="";
		
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", field.isReference()?Spark.stringType:field.getType());
				template.setAttribute("name", field.getName());
				template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
				
				if (field.isEmbeddedReference()){
					template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", CodeUtil.toCamelCase(field.getRefType()));
					template.setAttribute("name", field.getName()+"Entity");
					template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName())+"Entity");
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersoft, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Spark.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
				}
			}
		}
				
		return text;
	}
	
	private void generatePersistenceInterface(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Spark.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceInterface");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		List<Field> fields = table.getFields();
		String requestAll="";
		String autocompleteRequests="";
		HashSet<String> references = new HashSet<String>();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtil.generateQueryForReferences(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$referencedField$(EmbeddedField... fields) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("referencedField", CodeUtil.toCamelCase(field.getName()));
					requestAll+=stringTemplate.toString();
				}
				
				if (CodeUtil.generateAutoComplete(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception;");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					stringTemplate.setAttribute("fieldName", field.getName());
					
					autocompleteRequests+=stringTemplate.toString();
				}
				
				if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField())){
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
					stringTemplate.setAttribute("fieldName", field.getDisplayField());
					requestAll+=stringTemplate.toString();
					
					references.add(field.getRefType()+field.getDisplayField());

				}
				
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersoft, field.getRefType());
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						requestAll+=stringTemplate.toString();
					}
					
				}
			}
			i++;
		}
		template.setAttribute("requestAll", requestAll);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceService.java");
	
	}
	
	private void generatePersistenceImpl(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Spark.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceImplementation");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("active", table.isActiveReference()?"Active":"");
		
		StringTemplateGroup subTemplateGroup=new StringTemplateGroup("persistence",Spark.codePath+"persistence");
		if(CodeUtil.generateDescriptionAutocomplete(table)){
			StringTemplate subTemplate = subTemplateGroup.getInstanceOf("fieldPersistenceServiceImpl");
			subTemplate.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
			subTemplate.setAttribute("tableName", table.getName());
			template.setAttribute("queryByField", subTemplate.toString());
		}
		
		List<Field> fields = table.getFields();
		String requestImpl="";
		String autocompleteRequests="";
		HashSet<String> references = new HashSet<String>();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtil.generateQueryForReferences(field)){
					StringTemplate stringTemplate = subTemplateGroup.getInstanceOf("requestAll");
					stringTemplate.setAttribute("repo", table.isActiveReference()?"CustomRepo":"Repository");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperReferencedField", CodeUtil.toCamelCase(field.getName()));
					requestImpl+=stringTemplate.toString();
				}
				
				if (CodeUtil.generateAutoComplete(field)){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("autocompleteRequest");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("fieldName", field.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					autocompleteRequests+=stringTemplate.toString();
				}
				
				if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField())){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperReferencedField", CodeUtil.toCamelCase(field.getDisplayField()));
					stringTemplate.setAttribute("referencedField", field.getDisplayField());
					requestImpl+=stringTemplate.toString();
					
					references.add(field.getRefType()+field.getDisplayField());
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersoft, field.getRefType());
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
						stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperReferencedField", CodeUtil.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("referencedField", compoundField.getName());
						stringTemplate.setAttribute("tableName", table.getName());
						requestImpl+=stringTemplate.toString();
					}
					
				}
			}
			i++;
		}
		
		template.setAttribute("requestAll", requestImpl);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceServiceImpl.java");
		
	}
	
	private void generateRepositories(Table table){
		
		//Repository interface generation
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Spark.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("repository");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		//Add methods to search by each field
		String methods="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate subTemplate=new StringTemplate("$entityName$ findBy$fieldName$($fieldType$ value);\n");
				subTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
				subTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				subTemplate.setAttribute("fieldType", field.getType()!=null?CodeUtil.toCamelCase(field.getType()):Spark.stringType);
				methods+=subTemplate.toString()+"\n";
			}
		}
		
		template.setAttribute("findByFields", methods);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"Repository.java");
		
		//Custom repository interface generation
		template = templateGroup.getInstanceOf("customRepositoryInterface");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		
		String findAllActive="";
		String autocompleteQueries="";
		HashSet<String> references = new HashSet<String>();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtil.generateQueryForReferences(field)){
					StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findAllActiveBy$upperReferenceField$(EmbeddedField ...fields) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperReferenceField", CodeUtil.toCamelCase(field.getName()));
					findAllActive+=stringTemplate.toString();
				}
				
				if (CodeUtil.generateAutoComplete(field)){
					StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findByContaining$upperFieldName$(String substring);\n");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
					
					autocompleteQueries+=stringTemplate.toString();
				}
				
				if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField())){
					StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
					stringTemplate.setAttribute("fieldName", field.getDisplayField());
					findAllActive+=stringTemplate.toString();
					
					references.add(field.getRefType()+field.getDisplayField());

				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersoft, field.getRefType());
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						findAllActive+=stringTemplate.toString();
					}
					
				}
			}
			i++;
		}
		
		template.setAttribute("findAllActive", findAllActive);
		template.setAttribute("autocompleteQuery", autocompleteQueries);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CustomRepo.java");
		
		//Custom repository implementation generation
		template = templateGroup.getInstanceOf("customRepositoryImplementation");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		//Set single record for singleton tables
		if (table.getSingletonTable()){
			StringTemplate stringTemplate = templateGroup.getInstanceOf("startupSingletonRecord");
			stringTemplate.setAttribute("tableName", table.getName());
			template.setAttribute("startupSingletonRecord", stringTemplate.toString());
		}

		references = new HashSet<String>();
		findAllActive="";
		autocompleteQueries="";
		i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtil.generateQueryForReferences(field)){
					StringTemplate subTemplate = templateGroup.getInstanceOf("findAllActive");
					subTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					subTemplate.setAttribute("tableName", table.getName());
					subTemplate.setAttribute("upperReferenceField", CodeUtil.toCamelCase(field.getName()));
					subTemplate.setAttribute("referenceField", field.getName());
					findAllActive+=subTemplate.toString();
				}
				
				if (CodeUtil.generateAutoComplete(field)){
					StringTemplate subTemplate = templateGroup.getInstanceOf("byContainingField");
					subTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					subTemplate.setAttribute("tableName", table.getName());
					subTemplate.setAttribute("upperReferenceField", CodeUtil.toCamelCase(field.getName()));
					subTemplate.setAttribute("fieldName", field.getName());
					autocompleteQueries+=subTemplate.toString();
				}
				
				if (field.isReference() &&!references.contains(field.getRefType()+field.getDisplayField())){
					Field keyCompound = fields.get(i+1);
					StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompoundCustomRepo");
					stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
					stringTemplate.setAttribute("fieldName", field.getDisplayField());
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperKeyField", CodeUtil.toCamelCase(keyCompound.getName()));
					findAllActive+=stringTemplate.toString();
					
					references.add(field.getRefType()+field.getDisplayField());
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybersoft, field.getRefType());
				//The next field should complete the compound reference for the table 
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompoundCustomRepo");
						stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtil.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						stringTemplate.setAttribute("tableName", table.getName());
						stringTemplate.setAttribute("upperKeyField", CodeUtil.toCamelCase(keyCompound.getName()));
						findAllActive+=stringTemplate.toString();
					}
					
				}
			}
			i++;
		}
		
		template.setAttribute("findAllActive", findAllActive);
		template.setAttribute("byContainingFields", autocompleteQueries);
		
		CodeUtil.writeClass(template.toString(), Spark.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CustomRepoImpl.java");
		
	}

}
