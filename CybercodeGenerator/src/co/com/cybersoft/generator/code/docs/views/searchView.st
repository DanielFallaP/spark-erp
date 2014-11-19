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
	        .page-selection{background-color:#fff!important;border:1px #fff!important;box-shadow:inset 0 1px 1px transparent!important;transition:border linear .2s,box-shadow linear .2s!important;display:inline-block!important;height:12px!important;padding:4px 6px!important;margin:-4px -11px -3px!important;font-size:14px!important;line-height:0!important;color:#555!important;vertical-align:middle!important;border-radius:0!important;width:23px!important;text-align:right}
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
	    	  if(e.which == 107) {
	     		  \$('#new a').trigger('click');
	    	  }
	    	});
	    	
	     function resetPageValue(){
	    	 document.getElementById("_page").value=document.getElementById("_currentPage").value;
	     }
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 13 && document.getElementById("_page") === document.activeElement) {
	    		if (	document.getElementById("_page").value== null
	    			  	|| document.getElementById("_page").value == ""
	    				|| isNaN(document.getElementById("_page").value) 
	    				|| (!isNaN(document.getElementById("_page").value) && document.getElementById("_page").value <= 0)
	    				|| (!isNaN(document.getElementById("_page").value) && document.getElementById("_totalPages").value < document.getElementById("_page").value)
	    			){
					alert('Invalid page number');
					resetPageValue();
	    		}
	    		else{
		    		var path=window.location.pathname.split("/");
	 	    		var prefix="";
	 	    		if (path[1].localeCompare("docs")!=0){
	 	    			prefix="/"+path[1];	
	 	    		}
		     		window.open(prefix+"/docs/search$upperDocName$?page.page="+document.getElementById("_page").value,"_self");
	    		}
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
						<th >
							<a th:if="\${_field} == '_numericId' and \${_direction}" th:href="@{\${page.url}+'?field=_numericId'}" th:text="'&uarr;'+#{label.numericId}"></a>
							<a th:if="\${_field} == '_numericId' and !\${_direction}" th:href="@{\${page.url}+'?field=_numericId'}" th:text="'&darr;'+#{label.numericId}"></a>
							<a th:if="\${_field} != '_numericId'" th:href="@{\${page.url}+'?field=_numericId'}" th:text="#{label.numericId}"></a>
						</th>
						$searchFieldsHeaders$
						<th >
							<a th:if="\${_field} == 'dateOfModification' and \${_direction}" th:href="@{\${page.url}+'?field=dateOfModification'}" th:text="'&uarr;'+#{label.dateOfModification}"></a>
							<a th:if="\${_field} == 'dateOfModification' and !\${_direction}" th:href="@{\${page.url}+'?field=dateOfModification'}" th:text="'&darr;'+#{label.dateOfModification}"></a>
							<a th:if="\${_field} != 'dateOfModification'" th:href="@{\${page.url}+'?field=dateOfModification'}" th:text="#{label.dateOfModification}"></a>
						</th>
						<th >
							<a th:if="\${_field} == 'userName' and \${_direction}" th:href="@{\${page.url}+'?field=userName'}" th:text="'&uarr;'+#{label.userOfModification}"></a>
							<a th:if="\${_field} == 'userName' and !\${_direction}" th:href="@{\${page.url}+'?field=userName'}" th:text="'&darr;'+#{label.userOfModification}"></a>
							<a th:if="\${_field} != 'userName'" th:href="@{\${page.url}+'?field=userName'}" th:text="#{label.userOfModification}"></a>
						</th>
						<th >
							<a th:if="\${_field} == 'dateOfCreation' and \${_direction}" th:href="@{\${page.url}+'?field=dateOfCreation'}" th:text="'&uarr;'+#{label.dateOfCreation}"></a>
							<a th:if="\${_field} == 'dateOfCreation' and !\${_direction}" th:href="@{\${page.url}+'?field=dateOfCreation'}" th:text="'&darr;'+#{label.dateOfCreation}"></a>
							<a th:if="\${_field} != 'dateOfCreation'" th:href="@{\${page.url}+'?field=dateOfCreation'}" th:text="#{label.dateOfCreation}"></a>
						</th>
						<th >
							<a th:if="\${_field} == 'createdBy' and \${_direction}" th:href="@{\${page.url}+'?field=createdBy'}" th:text="&uarr;'+#{label.createdBy}"></a>
							<a th:if="\${_field} == 'createdBy' and !\${_direction}" th:href="@{\${page.url}+'?field=createdBy'}" th:text="&darr;'+#{label.createdBy}"></a>
							<a th:if="\${_field} != 'createdBy'" th:href="@{\${page.url}+'?field=createdBy'}" th:text="#{label.createdBy}"></a>
						</th>

					</tr>
				</thead>
				<tbody id="search">
					<tr th:each="object: \${list}" th:onClick="'buildURL(\''+\${object.id}+'\')'">
						<td th:text="\${object.numericId}"></td>
						$searchFieldsColumns$
						<td th:text="\${#calendars.format(object.dateOfModification,'dd/MM/yyyy HH:mm')}" ></td>
						<td th:text="\${object.userName}"></td>
						<td th:text="\${#calendars.format(object.dateOfCreation,'dd/MM/yyyy HH:mm')}" ></td>
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
	<div class="container-fluid" th:include="normal/layout :: footer"></div>
</body>
</html>