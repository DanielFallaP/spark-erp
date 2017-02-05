package co.com.cybersoft.maintenance.tables.core.services.maintenanceActivity;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceActivity.MaintenanceActivityFilterInfo;

public interface MaintenanceActivityReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, MaintenanceActivityFilterInfo filter) throws Exception;
	
}