package co.com.cybersoft.maintenance.tables.core.services.conceptKardex;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexFilterInfo;

public interface ConceptKardexReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ConceptKardexFilterInfo filter) throws Exception;
	
}