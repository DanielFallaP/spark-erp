package co.com.cybersoft.persistence.repository.wareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.WareHouse;


public class WareHouseCustomRepoImpl implements WareHouseCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<WareHouse> findByStartingCodeNumber(String codePrefix) {
		List<WareHouse> wareHouseList = new ArrayList<WareHouse>();
		DBCollection wareHouseCollection = mongo.getCollection("wareHouse");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor wareHouseCursor = wareHouseCollection.find(dbObject).limit(10);
		while (wareHouseCursor.hasNext()){
			DBObject object = wareHouseCursor.next();
			String code=(String) object.get("code");
			WareHouse wareHouse = new WareHouse();
			wareHouse.setCode(code);
			wareHouseList.add(wareHouse);
		}
		return wareHouseList;
	}

	@Override
		public List<WareHouse> findByContainingDescription(
				String descriptionSubstring) {
			List<WareHouse> wareHouseList = new ArrayList<WareHouse>();
			DBCollection wareHouseCollection = mongo.getCollection("wareHouse");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor wareHouseCursor = wareHouseCollection.find(dbObject).limit(10);
			while (wareHouseCursor.hasNext()){
				DBObject object = wareHouseCursor.next();
				String description=(String) object.get("description");
				WareHouse wareHouse = new WareHouse();
				wareHouse.setDescription(description);
				wareHouseList.add(wareHouse);
			}
			return wareHouseList;
		}

}