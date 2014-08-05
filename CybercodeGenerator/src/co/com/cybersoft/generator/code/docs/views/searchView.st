<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags">
 <head>
<meta charset="UTF-8"/>
<title th:text="#{label.$docName$Info}"></title>

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
		 <link th:href="@{/resources/css/magnificPopup.css}" rel="stylesheet" />
	     <script th:src="@{/resources/js/magnificPopup.min.js}"></script>
	     <script type="text/javascript">
	     	/* <![CDATA[ */
	     	
	     \$(document).on("click", "a", function(event){
	     	if (event.target.id!="about-ajax-popup"){
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
		       }
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
  	    	if (path[1].localeCompare("docs")!=0){
  	    		prefix="/"+path[1];	
  	    	}
	    	var url=prefix+"/docs/$docName$?id="+id;
	    	document.location=url;
	    }
	    
	    \$(document).ready( function () {
				
				\$('.about-ajax-popup').magnificPopup({
					type: 'ajax'
				});
			} );   	
	    	
 		       /* ]]> */
	     </script>
	    
</head>
<body>
	<div th:include="normal/layout :: header" ></div>
	<div class="container-fluid">	
		<div class="row-fluid">
			<h3 th:text="#{label.$docName$Info}"> </h3>
			<table width="100%">
				<tr>
					<td width="33%"><div id="new" class='pagination pagination-left'><ul><li><a th:href="@{/docs/$docName$}" th:title='#{label.newLine}'>+</a></li></ul></div></td>
					<td width="33%"><div th:include="normal/dataTable :: paginationbar" ></div></td>
					<td width="34%"></td>
				</tr>
			</table>
			<table class="table table-striped" >
				<thead>
					<tr>
						<th ><a th:href="@{\${page.url}+'?field=_numericId'}" th:text="#{label.numericId}"></a></th>
						$searchFieldsHeaders$
						<th ><a th:href="@{\${page.url}+'?field=dateOfModification'}" th:text="#{label.dateOfModification}"></a></th>
						<th ><a th:href="@{\${page.url}+'?field=userName'}" th:text="#{label.userOfModification}"></a></th>
						<th ><a th:href="@{\${page.url}+'?field=dateOfCreation'}" th:text="#{label.dateOfCreation}"></a></th>
						<th ><a th:href="@{\${page.url}+'?field=createdBy'}" th:text="#{label.createdBy}"></a></th>

					</tr>
				</thead>
				<tbody id="search">
					<tr th:each="object: \${list}" th:onClick="'buildURL(\''+\${object.id}+'\')'">
						<td th:text="\${object.numericId}"></td>
						$searchFieldsColumns$
						<td th:text="\${#calendars.format(object.dateOfModification,'MM/dd/yyyy HH:mm')}" ></td>
						<td th:text="\${object.userName}"></td>
						<td th:text="\${#calendars.format(object.dateOfCreation,'MM/dd/yyyy HH:mm')}" ></td>
						<td th:text="\${object.createdBy}"></td>

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