else{
					$entityName$ $tableName$ = new $entityName$PersistenceFactory($tableName$Repository).get$entityName$ByField(event.getField(),event.getValue());
					if ($tableName$!=null)
						$tableName$Details = $tableName$.to$entityName$Details();
				}