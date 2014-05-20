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
		$tableName$List.add($tableName$);
		Field[] declaredFields = currency.getClass().getDeclaredFields();
		//FIXME improve my performance
		for (int i = 0; i < declaredFields.length; i++) {
			Field declaredField = declaredFields[i];
			for (int j = 0; j < fields.length; j++) {
				String field=fields[j].getName();
				if (declaredField.getName().equals(field)){
					Method method = currency.getClass().getDeclaredMethod(CyberUtils.setMethodPrefix+toCamelCase(field), fields[j].getType());
					method.invoke(currency,object.get(field));
				}
			}
		}
		currencyList.add(currency);
	}
	return $tableName$List;
}

	