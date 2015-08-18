package co.com.cybersoft.man.services.excel;

import java.io.File;
import java.util.Locale;

public interface ReportingService {
	String toExcel(String className, String detailsClassName, Locale locale) throws Exception;
	
	String docToExcel(String className, String bodyClassName, Locale locale, Long id) throws Exception;
	
	File docToExcelFile(String className, String bodyClassName, Locale locale, Long id) throws Exception;
	
	void cleanupExcelDirectory() throws Exception;
}
