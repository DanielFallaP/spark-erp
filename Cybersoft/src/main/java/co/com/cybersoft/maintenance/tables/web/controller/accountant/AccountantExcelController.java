package co.com.cybersoft.maintenance.tables.web.controller.accountant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.accountant.AccountantReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Accountant;
import co.com.cybersoft.maintenance.tables.core.domain.AccountantDetails;
import co.com.cybersoft.maintenance.tables.web.domain.accountant.AccountantFilterInfo;


@Controller
@RequestMapping("/maintenance/accountant/export")
public class AccountantExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(AccountantModificationController.class);

	@Autowired
	private AccountantReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Accountant to Excel");
		return reportingService.toExcel(Accountant.class.getCanonicalName(), AccountantDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(AccountantFilterInfo) (request.getSession().getAttribute("accountantFilter")!=null?request.getSession().getAttribute("accountantFilter"):new AccountantFilterInfo()));
	}
	
}