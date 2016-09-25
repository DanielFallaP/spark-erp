<tr>
 			<td ><b th:text="#{label.$tableName$$upperFieldName$}+': '"></b></td>
   			<td >
   				<input th:if="\${_loggedInUser.$tableName$$operation$}" type="checkbox" th:field="*{$fieldName$}" />
   				<input th:if="\${_loggedInUser.$tableName$$operation$} != true" disabled="disabled" type="checkbox" th:field="*{$fieldName$}" />
   			</td>
</tr>