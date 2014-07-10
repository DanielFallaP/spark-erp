<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags" >
    <head>
    	<meta charset="UTF-8" />
        <title th:text="#{label.$docName$CreationTitle}"></title>
        
        <link th:href="@{/resources/css/bootstrap.min.css}" rel="stylesheet"/>
		<style type="text/css">
	        body {
	            padding-top: 60px;
	            padding-bottom: 40px;
	        }
	
	        .sidebar-nav {
	            padding: 9px 0;
	        }
	    </style>
	   	<link rel="stylesheet" th:href="@{/resources/css/jquery-ui-1.10.4.min.css}"/>
<!-- 	      For production purposes, min version should be used -->
		<script th:src="@{/resources/js/jquery-1.11.0.min.js}"></script>
		<script th:src="@{/resources/js/jquery-ui-1.10.4.min.js}"></script>
		<script th:src="@{/resources/js/jquery.dataTables.js}"></script>
	    <link th:href="@{/resources/css/bootstrap-responsive.min.css}" rel="stylesheet" />
	    <link th:href="@{/resources/css/jquery.dataTables.css}" rel="stylesheet" />
	    <link th:href="@{/resources/css/magnificPopup.css}" rel="stylesheet" />
	    <script th:src="@{/resources/js/magnificPopup.min.js}"></script>
	    <script type="text/javascript">
	      /* <![CDATA[ */
	                   
	      var popupVisible=false;
	      
	      function newEntry(){
			   $('.popup-with-form').click();
			   popupVisible=true;
		   		document.getElementById("item").focus();
		   }

		   $(document).keyup(function(e) {
	    	  if(e.which == 27 && !popupVisible) {
	    		var path=window.location.pathname.split("/");
  	    		var prefix="";
  	    		if (path[1].localeCompare("docs")!=0){
  	    			prefix="/"+path[1];	
  	    		}
	     		 window.open(prefix+"/docs/searchRequisition","_self");
	    	  }
	    	});
	      
	      $.magnificPopup.instance.close = function () {
				popupVisible=false;
			   $.magnificPopup.proto.close.call(this);
	      }; 
	      
	      $(document).keyup(function(e) {
	    	  if(e.which == 107 && document.getElementById("newEntry")!=null) {
  				newEntry();
	    	  }
	    	});

		$( function () {
			$( "#date" ).datepicker();
		} );
		
		$( function () {
			$( "#requiredOnDate" ).datepicker();
		} );

		$( function () {
			$( "#_requiredOnDateItemModification" ).datepicker();
		} );
		
		
		$( function () {
			$( "#bodyRequiredOnDate" ).datepicker();
		} );
		     
			function submitDeletion(){
				var deletion="";
				$("#$docName$ItemListBody").find('tr').each(function (i){
					if ($(this).find("td:first").find('div').find('input').is(':checked')){
						deletion+=$(this).prop( 'id' )+",";
					}
				});
				var form=document.forms['header-form'];
				form.elements["_deletion"].value=deletion;
				form.elements["_deletionSubmit"].value="deletion";
				form.submit();
			}
			
		  function setFocus(){
		   		document.getElementById("consecutive").focus();
		   }
		  
		function submitCreation(){
			var form=document.forms['$docName$-body-form'];
			form.elements["_submit"].value="creation";
			form.submit();
		}
		
		function submitHeader(){
			var form=document.forms['header-form'];
			form.elements["_submit"].value="header";
			form.submit();
		}
		
		function submitModification(){
			var form=document.forms['$docName$-body-modification-form'];
			form.elements["_modificationSubmit"].value="modification";
			form.submit();
		}
		   
		   $(function(){
		   	  			$("#country").change(function(){
		   	  				var e=document.getElementById("country");
		   	  	 		   	var country=e.options[e.selectedIndex].value;
		   	  	 		   	var path=window.location.pathname.split("/");
		   	  	 		    var prefix="";
		   	  	 		   	if (path[1].localeCompare("docs")!=0){
		   	  	 		   			prefix="/"+path[1];	
		   	  	 		   	}
		   	  	 		   	var url=prefix+"/docs/$docName$/retrieveCity/retrieve?country="+country;
		   	  	 		 $.ajax({
		   		  	 		  url: url,
		   		  	 		  beforeSend: function(xhr) {
		   		  	            xhr.setRequestHeader("Accept", "application/json");
		   		  	        }
		   		  	 		 }).done(function(info){
		   		  	 			var optionsAsString;
		   		  	 			
		   		  	 			optionsAsString = "";
		   		  	 			var stateArray=info.stateList;
		   		  	 					  	 			
		   		  	 			if (stateArray!=null){
		   		  	 				for(var i = 0; i < stateArray.length; i++) {
		   		  	 			    optionsAsString += "<option value='" + stateArray[i].state + "'>" + stateArray[i].state + "</option>";
		   		  	 				}
		   		  	 				$( 'select[name="state"]' ).find('option').remove().end().append( optionsAsString );
		   		  	 			}
		   		  	 			else {
		   		  	 				$( 'select[name="state"]' ).find('option').remove().end();
		   		  	 			}
		   		  	 			optionsAsString = "";
		   		  	 			var populatedPlaceArray=info.populatedPlaceList;
		   		  	 					  	 			
		   		  	 			if (populatedPlaceArray!=null){
		   		  	 				for(var i = 0; i < populatedPlaceArray.length; i++) {
		   		  	 			    optionsAsString += "<option value='" + populatedPlaceArray[i].populatedPlace + "'>" + populatedPlaceArray[i].populatedPlace + "</option>";
		   		  	 				}
		   		  	 				$( 'select[name="populatedPlace"]' ).find('option').remove().end().append( optionsAsString );
		   		  	 			}
		   		  	 			else {
		   		  	 				$( 'select[name="populatedPlace"]' ).find('option').remove().end();
		   		  	 			}

		   		  	 					  	 			
		   		  	 		 });
		   	  	 		  
		   	  	 		  });
		   			});
		   $(function(){
		   	  			$("#state").change(function(){
		   	  				var e=document.getElementById("state");
		   	  	 		   	var state=e.options[e.selectedIndex].value;
		   	  	 		   	var path=window.location.pathname.split("/");
		   	  	 		    var prefix="";
		   	  	 		   	if (path[1].localeCompare("docs")!=0){
		   	  	 		   			prefix="/"+path[1];	
		   	  	 		   	}
		   	  	 		   	var url=prefix+"/docs/$docName$/retrieveCity/retrieve?state="+state;
		   	  	 		 $.ajax({
		   		  	 		  url: url,
		   		  	 		  beforeSend: function(xhr) {
		   		  	            xhr.setRequestHeader("Accept", "application/json");
		   		  	        }
		   		  	 		 }).done(function(info){
		   		  	 			var optionsAsString;
		   		  	 			
		   		  	 			optionsAsString = "";
		   		  	 			var populatedPlaceArray=info.populatedPlaceList;
		   		  	 					  	 			
		   		  	 			if (populatedPlaceArray!=null){
		   		  	 				for(var i = 0; i < populatedPlaceArray.length; i++) {
		   		  	 			    optionsAsString += "<option value='" + populatedPlaceArray[i].populatedPlace + "'>" + populatedPlaceArray[i].populatedPlace + "</option>";
		   		  	 				}
		   		  	 				$( 'select[name="populatedPlace"]' ).find('option').remove().end().append( optionsAsString );
		   		  	 			}
		   		  	 			else {
		   		  	 				$( 'select[name="populatedPlace"]' ).find('option').remove().end();
		   		  	 			}

		   		  	 					  	 			
		   		  	 		 });
		   	  	 		  
		   	  	 		  });
		   			});
		   

			function setModificationFormValues(id){
			   var selectedRow="#"+id;
			   var link=$("#$docName$-body-modification-form");
			   var identification="#_id";	 
			   $(identification).val(id);
			   $(selectedRow).find('td').each(function (i){
				  if (i==1){
					    var field="#_itemModification";	 
						$(field).val($(this).html());
				  }
				  if (i==2){
					  var field="#_requiredOnDateItemModification";	 
					  $(field).val($(this).html());
				  }
				  if (i==3){
					  var field="#_priorityItemModification";	 
					  $(field).val($(this).html());
				  }
				  if (i==4){
					  var field="#_quantityModification";	 
					  $(field).val($(this).html());
				  }
				  
			   });
			$('.popup-with-form2').click();
			document.getElementById("_itemModification").focus();
			 popupVisible=true;
		   }
			
		   $(document).ready( function () {
				$('#$docName$ItemList').dataTable();

			} );
		   
		   $(document).ready( function () {
				
				$('.about-ajax-popup').magnificPopup({
					type: 'ajax'
				});
			} );
		   
		   
		   $(document).ready(function() {
				$('.popup-with-form').magnificPopup({
					type: 'inline',
					preloader: false,
					focus: '#name',

					// When elemened is focused, some mobile browsers in some cases zoom in
					// It looks not nice, so we disable it:
					callbacks: {
						beforeOpen: function() {
							if($(window).width() < 700) {
								this.st.focus = false;
							} else {
								this.st.focus = '#name';
							}
						}
					}
				});
			});
		   
		   $(document).ready(function() {
				$('.popup-with-form2').magnificPopup({
					type: 'inline',
					preloader: false,
					focus: '#name',

					// When elemened is focused, some mobile browsers in some cases zoom in
					// It looks not nice, so we disable it:
					callbacks: {
						beforeOpen: function() {
							if($(window).width() < 700) {
								this.st.focus = false;
							} else {
								this.st.focus = '#name';
							}
						}
					}
				});
			});
		   
		   $(document).ready(function() {
				$("#header-form :input").change(function() {
					document.getElementById("_savedAlert").style.display='none';
				});
			});
		   
		   
		   $(function(){
	   		     $("#item").autocomplete({
	   		    	    source: function (request, response) {
	   		    	    		var path=window.location.pathname.split("/");
	   		    	    		var prefix="";
	   		    	    		if (path[1].localeCompare("docs")!=0){
	   		    	    			prefix="/"+path[1];	
	   		    	    		}
	   							$.get(prefix+"/configuration/item/getDescriptionList?description="+request.term, {}, function (data) {
	   		    	            var array=data.split("/////");
	   		    	            if (data!="")
	   		    	            	response(array);
	   		    	            else
	   		    	            	response(null);
	   		    	        });
	   		    	    },
	   		    	    minLength: 3,
	   	    	    		select: function(a, b){
	   	    	    		$(this).val(b.item.value);
	   	    	    	}
	   		    	});
	   		    	 
	   		     });
		   
		   $(function(){
	   		     $("#_itemModification").autocomplete({
	   		    	    source: function (request, response) {
	   		    	    		var path=window.location.pathname.split("/");
	   		    	    		var prefix="";
	   		    	    		if (path[1].localeCompare("docs")!=0){
	   		    	    			prefix="/"+path[1];	
	   		    	    		}
	   							$.get(prefix+"/configuration/item/getDescriptionList?description="+request.term, {}, function (data) {
	   		    	            var array=data.split("/////");
	   		    	            if (data!="")
	   		    	            	response(array);
	   		    	            else
	   		    	            	response(null);
	   		    	        });
	   		    	    },
	   		    	    minLength: 3,
	   	    	    		select: function(a, b){
	   	    	    		$(this).val(b.item.value);
	   	    	    	}
	   		    	});
	   		    	 
	   		     });
		   
		   function checkExceptions(){
			   if (document.getElementById("_AdditionFormException")!=null){
				   newEntry();
			   }
			   
			   if (document.getElementById("_ModificationFormException")!=null){
				   $('.popup-with-form2').click();
					document.getElementById("_itemModification").focus();
					 popupVisible=true;
			   }
		   }
		   
	       /* ]]> */
	     </script>

    </head>
    <body onload="checkExceptions();">
    	<div th:include="normal/layout :: header" ></div>
    	<div class="container-fluid">	
			<div class="row-fluid">
		        <form id="header-form" th:object="${$docName$Info}" method="post" >
		        	<input id="_submit" hidden="" th:field="*{$docName$ItemModificationInfo.submit}"></input>
		        	<input th:if='${$docName$Info.created}' hidden="" value="true" th:field="*{created}"></input>
		        	<div ><a id="back" th:text="#{label.back}" th:href="@{/docs/searchRequisition}"></a></div>
		        	
		        	<h3 th:if='${$docName$Info.numericId==null}' th:text="#{label.$docName$Title}"></h3>
		        	<h3 th:if='${$docName$Info.numericId!=null}' th:text="#{label.$docName$Title}+' '+${$docName$Info.numericId}"></h3>
		        	<input id="_deletion" hidden="" th:field="*{deletion}"></input>
		        	<input id="_deletionSubmit" hidden="hidden" th:field="*{$docName$ItemModificationInfo.submit}"></input>
		        	
		        	<table>
		        		<tr th:if="${requiredOnDateExcep8} or ${requiredOnDateException}">
		        			<td></td>
		        			<td></td>
		        			<td th:if="${requiredOnDateException}" colspan="2"><div class="alert alert-error" th:text="#{${requiredOnDateValidationErrorMessage}}"></div></td>
		        			<td></td>
		        			<td></td>
		        		</tr>		        			        			
		        		<tr>
        		 			<td style="padding:0 15px;"><b th:text="#{label.$docName$Stock}+': '"></b></td>
        		   			<td ><input type="checkbox" th:field="*{stock}" /></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$RequiredOnDate}+': '"></b></td>
		        			<td ><input id="requiredOnDate" name="requiredOnDate" type="text" th:value="*{{requiredOnDate}}" /></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$Priority}+': '"></b></td>
		        			<td ><select th:field="*{priority}"><option th:each="reference : ${$docName$Info.priorityList}" th:value="${reference.priority}" th:text="#{${reference.priority}}"></option></select></td>
		        			<td th:if="${priorityException}"><div class="alert alert-error" th:text="#{${priorityValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$Country}+': '"></b></td>
		        			<td><select id="country" onChange="countrySelectedRefresh()" th:field="*{country}"><option th:each="reference : ${$docName$Info.countryList}" th:value="${reference.country}" th:text="${reference.country}"></option></select></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$State}+': '"></b></td>
		        			<td ><select id="state" onChange="stateSelectedRefresh()" th:field="*{state}"><option th:each="reference : ${$docName$Info.stateList}" th:value="${reference.state}" th:text="${reference.state}"></option></select></td>
		        			<td th:if="${stateException}"><div class="alert alert-error" th:text="#{${stateValidationErrorMessage}}"></div></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$PopulatedPlace}+': '"></b></td>
		        			<td><select id="populatedPlace" onChange="populatedPlaceSelectedRefresh()" th:field="*{populatedPlace}"><option th:each="reference : ${$docName$Info.populatedPlaceList}" th:value="${reference.populatedPlace}" th:text="${reference.populatedPlace}"></option></select></td>
		        			<td th:if="${populatedPlaceException}"><div class="alert alert-error" th:text="#{${populatedPlaceValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr th:if="${dateException}">
		        			<td th:if="${dateException}" colspan="2"><div class="alert alert-error" th:text="#{${dateValidationErrorMessage}}"></div></td>
		        			<td></td>
		        			<td></td>
		        			<td></td>
		        			<td></td>
		        		</tr>	
		        		<tr>
		        			<td style="padding:0 15px;"><b th:text="#{label.date}+': '"></b></td>
		        			<td><input id="date" name="date" type="text" th:value="*{{date}}" /></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$RequestingDepartment}+': '"></b></td>
		        			<td><select id="requestingDepartment" onChange="requestingDepartmentSelectedRefresh()" th:field="*{requestingDepartment}"><option th:each="reference : ${$docName$Info.requestingDepartmentList}" th:value="${reference.department}" th:text="${reference.department}"></option></select></td>
		        			<td th:if="${requestingDepartmentException}"><div class="alert alert-error" th:text="#{${requestingDepartmentValidationErrorMessage}}"></div></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$ExpenseType}+': '"></b></td>
		        			<td><select id="expenseType" onChange="expenseTypeSelectedRefresh()" th:field="*{expenseType}"><option th:each="reference : ${$docName$Info.expenseTypeList}" th:value="${reference.expenseType}" th:text="${reference.expenseType}"></option></select></td>
		        			<td th:if="${expenseTypeException}"><div class="alert alert-error" th:text="#{${expenseTypeValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$TransportationType}+': '"></b></td>
		        			<td><select th:field="*{transportationType}"><option th:each="reference : ${$docName$Info.transportationTypeList}" th:value="${reference.transportationType}" th:text="#{${reference.transportationType}}"></option></select></td>
		        			<td th:if="${transportationTypeException}"><div class="alert alert-error" th:text="#{${transportationTypeValidationErrorMessage}}"></div></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$ReceivingWarehouse}+': '"></b></td>
		        			<td><select id="receivingWarehouse" onChange="receivingWarehouseSelectedRefresh()" th:field="*{receivingWarehouse}"><option th:each="reference : ${$docName$Info.receivingWarehouseList}" th:value="${reference.warehouse}" th:text="${reference.warehouse}"></option></select></td>
		        			<td th:if="${receivingWarehouseException}"><div class="alert alert-error" th:text="#{${receivingWarehouseValidationErrorMessage}}"></div></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$State+': '}"></b></td>
		        			<td th:text="#{label.$docName$StateIncomplete}"></td>
		        		</tr>
		        	</table>
		        	<br/>
		        	<br/>
		        	<table th:if='${$docName$Info.showBody}' id="$docName$ItemList" class="table table-striped">
		        		<thead>
							<tr>
								<th ></th>
								<th th:text="#{label.$docName$ItemItem}"></th>
								<th th:text="#{label.$docName$ItemRequiredOnDate}"></th>
								<th th:text="#{label.$docName$ItemPriority}"></th>
								<th th:text="#{label.$docName$ItemQuantity}"></th>
								<th th:text="#{label.$docName$ItemLocalCurrencyUnitValue}"></th>
								<th th:text="#{label.$docName$ItemForeignCurrencyUnitValue}"></th>
							</tr>
						</thead>
						<tbody id="$docName$ItemListBody">
							<tr th:each="object: ${$docName$ItemInfoList}" th:id="${object.id}"  >
								<td><div align="center" ><input type="checkbox" /></div></td>
								<td th:text="${object.item}" th:onClick="'setModificationFormValues(\''+${object.id}+'\')'" ></td>
								<td th:text="${{object.bodyRequiredOnDate}}" th:onClick="'setModificationFormValues(\''+${object.id}+'\')'" ></td>
								<td th:text="#{${object.priority}}" th:onClick="'setModificationFormValues(\''+${object.id}+'\')'" ></td>
								<td th:text="${object.quantity}" th:onClick="'setModificationFormValues(\''+${object.id}+'\')'"></td>
								<td th:text="${object.localCurrencyUnitValue}" th:onClick="'setModificationFormValues(\''+${object.id}+'\')'"></td>
								<td th:text="${object.foreignCurrencyUnitValue}" th:onClick="'setModificationFormValues(\''+${object.id}+'\')'"></td>
							
							</tr>
						</tbody>
		        	</table>
					<button th:if='${$docName$Info.showBody}'  th:text="#{label.newEntryDocTable}" onclick="newEntry();return false;"></button>
					 
       				<input th:if='${$docName$Info.showBody}' type="button" th:value="#{label.deleteSelection}" onclick="submitDeletion();"/>
       				<a class="popup-with-form" href="#$docName$-body-form" hidden=""></a>
       				<a class="popup-with-form2" href="#$docName$-body-modification-form" hidden=""></a>
							        				
       				<div align="right" ><input type="button" th:value="#{label.save}" onclick="submitHeader();"/></div>
		        </form>
					<input th:if='${$docName$Info.showBody}' id="newEntry" hidden=""/> 
			 <!-- Custom form for adding new records -->
			 <form id="$docName$-body-form" class="white-popup mfp-hide"  th:object="${$docName$ItemInfo}" method="post">
		        	<h3 th:text="#{label.$docName$ItemCreationTitle}"></h3>
		        	<input id="_submit" hidden="" th:field="*{$docName$ItemModificationInfo.submit}"></input>
		        	<input th:if='${$docName$Info.created}' hidden="" value="true" th:field="*{created}"></input>
		        	<input th:if="${_AdditionFormException}" id="_AdditionFormException" hidden=""></input> 
		        	<table>		        			        			
		        		<tr>
		        			<td ><b th:text="#{label.$docName$ItemItem}+': '"></b></td>
		        			<td><input id="item" type="text" th:field="*{item}" /></td>
		        			<td th:if="${itemException}"><div class="alert alert-error" th:text="#{${itemValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        				<td ><b th:text="#{label.$docName$ItemRequiredOnDate}+': '"></b></td>
		        				<td><input id="bodyRequiredOnDate" name="bodyRequiredOnDate" type="text" th:value="*{{bodyRequiredOnDate}}" /></td>
		        				<td th:if="${bodyRequiredOnDateException}"><div class="alert alert-error" th:text="#{${bodyRequiredOnDateValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        			<td ><b th:text="#{label.$docName$ItemPriority}+': '"></b></td>
		        			<td><select th:field="*{priority}"><option th:each="reference : ${$docName$ItemInfo.priorityList}" th:value="${reference.priority}" th:text="#{${reference.priority}}"></option></select></td>
		        			<td th:if="${priorityException}"><div class="alert alert-error" th:text="#{${priorityValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        				<td ><b th:text="#{label.$docName$ItemQuantity}+': '"></b></td>
		        				<td><input id="quantity" type="text" th:field="*{quantity}" /></td>
		        				<td th:if="${quantityException}"><div class="alert alert-error" th:text="#{${quantityValidationErrorMessage}}"></div></td>
		        		</tr>

		        		<tr>
		        			<td>
		        				<input type="button" th:value="#{label.save}" onclick="submitCreation();"/>
		        			</td>
		        		</tr>
		        	</table>
		        </form>
		        
		        <form id="$docName$-body-modification-form" class="white-popup mfp-hide" th:object="${$docName$ItemModificationInfo}" method="post">
		        	<h3 th:text="#{label.$docName$ItemModificationTitle}"></h3>
		        	<input id="_id" hidden="" th:field="*{$docName$ItemModificationInfo.id}"></input>
		        	<input id="_modificationSubmit" hidden="hidden" th:field="*{$docName$ItemModificationInfo.submit}"></input>
		        	<input th:if='${$docName$Info.created}' hidden="" value="true" th:field="*{created}"></input>
		        	<input th:if="${_ModificationFormException}" id="_ModificationFormException" hidden=""></input>
		        	<table >		        			        			
		        		<tr>
		        			<td ><b th:text="#{label.$docName$ItemItem}+': '"></b></td>
		        			<td><input id="_itemModification" type="text" th:field="*{requisitionItemModificationInfo.item}" /></td>
		        			<td th:if="${_itemModification}"><div class="alert alert-error" th:text="#{${itemValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        				<td ><b th:text="#{label.requisitionItemRequiredOnDate}+': '"></b></td>
		        				<td><input id="_requiredOnDateItemModification" type="text" th:field="*{requisitionItemModificationInfo.bodyRequiredOnDate}" /></td>
		        				<td th:if="${_requiredOnDateItemException}"><div class="alert alert-error" th:text="#{${requiredOnDateValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        			<td ><b th:text="#{label.requisitionItemPriority}+': '"></b></td>
		        			<td><select id="_priorityItemModification" th:field="*{requisitionItemModificationInfo.priority}"><option th:each="reference : ${requisitionItemInfo.priorityList}" th:value="${reference.priority}" th:text="#{${reference.priority}}"></option></select></td>
		        			<td th:if="${_priorityItemModificationException}"><div class="alert alert-error" th:text="#{${priorityValidationErrorMessage}}"></div></td>
		        		</tr>
		        		<tr>
		        				<td ><b th:text="#{label.requisitionItemQuantity}+': '"></b></td>
		        				<td><input id="_quantityModification" type="text" th:field="*{requisitionItemModificationInfo.quantity}" /></td>
		        				<td th:if="${_quantityModificationException}"><div class="alert alert-error" th:text="#{${quantityValidationErrorMessage}}"></div></td>
		        		</tr>

		        		<tr>
		        			<td>
		        				<input type="button" th:value="#{label.save}" onclick="submitModification();"/>
		        			</td>
		        		</tr>
		        	</table>
		        </form>
		    </div>
		    <div id="_savedAlert" th:if='${$docName$Info._toSave}' class="alert alert-info" th:text="#{label.saved}">
            </div>
            <div th:if='${$docName$CreateException}' class="alert alert-error" th:text="#{label.$docName$CreationError}">
            </div>
            <div th:if='${$docName$ValidationException}' class="alert alert-error" th:text="#{label.validationError}">
            </div>
	    </div>
		<div th:include="normal/layout :: footer"></div>
    </body>
</html>