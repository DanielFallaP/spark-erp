package co.com.cybersoft.maintenance.tables.core.services.maintenanceWork;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.maintenanceWork.MaintenanceWorkFilterInfo;

public interface MaintenanceWorkReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, MaintenanceWorkFilterInfo filter) throws Exception;
	
}