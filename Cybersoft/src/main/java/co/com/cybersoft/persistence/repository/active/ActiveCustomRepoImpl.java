package co.com.cybersoft.persistence.repository.active;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Active;


public class ActiveCustomRepoImpl implements ActiveCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Active> findByStartingCodeNumber(String codePrefix) {
		List<Active> activeList = new ArrayList<Active>();
		DBCollection activeCollection = mongo.getCollection("active");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor activeCursor = activeCollection.find(dbObject).limit(10);
		while (activeCursor.hasNext()){
			DBObject object = activeCursor.next();
			String code=(String) object.get("code");
			Active active = new Active();
			active.setCode(code);
			activeList.add(active);
		}
		return activeList;
	}

	@Override public List<Active> findByContainingDescription(String descriptionSubstring) {return null;}

}