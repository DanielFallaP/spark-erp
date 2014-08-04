package co.com.cybersoft.generator.code.docs.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberconstants;
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
		template.setAttribute("referenceFields", generateReferenceDomainFieldsAndGS(document));
		
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

	private Object generateReferenceDomainFieldsAndGS(Document document) {
		String fields="";
		Field referenceField = document.getDocReferenceField();
		if (referenceField!=null){
				List<String> bodyFields = referenceField.getBodyFields();
				for (String field : bodyFields) {
					StringTemplate fieldTemplate = new StringTemplate("private String $name$;\n\n");
					fieldTemplate.setAttribute("name", field);
					fields+=fieldTemplate.toString();
					fields+="\n";
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", Cyberconstants.stringType);
					gettersSettersTemplate.setAttribute("name", field);
					gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field));
					fields+=gettersSettersTemplate.toString()+"\n\n";
				}
		}
		return fields;
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
				template.setAttribute("rowsPerSearch", Cyberconstants.rowsPerSearch);
				
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
		template.setAttribute("autocompleteRepos", generateAutoCompleteRepos(document));
		template.setAttribute("docReference", generateDocReference(document));
		template.setAttribute("imports", generateImports(document));
		template.setAttribute("docReferenceFields", generateDocReferenceFields(document));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"PersistenceServiceImpl.java");
	}

	private Object generateDocReference(Document document) {
		String reference="";
		Field field = document.getDocReferenceField();
		if (field!=null){
			StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
			StringTemplate template = templateGroup.getInstanceOf("referenceDeclaration");
			template.setAttribute("upperRefType", CodeUtil.toCamelCase(field.getDocRefType()));
			template.setAttribute("refType", field.getDocRefType());
			reference+=template.toString()+"\n";
		}
		return reference;
	}

	private Object generateDocReferenceFields(Document document) {
		String getFields="";
		Field referenceField = document.getDocReferenceField();
		if(referenceField!=null){
			List<String> bodyFields = referenceField.getBodyFields();
			StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
			StringTemplate stringTemplate = templateGroup.getInstanceOf("bodyReference");
			stringTemplate.setAttribute("upperDocRef", CodeUtil.toCamelCase(referenceField.getDocRefType()));
			stringTemplate.setAttribute("docRef", referenceField.getDocRefType());
			stringTemplate.setAttribute("docName", document.getName());
			stringTemplate.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
			
			String fields="";
			for (String field : bodyFields) {
				
				StringTemplate template = new StringTemplate("$docName$Body.set$upperFieldName$($docRef$Body.get$upperFieldName$());\n");
				template.setAttribute("docRef", referenceField.getDocRefType());
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field));
				fields+=template.toString();
			}
			
			stringTemplate.setAttribute("fields", fields);
			getFields=stringTemplate.toString();
		}
		return getFields;
	}

	private Object generateImports(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.utilCodePath);
		
		HashSet<String> references = new HashSet<String>();
		String imports="";
		Field referenceField = document.getDocReferenceField();
		if (referenceField!=null){
			StringTemplate template=templateGroup.getInstanceOf("importDocReference");
			template.setAttribute("upperRefType", CodeUtil.toCamelCase(referenceField.getDocRefType()));
			template.setAttribute("refType", referenceField.getDocRefType());
			imports+=template.toString()+"\n";
		}

		List<Field> fields = document.getAllFields();
		for (Field field : fields) {
			if (CodeUtil.generateAutoCompleteReference(field) && !references.contains(field.getRefType())){
				StringTemplate template = templateGroup.getInstanceOf("referenceImport");
				template.setAttribute("upperRefType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				imports+=template.toString()+"\n";
				references.add(field.getRefType());
			}
		}
		
		return imports;
	}

	private Object generateAutoCompleteRepos(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
		
		String declarations="";
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.addAll(document.getBody());
		fields.addAll(document.getHeader());

		HashSet<String> references = new HashSet<String>();
		for (Field field : fields) {
			if (CodeUtil.generateAutoCompleteReference(field) && !references.contains(field.getRefType())){
				StringTemplate template = templateGroup.getInstanceOf("referenceDeclaration");
				template.setAttribute("upperRefType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				declarations+=template.toString()+"\n";
				references.add(field.getRefType());
			}
		}
		
		return declarations;
	}

	private Object generateBodyModificationCheck(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
	
		String checks="";
		List<Field> body = document.getBody();
		for (Field field : body) {
			if (field.isReference() && CodeUtil.generateAutoCompleteReference(field) && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("bodyModificationReferenceField");
				template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("displayField", CodeUtil.toCamelCase(field.getDisplayField()));
				template.setAttribute("fieldName", field.getName());
				
				checks+=template.toString();
			}
		}
		
		return checks;
	}

	private Object generateBodyAdditionCheck(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");

		String checks="";
		List<Field> body = document.getBody();
		for (Field field : body) {
			if (field.isReference() && CodeUtil.generateAutoCompleteReference(field) && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("bodyReferenceCheck");
				template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("displayField", CodeUtil.toCamelCase(field.getDisplayField()));
				template.setAttribute("fieldName", field.getName());
				
				checks+=template.toString();
			}
		}
		
		return checks;
	}

	private Object generateHeaderCheck(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");

		String checks="";
		List<Field> body = document.getHeader();
		for (Field field : body) {
			if (field.isReference() && CodeUtil.generateAutoCompleteReference(field) && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("headerReferenceCheck");
				template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtil.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("displayField", CodeUtil.toCamelCase(field.getDisplayField()));
				template.setAttribute("fieldName", field.getName());
				
				checks+=template.toString();
			}
		}
		
		return checks;
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
				template.setAttribute("type", field.isReference()||field.getDocRefType()!=null?Cybertables.stringType:field.getType());
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
				template.setAttribute("type", field.isReference()||field.getDocRefType()!=null?Cybertables.stringType:field.getType());
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
