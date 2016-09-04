package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cyberconstants;
import co.com.cybersoft.generator.code.model.Cybermodules;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Module;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.tables.TableDirectoryCleaner;
import co.com.cybersoft.generator.code.tables.core.TableCoreGenerator;
import co.com.cybersoft.generator.code.tables.events.TableEventGenerator;
import co.com.cybersoft.generator.code.tables.persistence.TablePersistenceGenerator;
import co.com.cybersoft.generator.code.tables.views.TableViewGenerator;
import co.com.cybersoft.generator.code.tables.web.TableWebGenerator;
import co.com.cybersoft.generator.code.util.CodeUtils;


public class TableCodeGenerator {
	
	private ObjectMapper mapper=new ObjectMapper();
	
	public void generate() throws JsonParseException, JsonMappingException, IOException{
		Cybermodules modules=mapper.readValue(new InputStreamReader(new FileInputStream("Cybermodules.json"), "UTF8"), Cybermodules.class);
		for(Module module:modules.getModules()){
			Cybertables moduleTables=mapper.readValue(new InputStreamReader(new FileInputStream(module.getFileName()+".json"), "UTF8"), Cybertables.class);
			moduleTables.setModuleName(module.getName());
			
			System.out.println("Finished reading tables file for "+moduleTables.getModuleName());
			
			//Set singleton tables fields as not required
			List<Table> tables = moduleTables.getTables();
			for (Table table : tables) {
				List<Field> fields = table.getFields();
				if (table.getSingletonTable()){
					for (Field field : fields) {
						field.setRequired(false);
					}
				}
				
				//Change fields which have Java reserved words as names
				for (Field field : fields) {
					if (field.getName().toLowerCase().equals("class"))
						field.setName("classis");
				}

				List<Field> permissionFields = new ArrayList<Field>();
				if (table.getName().equals(CodeUtils.usersTableName)){
					for (Module module2:modules.getModules()){
						Cybertables moduleTables2=mapper.readValue(new InputStreamReader(new FileInputStream(module2.getFileName()+".json"), "UTF8"), Cybertables.class);
						moduleTables2.setModuleName(module2.getName());
						
						List<Table> tables2 = moduleTables2.getTables();
						for (Table table2 : tables2) {
							if (!table2.getLabelTable() && !table2.getSingletonTable()){
								Field createField = new Field();
								createField.setName(table2.getName()+"Create");
								createField.setFieldGroup(CodeUtils.toCamelCase(module2.getName()));
								createField.setType(Cyberconstants.booleanType);
								createField.setTrans(true);
								createField.setDisplayable(false);
								if (table2.getName().equals(CodeUtils.usersTableName))
									createField.setDefaultValue("true");
								
								Field readField = new Field();
								readField.setName(table2.getName()+"Read");
								readField.setFieldGroup(CodeUtils.toCamelCase(module2.getName()));
								readField.setType(Cyberconstants.booleanType);
								readField.setTrans(true);
								readField.setDisplayable(false);
								if (table2.getName().equals(CodeUtils.usersTableName))
									readField.setDefaultValue("true");
								
								Field updateField = new Field();
								updateField.setName(table2.getName()+"Update");
								updateField.setFieldGroup(CodeUtils.toCamelCase(module2.getName()));
								updateField.setType(Cyberconstants.booleanType);
								updateField.setTrans(true);
								updateField.setDisplayable(false);
								if (table2.getName().equals(CodeUtils.usersTableName))
									updateField.setDefaultValue("true");
								
								Field exportField = new Field();
								exportField.setName(table2.getName()+"Export");
								exportField.setFieldGroup(CodeUtils.toCamelCase(module2.getName()));
								exportField.setType(Cyberconstants.booleanType);
								exportField.setTrans(true);
								exportField.setDisplayable(false);
								if (table2.getName().equals(CodeUtils.usersTableName))
									exportField.setDefaultValue("true");
								
								permissionFields.add(createField);
								permissionFields.add(readField);
								permissionFields.add(updateField);
								permissionFields.add(exportField);
								
							}
						}
					}
				}
				table.getFields().addAll(permissionFields);
			}
			
			if (!moduleTables.getTables().isEmpty()){
//				new TableDirectoryCleaner(moduleTables).initialClean();
				new TableCoreGenerator(moduleTables).generate();
				new TableWebGenerator(moduleTables).generate();
				new TablePersistenceGenerator(moduleTables).generate();
				new TableViewGenerator(moduleTables).generate();
				new TableEventGenerator().generate(moduleTables);
//				new TableDirectoryCleaner(moduleTables).clean();
			}
		}
	}
}
