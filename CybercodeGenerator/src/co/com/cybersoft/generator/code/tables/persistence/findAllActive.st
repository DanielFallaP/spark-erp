@Override
public List<$entityName$> findAllActiveBy$upperReferenceField$(EmbeddedField... fields) throws Exception {
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
		java.lang.reflect.Field[] declaredFields = $tableName$.getClass().getDeclaredFields();
		//FIXME improve my performance
		for (int i = 0; i < declaredFields.length; i++) {
			java.lang.reflect.Field declaredField = declaredFields[i];
			for (int j = 0; j < fields.length; j++) {
				String $tableName$Field=fields[j].getName();
				if (declaredField.getName().equals($tableName$Field)){
					Method method = $tableName$.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase($tableName$Field), fields[j].getType());
					method.invoke($tableName$,object.get($tableName$Field));
				}
			}
		}
		$tableName$List.add($tableName$);
	}
	return $tableName$List;
}

	