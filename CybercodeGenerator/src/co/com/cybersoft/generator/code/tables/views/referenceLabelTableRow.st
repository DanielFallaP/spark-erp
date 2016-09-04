<tr>
	<td ><b th:text="#{label.$tableName$$upperFieldName$}+': '"></b></td>
	<td>
		<select th:if="\${_loggedInUser.$tableName$Update}" th:field="*{$fieldName$Id}"><option value=""></option><option th:each="reference : \${$tableName$Info.$fieldName$List}" th:value="\${reference.id}" th:text="#{\${reference.$displayName$}}"></option></select>
		<select th:if="\${_loggedInUser.$tableName$Update} != true" disabled="disabled" th:field="*{$fieldName$Id}"><option value=""></option><option th:each="reference : \${$tableName$Info.$fieldName$List}" th:value="\${reference.id}" th:text="#{\${reference.$displayName$}}"></option></select>
	</td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>