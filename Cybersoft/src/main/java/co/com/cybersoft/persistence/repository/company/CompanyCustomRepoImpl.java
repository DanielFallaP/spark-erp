package co.com.cybersoft.persistence.repository.company;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.Company;


public class CompanyCustomRepoImpl implements CompanyCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<Company> findByStartingCodeNumber(String codePrefix) {
		List<Company> companyList = new ArrayList<Company>();
		DBCollection companyCollection = mongo.getCollection("company");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor companyCursor = companyCollection.find(dbObject).limit(10);
		while (companyCursor.hasNext()){
			DBObject object = companyCursor.next();
			Integer code=(Integer) object.get("code");
			Company company = new Company();
			company.setCode(code);
			companyList.add(company);
		}
		return companyList;
	}

	@Override
		public List<Company> findByContainingDescription(
				String descriptionSubstring) {
			List<Company> companyList = new ArrayList<Company>();
			DBCollection companyCollection = mongo.getCollection("company");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor companyCursor = companyCollection.find(dbObject).limit(10);
			while (companyCursor.hasNext()){
				DBObject object = companyCursor.next();
				String description=(String) object.get("description");
				Company company = new Company();
				company.setDescription(description);
				companyList.add(company);
			}
			return companyList;
		}

}