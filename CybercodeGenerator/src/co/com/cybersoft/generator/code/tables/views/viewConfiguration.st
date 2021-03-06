<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags" >
<head>
	<meta charset="UTF-8"/>
	<title  th:text="#{label.configurationTitle}"></title>
	<link th:href="@{/resources/css/bootstrap.min.css}" rel="stylesheet"/>
		<style type="text/css">
	        body {
	            padding-top: 60px;
	            padding-bottom: 40px;
	        }.sidebar-nav {
	            padding: 9px 0;
	        }	        
	         .inheritBackground{background-color:inherit;}
	    </style>
	 <link th:href="@{/resources/css/bootstrap-responsive.min.css}" rel="stylesheet" />
	 <script th:src="@{/resources/js/jquery-1.11.0.min.js}"></script>
	 <script th:src="@{/resources/js/jquery-ui-1.10.4.min.js}"></script>
	 <link th:href="@{/resources/css/magnificPopup.css}" rel="stylesheet" />
	 <script th:src="@{/resources/js/magnificPopup.min.js}"></script>
	 <script type="text/javascript">
	      /* <![CDATA[ */
	      	\$(document).ready( function () {
				
				\$('.about-ajax-popup').magnificPopup({
					type: 'ajax'
				});
			} );  
			
				function setFocus(){
		   		document.getElementById("$firstOption$").focus();
		   }
			 
	      /* ]]> */
	 </script>
</head>
<body onload="setFocus()">
	<div th:include="normal/layout$module$ :: header" ></div>
	<div class="container-fluid">	
			<div class="hero-unit">
				<table class="inheritBackground" align="center">
					$links$
				</table>
			</div>
	</div>
	<div class="container-fluid" th:include="normal/layout$module$ :: footer"></div>
</body>
</html>