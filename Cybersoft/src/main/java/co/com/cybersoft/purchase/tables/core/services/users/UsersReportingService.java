package co.com.cybersoft.purchase.tables.core.services.users;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersFilterInfo;

public interface UsersReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, UsersFilterInfo filter) throws Exception;
	
}