@Override
	public List<$entityName$> findByContaining$upperReferenceField$(
			String $fieldName$Substring) {
			$fieldName$Substring = CyberUtils.escapePCRECharacters($fieldName$Substring);
			BasicQuery query = new BasicQuery("{\"$fieldName$\": {\$regex : '" + $fieldName$Substring + "',\$options:'i'}, \"active\":true }");
			query.limit(10);
			return	 mongo.find(query, $entityName$.class);
	}