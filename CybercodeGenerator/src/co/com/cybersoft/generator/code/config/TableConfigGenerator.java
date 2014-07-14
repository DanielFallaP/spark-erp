package co.com.cybersoft.generator.code.config;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class TableConfigGenerator {

	public void generate(Cybertables cybersoft){
		generateCoreConfig(cybersoft);
		generatePersistenceConfig(cybersoft);
	}
	
	private void generateCoreConfig(Cybertables cybersoft){
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
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

		
		CodeUtil.writeClass(template.toString(), Cybertables.rootClassPath+"/config", "CoreConfig.java");
	}
	
	private void generatePersistenceConfig(Cybertables cybersoft){
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
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
		int i=1;
		for (Table table : tables) {
			StringTemplate stringTemplate = new StringTemplate("$entityName$Repository.class");
			stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			repos+=stringTemplate.toString();
			if (i!=tables.size())
				repos+=",";
			i++;
		}
		
		template.setAttribute("imports", imports);
		template.setAttribute("beans", beans);
		template.setAttribute("repoFields", repoFields);
		template.setAttribute("repos", repos);
		
		CodeUtil.writeClass(template.toString(), Cybertables.rootClassPath+"/config", "PersistenceConfig.java");
	}
}