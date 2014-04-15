<tr>
	<td th:text="#{label.$tableName$$upperFieldName$}+': '"></td>
	<td><select th:field="*{$fieldName$}"><option th:each="reference : \${$tableName$Info.$fieldName$List}" th:value="\${reference.$displayName$}" th:text="\${reference.$displayName$}"></option></select></td>
</tr>