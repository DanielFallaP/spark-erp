@Override
	public List<$entityName$> findBy$upperFieldName$Name(String $fieldName$) throws Exception {
		ArrayList<$entityName$> $tableName$List = new ArrayList<$entityName$>();
		DBCollection $tableName$Collection = mongo.getCollection("$tableName$");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("active", true);
		dbObject.put("$fieldName$", $fieldName$);
		DBCursor cursor = $tableName$Collection.find(dbObject);
		while (cursor.hasNext()){
			DBObject object = cursor.next();
			String $tableName$String = (String) object.get("$tableName$");
			$entityName$ $tableName$ = new $entityName$();
			$tableName$.set$entityName$($tableName$String);
			$tableName$List.add($tableName$);
		}
		return $tableName$List;
	}