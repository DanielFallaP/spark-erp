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
		}
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
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceService.java");
	
	}
	
	private void generatePersistenceImpl(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceImplementation");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("repo", table.isActiveReference()?"CustomRepo":"Repository");
		template.setAttribute("active", table.isActiveReference()?"Active":"");
		
		StringTemplateGroup subTemplateGroup=new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate subTemplate = subTemplateGroup.getInstanceOf("descriptionPersistenceServiceImpl");
		subTemplate.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		subTemplate.setAttribute("tableName", table.getName());
		
		if(CodeUtil.containsDescriptionField(table))
			template.setAttribute("description", subTemplate.toString());
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/services/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PersistenceServiceImpl.java");
		
	}
	
	private void generateRepositories(Table table){
		
		//Repository interface generation
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("repository");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("codeType", CodeUtil.getCodeType(table));
		StringTemplate subTemplate=new StringTemplate("$entityName$ findByDescription(String description);\n");
		subTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		if (CodeUtil.containsDescriptionField(table))
			template.setAttribute("description", subTemplate.toString());
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"Repository.java");
		
		//Custom repository interface generation
		template = templateGroup.getInstanceOf("customRepositoryInterface");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CustomRepo.java");
		
		//Custom repository implementation generation
		template = templateGroup.getInstanceOf("customRepositoryImplementation");
		template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("fieldType", CodeUtil.getCodeType(table));
		template.setAttribute("byContainingDescription", generateSearchByDescription(table));
		template.setAttribute("findAllActive", generateAllActiveSearch(table));
		
		CodeUtil.writeClass(template.toString(), Cybersystems.targetClassPath+"/persistence/repository/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"CustomRepoImpl.java");
		
	}
	
	private String generateAllActiveSearch(Table table) {
			StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
			StringTemplate template = templateGroup.getInstanceOf("findAllActive");
			template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			template.setAttribute("tableName", table.getName());
			return template.toString();
	}

	private String generateSearchByDescription(Table table){
		StringTemplate template;
		if (CodeUtil.containsDescriptionField(table)){
			
			StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersystems.codePath+"persistence");
			template = templateGroup.getInstanceOf("byContainingDescription");
			template.setAttribute("entityName",CodeUtil.toCamelCase(table.getName()));
			template.setAttribute("tableName", table.getName());
		}
		else{
			template=new StringTemplate("@Override "+
					"public List<$entityName$> findByContainingDescription(String descriptionSubstring) {"+
				"return null;}");
			template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		}
			
		
		return template.toString();
	}

}
