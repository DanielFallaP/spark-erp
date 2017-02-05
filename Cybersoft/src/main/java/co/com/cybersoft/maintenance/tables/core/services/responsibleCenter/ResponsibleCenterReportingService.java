package co.com.cybersoft.maintenance.tables.core.services.responsibleCenter;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterFilterInfo;

public interface ResponsibleCenterReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ResponsibleCenterFilterInfo filter) throws Exception;
	
}