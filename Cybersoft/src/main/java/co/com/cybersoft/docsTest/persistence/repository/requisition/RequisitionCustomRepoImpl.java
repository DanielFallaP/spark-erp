package co.com.cybersoft.docsTest.persistence.repository.requisition;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import co.com.cybersoft.docsTest.persistence.domain.Requisition;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionCustomRepoImpl implements RequisitionCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private RequisitionRepository repo;
	
	@Autowired
	private DB mongoDB;
	
	@Override
	public List<Requisition> findAllActiveByPriority(EmbeddedField... fields) throws Exception {
		ArrayList<Requisition> requisitionList = new ArrayList<Requisition>();
		DBCollection requisitionCollection = mongo.getCollection("requisition");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("active", true);
		DBCursor requisitionCursor = requisitionCollection.find(dbObject);
		while (requisitionCursor.hasNext()){
			DBObject object = requisitionCursor.next();
			String priorityString=(String) object.get("priority");
			Requisition requisition = new Requisition();
			requisition.setPriority(priorityString);
			java.lang.reflect.Field[] declaredFields = requisition.getClass().getDeclaredFields();
			//FIXME improve my performance
			for (int i = 0; i < declaredFields.length; i++) {
				java.lang.reflect.Field declaredField = declaredFields[i];
				for (int j = 0; j < fields.length; j++) {
					String requisitionField=fields[j].getName();
					if (declaredField.getName().equals(requisitionField)){
						Method method = requisition.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase(requisitionField), fields[j].getType());
						method.invoke(requisition,object.get(requisitionField));
					}
				}
			}
			requisitionList.add(requisition);
		}
		return requisitionList;
	}@Override
	public List<Requisition> findAllActiveByRequestingDepartment(EmbeddedField... fields) throws Exception {
		ArrayList<Requisition> requisitionList = new ArrayList<Requisition>();
		DBCollection requisitionCollection = mongo.getCollection("requisition");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("active", true);
		DBCursor requisitionCursor = requisitionCollection.find(dbObject);
		while (requisitionCursor.hasNext()){
			DBObject object = requisitionCursor.next();
			String requestingDepartmentString=(String) object.get("requestingDepartment");
			Requisition requisition = new Requisition();
			requisition.setRequestingDepartment(requestingDepartmentString);
			java.lang.reflect.Field[] declaredFields = requisition.getClass().getDeclaredFields();
			//FIXME improve my performance
			for (int i = 0; i < declaredFields.length; i++) {
				java.lang.reflect.Field declaredField = declaredFields[i];
				for (int j = 0; j < fields.length; j++) {
					String requisitionField=fields[j].getName();
					if (declaredField.getName().equals(requisitionField)){
						Method method = requisition.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase(requisitionField), fields[j].getType());
						method.invoke(requisition,object.get(requisitionField));
					}
				}
			}
			requisitionList.add(requisition);
		}
		return requisitionList;
	}@Override
	public List<Requisition> findAllActiveByExpenseType(EmbeddedField... fields) throws Exception {
		ArrayList<Requisition> requisitionList = new ArrayList<Requisition>();
		DBCollection requisitionCollection = mongo.getCollection("requisition");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("active", true);
		DBCursor requisitionCursor = requisitionCollection.find(dbObject);
		while (requisitionCursor.hasNext()){
			DBObject object = requisitionCursor.next();
			String expenseTypeString=(String) object.get("expenseType");
			Requisition requisition = new Requisition();
			requisition.setExpenseType(expenseTypeString);
			java.lang.reflect.Field[] declaredFields = requisition.getClass().getDeclaredFields();
			//FIXME improve my performance
			for (int i = 0; i < declaredFields.length; i++) {
				java.lang.reflect.Field declaredField = declaredFields[i];
				for (int j = 0; j < fields.length; j++) {
					String requisitionField=fields[j].getName();
					if (declaredField.getName().equals(requisitionField)){
						Method method = requisition.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase(requisitionField), fields[j].getType());
						method.invoke(requisition,object.get(requisitionField));
					}
				}
			}
			requisitionList.add(requisition);
		}
		return requisitionList;
	}@Override
	public List<Requisition> findAllActiveByTransportationType(EmbeddedField... fields) throws Exception {
		ArrayList<Requisition> requisitionList = new ArrayList<Requisition>();
		DBCollection requisitionCollection = mongo.getCollection("requisition");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("active", true);
		DBCursor requisitionCursor = requisitionCollection.find(dbObject);
		while (requisitionCursor.hasNext()){
			DBObject object = requisitionCursor.next();
			String transportationTypeString=(String) object.get("transportationType");
			Requisition requisition = new Requisition();
			requisition.setTransportationType(transportationTypeString);
			java.lang.reflect.Field[] declaredFields = requisition.getClass().getDeclaredFields();
			//FIXME improve my performance
			for (int i = 0; i < declaredFields.length; i++) {
				java.lang.reflect.Field declaredField = declaredFields[i];
				for (int j = 0; j < fields.length; j++) {
					String requisitionField=fields[j].getName();
					if (declaredField.getName().equals(requisitionField)){
						Method method = requisition.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase(requisitionField), fields[j].getType());
						method.invoke(requisition,object.get(requisitionField));
					}
				}
			}
			requisitionList.add(requisition);
		}
		return requisitionList;
	}@Override
	public List<Requisition> findAllActiveByReceivingWarehouse(EmbeddedField... fields) throws Exception {
		ArrayList<Requisition> requisitionList = new ArrayList<Requisition>();
		DBCollection requisitionCollection = mongo.getCollection("requisition");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("active", true);
		DBCursor requisitionCursor = requisitionCollection.find(dbObject);
		while (requisitionCursor.hasNext()){
			DBObject object = requisitionCursor.next();
			String receivingWarehouseString=(String) object.get("receivingWarehouse");
			Requisition requisition = new Requisition();
			requisition.setReceivingWarehouse(receivingWarehouseString);
			java.lang.reflect.Field[] declaredFields = requisition.getClass().getDeclaredFields();
			//FIXME improve my performance
			for (int i = 0; i < declaredFields.length; i++) {
				java.lang.reflect.Field declaredField = declaredFields[i];
				for (int j = 0; j < fields.length; j++) {
					String requisitionField=fields[j].getName();
					if (declaredField.getName().equals(requisitionField)){
						Method method = requisition.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase(requisitionField), fields[j].getType());
						method.invoke(requisition,object.get(requisitionField));
					}
				}
			}
			requisitionList.add(requisition);
		}
		return requisitionList;
	}
	
	private String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
	
	@Override
	public Requisition save(Requisition requisition) throws Exception{
		String id=requisition.getId();
		Requisition requisition2 = repo.save(requisition);
		if (id==null){
			DBCollection requisitionCollection = mongoDB.getCollection("requisition");
			
			Double numericID = (Double) mongoDB.eval(CyberUtils.getNextSequence, "requisition_id");
			requisition.setNumericId(numericID.longValue());
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject("_numericId", numericID.longValue()));
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", new ObjectId(requisition2.getId()) );
			
			requisitionCollection.update(searchQuery, newDocument);
		}
		
		return requisition2;
	}

}