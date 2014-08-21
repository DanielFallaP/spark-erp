	if ($docName$.getId()==null){
		List<$upperDocRef$Body> $docRef$BodyEntityList = $docRef$.get$upperDocRef$BodyEntityList();
		ArrayList<$upperDocName$Body> $docName$BodyList = new ArrayList<$upperDocName$Body>();
		for ($upperDocRef$Body $docRef$Body : $docRef$BodyEntityList) {
			$upperDocName$Body $docName$Body = new $upperDocName$Body();
			$docName$Body.setId(UUID.randomUUID().toString());
			$fields$
			$docName$BodyList.add($docName$Body);
		}
		$docName$.set$upperDocName$BodyEntityList($docName$BodyList);
	}