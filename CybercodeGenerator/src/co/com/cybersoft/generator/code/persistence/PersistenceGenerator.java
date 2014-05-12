package co.com.cybersoft.generator.code.persistence;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersystems;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class PersistenceGenerator {
	
	public void generate(Cybersystems cybersoft){
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
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceFactory");
		List<Field> fields = table.getFields();
		String byFields="";
		for (Field field : fields) {
			if (!field.isReference() && field.getType().equals(Cybersystems.stringType)){
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
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceFactory.java");
	}

	public void generateDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceDomain");
		template.setAttribute("fieldDeclaration", generateDomainClassFieldDeclaration(table));
		template.setAttribute("gettersAndSetters", generateGettersSetters(table));
		template.setAttribute("coreDomainClass",CodeUtil.toCamelCase(table.getName())+"Details");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("variableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/domain", CodeUtil.toCamelCase(table.getName())+".java");
	}
	
	private String generateDomainClassFieldDeclaration(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		
		//For every field
		for (Field field : fields) {
				if (!field.isReference()&&field.getUnique()!=null && field.getUnique()){
					body+="@Indexed(unique=true)\n";
				}
				StringTemplate template = new StringTemplate("private $type$ $name$;\n");
				template.setAttribute("type", field.isReference()?Cybersystems.stringType:field.getType());
				template.setAttribute("name", field.getName());
				body+=template.toString();
				body+="\n";
		}
		
		
		return body;
	}
	
	private String generateGettersSetters(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"util");
		List<Field> fields = table.getFields();
		
		String text="";
		
		//For every field
		for (Field field : fields) {
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", field.isReference()?Cybersystems.stringType:field.getType());
			template.setAttribute("name", field.getName());
			template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			text+=template.toString()+"\n";
		}
				
		return text;
	}
	
	private void generatePersistenceInterface(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceInterface");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		List<Field> fields = table.getFields();
		String requestAll="";
		String autocompleteRequests="";
		for (Field field : fields) {
			if (CodeUtil.generateQueryForReferences(field)){
				StringTemplate stringTemplate = new StringTemplate("$entityName$PageEvent requestAllBy$referencedField$() throws Exception;\n");
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
		}
		template.setAttribute("requestAll", requestAll);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceService.java");
	
	}
	
	private void generatePersistenceImpl(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceImplementation");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("active", table.isActiveReference()?"Active":"");
		
		StringTemplateGroup subTemplateGroup=new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		if(CodeUtil.generateDescriptionAutocomplete(table)){
			StringTemplate subTemplate = subTemplateGroup.getInstanceOf("fieldPersistenceServiceImpl");
			subTemplate.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
			subTemplate.setAttribute("tableName", table.getName());
			template.setAttribute("queryByField", subTemplate.toString());
		}
		
		List<Field> fields = table.getFields();
		String requestImpl="";
		String autocompleteRequests="";
		for (Field field : fields) {
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
		}
		
		template.setAttribute("requestAll", requestImpl);
		template.setAttribute("autocompleteRequest", autocompleteRequests);
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceServiceImpl.java");
		
	}
	
	private void generateRepositories(Table table){
		
		//Repository interface generation
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("repository");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		//Add methods to search by each field
		String methods="";
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			StringTemplate subTemplate=new StringTemplate("$entityName$ findBy$fieldName$($fieldType$ value);\n");
			subTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			subTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			subTemplate.setAttribute("fieldType", field.getType()!=null?CodeUtil.toCamelCase(field.getType()):Cybersystems.stringType);
			methods+=subTemplate.toString()+"\n";
		}
		
		template.setAttribute("findByFields", methods);
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"Repository.java");
		
		//Custom repository interface generation
		template = templateGroup.getInstanceOf("customRepositoryInterface");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		String findAllActive="";
		String autocompleteQueries="";
		for (Field field : fields) {
			if (CodeUtil.generateQueryForReferences(field)){
				StringTemplate stringTemplate = new StringTemplate("List<$entityName$> findAllActiveBy$upperReferenceField$();\n");
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
			
		}
		
		template.setAttribute("findAllActive", findAllActive);
		template.setAttribute("autocompleteQuery", autocompleteQueries);
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CustomRepo.java");
		
		//Custom repository implementation generation
		template = templateGroup.getInstanceOf("customRepositoryImplementation");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		findAllActive="";
		autocompleteQueries="";
		for (Field field : fields) {
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
		}
		
		template.setAttribute("findAllActive", findAllActive);
		template.setAttribute("byContainingFields", autocompleteQueries);
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CustomRepoImpl.java");
		
	}

}
