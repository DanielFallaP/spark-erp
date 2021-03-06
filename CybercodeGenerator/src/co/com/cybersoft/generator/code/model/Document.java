package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.generator.code.util.CodeUtils;

public class Document {

	private String name;
	
	private String spanishName;
	
	private List<Field> header;
	
	private List<Field> body;
	
	private JavaAPI onHeaderSave;
	
	private JavaAPI onHeaderPreSave;
	
	private JavaAPI onBodyModification;
	
	private JavaAPI onBodyPreModification;
	
	private JavaAPI onBodyAddition;
	
	private JavaAPI onBodyPreAddition;
	
	private Boolean originalDeletion;
	
	private Boolean deletion=Boolean.TRUE;
	
	private Boolean addition=Boolean.TRUE;
	
	public Boolean hasReadyField(){
		for (Field field:header){
			if (field.getName().equals(Cyberconstants.readyFieldName)&&field.getType().equals(Cyberconstants.booleanType))
				return true;
		}
		return false;
	}
	
	public JavaAPI getOnBodyModification() {
		return onBodyModification;
	}

	public void setOnBodyModification(JavaAPI onBodyModification) {
		this.onBodyModification = onBodyModification;
	}

	public JavaAPI getOnBodyPreModification() {
		return onBodyPreModification;
	}

	public void setOnBodyPreModification(JavaAPI onBodyPreModification) {
		this.onBodyPreModification = onBodyPreModification;
	}

	public JavaAPI getOnBodyAddition() {
		return onBodyAddition;
	}



	public void setOnBodyAddition(JavaAPI onBodyAddition) {
		this.onBodyAddition = onBodyAddition;
	}



	public JavaAPI getOnBodyPreAddition() {
		return onBodyPreAddition;
	}



	public void setOnBodyPreAddition(JavaAPI onBodyPreAddition) {
		this.onBodyPreAddition = onBodyPreAddition;
	}



	public Boolean getDeletion() {
		return deletion;
	}

	public void setDeletion(Boolean deletion) {
		this.deletion = deletion;
	}

	public Boolean getAddition() {
		return addition;
	}

	public void setAddition(Boolean addition) {
		this.addition = addition;
	}

	public Boolean getOriginalDeletion() {
		return originalDeletion;
	}

	public void setOriginalDeletion(Boolean originalDeletion) {
		this.originalDeletion = originalDeletion;
	}

	public JavaAPI getOnHeaderPreSave() {
		return onHeaderPreSave;
	}

	public void setOnHeaderPreSave(JavaAPI onHeaderPreSave) {
		this.onHeaderPreSave = onHeaderPreSave;
	}

	public JavaAPI getOnHeaderSave() {
		return onHeaderSave;
	}

	public void setOnHeaderSave(JavaAPI onHeaderSave) {
		this.onHeaderSave = onHeaderSave;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpanishName() {
		return spanishName;
	}

	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}

	public List<Field> getHeader() {
		return header;
	}

	public void setHeader(List<Field> header) {
		this.header = header;
	}

	public List<Field> getBody() {
		return body;
	}

	public void setBody(List<Field> body) {
		this.body = body;
	}
	
	public boolean hasCompoundIndex(){
		for (Field field:header) {
			if (field.getKeyCompound())
				return true;
		}
		return false;
	}
	
	public boolean hasCompoundReference(){
		for (Field field: header) {
			if (field.getCompoundReference())
				return true;
		}
		return false;
	}
	
	public List<Field> getCompositeFields(){
		ArrayList<Field> list = new ArrayList<Field>();
		for (Field field: header) {
			if (field.getCompoundReference())
				list.add(field);
		}
		return list;
	}
	
	public List<Field> getCompoundIndex(Cybertables cybertables){
		ArrayList<Field> compoundIndex = new ArrayList<Field>();
		for (Field field:header) {
			if (field.getKeyCompound()&&!field.getCompoundReference())
				compoundIndex.add(field);
			if (field.getKeyCompound()&&field.getCompoundReference())
				compoundIndex.addAll(CodeUtils.getCompoundKey(cybertables, field.getRefType()));
		}
		return compoundIndex;
	}
	
	public List<Field> getAllFields(){
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.addAll(header);
		fields.addAll(body);
		
		return fields;
	}
	
	public Field getBodyDocReferenceField(){
		List<Field> header = getHeader();
		for (Field field : header) {
			if (field.getDocRefType()!=null&&field.getBodyFields()!=null&&!field.getBodyFields().isEmpty()){
				return field;
			}
		}
		return null;
	}
	
	public Field getDocReferenceField(){
		List<Field> header = getHeader();
		for (Field field : header) {
			if (field.getDocRefType()!=null){
				return field;
			}
		}
		return null;
	}
	
	public Field getHeaderDocReferenceField(){
		List<Field> header = getHeader();
		for (Field field : header) {
			if (field.getDocRefType()!=null&&field.getHeaderFields()!=null&&!field.getHeaderFields().isEmpty()){
				return field;
			}
		}
		return null;
	}
	
	
	public Field getBodyKey(){
		for (Field field : body) {
			if (field.getUnique()){
				return field;
			}
		}
		return null;
	}
	
}
