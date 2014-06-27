package co.com.cybersoft.tables.web.controller.$tableName$;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.cybersoft.tables.web.domain.$tableName$.$entityName$Info;

$referenceServicesImports$


/**
 * Controller for $tableName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/$tableName$/retrieve$upperFieldName$")
public class $entityName$$upperFieldName$Controller {

	$referenceServicesDeclarations$
	
	@RequestMapping(value="/retrieve", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public $entityName$Info retrieveLists(HttpServletRequest request) throws Exception{
		$entityName$Info $tableName$ = new $entityName$Info();
		
		$compoundReferences$
		
		$setCompoundLists$

		return $tableName$;
	}
}
