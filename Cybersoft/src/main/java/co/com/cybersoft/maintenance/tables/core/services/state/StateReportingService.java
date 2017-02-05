package co.com.cybersoft.maintenance.tables.core.services.state;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateFilterInfo;

public interface StateReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, StateFilterInfo filter) throws Exception;
	
}