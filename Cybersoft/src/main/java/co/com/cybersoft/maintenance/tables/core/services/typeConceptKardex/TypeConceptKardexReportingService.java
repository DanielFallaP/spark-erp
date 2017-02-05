package co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.typeConceptKardex.TypeConceptKardexFilterInfo;

public interface TypeConceptKardexReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, TypeConceptKardexFilterInfo filter) throws Exception;
	
}