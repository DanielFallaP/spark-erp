<input th:if='\${$docName$Info.showBody} $readyCondition$' type="button" th:value="#{label.deleteSelection}" onclick="submitDeletion();"/>