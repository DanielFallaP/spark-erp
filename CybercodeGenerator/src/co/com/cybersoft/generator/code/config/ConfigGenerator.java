package co.com.cybersoft.generator.code.config;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybermodules;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Module;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class ConfigGenerator {
	
	private final Cybermodules cybermodules;
	
	private final Cyberdocs cyberdocs;

	private ObjectMapper mapper=new ObjectMapper();
	
	private String repoImports;
	
	private String repoFields;
	
	private String repoBeans;

	public ConfigGenerator(Cybermodules cybersoft, Cyberdocs cyberdocs){
		this.cybermodules=cybersoft;
		this.cyberdocs=cyberdocs;
	}

	public void generate(){
		try {
			generatePersistenceConfig(cybermodules, cyberdocs);
			generateCoreConfig(cybermodules);
			generateWebConfig(cybermodules);
			generateSecurityConfig(cybermodules);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateSecurityConfig(Cybermodules cybermodules2) throws Exception{
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
		StringTemplate template = templateGroup.getInstanceOf("securityConfig");
		
		
		List<Module> modules = cybermodules.getModules();
		
		String modulesString="";
		for (Module module : modules) {
			Cybertables cybertables = mapper.readValue(new InputStreamReader(new FileInputStream(module.getFileName()+".json"), "UTF8"), Cybertables.class);
			cybertables.setModuleName(module.getName());
			
			StringTemplate modTemplate = new StringTemplate(".antMatchers(\"/$module$/**\").access(\"hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')\").antMatchers(\"/$module$Settings/**\").access(\"hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')\")");	
					modTemplate.setAttribute("module", module.getName());
			modulesString+=modTemplate.toString();
		}
		
		template.setAttribute("modules", modulesString);
		CodeUtils.writeClass(template.toString(), Cybertables.rootClassPath+"/config", "SecurityConfig.java");
	}

	private void generateWebConfig(Cybermodules cybermodules) throws Exception{
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
		StringTemplate template = templateGroup.getInstanceOf("webConfig");
		
		
		List<Module> modules = cybermodules.getModules();
		
		String modulesString="";
		String moduleControllers="";
		
		for (Module module : modules) {
			Cybertables cybertables = mapper.readValue(new InputStreamReader(new FileInputStream(module.getFileName()+".json"), "UTF8"), Cybertables.class);
			cybertables.setModuleName(module.getName());
			
			StringTemplate modTemplate = new StringTemplate("registry.addViewController(\"/$module$\").setViewName(\"$module$\");registry.addViewController(\"/$module$Settings\").setViewName(\"$module$Settings\");");
			modTemplate.setAttribute("module", module.getName());
			modulesString+=modTemplate.toString();
			
			StringTemplate moduleControllersTemp = new StringTemplate("\"co.com.cybersoft.$module$.tables.web.controller\",\"co.com.cybersoft.$module$.tables.web.domain\",");
			moduleControllersTemp.setAttribute("module", module.getName());
			moduleControllers+=moduleControllersTemp.toString();
		}
		
		template.setAttribute("modules", modulesString);
		template.setAttribute("moduleControllers", moduleControllers);
		
		CodeUtils.writeClass(template.toString(), Cybertables.rootClassPath+"/config", "WebConfig.java");
	}

	private void generateCoreConfig(Cybermodules cybersoft) throws Exception{
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
		StringTemplate template = templateGroup.getInstanceOf("coreConfig");
		
		List<Module> modules = cybersoft.getModules();
		
		String imports="";
		String beans="";

		for (Module module : modules) {
			Cybertables cybertables = mapper.readValue(new InputStreamReader(new FileInputStream(module.getFileName()+".json"), "UTF8"), Cybertables.class);
			cybertables.setModuleName(module.getName());
			
			List<Table> tables = cybertables.getTables();
			//Imports
			for (Table table : tables) {
				StringTemplate temp = templateGroup.getInstanceOf("coreConfigImport");
				
				temp.setAttribute("tableName",table.getName());
				temp.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				temp.setAttribute("module", cybertables.getModuleName());
				imports+=temp.toString()+"\n\n";
			}
			
			//Beans
			for (Table table : tables) {
				StringTemplate temp = templateGroup.getInstanceOf("coreBeanDeclaration");
				
				temp.setAttribute("tableName",table.getName());
				temp.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				beans+=temp.toString()+"\n\n";
			}
		}
		
		imports+=this.repoImports;
		template.setAttribute("imports", imports);
		template.setAttribute("beanDeclarations", beans);
		template.setAttribute("repoFields", this.repoFields);
		template.setAttribute("repoBeans", this.repoBeans);
		
		
		CodeUtils.writeClass(template.toString(), Cybertables.rootClassPath+"/config", "CoreConfig.java");
	}
	
	private void generatePersistenceConfig(Cybermodules cybersoft, Cyberdocs cyberdocs) throws Exception{
		StringTemplateGroup templateGroup = new StringTemplateGroup("config", Cybertables.configCodePath);
		StringTemplate template = templateGroup.getInstanceOf("persistenceConfig");

		String imports="";
		String beans="";
		String repoFields="";
		String repos="";
		String basePackages="";
		
		List<Module> modules = cybersoft.getModules();

		int k=1;
		for (Module module : modules) {
			Cybertables cybertables = mapper.readValue(new InputStreamReader(new FileInputStream(module.getFileName()+".json"), "UTF8"), Cybertables.class);
			cybertables.setModuleName(module.getName());
			
			List<Table> tables = cybertables.getTables();
			//Imports
			StringTemplate persistenceTemplate = new StringTemplate("\"co.com.cybersoft.$module$.tables.persistence.repository\",");
			persistenceTemplate.setAttribute("module", module.getName());
			
			basePackages+=persistenceTemplate.toString();
			if (cybertables.getModuleName().equals("purchase"))
			
			for (Table table : tables) {
				StringTemplate temp = templateGroup.getInstanceOf("persistenceConfigImport");
				
				temp.setAttribute("tableName",table.getName());
				temp.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				temp.setAttribute("nature","tables");
				temp.setAttribute("module", cybertables.getModuleName());
				imports+=temp.toString()+"\n\n";
			}
			
			//Beans
			if (cybertables.getModuleName().equals("purchase"))
			
			for (Table table : tables) {
				StringTemplate temp = templateGroup.getInstanceOf("persistenceBeanDeclaration");
				
				temp.setAttribute("tableName",table.getName());
				temp.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				
				beans+=temp.toString()+"\n\n";
			}
			
			for (Table table : tables) {
				StringTemplate temp = templateGroup.getInstanceOf("persistenceRepoField");
				
				temp.setAttribute("tableName",table.getName());
				temp.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				repoFields+=temp.toString()+"\n\n";
			}
			
			
			int i=1;
			for (Table table : tables) {
				StringTemplate stringTemplate = new StringTemplate("$entityName$Repository.class");
				stringTemplate.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
				repos+=stringTemplate.toString();
				if (i!=tables.size()|| k!=modules.size())
					repos+=",";
				i++;
			}
			k++;
		}
		template.setAttribute("imports", imports);
		this.repoImports=imports;
		this.repoFields=repoFields;
		this.repoBeans=beans;
		template.setAttribute("repos", repos);
		template.setAttribute("module", "purchase");
		template.setAttribute("packages", basePackages.substring(0, basePackages.length()-1));

		
		CodeUtils.writeClass(template.toString(), Cybertables.rootClassPath+"/config", "PersistenceConfig.java");
	}
}
