@Bean 
public $entityName$PersistenceService $tableName$PersistenceService(){
	return new $entityName$PersistenceServiceImpl($tableName$Repository, $tableName$CustomRepo());
}

@Bean 
public $entityName$ReportingService $tableName$ReportingService(){
	return new $entityName$ReportingServiceImpl();
}