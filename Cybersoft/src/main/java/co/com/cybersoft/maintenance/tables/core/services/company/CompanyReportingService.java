package co.com.cybersoft.maintenance.tables.core.services.company;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyFilterInfo;

public interface CompanyReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, CompanyFilterInfo filter) throws Exception;
	
}