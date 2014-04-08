package co.com.cybersoft.generator.code.events;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class EventGenerator {
	
	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateRequestCreateEvent(table);
			generateDetailsEvent(table);
			generateResponseDetailsEvent(table);
			generatePageEvent(table);
			generateResponsePageEvent(table);
			generateRequestModificationEvent(table);
		}
	}

	private void generateRequestCreateEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybersoft.codePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestCreateEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/events/"+table.getName(), "Create"+CodeUtil.toCamelCase(table.getName())+"Event.java");
	}
	
	private void generateDetailsEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybersoft.codePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestDetailsEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/events/"+table.getName(), "Request"+CodeUtil.toCamelCase(table.getName())+"DetailsEvent.java");
	}
	
	private void generateResponseDetailsEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybersoft.codePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("responseDetailsEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/events/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"DetailsEvent.java");
	}
	
	private void generatePageEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybersoft.codePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestPageEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/events/"+table.getName(), "Request"+CodeUtil.toCamelCase(table.getName())+"PageEvent.java");
	}
	
	private void generateResponsePageEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybersoft.codePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("responsePageEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/events/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"PageEvent.java");

	}
	
	private void generateRequestModificationEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybersoft.codePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestModificationEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetClassPath+"/events/"+table.getName(), CodeUtil.toCamelCase(table.getName())+"ModificationEvent.java");

	}
}
