package co.com.cybersoft.generator.code.config;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class ConfigGenerator {
	
	private final Cybertables cybersoft;
	
	private final Cyberdocs cyberdocs;
	
	public ConfigGenerator(Cybertables cybersoft, Cyberdocs cyberdocs){
		this.cybersoft=cybersoft;
		this.cyberdocs=cyberdocs;
	}

	public void generate(){
		generateCoreConfig(cybersoft);
		generatePersistenceConfig(cybersoft, cyberdocs);
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
	
	private void generatePersistenceConfig(Cybertables cybersoft, Cyberdocs cyberdocs){
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
		StringTemplate template = templateGroup.getInstanceOf("persistenceConfig");
		
		List<Table> tables = cybersoft.getTables();
		List<Document> documents = cyberdocs.getDocuments();
		String imports="";
		String beans="";
		String repoFields="";
		String repos="";
		
		//Imports
		for (Document document: documents){
			StringTemplate stringTemplate = templateGroup.getInstanceOf("persistenceConfigImport");
			stringTemplate.setAttribute("tableName", document.getName());
			stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
			stringTemplate.setAttribute("nature", "docs");
			imports+=stringTemplate.toString()+"\n\n";
		}
		
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceConfigImport");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			temp.setAttribute("nature","tables");
			imports+=temp.toString()+"\n\n";
		}
		
		//Beans
		for (Document document : documents) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceBeanDeclaration");
			
			temp.setAttribute("tableName",document.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
			
			beans+=temp.toString()+"\n\n";
		}
		
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceBeanDeclaration");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			
			beans+=temp.toString()+"\n\n";
		}
		
		//Repo fields
		for(Document document:documents){
			StringTemplate temp = templateGroup.getInstanceOf("persistenceRepoField");
			temp.setAttribute("tableName",document.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
			repoFields+=temp.toString()+"\n\n";
		}
		
		for (Table table : tables) {
			StringTemplate temp = templateGroup.getInstanceOf("persistenceRepoField");
			
			temp.setAttribute("tableName",table.getName());
			temp.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
			repoFields+=temp.toString()+"\n\n";
		}
		
		//Repos
		for(Document document:documents){
			StringTemplate stringTemplate = new StringTemplate("$entityName$Repository.class");
			stringTemplate.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
			repos+=stringTemplate.toString()+",";
		}
		
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
