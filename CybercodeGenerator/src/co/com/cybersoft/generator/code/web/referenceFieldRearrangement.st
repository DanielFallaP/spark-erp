public void rearrange$upperFieldName$List(String selected){
		$entityName$Details selected$entityName$=null;
		ArrayList<$entityName$Details> newList = new ArrayList<$entityName$Details>();
		for($entityName$Details $tableName$:$fieldName$List){
			if ($tableName$.get$refFieldName$().equals(selected)){
				selected$entityName$=$tableName$;
				newList.add(0, selected$entityName$);
			}
			else{
				newList.add($tableName$);
			}
		}
		$fieldName$List=newList;
	
	}