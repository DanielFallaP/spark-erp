package co.com.cybersoft.maintenance.tables.web.controller.failureCauseTechnicalaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction.FailureCauseTechnicalactionReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.FailureCauseTechnicalaction;
import co.com.cybersoft.maintenance.tables.core.domain.FailureCauseTechnicalactionDetails;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionFilterInfo;


@Controller
@RequestMapping("/maintenance/failureCauseTechnicalaction/export")
public class FailureCauseTechnicalactionExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(FailureCauseTechnicalactionModificationController.class);

	@Autowired
	private FailureCauseTechnicalactionReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting FailureCauseTechnicalaction to Excel");
		return reportingService.toExcel(FailureCauseTechnicalaction.class.getCanonicalName(), FailureCauseTechnicalactionDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(FailureCauseTechnicalactionFilterInfo) (request.getSession().getAttribute("failureCauseTechnicalactionFilter")!=null?request.getSession().getAttribute("failureCauseTechnicalactionFilter"):new FailureCauseTechnicalactionFilterInfo()));
	}
	
}