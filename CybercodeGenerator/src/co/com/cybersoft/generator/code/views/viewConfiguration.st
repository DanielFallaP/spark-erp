<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags" >
<head>
	<meta charset="ISO-8859-1"/>
	<title  th:text="#{label.configurationTitle}"></title>
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
			<div class="hero-unit span9">
				<div><a th:href="@{/configuration/items/searchItem}" th:text="#{label.itemConfiguration}"></a> </div>
				$links$
				<div><a th:href="@{/configuration/language}" th:text="#{label.languageConfiguration}"></a> </div>
			</div>
	</div>
	<div th:include="normal/layout :: footer"></div>
</body>
</html>