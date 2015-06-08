package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.config.ConfigGenerator;
import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybermodules;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Module;

public class ConfigCodeGenerator {
	
	private ObjectMapper mapper=new ObjectMapper();

	public void generate() throws JsonParseException, JsonMappingException, IOException{
		
		Cyberdocs cyberdocs = mapper.readValue(new InputStreamReader(new FileInputStream("Cyberdocs.json"), "UTF8"), Cyberdocs.class);
		
		Cybermodules modules=mapper.readValue(new InputStreamReader(new FileInputStream("Cybermodules.json"), "UTF8"), Cybermodules.class);
		new ConfigGenerator(modules,cyberdocs).generate();

	}
}
