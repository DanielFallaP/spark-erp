<tr>
	<td ><b th:text="#{label.$tableName$$upperFieldName$}+': '"></b></td>
	<td>
		<input th:if="\${_loggedInUser.$tableName$Update}" id="$fieldName$" type="text" th:field="*{$fieldName$}" />
		<input th:if="\${_loggedInUser.$tableName$Update} != true" disabled="disabled" id="$fieldName$" type="text" th:field="*{$fieldName$}" />
	</td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>