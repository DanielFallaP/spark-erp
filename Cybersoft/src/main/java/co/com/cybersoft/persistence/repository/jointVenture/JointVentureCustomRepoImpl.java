package co.com.cybersoft.persistence.repository.jointVenture;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.JointVenture;


public class JointVentureCustomRepoImpl implements JointVentureCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<JointVenture> findByStartingCodeNumber(String codePrefix) {
		List<JointVenture> jointVentureList = new ArrayList<JointVenture>();
		DBCollection jointVentureCollection = mongo.getCollection("jointVenture");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor jointVentureCursor = jointVentureCollection.find(dbObject).limit(10);
		while (jointVentureCursor.hasNext()){
			DBObject object = jointVentureCursor.next();
			String code=(String) object.get("code");
			JointVenture jointVenture = new JointVenture();
			jointVenture.setCode(code);
			jointVentureList.add(jointVenture);
		}
		return jointVentureList;
	}

	@Override public List<JointVenture> findByContainingDescription(String descriptionSubstring) {return null;}

}