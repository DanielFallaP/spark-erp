package co.com.cybersoft.purchase.tables.web.controller.country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.country.CountryReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import co.com.cybersoft.purchase.tables.web.domain.country.CountryFilterInfo;


@Controller
@RequestMapping("/purchase/country/export")
public class CountryExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CountryModificationController.class);

	@Autowired
	private CountryReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Country to Excel");
		return reportingService.toExcel(Country.class.getCanonicalName(), CountryDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CountryFilterInfo) (request.getSession().getAttribute("countryFilter")!=null?request.getSession().getAttribute("countryFilter"):new CountryFilterInfo()));
	}
	
}