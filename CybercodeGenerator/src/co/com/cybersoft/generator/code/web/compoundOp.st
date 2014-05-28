if ($fieldName$!=null || $tableName$InfoSession==null){	
		if ($fieldName$!=null)	
			$tableName$Info.set$upperFieldName$($fieldName$);
		if (all$upperFieldName$Event!=null && !all$upperFieldName$Event.get$upperFieldName$List().isEmpty()){
			all$nextUpperField$Event = $nextField$Service.requestAllBy$upperFieldName$Name($fieldName$==null?all$upperFieldName$Event.get$upperFieldName$List().get(0).get$upperFieldName$():$fieldName$);
			$tableName$Info.set$nextUpperField$List(all$nextUpperField$Event.get$nextUpperField$List());
		}
	}
	else{
		$tableName$Info.set$upperFieldName$($tableName$InfoSession.get$upperFieldName$());
		$tableName$Info.set$upperFieldName$List($tableName$InfoSession.get$upperFieldName$List());
}