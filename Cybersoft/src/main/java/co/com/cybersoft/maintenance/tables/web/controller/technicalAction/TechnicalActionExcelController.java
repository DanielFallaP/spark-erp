package co.com.cybersoft.maintenance.tables.web.controller.technicalAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.technicalAction.TechnicalActionReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.TechnicalAction;
import co.com.cybersoft.maintenance.tables.core.domain.TechnicalActionDetails;
import co.com.cybersoft.maintenance.tables.web.domain.technicalAction.TechnicalActionFilterInfo;


@Controller
@RequestMapping("/maintenance/technicalAction/export")
public class TechnicalActionExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(TechnicalActionModificationController.class);

	@Autowired
	private TechnicalActionReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting TechnicalAction to Excel");
		return reportingService.toExcel(TechnicalAction.class.getCanonicalName(), TechnicalActionDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(TechnicalActionFilterInfo) (request.getSession().getAttribute("technicalActionFilter")!=null?request.getSession().getAttribute("technicalActionFilter"):new TechnicalActionFilterInfo()));
	}
	
}