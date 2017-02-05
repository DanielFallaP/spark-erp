package co.com.cybersoft.maintenance.tables.web.controller.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.operation.OperationReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Operation;
import co.com.cybersoft.maintenance.tables.core.domain.OperationDetails;
import co.com.cybersoft.maintenance.tables.web.domain.operation.OperationFilterInfo;


@Controller
@RequestMapping("/maintenance/operation/export")
public class OperationExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(OperationModificationController.class);

	@Autowired
	private OperationReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Operation to Excel");
		return reportingService.toExcel(Operation.class.getCanonicalName(), OperationDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(OperationFilterInfo) (request.getSession().getAttribute("operationFilter")!=null?request.getSession().getAttribute("operationFilter"):new OperationFilterInfo()));
	}
	
}