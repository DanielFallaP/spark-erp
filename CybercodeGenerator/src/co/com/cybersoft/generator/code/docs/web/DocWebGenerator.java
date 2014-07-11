package co.com.cybersoft.generator.code.docs.web;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.util.CodeUtil;

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
	}

	private void generateSaveController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("saveController");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		template.setAttribute("imports", generateSaveControllerImports(document));
		
		CodeUtil.writeClass(template.toString(), Cybertables.documentCodePath+"/web/controller/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"Controller.java");
	}

	private String generateSaveControllerImports(Document document) {
		// TODO Auto-generated method stub
		String imports="";
		
		return imports;
	}

}
