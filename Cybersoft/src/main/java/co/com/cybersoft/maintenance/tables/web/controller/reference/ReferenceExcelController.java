package co.com.cybersoft.maintenance.tables.web.controller.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.reference.ReferenceReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Reference;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceDetails;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceFilterInfo;


@Controller
@RequestMapping("/maintenance/reference/export")
public class ReferenceExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ReferenceModificationController.class);

	@Autowired
	private ReferenceReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Reference to Excel");
		return reportingService.toExcel(Reference.class.getCanonicalName(), ReferenceDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ReferenceFilterInfo) (request.getSession().getAttribute("referenceFilter")!=null?request.getSession().getAttribute("referenceFilter"):new ReferenceFilterInfo()));
	}
	
}