function $fieldName$SelectedRefresh(){
		   	  var e=document.getElementById("$fieldName$");
		   	  var val=e.options[e.selectedIndex].value;
		   	  var path=window.location.pathname.split("/");
		      var prefix="";
		      var id=window.location.pathname;
		      var path=id.split("/");
		      id=path[path.length-1];
		      if (id.indexOf("?$fieldName$") == -1){
		    	  id=id+"?$fieldName$="+val;
		      }
		      else{
		    	  path=id.split("?");
		    	  id=[path.length-2];
		    	  id=id+"?$fieldName$="+val;
		      }
		    	  
		   	  if (path[1].localeCompare("configuration")!=0){
		   	  		prefix="/"+path[1];	
		   	  }
		   	  window.open(prefix+"/configuration/$tableName$/modify$entityName$/"+id,"_self");
	   }