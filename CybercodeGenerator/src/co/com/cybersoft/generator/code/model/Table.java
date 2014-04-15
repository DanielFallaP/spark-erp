package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.generator.code.model.Field;

public class Table {
	private String name;
	private List<Field> fields;
	private List<ReferenceField> references=new ArrayList<ReferenceField>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public List<ReferenceField> getReferences() {
		return references;
	}
	public void setReferences(List<ReferenceField> references) {
		this.references = references;
	}
	
}
