package co.com.cybersoft.maintenance.tables.core.services.job;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobFilterInfo;

public interface JobReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, JobFilterInfo filter) throws Exception;
	
}