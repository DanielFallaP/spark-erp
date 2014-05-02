package co.com.cybersoft.generator.code.model;

public class Field {
	private String name;
	private String type;
	private Boolean unique;
	private Integer length;
	private Boolean required=Boolean.TRUE;
	private Boolean visible=Boolean.TRUE;
	private Boolean largeText=Boolean.FALSE;
	private Boolean readOnly=Boolean.FALSE;
	
	//For references only
	private String refType;
	private String displayField;

	public Boolean getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getDisplayField() {
		return displayField;
	}
	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}
	public Boolean getLargeText() {
		return largeText;
	}
	public void setLargeText(Boolean largeText) {
		this.largeText = largeText;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getUnique() {
		return unique;
	}
	public void setUnique(Boolean unique) {
		this.unique = unique;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public boolean isReference(){
		return this.type==null;
	}

}
