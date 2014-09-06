  \$(function(){
		     \$("#$fieldName$").autocomplete({
		    	    source: function (request, response) {
		    	    		var path=window.location.pathname.split("/");
		    	    		var prefix="";
		    	    		if (path[1].localeCompare("common")!=0){
		    	    			prefix="/"+path[1];	
		    	    		}
							\$.get(prefix+"/common/$referenceType$/getListBy$upperDisplayField$Return$upperDisplayField$?$displayField$="+request.term, {}, function (data) {
		    	            var array=data.split("$arraySeparator$");
		    	            if (data!="")
		    	            	response(array);
		    	            else
		    	            	response(null);
		    	        });
		    	    },
		    	    minLength: 3,
	    	    		select: function(a, b){
	    	    		$onSelectionAPICall$
	    	    		
	    	    		\$(this).val(b.item.value);
	    	    	}
		    	});
		    	 
		     });