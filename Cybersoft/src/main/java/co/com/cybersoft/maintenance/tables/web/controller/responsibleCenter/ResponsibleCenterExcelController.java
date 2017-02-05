package co.com.cybersoft.maintenance.tables.web.controller.responsibleCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.ResponsibleCenter;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleCenterDetails;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterFilterInfo;


@Controller
@RequestMapping("/maintenance/responsibleCenter/export")
public class ResponsibleCenterExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ResponsibleCenterModificationController.class);

	@Autowired
	private ResponsibleCenterReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting ResponsibleCenter to Excel");
		return reportingService.toExcel(ResponsibleCenter.class.getCanonicalName(), ResponsibleCenterDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ResponsibleCenterFilterInfo) (request.getSession().getAttribute("responsibleCenterFilter")!=null?request.getSession().getAttribute("responsibleCenterFilter"):new ResponsibleCenterFilterInfo()));
	}
	
}