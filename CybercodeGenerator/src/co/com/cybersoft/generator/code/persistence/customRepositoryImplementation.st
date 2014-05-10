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
	
	$byContainingFields$
	
	$findAllActive$

}
