package co.com.cybersoft.generator.code.docs.web;

import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
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
			generateDomainHeader(document);
			generateDomainBody(document);
		}
	}

	private void generateSearchController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("searchController");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/web/controller/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"SearchController.java");
	}

	private void generateSaveController(Document document) {
		StringTemplateGroup templateGroup = new StringTemplateGroup("controller",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("saveController");
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		
		template.setAttribute("imports", generateSaveControllerImports(document));
		
		CodeUtil.writeClass(template.toString(), Cybertables.targetDocumentClassPath+"/web/controller/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"Controller.java");
	}

	private String generateSaveControllerImports(Document document) {
		// TODO Auto-generated method stub
		String imports="";
		
		return imports;
	}
	
	private void generateDomainBody(Document document){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainBody");
		template.setAttribute("fields", generateDomainFieldsAndGS(document.getBody()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("imports", generateDomainClassImports(document.getBody()));
		CodeUtil.writeClass(template.toString(),Cybertables.targetDocumentClassPath+"/web/domain/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"BodyInfo.java");
	}
	
	private void generateDomainHeader(Document document){
		StringTemplateGroup templateGroup = new StringTemplateGroup("web",Cybertables.documentCodePath+"web");
		StringTemplate template = templateGroup.getInstanceOf("domainHeader");
		template.setAttribute("fields", generateDomainFieldsAndGS(document.getHeader()));
		template.setAttribute("docName", document.getName());
		template.setAttribute("upperDocName", CodeUtil.toCamelCase(document.getName()));
		template.setAttribute("imports", generateDomainClassImports(document.getHeader()));
		CodeUtil.writeClass(template.toString(),Cybertables.targetDocumentClassPath+"/web/domain/"+document.getName(), CodeUtil.toCamelCase(document.getName())+"Info.java");
	}

	private String generateDomainClassImports(List<Field> fields) {
		String imports="";
		
		//Check for validation constraints
		for (Field field : fields) {
			if (field.getLength()!=null && field.getType().equals(Cybertables.stringType)){
				imports+="import org.hibernate.validator.constraints.Length;\n";
				break;
			}
				
		}
		
		for (Field field : fields) {
			if (!field.isReference() && field.getRequired() && field.getVisible() && field.getType().equals(Cybertables.stringType)){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
			if (field.isReference()&&field.getRequired()){
				imports+="import org.hibernate.validator.constraints.NotEmpty;\n";
				break;
			}
		}
		
			for (Field field : fields) {
				if (!field.isReference()){
					if(field.getRequired() && field.getVisible() && (field.getType().equals(Cybertables.integerType) || 
							field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
						imports+="import javax.validation.constraints.NotNull;\n";
						break;
					}
				}
			}
		
		for (Field field : fields) {
			if (field.getLength()!=null && (field.getType().equals(Cybertables.integerType)
					||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
				imports+="import org.hibernate.validator.constraints.Range;\n";
			}
		}
		
		for (Field field : fields) {
			
			if (field.isReference()){
				StringTemplate template = new StringTemplate("import co.com.cybersoft.tables.core.domain.$entityName$Details;\n");
				template.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
				imports+=template.toString();
			}
		}
		
		for (Field field : fields) {
			if (field.getCompoundReference()){
				List<Field> compoundKey = CodeUtil.getCompoundKey(cyberdocs, field.getRefType());
				for (Field compoundField : compoundKey) {
					StringTemplate stringTemplate = new StringTemplate("import co.com.cybersoft.tables.core.domain.$fieldName$Details;\n");
					stringTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					imports+=stringTemplate.toString();
				}
			}
		}
		
		return imports;
	}

	private String generateDomainFieldsAndGS(List<Field> fields) {
		String body="";
		
		//Attributes
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
				if (!field.isReference() && !field.getType().equals(Cybertables.booleanType)){
					if (field.getLength()!=null && field.getType().equals(Cybertables.stringType)){
						body+="@Length(max="+field.getLength()+")\n";
					}
					if (field.getRequired()&&field.getVisible()&&(field.isReference()|| field.getType().equals(Cybertables.stringType))){
						body+="@NotEmpty\n";
					}
					
					if (field.getRequired()&&field.getVisible()&&!field.isReference()&&(field.getType().equals(Cybertables.integerType)
							||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
						body+="@NotNull\n";
					}
					
					if (field.getLength()!=null && !field.isReference() && (field.getType().equals(Cybertables.integerType)
							||field.getType().equals(Cybertables.longType) || field.getType().equals(Cybertables.doubleType))){
						body+="@Range(max="+CodeUtil.getMaxNumber(field.getLength())+")\n";
					}
				}
				
				if (field.isReference()&&field.getRequired()){
					body+="@NotEmpty\n";
				}
				
				StringTemplate fieldTemplate;
				
				if (!field.isReference()){
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", field.getType());
					fieldTemplate.setAttribute("name", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";			
				}
				else{
					fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.stringType);
					fieldTemplate.setAttribute("name", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";
					
					fieldTemplate = new StringTemplate("private List<$entityName$Details> $tableName$List;");
					fieldTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getRefType()));
					fieldTemplate.setAttribute("tableName", field.getName());
					body+=fieldTemplate.toString();
					body+="\n";				
					
					if (field.isEmbeddedReference()){
						fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
						fieldTemplate.setAttribute("type", CodeUtil.toCamelCase(field.getRefType())+"Details");
						fieldTemplate.setAttribute("name", field.getName()+"Details");
						body+=fieldTemplate.toString();
						body+="\n";				
					}
					
				}
			}
			else{
				List<Field> compoundKey = CodeUtil.getCompoundKey(cyberdocs, field.getRefType());
				for (Field compoundField : compoundKey) {
					if (field.getRequired())
						body+="@NotEmpty\n";
					StringTemplate fieldTemplate = new StringTemplate("private $type$ $name$;\n\n");
					fieldTemplate.setAttribute("type", Cybertables.stringType);
					fieldTemplate.setAttribute("name", compoundField.getName());
					body+=fieldTemplate.toString();
					body+="\n";
					
					fieldTemplate = new StringTemplate("private List<$entityName$Details> $tableName$List;");
					fieldTemplate.setAttribute("entityName", CodeUtil.toCamelCase(compoundField.getTableName()));
					fieldTemplate.setAttribute("tableName", compoundField.getTableName());
					body+=fieldTemplate.toString();
					body+="\n";				
					
				}
			}
		}
				
		//Getters and setters
		for (Field field : fields) {
			if (!field.getCompoundReference()){
				
				if (!field.isReference()){
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("getterSetter");
					gettersSettersTemplate.setAttribute("type", field.getType());
					gettersSettersTemplate.setAttribute("name", field.getName());
					gettersSettersTemplate.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
					body+=gettersSettersTemplate.toString()+"\n\n";
				}
				else{
						StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
						StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
						gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(field.getName()));
						gettersSettersTemplate.setAttribute("tableName", field.getName());
						gettersSettersTemplate.setAttribute("refEntityName", CodeUtil.toCamelCase(field.getRefType()));
						body+=gettersSettersTemplate.toString()+"\n\n";			
						
						StringTemplate template = templateGroup.getInstanceOf("getterSetter");
						template.setAttribute("type", Cybertables.stringType);
						template.setAttribute("name", field.getName());
						template.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName()));
						
						if (field.isEmbeddedReference()){
							StringTemplate addOps = new StringTemplate("\nfor ($referenceType$Details $referenceField$Details : $referenceField$List) {\n"+
									"if ($referenceField$Details.get$upperFieldName$().equals($referenceField$))\n"+
									"this.$referenceField$Details=$referenceField$Details;}\n");
							addOps.setAttribute("referenceType", CodeUtil.toCamelCase(field.getRefType()));
							addOps.setAttribute("referenceField", field.getName());
							addOps.setAttribute("upperFieldName", CodeUtil.toCamelCase(field.getDisplayField()));
							addOps.setAttribute("upperDisplayName", CodeUtil.toCamelCase(field.getName()));
							template.setAttribute("addOps", addOps.toString());
						}
						
						body+=template.toString()+"\n\n";
						
						if (field.isEmbeddedReference()){
							StringTemplate addGetterSetter=templateGroup.getInstanceOf("getterSetter");
							addGetterSetter.setAttribute("type", CodeUtil.toCamelCase(field.getRefType())+"Details");
							addGetterSetter.setAttribute("name", field.getName()+"Details");
							addGetterSetter.setAttribute("fieldName", CodeUtil.toCamelCase(field.getName())+"Details");
							body+=addGetterSetter.toString()+"\n\n";
						}
				}
			}
			else{
				
				List<Field> compoundKey = CodeUtil.getCompoundKey(cyberdocs, field.getRefType());
				for (Field compoundField : compoundKey) {
					
					StringTemplateGroup templateGroup = new StringTemplateGroup("domain group",Cybertables.utilCodePath);
					StringTemplate gettersSettersTemplate = templateGroup.getInstanceOf("listGetters");
					gettersSettersTemplate.setAttribute("entityName", CodeUtil.toCamelCase(compoundField.getTableName()));
					gettersSettersTemplate.setAttribute("tableName", compoundField.getTableName());
					gettersSettersTemplate.setAttribute("refEntityName", CodeUtil.toCamelCase(compoundField.getTableName()));
					body+=gettersSettersTemplate.toString()+"\n\n";			
					
					StringTemplate template = templateGroup.getInstanceOf("getterSetter");
					template.setAttribute("type", Cybertables.stringType);
					template.setAttribute("name", compoundField.getName());
					template.setAttribute("fieldName", CodeUtil.toCamelCase(compoundField.getName()));
					
					body+=template.toString()+"\n\n";
				}
			}
				
		}
		
		return body;
	}
	

}
