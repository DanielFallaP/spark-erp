package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private String columnName;
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
	private Boolean mobileDisplayable=Boolean.TRUE;
	private ArithmeticExpression value;
	private String append;
	private String fieldGroup;
	private Boolean trans=Boolean.FALSE;
	
	//For references only
	private String refType;
	private String displayField;
	private List<String> embeddedFields=new ArrayList<String>();
	private String tableName;
	private Boolean checkReference=Boolean.TRUE;
	private List<String> additionalFields=new ArrayList<String>();
	
	//For documents only
	private String docRefType;
	private List<String> bodyFields=new ArrayList<String>();
	private List<String> headerFields=new ArrayList<String>();
	private Boolean searchDisplayable=Boolean.FALSE;
	private JavaAPI onBodySave;
	
	private OnLoad onLoad;
	
	
	public Boolean getTrans() {
		return trans;
	}
	public void setTrans(Boolean trans) {
		this.trans = trans;
	}
	public List<String> getAdditionalFields() {
		return additionalFields;
	}
	public void setAdditionalFields(List<String> additionalFields) {
		this.additionalFields = additionalFields;
	}
	private JavaScriptAPI autoCompletePeerFunction;
	
	public String getFieldGroup() {
		return fieldGroup;
	}
	public void setFieldGroup(String fieldGroup) {
		this.fieldGroup = fieldGroup;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public JavaAPI getOnBodySave() {
		return onBodySave;
	}
	public void setOnBodySave(JavaAPI onBodySave) {
		this.onBodySave = onBodySave;
	}
	public Boolean getMobileDisplayable() {
		return mobileDisplayable;
	}
	public void setMobileDisplayable(Boolean mobileDisplayable) {
		this.mobileDisplayable = mobileDisplayable;
	}
	public List<String> getHeaderFields() {
		return headerFields;
	}
	public void setHeaderFields(List<String> headerFields) {
		this.headerFields = headerFields;
	}
	public ArithmeticExpression getValue() {
		return value;
	}
	public void setValue(ArithmeticExpression value) {
		this.value = value;
	}
	
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = append;
	}
	public Boolean getSearchDisplayable() {
		return searchDisplayable;
	}
	public void setSearchDisplayable(Boolean searchDisplayable) {
		this.searchDisplayable = searchDisplayable;
	}
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
