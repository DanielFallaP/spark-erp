<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags" >
    <head>
    	<meta charset="UTF-8" />
        <title th:text="#{label.$tableName$CreationTitle}"></title>
        
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
	    <link th:href="@{/resources/css/bootstrap-responsive.min.css}" rel="stylesheet" />
	    <link th:href="@{/resources/css/magnificPopup.css}" rel="stylesheet" />
	    <script th:src="@{/resources/js/magnificPopup.min.js}"></script>
	    <script type="text/javascript">
	      /* <![CDATA[ */
	      
	      function hideAlert(){
	    	  document.getElementById("_savedAlert").style.display='none';
	      }
	     
	      \$(document).keyup(function(e) {
	    	  if(e.which == 27) {
	    		var path=window.location.pathname.split("/");
  	    		var prefix="";
  	    		if (path[1].localeCompare("configuration")!=0){
  	    			prefix="/"+path[1];	
  	    		}
	     		 window.open(prefix+"/configuration/$tableName$/search$entityName$","_self");
	    	  }
	    	});
	        	     
		   $autocompleteFunctions$
		   
		   $autocompleteReferenceFunctions$
		     
		  function setFocus(){
		   		document.getElementById("$firstField$").focus();
		   } 
		   
		   $compoundSelectionFunctions$
		   
		   \$(document).ready( function () {
				
				\$('.about-ajax-popup').magnificPopup({
					type: 'ajax'
				});
			} );
		   
	       /* ]]> */
	     </script>
	    $datePickerConfig$
    </head>
    <body onload="setFocus()">
    	<div th:include="normal/layout :: header" ></div>
    	<div class="container-fluid">	
			<div class="row-fluid">
		        <form th:object="\${$tableName$Info}" method="post" onchange="hideAlert();">
		        	<div id="back"><a th:if="\${$tableName$Info.calledFrom=='fromList'}" th:text="#{label.back}" th:href="@{/configuration/$tableName$/search$entityName$}"></a></div>
		        	<h3 th:text="#{label.$tableName$CreationTitle}"></h3>
		        	<table>		        			        			
		        		$rows$
		        		<tr>
		        			<td>
		        				<input type="submit" th:value="#{label.save}" />
		        			</td>
		        		</tr>
		        	</table>
		        </form>
		    </div>
		    <div id="_savedAlert" th:if='\${$tableName$Info.created}' class="alert alert-info" th:text="#{label.created}">
            </div>
            <div th:if='\${$tableName$CreateException}' class="alert alert-error" th:text="#{label.$tableName$CreationError}">
            </div>
            <div th:if='\${$tableName$ValidationException}' class="alert alert-error" th:text="#{label.$tableName$ValidationError}">
            </div>
	    </div>
		<div th:include="normal/layout :: footer"></div>    
    </body>
</html>