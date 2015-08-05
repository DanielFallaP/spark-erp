<tr>
	<td ><b th:text="#{label.$tableName$$upperFieldName$}+': '"></b></td>
	<td><select id="$fieldName$" onChange="$fieldName$SelectedRefresh()" th:field="*{$fieldName$Id}">$optionalReference$<option th:each="reference : \${$tableName$Info.$fieldName$List}" th:value="\${reference.id}" th:text="\${reference.$displayName$}"></option></select></td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>