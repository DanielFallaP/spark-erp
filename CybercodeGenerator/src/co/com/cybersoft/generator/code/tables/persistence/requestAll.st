	public $entityName$PageEvent requestAllBy$upperReferencedField$(EmbeddedField... fields) throws Exception {
		List<$entityName$> all = $tableName$$repo$.findAllActiveBy$upperReferencedField$(fields);
		List<$entityName$Details> list = new ArrayList<$entityName$Details>();
		for ($entityName$ $tableName$ : all) {
			list.add(new $entityName$Details().to$entityName$Details($tableName$));
		}
		return new $entityName$PageEvent(list);
	}