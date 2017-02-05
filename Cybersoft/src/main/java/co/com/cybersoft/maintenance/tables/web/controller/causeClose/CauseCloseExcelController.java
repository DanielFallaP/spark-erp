package co.com.cybersoft.maintenance.tables.web.controller.causeClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.causeClose.CauseCloseReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.CauseClose;
import co.com.cybersoft.maintenance.tables.core.domain.CauseCloseDetails;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseFilterInfo;


@Controller
@RequestMapping("/maintenance/causeClose/export")
public class CauseCloseExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CauseCloseModificationController.class);

	@Autowired
	private CauseCloseReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting CauseClose to Excel");
		return reportingService.toExcel(CauseClose.class.getCanonicalName(), CauseCloseDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CauseCloseFilterInfo) (request.getSession().getAttribute("causeCloseFilter")!=null?request.getSession().getAttribute("causeCloseFilter"):new CauseCloseFilterInfo()));
	}
	
}