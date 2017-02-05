package co.com.cybersoft.maintenance.tables.web.controller.typeMaintenance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.typeMaintenance.TypeMaintenanceReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeMaintenance;
import co.com.cybersoft.maintenance.tables.core.domain.TypeMaintenanceDetails;
import co.com.cybersoft.maintenance.tables.web.domain.typeMaintenance.TypeMaintenanceFilterInfo;


@Controller
@RequestMapping("/maintenance/typeMaintenance/export")
public class TypeMaintenanceExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeMaintenanceModificationController.class);

	@Autowired
	private TypeMaintenanceReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting TypeMaintenance to Excel");
		return reportingService.toExcel(TypeMaintenance.class.getCanonicalName(), TypeMaintenanceDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(TypeMaintenanceFilterInfo) (request.getSession().getAttribute("typeMaintenanceFilter")!=null?request.getSession().getAttribute("typeMaintenanceFilter"):new TypeMaintenanceFilterInfo()));
	}
	
}