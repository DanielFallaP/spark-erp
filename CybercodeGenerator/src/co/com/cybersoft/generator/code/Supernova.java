package co.com.cybersoft.generator.code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cybermodules;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Module;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

/**
 * LET THERE BE CODE
 * @author Daniel Falla, Cybersystems
 *
 */
public class Supernova {

	public static ObjectMapper mapper=new ObjectMapper();

	
	public static void main(String[] args) {
		//Store all tables for referencing
		Cybermodules modules;
		List<Table> tables=new ArrayList<>();
		try {
			modules = mapper.readValue(new InputStreamReader(new FileInputStream("Cybermodules.json"), "UTF8"), Cybermodules.class);
			for(Module module:modules.getModules()){
				Cybertables moduleTables=mapper.readValue(new InputStreamReader(new FileInputStream(module.getFileName()+".json"), "UTF8"), Cybertables.class);
				List<Table> tables2 = moduleTables.getTables();
				for (Table table : tables2) {
					table.setModule(module.getName());
				}
				tables.addAll(moduleTables.getTables());
				CodeUtils.allTables=tables;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		TableCodeGenerator tableCodeGenerator = new TableCodeGenerator();
		DocCodeGenerator docCodeGenerator = new DocCodeGenerator();
		ConfigCodeGenerator configCodeGenerator = new ConfigCodeGenerator();
		try {
			tableCodeGenerator.generate();
			configCodeGenerator.generate();
//			docCodeGenerator.generate();
			System.out.println("");
			System.out.println("");
			System.out.println("APPLICATION BUILT SUCCESSFULLY: "+new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
