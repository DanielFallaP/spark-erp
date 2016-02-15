package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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
