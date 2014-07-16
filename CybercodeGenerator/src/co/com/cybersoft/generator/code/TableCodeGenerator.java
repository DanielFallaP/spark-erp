package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Field;
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
		Cybertables cybersystems=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybertables.class);
		System.out.println("Finished reading tables file");
		
		//Set singleton tables fields as not required
		List<Table> tables = cybersystems.getTables();
		for (Table table : tables) {
			if (table.getSingletonTable()){
				List<Field> fields = table.getFields();
				for (Field field : fields) {
					field.setRequired(false);
				}
			}
				
		}
		
		if (!cybersystems.getTables().isEmpty()){
			new TableDirectoryCleaner(cybersystems).initialClean();
			new TableCoreGenerator(cybersystems).generate();
			new TableWebGenerator(cybersystems).generate();
			new TablePersistenceGenerator(cybersystems).generate();
			new TableViewGenerator(cybersystems).generate();
			new TableEventGenerator().generate(cybersystems);
			new TableDirectoryCleaner(cybersystems).clean();
		}
	}
}
