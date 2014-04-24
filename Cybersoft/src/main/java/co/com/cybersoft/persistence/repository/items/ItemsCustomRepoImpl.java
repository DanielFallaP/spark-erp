package co.com.cybersoft.persistence.repository.items;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Items;


public class ItemsCustomRepoImpl implements ItemsCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Items> findByStartingCodeNumber(String codePrefix) {
		List<Items> itemsList = new ArrayList<Items>();
		DBCollection itemsCollection = mongo.getCollection("items");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor itemsCursor = itemsCollection.find(dbObject).limit(10);
		while (itemsCursor.hasNext()){
			DBObject object = itemsCursor.next();
			String code=(String) object.get("code");
			Items items = new Items();
			items.setCode(code);
			itemsList.add(items);
		}
		return itemsList;
	}

	@Override
		public List<Items> findByContainingDescription(
				String descriptionSubstring) {
			List<Items> itemsList = new ArrayList<Items>();
			DBCollection itemsCollection = mongo.getCollection("items");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor itemsCursor = itemsCollection.find(dbObject).limit(10);
			while (itemsCursor.hasNext()){
				DBObject object = itemsCursor.next();
				String description=(String) object.get("description");
				Items items = new Items();
				items.setDescription(description);
				itemsList.add(items);
			}
			return itemsList;
		}

}