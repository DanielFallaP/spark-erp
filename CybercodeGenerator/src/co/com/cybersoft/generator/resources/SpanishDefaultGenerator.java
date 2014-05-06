package co.com.cybersoft.generator.resources;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class SpanishDefaultGenerator {
	private final PreparedStatement updatePst;
	public SpanishDefaultGenerator(final PreparedStatement pst){
		this.updatePst=pst;
	}
	
	public void updateDefaultSpanishMessages(Table table) throws SQLException {
		updateDefaultEnglishFields(table);
		updateDefaultEnglishTableName(table);
		updateDefaultEnglishInfoName(table);
		updateDefaultEnglishModificationName(table);
		updateDefaultEnglishCreationName(table);
		updateDefaultEnglishConfigurationName(table);
		
	}

	private void updateDefaultEnglishConfigurationName(Table table) throws SQLException {
		updatePst.setString(1, table.getSpanishName());
		updatePst.setString(2, table.getName()+"Configuration");
		updatePst.execute();
		
	}

	private void updateDefaultEnglishCreationName(Table table) throws SQLException {
		updatePst.setString(1, "Creación de "+table.getSpanishName());
		updatePst.setString(2, table.getName()+"CreationTitle");
		updatePst.execute();
	}

	private void updateDefaultEnglishModificationName(Table table) throws SQLException {
		updatePst.setString(1, "Modificación de "+table.getSpanishName());
		updatePst.setString(2, table.getName()+"ModificationTitle");
		updatePst.execute();
	}

	private void updateDefaultEnglishInfoName(Table table) throws SQLException {
		updatePst.setString(1, table.getSpanishName());
		updatePst.setString(2, table.getName()+"Info");
		updatePst.execute();
	}

	private void updateDefaultEnglishTableName(Table table) throws SQLException {
		updatePst.setString(1, table.getSpanishName());
		updatePst.setString(2, table.getName());
		updatePst.execute();
	}

	private void updateDefaultEnglishFields(Table table) throws SQLException {
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
