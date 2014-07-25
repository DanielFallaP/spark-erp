package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private String name;
	private String type;
	private Boolean unique=Boolean.FALSE;
	private Integer length;
	private Boolean required=Boolean.TRUE;
	private Boolean visible=Boolean.TRUE;
	private Boolean largeText=Boolean.FALSE;
	private Boolean readOnly=Boolean.FALSE;
	private Boolean autocomplete=Boolean.FALSE;
	private String spanishName;
	private String defaultValue;
	private Boolean labelField=Boolean.FALSE;
	private Boolean keyCompound=Boolean.FALSE;
	private Boolean compoundReference=Boolean.FALSE;
	private Boolean displayable=Boolean.TRUE;
	
	//For references only
	private String refType;
	private String displayField;
	private List<String> embeddedFields=new ArrayList<String>();
	private String tableName;
	private Boolean checkReference=Boolean.TRUE;
	
	//For documents only
	private String docRefType;
	private List<String> bodyFields=new ArrayList<String>();
	
	private OnLoad onLoad;
	
	private JavaScriptAPI autoCompletePeerFunction;
	
	public JavaScriptAPI getAutoCompletePeerFunction() {
		return autoCompletePeerFunction;
	}
	public void setAutoCompletePeerFunction(JavaScriptAPI autoCompletePeerFunction) {
		this.autoCompletePeerFunction = autoCompletePeerFunction;
	}
	public OnLoad getOnLoad() {
		return onLoad;
	}
	public void setOnLoad(OnLoad onLoad) {
		this.onLoad = onLoad;
	}
	public Boolean getCheckReference() {
		return checkReference;
	}
	public void setCheckReference(Boolean checkReference) {
		this.checkReference = checkReference;
	}
	public String getDocRefType() {
		return docRefType;
	}
	public void setDocRefType(String docRefType) {
		this.docRefType = docRefType;
	}
	public Boolean getDisplayable() {
		return displayable;
	}
	public void setDisplayable(Boolean displayable) {
		this.displayable = displayable;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Boolean getCompoundReference() {
		return compoundReference;
	}
	public void setCompoundReference(Boolean compoundReference) {
		this.compoundReference = compoundReference;
	}
	public Boolean getKeyCompound() {
		return keyCompound;
	}
	public void setKeyCompound(Boolean keyCompound) {
		this.keyCompound = keyCompound;
	}
	public List<String> getEmbeddedFields() {
		return embeddedFields;
	}
	public void setEmbeddedFields(List<String> embeddedFields) {
		this.embeddedFields = embeddedFields;
	}
	public Boolean getLabelField() {
		return labelField;
	}
	public void setLabelField(Boolean labelField) {
		this.labelField = labelField;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Boolean getAutocomplete() {
		return autocomplete;
	}
	public void setAutocomplete(Boolean autocomplete) {
		this.autocomplete = autocomplete;
	}
	public String getSpanishName() {
		return spanishName;
	}
	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}
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
		if (type==null && refType!=null)
			return true;
		else
			return false;
	}
	
	public boolean isEmbeddedReference(){
		return !embeddedFields.isEmpty(); 
	}
	public List<String> getBodyFields() {
		return bodyFields;
	}
	public void setBodyFields(List<String> bodyFields) {
		this.bodyFields = bodyFields;
	}

}
