package co.com.cybersoft.generator.code.docs.events;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.util.CodeUtils;

public class DocEventGenerator {
	
	private final Cyberdocs cyberdocs;
	
	private final Cybertables cybertables;
	
	public DocEventGenerator(Cyberdocs cyberdocs, Cybertables cybertables){
		this.cyberdocs=cyberdocs;
		this.cybertables=cybertables;
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
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.documentCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("saveEvent");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("module", "purchase");

		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/events/"+document.getName(), "Save"+CodeUtils.toCamelCase(document.getName())+"Event.java");
	}

	private void generateResponseDocumentEvent(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.documentCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("responseDocumentEvent");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("module", "purchase");

		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/events/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"Event.java");		
	}

	private void generateRequestDocumentEvent(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.documentCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestDocumentEvent");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("module", "purchase");

		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/events/"+document.getName(), "Request"+CodeUtils.toCamelCase(document.getName())+"Event.java");
	}

	private void generateResponsePageEvent(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.documentCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("responsePageEvent");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("module", "purchase");

		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/events/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"PageEvent.java");
	}

	private void generateRequestPageEvent(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("events",Cybertables.documentCodePath+"events");
		StringTemplate template = templateGroup.getInstanceOf("requestPageEvent");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("module", "purchase");

		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/events/"+document.getName(), "Request"+CodeUtils.toCamelCase(document.getName())+"PageEvent.java");
	}
}
