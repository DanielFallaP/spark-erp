package co.com.cybersoft.generator.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import co.com.cybersoft.generator.code.model.Cybersystems;
import co.com.cybersoft.generator.code.model.Field;
import co.com.cybersoft.generator.code.model.Table;
import co.com.cybersoft.generator.code.util.CodeUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class DataPopulation implements DBConstants{
	
	private final DB mongoDB;
	
	private final Cybersystems cybersystems;
	
	public static void main(String[] args) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Cybersystems cybersystems=mapper.readValue(new InputStreamReader(new FileInputStream("Cybertables.json"), "UTF8"), Cybersystems.class);

			MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost",27017)));
			DB db = mongoClient.getDB(mongoDBName);
			DataPopulation dataPopulation = new DataPopulation(db, cybersystems);
			dataPopulation.populate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public DataPopulation(DB mongoDB, Cybersystems cybersystems){
		this.mongoDB=mongoDB;
		this.cybersystems=cybersystems;
	}
	
	public void populate() throws Exception{
		FileReader fileReader = new FileReader(dataTablePath);
		BufferedReader reader = new BufferedReader(fileReader);
		
		BufferedWriter bufferedWriter = CodeUtil.initializeErrorFileWriting("insertionErrors.txt");
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
					bufferedWriter = CodeUtil.writeErrorLine(errorLine, bufferedWriter);
					line=reader.readLine();
					continue;
				}
			}

		bufferedWriter.close();
		reader.close();
	}
	
	private void insertRecord(Table table, String[] data){
			List<Field> fields = table.getFields();
			DBCollection collection = mongoDB.getCollection(table.getName());
			BasicDBObject doc=new BasicDBObject();
			for (int i = 0; i < fields.size(); i++) {
				Field field=fields.get(i);
				appendValue(doc, field, data[i+1]);
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
	
	private void appendValue(BasicDBObject doc, Field field, String stringValue){
			if (field.getType()!=null && !stringValue.equals("")){
				if (field.getType().equals(Cybersystems.doubleType))
					doc.append(field.getName(), Double.parseDouble(stringValue));
				else if (field.getType().equals(Cybersystems.booleanType))
					doc.append(field.getName(), Boolean.parseBoolean(stringValue));
				else if (field.getType().equals(Cybersystems.dateType))
					doc.append(field.getName(), stringValue);
				else if (field.getType().equals(Cybersystems.integerType))
					doc.append(field.getName(), Integer.parseInt(stringValue));
				else if (field.getType().equals(Cybersystems.longType))
					doc.append(field.getName(), Long.parseLong(stringValue));
				else
					doc.append(field.getName(), stringValue);
			}
			else{
				if (field.getType()!=null && field.getType().equals(Cybersystems.stringType))
					doc.append(field.getName(), stringValue);
				if (field.getRefType()!=null)
					doc.append(field.getName(), stringValue);
			}
			
	}
					
}
