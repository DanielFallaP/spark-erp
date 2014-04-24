package co.com.cybersoft.persistence.repository.calculusType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.CalculusType;


public class CalculusTypeCustomRepoImpl implements CalculusTypeCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<CalculusType> findByStartingCodeNumber(String codePrefix) {
		List<CalculusType> calculusTypeList = new ArrayList<CalculusType>();
		DBCollection calculusTypeCollection = mongo.getCollection("calculusType");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor calculusTypeCursor = calculusTypeCollection.find(dbObject).limit(10);
		while (calculusTypeCursor.hasNext()){
			DBObject object = calculusTypeCursor.next();
			String code=(String) object.get("code");
			CalculusType calculusType = new CalculusType();
			calculusType.setCode(code);
			calculusTypeList.add(calculusType);
		}
		return calculusTypeList;
	}

	@Override
		public List<CalculusType> findByContainingDescription(
				String descriptionSubstring) {
			List<CalculusType> calculusTypeList = new ArrayList<CalculusType>();
			DBCollection calculusTypeCollection = mongo.getCollection("calculusType");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor calculusTypeCursor = calculusTypeCollection.find(dbObject).limit(10);
			while (calculusTypeCursor.hasNext()){
				DBObject object = calculusTypeCursor.next();
				String description=(String) object.get("description");
				CalculusType calculusType = new CalculusType();
				calculusType.setDescription(description);
				calculusTypeList.add(calculusType);
			}
			return calculusTypeList;
		}

}