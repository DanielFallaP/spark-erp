package co.com.cybersoft.maintenance.tables.web.controller.storeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.storeType.StoreTypeReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.StoreType;
import co.com.cybersoft.maintenance.tables.core.domain.StoreTypeDetails;
import co.com.cybersoft.maintenance.tables.web.domain.storeType.StoreTypeFilterInfo;


@Controller
@RequestMapping("/maintenance/storeType/export")
public class StoreTypeExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(StoreTypeModificationController.class);

	@Autowired
	private StoreTypeReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting StoreType to Excel");
		return reportingService.toExcel(StoreType.class.getCanonicalName(), StoreTypeDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(StoreTypeFilterInfo) (request.getSession().getAttribute("storeTypeFilter")!=null?request.getSession().getAttribute("storeTypeFilter"):new StoreTypeFilterInfo()));
	}
	
}