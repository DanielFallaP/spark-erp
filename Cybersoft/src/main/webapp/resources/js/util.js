function toCamelCase(string){
	var first=string.charAt(0);
	return first.toUpperCase()+string.substring(1);
}

function dateFormat(date){
	var month;
	var day;
	if (date.getMonth()<10)
		month="0"+date.getMonth();
	else
		month=date.getMonth();
	
	if (date.getDay()<10)
		day="0"+date.getDay();
	else
		day=date.getDay();
	
	
	return month+"/"+day+"/"+date.getFullYear();
}