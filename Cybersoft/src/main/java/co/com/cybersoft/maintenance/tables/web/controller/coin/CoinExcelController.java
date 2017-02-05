package co.com.cybersoft.maintenance.tables.web.controller.coin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.coin.CoinReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;
import co.com.cybersoft.maintenance.tables.web.domain.coin.CoinFilterInfo;


@Controller
@RequestMapping("/maintenance/coin/export")
public class CoinExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CoinModificationController.class);

	@Autowired
	private CoinReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Coin to Excel");
		return reportingService.toExcel(Coin.class.getCanonicalName(), CoinDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CoinFilterInfo) (request.getSession().getAttribute("coinFilter")!=null?request.getSession().getAttribute("coinFilter"):new CoinFilterInfo()));
	}
	
}