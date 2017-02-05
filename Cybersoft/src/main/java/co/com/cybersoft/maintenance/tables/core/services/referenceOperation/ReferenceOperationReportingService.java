package co.com.cybersoft.maintenance.tables.core.services.referenceOperation;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.referenceOperation.ReferenceOperationFilterInfo;

public interface ReferenceOperationReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ReferenceOperationFilterInfo filter) throws Exception;
	
}