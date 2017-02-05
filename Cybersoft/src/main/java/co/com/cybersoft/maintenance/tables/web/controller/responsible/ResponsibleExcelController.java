package co.com.cybersoft.maintenance.tables.web.controller.responsible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.responsible.ResponsibleReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Responsible;
import co.com.cybersoft.maintenance.tables.core.domain.ResponsibleDetails;
import co.com.cybersoft.maintenance.tables.web.domain.responsible.ResponsibleFilterInfo;


@Controller
@RequestMapping("/maintenance/responsible/export")
public class ResponsibleExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ResponsibleModificationController.class);

	@Autowired
	private ResponsibleReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Responsible to Excel");
		return reportingService.toExcel(Responsible.class.getCanonicalName(), ResponsibleDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ResponsibleFilterInfo) (request.getSession().getAttribute("responsibleFilter")!=null?request.getSession().getAttribute("responsibleFilter"):new ResponsibleFilterInfo()));
	}
	
}