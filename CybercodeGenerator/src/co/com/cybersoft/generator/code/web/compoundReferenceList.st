	
	//StatePageEvent allStateEvent = null;
	//PopulatedPlacePageEvent allPopulatedPlaceEvent = null;
	$fieldStateEvents$
	
	all$upperFirstField$Event = $firstField$Service.requestAllBy$upperFirstField$();
	$tableName$Info.set$upperFirstField$List(all$upperFirstField$Event.get$upperFirstField$List());

	$compoundOps$
	/*if (country!=null || deliveryInfoSession==null){	
		if (country!=null)	
			deliveryInfo.setCountry(country);
		if (allCountryEvent!=null && !allCountryEvent.getCountryList().isEmpty()){
			allStateEvent = stateService.requestAllByCountryName(country==null?allCountryEvent.getCountryList().get(0).getCountry():country);
			deliveryInfo.setStateList(allStateEvent.getStateList());
		}
	}
	else{
		deliveryInfo.setCountryList(deliveryInfoSession.getCountryList());
		deliveryInfo.setCountry(deliveryInfoSession.getCountry());
	}
		
	if (state!=null || deliveryInfoSession==null){
		if (state!=null)
			deliveryInfo.setState(state);
		if (allStateEvent!=null && !allStateEvent.getStateList().isEmpty()){
			allPopulatedPlaceEvent = populatedPlaceService.requestAllByStateName(state==null?allStateEvent.getStateList().get(0).getState():state);
			deliveryInfo.setPopulatedPlaceList(allPopulatedPlaceEvent.getPopulatedPlaceList());
		}
	}
	else{
		deliveryInfo.setStateList(deliveryInfoSession.getStateList());
		deliveryInfo.setState(deliveryInfoSession.getState());
	}*/
	
	if (all$upperLastField$Event!=null && !all$upperLastField$Event.get$upperLastField$List().isEmpty()){
		$tableName$Info.set$upperLastField$List(all$upperLastField$Event.get$upperLastField$List());
		$tableName$Info.set$upperLastField$(all$upperLastField$Event.get$upperLastField$List().get(0).get$upperLastField$());
	}
	else if ($tableName$InfoSession!=null){
		$tableName$Info.set$upperLastField$List($tableName$InfoSession.get$upperLastField$List());
		$tableName$Info.set$upperLastField$($tableName$InfoSession.get$upperLastField$());
	}

		