package co.com.cybersoft.docsTest.persistence.repository.requisition;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.docsTest.persistence.domain.Requisition;
import co.com.cybersoft.util.CyberUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class RequisitionCustomRepoImpl implements RequisitionCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private RequisitionRepository repo;
	
	@Autowired
	private DB mongoDB;
	
	
	@Override
	public Requisition save(Requisition requisition) throws Exception{
		String id=requisition.getId();
		Requisition requisition2 = repo.save(requisition);
		if (id==null){
			DBCollection requisitionCollection = mongoDB.getCollection("requisition");
			
			Double numericID = (Double) mongoDB.eval(CyberUtils.getNextSequence, "requisition_id");
			requisition.setNumericId(numericID.longValue());
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject("_numericId", numericID.longValue()));
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", new ObjectId(requisition2.getId()) );
			
			requisitionCollection.update(searchQuery, newDocument);
		}
		
		return requisition2;
	}

}