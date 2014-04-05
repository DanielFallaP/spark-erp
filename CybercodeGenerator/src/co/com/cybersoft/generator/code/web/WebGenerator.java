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
		
	}
	
	public void generateCreateController(Table table){
		
	}
	
	public void generateModifyController(Table table){
		
	}
	
	public void generateDomain(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybersoft.codePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainHeader");
		template.setAttribute("domainClassName", CodeUtil.toCamelCase(table.getName()+"Info"));
		template.setAttribute("bodyDomainClass", generateDomainBody(table));
		System.out.println(template.toString());
	}
	
	public String generateDomainBody(Table table){
		String body="";
		StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			fieldTemplate.setAttribute("type", field.getType());
			fieldTemplate.setAttribute("name", field.getName());
			body+=fieldTemplate.toString();
		}
		return body;
	}
}
