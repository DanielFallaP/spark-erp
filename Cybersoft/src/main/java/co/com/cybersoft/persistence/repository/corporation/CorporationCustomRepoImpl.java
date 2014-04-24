package co.com.cybersoft.persistence.repository.corporation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Corporation;


public class CorporationCustomRepoImpl implements CorporationCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Corporation> findByStartingCodeNumber(String codePrefix) {
		List<Corporation> corporationList = new ArrayList<Corporation>();
		DBCollection corporationCollection = mongo.getCollection("corporation");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor corporationCursor = corporationCollection.find(dbObject).limit(10);
		while (corporationCursor.hasNext()){
			DBObject object = corporationCursor.next();
			Integer code=(Integer) object.get("code");
			Corporation corporation = new Corporation();
			corporation.setCode(code);
			corporationList.add(corporation);
		}
		return corporationList;
	}

	@Override
		public List<Corporation> findByContainingDescription(
				String descriptionSubstring) {
			List<Corporation> corporationList = new ArrayList<Corporation>();
			DBCollection corporationCollection = mongo.getCollection("corporation");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor corporationCursor = corporationCollection.find(dbObject).limit(10);
			while (corporationCursor.hasNext()){
				DBObject object = corporationCursor.next();
				String description=(String) object.get("description");
				Corporation corporation = new Corporation();
				corporation.setDescription(description);
				corporationList.add(corporation);
			}
			return corporationList;
		}

}