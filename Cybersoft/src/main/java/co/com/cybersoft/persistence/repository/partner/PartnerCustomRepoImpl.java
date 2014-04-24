package co.com.cybersoft.persistence.repository.partner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Partner;


public class PartnerCustomRepoImpl implements PartnerCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Partner> findByStartingCodeNumber(String codePrefix) {
		List<Partner> partnerList = new ArrayList<Partner>();
		DBCollection partnerCollection = mongo.getCollection("partner");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor partnerCursor = partnerCollection.find(dbObject).limit(10);
		while (partnerCursor.hasNext()){
			DBObject object = partnerCursor.next();
			Integer code=(Integer) object.get("code");
			Partner partner = new Partner();
			partner.setCode(code);
			partnerList.add(partner);
		}
		return partnerList;
	}

	@Override
		public List<Partner> findByContainingDescription(
				String descriptionSubstring) {
			List<Partner> partnerList = new ArrayList<Partner>();
			DBCollection partnerCollection = mongo.getCollection("partner");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor partnerCursor = partnerCollection.find(dbObject).limit(10);
			while (partnerCursor.hasNext()){
				DBObject object = partnerCursor.next();
				String description=(String) object.get("description");
				Partner partner = new Partner();
				partner.setDescription(description);
				partnerList.add(partner);
			}
			return partnerList;
		}

}