@Override
	public List<$entityName$> findByContaining$upperReferenceField$(
			String $fieldName$Substring) {
		List<$entityName$> $tableName$List = new ArrayList<$entityName$>();
		DBCollection $tableName$Collection = mongo.getCollection("$tableName$");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("$fieldName$", Pattern.compile($fieldName$Substring));
		DBCursor $tableName$Cursor = $tableName$Collection.find(dbObject).limit(10);
		while ($tableName$Cursor.hasNext()){
			DBObject object = $tableName$Cursor.next();
			String $fieldName$String=(String) object.get("$fieldName$");
			$entityName$ $tableName$ = new $entityName$();
			$tableName$.set$upperReferenceField$($fieldName$String);
			$tableName$List.add($tableName$);
		}
		return $tableName$List;
	}