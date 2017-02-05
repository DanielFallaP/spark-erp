package co.com.cybersoft.purchase.tables.web.controller.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyFilterInfo;


@Controller
@RequestMapping("/purchase/currency/export")
public class CurrencyExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CurrencyModificationController.class);

	@Autowired
	private CurrencyReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Currency to Excel");
		return reportingService.toExcel(Currency.class.getCanonicalName(), CurrencyDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CurrencyFilterInfo) (request.getSession().getAttribute("currencyFilter")!=null?request.getSession().getAttribute("currencyFilter"):new CurrencyFilterInfo()));
	}
	
}