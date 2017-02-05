package co.com.cybersoft.maintenance.tables.web.controller.failureCause;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.failureCause.FailureCauseReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCause;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseDetails;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseFilterInfo;


@Controller
@RequestMapping("/maintenance/failureCause/export")
public class FailureCauseExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(FailureCauseModificationController.class);

	@Autowired
	private FailureCauseReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting FailureCause to Excel");
		return reportingService.toExcel(FailureCause.class.getCanonicalName(), FailureCauseDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(FailureCauseFilterInfo) (request.getSession().getAttribute("failureCauseFilter")!=null?request.getSession().getAttribute("failureCauseFilter"):new FailureCauseFilterInfo()));
	}
	
}