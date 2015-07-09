package co.com.cybersoft.purchase.tables.web.controller.continent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.Continent;

//@Controller
@RequestMapping("/purchase/continent/export")
public class ContinentExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ContinentModificationController.class);

//	@Autowired
//	private ReportingService reportingService;
	
//	@RequestMapping(method=RequestMethod.GET)
//	public String toExcel() throws Exception{
//		LOG.debug("Exporting Continent to Excel");
//		return reportingService.toExcel(Continent.class.getCanonicalName(), LocaleContextHolder.getLocale());
//	}
	
}