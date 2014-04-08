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
	     <link th:href="@{/resources/css/bootstrap-responsive.min.css}" rel="stylesheet" />
    </head>
    <body>
    	<div th:include="normal/layout :: header" ></div>
    	<div class="container-fluid">	
			<div class="row-fluid">
		        <form th:object="\${$tableName$Info}" method="post">
		        	<a th:if="\${$tableName$Info.calledFrom=='fromList'}" th:text="#{label.back}" th:href="@{/configuration/$tableName$/search$entityName$}"></a>
		        	<h3 th:text="#{label.$tableName$CreationTitle}"></h3>
		        	<table>		        			        			
		        		$editableTableRows$
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
	    </div>
		<div th:include="normal/layout :: footer"></div>    
    </body>
</html>