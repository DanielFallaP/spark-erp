package co.com.cybersoft.maintenance.tables.core.services.otherConcepts;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsFilterInfo;

public interface OtherConceptsReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, OtherConceptsFilterInfo filter) throws Exception;
	
}