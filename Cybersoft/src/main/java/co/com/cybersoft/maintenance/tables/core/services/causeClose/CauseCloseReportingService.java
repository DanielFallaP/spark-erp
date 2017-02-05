package co.com.cybersoft.maintenance.tables.core.services.causeClose;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.causeClose.CauseCloseFilterInfo;

public interface CauseCloseReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CauseCloseFilterInfo filter) throws Exception;
	
}