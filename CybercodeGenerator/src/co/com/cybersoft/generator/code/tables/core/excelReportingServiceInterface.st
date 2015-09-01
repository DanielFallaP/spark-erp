package co.com.cybersoft.$module$.tables.core.services.$tableName$;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.$module$.tables.web.domain.$tableName$.$entityName$FilterInfo;

public interface $entityName$ReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, $entityName$FilterInfo filter) throws Exception;
	
}
