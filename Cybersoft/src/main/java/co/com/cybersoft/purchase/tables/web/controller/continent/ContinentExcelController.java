package co.com.cybersoft.purchase.tables.web.controller.continent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.continent.ContinentReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.Continent;
import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.web.domain.continent.ContinentFilterInfo;


@Controller
@RequestMapping("/purchase/continent/export")
public class ContinentExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ContinentModificationController.class);

	@Autowired
	private ContinentReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Continent to Excel");
		return reportingService.toExcel(Continent.class.getCanonicalName(), ContinentDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ContinentFilterInfo) (request.getSession().getAttribute("continentFilter")!=null?request.getSession().getAttribute("continentFilter"):new ContinentFilterInfo()));
	}
	
}