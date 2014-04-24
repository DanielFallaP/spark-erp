package co.com.cybersoft.persistence.repository.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Contract;


public class ContractCustomRepoImpl implements ContractCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Contract> findByStartingCodeNumber(String codePrefix) {
		List<Contract> contractList = new ArrayList<Contract>();
		DBCollection contractCollection = mongo.getCollection("contract");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor contractCursor = contractCollection.find(dbObject).limit(10);
		while (contractCursor.hasNext()){
			DBObject object = contractCursor.next();
			String code=(String) object.get("code");
			Contract contract = new Contract();
			contract.setCode(code);
			contractList.add(contract);
		}
		return contractList;
	}

	@Override
		public List<Contract> findByContainingDescription(
				String descriptionSubstring) {
			List<Contract> contractList = new ArrayList<Contract>();
			DBCollection contractCollection = mongo.getCollection("contract");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor contractCursor = contractCollection.find(dbObject).limit(10);
			while (contractCursor.hasNext()){
				DBObject object = contractCursor.next();
				String description=(String) object.get("description");
				Contract contract = new Contract();
				contract.setDescription(description);
				contractList.add(contract);
			}
			return contractList;
		}

}