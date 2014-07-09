@PostConstruct
	public void startupSingletonTable(){
		DBCollection collection = mongo.getCollection("$tableName$");
		if (collection.getCount()==0){
			BasicDBObject object = new BasicDBObject();
			collection.insert(object);
		}
	}