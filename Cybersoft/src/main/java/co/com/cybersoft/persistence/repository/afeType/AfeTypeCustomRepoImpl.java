package co.com.cybersoft.persistence.repository.afeType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.AfeType;


public class AfeTypeCustomRepoImpl implements AfeTypeCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<AfeType> findByStartingCodeNumber(String codePrefix) {
		List<AfeType> afeTypeList = new ArrayList<AfeType>();
		DBCollection afeTypeCollection = mongo.getCollection("afeType");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor afeTypeCursor = afeTypeCollection.find(dbObject).limit(10);
		while (afeTypeCursor.hasNext()){
			DBObject object = afeTypeCursor.next();
			String code=(String) object.get("code");
			AfeType afeType = new AfeType();
			afeType.setCode(code);
			afeTypeList.add(afeType);
		}
		return afeTypeList;
	}

	@Override
		public List<AfeType> findByContainingDescription(
				String descriptionSubstring) {
			List<AfeType> afeTypeList = new ArrayList<AfeType>();
			DBCollection afeTypeCollection = mongo.getCollection("afeType");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor afeTypeCursor = afeTypeCollection.find(dbObject).limit(10);
			while (afeTypeCursor.hasNext()){
				DBObject object = afeTypeCursor.next();
				String description=(String) object.get("description");
				AfeType afeType = new AfeType();
				afeType.setDescription(description);
				afeTypeList.add(afeType);
			}
			return afeTypeList;
		}

}