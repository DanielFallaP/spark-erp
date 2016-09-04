<tr>
 			<td ><b th:text="#{label.$tableName$$upperFieldName$}+': '"></b></td>
   			<td >
   				<input th:if="\${_loggedInUser.$tableName$Update}" type="checkbox" th:field="*{$fieldName$}" />
   				<input th:if="\${_loggedInUser.$tableName$Update} != true" disabled="disabled" type="checkbox" th:field="*{$fieldName$}" />
   			</td>
</tr>