package co.com.cybersoft.maintenance.tables.core.services.technicalAction;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.technicalAction.TechnicalActionFilterInfo;

public interface TechnicalActionReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, TechnicalActionFilterInfo filter) throws Exception;
	
}