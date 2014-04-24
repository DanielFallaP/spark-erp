package co.com.cybersoft.persistence.repository.operationType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.OperationType;


public class OperationTypeCustomRepoImpl implements OperationTypeCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<OperationType> findByStartingCodeNumber(String codePrefix) {
		List<OperationType> operationTypeList = new ArrayList<OperationType>();
		DBCollection operationTypeCollection = mongo.getCollection("operationType");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor operationTypeCursor = operationTypeCollection.find(dbObject).limit(10);
		while (operationTypeCursor.hasNext()){
			DBObject object = operationTypeCursor.next();
			String code=(String) object.get("code");
			OperationType operationType = new OperationType();
			operationType.setCode(code);
			operationTypeList.add(operationType);
		}
		return operationTypeList;
	}

	@Override
		public List<OperationType> findByContainingDescription(
				String descriptionSubstring) {
			List<OperationType> operationTypeList = new ArrayList<OperationType>();
			DBCollection operationTypeCollection = mongo.getCollection("operationType");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor operationTypeCursor = operationTypeCollection.find(dbObject).limit(10);
			while (operationTypeCursor.hasNext()){
				DBObject object = operationTypeCursor.next();
				String description=(String) object.get("description");
				OperationType operationType = new OperationType();
				operationType.setDescription(description);
				operationTypeList.add(operationType);
			}
			return operationTypeList;
		}

}