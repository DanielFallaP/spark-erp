@RequestMapping(value="/configuration/$tableName$/getListBy$fieldName$Return$returnField$", method=RequestMethod.GET)
		public void get$entityName$ListReturn$returnField$(String $fieldName$, HttpServletResponse response) throws Exception{
		$entityName$PageEvent $fieldName$Event = $tableName$Service.requestByContaining$upperFieldName$($fieldName$);
		
		List<$entityName$Details> $fieldName$List = $fieldName$Event.get$entityName$List();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for ($entityName$Details $tableName$Details : $fieldName$List) {
			responseBuff.append($tableName$Details.get$returnField$());
			if (i!=$fieldName$List.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}