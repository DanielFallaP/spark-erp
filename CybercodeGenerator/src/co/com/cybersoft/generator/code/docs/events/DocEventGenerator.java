package co.com.cybersoft.generator.code.docs.events;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class DocEventGenerator {
	
	private final Cyberdocs cyberdocs;
	
	public DocEventGenerator(Cyberdocs cyberdocs){
		this.cyberdocs=cyberdocs;
	}

	public void generate(){
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			generateRequestPageEvent(document);
			generateResponsePageEvent(document);
			generateRequestDocumentEvent(document);
			generateResponseDocumentEvent(document);
			generateSaveDocumentEvent(document);
		}
	}

	private void generateSaveDocumentEvent(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.tableCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestCreateEvent");
		template.setAttribute("entityName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("tableName", document.getName());
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetCodeClassPath+"/events/"+document.getName(), "Create"+CodeUtil.toCamelCase(document.getName())+"Event.java");
	}

	private void generateResponseDocumentEvent(Document document) {
		// TODO Auto-generated method stub
		
	}

	private void generateRequestDocumentEvent(Document document) {
		// TODO Auto-generated method stub
		
	}

	private void generateResponsePageEvent(Document document) {
		// TODO Auto-generated method stub
		
	}

	private void generateRequestPageEvent(Document document) {
		// TODO Auto-generated method stub
		
	}
}
