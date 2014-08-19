<tr>
	<td ><b th:text="#{label.$docName$$upperFieldName$}+': '"></b></td>
	<td><select id="$fieldNameId$" th:field="*{$fieldNamePath$}">$optionalReference$<option th:each="reference : \${$docName$Info.$docName$BodyList}" th:value="\${reference.$fieldName$}" th:text="\${reference.$fieldName$}"></option></select></td>
	<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>