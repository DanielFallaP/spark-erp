package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.generator.code.util.CodeUtil;

public class Table {
	private String name;
	private List<Field> fields;
	private Boolean labelTable=Boolean.FALSE;
	private String spanishName;
	private Boolean singletonTable=Boolean.FALSE;
	
	public Boolean getSingletonTable() {
		return singletonTable;
	}
	public void setSingletonTable(Boolean singletonTable) {
		this.singletonTable = singletonTable;
	}
	public String getSpanishName() {
		return spanishName;
	}
	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}
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
	
	public boolean isActiveReference(){
		if (CodeUtil.containsField(this, Cybersystems.activeFieldName))
			return true;
		else
			return false;
	}
	
	public boolean hasCompoundIndex(){
		for (Field field:fields) {
			if (field.getKeyCompound())
				return true;
		}
		return false;
	}
	
	public List<Field> getCompoundIndex(){
		ArrayList<Field> compoundIndex = new ArrayList<Field>();
		for (Field field:fields) {
			if (field.getKeyCompound())
				compoundIndex.add(field);
		}
		return compoundIndex;
	}
	
}
