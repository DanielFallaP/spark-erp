<th >
	<a th:if="\${_field} == '$fieldName$' and \${_direction}" th:href="@{\${page.url}+'?field=$fieldName$'}" th:text="'&uarr;'+#{label.$docName$$upperFieldName$}"></a>
	<a th:if="\${_field} == '$fieldName$' and !\${_direction}" th:href="@{\${page.url}+'?field=$fieldName$'}" th:text="'&darr;'+#{label.$docName$$upperFieldName$}"></a>
	<a th:if="\${_field} != '$fieldName$'" th:href="@{\${page.url}+'?field=$fieldName$'}" th:text="#{label.$docName$$upperFieldName$}"></a>
</th>
