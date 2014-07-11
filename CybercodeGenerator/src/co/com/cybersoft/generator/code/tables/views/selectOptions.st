optionsAsString = "";
var $field$Array=info.$field$List;
		  	 			
if ($field$Array!=null){
	for(var i = 0; i < $field$Array.length; i++) {
    optionsAsString += "<option value='" + $field$Array[i].$field$ + "'>" + $field$Array[i].$field$ + "</option>";
	}
	\$( 'select[name="$field$"]' ).find('option').remove().end().append( optionsAsString );
}
else {
	\$( 'select[name="$field$"]' ).find('option').remove().end();
}