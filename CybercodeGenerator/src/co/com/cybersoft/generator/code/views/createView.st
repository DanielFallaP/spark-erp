<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags" >
    <head>
    	<meta charset="ISO-8859-1" />
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
	    <script type="text/javascript">
	     
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
	    	
	     \$(function(){
	     \$("#code").autocomplete({
	    	    source: function (request, response) {
	    	    		var path=window.location.pathname.split("/");
	    	    		var prefix="";
	    	    		if (path[1].localeCompare("configuration")!=0){
	    	    			prefix="/"+path[1];	
	    	    		}
	    	    		
						\$.get(prefix+"/configuration/$tableName$/getCodeList/"+request.term, {}, function (data) {
	    	            var array=data.split("$arraySeparator$");
	    	            if (data!="")
	    	            	response(array);
	    	            else
	    	            	response(null);
	    	        });
	    	    },
	    	    minLength: 3,
	    	    select: function(a, b){
	    	    		\$(this).val(b.item.value);
	    	    		var path=window.location.pathname.split("/");
	    	    		var prefix="";
	    	    		if (path[1].localeCompare("configuration")!=0){
	    	    			prefix="/"+path[1];	
	    	    		}
	    	    		window.open(prefix+"/configuration/$tableName$/create$entityName$/fromList?id="+b.item.value,"_self");
	    	    	}
	    	});
	    	 
	     });
	     
	     \$(function(){
		     \$("#description").autocomplete({
		    	    source: function (request, response) {
		    	    		var path=window.location.pathname.split("/");
		    	    		var prefix="";
		    	    		if (path[1].localeCompare("configuration")!=0){
		    	    			prefix="/"+path[1];	
		    	    		}
		    	    		
							\$.get(prefix+"/configuration/$tableName$/getDescriptionList/"+request.term, {}, function (data) {
		    	            var array=data.split("$arraySeparator$");
		    	            if (data!="")
		    	            	response(array);
		    	            else
		    	            	response(null);
		    	        });
		    	    },
		    	    minLength: 3,
	    	    		select: function(a, b){
	    	    		\$(this).val(b.item.value);
	    	    		var path=window.location.pathname.split("/");
	    	    		var prefix="";
	    	    		if (path[1].localeCompare("configuration")!=0){
	    	    			prefix="/"+path[1];	
	    	    		}
	    	    		window.open(prefix+"/configuration/$tableName$/create$entityName$/fromList?desc="+b.item.value,"_self");
	    	    	}
		    	});
		    	 
		     });
		     
		   function setFocus(){
		   		document.getElementById("code").focus();
		   } 
	     
	     </script>
	    $datePickerConfig$
    </head>
    <body onload="setFocus()">
    	<div th:include="normal/layout :: header" ></div>
    	<div class="container-fluid">	
			<div class="row-fluid">
		        <form th:object="\${$tableName$Info}" method="post">
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
		    <div th:if='\${$tableName$Info.created}' class="alert alert-info" th:text="#{label.created}">
            </div>
            <div th:if='\${$tableName$CreateException}' class="alert alert-error" th:text="#{label.$tableName$CreationError}">
            </div>
            <div th:if='\${$tableName$ValidationException}' class="alert alert-error" th:text="#{label.$tableName$ValidationError}">
            </div>
	    </div>
		<div th:include="normal/layout :: footer"></div>    
    </body>
</html>