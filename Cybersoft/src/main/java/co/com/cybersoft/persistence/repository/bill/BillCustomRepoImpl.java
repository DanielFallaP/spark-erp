package co.com.cybersoft.persistence.repository.bill;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Bill;


public class BillCustomRepoImpl implements BillCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Bill> findByStartingCodeNumber(String codePrefix) {
		List<Bill> billList = new ArrayList<Bill>();
		DBCollection billCollection = mongo.getCollection("bill");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor billCursor = billCollection.find(dbObject).limit(10);
		while (billCursor.hasNext()){
			DBObject object = billCursor.next();
			String code=(String) object.get("code");
			Bill bill = new Bill();
			bill.setCode(code);
			billList.add(bill);
		}
		return billList;
	}

	@Override
		public List<Bill> findByContainingDescription(
				String descriptionSubstring) {
			List<Bill> billList = new ArrayList<Bill>();
			DBCollection billCollection = mongo.getCollection("bill");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor billCursor = billCollection.find(dbObject).limit(10);
			while (billCursor.hasNext()){
				DBObject object = billCursor.next();
				String description=(String) object.get("description");
				Bill bill = new Bill();
				bill.setDescription(description);
				billList.add(bill);
			}
			return billList;
		}

}