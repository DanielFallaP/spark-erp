package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.config.ConfigGenerator;
import co.com.cybersoft.generator.code.core.CoreGenerator;
import co.com.cybersoft.generator.code.events.EventGenerator;
import co.com.cybersoft.generator.code.model.Cybersystems;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.persistence.PersistenceGenerator;
import co.com.cybersoft.generator.code.views.ViewGenerator;
import co.com.cybersoft.generator.code.web.WebGenerator;


public class CodeGenerator {
	
	private ObjectMapper mapper=new ObjectMapper();
	
	public void generate() throws JsonParseException, JsonMappingException, IOException{
		Cybersystems cybersoft=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybersystems.class);
		System.out.println("Finished reading file");
		
		//Set singleton tables fields as not required
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (table.getSingletonTable()){
				List<Field> fields = table.getFields();
				for (Field field : fields) {
					field.setRequired(false);
				}
			}
				
		}
		
		if (!cybersoft.getTables().isEmpty()){
			new DirectoryCleaner(cybersoft).initialClean();
			new WebGenerator().generate(cybersoft);
			new CoreGenerator().generate(cybersoft);
			new PersistenceGenerator().generate(cybersoft);
			new ViewGenerator(cybersoft).generate();
			new EventGenerator().generate(cybersoft);
			new ConfigGenerator().generate(cybersoft);
			new DirectoryCleaner(cybersoft).clean();
		}
	}
}
