package co.com.cybersoft.maintenance.tables.core.services.typeSorter;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterFilterInfo;

public interface TypeSorterReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, TypeSorterFilterInfo filter) throws Exception;
	
}