package co.com.cybersoft.maintenance.tables.web.controller.referenceOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.referenceOperation.ReferenceOperationReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.ReferenceOperation;
import co.com.cybersoft.maintenance.tables.core.domain.ReferenceOperationDetails;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationFilterInfo;


@Controller
@RequestMapping("/maintenance/referenceOperation/export")
public class ReferenceOperationExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ReferenceOperationModificationController.class);

	@Autowired
	private ReferenceOperationReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting ReferenceOperation to Excel");
		return reportingService.toExcel(ReferenceOperation.class.getCanonicalName(), ReferenceOperationDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ReferenceOperationFilterInfo) (request.getSession().getAttribute("referenceOperationFilter")!=null?request.getSession().getAttribute("referenceOperationFilter"):new ReferenceOperationFilterInfo()));
	}
	
}