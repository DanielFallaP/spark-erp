package co.com.cybersoft.generator.code.model;

public class Field {
	private String name;
	private String type;
	private Boolean unique;

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
	
}
