package co.com.cybersoft.generator.code.docs.persistence;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class DocPersistenceGenerator {

	private final Cyberdocs cyberdocs;
	
	private final Cybertables cybertables;

	public DocPersistenceGenerator(Cyberdocs cyberdocs, Cybertables cybertables){
		this.cyberdocs=cyberdocs;
		this.cybertables=cybertables;
	}
	
	public void generate(){
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			generateDomainHeaderClass(document);
			generateDomainBodyClass(document);
			generatePersistenceInterface(document);
			generatePersistenceImpl(document);
			generateRepositories(document);
		}
		
		generateSequenceService();
	}

	
	private void generateSequenceService() {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("startup");
		template.setAttribute("checkAndStart", generateSequences());
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/startup", "SequenceServiceImpl.java");
	}

	private String generateSequences() {
		String sequences="";
		
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			StringTemplate template = new StringTemplate("checkAndStart(\"$docName$_id\", 0);\n");
			template.setAttribute("docName", document.getName());
			sequences += template.toString();
		}
		
		return sequences;
	}
	
	private void generateDomainBodyClass(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("domainBody");
		template.setAttribute("fields", generateDomainClassBodyFieldDeclaration(document));
		template.setAttribute("gettersSetters", generateGettersSetters(document.getBody()));
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		if (document.hasCompoundIndex()){
			StringTemplate subTemplate = templateGroup.getInstanceOf("compoundIndex");
			List<Field> compoundIndex = document.getCompoundIndex(cybertables);
			String dec="";
			int i=0;
			for (Field field : compoundIndex) {
				dec+="'"+field.getName()+"':1";
				if (i!=compoundIndex.size()-1)
					dec+=",";
				i++;
			}
			subTemplate.setAttribute("docName", document.getName());
			subTemplate.setAttribute("fields", dec);
			
			template.setAttribute("compoundIndex", subTemplate.toString());
		}
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/domain", CodeUtil.toCamelCase(document.getName())+"Body.java");
	}

	private void generateRepositories(Document document) {
		//Repository interface generation
				StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
				StringTemplate template = templateGroup.getInstanceOf("repository");
				template.setAttribute("upperDocName",CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("docName", document.getName());
				
				CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/repository/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"Repository.java");
				
				//Custom repository interface generation
				template = templateGroup.getInstanceOf("customRepoInterface");
				template.setAttribute("upperDocName",CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("docName", document.getName());
				
				
				CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/repository/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"CustomRepo.java");
				
				//Custom repository implementation generation
				template = templateGroup.getInstanceOf("customRepoImpl");
				template.setAttribute("upperDocName",CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("docName", document.getName());

				CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/repository/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"CustomRepoImpl.java");
	}

	private void generatePersistenceImpl(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceImpl");
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("checkHeaderReferences", generateHeaderCheck(document));
		template.setAttribute("checkAdditionReferences", generateBodyAdditionCheck(document));
		template.setAttribute("checkModificationReferences", generateBodyModificationCheck(document));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"PersistenceServiceImpl.java");
	}

	private Object generateBodyModificationCheck(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object generateBodyAdditionCheck(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object generateHeaderCheck(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	private void generatePersistenceInterface(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceInterface");
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"PersistenceService.java");
	}

	private void generateDomainHeaderClass(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("domainHeader");
		template.setAttribute("fields", generateDomainClassHeaderFieldDeclaration(document));
		template.setAttribute("gettersSetters", generateGettersSetters(document.getHeader()));
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		
		if (document.hasCompoundIndex()){
			StringTemplate subTemplate = templateGroup.getInstanceOf("compoundIndex");
			List<Field> compoundIndex = document.getCompoundIndex(cybertables);
			String dec="";
			int i=0;
			for (Field field : compoundIndex) {
				dec+="'"+field.getName()+"':1";
				if (i!=compoundIndex.size()-1)
					dec+=",";
				i++;
			}
			subTemplate.setAttribute("docName", document.getName());
			subTemplate.setAttribute("fields", dec);
			
			template.setAttribute("compoundIndex", subTemplate.toString());
		}
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/domain", CodeUtil.toCamelCase(document.getName())+".java");
	}

	private String generateGettersSetters(List<Field> fields) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.utilCodePath);
		
		String text="";
		
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
				
				if (field.isEmbeddedReference()){
					template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", CodeUtil.toCamelCase(field.getRefType()));
					template.setAttribute("name", field.getName()+"Entity");
					template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName())+"Entity");
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					text+=template.toString()+"\n";
				}
			}
		}
				
		return text;
	}

	private String generateDomainClassHeaderFieldDeclaration(Document document) {
		String body="";
		
		List<Field> headerFields = document.getHeader();
		
		for (Field field : headerFields) {
			if (!field.getCompoundReference()){
				if (!field.isReference()&&field.getUnique()!=null && field.getUnique()){
					body+="@Indexed(unique=true)\n";
				}
				StringTemplate template = new StringTemplate("private $type$ $name$;\n");
				template.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				body+=template.toString();
				body+="\n";
				
				if (field.isEmbeddedReference()){
					StringTemplate subTemp = new StringTemplate("private $type$ $name$;\n");
					subTemp.setAttribute("type", CodeUtil.toCamelCase(field.getRefType()));
					subTemp.setAttribute("name", field.getName()+"Entity");
					body+=subTemp.toString();
					body+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = new StringTemplate("private $type$ $name$;\n");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					body+=template.toString();
					body+="\n";
				}
			}
		}
		
		
		return body;
	}
	
	private String generateDomainClassBodyFieldDeclaration(Document document) {
		String body="";
		
		List<Field> bodyFields = document.getBody();
		
		for (Field field : bodyFields) {
			if (!field.getCompoundReference()){
				
				StringTemplate template = new StringTemplate("private $type$ $name$;\n");
				template.setAttribute("type", field.isReference()?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				body+=template.toString();
				body+="\n";
				
				if (field.isEmbeddedReference()){
					StringTemplate subTemp = new StringTemplate("private $type$ $name$;\n");
					subTemp.setAttribute("type", CodeUtil.toCamelCase(field.getRefType()));
					subTemp.setAttribute("name", field.getName()+"Entity");
					body+=subTemp.toString();
					body+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = new StringTemplate("private $type$ $name$;\n");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					body+=template.toString();
					body+="\n";
				}
			}
		}
		
		
		return body;
	}
}
