if (entity.get$entityName$Permissions()==null || entity.get$entityName$Permissions().equals("")){
	this.$tableName$Create=false;
	this.$tableName$Read=false;
	this.$tableName$Update=false;
	this.$tableName$Export=false;
}else{
	this.$tableName$Create=entity.get$entityName$Permissions().contains("C");
	this.$tableName$Read=entity.get$entityName$Permissions().contains("R");
	this.$tableName$Update=entity.get$entityName$Permissions().contains("U");
	this.$tableName$Export=entity.get$entityName$Permissions().contains("E");
}