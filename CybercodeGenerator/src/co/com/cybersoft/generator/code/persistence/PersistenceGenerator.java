package co.com.cybersoft.generator.code.persistence;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class PersistenceGenerator {
	
	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateDomainClass(table);
		}
	}
	
	public void generateDomainClass(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersoft.codePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceDomain");
		template.setAttribute("fieldDeclaration", generateDomainClassFieldDeclaration(table));
		template.setAttribute("gettersAndSetters", generateGettersSetters(table));
		template.setAttribute("coreDomainClass",CodeUtil.toCamelCase(table.getName())+"Details");
		template.setAttribute("entityName", CodeUtil.toCamelCase(table.getName()));
		template.setAttribute("variableName", table.getName());
		
		CodeUtil.writeClass(template.toString(), Cybersoft.targetPath+"/persistence/domain", CodeUtil.toCamelCase(table.getName())+".java");
	}
	
	private String generateDomainClassFieldDeclaration(Table table){
		String body="";
		
		List<Field> fields = table.getFields();
		
		for (Field field : fields) {
			if (field.getUnique()!=null && field.getUnique()){
				body+="@Indexed(unique=true)\n";
			}
			StringTemplate template = new StringTemplate("private $type$ $name$;\n");
			template.setAttribute("type", field.getName());
			template.setAttribute("name", field.getName());
			body+=template.toString();
			body+="\n";
			
		}
		return body;
	}
	
	private String generateGettersSetters(Table table){
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybersoft.codePath+"util");
		List<Field> fields = table.getFields();
		String text="";
		for (Field field : fields) {
			StringTemplate template = templateGroup.getInstanceOf("getterSetter");
			template.setAttribute("type", field.getType());
			template.setAttribute("name", field.getName());
			text+=template.toString()+"\n";
		}
		
		return text;
	}

}
