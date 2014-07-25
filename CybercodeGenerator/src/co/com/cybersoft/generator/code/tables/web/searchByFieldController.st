package co.com.cybersoft.tables.web.controller.$tableName$;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.tables.core.domain.$entityName$Details;
import co.com.cybersoft.tables.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.tables.events.$tableName$.$entityName$PageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class $entityName$SearchBy$upperFieldName$Controller {

	@Autowired
	private $entityName$Service $tableName$Service;
	
	$searchMethods$
	@RequestMapping(value="/configuration/$tableName$/get$upperFieldName$List", method=RequestMethod.GET)
		public void get$entityName$List(String $fieldName$, HttpServletResponse response) throws Exception{
		$entityName$PageEvent $fieldName$Event = $tableName$Service.requestByContaining$upperFieldName$($fieldName$);
		
		List<$entityName$Details> $fieldName$List = $fieldName$Event.get$entityName$List();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for ($entityName$Details $tableName$Details : $fieldName$List) {
			responseBuff.append($tableName$Details.get$upperFieldName$());
			if (i!=$fieldName$List.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}
