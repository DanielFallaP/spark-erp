<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:spring="http://www.springframework.org/tags">
 <head>
<meta charset="UTF-8"/>
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
	        .page-selection{background-color:#fff!important;border:1px #fff!important;box-shadow:inset 0 1px 1px transparent!important;transition:border linear .2s,box-shadow linear .2s!important;display:inline-block!important;height:12px!important;padding:4px 6px!important;margin:-4px -11px -3px!important;font-size:14px!important;line-height:0!important;color:#555!important;vertical-align:middle!important;border-radius:0!important;width:23px!important;text-align:right}
	    </style>
	     <link th:href="@{/resources/css/cybersystems.css}" rel="stylesheet" />
	     <link th:href="@{/resources/css/bootstrap-responsive.min.css}" rel="stylesheet" />
		 <script th:src="@{/resources/js/jquery-1.11.0.min.js}"></script>
		 <script th:src="@{/resources/js/jquery-ui-1.10.4.min.js}"></script>
		 <link th:href="@{/resources/css/magnificPopup.css}" rel="stylesheet" />
	     <script th:src="@{/resources/js/magnificPopup.min.js}"></script>
 	     <script th:src="@{/resources/js/bootstrap-alert.js}"></script>
	     <script th:src="@{/resources/js/bootstrap-collapse.js}"></script>
	     <script th:src="@{/resources/js/bootstrap-dropdown.js}"></script>
	     <script th:src="@{/resources/js/bootstrap-modal.js}"></script>
	     <script th:src="@{/resources/js/bootstrap-transitions.js}"></script>
	     
	     
	     <script type="text/javascript">
	     	/* <![CDATA[ */
	     	
	     function resetPageValue(){
	    	 document.getElementById("_page").value=document.getElementById("_currentPage").value;
	     }
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 13 && document.getElementById("_page") === document.activeElement) {
	    		/*if (	document.getElementById("_page").value== null
	    			  	|| document.getElementById("_page").value == ""
	    				|| isNaN(document.getElementById("_page").value) 
	    				|| (!isNaN(document.getElementById("_page").value) && document.getElementById("_page").value <= 0)
	    				|| (!isNaN(document.getElementById("_page").value) && document.getElementById("_totalPages").value < document.getElementById("_page").value)
	    			){
					alert('Invalid page number');
					resetPageValue();
	    		}
	    		else{*/
		    		var path=window.location.pathname.split("/");
	 	    		var prefix="";
	 	    		if (path[1].localeCompare("configuration")!=0){
	 	    			prefix="/"+path[1];	
	 	    		}
					submitFilterPage(document.getElementById("_page").value);	 	    		
	    		//}
	    	  }
	    		else if (e.which == 13 && (document.getElementById("createdBy") === document.activeElement || document.getElementById("userName") === document.activeElement || 
	    		document.getElementById("dateOfModification") === document.activeElement || document.getElementById("dateOfCreation") === document.activeElement $focusCheck$)){
	    		  submitFilter(1);
	    		}
	    	});
	     	
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
	    	  if(e.which == 27 ) {
	  			\$('#back a').trigger('click');
	    	  }
	    	});	
	    	
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 107) {
	     		  \$('#new a').trigger('click');
	    	  }
	    	});
	    	
	       \$(document).keyup(function(e) {
	    	  if(e.which == 34) {
	    	  //if(e.which == 39 || e.which == 34) {
	     		  \$('#_nextPage a').trigger('click');
	    	  }
	    	});
	     
	     \$(document).keyup(function(e) {
	    	  if(e.which == 33) {
//	    	  if(e.which == 37 || e.which == 33) {
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

		function submitFilter(page){
    	 	var form=document.forms['filter-form'];
    	 	form.elements["selectedFilterPage"].value=page;
			form.submit();
	    }
	    
		function submitAction(action){
    	 	var form=document.forms['filter-form'];
    	 	form.elements["aaaaction"].value=action;
    	 	form.elements["selectedFilterPage"].value=1;
    	 	form.submit();
	    }
	    
	    function addFilter(){
	    	$clearFields$
	    	document.getElementById('dateOfModification').value='';		    	
	    	document.getElementById('userName').value='';		    	
	    	document.getElementById('dateOfCreation').value='';		    	
	    	document.getElementById('createdBy').value='';		    	
	    	
	    
		 	var form=document.forms['filter-form'];
    	 	form.elements["aaddFilter"].value=true;
		}
		
		function submitFilterPage(page,field,changeDirection){
    	 	var form=document.forms['filter-form'];
    	 	form.elements["selectedFilterPage"].value=page;
    	 	if (field!=null)
    	 		form.elements["selectedFilterField"].value=field;
   	 		form.elements["changeSortingFieldDirection"].value=changeDirection!=null?true:false;
    	 	form.submit();
	    }

	    function buildURL(id){
	    	var path=window.location.pathname.split("/");
  	    	var prefix="";
  	    	if (path[1].localeCompare("configuration")!=0){
  	    		prefix="/"+path[1];	
  	    	}
	    	var url=prefix+"/$module$/$tableName$/modify$entityName$/"+id;
	    	document.location=url;
	    }
	    
	    \$(document).ready( function () {
				
				\$('.about-ajax-popup').magnificPopup({
					type: 'ajax'
				});
			} );   	
	    	
	    function generateExcelFile(){

	    	var path=window.location.pathname.split("/");
  	    	var prefix="";
  	    	if (path[1].localeCompare("configuration")!=0){
  	    		prefix="/"+path[1];	
  	    	}
  	    	var url=prefix+'/$module$/$tableName$/export';

	    	        \$.ajax({
	    	            url: url,
	    	            type: 'post',
	    	            dataType: 'json',
	    	            headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
	    	            success : function(result, a,b,c){
	    	            	window.open(prefix+result.url, "_blank");
	    	            },
	    	            error: function(a,b,c,d){
	    	            },
	    	            async: true
	    	            
	    	        });
	    }
 		       /* ]]> */
	     </script>
	    
</head>
<body>
	<div th:fragment="header">
		    <div class="navbar navbar-inverse navbar-fixed-top">
		        <div class="navbar-inner">
		            <div class="container-fluid">
					<form hidden="" id="navBar" th:action="@{/logout}" method="post">
						<input id="logoutURL" type="hidden" th:value="@{/logout}"/>
					</form>
		                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                </a>
		                <a class="brand" th:href="@{/}" ><img alt="" th:src="@{/resources/img/spark.png}" /></a>
			                <div class="nav-collapse collapse">
			                    <p class="navbar-text pull-right">
			
			                    </p>
			                    <ul class="nav" id="navigationBar">
				                    $actions$
				                    
			                        <li sec:authorize-url="/docs"><a th:href="@{/docs/searchRequisition}" th:text="#{label.requisitionsOption}"></a></li>
			                        <li sec:authorize-url="/docs"><a th:href="@{/docs/searchRequisitionDistribution}" th:text="#{label.requisitionDistributionOption}"></a></li>
			                        <li sec:authorize-url="/docs"><a th:href="@{/docs/searchQuotation}" th:text="#{label.quotationOption}"></a></li>
			                        <li sec:authorize-url="/docs"><a th:href="@{/docs/searchPurchaseOrder}" th:text="#{label.purchaseOrders}"></a></li>
			                        <li sec:authorize-url="/docs"><a th:href="@{/docs/searchItemReceipt}" th:text="#{label.itemReceiptOrder}"></a></li>
<!-- 			                        <li sec:authorize-url="/docs"><a th:href="@{/docs/purchaseOrderOtherValue}" th:text="#{label.purchaseOrderOtherValueOption}"></a></li> -->
									<li sec:authorize-url="/$module$"><a th:href="@{/$module$}" th:text="#{label.navbarConfiguration}"></a></li>
			                        <li sec:authorize-url="/$module$Settings"><a th:href="@{/$module$Settings}" th:text="#{label.settingsOption}"></a></li>
			                        <li sec:authorize-url="/preferences"><a th:href="@{/preferences}" th:text="#{label.languageOption}"></a></li>
			                        <li><a id="about-ajax-popup" class="about-ajax-popup" th:href="@{/about}" th:text="#{label.aboutOption}"></a></li>
			                        <li><a href=""><img onclick="var url=\$('#logoutURL').val();\$.ajax({type: 'POST',url: url});" th:src="@{/resources/img/logout.png}"/></a></li>
			                    </ul>
			                </div> <!--/.nav-collapse -->
		            </div>
		        </div>
		    </div>
		</div>
	<div class="container-fluid">	
		<div class="row-fluid">
			<div id="back"><a th:text="#{label.back}" th:href="@{/$backURL$}"></a></div>
			<h3 th:text="#{label.$tableName$Info}"> </h3>
			<form id="filter-form" th:object="\${$tableName$FilterInfo}" method="post" >
		  	<input id="selectedFilterPage" type="hidden" th:field="*{selectedFilterPage}"/>
		  	<input id="aaaaction" type="hidden" th:field="*{aaaaction}"/>
			<input id="aaddFilter" type="hidden" th:field="*{aaddFilter}"/>
			<input id="selectedFilterField" type="hidden" th:field="*{selectedFilterField}"/>
		  	<input id="changeSortingFieldDirection" type="hidden" th:field="*{changeSortingFieldDirection}"/>
		    <div style="float:left;"><b id="filterAsText" th:text="'Filter: '+\${ffilterAsText}"></b></div><div style="float:right;margin-top: 0;margin-bottom: 0;" id="unionOp" class='pagination pagination-left'><ul><li><a href="#" th:title='#{label.addFilter}' tabindex="-1" ><label onclick="addFilter();">U</label></a></li></ul></div>
			
			<table width="100%">
				<tr>
					<td width="33%"><div id="new" class='pagination pagination-left'><ul><li><a th:href="@{/$module$/$tableName$/create$entityName$/fromList}" th:title='#{label.newLine}'>+</a></li></ul></div></td>
					<td width="33%"><div th:include="normal/filterDataTable :: paginationbar" ></div></td>
					<td width="34%"><div align="right"><img style="cursor: pointer;" onclick="generateExcelFile();" th:src="@{/resources/img/excel.png}"/></div></td>
				</tr>
			</table>
			<table class="table table-striped" >
				<thead>
					<tr>
						$filterColumnHeaders$
					</tr>
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
			</form>
			<table width="100%">
				<tr>
					<td width="33%"></td>
					<td width="33%"><div th:include="normal/filterDataTable :: paginationbar" ></div></td>
					<td width="34%"><div></div></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="container-fluid" th:include="normal/layout$module$ :: footer"></div>
</body>
</html>