<tr>
	<td ><b th:text="#{label.$docName$$upperFieldName$}+': '"></b></td>
	<td><select th:field="*{$fieldNamePath$}"><option value=""></option><option th:each="reference : \${$docName$BodyInfo.$fieldName$List}" th:value="\${reference.$displayName$}" th:text="#{\${reference.$displayName$}}"></option></select></td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>