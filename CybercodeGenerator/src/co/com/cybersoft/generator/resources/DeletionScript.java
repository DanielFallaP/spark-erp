package co.com.cybersoft.generator.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Table;

public class DeletionScript implements DBConstants{

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			Cybertables cybersystems=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybertables.class);
			Cyberdocs cyberdocs=mapper.readValue(new InputStreamReader(new FileInputStream("Cyberdocs.json"), "UTF8"), Cyberdocs.class);
			
			String script="mongo "+mongoDBName+" --eval \"";

			List<Table> tables = cybersystems.getTables();
			for (Table table : tables) {
				script+="db."+table.getName()+".remove({});";
			}
			
			List<Document> documents = cyberdocs.getDocuments();
			for (Document document : documents) {
				script+="db."+document.getName()+".remove({});";
			}
			
			script+="\"";
			
			File file = new File("deletion.txt");
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bufferedWriter.write(script);
			bufferedWriter.close();
			System.out.println("DELETION SCRIPT CREATION COMPLETE");
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
