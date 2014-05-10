@Override
	public $entityName$PageEvent requestByContaining$upperFieldName$(String $fieldName$) throws Exception {
		ArrayList<$entityName$Details> list = new ArrayList<$entityName$Details>();
		List<$entityName$> $fieldName$List = $tableName$CustomRepo.findByContaining$upperFieldName$($fieldName$);
		for ($entityName$ $tableName$Entity : $fieldName$List) {
			list.add($tableName$Entity.to$entityName$Details());
		}
		return new $entityName$PageEvent(list);
	}
