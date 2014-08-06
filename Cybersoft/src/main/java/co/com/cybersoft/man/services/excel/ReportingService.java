package co.com.cybersoft.man.services.excel;

import java.util.Locale;

public interface ReportingService {
	String toExcel(String className, Locale locale) throws Exception;
	
	void cleanupExcelDirectory() throws Exception;
}
