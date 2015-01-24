<th >
	<div>
		<a th:if="\${_field} == '$fieldName$' and \${_direction}" th:href="'javascript:submitFilterPage(1,\'$fieldName$\',true)'" th:text="'&uarr;'+#{label.$tableName$$upperFieldName$}"></a>
		<a th:if="\${_field} == '$fieldName$' and !\${_direction}" th:href="'javascript:submitFilterPage(1,\'$fieldName$\',true)'" th:text="'&darr;'+#{label.$tableName$$upperFieldName$}"></a>
		<a th:if="\${_field} != '$fieldName$'" th:href="'javascript:submitFilterPage(1,\'$fieldName$\',true)'" th:text="#{label.$tableName$$upperFieldName$}"></a>
	</div>
</th>