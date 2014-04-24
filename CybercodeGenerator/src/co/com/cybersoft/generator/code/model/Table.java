package co.com.cybersoft.generator.code.model;

import java.util.List;

public class Table {
	private String name;
	private List<Field> fields;
	private Boolean labelTable=Boolean.FALSE;
	
	public Boolean getLabelTable() {
		return labelTable;
	}
	public void setLabelTable(Boolean labelTable) {
		this.labelTable = labelTable;
	}
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
	
}
