<tr>
	<td ><b th:text="#{label.$docName$$upperFieldName$}+': '"></b></td>
	<td><input id="$fieldNameId$" readonly="readonly" type="text" th:field="*{$fieldNamePath$}"></input></td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>