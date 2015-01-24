<th >
	<a th:if="\${_field} == '$fieldName$' and \${_direction}" th:href="'javascript:submitFilterPage(1,\'$fieldName$\',true)'" th:text="'&uarr;'+#{label.$docName$$upperFieldName$}"></a>
	<a th:if="\${_field} == '$fieldName$' and !\${_direction}" th:href="'javascript:submitFilterPage(1,\'$fieldName$\',true)'" th:text="'&darr;'+#{label.$docName$$upperFieldName$}"></a>
	<a th:if="\${_field} != '$fieldName$'" th:href="'javascript:submitFilterPage(1,\'$fieldName$\',true)'" th:text="#{label.$docName$$upperFieldName$}"></a>
</th>
