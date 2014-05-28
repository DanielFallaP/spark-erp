function $fieldName$SelectedRefresh(){
	  var e=document.getElementById("$fieldName$");
	  var val=e.options[e.selectedIndex].value;
	  var path=window.location.pathname.split("/");
      var prefix="";
	  if (path[1].localeCompare("configuration")!=0){
	  		prefix="/"+path[1];	
	  }
	  window.open(prefix+"/configuration/$tableName$/create$entityName$/fromList?$fieldName$="+val,"_self");
}