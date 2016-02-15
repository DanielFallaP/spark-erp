package co.com.cybersoft.$module$.docs.persistence.services.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.man.services.sequence.SequenceService;
import co.com.cybersoft.util.CyberUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class SequenceServiceImpl implements SequenceService{

	@Autowired
	MongoOperations mongo;
	
	public void startupSequences() throws Exception {
	
		$checkAndStart$
		
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
