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
			   \$('.popup-with-form').click();
			   popupVisible=true;
		   		document.getElementById("item").focus();
		   }

		   \$(document).keyup(function(e) {
	    	  if(e.which == 27 && !popupVisible) {
	    		var path=window.location.pathname.split("/");
  	    		var prefix="";
  	    		if (path[1].localeCompare("docs")!=0){
  	    			prefix="/"+path[1];	
  	    		}
	     		 window.open(prefix+"/docs/search$upperDocName$","_self");
	    	  }
	    	});
	      
	      \$.magnificPopup.instance.close = function () {
				popupVisible=false;
			   \$.magnificPopup.proto.close.call(this);
	      }; 
	      
	      \$(document).keyup(function(e) {
	    	  if(e.which == 107 && document.getElementById("newEntry")!=null) {
  				newEntry();
	    	  }
	    	});
	    	
	    $datePickers$
		
			function submitDeletion(){
				var deletion="";
				\$("#$docName$BodyList").find('tr').each(function (i){
					if (\$(this).find("td:first").find('div').find('input').is(':checked')){
						deletion+=\$(this).prop( 'id' )+",";
					}
				});
				var form=document.forms['header-form'];
				form.elements["_deletion"].value=deletion;
				form.elements["_deletionSubmit"].value="deletion";
				form.submit();
			}
			
		  function setFocus(){
		   		document.getElementById("$firstField$").focus();
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
		   
		$compoundReferenceFunctions$   
			   
		$setModificationFormFunction$
		
		$autoCompleteFunctions$
			
		   \$(document).ready( function () {
				\$('#$docName$BodyList').dataTable();

			} );
		   
		   \$(document).ready( function () {
				
				\$('.about-ajax-popup').magnificPopup({
					type: 'ajax'
				});
			} );
		   
		   
		   \$(document).ready(function() {
				\$('.popup-with-form').magnificPopup({
					type: 'inline',
					preloader: false,
					focus: '#name',

					// When elemened is focused, some mobile browsers in some cases zoom in
					// It looks not nice, so we disable it:
					callbacks: {
						beforeOpen: function() {
							if(\$(window).width() < 700) {
								this.st.focus = false;
							} else {
								this.st.focus = '#name';
							}
						}
					}
				});
			});
		   
		   \$(document).ready(function() {
				\$('.popup-with-form2').magnificPopup({
					type: 'inline',
					preloader: false,
					focus: '#name',

					// When elemened is focused, some mobile browsers in some cases zoom in
					// It looks not nice, so we disable it:
					callbacks: {
						beforeOpen: function() {
							if(\$(window).width() < 700) {
								this.st.focus = false;
							} else {
								this.st.focus = '#name';
							}
						}
					}
				});
			});
		   
		   \$(document).ready(function() {
				\$("#header-form :input").change(function() {
					document.getElementById("_savedAlert").style.display='none';
				});
			});
		   
		   function checkExceptions(){
			   if (document.getElementById("_AdditionFormException")!=null){
				   newEntry();
			   }
			   
			   if (document.getElementById("_ModificationFormException")!=null){
				   \$('.popup-with-form2').click();
					document.getElementById("_itemModification").focus();
					 popupVisible=true;
			   }
		   }
		   
	       /* ]]> */
	     </script>

    </head>
    <body onload="checkExceptions();setFocus();">
    	<div th:include="normal/layout :: header" ></div>
    	<div class="container-fluid">	
			<div class="row-fluid">
		        <form id="header-form" th:object="\${$docName$Info}" method="post" >
		        	<input id="_submit" hidden="" th:field="*{$docName$BodyModificationInfo.submit}"></input>
		        	<input th:if='\${$docName$Info.created}' hidden="" value="true" th:field="*{created}"></input>
		        	<div ><a id="back" th:text="#{label.back}" th:href="@{/docs/search$upperDocName$}"></a></div>
		        	
		        	<h3 th:if='\${$docName$Info.numericId==null}' th:text="#{label.$docName$Title}"></h3>
		        	<h3 th:if='\${$docName$Info.numericId!=null}' th:text="#{label.$docName$Title}+' '+\${$docName$Info.numericId}"></h3>
		        	<input id="_deletion" hidden="" th:field="*{deletion}"></input>
		        	<input id="_deletionSubmit" hidden="hidden" th:field="*{$docName$BodyModificationInfo.submit}"></input>
		        	
		        	<table>
		        		$headerFields$
		        	</table>
		        	<br/>
		        	<br/>
		        	<table th:if='\${$docName$Info.showBody}' id="$docName$BodyList" class="table table-striped">
		        		<thead>
							<tr>
								<th ></th>
								$bodyHeaderFields$
							</tr>
						</thead>
						<tbody id="$docName$BodyList">
							<tr th:each="object: \${$docName$BodyList}" th:id="\${object.id}"  >
								<td><div align="center" ><input type="checkbox" /></div></td>
								$bodyFields$
							</tr>
						</tbody>
		        	</table>
					<button th:if='\${$docName$Info.showBody}'  th:text="#{label.newEntryDocTable}" onclick="newEntry();return false;"></button>
					 
       				<input th:if='\${$docName$Info.showBody}' type="button" th:value="#{label.deleteSelection}" onclick="submitDeletion();"/>
       				<a class="popup-with-form" href="#$docName$-body-form" hidden=""></a>
       				<a class="popup-with-form2" href="#$docName$-body-modification-form" hidden=""></a>
							        				
       				<div align="right" ><input type="button" th:value="#{label.save}" onclick="submitHeader();"/></div>
		        </form>
					<input th:if='\${$docName$Info.showBody}' id="newEntry" hidden=""/>
			 <!-- Custom form for adding new records -->
			 <form id="$docName$-body-form" class="white-popup mfp-hide"  th:object="\${$docName$BodyInfo}" method="post">
		        	<h3 th:text="#{label.$docName$BodyCreationTitle}"></h3>
		        	<input id="_submit" hidden="" th:field="*{$docName$BodyModificationInfo.submit}"></input>
		        	<input th:if='\${$docName$Info.created}' hidden="" value="true" th:field="*{created}"></input>
		        	<input th:if="\${_AdditionFormException}" id="_AdditionFormException" hidden=""></input> 
		        	<table>
		        		$additionFormFields$
		        		<tr>
		        			<td>
		        				<input type="button" th:value="#{label.save}" onclick="submitCreation();"/>
		        			</td>
		        		</tr>
		        	</table>
		        </form>
		        
		        <form id="$docName$-body-modification-form" class="white-popup mfp-hide" th:object="\${$docName$BodyModificationInfo}" method="post">
		        	<h3 th:text="#{label.$docName$BodyModificationTitle}"></h3>
		        	<input id="_id" hidden="" th:field="*{$docName$BodyModificationInfo.id}"></input>
		        	<input id="_modificationSubmit" hidden="hidden" th:field="*{$docName$BodyModificationInfo.submit}"></input>
		        	<input th:if='\${$docName$Info.created}' hidden="" value="true" th:field="*{created}"></input>
		        	<input th:if="\${_ModificationFormException}" id="_ModificationFormException" hidden=""></input>
		        	<table >
		        		$modificationFormFields$
		        		<tr>
		        			<td>
		        				<input type="button" th:value="#{label.save}" onclick="submitModification();"/>
		        			</td>
		        		</tr>
		        	</table>
		        </form>
		    </div>
		    <div id="_savedAlert" th:if='\${$docName$Info._toSave}' class="alert alert-info" th:text="#{label.saved}">
            </div>
            <div th:if='\${$docName$CreateException}' class="alert alert-error" th:text="#{label.$docName$CreationError}">
            </div>
            <div th:if='\${$docName$ValidationException}' class="alert alert-error" th:text="#{label.validationError}">
            </div>
	    </div>
		<div th:include="normal/layout :: footer"></div>
    </body>
</html>