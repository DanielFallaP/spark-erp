package co.com.cybersoft.maintenance.tables.core.services.operation;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.operation.OperationFilterInfo;

public interface OperationReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, OperationFilterInfo filter) throws Exception;
	
}