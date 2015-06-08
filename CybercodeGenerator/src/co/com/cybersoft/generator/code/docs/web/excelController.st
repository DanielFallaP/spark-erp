package co.com.cybersoft.$module$.docs.web.controller.$docName$;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.$module$.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.$module$.docs.persistence.domain.$upperDocName$Body;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.$module$.tables.web.controller.exchangeRate.ExchangeRateModificationController;

@Controller
@RequestMapping("/docs/$docName$/export")
public class $upperDocName$ExcelController {
	private static final Logger LOG=LoggerFactory.getLogger($upperDocName$ExcelController.class);
	
	@Autowired
	private ReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String toExcel(String id) throws Exception{
		LOG.debug("Exporting $upperDocName$ to Excel");
		return reportingService.docToExcel($upperDocName$.class.getCanonicalName(), $upperDocName$Body.class.getCanonicalName(), LocaleContextHolder.getLocale(), Long.parseLong(id));
	}
}
