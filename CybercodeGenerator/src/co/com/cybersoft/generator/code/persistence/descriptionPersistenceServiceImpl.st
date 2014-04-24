else{
			$entityName$ $tableName$ = $tableName$Repository.findByDescription(event.getDescription());
			if ($tableName$!=null)
				$tableName$Details = $tableName$.to$entityName$Details();
		}
		