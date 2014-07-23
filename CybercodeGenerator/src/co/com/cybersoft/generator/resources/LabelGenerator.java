package co.com.cybersoft.generator.resources;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cyberdocs;
import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Document;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class LabelGenerator implements DBConstants{

	public static final String viewsAbsolutePath="C:\\Users\\Raul\\git3\\Cybersoft\\Cybersoft\\src\\main\\webapp\\WEB-INF\\views";
	
	public final PreparedStatement insertionPst;
	
	public final PreparedStatement englishUpdatePst;
	
	public final PreparedStatement spanishUpdatePst;
	
	private final Cybertables cybertables;
	
	private final Cyberdocs cyberdocs;
	
	private final DB mongoDB;
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://cybersoft.c6g4nh1b2apj.us-east-1.rds.amazonaws.com/cybersoft?"
					+ "user=cybersoft&password=petronube");
			
			con.setAutoCommit(true);
			PreparedStatement insertionPst = con.prepareStatement("insert into cybersoft.dictionary (message) values (?)");
			
			PreparedStatement englishUpdatePst = con.prepareStatement("update cybersoft.dictionary set english=?, generated=1 where message=? and english is null");
			
			PreparedStatement spanishUpdatePst = con.prepareStatement("update cybersoft.dictionary set spanish=?, generated=1 where message=? and spanish is null");
			//Mongo setup. For replica set configurations, it is necessary to supply
			//the seed members to auto-discover the primary instance
			MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost",27017)));
			DB db = mongoClient.getDB(mongoDBName);
			
			ObjectMapper mapper = new ObjectMapper();
			Cybertables cybersoft=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybertables.class);
			
			Cyberdocs cyberdocs=mapper.readValue(new InputStreamReader(new FileInputStream("Cyberdocs.json"), "UTF8"), Cyberdocs.class);

			File rootDirectory = new File(viewsAbsolutePath);
			
			LabelGenerator labelGenerator = new LabelGenerator(cybersoft, cyberdocs, insertionPst, englishUpdatePst, spanishUpdatePst, db);

			//Inserts all labels found in the views directory
			labelGenerator.insertLabels(rootDirectory);
			
			//Inserts all labels found in label tables
			labelGenerator.insertLabelTablesContent();
			
			//Updates English default messages for empty messages in the DB
			labelGenerator.updateDefaultEnglishMessages();
			
			//Updates Spanish default messages for empty messages in the DB
			labelGenerator.updateDefaultSpanishMessages();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateDefaultSpanishMessages() throws SQLException {
		List<Table> tables = cybertables.getTables();
		SpanishDefaultGenerator generator = new SpanishDefaultGenerator(spanishUpdatePst);
		for (Table table : tables) {
			System.out.println("=============Inserting default Spanish messages for table: "+table.getName());
			generator.updateDefaultSpanishTableMessages(table);
		}
		
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			System.out.println("=============Inserting default Spanish messages for document: "+document.getName());
			generator.updateDefaultSpanishDocumentMessages(document);
		}
	}

	private void updateDefaultEnglishMessages() throws SQLException {
		List<Table> tables = cybertables.getTables();
		for (Table table : tables) {
			System.out.println("=============Inserting default English messages for table: "+table.getName());
			updateDefaultEnglishTableMessages(table);
		}
		
		List<Document> documents = cyberdocs.getDocuments();
		for (Document document : documents) {
			System.out.println("=============Inserting default English messages for document: "+document.getName());
			updateDefaultEnglishDocumentMessages(document);
		}
	}

	
	private void updateDefaultEnglishDocumentMessages(Document table) throws SQLException {
		updateDefaultEnglishFields(table);
		updateDefaultEnglishDocumentName(table);
		updateDefaultEnglishInfoName(table);
		updateDefaultEnglishModificationName(table);
		updateDefaultEnglishCreationName(table);
	}

	private void updateDefaultEnglishCreationName(Document table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName())+" Creation");
		englishUpdatePst.setString(2, table.getName()+"CreationTitle");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishModificationName(Document table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName())+" Modification");
		englishUpdatePst.setString(2, table.getName()+"ModificationTitle");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishInfoName(Document table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName()));
		englishUpdatePst.setString(2, table.getName()+"Info");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishDocumentName(Document table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName()));
		englishUpdatePst.setString(2, table.getName());
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishFields(Document table) throws SQLException {
		List<Field> fields = table.getAllFields();
		for (Field field : fields) {
			englishUpdatePst.setString(1, CodeUtil.getDefaultName(field.getName()));
			englishUpdatePst.setString(2, table.getName()+CodeUtil.toCamelCase(field.getName()));
			englishUpdatePst.execute();
		}
	}

	private void updateDefaultEnglishTableMessages(Table table) throws SQLException {
		updateDefaultEnglishTableFields(table);
		updateDefaultEnglishTableName(table);
		updateDefaultEnglishTableInfoName(table);
		updateDefaultEnglishTableModificationName(table);
		updateDefaultEnglishTableCreationName(table);
		updateDefaultEnglishTableConfigurationName(table);
	}

	private void updateDefaultEnglishTableConfigurationName(Table table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName()));
		englishUpdatePst.setString(2, table.getName()+"Configuration");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishTableCreationName(Table table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName())+" Creation");
		englishUpdatePst.setString(2, table.getName()+"CreationTitle");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishTableModificationName(Table table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName())+" Modification");
		englishUpdatePst.setString(2, table.getName()+"ModificationTitle");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishTableInfoName(Table table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName()));
		englishUpdatePst.setString(2, table.getName()+"Info");
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishTableName(Table table) throws SQLException {
		englishUpdatePst.setString(1, CodeUtil.getDefaultName(table.getName()));
		englishUpdatePst.setString(2, table.getName());
		englishUpdatePst.execute();
	}

	private void updateDefaultEnglishTableFields(Table table) throws SQLException {
		List<Field> fields = table.getFields();
		for (Field field : fields) {
			englishUpdatePst.setString(1, CodeUtil.getDefaultName(field.getName()));
			englishUpdatePst.setString(2, table.getName()+CodeUtil.toCamelCase(field.getName()));
			englishUpdatePst.execute();
		}
	}

	public LabelGenerator(Cybertables cybersoft, Cyberdocs cyberdocs, PreparedStatement insertionPst, PreparedStatement updatePst, PreparedStatement spanishUpdatePst, DB mongoDB){
		this.cybertables=cybersoft;
		this.cyberdocs=cyberdocs;
		this.insertionPst=insertionPst;
		this.englishUpdatePst=updatePst;
		this.spanishUpdatePst=spanishUpdatePst;
		this.mongoDB=mongoDB;
	}
	
	/**
	 * Inserts all labels contained in the system's label tables
	 * @throws SQLException 
	 */
	private void insertLabelTablesContent() throws SQLException {
		List<Table> tables = cybertables.getTables();
		for (Table table : tables) {
			if (table.getLabelTable()){
				insertFromLabelTableContent(table);
			}
		}		
	}
	
	/**
	 * Inserts all labels contained in the specified label table
	 * @param table
	 * @throws SQLException 
	 */
	private void insertFromLabelTableContent(Table table) throws SQLException {
		System.out.println("====================Inserting labels in label table: "+table.getName());
		DBCollection collection = mongoDB.getCollection(table.getName());
		DBCursor cursor = collection.find();
		String labelField = CodeUtil.getLabelField(table);
		while (cursor.hasNext()){
			DBObject object = cursor.next();
			String label = (String) object.get(labelField);
			if (label!=null && label.startsWith(labelPrefix+".")){
				label=label.replace(labelPrefix+".", "");
			}
			ArrayList<String> messages = new ArrayList<String>();
			messages.add(label);
			insertMessages(messages);
		}
	}

	/**
	 * This method inserts all labels contained in all the html pages
	 * in the Cybersystems's source view directory into the label database 
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
				insertionPst.setString(1, message);
				insertionPst.execute();
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
