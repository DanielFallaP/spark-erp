package co.com.cybersoft.web.controller.$tableName$;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.$entityName$Details;
import co.com.cybersoft.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.events.$tableName$.$entityName$PageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/$tableName$/getDescriptionList/{description}")
public class $entityName$SearchByDescriptionController {

	@Autowired
	private $entityName$Service $tableName$Service;
	
	@RequestMapping(method=RequestMethod.GET)
	public void get$entityName$Descriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		$entityName$PageEvent descriptionEvent = $tableName$Service.requestByContainingDescription(description);
		
		List<$entityName$Details> descriptions = descriptionEvent.get$entityName$List();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for ($entityName$Details $tableName$Details : descriptions) {
			responseBuff.append($tableName$Details.getDescription());
			if (i!=descriptions.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}
