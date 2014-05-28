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
		if (CodeUtil.containsField(this, Spark.activeFieldName))
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
	
	public List<Field> getCompoundIndex(Spark spark){
		ArrayList<Field> compoundIndex = new ArrayList<Field>();
		for (Field field:fields) {
			if (field.getKeyCompound()&&!field.getCompoundReference())
				compoundIndex.add(field);
			if (field.getKeyCompound()&&field.getCompoundReference())
				compoundIndex.addAll(CodeUtil.getCompoundKey(spark, field.getRefType()));
		}
		return compoundIndex;
	}
	
	public boolean hasCompoundReference(){
		for (Field field: fields) {
			if (field.getCompoundReference())
				return true;
		}
		return false;
	}
	
	public List<Field> getCompositeFields(){
		ArrayList<Field> list = new ArrayList<Field>();
		for (Field field: fields) {
			if (field.getCompoundReference())
				list.add(field);
		}
		return list;
	}
	
}
