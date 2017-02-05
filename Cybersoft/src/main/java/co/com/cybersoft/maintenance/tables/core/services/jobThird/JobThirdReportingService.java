package co.com.cybersoft.maintenance.tables.core.services.jobThird;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.jobThird.JobThirdFilterInfo;

public interface JobThirdReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, JobThirdFilterInfo filter) throws Exception;
	
}