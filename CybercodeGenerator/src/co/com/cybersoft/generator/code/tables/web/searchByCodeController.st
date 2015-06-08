package co.com.cybersoft.$module$.tables.web.controller.$tableName$;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;
import co.com.cybersoft.$module$.tables.core.services.$tableName$.$entityName$Service;
import co.com.cybersoft.$module$.tables.events.$tableName$.$entityName$PageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/$module$/$tableName$/getCodeList/{code}")
public class $entityName$SearchByCodeController {

	@Autowired
	private $entityName$Service $tableName$Service;
	
	@RequestMapping(method=RequestMethod.GET)
	public void get$entityName$Codes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		$entityName$PageEvent codesEvent = $tableName$Service.requestByCodePrefix(code);
		
		List<$entityName$Details> codes = codesEvent.get$entityName$List();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for ($entityName$Details $tableName$Details : codes) {
			responseBuff.append($tableName$Details.getCode());
			if (i!=codes.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}
