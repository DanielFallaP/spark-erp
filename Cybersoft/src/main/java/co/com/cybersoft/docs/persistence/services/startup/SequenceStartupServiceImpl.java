package co.com.cybersoft.docs.persistence.services.startup;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class SequenceStartupServiceImpl implements SequenceStartupService{

	@Autowired
	MongoOperations mongo;
	
	@PostConstruct
	@Override
	public void startupSequences() throws Exception {
		DBCollection counters = mongo.getCollection("counters");

		BasicDBObject object = new BasicDBObject();
		
		object.append("_id", "requisition_id");
		DBCursor find = counters.find(object);
		
		if (!find.hasNext()){
			object=new BasicDBObject();
			object.put("_id", "requisition_id");
			object.put("seq", 0);
			
			counters.insert(object);
			
			
		}
		
	}

}
