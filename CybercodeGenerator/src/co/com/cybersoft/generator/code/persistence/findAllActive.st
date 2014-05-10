@Override
public List<$entityName$> findAllActiveBy$upperReferenceField$() {
	ArrayList<$entityName$> $tableName$List = new ArrayList<$entityName$>();
	DBCollection $tableName$Collection = mongo.getCollection("$tableName$");
	BasicDBObject dbObject = new BasicDBObject();
	dbObject.put("active", true);
	DBCursor $tableName$Cursor = $tableName$Collection.find(dbObject);
	while ($tableName$Cursor.hasNext()){
		DBObject object = $tableName$Cursor.next();
		String $referenceField$String=(String) object.get("$referenceField$");
		$entityName$ $tableName$ = new $entityName$();
		$tableName$.set$upperReferenceField$($referenceField$String);
		$tableName$List.add($tableName$);
	}
	return $tableName$List;
}