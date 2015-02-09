package co.com.cybersoft.docs.persistence.repository.$docName$;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import java.util.Collections;
import java.text.SimpleDateFormat;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.man.services.security.SessionAction;
import co.com.cybersoft.man.services.security.SessionLogger;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$FilterInfo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.util.List;


/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $upperDocName$CustomRepoImpl implements $upperDocName$CustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private $upperDocName$Repository repo;
	
	@Autowired
	private DB mongoDB;
	
	public void updateLog(String userName, String action, Long id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"$docName$", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}
	
	@Override
	public $upperDocName$ save($upperDocName$ $docName$) throws Exception{
		String id=$docName$.getId();
		$upperDocName$ $docName$2 = repo.save($docName$);
		if (id==null){
			DBCollection $docName$Collection = mongoDB.getCollection("$docName$");
			
			Double numericID = (Double) mongoDB.eval(CyberUtils.getNextSequence, "$docName$_id");
			$docName$.setNumericId(numericID.longValue());
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("\$set", new BasicDBObject("numericId", numericID.longValue()).append("stringId", new Long(numericID.longValue()).toString()));
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", new ObjectId($docName$2.getId()) );
			
			$docName$Collection.update(searchQuery, newDocument);
			
		}
		updateLog(SecurityContextHolder.getContext().getAuthentication().getName(), "save",$docName$2.getNumericId());
		
		return $docName$2;
	}
	
	@Override
	public List<$upperDocName$> findByContainingNumericId(String numericId) throws Exception{
		numericId=CyberUtils.escapePCRECharacters(numericId);
		BasicQuery query = new BasicQuery("{\"stringId\": {\$regex : '" + numericId + "',\$options:'i'}, \"active\":true }");
		query.limit($rowsPerSearch$);
		return mongo.find(query, $upperDocName$.class);
	}
	
	@Override
	public Page<$upperDocName$> findAll(Pageable pageable, $upperDocName$FilterInfo filter) throws Exception{
		List<$upperDocName$> list = findAll(buildCriteria(filter).with(pageable));
		return new PageImpl<$upperDocName$>(list, pageable, count(filter));
	}
	
	private Query buildCriteria($upperDocName$FilterInfo filter)throws Exception{
		Query filterQuery=new Query();
		$fieldCriteria$
		if (filter.getNumericId()!=null && !filter.getNumericId().equals(""))filterQuery.addCriteria(translateLongOperators(filter.getNumericId(),"numericId" ));
		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))filterQuery.addCriteria(translateDateOperators(filter.getDateOfCreation(),"dateOfCreation" ));
		if (filter.getDateOfModification()!=null && !filter.getDateOfModification().equals(""))filterQuery.addCriteria(translateDateOperators(filter.getDateOfModification(),"dateOfModification" ));
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))filterQuery.addCriteria(Criteria.where("userName").regex(translateWildcards(filter.getUserName())));
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))filterQuery.addCriteria(Criteria.where("createdBy").regex(translateWildcards(filter.getCreatedBy())));
		
		return filterQuery;
	}
	
	private List<$upperDocName$> findAll(Query query) {

		if (query == null) {
			return Collections.emptyList();
		}

		return mongo.find(query, $upperDocName$.class, "$docName$");
	}
	
	private long count($upperDocName$FilterInfo filter)throws Exception{
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