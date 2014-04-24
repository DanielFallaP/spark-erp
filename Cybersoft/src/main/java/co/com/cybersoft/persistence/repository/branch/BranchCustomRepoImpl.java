package co.com.cybersoft.persistence.repository.branch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Branch;


public class BranchCustomRepoImpl implements BranchCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Branch> findByStartingCodeNumber(String codePrefix) {
		List<Branch> branchList = new ArrayList<Branch>();
		DBCollection branchCollection = mongo.getCollection("branch");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor branchCursor = branchCollection.find(dbObject).limit(10);
		while (branchCursor.hasNext()){
			DBObject object = branchCursor.next();
			Integer code=(Integer) object.get("code");
			Branch branch = new Branch();
			branch.setCode(code);
			branchList.add(branch);
		}
		return branchList;
	}

	@Override
		public List<Branch> findByContainingDescription(
				String descriptionSubstring) {
			List<Branch> branchList = new ArrayList<Branch>();
			DBCollection branchCollection = mongo.getCollection("branch");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor branchCursor = branchCollection.find(dbObject).limit(10);
			while (branchCursor.hasNext()){
				DBObject object = branchCursor.next();
				String description=(String) object.get("description");
				Branch branch = new Branch();
				branch.setDescription(description);
				branchList.add(branch);
			}
			return branchList;
		}

}