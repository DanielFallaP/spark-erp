package co.com.cybersoft.generator.code.docs.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.util.CodeUtils;

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
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/startup", "SequenceServiceImpl.java");
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
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
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
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/domain", CodeUtils.toCamelCase(document.getName())+"Body.java");
	}


	private Object generateReferenceDomainFieldsAndGS(Document document) {
		String fields="";
		Field referenceField = document.getBodyDocReferenceField();
		if (referenceField!=null){
				List<Field> bodyFields = CodeUtils.getDocReferenceBodyFields(document, cyberdocs);
				for (Field field : bodyFields) {
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					fieldTemplate.setAttribute("type", field.getType());
					fields+=fieldTemplate.toString();
					fields+="\n";
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					fields+=gettersSettersTemplate.toString()+"\n\n";
				}
		}
		return fields;
	}
	
	private Object generateHeaderReferenceDomainFieldsAndGS(Document document) {
		String fields="";
		Field referenceField = document.getHeaderDocReferenceField();
		if (referenceField!=null){
				List<Field> headerFields  = CodeUtils.getDocReferenceHeaderFields(document, cyberdocs);
				for (Field field : headerFields) {
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("name", field.getName());
					fieldTemplate.setAttribute("type", field.getType());
					fields+=fieldTemplate.toString();
					fields+="\n";
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
					fields+=gettersSettersTemplate.toString()+"\n\n";
				}
		}
		return fields;
	}

	private void generateRepositories(Document document) {
		//Repository interface generation
				StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
				StringTemplate template = templateGroup.getInstanceOf("repository");
				template.setAttribute("upperDocName",CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("docName", document.getName());
				
				CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/repository/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"Repository.java");
				
				//Custom repository interface generation
				template = templateGroup.getInstanceOf("customRepoInterface");
				template.setAttribute("upperDocName",CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("docName", document.getName());
				
				
				CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/repository/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"CustomRepo.java");
				
				//Custom repository implementation generation
				template = templateGroup.getInstanceOf("customRepoImpl");
				template.setAttribute("upperDocName",CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("docName", document.getName());
				template.setAttribute("rowsPerSearch", Cyberconstants.rowsPerSearch);
				
				CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/repository/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"CustomRepoImpl.java");
	}

	private void generatePersistenceImpl(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceImpl");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("checkHeaderReferences", generateHeaderCheck(document));
		template.setAttribute("checkAdditionReferences", generateBodyAdditionCheck(document));
		template.setAttribute("checkModificationReferences", generateBodyModificationCheck(document));
		template.setAttribute("autocompleteRepos", generateAutoCompleteRepos(document));
		template.setAttribute("docReference", generateDocReference(document));
		template.setAttribute("imports", generateImports(document));
		template.setAttribute("getDocReference", generateDocReferenceVariable(document));
		template.setAttribute("docReferenceFields", generateDocReferenceFields(document));
		template.setAttribute("headerReferences", generateHeaderReferences(document));

		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"PersistenceServiceImpl.java");
	}

	private Object generateDocReferenceVariable(Document document) {
		String variable="";
		if (document.getBodyDocReferenceField()!=null || document.getHeaderDocReferenceField()!=null){
			Field referenceField=document.getBodyDocReferenceField()!=null?document.getBodyDocReferenceField():document.getHeaderDocReferenceField();
			StringTemplate stringTemplate = new StringTemplate("$upperDocRef$ $docRef$ = $docRef$Repository.findByNumericId(Long.parseLong($docName$.get$upperRefField$()));\n");
			stringTemplate.setAttribute("docName", document.getName());
			stringTemplate.setAttribute("docRef", referenceField.getDocRefType());
			stringTemplate.setAttribute("upperRefField", CodeUtils.toCamelCase(referenceField.getName()));
			stringTemplate.setAttribute("upperDocRef", CodeUtils.toCamelCase(referenceField.getDocRefType()));
			variable=stringTemplate.toString();
		}
		return variable;
	}

	private Object generateHeaderReferences(Document document) {
		String references="";
		Field referenceField = document.getHeaderDocReferenceField();
		if (referenceField!=null){
			List<String> headerFields = referenceField.getHeaderFields();
			for (String field : headerFields) {
				StringTemplate stringTemplate = new StringTemplate("$docName$.set$upperFieldName$($docRef$.get$upperFieldName$());\n");
				stringTemplate.setAttribute("docName", document.getName());
				stringTemplate.setAttribute("docRef", referenceField.getDocRefType());
				stringTemplate.setAttribute("upperFieldName", CodeUtils.toCamelCase(field));
				references+=stringTemplate.toString();
			}
		}
		return references;
	}

	private Object generateDocReference(Document document) {
		String reference="";
		Field field = document.getDocReferenceField();
		if (field!=null){
			StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.tableCodePath+"persistence");
			StringTemplate template = templateGroup.getInstanceOf("referenceDeclaration");
			template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getDocRefType()));
			template.setAttribute("refType", field.getDocRefType());
			reference+=template.toString()+"\n";
		}
		
		return reference;
	}

	private Object generateDocReferenceFields(Document document) {
		String getFields="";
		Field referenceField = document.getBodyDocReferenceField();
		if(referenceField!=null){
			List<String> bodyFields = referenceField.getBodyFields();
			StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
			StringTemplate stringTemplate = templateGroup.getInstanceOf("bodyReference");
			stringTemplate.setAttribute("upperDocRef", CodeUtils.toCamelCase(referenceField.getDocRefType()));
			stringTemplate.setAttribute("docRef", referenceField.getDocRefType());
			stringTemplate.setAttribute("docName", document.getName());
			stringTemplate.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
			
			String fields="";
			for (String field : bodyFields) {
				
				StringTemplate template = new StringTemplate("$docName$Body.set$upperFieldName$($docRef$Body.get$upperFieldName$());\n");
				template.setAttribute("docRef", referenceField.getDocRefType());
				template.setAttribute("docName", document.getName());
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field));
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
			template.setAttribute("upperRefType", CodeUtils.toCamelCase(referenceField.getDocRefType()));
			template.setAttribute("refType", referenceField.getDocRefType());
			imports+=template.toString()+"\n";
			references.add(referenceField.getDocRefType());
		}
				
		List<Field> fields = document.getAllFields();
		for (Field field : fields) {
			if (CodeUtils.generateAutoCompleteReference(field) && !references.contains(field.getRefType())){
				StringTemplate template = templateGroup.getInstanceOf("referenceImport");
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
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
			if (CodeUtils.generateAutoCompleteReference(field) && !references.contains(field.getRefType())){
				StringTemplate template = templateGroup.getInstanceOf("referenceDeclaration");
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
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
			if (field.isReference() && CodeUtils.generateAutoCompleteReference(field) && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("bodyModificationReferenceField");
				template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("displayField", CodeUtils.toCamelCase(field.getDisplayField()));
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
			if (field.isReference() && CodeUtils.generateAutoCompleteReference(field) && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("bodyReferenceCheck");
				template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("displayField", CodeUtils.toCamelCase(field.getDisplayField()));
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
			if (field.isReference() && CodeUtils.generateAutoCompleteReference(field) && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("headerReferenceCheck");
				template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getRefType()));
				template.setAttribute("refType", field.getRefType());
				template.setAttribute("displayField", CodeUtils.toCamelCase(field.getDisplayField()));
				template.setAttribute("fieldName", field.getName());
				
				checks+=template.toString()+"\n";
			}
			
			if (field.getDocRefType()!=null && field.getCheckReference()){
				StringTemplate template = templateGroup.getInstanceOf("headerDocReferenceCheck");
				template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
				template.setAttribute("upperFieldName", CodeUtils.toCamelCase(field.getName()));
				template.setAttribute("upperRefType", CodeUtils.toCamelCase(field.getDocRefType()));
				template.setAttribute("refType", field.getDocRefType());
				template.setAttribute("displayField", CodeUtils.toCamelCase(Cyberconstants.numericIdName));
				template.setAttribute("fieldName", field.getName());
				
				checks+=template.toString()+"\n";
			}
		}
		
		return checks;
	}

	private void generatePersistenceInterface(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("persistenceServiceInterface");
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/services/"+document.getName(), CodeUtils.toCamelCase(document.getName())+"PersistenceService.java");
	}

	private void generateDomainHeaderClass(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.documentCodePath+"persistence");
		StringTemplate template = templateGroup.getInstanceOf("domainHeader");
		template.setAttribute("fields", generateDomainClassHeaderFieldDeclaration(document));
		template.setAttribute("gettersSetters", generateGettersSetters(document.getHeader()));
		template.setAttribute("upperDocName", CodeUtils.toCamelCase(document.getName()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("referenceFields", generateHeaderReferenceDomainFieldsAndGS(document));
		
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
		
		CodeUtils.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/persistence/domain", CodeUtils.toCamelCase(document.getName())+".java");
	}

	private String generateGettersSetters(List<Field> fields) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("persistence",Cybertables.utilCodePath);
		
		String text="";
		
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				StringTemplate template = templateGroup.getInstanceOf("getterSetter");
				template.setAttribute("type", field.isReference()||field.getDocRefType()!=null?Cybertables.stringType:field.getType());
				template.setAttribute("name", field.getName());
				template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName()));
				text+=template.toString()+"\n";
				
				if (field.isEmbeddedReference()){
					template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", CodeUtils.toCamelCase(field.getRefType()));
					template.setAttribute("name", field.getName()+"Entity");
					template.setAttribute("fieldName", CodeUtils.toCamelCase(field.getName())+"Entity");
					text+=template.toString()+"\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtils.toCamelCase(compoundField.getName()));
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
					subTemp.setAttribute("type", CodeUtils.toCamelCase(field.getRefType()));
					subTemp.setAttribute("name", field.getName()+"Entity");
					body+=subTemp.toString();
					body+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
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
					subTemp.setAttribute("type", CodeUtils.toCamelCase(field.getRefType()));
					subTemp.setAttribute("name", field.getName()+"Entity");
					body+=subTemp.toString();
					body+="\n";
				}
			}
			else{
				List<Field> compoundKey = CodeUtils.getCompoundKey(cybertables, field.getRefType());
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
