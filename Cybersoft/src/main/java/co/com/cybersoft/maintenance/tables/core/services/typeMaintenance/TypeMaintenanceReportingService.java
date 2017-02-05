package co.com.cybersoft.maintenance.tables.core.services.typeMaintenance;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.typeMaintenance.TypeMaintenanceFilterInfo;

public interface TypeMaintenanceReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, TypeMaintenanceFilterInfo filter) throws Exception;
	
}