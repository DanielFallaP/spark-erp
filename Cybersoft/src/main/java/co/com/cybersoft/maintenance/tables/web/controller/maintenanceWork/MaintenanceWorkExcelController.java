package co.com.cybersoft.maintenance.tables.web.controller.maintenanceWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.maintenanceWork.MaintenanceWorkReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceWork;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceWorkDetails;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkFilterInfo;


@Controller
@RequestMapping("/maintenance/maintenanceWork/export")
public class MaintenanceWorkExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(MaintenanceWorkModificationController.class);

	@Autowired
	private MaintenanceWorkReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting MaintenanceWork to Excel");
		return reportingService.toExcel(MaintenanceWork.class.getCanonicalName(), MaintenanceWorkDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(MaintenanceWorkFilterInfo) (request.getSession().getAttribute("maintenanceWorkFilter")!=null?request.getSession().getAttribute("maintenanceWorkFilter"):new MaintenanceWorkFilterInfo()));
	}
	
}