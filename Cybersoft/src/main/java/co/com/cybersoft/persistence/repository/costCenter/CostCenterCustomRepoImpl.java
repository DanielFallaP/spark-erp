package co.com.cybersoft.persistence.repository.costCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.CostCenter;


public class CostCenterCustomRepoImpl implements CostCenterCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<CostCenter> findByStartingCodeNumber(String codePrefix) {
		List<CostCenter> costCenterList = new ArrayList<CostCenter>();
		DBCollection costCenterCollection = mongo.getCollection("costCenter");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor costCenterCursor = costCenterCollection.find(dbObject).limit(10);
		while (costCenterCursor.hasNext()){
			DBObject object = costCenterCursor.next();
			String code=(String) object.get("code");
			CostCenter costCenter = new CostCenter();
			costCenter.setCode(code);
			costCenterList.add(costCenter);
		}
		return costCenterList;
	}

	@Override public List<CostCenter> findByContainingDescription(String descriptionSubstring) {return null;}

}