package co.com.cybersoft.maintenance.tables.core.services.characteristic;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicFilterInfo;

public interface CharacteristicReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CharacteristicFilterInfo filter) throws Exception;
	
}