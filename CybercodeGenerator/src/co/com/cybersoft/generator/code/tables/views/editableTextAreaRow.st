<tr>
		        				<td ><b th:text="#{label.$tableName$$upperFieldName$}+': '"></b></td>
		        				<td>
		        					<textarea th:if="\${_loggedInUser.$tableName$Update}" th:field="*{$fieldName$}" ></textarea>
		        					<textarea th:if="\${_loggedInUser.$tableName$Update} != true" disabled="disabled" th:field="*{$fieldName$}" ></textarea>
		        				</td>
								<td th:if="\${$fieldName$Exception}"><div class="alert alert-error" th:text="#{\${$fieldName$ValidationErrorMessage}}"></div></td>
</tr>