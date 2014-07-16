package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.docs.events.DocEventGenerator;
import co.com.cybersoft.generator.code.docs.persistence.DocPersistenceGenerator;
import co.com.cybersoft.generator.code.docs.views.DocViewGenerator;
import co.com.cybersoft.generator.code.docs.web.DocWebGenerator;
import co.com.cybersoft.generator.code.model.Cyberdocs;

public class DocCodeGenerator {

	private ObjectMapper mapper=new ObjectMapper();
	
	public void generate() throws JsonParseException, JsonMappingException, IOException{
		
		Cyberdocs cyberdocs = mapper.readValue(new InputStreamReader(new FileInputStream("Cyberdocs.json"), "UTF8"), Cyberdocs.class);
		System.out.println("Finished reading documents file");
		
		if (!cyberdocs.getDocuments().isEmpty()){
			new DocWebGenerator(cyberdocs).generate();
			new DocViewGenerator(cyberdocs).generate();
			new DocPersistenceGenerator(cyberdocs).generate();
			new DocEventGenerator(cyberdocs).generate();
		}
	}
}
