function toCamelCase(string){
	var first=string.charAt(0);
	return first.toUpperCase()+string.substring(1);
}

function dateFormat(date){
	var month;
	var day;
	if (date.getMonth()<9)
		month="0"+(date.getMonth()+1);
	else
		month=date.getMonth()+1;
	
	if (date.getDate()<10)
		day="0"+date.getDate();
	else
		day=date.getDate();
	
	
	return day+"/"+month+"/"+date.getFullYear();
}

function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}