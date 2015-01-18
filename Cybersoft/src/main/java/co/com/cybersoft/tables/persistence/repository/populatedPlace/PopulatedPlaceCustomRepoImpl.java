package co.com.cybersoft.tables.persistence.repository.populatedPlace;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import co.com.cybersoft.tables.persistence.domain.PopulatedPlace;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.tables.web.domain.populatedPlace.PopulatedPlaceFilterInfo;


/**
 * 
 * @author Cybersystems 2015. All rights reserved.
 *
 */
public class PopulatedPlaceCustomRepoImpl implements PopulatedPlaceCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	
	@Override
		public List<PopulatedPlace> findByContainingCountry(
				String countrySubstring) {
				countrySubstring = CyberUtils.escapePCRECharacters(countrySubstring);
				BasicQuery query = new BasicQuery("{\"country\": {$regex : '" + countrySubstring + "',$options:'i'}, \"active\":true }");
				query.limit(10);
				return	 mongo.find(query, PopulatedPlace.class);
		}@Override
		public List<PopulatedPlace> findByContainingState(
				String stateSubstring) {
				stateSubstring = CyberUtils.escapePCRECharacters(stateSubstring);
				BasicQuery query = new BasicQuery("{\"state\": {$regex : '" + stateSubstring + "',$options:'i'}, \"active\":true }");
				query.limit(10);
				return	 mongo.find(query, PopulatedPlace.class);
		}
	
	@Override
		public List<PopulatedPlace> findByStateName(String state) throws Exception {
			ArrayList<PopulatedPlace> populatedPlaceList = new ArrayList<PopulatedPlace>();
			DBCollection populatedPlaceCollection = mongo.getCollection("populatedPlace");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("active", true);
			dbObject.put("state", state);
			DBCursor cursor = populatedPlaceCollection.find(dbObject);
			while (cursor.hasNext()){
				DBObject object = cursor.next();
				String populatedPlaceString = (String) object.get("populatedPlace");
				PopulatedPlace populatedPlace = new PopulatedPlace();
				populatedPlace.setPopulatedPlace(populatedPlaceString);
				populatedPlaceList.add(populatedPlace);
			}
			return populatedPlaceList;
		}
	
	private String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}

	@Override
	public Page<PopulatedPlace> findAll(Pageable pageable, PopulatedPlaceFilterInfo filter) throws Exception{
		List<PopulatedPlace> list = findAll(buildCriteria(filter).with(pageable));
		return new PageImpl<PopulatedPlace>(list, pageable, count(filter));
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
	
	private Query buildCriteria(PopulatedPlaceFilterInfo filter) throws Exception{
		Query filterQuery=new Query();
		if (filter.getCountry()!=null && !filter.getCountry().equals(""))filterQuery.addCriteria(Criteria.where("country").regex(translateWildcards(filter.getCountry())));
		if (filter.getState()!=null && !filter.getState().equals(""))filterQuery.addCriteria(Criteria.where("state").regex(translateWildcards(filter.getState())));
		if (filter.getPopulatedPlace()!=null && !filter.getPopulatedPlace().equals(""))filterQuery.addCriteria(Criteria.where("populatedPlace").regex(translateWildcards(filter.getPopulatedPlace())));
		if (filter.getArea()!=null && !filter.getArea().equals(""))filterQuery.addCriteria(translateDoubleOperators(filter.getArea(), "area"));
		if (filter.getPopulation()!=null && !filter.getPopulation().equals(""))filterQuery.addCriteria(translateIntegerOperators(filter.getPopulation(), "population"));
		if (filter.getLatitude()!=null && !filter.getLatitude().equals(""))filterQuery.addCriteria(Criteria.where("latitude").regex(translateWildcards(filter.getLatitude())));
		if (filter.getLongitude()!=null && !filter.getLongitude().equals(""))filterQuery.addCriteria(Criteria.where("longitude").regex(translateWildcards(filter.getLongitude())));
		if (filter.getActive()!=null && !filter.getActive().equals(""))filterQuery.addCriteria(Criteria.where("active").is(filter.getActive()));

		if (filter.getDateOfCreation()!=null && !filter.getDateOfCreation().equals(""))filterQuery.addCriteria(translateDateOperators(filter.getDateOfCreation(),"dateOfCreation" ));
		if (filter.getDateOfModification()!=null)filterQuery.addCriteria(Criteria.where("dateOfModification").is(filter.getDateOfModification()));
		if (filter.getUserName()!=null && !filter.getUserName().equals(""))filterQuery.addCriteria(Criteria.where("userName").regex(translateWildcards(filter.getUserName())));
		if (filter.getCreatedBy()!=null && !filter.getCreatedBy().equals(""))filterQuery.addCriteria(Criteria.where("createdBy").regex(translateWildcards(filter.getCreatedBy())));
		
		return filterQuery;
	}
	
	private List<PopulatedPlace> findAll(Query query) {

		if (query == null) {
			return Collections.emptyList();
		}

		return mongo.find(query, PopulatedPlace.class, "populatedPlace");
	}
	
	private long count(PopulatedPlaceFilterInfo filter) throws Exception{
		return findAll(buildCriteria(filter)).size();
	}
	
	private Pattern translateWildcards(String value){
		if (value.indexOf("%")>=0 && value.indexOf('%')!=value.lastIndexOf("%")){
			value=value.replaceAll("%", "");
			return Pattern.compile("^(.)*"+value+"(.)*$",Pattern.CASE_INSENSITIVE);
		}
		else if(value.indexOf('%')==0){
			value=value.replaceAll("%", "");
			return Pattern.compile("^.*("+value+")$",Pattern.CASE_INSENSITIVE);
		}
		else if(value.indexOf('%')==value.length()-1){
			value=value.replaceAll("%", "");
			return Pattern.compile("^("+value+").*$",Pattern.CASE_INSENSITIVE);
		}
		else{
			return Pattern.compile("^"+value+"$",Pattern.CASE_INSENSITIVE);
		}
	}
}