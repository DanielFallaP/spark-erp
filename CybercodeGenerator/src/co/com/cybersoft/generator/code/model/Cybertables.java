package co.com.cybersoft.generator.code.model;

import java.util.List;

public class Cybertables implements Cyberconstants{
	private String moduleName;
	
	private List<Table> tables;
	
	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
