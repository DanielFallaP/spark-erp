package co.com.cybersoft.maintenance.tables.web.controller.typeWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.typeWork.TypeWorkReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeWork;
import co.com.cybersoft.maintenance.tables.core.domain.TypeWorkDetails;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkFilterInfo;


@Controller
@RequestMapping("/maintenance/typeWork/export")
public class TypeWorkExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeWorkModificationController.class);

	@Autowired
	private TypeWorkReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting TypeWork to Excel");
		return reportingService.toExcel(TypeWork.class.getCanonicalName(), TypeWorkDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(TypeWorkFilterInfo) (request.getSession().getAttribute("typeWorkFilter")!=null?request.getSession().getAttribute("typeWorkFilter"):new TypeWorkFilterInfo()));
	}
	
}