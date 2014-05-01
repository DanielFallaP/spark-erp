package co.com.cybersoft.generator.code.model;

import java.util.List;

public class Cybersoft {
	

	private List<Table> tables;
	
	public final static String codePath="src/co/com/cybersoft/generator/code/";
	
	public final static String targetClassPath="build/java/co/com/cybersoft/";
	
	public final static String targetViewPath="build/webapp/WEB-INF/views/";

	public final static String dateType="Date";
	
	public final static String stringType="String";
	
	public final static String integerType="Integer";

	public final static String longType="Long";
	
	public final static String doubleType="Double";
	
	public final static String arraySeparator="/////";
	
	public final static String decisionTable="yesNo";
	
	public final static String decisionTableTrueValue="label.true";
	
	public final static String decisionTableFalseValue="label.false";
	
	public final static String activeFieldName="active";
	
	public final static String projectName="Cybersoft";

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

}
