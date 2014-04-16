package co.com.cybersoft.generator.code.config;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class ConfigGenerator {

	public void generate(Cybersoft cybersoft){
		generateCoreConfig(cybersoft);
		generatePersistenceConfig(cybersoft);
	}
	
	private void generateCoreConfig(Cybersoft cybersoft){
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybersoft.codePath+"config");
		StringTemplate template = templateGroup.getInstanceOf("coreConfig");
		
		List<Table> tables = cybersoft.getTables();
		String imports="";
		String beans="";
		
		//Imports
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("coreConfigImport");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			imports+=temp.toString()+"\n\n";
		}
		
		//Beans
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("coreBeanDeclaration");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			beans+=temp.toString()+"\n\n";
		}
		
		template.setAttribute("imports", imports);
		template.setAttribute("beanDeclarations", beans);

		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/config", "CoreConfig.java");
	}
	
	private void generatePersistenceConfig(Cybersoft cybersoft){
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybersoft.codePath+"config");
		StringTemplate template = templateGroup.getInstanceOf("persistenceConfig");
		
		List<Table> tables = cybersoft.getTables();
		String imports="";
		String beans="";
		String repoFields="";
		String repos="";
		
		//Imports
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceConfigImport");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			imports+=temp.toString()+"\n\n";
		}
		
		//Beans
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceBeanDeclaration");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			beans+=temp.toString()+"\n\n";
		}
		
		//Repo fields
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceRepoField");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			repoFields+=temp.toString()+"\n\n";
		}
		
		//Repos
		for (Table table : tables) {
			StringTemplate stringTemplate = new StringTemplate(",$entityName$Repository.class");
			
			stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			repos+=stringTemplate.toString();
		}
		
		template.setAttribute("imports", imports);
		template.setAttribute("beans", beans);
		template.setAttribute("repoFields", repoFields);
		template.setAttribute("repos", repos);
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/config", "PersistenceConfig.java");
	}
}