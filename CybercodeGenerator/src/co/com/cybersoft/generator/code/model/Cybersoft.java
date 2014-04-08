package co.com.cybersoft.generator.code.model;

import java.util.List;

public class Cybersoft {
	
	private List<Table> tables;
	
	public final static String codePath="src/co/com/cybersoft/generator/code/";
	
	public final static String targetClassPath="build/java/co/com/cybersoft/";
	
	public final static String targetViewPath="build/webapp/WEB-INF/views/";

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

}
