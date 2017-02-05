package co.com.cybersoft.maintenance.tables.web.controller.maintenanceActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity.MaintenanceActivityReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceActivity;
import co.com.cybersoft.maintenance.tables.core.domain.MaintenanceActivityDetails;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityFilterInfo;


@Controller
@RequestMapping("/maintenance/maintenanceActivity/export")
public class MaintenanceActivityExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(MaintenanceActivityModificationController.class);

	@Autowired
	private MaintenanceActivityReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting MaintenanceActivity to Excel");
		return reportingService.toExcel(MaintenanceActivity.class.getCanonicalName(), MaintenanceActivityDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(MaintenanceActivityFilterInfo) (request.getSession().getAttribute("maintenanceActivityFilter")!=null?request.getSession().getAttribute("maintenanceActivityFilter"):new MaintenanceActivityFilterInfo()));
	}
	
}