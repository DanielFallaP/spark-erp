package co.com.cybersoft.generator.code;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.core.CoreGenerator;
import co.com.cybersoft.generator.code.events.EventGenerator;
import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.persistence.PersistenceGenerator;
import co.com.cybersoft.generator.code.views.ViewGenerator;
import co.com.cybersoft.generator.code.web.WebGenerator;


public class CodeGenerator {
	
	private ObjectMapper mapper=new ObjectMapper();
	
	public void generate() throws JsonParseException, JsonMappingException, IOException{
		Cybersoft cybersoft = mapper.readValue(new File("Cybersoft.json"), Cybersoft.class);
		System.out.println("Finish reading file");
		new WebGenerator().generate(cybersoft);
		new CoreGenerator().generate(cybersoft);
		new PersistenceGenerator().generate(cybersoft);
		new ViewGenerator().generate(cybersoft);
		new EventGenerator().generate(cybersoft);
	}
}
