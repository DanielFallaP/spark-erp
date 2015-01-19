package co.com.cybersoft.tables.persistence.repository.$tableName$;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import java.util.Collections;
import java.text.SimpleDateFormat;

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
	
	private Query buildCriteria($entityName$FilterInfo filter)throws Exception{
		Query filterQuery=new Query();
		$fieldCriteria$
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))filterQuery.addCriteria(translateDateOperators(filter.getDateOfCreation(),"dateOfCreation" ));
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))filterQuery.addCriteria(translateDateOperators(filter.getDateOfModification(),"dateOfModification" ));
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))filterQuery.addCriteria(Criteria.where("userName").regex(translateWildcards(filter.getUserName())));
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))filterQuery.addCriteria(Criteria.where("createdBy").regex(translateWildcards(filter.getCreatedBy())));
		
		return filterQuery;
	}
	
	private List<$entityName$> findAll(Query query) {

		if (query == null) {
			return Collections.emptyList();
		}

		return mongo.find(query, $entityName$.class, "$tableName$");
	}
	
	private long count($entityName$FilterInfo filter)throws Exception{
		return findAll(buildCriteria(filter)).size();
	}
	
	private Pattern translateWildcards(String value){
		if (value.indexOf(CyberUtils.stringWilcard)>=0 && value.indexOf(CyberUtils.stringWilcard)!=value.lastIndexOf(CyberUtils.stringWilcard)){
			value=value.replaceAll(CyberUtils.stringWilcard, "");
			return Pattern.compile("^(.)*"+value+"(.)*\$",Pattern.CASE_INSENSITIVE);
		}
		else if(value.indexOf(CyberUtils.stringWilcard)==0){
			value=value.replaceAll(CyberUtils.stringWilcard, "");
			return Pattern.compile("^.*("+value+")\$",Pattern.CASE_INSENSITIVE);
		}
		else if(value.indexOf(CyberUtils.stringWilcard)==value.length()-1){
			value=value.replaceAll(CyberUtils.stringWilcard, "");
			return Pattern.compile("^("+value+").*\$",Pattern.CASE_INSENSITIVE);
		}
		else{
			return Pattern.compile("^"+value+"\$",Pattern.CASE_INSENSITIVE);
		}
	}
	
	private Criteria translateDoubleOperators(String value, String fieldName){
		String op;
		if ((op=CyberUtils.getOperator(value))!=null){
			if (op.equals(CyberUtils.greaterThanOp))
				return Criteria.where(fieldName).gt(Double.parseDouble(value.replace(op, "")));
			if (op.equals(CyberUtils.greaterEqualThanOp))
				return Criteria.where(fieldName).gte(Double.parseDouble(value.replace(op, "")));
			if (op.equals(CyberUtils.lessThanOp))
				return Criteria.where(fieldName).lt(Double.parseDouble(value.replace(op, "")));
			if (op.equals(CyberUtils.lessEqualThanOp))
				return Criteria.where(fieldName).lte(Double.parseDouble(value.replace(op, "")));
			if (op.equals(CyberUtils.notOp))
				return Criteria.where(fieldName).ne(Double.parseDouble(value.replace(op, "")));
		}
		return Criteria.where(fieldName).is(Double.parseDouble(value));
	}
	
	private Criteria translateIntegerOperators(String value, String fieldName){
		String op;
		if ((op=CyberUtils.getOperator(value))!=null){
			if (op.equals(CyberUtils.greaterThanOp))
				return Criteria.where(fieldName).gt(Integer.parseInt(value.replace(op, "")));
			if (op.equals(CyberUtils.greaterEqualThanOp))
				return Criteria.where(fieldName).gte(Integer.parseInt(value.replace(op, "")));
			if (op.equals(CyberUtils.lessThanOp))
				return Criteria.where(fieldName).lt(Integer.parseInt(value.replace(op, "")));
			if (op.equals(CyberUtils.lessEqualThanOp))
				return Criteria.where(fieldName).lte(Integer.parseInt(value.replace(op, "")));
			if (op.equals(CyberUtils.notOp))
				return Criteria.where(fieldName).ne(Integer.parseInt(value.replace(op, "")));
		}
		return Criteria.where(fieldName).is(Integer.parseInt(value));
	}
	
	private Criteria translateDateOperators(String value, String fieldName) throws Exception{
		String op;
		SimpleDateFormat dateParser=new SimpleDateFormat("dd/mm/yyyy");
		if ((op=CyberUtils.getOperator(value))!=null){
			if (op.equals(CyberUtils.greaterThanOp))
				return Criteria.where(fieldName).gt(dateParser.parse(value.replace(op, "")));
			if (op.equals(CyberUtils.greaterEqualThanOp))
				return Criteria.where(fieldName).gte(dateParser.parse(value.replace(op, "")));
			if (op.equals(CyberUtils.lessThanOp))
				return Criteria.where(fieldName).lt(dateParser.parse(value.replace(op, "")));
			if (op.equals(CyberUtils.lessEqualThanOp))
				return Criteria.where(fieldName).lte(dateParser.parse(value.replace(op, "")));
			if (op.equals(CyberUtils.notOp))
				return Criteria.where(fieldName).ne(dateParser.parse(value.replace(op, "")));
		}
		return Criteria.where(fieldName).is(dateParser.parse(value));
	}
	
	private Criteria translateLongOperators(String value, String fieldName){
		String op;
		if ((op=CyberUtils.getOperator(value))!=null){
			if (op.equals(CyberUtils.greaterThanOp))
				return Criteria.where(fieldName).gt(Long.parseLong(value.replace(op, "")));
			if (op.equals(CyberUtils.greaterEqualThanOp))
				return Criteria.where(fieldName).gte(Long.parseLong(value.replace(op, "")));
			if (op.equals(CyberUtils.lessThanOp))
				return Criteria.where(fieldName).lt(Long.parseLong(value.replace(op, "")));
			if (op.equals(CyberUtils.lessEqualThanOp))
				return Criteria.where(fieldName).lte(Long.parseLong(value.replace(op, "")));
			if (op.equals(CyberUtils.notOp))
				return Criteria.where(fieldName).ne(Long.parseLong(value.replace(op, "")));
		}
		return Criteria.where(fieldName).is(Long.parseLong(value));
	}
}

