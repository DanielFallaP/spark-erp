<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags" >
    <head>
    	<meta charset="ISO-8859-1" />
        <title th:text="#{label.$tableName$ModificationTitle}"></title>
        
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
	     \$(document).on("click", "a", function(event){
	    	  var link = \$(this);
	    	  var target = link.attr("target");
	    	  if(\$.trim(target).length > 0)
	    	  {
	    	    window.open(link.attr("href"), target);
	    	  }
	    	  else
	    	  {
	    	     window.location = link.attr("href");
	    	  }

	    	  event.preventDefault();
	    	});
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 27) {
	     		  \$('#back a').trigger('click');
	    	  }
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
		        	<div id="back"><a th:text="#{label.back}" th:href="@{/configuration/$tableName$/search$entityName$}"></a></div>
		        	<h3 th:text="#{label.$tableName$ModificationTitle}"></h3>
		        	<table>
		        		$editableTableRows$
		        		$editableReferenceRows$
		        		<tr>
		        			<td>
		        				<input type="submit" th:value="#{label.save}" />
		        			</td>
		        		</tr>
		        	</table>
		        </form>
		    </div>
		    <div th:if='\${$tableName$ModificationException}' class="alert alert-error" th:text="#{label.$tableName$ModificationError}">
            </div>
            <div th:if='\${$tableName$ValidationException}' class="alert alert-error" th:text="#{label.$tableName$ValidationError}">
            </div>
	    </div>
		<div th:include="normal/layout :: footer"></div>    
    </body>
</html>