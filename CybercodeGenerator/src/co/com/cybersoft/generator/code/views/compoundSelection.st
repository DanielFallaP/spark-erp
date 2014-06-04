			\$(function(){
	  			\$("#$field$").change(function(){
	  				var e=document.getElementById("$field$");
	  	 		   	var $field$=e.options[e.selectedIndex].value;
	  	 		   	var path=window.location.pathname.split("/");
	  	 		    var prefix="";
	  	 		   	if (path[1].localeCompare("configuration")!=0){
	  	 		   			prefix="/"+path[1];	
	  	 		   	}
	  	 		   	var url=prefix+"/configuration/$tableName$/retrieve$compoundReference$/retrieve?$field$="+$field$;
	  	 		 \$.ajax({
		  	 		  url: url,
		  	 		  beforeSend: function(xhr) {
		  	            xhr.setRequestHeader("Accept", "application/json");
		  	        }
		  	 		 }).done(function(info){
		  	 			var optionsAsString;
		  	 			
		  	 			$setOptions$
		  	 					  	 			
		  	 		 });
	  	 		  
	  	 		  });
			});