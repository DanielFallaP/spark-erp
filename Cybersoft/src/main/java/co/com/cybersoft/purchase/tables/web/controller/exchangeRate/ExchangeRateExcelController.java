package co.com.cybersoft.purchase.tables.web.controller.exchangeRate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.exchangeRate.ExchangeRateReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;


@Controller
@RequestMapping("/purchase/exchangeRate/export")
public class ExchangeRateExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ExchangeRateModificationController.class);

	@Autowired
	private ExchangeRateReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting ExchangeRate to Excel");
		return reportingService.toExcel(ExchangeRate.class.getCanonicalName(), ExchangeRateDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ExchangeRateFilterInfo) (request.getSession().getAttribute("exchangeRateFilter")!=null?request.getSession().getAttribute("exchangeRateFilter"):new ExchangeRateFilterInfo()));
	}
	
}