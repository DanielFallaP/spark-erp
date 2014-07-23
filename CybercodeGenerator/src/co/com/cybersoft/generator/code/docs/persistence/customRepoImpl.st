package co.com.cybersoft.docs.persistence.repository.$docName$;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.util.CyberUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $upperDocName$CustomRepoImpl implements $upperDocName$CustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private $upperDocName$Repository repo;
	
	@Autowired
	private DB mongoDB;
	
	
	@Override
	public $upperDocName$ save($upperDocName$ $docName$) throws Exception{
		String id=$docName$.getId();
		$upperDocName$ $docName$2 = repo.save($docName$);
		if (id==null){
			DBCollection $docName$Collection = mongoDB.getCollection("$docName$");
			
			Double numericID = (Double) mongoDB.eval(CyberUtils.getNextSequence, "$docName$_id");
			$docName$.setNumericId(numericID.longValue());
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("\$set", new BasicDBObject("_numericId", numericID.longValue()).append("stringId", new Long(numericID.longValue()).toString()));
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", new ObjectId($docName$2.getId()) );
			
			$docName$Collection.update(searchQuery, newDocument);
		}
		
		return $docName$2;
	}

}