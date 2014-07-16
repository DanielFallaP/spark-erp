package co.com.cybersoft.generator.code.docs.views;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class DocViewGenerator {

	private final Cyberdocs cyberdocs;

	public DocViewGenerator(Cyberdocs cyberdocs){
		this.cyberdocs=cyberdocs;
	}
	
	public void generate(){
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			generateSearchView(document);
			generateSaveView(document);
		}
	}

	private void generateSaveView(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("saveView");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("rows", generateFieldRows(document));
		template.setAttribute("datePickerConfig", generateDateFieldPickers(document));
		template.setAttribute("autocompleteReferenceFunctions", generateAutocompleteReferenceFunctions(document));
		template.setAttribute("modificationCompoundSelectionFunctions", generateCompoundSelectionFunctions(document));

		List<Field> fields = document.getHeader();
		if (!fields.isEmpty()){
			template.setAttribute("firstField", fields.get(0).getName());
		}
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "save"+CodeUtil.toCamelCase(document.getName())+".html");
	}

	private String generateCompoundSelectionFunctions(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateAutocompleteReferenceFunctions(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateDateFieldPickers(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private String generateFieldRows(Document document) {
		// TODO Auto-generated method stub
		return "";
	}

	private void generateSearchView(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("views",Cybertables.documentCodePath+"views");
		StringTemplate template = templateGroup.getInstanceOf("searchView");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetViewPath+"/normal/docs/"+document.getName(), "search"+CodeUtil.toCamelCase(document.getName())+".html");
	}
}
