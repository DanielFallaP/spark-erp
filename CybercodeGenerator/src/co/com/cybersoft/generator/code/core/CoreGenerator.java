package co.com.cybersoft.generator.code.core;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class CoreGenerator {

	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateCoreServiceImplementation(table);
			generateCoreServiceInterface(table);
			generateCoreDomainClass(table);
		}
	}
	
	private void generateCoreServiceInterface(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybersoft.codePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreServiceInterface");
		String className=CodeUtil.toCamelCase(table.getName())+"Service";
		template.setAttribute("entityName", table.getName());
		template.setAttribute("createdEvent", CodeUtil.toCamelCase(table.getName())+"CreatedEvent");
		template.setAttribute("createEvent", "Create"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleResponseEvent", CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allResponseEvent", CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("modifyEvent", "Modify"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("className", className);
		template.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetPath+"/core/"+table.getName()+"/services", className+".java");
	}
	
	private void generateCoreServiceImplementation(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybersoft.codePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreServiceImpl");
		String className=CodeUtil.toCamelCase(table.getName())+"Service";
		template.setAttribute("entityName", table.getName());
		template.setAttribute("createdEvent", CodeUtil.toCamelCase(table.getName())+"CreatedEvent");
		template.setAttribute("createEvent", "Create"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleResponseEvent", CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allResponseEvent", CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("modifyEvent", "Modify"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("singleRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"DetailsEvent");
		template.setAttribute("allRequestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("entityUppercaseName", CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetPath+"/core/"+table.getName()+"/services", className+"Impl.java");
	}
	
	private void generateCoreDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("core",Cybersoft.codePath+"core");
		StringTemplate template = templateGroup.getInstanceOf("coreDomain");
		template.setAttribute("fieldDeclaration", CodeUtil.getFieldDeclarations(table));
		template.setAttribute("gettersAndSetters", CodeUtil.getGettersAndSetters(table));
		template.setAttribute("coreDomainClass", CodeUtil.toCamelCase(table.getName())+"Details");
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetPath+"/core/domain", CodeUtil.toCamelCase(table.getName())+"Details.java");
	}
}
