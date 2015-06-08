package co.com.cybersoft.$module$.docs.web.controller.$docName$;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.$module$.docs.events.$docName$.$upperDocName$PageEvent;
import co.com.cybersoft.$module$.docs.persistence.services.$docName$.$upperDocName$PersistenceService;
import co.com.cybersoft.$module$.docs.web.domain.$docName$.$upperDocName$Info;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class $upperDocName$SearchByNumericIdController {

	@Autowired
	private $upperDocName$PersistenceService $docName$Service;
	
	@RequestMapping(value="/docs/$docName$/get$upperDocName$List", method=RequestMethod.GET)
	public void get$upperDocName$List(String numericId, HttpServletResponse response) throws Exception
	{
		$upperDocName$PageEvent $docName$Event = $docName$Service.requestByContainingNumericId(numericId);
		List<$upperDocName$Info> $docName$List = $docName$Event.get$upperDocName$List();
		StringBuffer responseBuff = new StringBuffer();
		int i=1;
		for ($upperDocName$Info $docName$Info : $docName$List) {
			responseBuff.append($docName$Info.getNumericId());
			if (i!=$docName$List.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
		
	}
}
