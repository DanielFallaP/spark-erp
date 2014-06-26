package co.com.cybersoft.docs.persistence.services.startup;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.util.CyberUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class SequenceStartupServiceImpl implements SequenceStartupService{

	@Autowired
	MongoOperations mongo;
	
	@PostConstruct
	@Override
	public void startupSequences() throws Exception {
	
		checkAndStart("requisition_id", 1);
		
	}
	
	private void checkAndStart(String sequenceName, Integer startingNumber){
		DBCollection counters = mongo.getCollection(CyberUtils.countersCollection);
		BasicDBObject object = new BasicDBObject();
		
		object.append("_id", sequenceName);
		DBCursor find = counters.find(object);
		
		if (!find.hasNext()){
			object=new BasicDBObject();
			object.put("_id", sequenceName);
			object.put("seq", startingNumber);			
			counters.insert(object);
		}
	}

}
