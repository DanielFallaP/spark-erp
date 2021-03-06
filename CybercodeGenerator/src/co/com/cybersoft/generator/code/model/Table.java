package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.generator.code.util.CodeUtils;

public class Table {
	private String name;
	private List<Field> fields;
	private Boolean labelTable=Boolean.FALSE;
	private String spanishName;
	private Boolean singletonTable=Boolean.FALSE;
	private String module;
	private List<Action> actions;
	private Boolean multiCompany;

	
	public Boolean getMultiCompany() {
		return multiCompany;
	}
	public void setMultiCompany(Boolean multiCompany) {
		this.multiCompany = multiCompany;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
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
		if (CodeUtils.containsField(this, Cybertables.activeFieldName))
			return true;
		else
			return false;
	}
	
	public boolean hasFieldGroups(){
		return fields.get(0).getFieldGroup()!=null;
	}
	
	public List<String> getFieldGroups(){
		ArrayList<String> fieldGroups = new ArrayList<>();
		for (Field field : fields) {
			if (!fieldGroups.contains(field.getFieldGroup())){
				fieldGroups.add(field.getFieldGroup());
			}
		}
		return fieldGroups;
	}
	
	public boolean hasCompoundIndex(){
		for (Field field:fields) {
			if (field.getKeyCompound())
				return true;
		}
		return false;
	}
	
	public List<Field> getCompoundIndex(Cybertables spark){
		ArrayList<Field> compoundIndex = new ArrayList<Field>();
		for (Field field:fields) {
			if (field.getKeyCompound()&&!field.getCompoundReference()){
				if (CodeUtils.reservedSQLWords.contains(field.getName()) && !field.isReference()){
					field.setColumnName("f_"+field.getName());
				}
				else if (field.isReference())
					field.setColumnName(field.getName().toUpperCase()+"_ID");
				else
					field.setColumnName(field.getName());
					
				compoundIndex.add(field);
			}
			if (field.getKeyCompound()&&field.getCompoundReference())
				compoundIndex.addAll(CodeUtils.getCompoundKey(spark, field.getRefType()));
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
