<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags">
 <head>
<meta charset="ISO-8859-1"/>
<title th:text="#{label.$tableName$Title}"></title>

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
			<h3 th:text="#{label.$tableName$Info}"> </h3>
			<table width="100%">
				<tr>
					<td width="33%"><div class='pagination pagination-left'><ul><li><a th:href="@{/configuration/$tableName$/create$entityName$/fromList}" th:title='#{label.$tableName$}'>+</a></li></ul></div></td>
					<td width="33%"><div th:include="normal/dataTable :: paginationbar" ></div></td>
					<td width="34%"><div></div></td>
				</tr>
			</table>
			<table class="table table-striped" >
				<thead>
					<tr>
						$columnHeaders$
					</tr>
				</thead>
				<tbody>
					<tr th:each="object: \${list}">
						$codeColumn$
						$otherColumns$
					</tr>
				</tbody>
			</table>
			<table width="100%">
				<tr>
					<td width="33%"></td>
					<td width="33%"><div th:include="normal/dataTable :: paginationbar" ></div></td>
					<td width="34%"><div></div></td>
				</tr>
			</table>
		</div>
	</div>
	<div th:include="normal/layout :: footer"></div>
</body>
</html>