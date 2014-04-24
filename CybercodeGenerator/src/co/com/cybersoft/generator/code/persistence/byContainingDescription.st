@Override
	public List<$entityName$> findByContainingDescription(
			String descriptionSubstring) {
		List<$entityName$> $tableName$List = new ArrayList<$entityName$>();
		DBCollection $tableName$Collection = mongo.getCollection("$tableName$");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("description", Pattern.compile(descriptionSubstring));
		DBCursor $tableName$Cursor = $tableName$Collection.find(dbObject).limit(10);
		while ($tableName$Cursor.hasNext()){
			DBObject object = $tableName$Cursor.next();
			String description=(String) object.get("description");
			$entityName$ $tableName$ = new $entityName$();
			$tableName$.setDescription(description);
			$tableName$List.add($tableName$);
		}
		return $tableName$List;
	}