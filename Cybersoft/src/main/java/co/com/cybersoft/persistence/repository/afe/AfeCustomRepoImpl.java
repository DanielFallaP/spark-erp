package co.com.cybersoft.persistence.repository.afe;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Afe;


public class AfeCustomRepoImpl implements AfeCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Afe> findByStartingCodeNumber(String codePrefix) {
		List<Afe> afeList = new ArrayList<Afe>();
		DBCollection afeCollection = mongo.getCollection("afe");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor afeCursor = afeCollection.find(dbObject).limit(10);
		while (afeCursor.hasNext()){
			DBObject object = afeCursor.next();
			String code=(String) object.get("code");
			Afe afe = new Afe();
			afe.setCode(code);
			afeList.add(afe);
		}
		return afeList;
	}

	@Override
		public List<Afe> findByContainingDescription(
				String descriptionSubstring) {
			List<Afe> afeList = new ArrayList<Afe>();
			DBCollection afeCollection = mongo.getCollection("afe");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor afeCursor = afeCollection.find(dbObject).limit(10);
			while (afeCursor.hasNext()){
				DBObject object = afeCursor.next();
				String description=(String) object.get("description");
				Afe afe = new Afe();
				afe.setDescription(description);
				afeList.add(afe);
			}
			return afeList;
		}

}