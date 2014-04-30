package co.com.cybersoft.persistence.repository.$tableName$;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.$entityName$;
import co.com.cybersoft.util.CyberUtils;

public class $entityName$CustomRepoImpl implements $entityName$CustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<$entityName$> findByStartingCodeNumber(String codePrefix) {
		List<$entityName$> $tableName$List = new ArrayList<$entityName$>();
		DBCollection $tableName$Collection = mongo.getCollection("$tableName$");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor $tableName$Cursor = $tableName$Collection.find(dbObject).limit(10);
		while ($tableName$Cursor.hasNext()){
			DBObject object = $tableName$Cursor.next();
			$fieldType$ code=($fieldType$) object.get("code");
			$entityName$ $tableName$ = new $entityName$();
			$tableName$.setCode(code);
			$tableName$List.add($tableName$);
		}
		return $tableName$List;
	}

	$byContainingDescription$
	
	$findAllActive$

}
