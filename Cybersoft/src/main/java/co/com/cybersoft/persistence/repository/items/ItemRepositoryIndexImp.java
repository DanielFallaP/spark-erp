package co.com.cybersoft.persistence.repository.items;

import co.com.cybersoft.persistence.domain.Item;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class ItemRepositoryIndexImp {

	
	public <S extends Item> S save(S arg0) throws Exception{
		MongoClient mongoClient;
			mongoClient = new MongoClient("localhost" );
			DB db = mongoClient.getDB("cybersoft");
			DBCollection collection = db.getCollection("items");
			
			BasicDBObject basicDBObject = new BasicDBObject("code",arg0.getCode());
			collection.insert(basicDBObject);
			
			
		
		return arg0;
	}

}
