@Bean 
public $entityName$PersistenceService $tableName$PersistenceService(){
	return new $entityName$PersistenceServiceImpl($tableName$Repository, $tableName$CustomRepo());
}