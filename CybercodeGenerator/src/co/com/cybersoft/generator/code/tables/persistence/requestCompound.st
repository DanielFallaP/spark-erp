@Override
	public $entityName$PageEvent requestAllBy$upperReferencedField$Name(String $referencedField$) throws Exception {
		List<$entityName$> all = $tableName$CustomRepo.findBy$upperReferencedField$Name($referencedField$);
		List<$entityName$Details> list = new ArrayList<$entityName$Details>();
		for ($entityName$ $tableName$ : all) {
			list.add(new $entityName$Details().to$entityName$Details($tableName$));
		}
		return new $entityName$PageEvent(list);
	}