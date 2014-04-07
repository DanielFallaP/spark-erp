package co.com.cybersoft.generator.code.web;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates the web layer of the application
 * @author daniel, Cybersoft
 *
 */
public class WebGenerator {

	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateSearchController(table);
			generateDomain(table);
		}
	}
	
	public void generateSearchController(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchController");
		template.setAttribute("entityName", table.getName());
		template.setAttribute("coreService", CodeUtil.toCamelCase(table.getName())+"Service");
		template.setAttribute("responseEvent", CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("requestEvent", "Request"+CodeUtil.toCamelCase(table.getName())+"Event");
		template.setAttribute("domain", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("url", "/configuration/"+table.getName()+"/search"+CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("searchControllerName",CodeUtil.toCamelCase(table.getName())+"SearchController" );
		template.setAttribute("getListMethod", "getPageContent");
		template.setAttribute("requestMethodName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("viewURL", "/configuration/"+table.getName()+"/search"+CodeUtil.toCamelCase(table.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetPath+"/web/controller/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"SearchController.java");
	}
	
	public void generateCreateController(Table table){
		
	}
	
	public void generateModifyController(Table table){
		
	}
	
	public void generateDomain(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainClasses");
		String className = CodeUtil.toCamelCase(table.getName()+"Info");
		template.setAttribute("domainClassName", className);
		template.setAttribute("bodyDomainClass", generateDomainBody(table));
		template.setAttribute("tableName", table.getName());
		CodeUtil.writeClass(template.toString(),Cybersoft.targetPath+"/web/domain/"+table.getName(), className+".java");
	}
	
	private String generateDomainBody(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		
		//Attributes
		for (Field field : fields) {
			StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
			fieldTemplate.setAttribute("type", field.getType());
			fieldTemplate.setAttribute("name", field.getName());
			body+=fieldTemplate.toString();
			body+="\n";
		}
		
		//Getters and setters
		for (Field field : fields) {
			StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"util");
			StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
			gettersSettersTemplate.setAttribute("type", field.getType());
			gettersSettersTemplate.setAttribute("name", field.getName());
			gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
			body+=gettersSettersTemplate.toString()+"\n\n";
		}
		return body;
	}
}
