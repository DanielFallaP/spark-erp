package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.generator.code.util.CodeUtil;

public class Document {

	private String name;
	
	private String spanishName;
	
	private List<Field> header;
	
	private List<Field> body;
	
	private JavaAPI onHeaderSave;
	
	private JavaAPI onHeaderPreSave;
	
	private Boolean deleteOriginal=Boolean.TRUE;
	
	private Boolean delete=Boolean.TRUE;
	
	private Boolean add=Boolean.TRUE;
	
	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getAdd() {
		return add;
	}

	public void setAdd(Boolean add) {
		this.add = add;
	}

	public Boolean getDeleteOriginal() {
		return deleteOriginal;
	}

	public void setDeleteOriginal(Boolean deleteOriginal) {
		this.deleteOriginal = deleteOriginal;
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
				compoundIndex.addAll(CodeUtil.getCompoundKey(cybertables, field.getRefType()));
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
