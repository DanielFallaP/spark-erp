package co.com.cybersoft.maintenance.tables.core.services.measurementUnit;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitFilterInfo;

public interface MeasurementUnitReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, MeasurementUnitFilterInfo filter) throws Exception;
	
}