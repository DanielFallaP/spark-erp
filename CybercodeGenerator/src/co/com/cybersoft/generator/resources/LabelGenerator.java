package co.com.cybersoft.generator.resources;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class LabelGenerator {

	public static final String viewsAbsolutePath="C:\\Users\\daniel\\git\\co.com.cybersoft\\Cybersoft\\src\\main\\webapp\\WEB-INF\\views";
	
	public final PreparedStatement pst;
	
	private final Cybersoft cybersoft;
	
	private final DB mongoDB;
	
	public static final String labelPrefix="label";
	
	public static final String mongoDBNAme="cybersoft";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://cybersoft.c6g4nh1b2apj.us-east-1.rds.amazonaws.com/cybersoft?"
					+ "user=cybersoft&password=petronube");
			PreparedStatement pst = con.prepareStatement("insert into cybersoft.dictionary (message) values (?)");
			
			//Mongo setup. For replica set configurations, it is necessary to supply
			//the seed members to auto-discover the primary instance
			MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost",27017)));
			DB db = mongoClient.getDB(mongoDBNAme);
			
			ObjectMapper mapper = new ObjectMapper();
			Cybersoft cybersoft = mapper.readValue(new File("Cybertables.json"), Cybersoft.class);
			
			File rootDirectory = new File(viewsAbsolutePath);
			
			LabelGenerator labelGenerator = new LabelGenerator(cybersoft,pst,db);

			//Inserts all labels found in the views directory
			labelGenerator.insertLabels(rootDirectory);
			
			//Inserts all labels found in label tables
			labelGenerator.insertLabelTablesContent();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LabelGenerator(Cybersoft cybersoft, PreparedStatement pst, DB mongoDB){
		this.cybersoft=cybersoft;
		this.pst=pst;
		this.mongoDB=mongoDB;
	}
	
	/**
	 * Inserts all labels contained in the system's label tables
	 * @throws SQLException 
	 */
	private void insertLabelTablesContent() throws SQLException {
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			if (table.getLabelTable()){
				insertLabelTableContent(table);
			}
		}		
	}
	
	/**
	 * Inserts all labels contained in the specified table
	 * @param table
	 * @throws SQLException 
	 */
	private void insertLabelTableContent(Table table) throws SQLException {
		System.out.println("====================Inserting labels in label table: "+table.getName());
		DBCollection collection = mongoDB.getCollection(table.getName());
		DBCursor cursor = collection.find();
		String labelField = CodeUtil.getLabelField(table);
		while (cursor.hasNext()){
			DBObject object = cursor.next();
			String label = (String) object.get(labelField);
			if (label.startsWith(labelPrefix+".")){
				label=label.replace(labelPrefix+".", "");
			}
			ArrayList<String> messages = new ArrayList<String>();
			messages.add(label);
			insertMessages(messages);
		}
	}

	/**
	 * This method inserts all labels contained in all the html pages
	 * in the given view directory into the label database 
	 * for further generation of message resources
	 * @param directory
	 * @throws FileNotFoundException 
	 */
	public void insertLabels(File directory) throws Exception{
		File[] files = directory.listFiles();
		List<File> filesList = Arrays.asList(files);
		for (File file : filesList) {
			if (!file.isDirectory() && file.getName().endsWith(".html")){
				insertFileLabels(file);
			}
			else{
				insertLabels(file);
			}
		}
	}
	
	public void insertFileLabels(File htmlFile) throws Exception{
		System.out.println("====================Inserting labels in file: "+htmlFile.getName());
		insertMessages(getFileLabels(htmlFile));
	}
	
	public void insertMessages(List<String> messages){
		for (String message : messages) {
			try {
//				System.out.println(message);
				pst.setString(1, message);
				pst.execute();
			} catch (Exception e) {
				//Just continue with the next message if this one was previously inserted
				continue;
			}
		}
	}
	
	public List<String> getFileLabels(File htmlFile) throws Exception{
		ArrayList<String> labelList = new ArrayList<String>();
		FileReader fileReader = new FileReader(htmlFile);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = reader.readLine();
		while (line!=null){
			labelList.addAll(getLineLabels(line));
			line=reader.readLine();
		}
		reader.close();
		return labelList;
	}
	
	public List<String> getLineLabels(String line){
		ArrayList<String> list = new ArrayList<String>();
		while (line.lastIndexOf("#{label.")!=-1){
			String substring = line.substring(line.lastIndexOf("#{label.")+8,line.lastIndexOf("#{label.")+8+getLabelLength(line));
			list.add(substring);
			line = line.substring(0,line.lastIndexOf("#{label."));
		}
		return list;
	}
	
	public int getLabelLength(String line){
		int indexOf = line.lastIndexOf("#{label.")+"#{label.".length()+1;
		int length=1;
		for (; indexOf < line.length(); indexOf++) {
			if (line.charAt(indexOf)!='}'){
				length++;
			}
			else{
				break;
			}
		}
		return length;
	}
}
