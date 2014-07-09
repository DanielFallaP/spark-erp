package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.config.ConfigGenerator;
import co.com.cybersoft.generator.code.model.Spark;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.tables.core.CoreGenerator;
import co.com.cybersoft.generator.code.tables.events.EventGenerator;
import co.com.cybersoft.generator.code.tables.persistence.PersistenceGenerator;
import co.com.cybersoft.generator.code.tables.views.ViewGenerator;
import co.com.cybersoft.generator.code.tables.web.WebGenerator;


public class CodeGenerator {
	
	private ObjectMapper mapper=new ObjectMapper();
	
	public void generate() throws JsonParseException, JsonMappingException, IOException{
		Spark cybersystems=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Spark.class);
		System.out.println("Finished reading file");
		
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
			new DirectoryCleaner(cybersystems).initialClean();
			new CoreGenerator(cybersystems).generate();
			new WebGenerator(cybersystems).generate();
			new PersistenceGenerator(cybersystems).generate();
			new ViewGenerator(cybersystems).generate();
			new EventGenerator().generate(cybersystems);
			new ConfigGenerator().generate(cybersystems);
			new DirectoryCleaner(cybersystems).clean();
		}
	}
}
