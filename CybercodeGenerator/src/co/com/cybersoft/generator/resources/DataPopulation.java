package co.com.cybersoft.generator.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cybertables;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class DataPopulation implements DBConstants{
	
	private final DB mongoDB;
	
	private final Cybertables cybersystems;
	
	public static void main(String[] args) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Cybertables cybersystems=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybertables.class);

			MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost",27017)));
			DB db = mongoClient.getDB(mongoDBName);
			DataPopulation dataPopulation = new DataPopulation(db, cybersystems);
			dataPopulation.populate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public DataPopulation(DB mongoDB, Cybertables cybersystems){
		this.mongoDB=mongoDB;
		this.cybersystems=cybersystems;
	}
	
	public void populate() throws Exception{
		FileReader fileReader = new FileReader(dataTablePath);
		BufferedReader reader = new BufferedReader(fileReader);
		
		BufferedWriter bufferedWriter = CodeUtils.initializeErrorFileWriting("insertionErrors.txt");
		String line = reader.readLine();
			while(line!=null){
				try {
					String[] data = line.split(dataFileSeparator);
					if (data.length!=0 && !data[0].equals("")){
						System.out.println("=============Inserting data in: "+data[0]);
						insertRecord(getTableLine(data), data);
						line=reader.readLine();
					}
					else{
						line=reader.readLine();
					}
				} catch (Exception e) {
					String errorLine=line+"   "+e.getMessage();
					bufferedWriter = CodeUtils.writeErrorLine(errorLine, bufferedWriter);
					line=reader.readLine();
					continue;
				}
			}

		bufferedWriter.close();
		reader.close();
	}
	
	private void insertRecord(Table table, String[] data) throws ParseException{
			List<Field> fields = table.getFields();
			DBCollection collection = mongoDB.getCollection(table.getName());
			BasicDBObject doc=new BasicDBObject();
			int i=0;
			for (Field field:fields) {
				if (!field.getCompoundReference()){
					appendValue(doc, field, data[i+1],mongoDB);
					i++;
				}
				else{
					List<Field> compoundKey = CodeUtils.getCompoundKey(cybersystems, field.getRefType());
					for (Field compoundField : compoundKey) {
						appendValue(doc, compoundField, data[i+1], mongoDB);
						i++;
					}
				}
			}
			
			doc.append("dateOfCreation", new Date());
			doc.append("createdBy", "batchProcess");
			doc.append("dateOfModification", new Date());
			doc.append("userName", "batchProcess");
			
			if (doc.size()!=0)
				collection.insert(doc);
	}
	
	private Table getTableLine(String[] data) throws Exception{
		String tableName = data[0];
		List<Table> tables = cybersystems.getTables();
		for (Table table : tables) {
			if (table.getName().equals(tableName)){
				return table;
			}
		}
		Exception exception = new Exception("Table not found, please review the data table file");
		throw exception;
	}
	
	private void appendValue(BasicDBObject doc, Field field, String stringValue, DB mongo) throws ParseException{
			if (field.getType()!=null && !stringValue.equals("")){
				if (field.getType().equals(Cybertables.doubleType))
					doc.append(field.getName(), Double.parseDouble(stringValue));
				else if (field.getType().equals(Cybertables.booleanType))
					doc.append(field.getName(), Boolean.parseBoolean(stringValue));
				else if (field.getType().equals(Cybertables.dateType)){
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					Date parse = dateFormat.parse(stringValue);
					doc.append(field.getName(), parse);
				}
				else if (field.getType().equals(Cybertables.integerType))
					doc.append(field.getName(), Integer.parseInt(stringValue));
				else if (field.getType().equals(Cybertables.longType))
					doc.append(field.getName(), Long.parseLong(stringValue));
				else
					doc.append(field.getName(), stringValue);
			}
			else{
				if (field.getType()!=null && field.getType().equals(Cybertables.stringType))
					doc.append(field.getName(), stringValue);
				if (field.getRefType()!=null)
					doc.append(field.getName(), stringValue);
			}
			
			if (!field.getEmbeddedFields().isEmpty()){
				
				BasicDBObject queryObject = new BasicDBObject();
				queryObject.put(field.getDisplayField(), stringValue);
				DBCollection collection = mongo.getCollection(field.getRefType());
				DBCursor cursor = collection.find(queryObject);
				HashMap<String, Object> embeddedMap = new HashMap<>();
				while (cursor.hasNext()){
					DBObject object = cursor.next();
					List<String> embeddedFields = field.getEmbeddedFields();
					for (String embeddedField : embeddedFields) {
						embeddedMap.put(embeddedField, object.get(embeddedField));
					}
				}
				
				if (!embeddedMap.values().isEmpty()){
					String fieldName=field.getRefType()+"Entity";
					doc.append(fieldName, embeddedMap);
				}
			}
	}
					
}
