function peerCompletion(field, peerField, method, table, fieldName){
							var prefix="";
				    	    		var path=window.location.pathname.split("/");
				    	    		if (path[1].localeCompare("configuration")!=0){
				    	    			prefix="/"+path[1];	
				    	    		}
			    	    		$.get(prefix+"/configuration/"+table+"/"+method+"?"+fieldName+"="+field.item.value, {}, function (data) {
					    	           var array=data.split("/////");
					    	           if (data!=""){
					    	        	   $("#"+peerField).val(array[0]);
					    	           }
					    	      });
			    	    		
			    	    		
}