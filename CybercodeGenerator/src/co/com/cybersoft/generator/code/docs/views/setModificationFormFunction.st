function setModificationFormValues(id){
			   var selectedRow="#"+id;
			   var link=\$("#$docName$-body-modification-form");
			   var identification="#_id";	 
			   \$(identification).val(id);
			   \$(selectedRow).find('td').each(function (i){
			   		if (i==0){
					   			var field="#$docName$BodyModificationInfo\\\\._enableDeletion";
					   		 	if (\$(this).has("input[type='checkbox']").length!=0)
					   		 		\$(field).prop('checked', true);
					   		 	else
					   		 		\$(field).prop('checked', false);
					}
			   		$fieldValues$
			   });
			\$('.popup-with-form2').click();
			document.getElementById("_itemModification").focus();
			 popupVisible=true;
		   }