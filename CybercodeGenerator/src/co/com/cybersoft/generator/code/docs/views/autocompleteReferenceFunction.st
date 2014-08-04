  \$(function(){
		     \$("#$fieldName$").autocomplete({
		    	    source: function (request, response) {
		    	    		var path=window.location.pathname.split("/");
		    	    		var prefix="";
		    	    		if (path[1].localeCompare("$namespace$")!=0){
		    	    			prefix="/"+path[1];	
		    	    		}
							\$.get(prefix+"/docs/$referenceType$/get$upperReferenceType$List?numericId="+request.term, {}, function (data) {
		    	            var array=data.split("$arraySeparator$");
		    	            if (data!="")
		    	            	response(array);
		    	            else
		    	            	response(null);
		    	        });
		    	    },
		    	    minLength: 1,
	    	    		select: function(a, b){
	    	    		$onSelectionAPICall$
	    	    		
	    	    		\$(this).val(b.item.value);
	    	    		$submitHeader$
	    	    	}
		    	});
		    	 
		     });