								var path=window.location.pathname.split("/");
			    	    		var prefix="";
			   	  	 		   	if (path[1].localeCompare("docs")!=0){
			   	  	 		   			prefix="/"+path[1];	
			   	  	 		   	}
			   	  	 		   	var url=prefix+"/custom/itemHistory/lastPurchase?description="+b.item.value;
				   	  	 		 $.ajax({
				   		  	 		  url: url,
				   		  	 		  beforeSend: function(xhr) {
				   		  	            xhr.setRequestHeader("Accept", "application/json");
				   		  	        }
				   		  	 		 }).done(function(info){
				   		  	 			var purchaseDate=info.purchaseDate;
				   		  	 			var localCurrencyValue=info.localCurrencyValue;
				   		  	 			var foreignCurrencyValue=info.foreignCurrencyValue;
				   		  	 			if (info.purchaseDate!=null){
				   		  	 				var lastPurchaseDate=dateFormat(new Date(info.purchaseDate));
				   		  	 				$("#lastPurchaseDate").val(lastPurchaseDate);
				   		  	 			}else{
				   		  	 				$("#lastPurchaseDate").val("");
				   		  	 			}
				   		  	 			$("#localCurrencyUnitValue").val(localCurrencyValue);
				   		  	 			$("#foreignCurrencyUnitValue").val(foreignCurrencyValue);

				   		  	 		 });