  \$(function(){
		     \$("#$fieldName$").autocomplete({
		    	    source: function (request, response) {
		    	    		var path=window.location.pathname.split("/");
		    	    		var prefix="";
		    	    		if (path[1].localeCompare("configuration")!=0){
		    	    			prefix="/"+path[1];	
		    	    		}
		    	    		
							\$.get(prefix+"/configuration/$tableName$/get$upperFieldName$List/"+request.term, {}, function (data) {
		    	            var array=data.split("$arraySeparator$");
		    	            if (data!="")
		    	            	response(array);
		    	            else
		    	            	response(null);
		    	        });
		    	    },
		    	    minLength: 3,
	    	    		select: function(a, b){
	    	    		\$(this).val(b.item.value);
	    	    		var path=window.location.pathname.split("/");
	    	    		var prefix="";
	    	    		if (path[1].localeCompare("configuration")!=0){
	    	    			prefix="/"+path[1];	
	    	    		}
	    	    		window.open(prefix+"/configuration/$tableName$/create$entityName$/fromList?field=$fieldName$&value="+encodeURIComponent(b.item.value),"_self");
	    	    	}
		    	});
		    	 
		     });