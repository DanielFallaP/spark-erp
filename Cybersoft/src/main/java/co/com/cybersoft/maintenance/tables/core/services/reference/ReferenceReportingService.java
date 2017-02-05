package co.com.cybersoft.maintenance.tables.core.services.reference;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.reference.ReferenceFilterInfo;

public interface ReferenceReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ReferenceFilterInfo filter) throws Exception;
	
}