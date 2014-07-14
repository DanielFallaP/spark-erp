<td th:if="!\${$fieldName$Exception}" colspan="2"></td>
<td th:if="\${$fieldName$Exception}" colspan="2"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>