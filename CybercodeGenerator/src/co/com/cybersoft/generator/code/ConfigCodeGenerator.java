package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.config.ConfigGenerator;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;

public class ConfigCodeGenerator {
	
	private ObjectMapper mapper=new ObjectMapper();

	public void generate() throws JsonParseException, JsonMappingException, IOException{
		
		Cyberdocs cyberdocs = mapper.readValue(new InputStreamReader(new FileInputStream("Cyberdocs.json"), "UTF8"), Cyberdocs.class);
		Cybertables cybertables = mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybertables.class);

		new ConfigGenerator(cybertables,cyberdocs).generate();
	}
}
