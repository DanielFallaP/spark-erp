package co.com.cybersoft.generator.code.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

/**
 * Generates the message labels for the view associated with 
 * the generated tables 
 * @author Daniel Falla
 *
 */
public class ResourceGenerator {
	
	private Connection con;
	
	private PreparedStatement pst;
	
	private final Cybersoft cybersoft;
	
	public ResourceGenerator(Cybersoft cybersoft){
		this.cybersoft=cybersoft;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://cybersoft.c6g4nh1b2apj.us-east-1.rds.amazonaws.com/cybersoft?"
		              + "user=cybersoft&password=petronube");
			
			pst = con.prepareStatement("insert into cybersoft.dictionary (message) values (?)");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertMessageLabels(){
		List<String> messages = new LinkedList<String>();
		insertMessages(addConfigurationMessages(addCreationMessages(addModificationMessages(addSearchMessages(messages)))));
	}
	
	private List<String> addCreationMessages(List<String> messages){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			messages.add(table.getName()+"CreationTitle");
			messages.add(table.getName()+"CreationError");
		}		
		
		return messages;
	}
	
	private List<String> addModificationMessages(List<String> messages){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			messages.add(table.getName()+"ModificationTitle");
		}		
		
		return messages;
	}
	
	private List<String> addSearchMessages(List<String> messages){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			List<Field> fields = table.getFields();
			for (Field field : fields) {
				messages.add(table.getName()+CodeUtil.toCamelCase(field.getName()));
			}
			messages.add(table.getName()+"Title");
			messages.add(table.getName()+"Info");
			messages.add(table.getName());
		}		
		
		return messages;
	}
	
	private List<String> addConfigurationMessages(List<String> messages){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			messages.add(table.getName()+"Configuration");
		}		
		
		return messages;
	}
	
	private void insertMessages(List<String> messages){
		for (String message : messages) {
			try {
				pst.setString(1, message);
				pst.execute();
			} catch (SQLException e) {
				//Just continue with the next message if this one was previously inserted
				continue;
			}
		}
	}
}
