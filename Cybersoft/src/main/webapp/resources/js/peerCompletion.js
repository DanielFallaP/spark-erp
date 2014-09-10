function peerCompletion(field, peerField, method, table, fieldName, otherFields, modificationPrefix){
							var prefix="";
				    	    		var path=window.location.pathname.split("/");
				    	    		if (path[1].localeCompare("docs")!=0){
				    	    			prefix="/"+path[1];	
				    	    		}
			    	    		$.get(prefix+"/common/"+table+"/"+method+"?"+fieldName+"="+field.item.value, {}, function (data) {
					    	           var array=data.split("/////");
					    	           if (data!=""){
					    	        	   $("#"+peerField).val(array[0]);
					    	           }
					    	      });
			    	    		if (otherFields!=null){
			    	    			var i;
			    	    			for (i = 0; i < otherFields.length; ++i) {
			    	    				var upperField=toCamelCase(otherFields[i]);
			    	    				var lowerField=otherFields[i];
			    	    				if (otherFields[i]=="unit"){
			    	    					otherFields[i]="inputMeasurementUnit";
			    	    				}
			    	    				$.get(prefix+"/common/"+table+"/getListBy"+toCamelCase(fieldName)+"Return"+toCamelCase(otherFields[i])+"?"+fieldName+"="+field.item.value, {}, function (data) {
							    	           var array=data.split("/////");
							    	           if (data!=""){
							    	        	   $("#"+modificationPrefix+lowerField).val(array[0]);
							    	           }
							    	      });
			    	    			}
			    	    		}
			    	    		
			    	    		
}