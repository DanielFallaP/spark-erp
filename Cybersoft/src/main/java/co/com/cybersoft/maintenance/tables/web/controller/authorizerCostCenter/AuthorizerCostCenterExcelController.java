package co.com.cybersoft.maintenance.tables.web.controller.authorizerCostCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.authorizerCostCenter.AuthorizerCostCenterReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.AuthorizerCostCenter;
import co.com.cybersoft.maintenance.tables.core.domain.AuthorizerCostCenterDetails;
import co.com.cybersoft.maintenance.tables.web.domain.authorizerCostCenter.AuthorizerCostCenterFilterInfo;


@Controller
@RequestMapping("/maintenance/authorizerCostCenter/export")
public class AuthorizerCostCenterExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(AuthorizerCostCenterModificationController.class);

	@Autowired
	private AuthorizerCostCenterReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting AuthorizerCostCenter to Excel");
		return reportingService.toExcel(AuthorizerCostCenter.class.getCanonicalName(), AuthorizerCostCenterDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(AuthorizerCostCenterFilterInfo) (request.getSession().getAttribute("authorizerCostCenterFilter")!=null?request.getSession().getAttribute("authorizerCostCenterFilter"):new AuthorizerCostCenterFilterInfo()));
	}
	
}