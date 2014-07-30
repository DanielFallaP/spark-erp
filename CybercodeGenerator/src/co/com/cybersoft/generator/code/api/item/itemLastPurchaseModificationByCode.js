								var path=window.location.pathname.split("/");
			    	    		var prefix="";
			   	  	 		   	if (path[1].localeCompare("docs")!=0){
			   	  	 		   			prefix="/"+path[1];	
			   	  	 		   	}
			   	  	 		   	var url=prefix+"/custom/itemHistory/lastPurchase?item="+b.item.value;
				   	  	 		 $.ajax({
				   		  	 		  url: url,
				   		  	 		  beforeSend: function(xhr) {
				   		  	            xhr.setRequestHeader("Accept", "application/json");
				   		  	        }
				   		  	 		 }).done(function(info){
				   		  	 			var purchaseDate=info.purchaseDate;
				   		  	 			var localCurrencyValue=info.localCurrencyValue;
				   		  	 			var foreignCurrencyValue=info.foreignCurrencyValue;
				   		  	 			var docName=document.getElementById("_documentName").value;		
				   		  	 			if (info.purchaseDate!=null){
				   		  	 				var lastPurchaseDate=dateFormat(new Date(info.purchaseDate));
				   		  	 				$("#"+docName+"BodyModificationInfo\\.lastPurchaseDate").val(lastPurchaseDate);
				   		  	 			}else{
				   		  	 				$("#"+docName+"BodyModificationInfo\\.lastPurchaseDate").val("");
				   		  	 			}
				   		  	 			$("#"+docName+"BodyModificationInfo\\.localCurrencyUnitValue").val(localCurrencyValue);
				   		  	 			$("#"+docName+"BodyModificationInfo\\.foreignCurrencyUnitValue").val(foreignCurrencyValue);

				   		  	 		 });
				   	  	 		 