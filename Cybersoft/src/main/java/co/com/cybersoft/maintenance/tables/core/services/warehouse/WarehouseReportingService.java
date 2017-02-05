package co.com.cybersoft.maintenance.tables.core.services.warehouse;

import java.io.File;
import java.util.Locale;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.web.domain.warehouse.WarehouseFilterInfo;

public interface WarehouseReportingService {
	ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, WarehouseFilterInfo filter) throws Exception;
	
}