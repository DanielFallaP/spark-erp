package co.com.cybersoft.maintenance.tables.core.services.causePending;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.causePending.CausePendingFilterInfo;

public interface CausePendingReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CausePendingFilterInfo filter) throws Exception;
	
}