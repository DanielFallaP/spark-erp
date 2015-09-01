package co.com.cybersoft.$module$.tables.web.controller.$tableName$;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.$module$.tables.core.services.$tableName$.$entityName$ReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;
import co.com.cybersoft.$module$.tables.core.domain.$entityName$Details;
import co.com.cybersoft.$module$.tables.web.domain.$tableName$.$entityName$FilterInfo;


@Controller
@RequestMapping("/$module$/$tableName$/export")
public class $entityName$ExcelController {
	private static final Logger LOG=LoggerFactory.getLogger($entityName$ModificationController.class);

	@Autowired
	private $entityName$ReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting $entityName$ to Excel");
		return reportingService.toExcel($entityName$.class.getCanonicalName(), $entityName$Details.class.getCanonicalName(), LocaleContextHolder.getLocale(),($entityName$FilterInfo) (request.getSession().getAttribute("$tableName$Filter")!=null?request.getSession().getAttribute("$tableName$Filter"):new $entityName$FilterInfo()));
	}
	
}
