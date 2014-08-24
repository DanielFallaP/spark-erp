<tr>
	<td ><b th:text="#{label.$docName$$upperFieldName$}$append$+': '"></b></td>
	<td><select id="$fieldNamePath$" onChange="$fieldName$SelectedRefresh()" th:field="*{$fieldNamePath$}">$optionalReference$<option th:each="reference : \${$docName$BodyInfo.$fieldName$List}" th:value="\${reference.$displayName$}" th:text="\${reference.$displayName$}"></option></select></td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>