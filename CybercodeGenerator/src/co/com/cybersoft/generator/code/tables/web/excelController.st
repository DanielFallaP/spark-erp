package co.com.cybersoft.tables.web.controller.$tableName$;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.tables.persistence.domain.$entityName$;
import java.util.Locale;

@Controller
@RequestMapping("/configuration/$tableName$/export")
public class $entityName$ExcelController {
	private static final Logger LOG=LoggerFactory.getLogger($entityName$ModificationController.class);

	@Autowired
	private ReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String toExcel() throws Exception{
		LOG.debug("Exporting $entityName$ to Excel");
		return reportingService.toExcel($entityName$.class.getCanonicalName(), LocaleContextHolder.getLocale());
	}
	
}