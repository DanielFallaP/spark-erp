package co.com.cybersoft.maintenance.tables.core.services.third;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.third.ThirdFilterInfo;

public interface ThirdReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ThirdFilterInfo filter) throws Exception;
	
}