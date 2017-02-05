package co.com.cybersoft.maintenance.tables.core.services.responsible;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.responsible.ResponsibleFilterInfo;

public interface ResponsibleReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ResponsibleFilterInfo filter) throws Exception;
	
}