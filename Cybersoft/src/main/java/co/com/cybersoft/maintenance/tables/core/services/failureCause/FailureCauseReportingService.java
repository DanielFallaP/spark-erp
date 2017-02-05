package co.com.cybersoft.maintenance.tables.core.services.failureCause;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.failureCause.FailureCauseFilterInfo;

public interface FailureCauseReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, FailureCauseFilterInfo filter) throws Exception;
	
}