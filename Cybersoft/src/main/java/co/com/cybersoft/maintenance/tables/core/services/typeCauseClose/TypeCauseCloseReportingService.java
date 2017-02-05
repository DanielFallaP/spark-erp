package co.com.cybersoft.maintenance.tables.core.services.typeCauseClose;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.typeCauseClose.TypeCauseCloseFilterInfo;

public interface TypeCauseCloseReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, TypeCauseCloseFilterInfo filter) throws Exception;
	
}