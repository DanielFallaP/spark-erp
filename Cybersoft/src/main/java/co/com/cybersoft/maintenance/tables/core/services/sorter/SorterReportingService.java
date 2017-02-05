package co.com.cybersoft.maintenance.tables.core.services.sorter;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterFilterInfo;

public interface SorterReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, SorterFilterInfo filter) throws Exception;
	
}