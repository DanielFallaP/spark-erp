						<tr th:if="$rowExceptions$\${requiredOnDateExcep8} or \${requiredOnDateException}">
		        			$fieldsExceptions$
		        			<td></td>
		        			<td></td>
		        			<td th:if="\${requiredOnDateException}" colspan="2"><div class="alert alert-error" th:text="#{\${requiredOnDateValidationErrorMessage}}"></div></td>
		        			<td></td>
		        			<td></td>
		        		</tr>		        			        			
		        		<tr>
		        			$fields$
        		 			<td style="padding:0 15px;"><b th:text="#{label.$docName$Stock}+': '"></b></td>
        		   			<td ><input type="checkbox" th:field="*{stock}" /></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$RequiredOnDate}+': '"></b></td>
		        			<td ><input id="requiredOnDate" name="requiredOnDate" type="text" th:value="*{{requiredOnDate}}" /></td>
		        			<td style="padding:0 15px;"><b th:text="#{label.$docName$Priority}+': '"></b></td>
		        			<td ><select th:field="*{priority}"><option th:each="reference : \${$docName$Info.priorityList}" th:value="\${reference.priority}" th:text="#{\${reference.priority}}"></option></select></td>
		        			<td th:if="\${priorityException}"><div class="alert alert-error" th:text="#{\${priorityValidationErrorMessage}}"></div></td>
		        		</tr>