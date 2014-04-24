package co.com.cybersoft.persistence.repository.measurementUnits;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import co.com.cybersoft.persistence.domain.MeasurementUnits;


public class MeasurementUnitsCustomRepoImpl implements MeasurementUnitsCustomRepo {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<MeasurementUnits> findByStartingCodeNumber(String codePrefix) {
		List<MeasurementUnits> measurementUnitsList = new ArrayList<MeasurementUnits>();
		DBCollection measurementUnitsCollection = mongo.getCollection("measurementUnits");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("code", Pattern.compile(codePrefix));
		DBCursor measurementUnitsCursor = measurementUnitsCollection.find(dbObject).limit(10);
		while (measurementUnitsCursor.hasNext()){
			DBObject object = measurementUnitsCursor.next();
			String code=(String) object.get("code");
			MeasurementUnits measurementUnits = new MeasurementUnits();
			measurementUnits.setCode(code);
			measurementUnitsList.add(measurementUnits);
		}
		return measurementUnitsList;
	}

	@Override
		public List<MeasurementUnits> findByContainingDescription(
				String descriptionSubstring) {
			List<MeasurementUnits> measurementUnitsList = new ArrayList<MeasurementUnits>();
			DBCollection measurementUnitsCollection = mongo.getCollection("measurementUnits");
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("description", Pattern.compile(descriptionSubstring));
			DBCursor measurementUnitsCursor = measurementUnitsCollection.find(dbObject).limit(10);
			while (measurementUnitsCursor.hasNext()){
				DBObject object = measurementUnitsCursor.next();
				String description=(String) object.get("description");
				MeasurementUnits measurementUnits = new MeasurementUnits();
				measurementUnits.setDescription(description);
				measurementUnitsList.add(measurementUnits);
			}
			return measurementUnitsList;
		}

}