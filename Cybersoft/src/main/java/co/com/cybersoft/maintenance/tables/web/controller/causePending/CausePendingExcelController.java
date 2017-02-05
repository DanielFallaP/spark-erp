package co.com.cybersoft.maintenance.tables.web.controller.causePending;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.causePending.CausePendingReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.CausePending;
import co.com.cybersoft.maintenance.tables.core.domain.CausePendingDetails;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingFilterInfo;


@Controller
@RequestMapping("/maintenance/causePending/export")
public class CausePendingExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CausePendingModificationController.class);

	@Autowired
	private CausePendingReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting CausePending to Excel");
		return reportingService.toExcel(CausePending.class.getCanonicalName(), CausePendingDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CausePendingFilterInfo) (request.getSession().getAttribute("causePendingFilter")!=null?request.getSession().getAttribute("causePendingFilter"):new CausePendingFilterInfo()));
	}
	
}