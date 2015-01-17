package co.com.cybersoft.generator.code.tables.persistence;

import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class TablePersistenceGenerator {
	
	private final Cybertables cybertables;
	
	public TablePersistenceGenerator(Cybertables cybersoft){
		this.cybertables=cybersoft;
	}
	
	public void generate(){
		List<Table> tables = cybertables.getTables();
		for (Table table : tables) {
			generateDomainClass(table);
			generatePersistenceInterface(table);
			generatePersistenceImpl(table);
			generateRepositories(table);
			generatePersistenceFactory(table);
		}
	}
	
	private void generatePersistenceFactory(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceFactory");
		List<Field> fields = table.getFields();
		String byFields="";
		for (Field field : fields) {
			if (!field.getCompoundReference() && !field.isReference() && field.getType().equals(Cybertables.stringType)){
				StringTemplate stringTemplate = templateGroup.getInstanceOf("queryByField");
				stringTemplate.setAttribute("fieldName", field.getName());
				stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				stringTemplate.setAttribute("tableName", table.getName());
				byFields+=stringTemplate.toString();
			}
		}
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("queriesByFields", byFields);
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/services/"+table.getName(), CodeUtils.toCamelCase(table.getName())+"PersistenceFactory.java");
	}

	public void generateDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceDomain");
		template.setAttribute("fieldDeclaration", generateDomainClassFieldDeclaration(table));
		template.setAttribute("gettersAndSetters", generateGettersSetters(table));
		template.setAttribute("coreDomainClass",CodeUtils.toCamelCase(table.getName())+"Details");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("variableName", table.getName());
		
		if (table.hasCompoundIndex()){
			StringTemplate subTemplate = templateGroup.getInstanceOf("compoundIndex");
			List<Field> compoundIndex = table.getCompoundIndex(cybertables);
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
				subTemp.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				subTemp.setAttribute("refType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("embeddedReferences", subTemp.toString());
			}
		}
		
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/domain", CodeUtils.toCamelCase(table.getName())+".java");
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
				template.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				body+=template.toString();
				body+="\n";
				
				if (field.isEmbeddedReference()){
					StringTemplate subTemp = new StringTemplate("private $type$ $name$;\n");
					subTemp.setAttribute("type", CodeUtils.toCamelCase(field.getRefType()));
					subTemp.setAttribute("name", field.getName()+"Entity");
					body+=subTemp.toString();
					body+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = new StringTemplate("private $type$ $name$;\n");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					body+=template.toString();
					body+="\n";
				}
			}
		}
		
		
		return body;
	}
	
	private String generateGettersSetters(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.utilCodePath);
		List<Field> fields = table.getFields();
		
		String text="";
		
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
				
				if (field.isEmbeddedReference()){
					template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", CodeUtils.toCamelCase(field.getRefType()));
					template.setAttribute("name", field.getName()+"Entity");
					template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName())+"Entity");
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
				}
			}
		}
				
		return text;
	}
	
	private void generatePersistenceInterface(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceInterface");
		template.setAttribute("entityName",CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		List<Field> fields = table.getFields();
		String requestAll="";
		String autocompleteRequests="";
		HashSet<String> references = new HashSet<String>();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtils.generateQueryForReferences(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$referencedField$(EmbeddedField... fields) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("referencedField", CodeUtils.toCamelCase(field.getName()));
					requestAll+=stringTemplate.toString();
				}
				
				if (CodeUtils.generateAutoComplete(field)){
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception;");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
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
						requestAll+=stringTemplate.toString();
						
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
						requestAll+=stringTemplate.toString();
					}
					
					StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception;");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					stringTemplate.setAttribute("fieldName", compoundField.getName());
					
					autocompleteRequests+=stringTemplate.toString();
					
				}
			}
			i++;
		}
		template.setAttribute("requestAll", requestAll);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/services/"+table.getName(), CodeUtils.toCamelCase(table.getName())+"PersistenceService.java");
	
	}
	
	private void generatePersistenceImpl(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceImplementation");
		template.setAttribute("entityName",CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("active", table.isActiveReference()?"Active":"");
		template.setAttribute("autocompleteRepos", generateAutoCompleteRepos(table));
		template.setAttribute("checkReferences", generateReferenceCheck(table));
		template.setAttribute("imports", generateImports(table));
		
		StringTemplateGroup subTemplateGroup=new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		if(CodeUtils.generateDescriptionAutocomplete(table)){
			StringTemplate subTemplate = subTemplateGroup.getInstanceOf("fieldPersistenceServiceImpl");
			subTemplate.setAttribute("entityName",CodeUtils.toCamelCase(table.getName()));
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
				if (CodeUtils.generateQueryForReferences(field)){
					StringTemplate stringTemplate = subTemplateGroup.getInstanceOf("requestAll");
					stringTemplate.setAttribute("repo", table.isActiveReference()?"CustomRepo":"Repository");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("upperReferencedField", CodeUtils.toCamelCase(field.getName()));
					requestImpl+=stringTemplate.toString();
				}
				
				if (CodeUtils.generateAutoComplete(field)){
					StringTemplate stringTemplate = templateGroup.getInstanceOf("autocompleteRequest");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("fieldName", field.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					autocompleteRequests+=stringTemplate.toString();
				}
				
				if (i<fields.size()-1){
					Field keyCompound = fields.get(i+1);
					if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField()) && keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompound");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("tableName", table.getName());
						stringTemplate.setAttribute("upperReferencedField", CodeUtils.toCamelCase(field.getDisplayField()));
						stringTemplate.setAttribute("referencedField", field.getDisplayField());
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
						stringTemplate.setAttribute("upperReferencedField", CodeUtils.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("referencedField", compoundField.getName());
						stringTemplate.setAttribute("tableName", table.getName());
						requestImpl+=stringTemplate.toString();
					}
					
					StringTemplate stringTemplate = templateGroup.getInstanceOf("autocompleteRequest");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("tableName", table.getName());
					stringTemplate.setAttribute("fieldName", compoundField.getName());
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					autocompleteRequests+=stringTemplate.toString();
					
				}
			}
			i++;
		}
		
		template.setAttribute("requestAll", requestImpl);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/services/"+table.getName(), CodeUtils.toCamelCase(table.getName())+"PersistenceServiceImpl.java");
		
	}
	
	private String generateReferenceCheck(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		
		String checks="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("referenceCheck");
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("upperDisplayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				checks+=template.toString()+"\n";
			}
		}
		
		
		return checks;
	}

	private String generateImports(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.utilCodePath);
		
		String imports="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("referenceImport");
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				imports+=template.toString()+"\n";
			}
		}
		
		return imports;
	}

	private String generateAutoCompleteRepos(Table table) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		
		String declarations="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field)){
				StringTemplate template = templateGroup.getInstanceOf("referenceDeclaration");
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				declarations+=template.toString()+"\n";
			}
		}
		
		return declarations;
	}

	private void generateRepositories(Table table){
		
		//Repository interface generation
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("repository");
		template.setAttribute("entityName",CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		//Add methods to search by each field
		String methods="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate subTemplate=new StringTemplate("$entityName$ findBy$fieldName$($fieldType$ value);\n");
				subTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				subTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				subTemplate.setAttribute("fieldType", field.getType()!=null?CodeUtils.toCamelCase(field.getType()):Cybertables.stringType);
				methods+=subTemplate.toString()+"\n";
			}
			else
			{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate subTemplate=new StringTemplate("$entityName$ findBy$fieldName$($fieldType$ value);\n");
					subTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					subTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()));
					subTemplate.setAttribute("fieldType", compoundField.getType()!=null?CodeUtils.toCamelCase(compoundField.getType()):Cybertables.stringType);
					methods+=subTemplate.toString()+"\n";
				}
			}
			
		}
		
		template.setAttribute("findByFields", methods);
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/repository/"+table.getName(), CodeUtils.toCamelCase(table.getName())+"Repository.java");
		
		//Custom repository interface generation
		template = templateGroup.getInstanceOf("customRepositoryInterface");
		template.setAttribute("entityName",CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		
		String findAllActive="";
		String autocompleteQueries="";
		HashSet<String> references = new HashSet<String>();
		int i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtils.generateQueryForReferences(field)){
					StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findAllActiveBy$upperReferenceField$(EmbeddedField ...fields) throws Exception;\n");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperReferenceField", CodeUtils.toCamelCase(field.getName()));
					findAllActive+=stringTemplate.toString();
				}
				
				if (CodeUtils.generateAutoComplete(field)){
					StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findByContaining$upperFieldName$(String substring);\n");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
					
					autocompleteQueries+=stringTemplate.toString();
				}
				
				if (i<fields.size()-1){
					Field keyCompound = fields.get(i+1);
					if (field.isReference()&&!references.contains(field.getRefType()+field.getDisplayField()) && keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getDisplayField()));
						stringTemplate.setAttribute("fieldName", field.getDisplayField());
						findAllActive+=stringTemplate.toString();
						
						references.add(field.getRefType()+field.getDisplayField());
						
					}
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType()) && keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findBy$upperFieldName$Name(String $fieldName$) throws Exception;\n");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						findAllActive+=stringTemplate.toString();
					}
					
					StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findByContaining$upperFieldName$(String substring);\n");
					stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					
					autocompleteQueries+=stringTemplate.toString();
				}
			}
			i++;
		}
		
		template.setAttribute("findAllActive", findAllActive);
		template.setAttribute("autocompleteQuery", autocompleteQueries);
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/repository/"+table.getName(), CodeUtils.toCamelCase(table.getName())+"CustomRepo.java");
		
		//Custom repository implementation generation
		template = templateGroup.getInstanceOf("customRepositoryImplementation");
		template.setAttribute("entityName",CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());

		references = new HashSet<String>();
		findAllActive="";
		autocompleteQueries="";
		i=0;
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				if (CodeUtils.generateQueryForReferences(field)){
					StringTemplate subTemplate = templateGroup.getInstanceOf("findAllActive");
					subTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					subTemplate.setAttribute("tableName", table.getName());
					subTemplate.setAttribute("upperReferenceField", CodeUtils.toCamelCase(field.getName()));
					subTemplate.setAttribute("referenceField", field.getName());
					findAllActive+=subTemplate.toString();
				}
				
				if (CodeUtils.generateAutoComplete(field)){
					StringTemplate subTemplate = templateGroup.getInstanceOf("byContainingField");
					subTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					subTemplate.setAttribute("tableName", table.getName());
					subTemplate.setAttribute("upperReferenceField", CodeUtils.toCamelCase(field.getName()));
					subTemplate.setAttribute("fieldName", field.getName());
					autocompleteQueries+=subTemplate.toString();
				}
				
				if (i<fields.size()-1){
					Field keyCompound = fields.get(i+1);
					if (field.isReference() &&!references.contains(field.getRefType()+field.getDisplayField()) && keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompoundCustomRepo");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getDisplayField()));
						stringTemplate.setAttribute("fieldName", field.getDisplayField());
						stringTemplate.setAttribute("tableName", table.getName());
						stringTemplate.setAttribute("upperKeyField", CodeUtils.toCamelCase(keyCompound.getName()));
						findAllActive+=stringTemplate.toString();
						
						references.add(field.getRefType()+field.getDisplayField());
					}
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				//The next field should complete the compound reference for the table 
				Field keyCompound = fields.get(i+1);
				for (Field compoundField : compoundKey) {
					if (compoundField.getTableName().equals(field.getRefType())&&keyCompound.getKeyCompound()){
						StringTemplate stringTemplate = templateGroup.getInstanceOf("requestCompoundCustomRepo");
						stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
						stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
						stringTemplate.setAttribute("fieldName", compoundField.getName());
						stringTemplate.setAttribute("tableName", table.getName());
						stringTemplate.setAttribute("upperKeyField", CodeUtils.toCamelCase(keyCompound.getName()));
						findAllActive+=stringTemplate.toString();
					}
					
					StringTemplate subTemplate = templateGroup.getInstanceOf("byContainingField");
					subTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
					subTemplate.setAttribute("tableName", table.getName());
					subTemplate.setAttribute("upperReferenceField", CodeUtils.toCamelCase(compoundField.getName()));
					subTemplate.setAttribute("fieldName", compoundField.getName());
					autocompleteQueries+=subTemplate.toString();
				}
			}
			i++;
		}
		template.setAttribute("fieldCriteria", getFieldCriteria(table));
		template.setAttribute("findAllActive", findAllActive);
		template.setAttribute("byContainingFields", autocompleteQueries);
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetTableClassPath+"/persistence/repository/"+table.getName(), CodeUtils.toCamelCase(table.getName())+"CustomRepoImpl.java");
		
	}

	private Object getFieldCriteria(Table table) {
		String fieldCriteria="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate template;
				if (field.getType()==null || field.getType().equals(Cyberconstants.stringType)){
					template=new StringTemplate("if (filter.get$upperFieldName$()!=null && !filter.get$upperFieldName$().equals(\"\"))filterQuery.addCriteria(Criteria.where(\"$fieldName$\").regex(translateWildcards(filter.get$upperFieldName$())));");
				}
				else{
					template=new StringTemplate("if (filter.get$upperFieldName$()!=null && !filter.get$upperFieldName$().equals(\"\"))filterQuery.addCriteria(Criteria.where(\"$fieldName$\").is(filter.get$upperFieldName$()));");
				}
						
				template.setAttribute("fieldName", field.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				fieldCriteria+=template.toString()+"\n";
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate subTemplate=new StringTemplate("if (filter.get$upperFieldName$()!=null && !filter.get$upperFieldName$().equals(\"\"))filterQuery.addCriteria(Criteria.where(\"$fieldName$\").regex(translateWildcards(filter.get$upperFieldName$())));");
					subTemplate.setAttribute("fieldName", compoundField.getName());
					subTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(compoundField.getName()));
					fieldCriteria+=subTemplate.toString()+"\n";
				}
			}
		}
		return fieldCriteria;
	}

}
