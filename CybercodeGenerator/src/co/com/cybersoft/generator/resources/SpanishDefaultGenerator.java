package co.com.cybersoft.generator.resources;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class SpanishDefaultGenerator {
	private final PreparedStatement updatePst;
	public SpanishDefaultGenerator(final PreparedStatement pst){
		this.updatePst=pst;
	}
	
	public void updateDefaultSpanishTableMessages(Table table) throws SQLException {
		updateDefaultSpanishTableFields(table);
		updateDefaultSpanishTableName(table);
		updateDefaultSpanishTableInfoName(table);
		updateDefaultSpanishTableModificationName(table);
		updateDefaultSpanishTableCreationName(table);
		updateDefaultSpanishTableConfigurationName(table);
		
	}
	
	public void updateDefaultSpanishDocumentMessages(Document document) throws SQLException{
		updateDefaultSpanishFields(document);
		updateDefaultSpanishDocumentName(document);
		updateDefaultSpanishInfoName(document);
		updateDefaultSpanishModificationName(document);
		updateDefaultSpanishCreationName(document);
		
	}
	
	private void updateDefaultSpanishCreationName(Document document) throws SQLException {
		updatePst.setString(1, "Creación de "+document.getSpanishName());
		updatePst.setString(2, document.getName()+"CreationTitle");
		updatePst.execute();
	}

	private void updateDefaultSpanishModificationName(Document document) throws SQLException {
		updatePst.setString(1, "Modificación de "+document.getSpanishName());
		updatePst.setString(2, document.getName()+"ModificationTitle");
		updatePst.execute();
	}

	private void updateDefaultSpanishInfoName(Document document) throws SQLException {
		updatePst.setString(1, document.getSpanishName());
		updatePst.setString(2, document.getName()+"Info");
		updatePst.execute();
	}

	private void updateDefaultSpanishDocumentName(Document document) throws SQLException {
		updatePst.setString(1, document.getSpanishName());
		updatePst.setString(2, document.getName());
		updatePst.execute();
	}

	private void updateDefaultSpanishFields(Document document) throws SQLException {
		List<Field> fields = document.getAllFields();
		for (Field field : fields) {
			if (field.getSpanishName()!=null){
				updatePst.setString(1, field.getSpanishName());
				updatePst.setString(2, document.getName()+CodeUtil.toCamelCase(field.getName()));
				updatePst.execute();
			}
		}
	}

	private void updateDefaultSpanishTableConfigurationName(Table table) throws SQLException {
		updatePst.setString(1, table.getSpanishName());
		updatePst.setString(2, table.getName()+"Configuration");
		updatePst.execute();
		
	}

	private void updateDefaultSpanishTableCreationName(Table table) throws SQLException {
		updatePst.setString(1, "Creación de "+table.getSpanishName());
		updatePst.setString(2, table.getName()+"CreationTitle");
		updatePst.execute();
	}

	private void updateDefaultSpanishTableModificationName(Table table) throws SQLException {
		updatePst.setString(1, "Modificación de "+table.getSpanishName());
		updatePst.setString(2, table.getName()+"ModificationTitle");
		updatePst.execute();
	}

	private void updateDefaultSpanishTableInfoName(Table table) throws SQLException {
		updatePst.setString(1, table.getSpanishName());
		updatePst.setString(2, table.getName()+"Info");
		updatePst.execute();
	}

	private void updateDefaultSpanishTableName(Table table) throws SQLException {
		updatePst.setString(1, table.getSpanishName());
		updatePst.setString(2, table.getName());
		updatePst.execute();
	}

	private void updateDefaultSpanishTableFields(Table table) throws SQLException {
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			if (field.getSpanishName()!=null){
				updatePst.setString(1, field.getSpanishName());
				updatePst.setString(2, table.getName()+CodeUtil.toCamelCase(field.getName()));
				updatePst.execute();
			}
		}
	}
}
