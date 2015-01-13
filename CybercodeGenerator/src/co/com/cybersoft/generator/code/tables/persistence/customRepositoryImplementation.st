package co.com.cybersoft.tables.persistence.repository.$tableName$;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.tables.persistence.domain.$entityName$;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.tables.web.domain.$tableName$.$entityName$FilterInfo;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class $entityName$CustomRepoImpl implements $entityName$CustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	$startupSingletonRecord$
	
	$byContainingFields$
	
	$findAllActive$
	
	private String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}

	@Override
	public Page<$entityName$> findAll(Pageable pageable, $entityName$FilterInfo filter) throws Exception{
		List<$entityName$> list = findAll(buildCriteria(filter).with(pageable));
		return new PageImpl<$entityName$>(list, pageable, count(filter));
	}
	
	private Query buildCriteria($entityName$FilterInfo filter){
		Query filterQuery=new Query();
		$fieldCriteria$
		if (filter.getDateOfCreation()!=null)filterQuery.addCriteria(Criteria.where("dateOfCreation").is(filter.getDateOfCreation()));
		if (filter.getDateOfModification()!=null)filterQuery.addCriteria(Criteria.where("dateOfModification").is(filter.getDateOfModification()));
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))filterQuery.addCriteria(Criteria.where("userName").is(filter.getUserName()));
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))filterQuery.addCriteria(Criteria.where("createdBy").is(filter.getCreatedBy()));
		
		return filterQuery;
	}
	
	private List<$entityName$> findAll(Query query) {

		if (query == null) {
			return Collections.emptyList();
		}

		return mongo.find(query, $entityName$.class, "$tableName$");
	}
	
	private long count($entityName$FilterInfo filter){
		return findAll(buildCriteria(filter)).size();
	}
}

