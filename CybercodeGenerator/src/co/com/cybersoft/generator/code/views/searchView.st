<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags">
 <head>
<meta charset="ISO-8859-1"/>
<title th:text="#{label.$tableName$Info}"></title>

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
	     <link th:href="@{/resources/css/cybersystems.css}" rel="stylesheet" />
	     <link th:href="@{/resources/css/bootstrap-responsive.min.css}" rel="stylesheet" />
		 <script th:src="@{/resources/js/jquery-1.11.0.min.js}"></script>
		 <script th:src="@{/resources/js/jquery-ui-1.10.4.min.js}"></script>
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
	    	  if(e.which == 13 || e.which == 107) {
	     		  \$('#new a').trigger('click');
	    	  }
	    	});
	    	
	       \$(document).keyup(function(e) {
	    	  if(e.which == 39 || e.which == 34) {
	     		  \$('#_nextPage a').trigger('click');
	    	  }
	    	});
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 37 || e.which == 33) {
	     		  \$('#_previousPage a').trigger('click');
	    	  }
	    	});
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 35) {
	     		  \$('#_lastPage a').trigger('click');
	    	  }
	    	});
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 36) {
	     		  \$('#_firstPage a').trigger('click');
	    	  }
	    	});	
	    	
	    \$(window).load(function(){
		\$('#search tr').hover(function() {
		    \$(this).addClass('hover');
		}, function() {
		    \$(this).removeClass('hover');
		});
		});

	    function buildURL(id){
	    	var path=window.location.pathname.split("/");
  	    	var prefix="";
  	    	if (path[1].localeCompare("configuration")!=0){
  	    		prefix="/"+path[1];	
  	    	}
	    	var url=prefix+"/configuration/$tableName$/modify$entityName$/"+id;
	    	document.location=url;
	    }	
	    	
	     </script>
	    
</head>
<body>
	<div th:include="normal/layout :: header" ></div>
	<div class="container-fluid">	
		<div class="row-fluid">
			<h3 th:text="#{label.$tableName$Info}"> </h3>
			<table width="100%">
				<tr>
					<td width="33%"><div id="new" class='pagination pagination-left'><ul><li><a th:href="@{/configuration/$tableName$/create$entityName$/fromList}" th:title='#{label.newLine}'>+</a></li></ul></div></td>
					<td width="33%"><div th:include="normal/dataTable :: paginationbar" ></div></td>
					<td width="34%">$excel$</td>
				</tr>
			</table>
			<table class="table table-striped" >
				<thead>
					<tr>
						$columnHeaders$
					</tr>
				</thead>
				<tbody id="search">
					<tr th:each="object: \${list}" th:onClick="'buildURL(\''+\${object.id}+'\')'">
						$columns$
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