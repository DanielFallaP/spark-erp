package co.com.cybersoft.purchase.tables.web.controller.currencyCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeFilterInfo;


@Controller
@RequestMapping("/purchase/currencyCode/export")
public class CurrencyCodeExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CurrencyCodeModificationController.class);

	@Autowired
	private CurrencyCodeReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting CurrencyCode to Excel");
		return reportingService.toExcel(CurrencyCode.class.getCanonicalName(), CurrencyCodeDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CurrencyCodeFilterInfo) (request.getSession().getAttribute("currencyCodeFilter")!=null?request.getSession().getAttribute("currencyCodeFilter"):new CurrencyCodeFilterInfo()));
	}
	
}