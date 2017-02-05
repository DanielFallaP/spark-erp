package co.com.cybersoft.maintenance.tables.core.services.failureCauseTechnicalaction;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.failureCauseTechnicalaction.FailureCauseTechnicalactionFilterInfo;

public interface FailureCauseTechnicalactionReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, FailureCauseTechnicalactionFilterInfo filter) throws Exception;
	
}