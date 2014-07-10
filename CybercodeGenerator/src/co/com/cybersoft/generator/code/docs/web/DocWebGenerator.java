package co.com.cybersoft.generator.code.docs.web;

import java.util.List;

import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;

public class DocWebGenerator {
	
	private final Cyberdocs cyberdocs;
	
	public DocWebGenerator(Cyberdocs cyberdocs){
		this.cyberdocs=cyberdocs;
	}
	
	public void generate(){
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			generateSaveController(document);
			generateSearchController(document);
		}
	}

	private void generateSearchController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		
		
	}

	private void generateSaveController(Document document) {
		// TODO Auto-generated method stub
		
	}

}
