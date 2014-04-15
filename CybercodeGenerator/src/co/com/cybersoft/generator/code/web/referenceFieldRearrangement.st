public void rearrange$entityName$List(String selected){
		$entityName$Details selected$entityName$=null;
		ArrayList<$entityName$Details> newList = new ArrayList<$entityName$Details>();
		for($entityName$Details $tableName$:$tableName$List){
			if ($tableName$.get$fieldName$().equals(selected)){
				selected$entityName$=$tableName$;
				newList.add(0, selected$entityName$);
			}
			else{
				newList.add($tableName$);
			}
		}
		$tableName$List=newList;
	
	}