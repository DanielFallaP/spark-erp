package co.com.cybersoft.maintenance.tables.web.controller.typeCauseClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.typeCauseClose.TypeCauseCloseReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeCauseClose;
import co.com.cybersoft.maintenance.tables.core.domain.TypeCauseCloseDetails;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseFilterInfo;


@Controller
@RequestMapping("/maintenance/typeCauseClose/export")
public class TypeCauseCloseExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeCauseCloseModificationController.class);

	@Autowired
	private TypeCauseCloseReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting TypeCauseClose to Excel");
		return reportingService.toExcel(TypeCauseClose.class.getCanonicalName(), TypeCauseCloseDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(TypeCauseCloseFilterInfo) (request.getSession().getAttribute("typeCauseCloseFilter")!=null?request.getSession().getAttribute("typeCauseCloseFilter"):new TypeCauseCloseFilterInfo()));
	}
	
}