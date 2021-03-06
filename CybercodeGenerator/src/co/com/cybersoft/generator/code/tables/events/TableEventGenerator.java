package co.com.cybersoft.generator.code.tables.events;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class TableEventGenerator {
	
	private Cybertables cybersoft;
	
	public void generate(Cybertables cybersoft){
		this.cybersoft=cybersoft;
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
	
	private String generateResponsePageImports(Table table){
		String imports="";
			StringTemplate template = new StringTemplate("import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;\n"+
														 "import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;\n");
			template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
			template.setAttribute("module", cybersoft.getModuleName());

			imports+=template.toString();
			imports+="import java.util.List;";
		return imports;
	}

	private void generateRequestCreateEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestCreateEvent");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("module", cybersoft.getModuleName());

		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/events/"+table.getName()).replace("{{module}}", cybersoft.getModuleName()), "Create"+CodeUtils.toCamelCase(table.getName())+"Event.java");
	}
	
	private void generateDetailsEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestDetailsEvent");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("module", cybersoft.getModuleName());
		
		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/events/"+table.getName()).replace("{{module}}", cybersoft.getModuleName()), "Request"+CodeUtils.toCamelCase(table.getName())+"DetailsEvent.java");
	}
	
	private void generateResponseDetailsEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("responseDetailsEvent");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("module", cybersoft.getModuleName());

		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/events/"+table.getName()).replace("{{module}}", cybersoft.getModuleName()), CodeUtils.toCamelCase(table.getName())+"DetailsEvent.java");
	}
	
	private void generatePageEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestPageEvent");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("module", cybersoft.getModuleName());

		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/events/"+table.getName()).replace("{{module}}", cybersoft.getModuleName()), "Request"+CodeUtils.toCamelCase(table.getName())+"PageEvent.java");
	}
	
	private void generateResponsePageEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("responsePageEvent");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("referencesImports", generateResponsePageImports(table));
		template.setAttribute("referencesDeclarations", generateResponsePageReferencesDecl(table));
		template.setAttribute("referencesConstructors", generateResponsePageReferencesConstructors(table));
		template.setAttribute("referencesGettersSetters", generateResponsePageGetter(table));
		template.setAttribute("module", cybersoft.getModuleName());

		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/events/"+table.getName()).replace("{{module}}", cybersoft.getModuleName()), CodeUtils.toCamelCase(table.getName())+"PageEvent.java");

	}
	
	private String generateResponsePageReferencesDecl(Table table){
		String declarations="";
		
		StringTemplate template = new StringTemplate("private List<$entityName$Details> $tableName$List;\n");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		declarations+=template.toString()+"\n\n";
		
		return declarations;
	}
	
	private String generateResponsePageGetter(Table table){
		String getter="";
		
		StringTemplate template = new StringTemplate("public List<$entityName$Details> get$entityName$List() {\n"
				+ "return $tableName$List;\n"
				+ "}\n");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		getter+=template.toString()+"\n\n";
		
		return getter;
	}
	
	private String generateResponsePageReferencesConstructors(Table table){
		
		String constructors="";
		
			StringTemplate template = new StringTemplate("public $entityName$PageEvent(List<$entityName$Details>  $tableName$List){\n"
					+ "		this.$tableName$List= $tableName$List;\n"
					+ "}\n");
			template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
			template.setAttribute("tableName", table.getName());
			constructors+=template.toString()+"\n\n";
		
		return constructors;
	}
		
	private void generateRequestModificationEvent(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestModificationEvent");
		template.setAttribute("entityName", CodeUtils.toCamelCase(table.getName()));
		template.setAttribute("tableName", table.getName());
		template.setAttribute("module", cybersoft.getModuleName());

		CodeUtils.writeClass(template.toString(), (Cybertables.targetTableClassPath+"/events/"+table.getName()).replace("{{module}}", cybersoft.getModuleName()), CodeUtils.toCamelCase(table.getName())+"ModificationEvent.java");

	}
}
