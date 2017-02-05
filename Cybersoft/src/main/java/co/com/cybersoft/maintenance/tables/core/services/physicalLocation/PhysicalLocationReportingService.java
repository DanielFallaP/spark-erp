package co.com.cybersoft.maintenance.tables.core.services.physicalLocation;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationFilterInfo;

public interface PhysicalLocationReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, PhysicalLocationFilterInfo filter) throws Exception;
	
}