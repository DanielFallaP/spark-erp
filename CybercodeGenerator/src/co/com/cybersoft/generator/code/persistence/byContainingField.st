@Override
	public List<$entityName$> findByContaining$upperReferenceField$(
			String $fieldName$Substring) {
			$fieldName$Substring=$fieldName$Substring.replace(".", "\\\\\\\\.");
			BasicQuery query = new BasicQuery("{\"$fieldName$\": {\$regex : '" + $fieldName$Substring + "',\$options:'i'} }");
			query.limit(10);
			return	 mongo.find(query, $entityName$.class);
	}