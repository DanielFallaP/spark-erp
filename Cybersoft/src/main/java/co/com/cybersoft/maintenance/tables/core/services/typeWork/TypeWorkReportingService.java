package co.com.cybersoft.maintenance.tables.core.services.typeWork;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.typeWork.TypeWorkFilterInfo;

public interface TypeWorkReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, TypeWorkFilterInfo filter) throws Exception;
	
}