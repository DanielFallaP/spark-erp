@Override
	public $entityName$PageEvent requestAllBy$upperReferencedField$(EmbeddedField... fields) throws Exception {
		List<$entityName$> all = $tableName$$repo$.findAllActiveBy$upperReferencedField$(fields);
		List<$entityName$Details> list = new ArrayList<$entityName$Details>();
		for ($entityName$ $tableName$ : all) {
			list.add($tableName$.to$entityName$Details());
		}
		return new $entityName$PageEvent(list);
	}